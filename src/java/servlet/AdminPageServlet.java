/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import dao.AccountDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.PlantDao;
import dto.Account;
import dto.Categories;
import dto.Order;
import dto.Plant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.SessionRegistry;

/**
 *
 * @author RaeKyo
 */
@WebServlet(name = "adminPageServlet", urlPatterns = "/admin")
public class AdminPageServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null
                || ((Account)session.getAttribute("user")).getRole() != Account.ADMIN){
            resp.sendRedirect("guest");
            return;
        }
        
        try {
            if(session.getAttribute("listAccount") == null) 
                session.setAttribute("listAccount", AccountDao.listAccounts());
            else if(session.getAttribute("plantList") == null)
                session.setAttribute("plantList", PlantDao.listPlants());
            else if(session.getAttribute("cateList") == null)
                session.setAttribute("cateList", CategoryDao.listCate());
        } catch (Exception e) {
        }
        
        String action = req.getParameter("action");
        if(action == null)
            action = "manageSession";
        
        switch(action){
            case "manageAccount":
                this.aManageAccount(req, resp);
                break;
            case "searchAccount":
                this.aSearchAccount(req, resp);
                break;  
            case "updateAccountStatus":
                req.setAttribute("updateAccount_Successful", false);
                this.aUpdateAccountStatus(req, resp);
                break;
            case "managePlant":
                this.aManagePlant(req, resp);
                break;
            case "updatePlantInfo":
                req.setAttribute("updatePlant_Successful", false);
                this.aUpdatePlant(req, resp);
                break;
            case "searchPlant":
                this.aSearchPlant(req, resp);
                break;
            case "addNewPlant":
                req.setAttribute("addPlant_Successful", false);
                this.aInsertPlant(req, resp);
                break;
            case "deletePlant":
                this.aDeletePlant(req, resp);
                break;
            case "manageCate":
                this.aManageCate(req, resp);
                break;
            case "updateCateInfo":
                req.setAttribute("updateCate_Successful", true);
                this.aUpdateCate(req, resp);
                break;
            case "addNewCate":
                req.setAttribute("addCate_Successful", false); 
                this.aInsertCate(req, resp);
                break;
            case "manageOrder":
                this.aManageOrder(req, resp);
                break;
            case "reOrder":
                req.setAttribute("updateOrder_Successful", false);
                this.aManageOrder_reOrder(req, resp);
                break;
            case "cancelOrder":
                req.setAttribute("updateOrder_Successful", false);
                this.aManageOrder_cancelOrder(req, resp);
                break;
            case "searchOrderByDate":
                this.aSearchOrderByDate(req, resp);
                break;
            case "searchOrderByName":
                this.aSearchOrderByName(req, resp);
                break;
            case "manageSession":
            default :
                this.aManageSession(req, resp);
                break;
        }
    } 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        
    } 
    
    protected void aManageAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            if(session.getAttribute("listAccount") == null)
                session.setAttribute("listAccount", AccountDao.listAccounts());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_account.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aSearchAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String keySearch = req.getParameter("keySearch");
            ArrayList<Account> listAccount = (ArrayList<Account>)AccountDao.listAccounts();
            if(keySearch != null && !keySearch.isEmpty() && listAccount != null 
                    && !listAccount.isEmpty()){ // not empty keySearch
                listAccount = (ArrayList<Account>) listAccount.stream().filter(a -> a.getFullname().contains(keySearch) || a.getFullname().equals(keySearch)).collect(Collectors.toList());
            }
            req.setAttribute("listAccount", listAccount);
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_account.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    } 
    
    protected void aManageSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            req.setAttribute("timestamp", System.currentTimeMillis());
        req.setAttribute("numberOfSessions",
                SessionRegistry.getNumberOfSessions());
        req.setAttribute("sessionList", SessionRegistry.getAllSessions());
        req.getRequestDispatcher("/WEB-INF/jsp/view/admin/admin_index.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("guest");
        }
    }

    protected void aUpdateAccountStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String email = (String)req.getParameter("email");
            Account acUpdate = AccountDao.getAccount(email);
            if(email != null && !email.isEmpty() && acUpdate != null){
                acUpdate = AccountDao.updateAccountStatus(email, acUpdate.getStatus());
                req.getSession().setAttribute("listAccount", AccountDao.listAccounts());
                req.setAttribute("updateAccount_Successful", true);
                req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_account.jsp").forward(req, resp);
            }else resp.sendRedirect("admin");
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    } 

    protected void aManagePlant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            if(req.getSession().getAttribute("plantList") == null) 
                req.getSession().setAttribute("plantList", PlantDao.listPlants());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    } 
    
    protected void aUpdatePlant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int plantId = Integer.parseInt(req.getParameter("plantId"));
            String newPlantName = req.getParameter("plantName");
            int newPrice = Integer.parseInt(req.getParameter("price"));
            String newImgPath= req.getParameter("imgPath");
            String newDescription= req.getParameter("description");
            int newStatus = Integer.parseInt(req.getParameter("status"));
            int newCateId = Integer.parseInt(req.getParameter("cateId"));
            
            if(PlantDao.updatePlant(plantId, newPlantName, newPrice, newImgPath, newDescription, newStatus, newCateId) != null){
                req.getSession().setAttribute("plantList", PlantDao.listPlants());
                req.setAttribute("updatePlant_Successful", true);
                req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
            }else resp.sendRedirect("admin");
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aDeletePlant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int plantId = Integer.parseInt(req.getParameter("plantId"));
            Plant plantDelete = PlantDao.getPlant(plantId);
            if(plantDelete == null) resp.sendRedirect("admin");
            
            plantDelete = PlantDao.deletePlant(plantId);
            req.setAttribute("updatePlant_Successful", true);
            req.getSession().setAttribute("plantList", PlantDao.listPlants());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }

    protected void aSearchPlant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String keySearch = req.getParameter("keySearch");
            ArrayList<Plant> listPlant = (ArrayList<Plant>)PlantDao.listPlants();
            if(keySearch != null && !keySearch.isEmpty() && listPlant != null 
                    && !listPlant.isEmpty()){ // not empty keySearch
                listPlant = (ArrayList<Plant>) listPlant.stream().filter(p -> p.getPlantName().equals(keySearch) || p.getPlantName().contains(keySearch)).collect(Collectors.toList());
            }
            req.setAttribute("plantList", listPlant);
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aInsertPlant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String new_plantName = req.getParameter("new_plantName");
            int new_price = Integer.parseInt(req.getParameter("new_price"));
            String new_imgPath = req.getParameter("new_imgPath");
            String new_description = req.getParameter("new_description");
            int new_status = Integer.parseInt(req.getParameter("new_status"));
            int new_cateId = Integer.parseInt(req.getParameter("new_cateId"));
            
            // input valid
            Plant plant = new Plant(new_plantName, new_price, new_imgPath, new_description, new_status, new_cateId);
            if(PlantDao.insertPlant(plant) == null){
                resp.sendRedirect("admin");
                return;
            }
            
            req.setAttribute("addPlant_Successful", true);
            req.getSession().setAttribute("plantList", PlantDao.listPlants());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_plant.jsp").forward(req, resp);
        }
    }
    
    protected void aManageCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            if(session.getAttribute("cateList") == null)
                session.setAttribute("cateList", CategoryDao.listCate());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_cate.jsp").forward(req, resp);
        } catch (Exception e) {
        }
    }

    protected void aUpdateCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int cateId = Integer.parseInt(req.getParameter("cateId"));
            Categories cate = CategoryDao.getCate(cateId);
            String newCateName = req.getParameter("cateName");
            HttpSession session = req.getSession();
            if(cate != null && CategoryDao.updateCate(cateId, newCateName) != null){
                session.setAttribute("cateList", CategoryDao.listCate());
                req.setAttribute("updateCate_Successful", true);
                req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_cate.jsp").forward(req, resp);
            }else resp.sendRedirect("admin");
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aInsertCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String new_cateName = req.getParameter("new_cateName");
            Categories cate = new Categories(new_cateName);
            if(CategoryDao.insertCate(cate) == null){
                resp.sendRedirect("admin");
                return;
            }
            
            req.setAttribute("addCate_Successful", true);
            req.getSession().setAttribute("cateList", CategoryDao.listCate());
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_cate.jsp").forward(req, resp);
        } catch (Exception e) {e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aManageOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            ArrayList<Order> orderList = (ArrayList<Order>)OrderDao.listOrders();
                session.setAttribute("orderList", orderList);
                
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_order.jsp").forward(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(AdminPageServlet.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect("admin");
        }
    }
    
    protected void aManageOrder_reOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = OrderDao.getOrder(orderId);
            HttpSession session = req.getSession();
            if(order.getStatus() == Order.CANCEL){
                order = OrderDao.updateOrderStatus(orderId, Order.PROCESSING);
                if(order != null){
                    session.setAttribute("orderList", OrderDao.listOrders());
                    req.setAttribute("updateOrder_Successful", true);
                }
            }
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_order.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aManageOrder_cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = OrderDao.getOrder(orderId);
            HttpSession session = req.getSession();
            if(order.getStatus() == Order.PROCESSING){
                order = OrderDao.updateOrderStatus(orderId, Order.CANCEL);
                if(order != null){
                    session.setAttribute("orderList", OrderDao.listOrders());
                    req.setAttribute("updateOrder_Successful", true);
                }
            }
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_order.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aSearchOrderByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String customerName = req.getParameter("customerName");
            if(customerName != null && !customerName.isEmpty()){
                ArrayList<Order> orderList = (ArrayList<Order>)OrderDao.listOrderByName(customerName);
                if(orderList != null){
                    req.setAttribute("orderList", orderList);
                    req.setAttribute("customerName", customerName);
                }
            }
            req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_order.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
    
    protected void aSearchOrderByDate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String fromDate = req.getParameter("fromDate");
            String toDate = req.getParameter("toDate");
            
            if(fromDate != null && !fromDate.isEmpty() 
                    && toDate != null && !toDate.isEmpty()){
            ArrayList<Order> res = (ArrayList<Order>)OrderDao.searchOrders(fromDate, toDate);
                if(res != null && !res.isEmpty()){
                    req.setAttribute("orderList", res);
                    req.getRequestDispatcher("/WEB-INF/jsp/view/admin/manage_order.jsp").forward(req, resp);
                }else resp.sendRedirect("admin");
            }else resp.sendRedirect("admin");
            
        } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("admin");
        }
    }
}
