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

        MyBankDatabase creditCardMyBankDatabase = new MyBankDatabase();
        ArrayList<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(new CreditCard(5134464646464L,456,8546,new Date()));
        creditCardList.add(new CreditCard(4684654312314L,531,8530,new Date()));
        creditCardList.add(new CreditCard(6846316216464L,325,4651,new Date()));
        creditCardList.add(new CreditCard(5158464611112L,741,5241,new Date()));
        creditCardMyBankDatabase.create(creditCardList);

        ArrayList<CreditCard> ouput = creditCardMyBankDatabase.read();
        for(CreditCard each:ouput){
            System.out.println(each.toString());
        }

    }
}
