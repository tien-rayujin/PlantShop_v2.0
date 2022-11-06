/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author RaeKyo
 */
public class OrderDetail {
    private int detailId; // DetailId
    private int orderId; // OrderID
    private int plantId; // PID
    private int quantity; // quantity
    
    
    // extends field
    private String plantName;
    private String imgPath;
    private int price;
    private int status;
    private int plantStatus;
    
    public OrderDetail(){}

    public OrderDetail(int detailId, int orderId, int plantId, int quantity, String plantName, String imgPath, int price, int status, int plantStatus) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.plantId = plantId;
        this.quantity = quantity;
        this.plantName = plantName;
        this.imgPath = imgPath;
        this.price = price;
        this.status = status;
        this.plantStatus = plantStatus;
    }

    public OrderDetail(int orderId, int plantId, int quantity, String plantName, String imgPath, int price, int status, int plantStatus) {
        this.orderId = orderId;
        this.plantId = plantId;
        this.quantity = quantity;
        this.plantName = plantName;
        this.imgPath = imgPath;
        this.price = price;
        this.status = status;
        this.plantStatus = plantStatus;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(int plantStatus) {
        this.plantStatus = plantStatus;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "detailId=" + detailId + ", orderId=" + orderId + ", plantId=" + plantId + ", quantity=" + quantity + ", plantName=" + plantName + ", imgPath=" + imgPath + ", price=" + price + ", status=" + status + ", plantStatus=" + plantStatus + '}';
    }

    
    
}