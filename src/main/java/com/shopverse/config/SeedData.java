package com.shopverse.config;

import com.shopverse.product.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;

@Configuration
public class SeedData {
    @Bean
    CommandLineRunner seedProducts(ProductRepository repo) {
        return args -> {
            if (repo.count() > 0) return;
            repo.saveAll(List.of(
                new Product("Wireless Noise-Cancelling Headphones","Electronics","Immersive sound, comfortable fit and long battery life.",new BigDecimal("2999.00"),new BigDecimal("4999.00"),"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=800&q=80",4.5,1284,30),
                new Product("Everyday Running Shoes","Fashion","Lightweight everyday trainers for walks and workouts.",new BigDecimal("1899.00"),new BigDecimal("2999.00"),"https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&fit=crop&w=800&q=80",4.3,876,24),
                new Product("Minimal Desk Lamp","Home","Warm adjustable light for reading, studying and work.",new BigDecimal("999.00"),new BigDecimal("1499.00"),"https://images.unsplash.com/photo-1507473885765-e6ed057f782c?auto=format&fit=crop&w=800&q=80",4.6,324,18),
                new Product("Smart Fitness Watch","Electronics","Activity tracking, notifications and everyday fitness insights.",new BigDecimal("2499.00"),new BigDecimal("3999.00"),"https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=800&q=80",4.2,964,17),
                new Product("Travel Backpack 28L","Fashion","Organised compartments for commuting and weekend trips.",new BigDecimal("1599.00"),new BigDecimal("2199.00"),"https://images.unsplash.com/photo-1553062407-98eeb64c6a62?auto=format&fit=crop&w=800&q=80",4.4,542,21),
                new Product("Ceramic Coffee Mug Set","Home","A set of clean, durable ceramic mugs for daily coffee.",new BigDecimal("699.00"),new BigDecimal("999.00"),"https://images.unsplash.com/photo-1514228742587-6b1558fcca3d?auto=format&fit=crop&w=800&q=80",4.7,211,40)
            ));
        };
    }
}
