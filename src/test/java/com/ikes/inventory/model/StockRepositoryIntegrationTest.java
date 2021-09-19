package com.ikes.inventory.model;

import static com.ikes.inventory.model.Stock.RentalStatus.AVAILABLE_FOR_RENT;
import static com.ikes.inventory.model.Stock.RentalStatus.NOT_FOR_RENT;
import static com.ikes.inventory.model.Stock.SalesStatus.AVAILABLE_FOR_SALE;
import static com.ikes.inventory.model.Stock.SalesStatus.NOT_FOR_SALE;

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
public class StockRepositoryIntegrationTest {
  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private ProductRepository productRepository;

  @Test
  @Transactional
  void shouldCreateAndRetrieveAStock() {
    Product mountainBike = createATypeOfMountainBike();
    Stock specificBike = new Stock()
      .setProduct(mountainBike)
      .setSalesStatus(NOT_FOR_SALE)
      .setRentalStatus(AVAILABLE_FOR_RENT);

    stockRepository.save(specificBike);
    long stockId = specificBike.getId();
    Optional<Stock> retrievedStock = stockRepository.findById(stockId);

    assertThat(retrievedStock).isPresent();
    assertThat(retrievedStock.get().getProduct().getName()).isEqualTo("Yeti SB5");
  }

  @Test
  @Transactional
  void shouldCreateUniqueIds() {
    Product mountainBike = createATypeOfMountainBike();
    Stock specificBike1 = new Stock()
      .setProduct(mountainBike)
      .setSalesStatus(NOT_FOR_SALE)
      .setRentalStatus(AVAILABLE_FOR_RENT);
    Stock specificBike2 = new Stock()
      .setProduct(mountainBike)
      .setSalesStatus(AVAILABLE_FOR_SALE)
      .setRentalStatus(NOT_FOR_RENT);

    stockRepository.save(specificBike1);
    stockRepository.save(specificBike2);

    assertThat(specificBike1.getId()).isNotEqualTo(specificBike2.getId());
  }

  private Product createATypeOfMountainBike() {
    Product yetiSb5 = new Product()
      .setName("Yeti SB5")
      .setDescription("A high end mountain bike");
    productRepository.save(yetiSb5);
    return yetiSb5;
  }
}
