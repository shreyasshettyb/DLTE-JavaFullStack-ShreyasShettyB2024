package basics.java;

import java.util.Arrays;

// Scopes, Overloading
class Blocks {
    public static void main(String[] args) {
        System.out.println("CLI Number"+ Arrays.toString(args));
    }
}

class Facility{
    public static void main(String[] args) {
       System.out.println("ATM, CDM, Passbook entry");
    }
}

class Device{
    public static void main(String[] args) {
        System.out.println("Internet banking and Wallets");
    }
}
