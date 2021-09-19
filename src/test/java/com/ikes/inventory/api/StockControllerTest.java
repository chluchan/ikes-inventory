package com.ikes.inventory.api;

import com.ikes.inventory.TestContainerApplicationContextInitializer;
import com.ikes.inventory.model.Product;
import com.ikes.inventory.model.ProductRepository;
import com.ikes.inventory.model.Stock;
import com.ikes.inventory.model.StockRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ikes.inventory.model.Stock.RentalStatus.AVAILABLE_FOR_RENT;
import static com.ikes.inventory.model.Stock.SalesStatus.NOT_FOR_SALE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {TestContainerApplicationContextInitializer.class})
public class StockControllerTest {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private StockController stockController;

  @Test
  @Transactional
  void shouldGetAllProducts() {
    Product mountainBike = createATypeOfMountainBike();
    Product roadBike = createATypeOfRoadBike();
    Stock specificBike1 = createASpecificBike(mountainBike);
    Stock specificBike2 = createASpecificBike(roadBike);

    List<Stock> stocks = stockController.getStocks();

    assertThat(stocks).containsExactly(specificBike1, specificBike2);
  }

  private Stock createASpecificBike(Product product) {
    Stock bike = new Stock()
      .setProduct(product)
      .setSalesStatus(NOT_FOR_SALE)
      .setRentalStatus(AVAILABLE_FOR_RENT);
    return stockRepository.save(bike);
  }

  private Product createATypeOfMountainBike() {
    Product yetiSb5 = new Product()
      .setName("Yeti SB5")
      .setDescription("A high end mountain bike");
    return productRepository.save(yetiSb5);
  }

  private Product createATypeOfRoadBike() {
    Product aliance = new Product()
      .setName("Giant TCR Aliance")
      .setDescription("Another road bike");
    return productRepository.save(aliance);
  }
}
