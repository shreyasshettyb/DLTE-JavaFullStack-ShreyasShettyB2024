package basics.generics;

import java.util.*;
import java.util.stream.Collectors;


public class TransactionAnalysis {
    static List<Transaction> transactions = new ArrayList<>();
    public static void main(String[] args) {
        transactions.add(new Transaction("2024/05/11",5153,"Varun","Loan"));
        transactions.add( new Transaction("2024/01/08",2351530,"Prashanth","Education"));
        transactions.add(new Transaction("2024/08/16",653210,"Elory","Loan"));
        transactions.add(new Transaction("2024/05/26",52624,"Rakesh","Education"));
        Scanner scanner=new Scanner(System.in);
        int option;
        while(true){

            System.out.println("---------Welcome to transaction Section---------");
            System.out.println("1.Filter Transaction By The Range of Dates");
            System.out.println("2.Find Transaction With Least Amount");
            System.out.println("3.Find Transaction With Maximum Amount");
            System.out.println("4.Sort the Transaction");
            System.out.println("5.Exit");
            System.out.println("Enter Your Choice");
            option=scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter the start date");
                    String startDate=scanner.nextLine();
                    System.out.println("Enter the end date");
                    String endDate=scanner.nextLine();
                    List<Transaction> filterTransaction=transactions.stream().filter(transaction->transaction.getDateOfTransaction().compareTo(startDate)>=0&&transaction.getDateOfTransaction().compareTo(endDate)<=0).collect(Collectors.toList());
                    System.out.println("Filtered Transaction:");
                    filterTransaction.forEach(System.out::println);
                    break;
                case 2: Transaction minTransaction=transactions.stream().min(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("Minimum amount "+minTransaction);break;
                case 3:Transaction maxTransaction=transactions.stream().max(Comparator.comparing(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("Maximum amount "+maxTransaction);break;
                case 4:
                    System.out.println("Enter the property to sort");
                    String property=scanner.nextLine();
                    System.out.println("Enter the order");
                    String sort=scanner.nextLine();
                    Comparator<Transaction> comparator=null;
                    switch (property){
                        case "date": case "Date":comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                            break;
                        case "Amount":case "amount" : comparator=Comparator.comparing(Transaction::getAmountInTransaction);
                            break;
                        case "to": case "To":comparator=Comparator.comparing(Transaction::getTransactionTo);
                            break;
                        case "Remarks": case "remarks":comparator=Comparator.comparing(Transaction::getRemarks);
                            break;
                        default:System.out.println("Invalid property");
                            break;
                    }
                    if(comparator!=null){
                        if(!sort.equalsIgnoreCase("asce")){
                            comparator=comparator.reversed();
                        }
                        List<Transaction> sortTransaction=transactions.stream().sorted(comparator).collect(Collectors.toList());
                        System.out.println("Sorted Transaction are");
                        sortTransaction.forEach(System.out::println);
                    }
                    break;
                case 5:
                default:
                    System.out.println("Invalid option");
            }

        }
    }
}