package basics.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        MyBankDatabase <CreditCard> creditCardMyBankDatabase = new MyBankDatabase<>();
        MyBankDatabase<Transactions> transactionsMyBankDatabase = new MyBankDatabase<>();
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(new CreditCard(5134464646464L,456,8546,new Date()));
        creditCardList.add(new CreditCard(4684654312314L,531,8530,new Date()));
        creditCardList.add(new CreditCard(6846316216464L,325,4651,new Date()));
        creditCardList.add(new CreditCard(5158464611112L,741,5241,new Date()));
        List<Transactions> transactionsList = new ArrayList<>();
        transactionsList.add(new Transactions(new Date(),531531.4,"varun","loan"));
        transactionsList.add(new Transactions(new Date(),51231.3,"prashanth","books"));
        transactionsList.add(new Transactions(new Date(),6324.5,"rakesh","education"));
        transactionsList.add(new Transactions(new Date(),863690.5,"elroy","others"));

        creditCardMyBankDatabase.myObjects=creditCardList;
        transactionsMyBankDatabase.myObjects=transactionsList;

        System.out.println(creditCardMyBankDatabase.insertRecord(creditCardList.get(0)));
        System.out.println(creditCardMyBankDatabase.insertRecord(creditCardList.get(1)));
        System.out.println(creditCardMyBankDatabase.insertRecord(creditCardList.get(2)));
        System.out.println(creditCardMyBankDatabase.insertRecord(creditCardList.get(3)));

        System.out.println(transactionsMyBankDatabase.insertRecord(transactionsList.get(0)));
        System.out.println(transactionsMyBankDatabase.insertRecord(transactionsList.get(1)));
        System.out.println(transactionsMyBankDatabase.insertRecord(transactionsList.get(2)));
        System.out.println(transactionsMyBankDatabase.insertRecord(transactionsList.get(3)));

        System.out.println(transactionsMyBankDatabase.deleteRecord(2));

        System.out.println(creditCardMyBankDatabase.deleteRecord(3));


    }
}
