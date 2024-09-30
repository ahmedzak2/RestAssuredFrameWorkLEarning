package pojo.EcommerceAPI;

public class LoginResponse {
    String token;
    String userId;
    String message;

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
