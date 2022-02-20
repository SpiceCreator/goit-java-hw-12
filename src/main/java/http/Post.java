package http;

public class Post {
    private final int userID;
    private final int id;
    private final String title;
    private final String body;

    public Post(int userID, int id, String title, String body) {
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserID() {
        return userID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "{" +
                "userID: " + userID +
                ", id: " + id +
                ", title: \"" + title + '\"' +
                ", body: \"" + body + '\"' +
                '}';
    }
}
