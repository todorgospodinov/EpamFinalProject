package com.traulko.project.service;

import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.ServiceException;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findOrderItemsByOrderId(String id) throws ServiceException;
}
