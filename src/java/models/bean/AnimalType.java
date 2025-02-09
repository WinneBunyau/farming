package models.bean;

public class AnimalType {
    private int animalTypeId;
    private String animalTypeName;

    public AnimalType(int animalTypeId, String animalTypeName) {
        this.animalTypeId = animalTypeId;
        this.animalTypeName = animalTypeName;
    }

    public int getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(int animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getAnimalTypeName() {
        return animalTypeName;
    }

    public void setAnimalTypeName(String animalTypeName) {
        this.animalTypeName = animalTypeName;
    }
}
