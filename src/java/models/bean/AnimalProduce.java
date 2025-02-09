package models.bean;

import java.util.Date;

public class AnimalProduce {
    private int animalProduceId;
    private int animalId;
    private double quantity;
    private Date animalProduceDate;
    private String animalStorageLocation;

    public AnimalProduce(int animalProduceId, int animalId, double quantity, Date animalProduceDate, String animalStorageLocation) {
        this.animalProduceId = animalProduceId;
        this.animalId = animalId;
        this.quantity = quantity;
        this.animalProduceDate = animalProduceDate;
        this.animalStorageLocation = animalStorageLocation;
    }

    public int getAnimalProduceId() {
        return animalProduceId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public double getQuantity() {
        return quantity;
    }

    public Date getAnimalProduceDate() {
        return animalProduceDate;
    }

    public String getAnimalStorageLocation() {
        return animalStorageLocation;
    }

    public void setAnimalProduceId(int animalProduceId) {
        this.animalProduceId = animalProduceId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setAnimalProduceDate(Date animalProduceDate) {
        this.animalProduceDate = animalProduceDate;
    }

    public void setAnimalStorageLocation(String animalStorageLocation) {
        this.animalStorageLocation = animalStorageLocation;
    }
}
