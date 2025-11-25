package com.cmed.prescriptionapp.service;

import com.cmed.prescriptionapp.domain.CdsAnalysisRequest;
import com.cmed.prescriptionapp.domain.CdsAnalysisResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CdsService {

    @Value("${gcp.generative-language.api.key}")
    private String apiKey;

    private final WebClient webClient;

    private static final String DISCLAIMER = "AI-generated analysis for educational purposes. Not for clinical use. Verify all information.";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";

    public CdsService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
    }

    public CdsAnalysisResponse analyzePrescription(CdsAnalysisRequest request) throws IOException {
        String prompt = buildPrompt(request);

        // Construct the request body for the Generative Language API
        GeminiRequest apiRequest = new GeminiRequest(
                Collections.singletonList(new Content(
                        Collections.singletonList(new Part(prompt))
                ))
        );

        // Make the REST call
        Mono<JsonNode> responseMono = webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(apiRequest)
                .retrieve()
                .bodyToMono(JsonNode.class);

        // Block and get the result (for simplicity in this context)
        // In a fully reactive application, you would continue the chain.
        JsonNode response = responseMono.block();

        // Extract the text from the complex JSON response
        String analysisText = "No analysis available.";
        if (response != null && response.has("candidates")) {
            JsonNode candidates = response.get("candidates");
            if (candidates.isArray() && !candidates.isEmpty()) {
                JsonNode content = candidates.get(0).get("content");
                if (content != null && content.has("parts")) {
                    JsonNode parts = content.get("parts");
                    if (parts.isArray() && !parts.isEmpty()) {
                        analysisText = parts.get(0).get("text").asText();
                    }
                }
            }
        }

        return new CdsAnalysisResponse(analysisText, DISCLAIMER);
    }

    private String buildPrompt(CdsAnalysisRequest request) {
        // The prompt remains the same, as it's the core of the logic.
        StringBuilder sb = new StringBuilder();
        sb.append("You are an expert clinical pharmacologist providing decision support. Analyze the following draft prescription. ");
        sb.append("Provide a concise, bullet-pointed list of potential issues and suggestions. ");
        sb.append("Categorize your feedback into 'Drug-Drug Interactions', 'Diagnosis Mismatch', and 'General Suggestions'. ");
        sb.append("Do not include any introductory or concluding conversational text. Be direct and clinical.\\n\\n");
        sb.append("--- PATIENT DATA ---\\n");
        sb.append("Age: ").append(request.getPatientAge()).append("\\n");
        sb.append("Gender: ").append(request.getPatientGender()).append("\\n\\n");
        sb.append("--- PRESCRIPTION ---\\n");
        sb.append("Diagnosis/Symptoms: ").append(request.getDiagnosis()).append("\\n");
        sb.append("Medications: ").append(String.join(", ", request.getMedicines())).append("\\n\\n");
        sb.append("--- ANALYSIS ---\\n");
        return sb.toString();
    }

    // --- Inner classes to model the Gemini API's JSON structure ---

    @Data
    private static class GeminiRequest {
        private final List<Content> contents;
    }

    @Data
    private static class Content {
        private final List<Part> parts;
    }

    @Data
    private static class Part {
        private final String text;
    }
}
