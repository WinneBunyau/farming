package models.bean;

public class Farm {
    private int farmId;
    private String farmLocation;
    private double farmSize;
    private String waterSource;
    private String farmName;

    public Farm(int farmId, String farmLocation, double farmSize, String waterSource, String farmName) {
        this.farmId = farmId;
        this.farmLocation = farmLocation;
        this.farmSize = farmSize;
        this.waterSource = waterSource;
        this.farmName = farmName;
    }

    public int getFarmId() { return farmId; }
    public String getFarmLocation() { return farmLocation; }
    public double getFarmSize() { return farmSize; }
    public String getWaterSource() { return waterSource; }
    public String getFarmName() { return farmName; }
}