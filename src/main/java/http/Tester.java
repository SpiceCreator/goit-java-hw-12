package http;

import java.io.*;

public class Tester {

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://jsonplaceholder.typicode.com";

        User newPerson = new User(2, "David Copperfield", "Magician", "___@gmail.com", "Hoeger Mall",
                "Apt. 692", "South Elvis", "53919-4257", "29.4572", "-164.2990", "493-170-9623 x156",
                "david.biz", "Robel-Corkery", "Multi-tiered zero tolerance productivity", "transition cutting-edge web services");


        System.out.println("First task. POST ------------------------------------------------------");
        System.out.println(HttpUtils.postUser(url, newPerson));

        System.out.println("First task. PUT ------------------------------------------------------");
        System.out.println(HttpUtils.updateUser(url, newPerson));

        System.out.println("First task. DELETE ------------------------------------------------------");
        System.out.println(HttpUtils.deleteUser(url, newPerson));

        System.out.println("First task. GET ------------------------------------------------------");
        System.out.println(HttpUtils.getAllUsers(url));

        System.out.println("First task. GET + ID ------------------------------------------------------");
        System.out.println(HttpUtils.getUserById(url, 5));

        System.out.println("First task. GET + Username ------------------------------------------------------");
        System.out.println(HttpUtils.getUserByUserName(url, "Leopoldo_Corkery"));

        System.out.println("Second task. ------------------------------------------------------");
        System.out.println(HttpUtils.filingAllComments(url, newPerson));

        System.out.println("Third task. ------------------------------------------------------");
        HttpUtils.printUncompeledTask(url, newPerson);
    }

}
