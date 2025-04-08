package com.example;

import org.apache.commons.codec.binary.Base64;

/**
 * Example used to demonstrate a contract change
 * introduced by updating Apache Commons Codec from version 1.4 to 1.6.
 * <p>
 * Simulates a simple authentication system where passwords are encoded using Base64.
 * The system compares the encoded input against a stored value in the database.
 */
public class AuthenticationSystem {

    // Stored Base64-encoded password (version 1.4) - Simulating what was previously saved
    private static final String STORED_ENCODED_PASSWORD_V1_4 = "U2VjdXJlUGFzc3dvcmQxMjM=\r\n";
    public static void main(String[] args) {
        // User-provided password input
        String password = "SecurePassword123";

        // Simulate authentication attempt
        boolean isAuthenticated = authenticateUser(password);
        System.out.println("Authentication Successful? " + isAuthenticated);
    }

    /**
     * Simulates user authentication by encoding the input password
     * and comparing it against a stored Base64-encoded version.
     *
     */
    private static boolean authenticateUser(String inputPassword) {
        // Encode the input password using Base64
        String encodedPassword = Base64.encodeBase64String(inputPassword.getBytes());

        // Compare with the stored password
        return encodedPassword.equals(STORED_ENCODED_PASSWORD_V1_4);
    }
}
