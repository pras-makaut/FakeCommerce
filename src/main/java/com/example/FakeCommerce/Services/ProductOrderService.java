package com.example.FakeCommerce.Services;


import com.example.FakeCommerce.Repository.OrderRepository;
import com.example.FakeCommerce.Repository.ProductOrderRepository;
import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.OrderStatus;
import com.example.FakeCommerce.Schema.Product;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;

    private final OrderRepository orderRepository;

    private final ProductRepositry productRepositry;


    public boolean createOrder(List<CreateOrderRequestDto> createOrderRequestDtos){

        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        for(CreateOrderRequestDto createOrderRequestDto:createOrderRequestDtos){
            Product product = productRepositry.findById(createOrderRequestDto.getProductId()).orElse(null);

            ProductOrder productOrder = new ProductOrder();

            productOrder.setCount(createOrderRequestDto.getQuantity());
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrderRepository.save(productOrder);


        }
        return true;
    }

    public List<ProductOrder> getAllProductOrder(){
        return productOrderRepository.findAll();
    }

}
