package com.shopverse.order;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="customer_orders")
public class OrderEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String publicId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    private String customerName;
    private String email;
    private String address;
    private String itemsJson;
    private Double total;
    private String status = "PLACED";
    private Instant createdAt = Instant.now();

    public OrderEntity() {}
    public Long getId(){return id;} public String getPublicId(){return publicId;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String v){customerName=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getItemsJson(){return itemsJson;} public void setItemsJson(String v){itemsJson=v;}
    public Double getTotal(){return total;} public void setTotal(Double v){total=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public Instant getCreatedAt(){return createdAt;}
}
