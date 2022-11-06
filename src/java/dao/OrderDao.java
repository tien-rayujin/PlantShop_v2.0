/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Account;
import dto.Order;
import dto.OrderDetail;
import dto.Plant;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import util.DB_Utils;

/**
 *
 * @author RaeKyo
 */
public class OrderDao {
    private static Connection con; 
    
    private static final void connect() throws Exception{
        con = DB_Utils.makeConnection();
    }
    
    private static final void disconnect() throws Exception{
        if(con != null) con.close();
    }
    
    public static List<Order> listOrders() throws Exception{
        List<Order> list = new ArrayList<>();
        connect();
        
        String sql = "SELECT * from Orders";
        ResultSet rs = con.createStatement().executeQuery(sql);
        if(rs != null){
            while (rs.next()) {
                int orderId = rs.getInt("OrderID"); // OrderID
                Date orderDate = rs.getDate("OrdDate"); // OrdDate
                Date shipDate = rs.getDate("ShipDate"); // ShipDate
                int status = rs.getInt("status"); // status
                int accId = rs.getInt("accID"); // accID
                
                
                list.add(new Order(orderId, orderDate, shipDate, status, accId));
            }
        }
        if(rs != null) rs.close();
        disconnect();
        return list;
    }
    
    public static List<Order> listOrders(String _email) throws Exception{
        List<Order> list = new ArrayList<>();
        connect();
        
        String sql = "select o.* from Orders o join Accounts a on o.accID = a.accID\n" +
                    "where a.email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _email);
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            while (rs.next()) {
                int orderId = rs.getInt("OrderID"); // OrderID
                Date orderDate = rs.getDate("OrdDate"); // OrdDate
                Date shipDate = rs.getDate("ShipDate"); // ShipDate
                int status = rs.getInt("status"); // status
                int accId = rs.getInt("accID"); // accID
                
                
                list.add(new Order(orderId, orderDate, shipDate, status, accId));
            }
        }
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return list;
    }
    
    public static List<Order> listOrderByName(String _username) throws Exception{
        List<Order> list = new ArrayList<>();
        connect();
        
        String sql = "select o.* from Orders o join Accounts a on o.accID = a.accID\n" +
                    "where a.fullname like ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + _username + "%");
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            while (rs.next()) {
                int orderId = rs.getInt("OrderID"); // OrderID
                Date orderDate = rs.getDate("OrdDate"); // OrdDate
                Date shipDate = rs.getDate("ShipDate"); // ShipDate
                int status = rs.getInt("status"); // status
                int accId = rs.getInt("accID"); // accID
                
                
                list.add(new Order(orderId, orderDate, shipDate, status, accId));
            }
        }
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return list;
    }
    
    public static Order getOrder(int _orderId) throws Exception{
        Order order = null;
        connect();
        
        String sql = "SELECT * FROM Orders where OrderID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, _orderId);
        ResultSet rs = ps.executeQuery();
        
        if(rs != null){
            while(rs.next()){
                int orderId = _orderId; // OrderID
                Date orderDate = rs.getDate("OrdDate"); // OrdDate
                Date shipDate = rs.getDate("ShipDate"); // ShipDate
                int status = rs.getInt("status"); // status
                int accId = rs.getInt("accID"); // accID
                
                order = new Order(orderId, orderDate, shipDate, status, accId);
            }
        }
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return order;
    }// getOrder
    
    public static Order updateOrderStatus(int _orderId, int _status) throws Exception{
        Order orderUpdate = getOrder(_orderId);
        if(orderUpdate != null && orderUpdate.getStatus() != _status){
            orderUpdate.setStatus(_status);
        }else return null;
        return updateOrder(orderUpdate);
    }
    
    public static Order updateOrder(Order _order) throws Exception{
        boolean rowUpdate = false;
        connect();
        
        String sql = "UPDATE Orders SET OrdDate = ?, ShipDate = ?, status = ?, accId = ?";
        sql += " WHERE orderId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, _order.getOrderDate());
        ps.setDate(2, _order.getShipDate());
        ps.setInt(3, _order.getStatus());
        ps.setInt(4, _order.getAccId());
        ps.setInt(5, _order.getOrderId());
        rowUpdate = ps.executeUpdate() > 0;
        
        if(ps != null) ps.close();
        disconnect();
        return rowUpdate ? getOrder(_order.getOrderId()) : null;
    }
    
    public static List<OrderDetail> getOrderDetail(int _orderId) throws Exception{
        List<OrderDetail> list = new ArrayList<>();
        connect();
        
        String sql = "select od.DetailId, od.OrderID, od.PID, od.quantity, o.status, p.PName, p.imgPath, p.price, p.status as plantStatus\n" +
                    "from OrderDetails od, Orders o, Plants p \n" +
                    "where od.OrderID = o.OrderID AND p.PID = od.PID\n" +
                    "    AND od.OrderID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, _orderId);
        ResultSet rs = ps.executeQuery();
        
        if (rs != null) {
            while (rs.next()) {
                int detailId = rs.getInt("DetailId"); // DetailId
                int orderId = rs.getInt("OrderID"); // OrderID
                int plantId = rs.getInt("PID"); // PID
                int quantity = rs.getInt("quantity"); // quantity
                int status = rs.getInt("status");
                int plantStatus = rs.getInt("plantStatus");
                String plantName = rs.getString("PName");
                String imgPath = rs.getString("imgPath");
                int price = rs.getInt("price");
                
                list.add(new OrderDetail(detailId, orderId, plantId, quantity, plantName, imgPath, price, status, plantStatus));
            }
        }
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return list;
    }
    
    public static Order insertOrder(String _email, HashMap<Integer, Integer> cart)throws Exception{
        List<Plant> plantList = PlantDao.listPlants();
        Account orderAccount = AccountDao.getAccount(_email);
        connect();
        if(orderAccount != null && plantList != null && !plantList.isEmpty() && cart != null && !cart.isEmpty()){
            con.setAutoCommit(false);
            
            Date orderDate = new Date(System.currentTimeMillis());
            int status = Order.PROCESSING;
            int newOrderID = -1;
            String sql = "INSERT INTO Orders(OrdDate, status, accID) ";
            sql += "VALUES(?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, orderDate);
            ps.setInt(2, status);
            ps.setInt(3, orderAccount.getAccId());
            
            if(ps.executeUpdate() > 0){
                sql = "SELECT top (1) * FROM Orders where accID = ? order by OrderID desc";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderAccount.getAccId());
                ResultSet rs = ps.executeQuery();
                if(rs != null && rs.next()) newOrderID = rs.getInt("OrderID");
                
                for(Integer plantId : cart.keySet()){
                    sql = "INSERT INTO OrderDetails(OrderID, PID, quantity) ";
                    sql += "VALUES(?, ?, ?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, newOrderID);
                    ps.setInt(2, plantId);
                    ps.setInt(3, cart.get(plantId));
                    if(ps.executeUpdate() > 0){
                        con.commit();
                        con.setAutoCommit(true);
                    }else{ // insert into OrderDetails fail
                        if(con != null) con.rollback();
                    }
                }return getOrder(newOrderID);
                
            }else{// insrt into Order Fail
               if(con != null) con.rollback();
            }            
        }// valid 

        disconnect();
        return null;
    }

    public static List<Order> searchOrders(String _fromDate, String _toDate, String _email) throws Exception{
        if(_fromDate.isEmpty() || _toDate.isEmpty() || _email.isEmpty()) return null;
        connect();
        ArrayList<Order> res = new ArrayList<>();
        ArrayList<Order> orderList = (ArrayList<Order>)listOrders(_email);
        java.util.Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(_fromDate);
        java.util.Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(_toDate);
        
        if(orderList != null && fromDate != null && toDate != null){
            orderList.stream().filter(o -> o.getOrderDate().compareTo(fromDate) >= 0 && 
                    o.getOrderDate().compareTo(toDate) <= 1).forEach(o -> res.add(o));
        }
        disconnect();
        return res;
    }
}// OrderDao
