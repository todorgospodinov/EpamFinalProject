package com.traulko.project.service.impl;

import com.traulko.project.dao.OrderItemDao;
import com.traulko.project.dao.impl.OrderItemDaoImpl;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.OrderItem;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.service.OrderItemService;
import com.traulko.project.validator.ProjectValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public List<OrderItem> findOrderItemsByOrderId(String id) throws ServiceException {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            if (ProjectValidator.isCorrectIntValue(id)) {
                int orderId = Integer.parseInt(id);
                orderItemList = orderItemDao.findOrderItemsByOrderId(orderId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Error while finding order items by order id", e);
        }
        return orderItemList;
    }
}
