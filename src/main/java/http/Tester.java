package http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        String url = "https://jsonplaceholder.typicode.com/users";

        Person newPerson = new Person(11, "David", "qwerty", "aaa@gmail.com", "First", "1", "Kyiv", "0000",
                "25.5", "37.1", "+380654525425", "google.com", "Google", "CatchMe", "null");


//        System.out.println(Jsoup.connect(url)
//                .ignoreContentType(true)
//                .get());

        System.out.println(HttpUtils.getAllUsers(url));
        System.out.println(HttpUtils.getUserById(url, 2));
        System.out.println(HttpUtils.getUserByUserName(url, "Bret"));

        System.out.println(HttpUtils.postUser(url, newPerson));
    }
}
