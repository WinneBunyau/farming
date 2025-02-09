package models.bean;

public class Crop {
    private int cropId;
    private int farmerId;
    private String cropName;  // ✅ Add cropName
    private String cropType;
    private String plantingDate;
    private String harvestDate;
    private int yield;

    // ✅ Updated constructor with cropName
    public Crop(int cropId, int farmerId, String cropName, String cropType, String plantingDate, String harvestDate, int yield) {
        this.cropId = cropId;
        this.farmerId = farmerId;
        this.cropName = cropName;
        this.cropType = cropType;
        this.plantingDate = plantingDate;
        this.harvestDate = harvestDate;
        this.yield = yield;
    }

    public int getCropId() { return cropId; }
    public int getFarmerId() { return farmerId; }
    public String getCropName() { return cropName; } // ✅ Ensure this method exists
    public String getCropType() { return cropType; }
    public String getPlantingDate() { return plantingDate; }
    public String getHarvestDate() { return harvestDate; }
    public int getYield() { return yield; }
}
