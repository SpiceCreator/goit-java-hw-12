package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HttpUtils {

    public static String postUser(String url, User person) throws IOException, InterruptedException {
        return sendHttpRequest(url.concat("/users"), person, "POST");
    }

    public static String updateUser(String url, User person) throws IOException, InterruptedException {
        url = url.concat("/users").concat("/").concat(Integer.toString(person.getId()));
        return sendHttpRequest(url, person, "PUT");
    }

    public static String deleteUser(String url, User person) throws IOException, InterruptedException {
        url = url.concat("/users").concat("/").concat(Integer.toString(person.getId()));
        return sendHttpRequest(url, person, "DELETE");
    }

    public static List<User> getAllUsers(String url) throws IOException, InterruptedException {
        return convertJsonToCollection(sendHttpRequest(url.concat("/users"), null, "GET"), User.class);
    }

    public static List<User> getUserById(String url, int id) throws IOException, InterruptedException {
        List<User> list = convertJsonToCollection(sendHttpRequest(url.concat("/users"), null, "GET"), User.class);
        return list.stream()
                .filter(person -> person.getId() == id)
                .collect(Collectors.toList());
    }

    public static List<User> getUserByUserName(String url, String userName) throws IOException, InterruptedException {
        List<User> list = convertJsonToCollection(sendHttpRequest(url.concat("/users"), null, "GET"), User.class);
        return list.stream()
                .filter(person -> person.getUsername().equals(userName))
                .collect(Collectors.toList());
    }

    public static File filingAllComments(String url, User person) throws IOException, InterruptedException {
        String postsUrl = url.concat("/users/").concat(Integer.toString(person.getId())).concat("/posts");
        String allPosts = sendHttpRequest(postsUrl, person, "GET");
        List<Post> posts = convertJsonToCollection(allPosts, Post.class);
        String commentsUrl = url.concat("/posts/").concat(Integer.toString(posts.size()-1)).concat("/comments");

        File file = new File("./user-"
                .concat(Integer.toString(person.getId()))
                .concat("-post-")
                .concat(Integer.toString(posts.size()-1))
                .concat("-comments.json"));

        OutputStream os = new FileOutputStream(file);

        os.write(sendHttpRequest(commentsUrl, person, "GET").getBytes());

        os.close();
        return file;
    }

    public static void printUncompeledTask(String url, User user) throws IOException, InterruptedException {
        url = url.concat("/users/").concat(Integer.toString(user.getId())).concat("/todos");
        String allTasks = sendHttpRequest(url, user, "GET");
        List<Task> tasks = convertJsonToCollection(allTasks, Task.class);
        tasks.stream()
                .filter(it -> !it.isCompleted())
                .forEach(System.out::println);
    }

    private static String sendHttpRequest(String url, User user, String method) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .header("Content-Type", "application/json")
                .method(method, HttpRequest.BodyPublishers.ofString(new Gson().toJson(user)))
                .build();
        System.out.println(request);
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        return response.body();
    }

    private static <T> List<T> convertJsonToCollection(String json, Class clazz) {
        List<T> resultList = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<T>>(){}.getType();
        List<Object> list = gson.fromJson(json, type);
        for (Object o : list) {
            resultList.add((T) gson.fromJson(gson.toJson(o), clazz));
        }
        return resultList;
    }
}
