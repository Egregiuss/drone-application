package com.drone.drone.orders;

import com.drone.drone.product.Product;
import com.drone.drone.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Controller

public class OrdersController {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private ProductService productService;
    @GetMapping("/order")
    public String showOrderForm(Model model) {
        model.addAttribute("orderObject", new OrderObject());
        model.addAttribute("productList", productService.getAllProducts());
        return "orders";
    }

    @PostMapping("/orders")
    public String saveOrder(@ModelAttribute("orderObject") OrderObject orderObject, @RequestParam("selectedProducts") List<Long> selectedProducts) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long productId : selectedProducts) {
            Product product = productService.getProductById(productId);
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(product.getName());
            orderItem.setPrice(BigDecimal.valueOf(product.getPrice()));
            orderItem.setWeight(BigDecimal.valueOf(product.getWeight()));
            orderItem.setOrderObject(orderObject);
            orderItems.add(orderItem);
        }
        orderObject.setOrderItems(orderItems);
        ordersRepo.save(orderObject);
        return "order-list";
    }
}