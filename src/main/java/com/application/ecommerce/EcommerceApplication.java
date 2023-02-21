package com.application.ecommerce;

import com.application.ecommerce.model.Product;
import com.application.ecommerce.repository.ProductRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

//    @Bean
//    ApplicationRunner applicationRunner(ProductRepository repo) {
//        return args -> {
//            Product product1 = repo.save(
//                    Product.builder()
//                            .name("bench")
//                            .description("Just some bench")
//                            .price(400.0)
//                            .build()
//            );
//            Product product2 = repo.save(
//                    Product.builder()
//                            .name("book")
//                            .description("Just some book")
//                            .price(500.0)
//                            .build()
//            );
//            Product product3 = repo.save(
//                    Product.builder()
//                            .name("desk")
//                            .description("Just some desk")
//                            .price(300.0)
//                            .build()
//            );
//        };
//    }
}
