package communication;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import utils.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CoursesDownloader {

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> stepikCourses = new ArrayList<>();
        final String requestBody = "https://stepik.org/api/courses?page=";
        int page = 1;
        boolean nextPageMarker = true;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            do {
                try (CloseableHttpResponse response = httpClient.execute(new HttpGet(requestBody + page))) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        StringBuilder builder = new StringBuilder();
                        try (BufferedReader instream = new BufferedReader(new InputStreamReader(entity.getContent()));) {
                            String line;
                            while ((line = instream.readLine()) != null)
                                builder.append(line).append("\n");
                            JsonParser parser = new JsonParser();
                            JsonObject jsonPage = parser.parse(builder.toString()).getAsJsonObject();
                            JsonObject meta = jsonPage.getAsJsonObject("meta");
                            nextPageMarker = Boolean.parseBoolean(meta.get("has_next").toString());
                            JsonArray curPageCourses = jsonPage.getAsJsonArray("courses");
                            for (JsonElement course : curPageCourses) {
                                JsonObject userObject = course.getAsJsonObject();
                                stepikCourses.add(new Course(userObject.get("title").toString(), userObject.get("learners_count").toString(), userObject.get("id").toString()));
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                page++;
            } while (nextPageMarker);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stepikCourses.sort(Course::compareTo);
        return stepikCourses;
    }
}

