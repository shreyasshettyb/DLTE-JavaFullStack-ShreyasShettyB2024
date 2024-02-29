package technical.review;

public class TemporaryAddress{
    String AddressLine1 = "";
    String AddressLine2 = " ";
    String City = "";
    String State = "";
    Integer Pincode = 0;

    @Override
    public String toString() {
        return "\nAddressLine1= " + AddressLine1 +
                "\nAddressLine2= " + AddressLine2 +
                "\nCity= " + City +
                "\nState='" + State +
                "\nPincode=" + Pincode;
    }
}
