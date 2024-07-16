package com.vilarj.movies.validators;

/**
 * Validation class containing helpers/utilities to help validate parameters/arguments.
 */
public class Validation {
    /**
     * Helper method to validate time window parameter
     */
    public static boolean isValidTimeWindow(String time_window) {
        return time_window.equals("day") || time_window.equals("week");
    }

    /**
     * Validates the provided API key.
     * <p>
     * This method performs a basic validation to check if the provided API key is null or empty.
     *
     * @param apiKey {String} - The API key string to validate.
     * @return true - If the API key passes the basic validation (not null or empty).
     * </p>
     * @throws IllegalArgumentException - If the provided API key is null or empty.
     */
    public static boolean isValidAPIKey(String apiKey) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API KEY cannot be null or empty");
        }
        return true;
    }
}
