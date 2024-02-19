package basics.services;

public class InsuranceProvider {
    public static void main(String[] args) {
        //Intialization
        String max[] = {"health", "life"};
        String acko[] = {"health", "life", "car", "bike"};
        String star[] = {"health"};
        String lic[] = {"health", "life", "car"};

        //Logic
        for (String s : args) {
            System.out.println("Inurance Provider for "+s+" are");
            for(String i:max){
                if(i.equalsIgnoreCase(s)){
                    System.out.println("Max");
                    break;
                }
            }
            for(String i:acko){
                if(i.equalsIgnoreCase(s)){
                    System.out.println("Acko");
                    break;
                }
            }
            for(String i:star){
                if(i.equalsIgnoreCase(s)){
                    System.out.println("Star");
                    break;
                }
            }
            for(String i:lic){
                if(i.equalsIgnoreCase(s)){
                    System.out.println("LIC");
                    break;
                }
            }
        }
    }
}
