package com.ikes.inventory.model;

import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
  enum SalesStatus {
    AVAILABLE_FOR_SALE, NOT_FOR_SALE, SOLD,
  }

  enum RentalStatus {
    AVAILABLE_FOR_RENT, NOT_FOR_RENT,
  }

  @Id
  @Getter
  @Setter
  @Column(name="id")
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
}
