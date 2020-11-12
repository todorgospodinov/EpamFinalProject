package com.traulko.project.entity;

public class OrderItem {
    private Integer orderItemId;
    private Product product;
    private CustomOrder order;

    public OrderItem(Integer orderItemId, Product product, CustomOrder order) {
        this.orderItemId = orderItemId;
        this.product = product;
        this.order = order;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomOrder getOrder() {
        return order;
    }

    public void setOrder(CustomOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        if (order == null) {
            if (other.order != null) {
                return false;
            }
        } else if (!order.equals(other.order)) {
            return false;
        }
        if (orderItemId == null) {
            if (other.orderItemId != null) {
                return false;
            }
        } else if (!orderItemId.equals(other.orderItemId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((orderItemId == null) ? 0 : orderItemId.hashCode());
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderItem{");
        sb.append("orderItemId=").append(orderItemId);
        sb.append(", product=").append(product);
        sb.append(", order=").append(order);
        sb.append('}');
        return sb.toString();
    }
}
