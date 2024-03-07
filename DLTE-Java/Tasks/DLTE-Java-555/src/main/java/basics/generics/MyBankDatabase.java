package basics.generics;

import java.io.*;
import java.util.ArrayList;


public class MyBankDatabase implements Activity{


    @Override
    public void create(ArrayList<CreditCard> creditCardArrayList) {
        try{
            FileOutputStream fileOutputStream=new FileOutputStream("creditcard.txt");
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(creditCardArrayList);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ioException){}
    }

    public ArrayList<CreditCard> read(){
        ArrayList<CreditCard> creditCardArrayList = new ArrayList<>();
        try{
            FileInputStream fileInputStream=new FileInputStream("creditcard.txt");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

            creditCardArrayList = (ArrayList<CreditCard>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        }
        catch (IOException | ClassNotFoundException ioException){}
        return  creditCardArrayList;
    }
}
