package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Example used to demonstrate an exception handling change
 * introduced when upgrading from Java 8 to Java 9.
 */
public class ProductPriceCache {

    // Simulated in-memory price cache
    private static final Map<String, Double> priceCache = new HashMap<>();
    public static void main(String[] args) {
        // Simulate a cache that already contains some known entries,
        // as would be the case in a typical long-running application
        priceCache.put("apple", 1.0);
        priceCache.put("apple_discounted", 0.9);
        priceCache.put("banana", 0.5);
        priceCache.put("banana_discounted", 0.45);

        // Attempt to retrieve a product that isn't yet cached
        // This triggers computeIfAbsent, which will simulate an API call
        double price = getProductPrice("orange");
        System.out.println("Price of orange: $" + price);
    }

    /**
     * Retrieves a product price from the cache.
     * <p>
     * If not found, simulates fetching the price from an external API.
     * Also tries to insert a discounted price during the same call.
     */
    public static double getProductPrice(String product) {
        return priceCache.computeIfAbsent(product, key -> {
            // Simulate external API call
            double fetchedPrice = fetchPriceFromAPI(product);

            // Add a "default discount" entry inside the same map
            priceCache.put(product + "_discounted", fetchedPrice * 0.9);

            return fetchedPrice;
        });
    }

    /**
     * Simulates fetching the price from an external API.
     */
    private static double fetchPriceFromAPI(String product) {
        System.out.println("Fetching price for " + product + " from API...");
        return 2.0;
    }
}
