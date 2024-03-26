package employee.entity;

public class Employee {
    private String firstName, middleName, lastName;
    private Long phone;
    private String email;
    private Long employeeID;
    private Address permanentAddress, temporaryAddress;

    public Employee() {
    }

    public Employee(Address permanentAddress, Address temporaryAddress) {
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }

    public Employee(String firstName, String middleName, String lastName, Long phone, String email, Long employeeID) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.employeeID = employeeID;
    }

    public Employee(String firstName, String middleName, String lastName, Long phone, String email, Long employeeID, Address permanentAddress, Address temporaryAddress) {
//        super(firstName, middleName, lastName, phone, email, employeeID, permanentAddress, temporaryAddress);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.employeeID = employeeID;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
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
        return
                "\nfirstName='" + firstName + '\'' +
                        "\nmiddleName='" + middleName + '\'' +
                        "\nlastName='" + lastName + '\'' +
                        "\nphone=" + phone +
                        "\nemail='" + email + '\'' +
                        "\nemployeeID=" + employeeID +
                        "\npermanentAddress: " + permanentAddress +
                        "\ntemporaryAddress: " + temporaryAddress;
    }
}
