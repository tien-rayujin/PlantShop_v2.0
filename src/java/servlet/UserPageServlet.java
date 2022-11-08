/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import dao.AccountDao;
import dao.OrderDao;
import dto.Account;
import dto.Order;
import dto.OrderDetail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RaeKyo
 */
@WebServlet( name = "userPageServlet", urlPatterns = "/user")
public class UserPageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check if user must be login first
        HttpSession session = req.getSession();
        if(session.getAttribute("username") == null){
            resp.sendRedirect("guest?action=login");
        }else{
//            Account acLogin = (Account)session.getAttribute("user");
//            if(acLogin.getRole() == Account.ADMIN) {
//                resp.sendRedirect("admin");
//                return;
//            }
        }
        
        String action = req.getParameter("action");
        if(action == null)
            action = "list";
        
        switch(action){
            case "logout":
                this.uLogout(req, resp);
                break;
            case "getDetail":
                this.uGetDetail(req, resp);
                break;
            case "viewComplete":
                this.uList_CompleteOrder(req, resp);
                break;
            case "viewCancel":
                this.uList_CancelOrder(req, resp);
                break;
            case "viewProcessing":
                this.uList_ProcessingOrder(req, resp);
                break; 
            case "reOrder":
                req.setAttribute("updateOrder_Successful", false);
                this.uReOrder(req, resp);
                break;
            case "cancelOrder":
                req.setAttribute("updateOrder_Successful", false);
                this.uCancelOrder(req, resp);
                break;     
            case "changeProfile":
                this.uChangeProfile(req, resp);
                break;
            case "orderNow":
                req.setAttribute("order_Successful", false);
                this.uOrderNow(req, resp);
                break;
            case "searchOrder":
                this.uSearchOrder(req, resp);
                break;
            case "list":
            default:
                this.uListOrder(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check if user must be login first
        if(req.getSession().getAttribute("username") == null){
            resp.sendRedirect("guest?action=login");
        }
        String action = req.getParameter("action");
        
        switch(action){
            case "changeProfile":
                this.uChangeProfile_process(req, resp);
                break;
        }
    }
    
    // chua hoan thien vi : ko biet co nen xoa ca cookie luu session token ko ???
    /**
     * when user click "logout" on link on user_index.jsp then current session will invalidate and
     * all cart temporary will be delete (session.invalidate)
     * all attribute will be delete (sessionScope: user(Account), username, plantList, cart
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session != null){ 
            try {
                // if session exist then invalidate and redirect
                // delete token in that account before kill session, that thing delete user cookie :V
                AccountDao.deleteToken((String) session.getAttribute("username"));
                session.invalidate();
                resp.sendRedirect("guest");
                return;
            } catch (Exception ex) {
                Logger.getLogger(UserPageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * when user after login successfully 
     *              in sessionScope: user(Account), username(String), cart(HashMap), plantList(ArrayList)
     * 
     * then load all order of user by username (email) of the account
     * 
     * add to sessionScope: orderList(ArrayList<dto.Order>
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uListOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            String username = (String)session.getAttribute("username");
            ArrayList<Order> orderList;
            orderList = (ArrayList<Order>)OrderDao.listOrders(username); // get list of Order by username ( email as key )
           
            session.setAttribute("orderList", orderList);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_index.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
    
    /**
     * when user click on detail of specific specific order orderId must be sent 
     * the valid order_listDetail on orderId will be add
     * 
     * add to requestScope: order_listDetail(ArrayList<dto.OrderDetail>)
     *                      orderId (Integer)
     *                      total (Integer)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uGetDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{        
        try {
            Integer orderId = Integer.parseInt(req.getParameter("orderId"));
            if(orderId == null) resp.sendRedirect("user");
            
            // get all OrderDetail from OrderId 
            ArrayList<OrderDetail> order_listDetail = (ArrayList<OrderDetail>)OrderDao.getOrderDetail(orderId);
            if(order_listDetail == null) return; // no such an order exist
            
//            int total = order_listDetail.stream().collect(Collectors.summingInt(o -> o.getPrice() * o.getQuantity()));
            req.setAttribute("order_listDetail", order_listDetail);
            req.setAttribute("orderId", orderId);
//            req.setAttribute("total", total);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/order_detail.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }
    
    /**
     * when user click on "viewCompleteOrder" on user page then all complete order will display
     * 
     * add to requestScope: orderList (ArrayList<dto.Order>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uList_CompleteOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            ArrayList<Order> orderList;
            orderList = (ArrayList<Order>) session.getAttribute("orderList"); // get list of Order by username ( email as key )
            orderList = (ArrayList<Order>) orderList.stream().filter(o -> o.getStatus() == Order.SUCCESSFUL).collect(Collectors.toList());
            orderList.stream().forEach(System.out::println);
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_index.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("user");
        }
    }
    
    /**
     * when user click on "viewCancelOrder" on user page then all canceled order will display
     * 
     * add to requestScope: orderList (ArrayList<dto.Order>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uList_CancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            ArrayList<Order> orderList;
            orderList = (ArrayList<Order>)session.getAttribute("orderList"); // get list of Order by username ( email as key )
            orderList = (ArrayList<Order>) orderList.stream().filter(o -> o.getStatus() == Order.CANCEL).collect(Collectors.toList());
            
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_index.jsp").forward(req, resp);
        } catch (Exception e) {e.printStackTrace(); resp.sendRedirect("user");
        }
    }
    
    /**
     * when user click on "viewProcessingOrder" on user page then all processing order will display
     * 
     * add to requestScope: orderList (ArrayList<dto.Order>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uList_ProcessingOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            ArrayList<Order> orderList;
            orderList = (ArrayList<Order>) session.getAttribute("orderList"); // get list of Order by username ( email as key )
            orderList = (ArrayList<Order>) orderList.stream().filter(o -> o.getStatus() == Order.PROCESSING).collect(Collectors.toList());
            
            req.setAttribute("orderList", orderList);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_index.jsp").forward(req, resp);
        } catch (Exception e) {e.printStackTrace(); resp.sendRedirect("user");
        }
    }
    
    /**
     * when the user click on detail on specific Order orderId must be sent
     * if the order is an canceled Order then user can reorder again on that order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uReOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            ArrayList<OrderDetail> order_listDetail = (ArrayList<OrderDetail>)OrderDao.getOrderDetail(orderId);
            if(order_listDetail == null || order_listDetail.isEmpty()) resp.sendRedirect("user");
            
            if(OrderDao.getOrder(orderId).getStatus() == Order.CANCEL){
                OrderDao.updateOrderStatus(orderId, Order.PROCESSING);
                req.setAttribute("updateOrder_Successful", true);
            } 
                
            
            req.getRequestDispatcher("user?action=getDetail&orderId="+orderId).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * when user click on detail on specific order then orderId must be sent
     * if user need to perform cancel order on an order's state = processing
     * check if it is processing and cancel that order
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uCancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            ArrayList<OrderDetail> order_listDetail = (ArrayList<OrderDetail>)OrderDao.getOrderDetail(orderId);
            if(order_listDetail == null || order_listDetail.isEmpty()) resp.sendRedirect("user");
            
            if(OrderDao.getOrder(orderId).getStatus() == Order.PROCESSING){
                OrderDao.updateOrderStatus(orderId, Order.CANCEL);
                req.setAttribute("updateOrder_Successful", true);
            }
                
            
            req.getRequestDispatcher("user?action=getDetail&orderId="+orderId).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * when user click on changeProfile on userPgae then redirect user to input page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uChangeProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            req.setAttribute("update_Successful", false);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_changeProfile.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    /**
     * perform update after user input form user_changeProfile with newName and newPhone
     * click on any button to update
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void uChangeProfile_process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            Account user = (Account)session.getAttribute("user");
            String newName = req.getParameter("newName");
            String newPhone = req.getParameter("newPhone");
            
            if(user == null){
                resp.sendRedirect("user");
                return;
            }
            user = AccountDao.changeAccountProfile(user.getEmail(), newName, newPhone);
            if(user == null){ // fail to change profile
                resp.sendRedirect("user?action=changeProfile");
                return;
            }
            
            req.setAttribute("update_Successful", true);
            session.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_changeProfile.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace();
        }
    }

    protected void uOrderNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
            String username = (String )session.getAttribute("username");
            if(cart != null && !cart.isEmpty() && username != null && !username.isEmpty()){
                if(OrderDao.insertOrder(username, cart) != null){
                    req.setAttribute("order_Successful", true);
                    // reset cart
                    session.setAttribute("cart", new HashMap<Integer, Integer>());
                    req.getRequestDispatcher("/WEB-INF/jsp/view/guest/viewCart.jsp").forward(req, resp);
                }else{
                    resp.sendRedirect("guest");
                }
            }// valid
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("user");
        }
    }
    
    protected void uSearchOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String fromDate = req.getParameter("fromDate");
            String toDate = req.getParameter("toDate");
            String email = (String)req.getSession().getAttribute("username");
            
            if(fromDate != null && !fromDate.isEmpty() 
                    && toDate != null && !toDate.isEmpty()
                    && email != null && !email.isEmpty()){
            ArrayList<Order> res = (ArrayList<Order>)OrderDao.searchOrders(fromDate, toDate, email);
                if(res != null && !res.isEmpty()){
                    req.setAttribute("orderList", res);
                    req.getRequestDispatcher("/WEB-INF/jsp/view/user/user_index.jsp").forward(req, resp);
                }else resp.sendRedirect("user");
            }else resp.sendRedirect("user");
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("user");
        }
    }
}// UserPageServlet
