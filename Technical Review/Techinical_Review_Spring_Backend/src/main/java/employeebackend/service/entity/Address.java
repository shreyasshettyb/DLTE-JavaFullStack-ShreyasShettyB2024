package employeebackend.service.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Address implements Serializable {
    @NotNull(message = "${validation.employee.employeeIDNotNull}")
    private Long employeeID;

    @NotBlank(message = "${validation.address.houseNameNotBlank}")
    private String houseName;

    @NotBlank(message = "${validation.address.streetNameNotBlank}")
    private String streetName;

    @NotBlank(message = "${validation.address.cityNotBlank}")
    private String city;

    @NotBlank(message = "${validation.address.stateNotBlank}")
    private String state;

    @NotNull(message = "${validation.address.pincodeNotNull}")
    @Size(min = 6, max = 6, message = "${validation.address.pincodeSize}")
    private Integer pincode;

    @NotBlank(message = "${validation.address.typeNotBlank}")
    private String type;

    public Address() {
    }

    public Address(@NotNull(message = "${validation.employee.employeeIDNotNull}") Long employeeID, @NotBlank(message = "${validation.address.houseNameNotBlank}") String houseName, @NotBlank(message = "${validation.address.streetNameNotBlank}") String streetName, @NotBlank(message = "${validation.address.cityNotBlank}") String city, @NotBlank(message = "${validation.address.stateNotBlank}") String state, @NotNull(message = "${validation.address.pincodeNotNull}") @Size(min = 6, max = 6, message = "${validation.address.pincodeSize}") Integer pincode, @NotBlank(message = "${validation.address.typeNotBlank}") String type) {
        this.employeeID = employeeID;
        this.houseName = houseName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Address{" +
                "employeeID=" + employeeID +
                ", houseName='" + houseName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", type='" + type + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
