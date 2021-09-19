package com.ikes.inventory.model;

import static javax.persistence.GenerationType.AUTO;

import com.sun.istack.NotNull;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory_stock")
public class Stock {
  public enum SalesStatus {
    AVAILABLE_FOR_SALE, NOT_FOR_SALE, SOLD,
  }

  public enum RentalStatus {
    AVAILABLE_FOR_RENT, NOT_FOR_RENT,
  }

  @Id
  @Getter
  @Setter
  @SequenceGenerator(name = "inventory_stock_id_seq")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Getter
  @Setter
  @ManyToOne
  private Product product;

  @NotNull
  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(name="sales_status")
  private SalesStatus salesStatus;

  @NotNull
  @Getter
  @Setter
  @Enumerated(EnumType.STRING)
  @Column(name="rental_status")
  private RentalStatus rentalStatus;

  public Stock copyValuesFrom(Stock newStock, Optional<Product> newProduct) {
    newProduct.ifPresent(this::setProduct);
    this.salesStatus = newStock.getSalesStatus();
    this.rentalStatus = newStock.getRentalStatus();
    return this;
  }

  public Stock copyValuesFrom(Stock newStock) {
    return copyValuesFrom(newStock, Optional.empty());
  }

  public Stock copyValuesFrom(Stock newStock, Product newProduct) {
    return copyValuesFrom(newStock, Optional.of(newProduct));
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Stock other = (Stock)obj;
    return other.getId() == this.getId()
      && other.getRentalStatus() == this.getRentalStatus()
      && other.getSalesStatus() == this.getSalesStatus()
      && Objects.equals(other.getProduct(), this.getProduct());
  }
}
