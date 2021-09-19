package com.ikes.inventory.model;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "inventory_product")
public class Product {
  @Id
  @Getter
  @Setter
  @SequenceGenerator(name = "inventory_product_id_seq")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private long id;

  @NotNull
  @Getter
  @Setter
  @Column(name="name")
  private String name;

  @NotNull
  @Getter
  @Setter
  @Column(name="description")
  private String description;

  @Column(name="image_url")
  private String imageUrl;

  public Optional<String> getImageUrl() {
    return Optional.ofNullable(imageUrl);
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void removeImageUrl() {
    this.imageUrl = null;
  }

  public Product copyValuesFrom(Product newValues) {
    setName(newValues.getName());
    setDescription(newValues.getDescription()) ;
    if (newValues.getImageUrl().isPresent()) {
      setImageUrl(newValues.getImageUrl().get());
    }
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Product other = (Product)obj;
    return other.getId() == this.getId()
      && Objects.equals(other.getImageUrl(), this.getImageUrl())
      && Objects.equals(other.getName(), this.getName())
      && Objects.equals(other.getDescription(), this.getDescription()) ;
  }
}
