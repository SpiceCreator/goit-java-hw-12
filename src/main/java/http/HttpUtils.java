package http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtils {

    public static Document postUser(String url, Person person) throws IOException {
        return Jsoup.connect(url)
                .requestBody(new Gson().toJson(person))
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .post();
    }

//    public static Document updateUser(String url, Person person) throws IOException {
//        Jsoup.connect(url).
//    }


    public static List<Person> getAllUsers(String url) throws IOException {
        String json = getHTML(url);

        return convertJsonToCollection(json);
    }

    public static List<Person> getUserById(String url, int id) throws IOException {
        String json = getHTML(url);

        List<Person> list = convertJsonToCollection(json);

        return list.stream().
                filter(person -> person.getId() == id)
                .collect(Collectors.toList());
    }

    public static List<Person> getUserByUserName(String url, String userName) throws IOException {
        String json = getHTML(url);

        List<Person> list = convertJsonToCollection(json);

        return list.stream().
                filter(person -> person.getUsername().equals(userName))
                .collect(Collectors.toList());
    }

    private static String getHTML(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .get()
                .body()
                .html();
    }

    private static List<Person> convertJsonToCollection(String json) {
        Type listOfPersons = new TypeToken<ArrayList<Person>>(){}.getType();
        return new Gson().fromJson(json, listOfPersons);
    }
}
