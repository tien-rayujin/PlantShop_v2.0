/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DB_Utils;

/**
 *
 * @author RaeKyo
 */
public class AccountDao {
    private static Connection con;

    
    private static final void connect() throws Exception{
        con = DB_Utils.makeConnection();
    }
    
    private static final void disconnect() throws Exception{
        con.close();
    }
    
    public static List<Account> listAccounts() throws Exception{
        List<Account> list = new ArrayList<>();
        connect();
        
        String sql = "SELECT * from Accounts";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            
            while(rs.next()){
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int accid = rs.getInt("accid");
                int status = rs.getInt("status");
                int role = rs.getInt("role");
                
                
                Account newAc = new Account(fullname, password, email, phone, accid, status, role);
                list.add(newAc);
            }
            
        }
        
        disconnect();
        
        return list;
    }// getAccount
    
    public static Account insertAccount(Account _account) throws Exception{
        if(_account == null) return null;
        connect();
        
        Account ac = null;
        String sql = "INSERT INTO Accounts(email, password, fullname, phone, status, role) ";
        sql += "VALUES(?, ?, ?, ? , ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _account.getEmail());
        ps.setString(2, _account.getPassword());
        ps.setString(3, _account.getFullname());
        ps.setString(4, _account.getPhone());
        ps.setInt(5, _account.getStatus());
        ps.setInt(6, _account.getRole());
        
        if(ps.executeUpdate() > 0) ac = getAccount(_account.getEmail());
        ps.close();
        disconnect();
        
        return ac;
    }
    
    public static Account getAccount(String _email) throws Exception{
        Account ac = null;
        
        connect();
        
        String sql = "SELECT * from Accounts WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _email);

        
        ResultSet rs = ps.executeQuery();
        
        if (rs != null) {
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String email = _email;
                String phone = rs.getString("phone");
                int accid = rs.getInt("accid");
                int status = rs.getInt("status");
                int role = rs.getInt("role");

                ac = new Account(fullname, password, _email, phone, accid, status, role);
            }
        }
        
        rs.close();
        ps.close();
        disconnect();
        return ac;
    }
    
    public static List<Account> getAccountByName(String _fullname) throws Exception{
        ArrayList<Account> list = new ArrayList<>();
        connect();
        
        String sql = "SELECT * from Accounts WHERE fullname = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _fullname);

        
        ResultSet rs = ps.executeQuery();
        
        if (rs != null) {
            while (rs.next()) {
                String fullname = _fullname;
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int accid = rs.getInt("accid");
                int status = rs.getInt("status");
                int role = rs.getInt("role");

                list.add(new Account(fullname, password, email, phone, accid, status, role));
            }
        }
        
        rs.close();
        ps.close();
        disconnect();
        return list;
    }
            
    public static Account validateAccount(String _email, String _password) throws Exception{
        Account ac = null;
        connect();
        
        String sql = "SELECT * from Accounts WHERE email = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _email);
        ps.setString(2, _password);
        
        ResultSet rs = ps.executeQuery();
         
        if (rs != null) {
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                String password = _password;
                String email = _email;
                String phone = rs.getString("phone");
                int accid = rs.getInt("accid");
                int status = rs.getInt("status");
                int role = rs.getInt("role");

                ac = new Account(fullname, _password, _email, phone, accid, status, role);
            }
        }// rs != null
        
        
        rs.close();
        ps.close();
        
        disconnect();

        return ac;
    }
    
    public static Account changeAccountProfile(String _email, String _newName, String _newPhone) throws Exception{
        Account upAc = null;
        connect();
        
        upAc = getAccount(_email);
        if(upAc == null) return null;
        if(_newName == null || _newName.isEmpty()) _newName = upAc.getFullname();
        if(_newPhone == null || _newPhone.isEmpty()) _newPhone = upAc.getPhone();
      
        upAc.setFullname(_newName);
        upAc.setPhone(_newPhone);
        
        upAc = updateAccount(upAc);
        disconnect();
        return upAc;
    }
    
    public static Account updateAccount(Account _account) throws Exception{
        connect();
        Account ac = null;
        
        String sql = "update Accounts set email = ?,fullname = ?, password = ?, phone = ?, status = ?, role = ? ";
        sql += "where accID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _account.getEmail());
        ps.setString(2, _account.getFullname());
        ps.setString(3, _account.getPassword());
        ps.setString(4, _account.getPhone());
        ps.setInt(5, _account.getStatus());
        ps.setInt(6, _account.getRole());
        ps.setInt(7, _account.getAccId());
        
        
        if(ps.executeUpdate() > 0) ac = getAccount(_account.getEmail());
        
        if(ps != null) ps.close();
        disconnect();
        return ac;
    }
    
    public static Account updateAccountStatus(String _email, int _status) throws Exception{
        connect();
        Account acUpdate = getAccount(_email);
        if(acUpdate == null) return null;
        else if(_status == Account.ACTIVE){
            acUpdate.setStatus(Account.INACTIVE);
        }else if(_status == Account.INACTIVE){
            acUpdate.setStatus(Account.ACTIVE);
        }
        return updateAccount(acUpdate);
    }
    
    public static Account isExist(String _email) throws Exception{
        connect();
        
        Account ac = null;
        String sql = "SELECT * from Accounts WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _email);
        
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()) ac=getAccount(_email);
        
        
        
        if(ps!= null) ps.close();
        if(rs!= null) rs.close();
        disconnect();
        return ac;
    }
    
    public static Account addToken(String _token, String _email) throws Exception{
        if(_email.isEmpty() || _email == null || _token.isEmpty() || _token == null) return null;
        if(isExist(_email) == null) return null; // account is not exist
        connect();
        
        Account ac = null;
        String sql = "UPDATE Accounts SET token = ? WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _token);
        ps.setString(2, _email);

        if(ps.executeUpdate() > 0) ac = getAccount(_email);
        ps.close();
        disconnect();
        return ac;
    }
    
    public static Account isTokenExist(String _token) throws Exception{
        Account ac = null;
        connect();
        
        String sql = "select * from Accounts where token = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _token);
        ResultSet rs = ps.executeQuery();
        
        if(rs != null){
            while(rs.next()){
                String fullname = rs.getString("fullname");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int accid = rs.getInt("accid");
                int status = rs.getInt("status");
                int role = rs.getInt("role");

                ac = new Account(fullname, password, email, phone, accid, status, role);
                 
            }
        }
        
        disconnect();
        return ac;
    }
    
    public static Account deleteToken(String _email) throws Exception{
        if(_email.isEmpty() || _email == null) return null;
        connect();
        
        Account ac = null;
        String sql = "UPDATE Accounts SET token = NULL WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _email);
        
        if(ps.executeUpdate() > 0) ac = getAccount(_email);
        ps.close();
        disconnect();
        return ac;
    }
}
