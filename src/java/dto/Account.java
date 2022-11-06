/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author RaeKyo
 */
public class Account implements Serializable{
    // account role
    public static final int USER = 0;
    public static final int ADMIN = 1;
    
    // account status
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    private String fullname;
    private String password;
    private String email;
    private String phone;
    
    private int accId;
    private int status;
    private int role;
    
    public Account(){}

    public Account(String fullname, String password, String email, String phone, int status, int role) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    
    
    public Account(String fullname, String password, String email, String phone, int accId, int status, int role) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.accId = accId;
        this.status = status;
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "fullname=" + fullname + ", password=" + password + ", email=" + email + ", phone=" + phone + ", accId=" + accId + ", status=" + status + ", role=" + role + '}';
    }
    
    
}
