package com.traulko.project.service.impl;

import com.traulko.project.dao.CustomTransaction;
import com.traulko.project.dao.OrderDao;
import com.traulko.project.dao.impl.OrderDaoImpl;
import com.traulko.project.entity.CustomOrder;
import com.traulko.project.exception.DaoException;
import com.traulko.project.exception.ServiceException;
import com.traulko.project.exception.TransactionException;
import com.traulko.project.service.OrderService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class OrderServiceImplTest {
    private OrderDao orderDao;
    private CustomTransaction transaction;
    private OrderService orderService;

    @BeforeClass
    public void setUp() {
        orderDao = mock(OrderDaoImpl.class);
        transaction = mock(CustomTransaction.class);
        Whitebox.setInternalState(OrderDaoImpl.class, "INSTANCE", orderDao);
        Whitebox.setInternalState(CustomTransaction.class, "INSTANCE", transaction);
        orderService = new OrderServiceImpl();
    }

    @AfterClass
    public void tearDown() {
        orderDao = null;
        transaction = null;
        orderService = null;
    }

    @Test
    public void addPositiveTest() {
        try {
            when(transaction.addOrderAndOrderItems(any(CustomOrder.class), any(List.class))).thenReturn(true);
            boolean actual = orderService.add("1", new ArrayList<>());
            assertTrue(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void addNegativeTest() {
        try {
            when(transaction.addOrderAndOrderItems(any(CustomOrder.class), any(List.class))).thenReturn(true);
            boolean actual = orderService.add(" ", null);
            assertFalse(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removePositiveTest() {
        try {
            when(transaction.removeOrderAndOrderItems(any(Integer.class))).thenReturn(true);
            boolean actual = orderService.remove("1");
            assertTrue(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void removeNegativeTest() {
        try {
            when(transaction.removeOrderAndOrderItems(any(Integer.class))).thenReturn(true);
            boolean actual = orderService.remove(" ");
            assertFalse(actual);
        } catch (TransactionException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByUserIdPositiveTest() {
        try {
            when(orderDao.findOrdersByUserId(any(Integer.class))).thenReturn(new ArrayList<>());
            List<CustomOrder> orderList = orderService.findOrdersByUserId("1");
            assertEquals(orderList, new ArrayList<>());
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findOrdersByUserIdNegativeTest() {
        try {
            when(orderDao.findOrdersByUserId(any(Integer.class))).thenReturn(new ArrayList<>());
            List<CustomOrder> orderList = orderService.findOrdersByUserId(" ");
            assertEquals(orderList, new ArrayList<>());
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryPositiveTest() {
        try {
            List<CustomOrder> expected = new ArrayList<>();
            when(orderDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<CustomOrder> actual = orderService.findBySearchQuery("1");
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findBySearchQueryNegativeTest() {
        try {
            List<CustomOrder> expected = null;
            when(orderDao.findBySearchQuery(any(String.class))).thenReturn(new ArrayList<>());
            List<CustomOrder> actual = orderService.findBySearchQuery("1");
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllPositiveTest() {
        try {
            List<CustomOrder> expected = new ArrayList<>();
            when(orderDao.findAll()).thenReturn(new ArrayList<>());
            List<CustomOrder> actual = orderService.findAll();
            assertEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findAllNegativeTest() {
        try {
            List<CustomOrder> expected = null;
            when(orderDao.findAll()).thenReturn(new ArrayList<>());
            List<CustomOrder> actual = orderService.findAll();
            assertNotEquals(actual, expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void producePositiveTest() {
        try {
            when(orderDao.produce(any(Integer.class), any(LocalDate.class))).thenReturn(true);
            boolean actual = orderService.produce("1");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void produceNegativeTest() {
        try {
            when(orderDao.produce(any(Integer.class), any(LocalDate.class))).thenReturn(true);
            boolean actual = orderService.produce(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void rejectPositiveTest() {
        try {
            when(orderDao.reject(any(Integer.class), any(LocalDate.class))).thenReturn(true);
            boolean actual = orderService.reject("1");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void rejectNegativeTest() {
        try {
            when(orderDao.reject(any(Integer.class), any(LocalDate.class))).thenReturn(true);
            boolean actual = orderService.reject(" ");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdPositiveTest() {
        try {
            when(orderDao.findById(any(Integer.class))).thenReturn(Optional.of(new CustomOrder()));
            CustomOrder expected = new CustomOrder();
            Optional<CustomOrder> actual = orderService.findById("1");
            assertEquals(actual.get(), expected);
        } catch (DaoException | ServiceException e) {
            fail("Incorrect data", e);
        }
    }

    @Test
    public void findByIdNegativeTest() {
        try {
            Optional<CustomOrder> expected = Optional.of(new CustomOrder());
            when(orderDao.findById(any(Integer.class))).thenReturn(Optional.of(new CustomOrder()));
            Optional<CustomOrder> actual = orderService.findById(" ");
            assertNotEquals(actual, expected);
        } catch (ServiceException | DaoException e) {
            fail("Incorrect data", e);
        }
    }
}