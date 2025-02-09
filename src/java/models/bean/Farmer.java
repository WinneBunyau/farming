package models.bean;

public class Farmer {
    private int farmerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String farmLocation;

    public Farmer(int farmerId, String name, String email, String password, String phone, String farmLocation) {
        this.farmerId = farmerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.farmLocation = farmLocation;
    }

    public int getFarmerId() { return farmerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getFarmLocation() { return farmLocation; }
}