/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RaeKyo
 */
public class TestAccountDao {
    public static AccountDao dao = new AccountDao();
    public static void main(String[] args) {
        System.out.println("Before Task");
        listAll();
        
        System.out.println("");
        System.out.println("After Task");
        
//        Account newAccount = 
//                new Account("TestSomething", "123456", "test99@gmail.com", "019903193130", Account.ACTIVE, Account.USER);
//        insert(newAccount);
//        listAll();
        
        
//        String del_email = "test99@gmail.com";
//        delete(del_email);
//        listAll();

//        try {
//            Account ac = AccountDao.getAccount("test2@gmail.com");
//            ac.setFullname("Test100000");
//            ac.setPhone("0918273645");
//            updateAccount(ac);
//        } catch (Exception e) { e.printStackTrace();
//        }


        try {
            Account ac = AccountDao.getAccount("test2@gmail.com");
            changeProfile(ac.getEmail(), "Test_Hi", "011111111111");
        } catch (Exception e) {
        }
    }
    public static final void updateAccount(Account ac){
        try {
            Account res = AccountDao.updateAccount(ac);
            if(res != null){
                System.out.println("Successful");
                System.out.println(res);
            }else System.out.println("Fail");
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    public static final void changeProfile(String email, String newName, String newPhone){
        try {
            Account res = AccountDao.changeAccountProfile(email, newName, newPhone);
            if(res != null){
                System.out.println("Successful");
                System.out.println(res);
            }else System.out.println("Fail");
        } catch (Exception e) { e.printStackTrace();
        }
    }
    
    public static final void listAll(){
                try {
            ArrayList<Account> list =(ArrayList<Account>)dao.listAccounts();
            if(list != null && list.size() > 0){
                list.forEach(System.out::println);
            }else System.out.println("Fail");
        } catch (Exception e) {
        }
    }
    
    
    public static final void insert(Account newAccount){
        try {
            AccountDao.insertAccount(newAccount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void delete(String email){
        try {
//            AccountDao.deleteAccount(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void isTokenExist(String token){
        try {
            Account ac = AccountDao.isTokenExist(token);
            if(ac != null){
                System.out.println(ac);
            }else System.out.println("Token is not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void deleteToken(String email){
        try {
             Account res = AccountDao.deleteToken(email);
            if(null == res) System.out.println("Fail");
            else{
                System.out.println("Successful");
                listAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static final void addToken(String token, String email){
        try {
            Account res = AccountDao.addToken(token, email);
            if(null == res) System.out.println("Fail");
            else{
                System.out.println("Successful");
                listAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
