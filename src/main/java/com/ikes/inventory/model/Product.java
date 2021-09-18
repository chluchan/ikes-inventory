package com.ikes.inventory.model;

import com.sun.istack.NotNull;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  @Getter
  @Setter
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
}
