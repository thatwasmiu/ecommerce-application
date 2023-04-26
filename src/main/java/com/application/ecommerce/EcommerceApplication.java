package com.application.ecommerce;

import com.application.ecommerce.model.category.Category;
import com.application.ecommerce.model.category.CategoryType;
import com.application.ecommerce.model.product.Product;
import com.application.ecommerce.repository.CategoryRepo;
import com.application.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class EcommerceApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

//    @Bean
//    ApplicationRunner applicationRunner(ProductRepository repo, CategoryRepo categoryRepo) {
//        return args -> {
//            Category category1 = categoryRepo.save(
//                    Category.builder()
//                            .type(CategoryType.BOOK)
//                            .build()
//            );
//            Category category2 = categoryRepo.save(
//                    Category.builder()
//                            .type(CategoryType.BENCH)
//                            .build()
//            );
//            Category category3 = categoryRepo.save(
//                    Category.builder()
//                            .type(CategoryType.BAG)
//                            .build()
//            );
//            Category category4 = categoryRepo.save(
//                    Category.builder()
//                            .type(CategoryType.DESK)
//                            .build()
//            );
//            Product product1 = repo.save(
//                    Product.builder()
//                            .name("bench")
//                            .category(category2)
//                            .description("Just some bench")
//                            .price(400.0)
//                            .deleted(false)
//                            .build()
//            );
//            Product product2 = repo.save(
//                    Product.builder()
//                            .name("book")
//                            .category(category1)
//                            .description("Just some book")
//                            .price(500.0)
//                            .deleted(false)
//                            .build()
//            );
//            Product product3 = repo.save(
//                    Product.builder()
//                            .name("desk")
//                            .category(category4)
//                            .description("Just some desk")
//                            .price(300.0)
//                            .deleted(false)
//                            .build()
//            );
//            Product product4 = repo.save(
//                    Product.builder()
//                            .name("bag")
//                            .category(category3)
//                            .description("Just some bag")
//                            .price(250.0)
//                            .deleted(false)
//                            .build()
//            );
//        };
//    }

//            Category category1 = categoryRepo.save(
//                    Category.builder()
//                            .type(CategoryType.BOOK)
//                            .build()
//            );
//
//            List<Product> list = new ArrayList<>(4000);
//
//            int totalObjects = 70000;
//
//            for (int i = 0; i < totalObjects; i++) {
//                list.add(Product.builder()
//                        .name("Test Book")
//                        .category(category1)
//                        .description("Just some test book")
//                        .price(250.0)
//                        .deleted(false)
//                        .build());
//            }

            // save all
//            int batchSize = 1000;
//            long startTime = System.nanoTime();
//
//            for (int i = 0; i < totalObjects; i = i + batchSize) {
//                System.out.println(i);
//                if( i + batchSize > totalObjects){
//                    List<Product> products = list.subList(i, totalObjects - 1);
//                    repo.saveAll(products);
//                    break;
//                }
//                List<Product> products = list.subList(i, i + batchSize);
//                repo.saveAll(products);
//            }

//            repo.saveAll(list);


//            long endTime   = System.nanoTime();
//            long totalTime = endTime - startTime;
//            System.out.println("Saveall performance: ");
//            System.out.println(totalTime + "nanosecond");
//
//            long convert = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
//
//            System.out.println(convert + " seconds");


//            // jdbstemplate
//            long stTime = System.nanoTime();
//            insertBatch(list);
//            long nTime   = System.nanoTime();
//            long toeTime = nTime - stTime;
//            System.out.println("jdbc performance: ");
//            System.out.println(toeTime + "nanosecond");
//
//            long convertt = TimeUnit.SECONDS.convert(toeTime, TimeUnit.NANOSECONDS);
//
//            System.out.println(convertt + " seconds");



//        };
//
//    }
//
//    public void insertBatch(List<Product> products) {
//        jdbcTemplate.batchUpdate("INSERT INTO product (name, description, price, deleted) values (?, ?, ?, ?)",
//                products,
//                100,
//                (PreparedStatement ps, Product product) -> {
//                });
//
//    }
}
