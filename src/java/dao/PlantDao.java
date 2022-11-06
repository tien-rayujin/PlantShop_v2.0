/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Plant;

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
public class PlantDao {
    private static Connection con;

    
    private static final void connect() throws Exception{
        con = DB_Utils.makeConnection();
    }
    
    private static final void disconnect() throws Exception{
        con.close();
    }
    
    @SuppressWarnings("empty-statement")
    public static List<Plant> listPlants() throws Exception{
        List<Plant> list = new ArrayList<>();
        connect();
        
        String sql = "SELECT * FROM Plants";
        ResultSet rs = con.createStatement().executeQuery(sql);
        if(rs != null){
            while (rs.next()) {
                int plantId = rs.getInt("PID");
                String plantName = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");

                list.add(new Plant(plantId, plantName, price, imgPath, description, status, cateId));
            }// while
        }// if
        
        
        if(rs != null) rs.close();
        disconnect();
        return list;
    }// listPlants  
    
    @SuppressWarnings("empty-statement")
    public static Plant getPlant(int _plantId) throws Exception{
        Plant plant = null;
        connect();
        
        String sql = "SELECT * FROM Plants WHERE PID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, _plantId);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs != null){
            if(rs.next()){
                int plantId = _plantId;
                String plantName = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                
                plant = new Plant(plantId, plantName, price, imgPath, description, status, cateId);
            }
        }
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return plant;
    }
    
    public static Plant getPlant(String _plantName) throws Exception{
        Plant plant = null;
        connect();
        
        String sql = "SELECT * FROM Plants WHERE PName = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _plantName);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs != null){
            if(rs.next()){
                int plantId = rs.getInt("PID");
                String plantName = _plantName;
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                
                plant = new Plant(plantId, plantName, price, imgPath, description, status, cateId);
            }
        }
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return plant;
    }
    
    
    public static List<Plant> searchPlant(String keySearch, String searchBy) throws Exception{
        if(keySearch.equalsIgnoreCase("other"))
            return PlantDao.listPlants();
        else if(searchBy.equals("byName")) 
            return PlantDao.searchPlant_byName(keySearch, searchBy);
        else return PlantDao.searchPlant_byCate(keySearch, searchBy);
    }
    public static List<Plant> searchPlant_byName(String keySearch, String searchBy) throws Exception{
        List<Plant> list = new ArrayList<>();
        connect();
        
        String sql = "select * from Plants p JOIN Categories c on p.CateID = c.CateID\n" +
                    "where p.PName like ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + keySearch + "%");
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            while (rs.next()) {
                int plantId = rs.getInt("PID");
                String plantName = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");

                list.add(new Plant(plantId, plantName, price, imgPath, description, status, cateId));
            }// while
        }// if
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return list;
    }
    public static List<Plant> searchPlant_byCate(String keySearch, String searchBy) throws Exception{
        List<Plant> list = new ArrayList<>();
        connect();
        
        String sql = "select * from Plants p JOIN Categories c on p.CateID = c.CateID\n" +
                    "where c.CateName like ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + keySearch + "%");
        ResultSet rs = ps.executeQuery();
        if(rs != null){
            while (rs.next()) {
                int plantId = rs.getInt("PID");
                String plantName = rs.getString("PName");
                int price = rs.getInt("price");
                String imgPath = rs.getString("imgPath");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");

                list.add(new Plant(plantId, plantName, price, imgPath, description, status, cateId));
            }// while
        }// if
        
        if(ps != null) ps.close();
        if(rs != null) rs.close();
        disconnect();
        return list;
    }

    public static Plant updatePlant(Plant plant) throws Exception{
        connect();
        
        Plant plantUpdate = null;
        String sql = "UPDATE Plants SET PName=?, price=?, imgPath=?, description=?, status=?, cateID=? ";
        sql += " WHERE PID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, plant.getPlantName());
        ps.setInt(2, plant.getPrice());
        ps.setString(3, plant.getImgPath());
        ps.setString(4, plant.getDescription());
        ps.setInt(5, plant.getStatus());
        ps.setInt(6, plant.getCateId());
        ps.setInt(7, plant.getPlantId());
        
        if(ps.executeUpdate() > 0) plantUpdate = getPlant(plant.getPlantId());
        
        disconnect();
        return plantUpdate;
    }
    
    public static Plant updatePlant(int plantId, String newPlantName, int newPrice,
            String newImgPath, String newDescription, int newStatus, int newCateId) throws Exception{
        Plant plantUpdate = getPlant(plantId);
        if(plantUpdate == null) return plantUpdate;
        
        if(plantUpdate.getPlantName() == null ||!plantUpdate.getPlantName().equals(newPlantName)) plantUpdate.setPlantName(newPlantName);
        if(plantUpdate.getPrice()== 0 || plantUpdate.getPrice() != newPrice) plantUpdate.setPrice(newPrice);
        if(plantUpdate.getImgPath() == null || !plantUpdate.getImgPath().equals(newImgPath)) plantUpdate.setImgPath(newImgPath);
        if(plantUpdate.getDescription() == null || !plantUpdate.getDescription().equals(newDescription)) plantUpdate.setDescription(newDescription);
        if(plantUpdate.getStatus()== 0 || plantUpdate.getStatus() != newStatus) plantUpdate.setStatus(newStatus);
        if(plantUpdate.getCateId()== 0 || plantUpdate.getCateId() != newCateId) plantUpdate.setCateId(newCateId);
        
        return updatePlant(plantUpdate);
    }

    public static Plant insertPlant(Plant plant) throws Exception{
        if(getPlant(plant.getPlantId()) != null) return null;
        if(plant.getPlantName().isEmpty() || getPlant(plant.getPlantName()) != null
                || plant.getPrice() < 0
                || plant.getImgPath().isEmpty() || !plant.getImgPath().startsWith("/images/Plants")
                || plant.getDescription().isEmpty()
                || plant.getStatus() != Plant.UNAVAILABLE && plant.getStatus() != Plant.AVAILABLE
                || plant.getCateId() <=0 || CategoryDao.getCate(plant.getCateId()) == null ) return null;
        
        connect();
        
        String sql = "insert into Plants(PName, price, imgPath, description, status, CateID) ";
        sql += "values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, plant.getPlantName());
        ps.setInt(2, plant.getPrice());
        ps.setString(3, plant.getImgPath());
        ps.setString(4, plant.getDescription());
        ps.setInt(5, plant.getStatus());
        ps.setInt(6, plant.getCateId());
        
        if(ps.executeUpdate() > 0) plant = PlantDao.getPlant(plant.getPlantName());
        
        disconnect();
        return plant;
    }
    
    public static Plant deletePlant(int plantId) throws Exception{
        Plant plant = getPlant(plantId);
        if(plant == null) return null;
        
        connect();
        String sql = "DELETE FROM Plants WHERE PID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, plantId);
        if(ps.executeUpdate() < 0) return null; 
        
        disconnect();
        return plant;
    }
}// end PlantDao
