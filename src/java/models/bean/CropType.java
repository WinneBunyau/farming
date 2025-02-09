package models.bean;

public class CropType {
    private int cropTypeId;
    private String cropTypeName;

    public CropType(int cropTypeId, String cropTypeName) {
        this.cropTypeId = cropTypeId;
        this.cropTypeName = cropTypeName;
    }

    public int getCropTypeId() { return cropTypeId; }
    public String getCropTypeName() { return cropTypeName; }
}
