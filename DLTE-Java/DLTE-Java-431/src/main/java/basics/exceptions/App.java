package basics.exceptions;

import java.util.Set;

/**
 * Hello world!
 *
 */
//interface Sayable{
//    void hello();
//}
public class App {
//    public static void saySomething(){
//        System.out.println("Hello, this is static method.");
//    }
//    public static void main(String[] args) {
//        // Referring static method
//        Sayable sayable = App::saySomething;
//        // Calling interface method
//        sayable.hello();
//    }

    public static void main(String[] args) {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        threadSet.forEach(System.out::println);
        System.out.println(Thread.activeCount());
    }



}
