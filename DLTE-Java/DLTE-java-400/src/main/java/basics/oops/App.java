package basics.oops;
import java.util.Scanner;

public class App implements MyBank
{
    Long loanNumber;
    Double loanAmount;
    Integer loanDate;
    String loanStatus,borrowerName;
    Long borrowerContact;
    static int numberLoans ;
    Loan loan[] = new Loan[25];
    static int option;

    public static void main( String[] args )
    {
        App app = new App();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Enter option\n1.Add Loans\n2.Check Available Loans\n3.Check Closed Loans");
            option=scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter number of Loans");
                    numberLoans = scanner.nextInt();
                        app.addLoans();
                        break;
                case 2:app.checkLoans();
                        break;
                case 3:app.checkClosedLoans();
                        break;
            }
        }
    }

    @Override
    public void addLoans() {

        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<numberLoans;i++) {
            System.out.println("Enter Loan Amount");
            loanAmount = scanner.nextDouble();
            System.out.println("Enter Loan Number");
            loanNumber = scanner.nextLong();
            System.out.println("Enter Loan Date");
            loanDate = scanner.nextInt();
            System.out.println("Enter Loan Status");
            loanStatus = scanner.next();
            System.out.println("Enter Borrower Name");
            borrowerName = scanner.next();
            System.out.println("Enter Borrower Contact");
            borrowerContact = scanner.nextLong();
            loan[i]=new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
        }
        System.out.println("Loans inserted Successfully");

    }

    @Override
    public void checkLoans() {
        try{
        for (Loan each: loan) {
            if(each.getLoanStatus().equalsIgnoreCase("open")){
                System.out.println(each.toString());
            }
        }}
        catch (Exception e){
            return;
        }
    }

    @Override
    public void checkClosedLoans() {
        try {
            for (Loan each : loan) {
                if (each.getLoanStatus().equalsIgnoreCase("open")) {
                    System.out.println(each.toString());
                }
            }
        } catch (Exception e) {
            return;
        }
    }
}
