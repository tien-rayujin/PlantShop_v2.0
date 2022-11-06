/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Categories;
import java.util.ArrayList;

/**
 *
 * @author RaeKyo
 */
public class TestCategoryDao {
    public static void main(String[] args) {
//        try {
//            Categories cate = CategoryDao.getCate(1);
//            if(cate != null){
//                System.out.println("Successful");
//                System.out.println(cate);
//            }else System.out.println("Fail");
//        } catch (Exception e) {
//        }
        try {
            ArrayList<Categories> list = CategoryDao.listCate();
            list.forEach(System.out::println);
        } catch (Exception e) { e.printStackTrace();
        }
           
    }
}
