package com.shopverse.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String title;
    @NotBlank private String category;
    @Column(length = 2000) private String description;
    @NotNull @DecimalMin("0.0") private BigDecimal price;
    private BigDecimal originalPrice;
    private String imageUrl;
    private Double rating = 4.4;
    private Integer reviewCount = 0;
    private Integer stock = 20;

    public Product() {}
    public Product(String title, String category, String description, BigDecimal price, BigDecimal originalPrice, String imageUrl, Double rating, Integer reviewCount, Integer stock) {
        this.title=title; this.category=category; this.description=description; this.price=price; this.originalPrice=originalPrice; this.imageUrl=imageUrl; this.rating=rating; this.reviewCount=reviewCount; this.stock=stock;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getCategory(){return category;} public void setCategory(String v){category=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
    public BigDecimal getOriginalPrice(){return originalPrice;} public void setOriginalPrice(BigDecimal v){originalPrice=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
    public Double getRating(){return rating;} public void setRating(Double v){rating=v;}
    public Integer getReviewCount(){return reviewCount;} public void setReviewCount(Integer v){reviewCount=v;}
    public Integer getStock(){return stock;} public void setStock(Integer v){stock=v;}
}
