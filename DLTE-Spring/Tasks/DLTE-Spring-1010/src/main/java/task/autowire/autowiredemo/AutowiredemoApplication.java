package task.autowire.autowiredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AutowiredemoApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredemoApplication.class);
        applicationContext.scan("task.autowire.autowiredemo");
//        applicationContext.refresh();
        MyBank myBank=applicationContext.getBean(MyBank.class);
        System.out.println(myBank.callFind());

    }

}
