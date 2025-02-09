package models.bean;

public class Animal {
    private int animalId;
    private int farmerId;
    private String animalName;
    private int animalTypeId;
    private String birthDate;
    private double weight;

    public Animal(int animalId, int farmerId, String animalName, int animalTypeId, String birthDate, double weight) {
        this.animalId = animalId;
        this.farmerId = farmerId;
        this.animalName = animalName;
        this.animalTypeId = animalTypeId;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public int getAnimalTypeId() {
        return animalTypeId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalTypeId(int animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
