package basics.generics;

public class MyBankDatabase <T> extends Activity<T>{

    int objectsSize = myObjects.size();
    @Override
    public String insertRecord(T objects) {
        for(int index = 0;index < objectsSize;index++){
            for(T record:myObjects){
                if(myObjects.get(index).getClass().equals(objects.getClass()))
                myObjects.set(index,objects);
                return objects.toString() + " inserted";
            }
        }
        return objects.toString() + " insertion fail";
    }

    @Override
    public T readRecord(int index) {
        if(index >=0 && index < myObjects.size())
            return myObjects.get(index);
        return null;
    }

    @Override
    public String deleteRecord(int index) {
        if (index>=0 && index < objectsSize && myObjects.get(index)!=null){
            myObjects.set(index,null);
            return myObjects.get(index)+" Deleted";
        }
        return null;
    }

    @Override
    public void updateRecord(int index, T object) {
        if (index>=0 && index < objectsSize){
            myObjects.set(index,object);
            System.out.println(object+" has updated");
        }
        else
            System.out.println(object+" not updated");
    }
}
