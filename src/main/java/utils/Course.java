package utils;

public class Course implements Comparable<Course>{
    private String title;
    private int followers;
    private int id;

    @Override
    public int compareTo(Course o) {
        return -1 * Integer.compare(this.followers, o.followers);
    }

    public Course(String title, String follower, String id) {
        this.title = title;
        this.followers = Integer.parseInt(follower);
        this.id = Integer.parseInt(id);
    }

    @Override
    public String toString() {
        return title + " " + followers;
    }

    public int getId() {
        return this.id;
    }
}