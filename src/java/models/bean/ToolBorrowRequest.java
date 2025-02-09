package models.bean;

import java.sql.Timestamp;

public class ToolBorrowRequest {
    private int toolBorrowRequestID;
    private int farmerID;
    private int toolID;
    private Timestamp requestDate;
    private String status;
    private Timestamp approveDate;
    private Integer adminID; // Nullable adminID (can be NULL if not yet approved)

    // ✅ Constructor for all fields
    public ToolBorrowRequest(int toolBorrowRequestID, int farmerID, int toolID, Timestamp requestDate, String status, Timestamp approveDate, Integer adminID) {
        this.toolBorrowRequestID = toolBorrowRequestID;
        this.farmerID = farmerID;
        this.toolID = toolID;
        this.requestDate = requestDate;
        this.status = status;
        this.approveDate = approveDate;
        this.adminID = adminID;
    }

    // ✅ Constructor without `approveDate` & `adminID` (for pending requests)
    public ToolBorrowRequest(int toolBorrowRequestID, int farmerID, int toolID, Timestamp requestDate, String status) {
        this.toolBorrowRequestID = toolBorrowRequestID;
        this.farmerID = farmerID;
        this.toolID = toolID;
        this.requestDate = requestDate;
        this.status = status;
        this.approveDate = null;  // Not yet approved
        this.adminID = null;
    }

    // ✅ Getters & Setters
    public int getToolBorrowRequestID() {
        return toolBorrowRequestID;
    }

    public void setToolBorrowRequestID(int toolBorrowRequestID) {
        this.toolBorrowRequestID = toolBorrowRequestID;
    }

    public int getFarmerID() {
        return farmerID;
    }

    public void setFarmerID(int farmerID) {
        this.farmerID = farmerID;
    }

    public int getToolID() {
        return toolID;
    }

    public void setToolID(int toolID) {
        this.toolID = toolID;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }
}
