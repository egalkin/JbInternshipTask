package communication;

import exceptions.NegativeNumberException;
import exceptions.ToBigNumberException;
import utils.Course;
import java.util.ArrayList;
import java.util.List;

public class PopularCoursesGetter {
    private ArrayList<Course> courses;
    private int NUM_OF_COURSES;

    public PopularCoursesGetter() {
        courses = new ArrayList<>(CoursesDownloader.getAllCourses());
        this.NUM_OF_COURSES = courses.size();
    }

    public List<Course> getListOfNMostPopularCourses(int n) throws ToBigNumberException, NegativeNumberException{
        if (n > NUM_OF_COURSES) {
            throw new ToBigNumberException(Integer.toString(NUM_OF_COURSES));
        } else if (n < 0){
            throw new NegativeNumberException(Integer.toString(NUM_OF_COURSES));
        }
        return courses.subList(0, n);
    }

    public void update() {
        courses = new ArrayList<>(CoursesDownloader.getAllCourses());
    }

}
