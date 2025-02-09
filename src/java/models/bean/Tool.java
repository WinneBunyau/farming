package models.bean;

public class Tool {
    private int toolID;
    private String toolName;
    private String toolType;
    private String availabilityStatus;

    public Tool(int toolID, String toolName, String toolType, String availabilityStatus) {
        this.toolID = toolID;
        this.toolName = toolName;
        this.toolType = toolType;
        this.availabilityStatus = availabilityStatus;
    }

    public int getToolID() {
        return toolID;
    }

    public void setToolID(int toolID) {
        this.toolID = toolID;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
