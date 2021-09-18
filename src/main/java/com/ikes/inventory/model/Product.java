package com.ikes.inventory.model;

import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  @GeneratedValue(strategy = IDENTITY)
  @Getter
  @Setter
  @Column(name="id", nullable=false)
  private long id;

  @NotNull
  @Getter
  @Setter
  @Column(name="name", nullable=false)
  private String name;

  @NotNull
  @Getter
  @Setter
  @Column(name="description", nullable=false)
  private String description;

  @Column(name="image_url", nullable=true)
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
}
