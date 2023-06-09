package com.drone.drone.orders;

import com.drone.drone.product.Product;
import com.drone.drone.product.ProductRepo;
import com.drone.drone.product.ProductService;
import com.drone.drone.purchaseOrder.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class OrdersService {

    private final OrdersRepo ordersRepo;
    @Autowired
    private ProductRepo productRepo;
    private PurchaseOrderService purchaseOrderService;
    private ProductService productService;


    public OrdersService( OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;

    }

    public void saveOrder(OrderObject orderObject, List<Long> selectedProducts) {
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
    }



}

