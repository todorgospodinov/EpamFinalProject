package com.traulko.project.service.impl;

import com.traulko.project.dao.CustomTransaction;
import com.traulko.project.dao.OrderDao;
import com.traulko.project.dao.impl.OrderDaoImpl;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.entity.User;
import com.traulko.project.entity.UserBasketProduct;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.exception.TransactionException;
import com.traulko.project.service.OrderService;
import com.traulko.project.validator.ProjectValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private CustomTransaction customTransaction = new CustomTransaction();

    @Override
    public boolean add(Integer userId, List<UserBasketProduct> userBasketProductList) throws ServiceException {
        try {
            CustomOrder order = new CustomOrder();
            LocalDate date = LocalDate.now();
            order.setCreationDate(date);
            order.setClosingDate(date);
            order.setStatus(CustomOrder.Status.UNDER_CONSIDERATION);
            User user = new User();
            user.setUserId(userId);
            order.setUser(user);
            return customTransaction.addOrderAndOrderItem(order, userBasketProductList);
        } catch (TransactionException e) {
            throw new ServiceException("Error while adding order", e);
        }
    }

    @Override
    public List<CustomOrder> findOrdersByUserId(Integer id) throws ServiceException {
        try {
            return orderDao.getOrdersByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException("Error while finding orders by user id", e);
        }
    }

    @Override
    public Optional<CustomOrder> findById(String id) throws ServiceException {
        Optional<CustomOrder> orderOptional = Optional.empty();
        try {
            if (ProjectValidator.isCorrectIntValue(id)) {
                int orderId = Integer.parseInt(id);
                orderOptional = orderDao.findById(orderId);
            }
            return orderOptional;
        } catch (DaoException e) {
            throw new ServiceException("Error while finding order by id", e);
        }
    }
}
