package basics.oops;

public class BondsAnalysis {
    public static void main(String[] args) {
        //Initialization
        Bonds bonds[]={
                new Bonds(50000.0,8.2,true,"Shreyas",2),
                new Bonds(15000.0,5.2,false,"Varun",1),
                new Bonds(250000.0,8.8,true,"Prashanth",2),
                new Bonds(55000.0,4.3,true,"Rakesh",2),
                new Bonds(35000.0,9.2,false,"Akash",1),
        };
        BondsAnalysis bondsAnalysis = new BondsAnalysis();
        bondsAnalysis.sort(bonds);
    }
    //Soting Based on High Interrest Rate
    public void sort(Bonds bonds[]){
        for(int i=0;i<bonds.length;i++){
            for(int j=i;j<bonds.length;j++){
                if(bonds[i].getInterestRate().compareTo(bonds[j].getInterestRate())<=0){
                    Bonds temp = bonds[i];
                    bonds[i] = bonds[j];
                    bonds[j] = temp;
                }
            }
        }
        System.out.println("Bonds Sorted Based On Interest Rate :");;
        for(Bonds each1:bonds){
            System.out.println(each1.toString());
        }
    }
}
