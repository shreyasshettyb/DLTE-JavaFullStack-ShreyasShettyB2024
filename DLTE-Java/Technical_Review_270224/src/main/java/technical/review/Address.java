package technical.review;

public class Address {
    private String houseName;
    private String streetName;
    private String city;
    private String state;
    private Integer pincode;

    public Address(String houseName, String streetName, String city, String state, Integer pincode) {
        this.houseName = houseName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "House Name: " + houseName +
                "\nStreetName: " + streetName +
                "\nCity: " + city +
                "\nState: " + state +
                "\nPincode: " + pincode;
    }
}
