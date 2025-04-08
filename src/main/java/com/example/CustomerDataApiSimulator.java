package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Example used to demonstrate a runtime behavior change
 * introduced by updating Jackson from version 2.13.0 to 2.14.0.
 */
public class CustomerDataApiSimulator {

    public static void main(String[] args) throws JsonProcessingException {
        // Simulate an API call and print the serialized JSON response
        String jsonResponse = simulateApiCall();
        System.out.println("API Response: " + jsonResponse);
    }

    /**
     * Simulated API method that returns serialized customer data.
     * <p>
     * In a real application, this would be the output of a REST endpoint.
     */
    public static String simulateApiCall() throws JsonProcessingException {
        CustomerDataResponse response = new CustomerDataResponse();

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }
}

/**
 * Data class representing a typical API response.
 * <p>
 * Simulates a field deprecation scenario where a legacy field
 * is being phased out in favor of a modern alternative.
 */
class CustomerDataResponse {
    public String requestId = "REQ-00123"; // Unique request identifier
    public String legacyData = "Legacy field still used by old clients"; // To be deprecated
    public String newData = "Modern version of legacy data"; // Recommended replacement

    /**
     * Getter method for legacyData.
     * <p>
     * The combination of @JsonIgnore and @JsonProperty creates
     * version-specific behavior in Jackson:
     * <p>
     * - Jackson 2.13.0: public field is still serialized
     * <p>
     * - Jackson 2.14.0: getter takes precedence and the field is ignored
     */
    @JsonProperty
    @JsonIgnore // Applied to gradually phase out legacyData
    public String getLegacyData() {
        return legacyData;
    }
}
