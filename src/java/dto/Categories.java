/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author RaeKyo
 */
public class Categories implements Serializable{    
    private int cateId; // CateID
    private String cateName; // CateName

    public Categories(String cateName) {
        this.cateName = cateName;
    }
    
    public Categories(int cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }

    public Categories() {}

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "Categories{" + "cateId=" + cateId + ", cateName=" + cateName + '}';
    }   
}
