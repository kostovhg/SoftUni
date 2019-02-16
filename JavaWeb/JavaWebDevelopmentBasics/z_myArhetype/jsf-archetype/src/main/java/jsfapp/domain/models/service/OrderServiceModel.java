package jsfapp.domain.models.service;

import java.time.LocalDateTime;

public class OrderServiceModel {

    private String id;
    private String orderName;
    private LocalDateTime orderDate;
    private UserServiceModel orderBy;

    public OrderServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public UserServiceModel getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(UserServiceModel orderBy) {
        this.orderBy = orderBy;
    }
}