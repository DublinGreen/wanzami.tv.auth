package tv.wazami.model;

public class AuthPayload {
    private final String token;
    private final User user;

    public AuthPayload(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
