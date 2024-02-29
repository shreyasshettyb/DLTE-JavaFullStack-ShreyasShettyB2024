package technical.review;

public class App {
    public static void main(String[] args) {
        Employee employee[] = new Employee[2];
        //Null pointer exception
        employee[0].setName(new Name("s","d","s"));
        //solution
        employee[0] = new Employee(new Name("s","d","f"),null,null,null);

    }
}
