import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}


class MyTests {

    @Run
    public void testAddition() {
        System.out.println("Running testAddition");
        System.out.println(2 + 3 == 5);
    }

    @Run
    public void testSubtraction() {
        System.out.println("Running testSubtraction");
        System.out.println(10 - 4 == 6);
    }

    public void normalMethod() {
        System.out.println("This method should not run");
    }

    @Run
    public void testMultiplication() {
        System.out.println("Running testMultiplication");
        System.out.println(3 * 4 == 12);
    }
}


public class MiniTestRunner {

    public static void main(String[] args) {

        MyTests tests = new MyTests();

        int count = 0;

        Method[] methods =
                tests.getClass().getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                try {

                    method.invoke(tests);
                    count++;

                } catch (IllegalAccessException | InvocationTargetException e) {

                    System.out.println(
                            "Error running " +
                            method.getName());
                }
            }
        }

        System.out.println("Total @Run methods executed: "
                + count);
    }
}