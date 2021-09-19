package com.ikes.inventory.api;

import com.google.common.collect.ImmutableList;
import com.ikes.inventory.TestContainerApplicationContextInitializer;
import com.ikes.inventory.model.Product;
import com.ikes.inventory.model.ProductRepository;
import java.util.List;
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
class ProductControllerTest {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductController productController;

  @Test
  @Transactional
  void shouldGetAllProducts() {
    List<Product> twoBikes = ImmutableList.of(createATypeOfMountainBike(), createATypeOfRoadBike());

    List<Product> products = productController.getProducts();

    assertThat(products).containsExactlyInAnyOrderElementsOf(twoBikes);
  }

  private Product createATypeOfMountainBike() {
    Product yetiSb5 = new Product()
      .setName("Yeti SB5")
      .setDescription("A high end mountain bike");
    productRepository.save(yetiSb5);
    return yetiSb5;
  }

  private Product createATypeOfRoadBike() {
    Product aliance = new Product()
      .setName("Giant TCR Aliance")
      .setDescription("Another road bike");
    productRepository.save(aliance);
    return aliance;
  }
}
