
package dto;

import java.io.Serializable;

/**
 *
 * @author RaeKyo
 */
public class Plant implements Serializable{
    public static final int ORCHID = 1;
    public static final int ROSE = 2;
    
    public static final int AVAILABLE = 1;
    public static final int UNAVAILABLE = 0;
    
    private int plantId; // key
    private String plantName;
    private int price;
    private String imgPath;
    private String description;
    private int status;
    private int cateId;

    public Plant(String plantName, int price, String imgPath, String description, int status, int cateId) {
        this.plantName = plantName;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.cateId = cateId;
    }

    
    public Plant(int plantId, String plantName, int price, String imgPath, String description, int status, int cateId) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.cateId = cateId;
    }

    public Plant(){}

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    @Override
    public String toString() {
        return "Plant{" + "plantId=" + plantId + ", plantName=" + plantName + ", price=" + price + ", imgPath=" + imgPath + ", description=" + description + ", status=" + status + ", cateId=" + cateId + '}';
    }
    
    
}
