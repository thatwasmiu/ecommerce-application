package com.application.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testAuthentication() {
        AuthenticationMokito.runTest();
    }

    @Test
    void testOrderModule() {
        OrderMockito.runTest();
    }

    @Test
    void testProductModule() {
        ProductMockito.runTest();
    }

    @Test
    void testVoucherModule() {
        VoucherMockito.runTest();
    }

    @Test
    void testCategoryModule() {
        CategoryMockito.runTest();
    }

}
