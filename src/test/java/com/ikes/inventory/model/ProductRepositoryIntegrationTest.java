package com.ikes.inventory.model;

import com.ikes.inventory.TestContainerApplicationContextInitializer;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {TestContainerApplicationContextInitializer.class})
public class ProductRepositoryIntegrationTest {
  @Autowired
  private ProductRepository productRepository;

  @Test
  @Transactional
  void shouldCreateAndRetrieveAProduct() {
    Product product = new Product();
    product.setName("Test product");
    product.setDescription("This is a description of a test product");

    productRepository.save(product);
    long productId = product.getId();
    Optional<Product> foundProduct = productRepository.findById(productId);

    assertThat(foundProduct).isPresent();
    assertThat(foundProduct.get().getName()).isEqualTo("Test product");
  }

  @Test
  @Transactional
  void shouldCreateUniqueIds() {
    Product p1 = new Product()
      .setName("p1")
      .setDescription("p2");
    Product p2 = new Product()
      .setName("p2")
      .setDescription("p2");

    productRepository.save(p1);
    productRepository.save(p2);

    assertThat(p1.getId()).isNotEqualTo(p2.getId());
  }
}
