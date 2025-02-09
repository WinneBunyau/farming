package models.bean;

import java.util.Date;

public class CropProduce {
    private int cropProduceId;
    private int cropId;
    private double quantity;
    private Date cropProduceDate;
    private String cropStorageLocation;

    public CropProduce(int cropProduceId, int cropId, double quantity, Date cropProduceDate, String cropStorageLocation) {
        this.cropProduceId = cropProduceId;
        this.cropId = cropId;
        this.quantity = quantity;
        this.cropProduceDate = cropProduceDate;
        this.cropStorageLocation = cropStorageLocation;
    }

    public int getCropProduceId() {
        return cropProduceId;
    }

    public int getCropId() {
        return cropId;
    }

    public double getQuantity() {
        return quantity;
    }

    public Date getCropProduceDate() {
        return cropProduceDate;
    }

    public String getCropStorageLocation() {
        return cropStorageLocation;
    }

    public void setCropProduceId(int cropProduceId) {
        this.cropProduceId = cropProduceId;
    }

    public void setCropId(int cropId) {
        this.cropId = cropId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setCropProduceDate(Date cropProduceDate) {
        this.cropProduceDate = cropProduceDate;
    }

    public void setCropStorageLocation(String cropStorageLocation) {
        this.cropStorageLocation = cropStorageLocation;
    }
}
