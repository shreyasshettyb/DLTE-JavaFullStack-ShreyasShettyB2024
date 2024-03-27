package employeebackend.entity;

public class Address{
        private Long employeeID;
        private String houseName;
        private String streetName;
        private String city;
        private String state;
        private Integer pincode;
        private String type;

        public Address(Long employeeID, String houseName, String streetName, String city, String state, Integer pincode, String type) {
            this.employeeID = employeeID;
            this.houseName = houseName;
            this.streetName = streetName;
            this.city = city;
            this.state = state;
            this.pincode = pincode;
            this.type = type;
        }

    public Address() {
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
}
