import communication.PopularCoursesGetter;
import exceptions.NegativeNumberException;
import exceptions.ToBigNumberException;

public class Main {
    public static void main(String... args) {
        if (args.length == 0) {
            System.err.println("Empty input. Usage: lengthOfList");
        } else if (args.length > 1) {
            System.err.println("Too many arguments. Usage: lengthOfList");
        } else {
            Integer n = null;
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                System.err.println("Expected number, find: " + args[0]);
            }
            if (n != null) {
                if (n < 0) {
                    System.err.println("Given number is negative. Usage: lengthOfList");
                } else {
                    PopularCoursesGetter coursesGetter = new PopularCoursesGetter() ;
                    try {
                        coursesGetter.getListOfNMostPopularCourses(n).forEach(System.out::println);
                    } catch (ToBigNumberException | NegativeNumberException ex ){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
