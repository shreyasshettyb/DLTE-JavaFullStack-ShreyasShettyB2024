package employeebackend.service.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Employee {
    @NotBlank(message = "${validation.employee.firstNameNotBlank}")
    @Pattern(regexp = "^[A-Za-z]+$", message = "${validation.employee.firstNamePattern}")
    private String firstName;

    @Pattern(regexp = "^[A-Za-z]*$", message = "${validation.employee.middleNamePattern}")
    private String middleName;

    @NotBlank(message = "${validation.employee.lastNameNotBlank}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "${validation.employee.lastNamePattern}")
    private String lastName;

    @NotNull(message = "${validation.employee.phoneNotNull}")
    @Size(min = 10, max = 10, message = "${validation.employee.phoneSize}")
    private Long phone;

    @NotBlank(message = "${validation.employee.emailNotBlank}")
    @Pattern(regexp = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", message = "${validation.employee.emailPattern}")
    private String email;

    @NotNull(message = "${validation.employee.employeeIDNotNull}")
    private Long employeeID;

    @NotNull(message = "${validation.employee.permanentAddressNotNull}")
    @Valid
    private Address permanentAddress;

    @NotNull(message = "${validation.employee.temporaryAddressNotNull}")
    @Valid
    private Address temporaryAddress;

    public Employee(@NotBlank(message = "${validation.employee.firstNameNotBlank}") @Pattern(regexp = "^[A-Za-z]+$", message = "${validation.employee.firstNamePattern}") String firstName, @Pattern(regexp = "^[A-Za-z]*$", message = "${validation.employee.middleNamePattern}") String middleName, @NotBlank(message = "${validation.employee.lastNameNotBlank}") @Pattern(regexp = "^[A-Za-z]*$", message = "${validation.employee.lastNamePattern}") String lastName, @NotNull(message = "${validation.employee.phoneNotNull}") @Size(min = 10, max = 10, message = "${validation.employee.phoneSize}") Long phone, @NotBlank(message = "${validation.employee.emailNotBlank}") @Pattern(regexp = "^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", message = "${validation.employee.emailPattern}") String email, @NotNull(message = "${validation.employee.employeeIDNotNull}") Long employeeID, @NotNull(message = "${validation.employee.permanentAddressNotNull}") @Valid Address permanentAddress, @NotNull(message = "${validation.employee.temporaryAddressNotNull}") @Valid Address temporaryAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.employeeID = employeeID;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(Address temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", employeeID=" + employeeID +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                '}';
    }
}
