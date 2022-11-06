/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Plant;
import java.util.ArrayList;

/**
 *
 * @author RaeKyo
 */
public class TestPlantDAO {
    
    public static void main(String[] args) {
        // test getPlant(plantId)
        
        //test update plant
//        try {
//            Plant p = PlantDao.getPlant(1);
//            if(p != null){
//                p.setPrice(325);
//                p.setPlantName("cay can thang");
//                p = PlantDao.updatePlant(p);
//                if(p != null){
//                    System.out.println("Successful");
//                    System.out.println(p);
//                }else System.out.println("Fail");
//            }
//        } catch (Exception e) { e.printStackTrace();
//        }
        
        try {
            Plant p = PlantDao.insertPlant(new Plant("cay hoa giay", 325, "cay-gua.jpg", "none", 1, 1));
            if(p != null){
                System.out.println("Successful");
                System.out.println(p);
            }else System.out.println("Fail");
        } catch (Exception e) {
        }
    }
    
    public void searchPlant(){
        try {
            ArrayList<Plant> list = (ArrayList<Plant>)PlantDao.searchPlant("ro", "byCate");
            if(list == null && list.size() > 0) System.out.println("Fail");
            else{
                System.out.println("Successful");
                list.forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
