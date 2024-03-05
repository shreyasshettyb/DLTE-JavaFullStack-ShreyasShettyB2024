package technical.review;

public class Employee {
   private Name name;
   private Address permanentAddress, temporaryAddress;
   private Communication communication;

    public Employee(Name name, Address permanentAddress, Address temporaryAddress, Communication communication) {
        this.name = name;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
        this.communication = communication;
    }

    public Employee() {

    }

    public Name getName() {
        return name;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public Address getTemporaryAddress() {
        return temporaryAddress;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public void setTemporaryAddress(Address temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }

    @Override
    public String toString() {
        return name + "\nPermanent Address:\n" + permanentAddress+
                "\nTemporary Address:\n" + temporaryAddress+
                "\nCommunication:\n" + communication;
    }
}
