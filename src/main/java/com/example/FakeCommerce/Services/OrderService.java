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
import com.example.FakeCommerce.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Transactional
    public GetOrderResponseDto createOrderApi(CreateOrderRequestDtoByDiff createOrderRequestDto){
        // 1. we need to create a new order instance

        // 2.If the payload dto has some order products,add those in the order as well
        // other wise skip it.

        // return order

        Order order = Order.builder().status(OrderStatus.PENDING).build();

        orderRepository.save(order);


        if(createOrderRequestDto.getOrderItems() != null){
            List<Long> productIds = createOrderRequestDto.getOrderItems().stream().
                    map(OrderItemRequestDto::getProductId)
                    .toList();
            List<Product> products = productRepositry.findAllById(productIds);

            Map<Long,Product> productMap = products.stream().
                    collect(Collectors.toMap(Product::getId, Function.identity()));

            for(Long id:productIds){
                if(!productMap.containsKey(id)){
                    throw new ResourceNotFoundExeption("Product id not found with id : "+id);
                }
            }

            List<ProductOrder> productOrders = new ArrayList<>();


            for(var itemDto : createOrderRequestDto.getOrderItems()){
                Product product = productMap.get(itemDto.getProductId());

                productOrders.add(ProductOrder.builder().
                        product(product).
                        order(order)
                        .quantity(itemDto.getQuantity() != null ? itemDto.getQuantity() : 1)
                        .build());
            }
            productOrderRepository.saveAll(productOrders);
        }
        return orderMapper.toGetOrderResponseDto(order,productOrderRepository.findByOrderId(order.getId()));

    }

    public GetOrderResponseDto updateOrderApi(Long orderId, UpdateOrderRequestDto updateOrderRequestDto){
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new ResourceNotFoundExeption("Order not found id : "+ orderId));

        if(updateOrderRequestDto.getStatus() != null){
            order.setStatus(updateOrderRequestDto.getStatus());
            orderRepository.save(order);
        }

        if(updateOrderRequestDto.getOrderItems() != null){
            List<Long> pIds = updateOrderRequestDto.getOrderItems().stream()
                    .map(OrderItemActionDto::getProductId).toList();

            List<Product> products = productRepositry.findAllById(pIds);

            Map<Long,Product> productMap = products.stream().
                    collect(Collectors.toMap(Product::getId, Function.identity()));

            for(Long id:pIds){
                if(!productMap.containsKey(id)){
                    throw new ResourceNotFoundExeption("Product id not found with id : "+id);
                }
            }

            List<ProductOrder> toSave = new ArrayList<>();
            List<ProductOrder> toDelete = new ArrayList<>();
            Map<Long ,ProductOrder> existing = productOrderRepository.findByOrdertWithProduct(order).stream()
                    .collect(Collectors.toMap(op -> op.getProduct().getId(),Function.identity()));

            for(OrderItemActionDto orderItem : updateOrderRequestDto.getOrderItems()){
                Product product = productMap.get(orderItem.getProductId());

                ProductOrder existingProductOrder = existing.get(product.getId());

                switch (orderItem.getAction()) {
                    case OrderItemActions.ADD -> {
                        if (existingProductOrder != null) {
                            existingProductOrder.setQuantity(existingProductOrder.getQuantity() + (orderItem.getQuantity() != null ? orderItem.getQuantity() : 1));
                            toSave.add(existingProductOrder);
                        } else {
                            ProductOrder newProductOrder = ProductOrder.builder()
                                    .product(product)
                                    .order(order)
                                    .quantity(orderItem.getQuantity() != null ? orderItem.getQuantity() : 1)
                                    .build();
                            existing.put(product.getId(), newProductOrder);
                            toSave.add(newProductOrder);
                        }
                    }
                    case OrderItemActions.REMOVE -> {
                        if (existingProductOrder == null) {
                            throw new ResourceNotFoundExeption("Product not found with id:" + product.getId());
                        }

                        toDelete.add(existingProductOrder);
                        existing.remove(product.getId());
                    }
                    case OrderItemActions.INCREMENT -> {
                        if (existingProductOrder == null) {
                            throw new ResourceNotFoundExeption("Product not found with id:" + product.getId());
                        }
                        existingProductOrder.setQuantity(existingProductOrder.getQuantity() + 1);
                        toSave.add(existingProductOrder);
                    }
                    case OrderItemActions.DECREMENT -> {
                        if (existingProductOrder == null) {
                            throw new ResourceNotFoundExeption("Product not found with id:" + product.getId());
                        }
                        if (existingProductOrder.getQuantity() <= 1) {
                            toDelete.add(existingProductOrder);
                            existing.remove(product.getId());
                        } else {
                            existingProductOrder.setQuantity(existingProductOrder.getQuantity() - 1);
                            toSave.add(existingProductOrder);
                        }
                    }
                }
            }

            productOrderRepository.saveAll(toSave);
            productOrderRepository.deleteAll(toDelete);
        }
        return orderMapper.toGetOrderResponseDto(order,productOrderRepository.findByOrderId(orderId));
    }



}

//User --> cart --> Add an item --> New Order(Pending)

//User --> add more items in the cart -> same order will be updated

// During checkout --> Order pending --> success/failure
