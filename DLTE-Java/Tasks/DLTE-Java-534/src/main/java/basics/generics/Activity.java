package basics.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Activity <T>{
    List<T> myObjects = new ArrayList<>();
    public abstract String insertRecord(T objects);
    public void viewAll(){
        System.out.println(Arrays.toString(new List[]{myObjects}));
    }
    public abstract T readRecord(int index);
    public abstract String deleteRecord(int index);
    public abstract void updateRecord(int index,T object);
}
