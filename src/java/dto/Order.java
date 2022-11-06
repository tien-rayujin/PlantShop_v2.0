/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author RaeKyo
 */
public class Order implements Serializable{
    // order status
    public static final int PROCESSING = 1;
    public static final int SUCCESSFUL = 2;
    public static final int CANCEL = 3;
    
    private int orderId; // OrderID
    private Date orderDate; // OrdDate
    private Date shipDate; // ShipDate
    private int status; // status
    private int accId; // accID
    
    public Order(){}

    public Order(int orderId, Date orderDate, Date shipDate, int status, int accId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accId = accId;
    }

    public Order(Date orderDate, Date shipDate, int status, int accId) {
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accId = accId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", status=" + status + ", accId=" + accId + '}';
    }
    
    
}// Order
