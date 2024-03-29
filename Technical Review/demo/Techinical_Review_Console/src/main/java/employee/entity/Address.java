package employee.entity;

public class Address extends employee_backend.entity.Address {

    private Long employeeID;
    private String houseName;
    private String streetName;
    private String city;
    private String state;
    private Integer pincode;

    public Address(Long employeeID, String houseName, String streetName, String city, String state, Integer pincode) {
        super(employeeID,houseName,streetName,city,state,pincode);
        this.employeeID = employeeID;
        this.houseName = houseName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return
                "\n houseName='" + houseName + '\'' +
                "\n streetName='" + streetName + '\'' +
                "\n city='" + city + '\'' +
                "\n state='" + state + '\'' +
                "\n pincode=" + pincode ;
    }
}
