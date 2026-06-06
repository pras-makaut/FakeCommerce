package com.example.FakeCommerce.Repositories;

import com.example.FakeCommerce.Config.TestConfig;
import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Category;
import com.example.FakeCommerce.Schema.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.AutoConfigureDataJpa;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.AssertionErrors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Import(TestConfig.class)
public class ProductepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProductRepositry productRepositry;

    private Category category;
    private Product product;

    @BeforeEach
    void setUp() {
        // arrange.
        category = Category.builder().name("Electronics").build();
        product = Product.builder()
                .name("Phone")
                .description("A phone")
                .price(BigDecimal.valueOf(999.0).setScale(2, RoundingMode.HALF_UP))
                .ratings(BigDecimal.valueOf(4.5).setScale(2, RoundingMode.HALF_UP))
                .category(category)
                .build();

        testEntityManager.persistAndFlush(category);
        testEntityManager.persistAndFlush(product);

        testEntityManager.clear();
    }
    @Test
    void findProductWithDetailsById_whenFound_returnsProductWithCategory() {
        // act.
        List<Product> result = productRepositry.findProductWithDetailById(product.getId());

        // assert.
        assertEquals(1, result.size());
        assertEquals(category, result.get(0).getCategory());
        assertEquals(product.getName(), result.get(0).getName());
        assertEquals(product.getDescription(), result.get(0).getDescription());
        assertEquals(product.getPrice(), result.get(0).getPrice());
        assertEquals(product.getRatings(), result.get(0).getRatings());
        assertEquals(product.getImage(), result.get(0).getImage());
        assertEquals(product.getCategory().getName(), result.get(0).getCategory().getName());
    }

}
