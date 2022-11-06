/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.DB_Utils;

/**
 *
 * @author RaeKyo
 */
public class CategoryDao {
    private static Connection con;

    
    private static final void connect() throws Exception{
        con = DB_Utils.makeConnection();
    }
    
    private static final void disconnect() throws Exception{
        con.close();
    }
    
    public static Categories getCate(int _cateId) throws Exception{
        connect();
        
        Categories cate = null;
        String sql = "select * from Categories where CateId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, _cateId);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            int CateId = rs.getInt("CateID");
            String CateName = rs.getString("CateName");
            cate = new Categories(CateId, CateName);
        }
        
        disconnect();
        return cate;
    }
    
    public static Categories getCate(String _cateName) throws Exception{
        connect();
        
        Categories cate = null;
        String sql = "select * from Categories where CateName = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, _cateName);
        ResultSet rs = ps.executeQuery();
        if(rs != null && rs.next()){
            int CateId = rs.getInt("CateID");
            String CateName = rs.getString("CateName");
            cate = new Categories(CateId, CateName);
        }
        
        disconnect();
        return cate;
    }
    
    public static ArrayList<Categories> listCate() throws Exception{
        connect();
        ArrayList<Categories> list = new ArrayList<>();
        
        String sql = "select * from Categories";
        ResultSet rs = con.createStatement().executeQuery(sql);
        if(rs != null){
            while(rs.next()){
                int CateId = rs.getInt("CateID");
                String CateName = rs.getString("CateName");
                list.add(new Categories(CateId, CateName));
            }
        }
        
        disconnect();
        return list;
    }
    
    public static  Categories updateCate(Categories cate) throws Exception{
        connect();
        
        String sql = "update Categories set CateName = ? where CateID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cate.getCateName());
        ps.setInt(2, cate.getCateId());
        if(ps.executeUpdate() > 0) cate = getCate(cate.getCateId());
        
        
        disconnect();
        return cate;
    }
    
    public static Categories updateCate(int cateId, String newCateName) throws Exception{
        connect();
        
        Categories cate = getCate(cateId);
        if(cate == null) return cate;
        
        if(cate.getCateName() == null || !cate.getCateName().equals(newCateName)) cate.setCateName(newCateName);
        
        
        disconnect();
        return updateCate(cate);
    }

    public static  Categories insertCate(Categories cate) throws Exception{
        connect();
        
        if(cate.getCateName() == null || cate.getCateName().isEmpty()) return null;
        
        String sql = "insert into Categories(CateName) values(?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cate.getCateName());
        
        if(ps.executeUpdate() > 0) cate = getCate(cate.getCateName());
            
        disconnect();
        return cate;
    }
}
