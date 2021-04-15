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

import static nl.ronald.beershop.model.Order.Invoice_status.UNPAID;
import static nl.ronald.beershop.model.Order.Order_status.NEW_ADDED;

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

    //user auth fix & email verification
    @PostMapping(value = "/order")
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        order.setInvoice_status(UNPAID);
        order.setOrder_status(NEW_ADDED);
        orderRepository.save(order);
        //getJavaMailSender().send();
        URI location;
        return new ResponseEntity<>("Toegevoegd", HttpStatus.CREATED);
    }

    @PutMapping(value = "/order/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(updateOrder -> {
                    updateOrder.setInvoice_status(order.getInvoice_status());
                    updateOrder.setOrder_status(order.getOrder_status());
                    updateOrder.setCustomerId(order.getCustomerId());
                    updateOrder.setOrder_date(order.getOrder_date());
                    updateOrder.setOrder_sent(order.getOrder_sent());
                    updateOrder.setPrice_total(order.getPrice_total());
                    updateOrder.setShipping_id(order.getShipping_id());

                    return orderRepository.save(updateOrder);
                })
                .orElseGet(() -> {
                    return orderRepository.save(order);
                });
    }

    //check email
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("ronald.eijsden@gmail.com");
        mailSender.setPassword("ss");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    //auth fix!
    @GetMapping(value = "/order/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable("id") long id) {
        Optional<Order> order = orderRepository.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //auth fix!
    @GetMapping(value = "/orders/customer/{customer_id}")
    public ResponseEntity<Object> getCustomerOrders(@PathVariable("customer_id") long customer_id) {
        List<Order> customerOrders = orderRepository.findByCustomerId(customer_id);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    //auth fix & cascade Customer order_ID
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("user_id") long id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Verwijderd", HttpStatus.OK);
    }
}