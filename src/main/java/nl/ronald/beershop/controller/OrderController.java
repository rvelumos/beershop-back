package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Order;
import nl.ronald.beershop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static nl.ronald.beershop.model.Order.InvoiceStatus.UNPAID;
import static nl.ronald.beershop.model.Order.OrderStatus.NEW_ADDED;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @CrossOrigin
    @GetMapping(value = "/admin/orders")
    public ResponseEntity<Object> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/order/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        order.setInvoiceStatus(UNPAID);
        order.setOrderStatus(NEW_ADDED);
        orderRepository.save(order);
        //getJavaMailSender().send();
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/order/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(updateOrder -> {
                    updateOrder.setInvoiceStatus(order.getInvoiceStatus());
                    updateOrder.setOrderStatus(order.getOrderStatus());
                    updateOrder.setCustomerId(order.getCustomerId());
                    updateOrder.setOrderDate(order.getOrderDate());
                    updateOrder.setOrderSent(order.getOrderSent());
                    updateOrder.setPriceTotal(order.getPriceTotal());

                    return orderRepository.save(updateOrder);
                })
                .orElseGet(() -> {
                    return orderRepository.save(order);
                });
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") long id) {
        Optional<Order> order = orderRepository.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/customer/{customerId}")
    public ResponseEntity<Object> getCustomerOrders(@PathVariable("customerId") long customerId) {
        List<Order> customerOrders = orderRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("user_id") long id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Verwijderd", HttpStatus.OK);
    }
}