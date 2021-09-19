package com.ikes.inventory.api;

import com.google.common.collect.ImmutableList;
import com.ikes.inventory.model.Product;
import com.ikes.inventory.model.ProductRepository;
import com.ikes.inventory.model.Stock;
import com.ikes.inventory.model.StockRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
  private StockRepository stockRepository;
  private ProductRepository productRepository;

  @Autowired
  public StockController(
    StockRepository stockRepository,
    ProductRepository productRepository) {
    this.stockRepository = stockRepository;
    this.productRepository = productRepository;
  }

  @GetMapping("/stocks")
  public List<Stock> getStocks() {
    return ImmutableList.copyOf(stockRepository.findAll());
  }

  @PostMapping("/stocks")
  Stock newStock(@RequestBody Stock newStock) {
    return stockRepository.save(newStock);
  }

  @PutMapping("/stocks/{id}")
  Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {
    return stockRepository.findById(id)
      .map(Stock -> {
        Optional<Product> productChange = Optional.ofNullable(newStock.getProduct())
          .flatMap(product -> productRepository.findById(id));
        return stockRepository.save(Stock.copyValuesFrom(newStock, productChange));
      })
      .orElseThrow(() -> new IllegalArgumentException("No Stock by id: " + id));
  }

  @DeleteMapping("/stocks/{id}")
  void deleteStock(@PathVariable Long id) {
    stockRepository.deleteById(id);
  }
}
