package com.tms.estore.service.impl;

import com.tms.estore.domain.Order;
import com.tms.estore.domain.Product;
import com.tms.estore.domain.User;
import com.tms.estore.dto.OrderDto;
import com.tms.estore.dto.ProductDto;
import com.tms.estore.mapper.OrderMapper;
import com.tms.estore.mapper.ProductMapper;
import com.tms.estore.repository.OrderRepository;
import com.tms.estore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    @Override
    public Long createOrder(Long id) {
        String orderNumber = generateOrderNumber(id);
        Order order = Order.builder()
                .name(orderNumber)
                .date(LocalDate.now())
                .user(User.builder()
                        .id(id)
                        .build())
                .build();
        Order orderWithId = orderRepository.save(order);
        return orderWithId.getId();
    }

    @Override
    public void saveProductInOrderConfigurations(Long id, List<Product> products) {
        Order order = orderRepository.findOrderById(id);
        order.setProducts(products);
    }

    @Override
    public List<OrderDto> getOrdersById(Long id) {
        return orderRepository.findOrderByUserId(id).stream()
                .map(orderMapper::convertToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkOrderNumber(String number) {
        return orderRepository.existsByName(number);
    }

    @Transactional
    @Override
    public void saveUserOrder(Long userId, List<ProductDto> productsDto) {
        Long order = createOrder(userId);
        List<Product> products = productsDto.stream()
                .map(productMapper::convertToProduct)
                .toList();
        saveProductInOrderConfigurations(order, products);
    }

    private String generateOrderNumber(Long id) {
        String orderNumber = "";
        while (checkOrderNumber(orderNumber) || StringUtils.isEmpty(orderNumber)) {
            orderNumber = "#" + id + "-" + randomUUID();
        }
        return orderNumber;
    }
}
