/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Order;
import dto.OrderDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 *
 * @author RaeKyo
 */
public class TestOrderDao {
    public static void main(String[] args) {
        System.out.println("Before Task");
        printAll();
        
        System.out.println("");
        System.out.println("After Task");
        
        // update orderStatus
//        int orderId = 16;
//        int status = Order.CANCEL;
//        updateOrderStatus(orderId, status);
        

//        HashMap<Integer, Integer> cart = new HashMap<>();
//        cart.put(1, 2);
//        cart.put(2, 4);
//        String email = "test@gmail.com";
//        
//        try {
//            Order order = OrderDao.insertOrder(email, cart);
//            if(order != null){
//                System.out.println("Successful");
//                System.out.println(order);
//            }
//            else System.out.println("Fail");
//        } catch (Exception e) { e.printStackTrace();
//        }



//        String fromDate = "2022-10-23";
//        String toDate = "2022-10-29";
//        String email = "test@gmail.com";
//        try {
//            ArrayList<Order> res = (ArrayList<Order>)OrderDao.searchOrders(fromDate, toDate, email);
//            if(res != null && !res.isEmpty()){
//                System.out.println("Successful");
//                res.forEach(System.out::println);
//            }else System.out.println("FAIL");
//        } catch (Exception e) { e.printStackTrace();
//        }

        listOrderByName("Test");
    }
    
    public void test(){
        Integer orderId = 7;
        System.out.println("getOrderDetail of id #" +orderId);
        getDetail(orderId);
        
        // get total money here
        int total = 0;
        try {
            ArrayList<OrderDetail> list = (ArrayList<OrderDetail>)OrderDao.getOrderDetail(orderId);
            if(list != null && list.size() > 0){
                total = list.stream().collect(Collectors.summingInt(o -> o.getPrice() * o.getQuantity()));
                System.out.println("total = " + total);
            }else System.out.println("Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void printAll(){
        try {
            ArrayList<Order> list = (ArrayList<Order>)OrderDao.listOrders();
            if(list != null && list.size() > 0){
                list.stream().forEach(System.out::println);
            }else System.out.println("Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void printAll(String email){
        try {
            ArrayList<Order> list = (ArrayList<Order>)OrderDao.listOrders(email);
            if(list != null && list.size() > 0){
                list.stream().forEach(System.out::println);
            }else System.out.println("Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void listOrderByName(String fullname){
        try {
            ArrayList<Order> list = (ArrayList<Order>)OrderDao.listOrderByName(fullname);
            if(list != null && list.size() > 0)
                list.forEach(System.out::println);
            else System.out.println("Fail");
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    public static final void getDetail(int orderId){
        try {
            ArrayList<OrderDetail> list = (ArrayList<OrderDetail>)OrderDao.getOrderDetail(orderId);
            if(list != null && list.size() > 0){
                list.stream().forEach(System.out::println);
            }else System.out.println("Fail");
        } catch (Exception e) {
        }
    }
    
    public static final void updateOrderStatus(int orderId, int status){
        try {
            Order orderUpdate = OrderDao.updateOrderStatus(orderId, status);
            if(orderUpdate != null){
                System.out.println("Successful");
                System.out.println("OrderUpdate = " + orderUpdate);
                printAll();
            }else System.out.println("Fail");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
