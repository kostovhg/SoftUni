package jsfapp.repository;

import jsfapp.domain.entities.Order;

import java.util.List;

public interface OrderRepository extends GenericRepository<Order, String> {

    Order save(Order entity);

    List<Order> findAll();

    Order findById(String id);

    Long size();
}