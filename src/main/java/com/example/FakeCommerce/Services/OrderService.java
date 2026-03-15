package com.example.FakeCommerce.Services;


import com.example.FakeCommerce.Adapters.OrderAdapters;
import com.example.FakeCommerce.Exeptions.ResourceNotFoundExeption;
import com.example.FakeCommerce.Mapper.OrderItemMapper;
import com.example.FakeCommerce.Mapper.OrderMapper;
import com.example.FakeCommerce.Repository.OrderRepository;
import com.example.FakeCommerce.Repository.ProductOrderRepository;
import com.example.FakeCommerce.Repository.ProductRepositry;
import com.example.FakeCommerce.Schema.Order;
import com.example.FakeCommerce.Schema.OrderStatus;
import com.example.FakeCommerce.Schema.Product;
import com.example.FakeCommerce.Schema.ProductOrder;
import com.example.FakeCommerce.dtos.CreateOrderRequestDto;
import com.example.FakeCommerce.dtos.CreateOrderRequestDtoByDiff;
import com.example.FakeCommerce.dtos.GetOrderResponseDto;
import com.example.FakeCommerce.dtos.OrderItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductOrderRepository productOrderRepository;

    private final OrderRepository orderRepository;

    private final ProductRepositry productRepositry;

//    private final OrderAdapters orderAdapters;
    private final OrderMapper orderMapper;


    public boolean createOrder(List<CreateOrderRequestDto> createOrderRequestDtos){
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        for(CreateOrderRequestDto createOrderRequestDto:createOrderRequestDtos){
            Product product = productRepositry.findById(createOrderRequestDto.getProductId()).orElse(null);

            ProductOrder productOrder = new ProductOrder();

            productOrder.setQuantity(createOrderRequestDto.getQuantity());
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrderRepository.save(productOrder);
        }
        return true;
    }

    public List<GetOrderResponseDto> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            List<ProductOrder> productOrders = productOrderRepository.findByOrderId(order.getId());
            return orderMapper.toGetOrderResponseDto(order,productOrders);
        }).toList();

    }

//    public boolean deleteOrder(Long id) {
//        return productOrderRepository.d
//    }

    public GetOrderResponseDto getOrderById(Long id){

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Order Not found"));
        List<ProductOrder> items = productOrderRepository.findByOrderId(order.getId());
         return orderMapper.toGetOrderResponseDto(order,items);
    }

    public void createOrderApi(CreateOrderRequestDtoByDiff createOrderRequestDto){
        // 1. we need to create a new order instance

        // 2.If the payload dto has some order products,add those in the order as well
        // other wise skip it.

        // return order

        Order order = Order.builder().status(OrderStatus.PENDING).build();

        orderRepository.save(order);

        if(createOrderRequestDto.getOrderItems() != null){
            for(var itemDto : createOrderRequestDto.getOrderItems()){
                Product product = productRepositry.findById(itemDto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundExeption("Product Not found"));


                ProductOrder productOrder = ProductOrder.builder()
                        .order(order)
                        .product(product)
                        .quantity(itemDto.getQuantity() != null ? itemDto.getQuantity() : 1)
                        .build();

                productOrderRepository.save(productOrder);
            }
        }
    }

}

//User --> cart --> Add an item --> New Order(Pending)

//User --> add more items in the cart -> same order will be updated

// During checkout --> Order pending --> success/failure
