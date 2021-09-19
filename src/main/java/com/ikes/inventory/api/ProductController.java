package com.ikes.inventory.api;

import com.ikes.inventory.model.Product;
import com.ikes.inventory.model.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.common.collect.ImmutableList;

@RestController
public class ProductController {
  private ProductRepository productRepository;

  @Autowired
  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/products")
  public List<Product> getProducts() {
    return ImmutableList.copyOf(productRepository.findAll());
  }

  @PostMapping("/products")
  Product newEmployee(@RequestBody Product newProduct) {
    return productRepository.save(newProduct);
  }

  @PutMapping("/products/{id}")
  Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    return productRepository.findById(id)
      .map(product -> productRepository.save(product.copyValuesFrom(newProduct)))
      .orElseThrow(() -> new IllegalArgumentException("No product by id: " + id));
  }

  @DeleteMapping("/products/{id}")
  void deleteProduct(@PathVariable Long id) {
    productRepository.deleteById(id);
  }
}
