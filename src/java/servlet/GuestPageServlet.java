/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import dao.AccountDao;
import dao.PlantDao;
import dto.Account;
import dto.Plant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "guestPageServlet", urlPatterns = "/guest")
public class GuestPageServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action == null)
            action = "list";
        
        switch (action) {
            case "search":
                this.gSearch(req, resp);
                break;
            case "login":
                this.gLogin(req, resp);
                break; 
            case "register":
                this.gRegister(req, resp);
                break; 
            case "view":
                this.gViewPlantDetail(req, resp);
                break;
            case "addToCart":
                this.g_uAddToCart(req, resp);
                break;
            case "viewCart":
                this.g_uViewCart(req, resp);
                break;    
            case "updateQuantity":
                this.g_uUpdateQuantity(req, resp);
                break;
            case "deleteCartItem":
                this.g_uDeleteCartItem(req, resp);
                break; 
            case "emptyCartItem":
                this.g_uEmptyCart(req, resp);
                break;    
            case "list":
            default:
                this.gListPlant(req, resp);
                break;
        }// switch        
        
        
        
    }//doGet
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action == null)
            action = "list";
        
        switch (action) {
            case "login":
                this.gLogin_process(req, resp);
                break;
            case "register":
                this.gRegister_process(req, resp);
                break;   
            default: // just a tommy (for fun)
                this.gListPlant(req, resp);
                break;
        }
            
    }
    
    /**
     * check if user have cookie valid account'token in database
     * if yes -> return true
     * if not -> return false
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException 
     */
    private boolean gLoginToken(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        List<Cookie> cList = Arrays.asList(req.getCookies());
        if(cList != null){
            // all JSESSIONID from Client
            HashMap<String, String> tokenList = (HashMap<String, String>)cList.stream().collect(Collectors.toMap(Cookie::getName, Cookie::getValue));
            String token = tokenList.get("token");
            if(token != null && !token.isEmpty()){
                try {
                    Account gTokenAccount = AccountDao.isTokenExist(token);
                    if(gTokenAccount != null){ // usr have token and token exist
                        session.setAttribute("username", gTokenAccount.getEmail());
                        session.setAttribute("user", gTokenAccount);
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } // exist cookie name "token" 
        }// if page use cookie
        return false;
    }
    
    /**
     * check cookie of user if exist then redirect to user page 
     * if not then forward to login page to input form
     * 
     * add to session: username(String), user(Account)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gLogin(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("username") != null){ // if user already login
            resp.sendRedirect("user");
            return;
        }
        
        // if user have login exist, then check if with account token in database if valid then 
        // redirect, if not then just skip and forward to login to input form
        if(this.gLoginToken(req, resp)){
            Account acLogin = (Account)session.getAttribute("user");
            if(acLogin != null && acLogin.getRole() == Account.USER) resp.sendRedirect("user");
            else if(acLogin != null && acLogin.getRole() == Account.ADMIN) resp.sendRedirect("admin");
            return;
        }
        
        // if user have not login yet, and dont have cookie
        req.setAttribute("loginFailed", false);
        req.getRequestDispatcher("/WEB-INF/jsp/view/guest/login.jsp").forward(req, resp);
    }
    
    /**
     * if user input valid account then add userObject and username to session
     * if user require login by cookie then save JSESSIONID to Token of the account
     * in database
     * after login then redirect to user page
     * 
     * add to session: username(String), user(Account)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gLogin_process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("username") != null){ // if user already login
            resp.sendRedirect("user");
            return;
        }
        
        try {
             // if user have not login yet
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String remember = req.getParameter("remember"); // ask to save token
            Account acLogin = AccountDao.validateAccount(username, password);
        
            if(username == null || password == null ||
                acLogin == null){
                // user input is invalid
                req.setAttribute("loginFailed", true);
                req.getRequestDispatcher("/WEB-INF/jsp/view/guest/login.jsp").forward(req, resp);
            }else{
                // user input is valid account
                session.setAttribute("username", username);
                session.setAttribute("user", acLogin);
                
                // guest request that remember this account ,save token of user login
                if(remember != null){
                    String token = ""; // get the value of cookie is token not JSESSIONID
                    for(Cookie c : req.getCookies()){
                        if(c.getName().equals("JSESSIONID")){ // 
                            token = c.getValue();
                        }
                    }
                    if(token == null || token.isEmpty()) token = req.getCookies()[0].getValue();
                    if(AccountDao.addToken(token, username) == null) throw new Exception("Failed to add token to database");
                    
                    // addCookie to user browser
                    Cookie gTokenAccount = new Cookie("token", token);
                    resp.addCookie(gTokenAccount);
                }// remember checked
                
                // change sessionId to prevent fixsesison 
                // req.changeSessionId();
                if(acLogin.getRole() == Account.USER) resp.sendRedirect("user");
                else if(acLogin.getRole() == Account.ADMIN) resp.sendRedirect("admin");
            }
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    /**
     * check if user already login -> if yes then redirect page
     * if not then go to Register page to input form
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gRegister(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("username") != null){  // if user already login
            resp.sendRedirect("user");
            return;
        }
        // if user have login exist, then check if with account token in database if valid then 
        // redirect, if not then just skip and forward to login to input form
        if(this.gLoginToken(req, resp)){
            Account acLogin = (Account)session.getAttribute("user");
            if(acLogin != null && acLogin.getRole() == Account.USER) resp.sendRedirect("user");
            else if(acLogin != null && acLogin.getRole() == Account.ADMIN) resp.sendRedirect("admin");
            return;
        }
        
        // user is have not login yet 
        req.setAttribute("registerFailed", false);
        req.getRequestDispatcher("/WEB-INF/jsp/view/guest/register.jsp").forward(req, resp);
    }
    
    /**
     * If user are input valid then add username and user(Object) to session
     * and redirect to login page (to check if need cookie)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gRegister_process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("username") != null){ // if user already login
            resp.sendRedirect("user");
            return;
        }
        
        // if user have not login yet
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        
        
        try {
            if(username == null || password == null 
                    || phone == null || email == null||
                    AccountDao.isExist(email) != null){ // email already exist
                req.setAttribute("registerFailed", true);
                req.getRequestDispatcher("/WEB-INF/jsp/view/guest/register.jsp").forward(req, resp);
            }else{
                // user input is valid, add new Account to DATABASE
                int status = Account.ACTIVE;
                int role = Account.USER;
                Account newAccount = new Account(username, password, email, phone, status, role);
                AccountDao.insertAccount(newAccount);
                
                req.setAttribute("username", username);
                req.getRequestDispatcher("/WEB-INF/jsp/view/guest/register_successful.jsp").forward(req, resp);
            }
        } catch (Exception e) { e.printStackTrace();
        }
        
    }
    
    protected void gSearch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            String keySearch = req.getParameter("keySearch");
            String searchBy  = req.getParameter("searchBy");
            
            if(keySearch == null) keySearch = "other";
            ArrayList<Plant> plantList_search = (ArrayList<Plant>)PlantDao.searchPlant(keySearch, searchBy);
            
            // put plantList to sessionScope
            req.setAttribute("plantList", plantList_search);
            req.setAttribute("keySearch", keySearch);
            req.getRequestDispatcher("/WEB-INF/jsp/view/guest/guest_index.jsp").forward(req, resp);
        } catch (Exception ex) {  // code tu dong :))
            Logger.getLogger(GuestPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }// catch
    }
    
    /**
     * when an user access webpage then list all product
     * give user plantList (ArrayList<Plant>) and cart(HashMap<Integer, Integer>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gListPlant(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            if(session.getAttribute("plantList") == null){
                ArrayList<Plant> plantList = (ArrayList<Plant>)PlantDao.listPlants();;
                session.setAttribute("plantList", plantList);
            }else if(session.getAttribute("cart") == null)
                    session.setAttribute("cart", new HashMap<Integer, Integer>());
            
            req.getRequestDispatcher("/WEB-INF/jsp/view/guest/guest_index.jsp").forward(req, resp);
        } // gListPlant
        catch (Exception ex) {
            Logger.getLogger(GuestPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * when click on detail on an plant -> send back plantId
     * get that Plant information and send back to user on plant_detail.jsp
     * 
     * add to requestScope plant(Plant of plantId)
     *                     addToCart_Successful = false 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void gViewPlantDetail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        // view plant detail information, and some action to add to cart 
        try {
            int plantId = Integer.parseInt(req.getParameter("plantId"));
            Plant plant = PlantDao.getPlant(plantId);
            
            if(plant != null){
                req.setAttribute("plant", plant);
                req.setAttribute("addToCart_Successful", false); // display message
                req.getRequestDispatcher("/WEB-INF/jsp/view/guest/plant_detail.jsp").forward(req, resp);
            }else resp.sendRedirect("guest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * when user click on addToCart on plant_detail.jsp, the valid product will be added to cart
     * and user and return to home page to see the product in viewCart
     * 
     * add to requestScope addToCart_Successful = true ( if valid )
     *                     plant(Plant by plantId)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void g_uAddToCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            int plantId = Integer.parseInt(req.getParameter("plantId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Plant plant = PlantDao.getPlant(plantId);
            
            if(plant != null){// plant exist
                if(session.getAttribute("cart") == null) // if cart is not exist yet then create a new one
                    session.setAttribute("cart", new HashMap<Integer, Integer>());
            
                HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
                if(!cart.containsKey(plantId)) // plant dont have in cart yet
                    cart.put(plantId, 0); 
                
                // plant are in cart
                cart.put(plantId, cart.get(plantId) + quantity);
                
                // fordward back to the back call AddToCart and show message 
                req.setAttribute("addToCart_Successful", true); // display message
                req.setAttribute("plant", plant);
                req.getRequestDispatcher("/WEB-INF/jsp/view/guest/plant_detail.jsp").forward(req, resp);
            }else resp.sendRedirect("guest");
        } catch (Exception ex) {
            Logger.getLogger(GuestPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * redirect to viewCart.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void g_uViewCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            req.setAttribute("updateCart_Successful", false);
            req.getRequestDispatcher("/WEB-INF/jsp/view/guest/viewCart.jsp").forward(req, resp);
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    /**
     * when user click on button update(quantity) after change the quantity on viewCart.jsp
     * if valid plantId specific then set cart to valid quantity
     * 
     * add to requestScope updateCart_Successful = true
     * add to sessionScope (replication) cart (HashMap<Integer, Integer>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void g_uUpdateQuantity(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("cart") == null) resp.sendRedirect("guest");
        else{
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
            if(cart == null || cart.isEmpty()) 
                resp.sendRedirect("guest?action=viewCart");
            
            // cart must exist then check plant to update
            Plant plantUpdate;
            try { // check valid plantId valid
                int plantIdUpdate = Integer.parseInt(req.getParameter("plantId"));
                int quantity = Integer.parseInt(req.getParameter("quantityUpdate"));
                plantUpdate = PlantDao.getPlant(plantIdUpdate);
                if(plantUpdate != null){//plant exist
                    if(cart.get(plantIdUpdate) != quantity) { // different quantity
                        cart.put(plantIdUpdate, quantity);
                        session.setAttribute("cart", cart); // cart replication 
                        req.setAttribute("updateCart_Successful", true); // message display
                    }
                    req.getRequestDispatcher("/WEB-INF/jsp/view/guest/viewCart.jsp").forward(req, resp);
                }else{ // plant is not exist
                   resp.sendRedirect("guest");
                }
            } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("guest");
            }        
        }
    }
    
    /**
     * when user click on delete on specific product in viewCart.sp
     * return plantId of valid product then access cart and remove the valid products
     * 
     * add to requestScope updateCart_Successful = true
     * add to sessionScope (replication) cart(HashMap<Integer, Integer>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void g_uDeleteCartItem(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        HttpSession session = req.getSession();
        if(session.getAttribute("cart") == null) resp.sendRedirect("guest");
        else{
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
            if(cart == null || cart.isEmpty()) 
                resp.sendRedirect("guest?action=viewCart");
            
            // cart must exist then check plant to update
            Plant plantUpdate;
            try { // check valid plantId valid
                int plantIdUpdate = Integer.parseInt(req.getParameter("plantId"));
                plantUpdate = PlantDao.getPlant(plantIdUpdate);
                if(plantUpdate != null){//plant exist
                    cart.remove(plantIdUpdate);
                    session.setAttribute("cart", cart);
                    req.setAttribute("updateCart_Successful", true);
                    req.getRequestDispatcher("/WEB-INF/jsp/view/guest/viewCart.jsp").forward(req, resp);
                }else{// plant is not exist
                   resp.sendRedirect("guest");
                }
            } catch (Exception e) { e.printStackTrace(); resp.sendRedirect("guest");
            }        
        }
    }
    
    /**
     * when user click on link to emptyCart on that cart IS NOT EMPTY on viewCart.jsp
     * then then cart in session will be replace
     * 
     * add to sessionScope (replication)cart(HashMap<Integer, Integer>)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void g_uEmptyCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        try {
            HttpSession session = req.getSession();
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)session.getAttribute("cart");
            if(cart == null || cart.isEmpty()) resp.sendRedirect("guest?action=viewCart");
            
            // client need to empty cart
            cart = new HashMap<Integer, Integer>();
            session.setAttribute("cart", cart);
            resp.sendRedirect("guest?action=viewCart");
        } catch (Exception e) { e.printStackTrace();
        }
    }
}
