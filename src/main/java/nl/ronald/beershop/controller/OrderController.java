package nl.ronald.beershop.controller;

import nl.ronald.beershop.model.Order;
import nl.ronald.beershop.payload.ConfirmationRequest;
import nl.ronald.beershop.repository.OrderRepository;
import nl.ronald.beershop.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static nl.ronald.beershop.model.Order.InvoiceStatus.UNPAID;
import static nl.ronald.beershop.model.Order.OrderStatus.NEW_ADDED;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    EmailService emailService;

    @PostMapping(value = "/customer/confirmation")
    public ResponseEntity<Object> sendConfirmation(@RequestBody ConfirmationRequest request)
    {
        emailService.sendConfirmation(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/admin/orders")
    public ResponseEntity<Object> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        order.setInvoiceStatus(UNPAID);
        order.setOrderStatus(NEW_ADDED);
        orderRepository.save(order);
        //getJavaMailSender().send();
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
                .orElseGet(() -> orderRepository.save(order));
    }

    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") long id) {
        Optional<Order> order = orderRepository.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/orders/customer/{username}")
    public ResponseEntity<Object> getCustomerOrders(@PathVariable("username") String username) {
        List<Order> customerOrders = orderRepository.findByUsername(username);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/order/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("id") long id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Verwijderd", HttpStatus.OK);
    }
}