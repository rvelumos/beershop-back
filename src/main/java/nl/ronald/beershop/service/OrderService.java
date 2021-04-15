package nl.ronald.beershop.service;

import nl.ronald.beershop.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();
    public Order getOrder(long id);
    public void save(Order Order);
    public void deleteById(long id);

}