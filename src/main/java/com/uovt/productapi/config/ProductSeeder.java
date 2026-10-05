package com.uovt.productapi.config;

import com.uovt.productapi.model.Product;
import com.uovt.productapi.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {

        if (productRepository.count() > 0) {
            return;
        }

        productRepository.save(new Product("Laptop", 250000.00, 10));
        productRepository.save(new Product("Desktop Computer", 180000.00, 8));
        productRepository.save(new Product("Monitor", 55000.00, 15));
        productRepository.save(new Product("Keyboard", 4500.00, 30));
        productRepository.save(new Product("Mouse", 2500.00, 40));
        productRepository.save(new Product("Headphones", 7500.00, 25));
        productRepository.save(new Product("Webcam", 12000.00, 12));
        productRepository.save(new Product("USB Flash Drive 64GB", 3500.00, 50));
        productRepository.save(new Product("External HDD 1TB", 22000.00, 18));
        productRepository.save(new Product("SSD 512GB", 28000.00, 20));

        productRepository.save(new Product("RAM 8GB DDR4", 8500.00, 25));
        productRepository.save(new Product("RAM 16GB DDR4", 16000.00, 15));
        productRepository.save(new Product("Graphics Card", 95000.00, 5));
        productRepository.save(new Product("Motherboard", 42000.00, 10));
        productRepository.save(new Product("Power Supply 650W", 18000.00, 14));
        productRepository.save(new Product("Computer Case", 15000.00, 12));
        productRepository.save(new Product("WiFi Router", 8500.00, 20));
        productRepository.save(new Product("Network Cable 10m", 1800.00, 35));
        productRepository.save(new Product("HDMI Cable", 2200.00, 30));
        productRepository.save(new Product("USB-C Cable", 1500.00, 45));

        productRepository.save(new Product("Smartphone", 125000.00, 10));
        productRepository.save(new Product("Tablet", 85000.00, 8));
        productRepository.save(new Product("Power Bank", 6500.00, 25));
        productRepository.save(new Product("Bluetooth Speaker", 9500.00, 18));
        productRepository.save(new Product("Smart Watch", 18000.00, 15));
        productRepository.save(new Product("Printer", 45000.00, 7));
        productRepository.save(new Product("Scanner", 38000.00, 6));
        productRepository.save(new Product("UPS 1200VA", 24000.00, 10));
        productRepository.save(new Product("Laptop Stand", 5500.00, 20));
        productRepository.save(new Product("USB Hub", 3500.00, 30));

        System.out.println("30 products seeded successfully!");
    }
}