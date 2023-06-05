package at.kaindorf.shared.sharedModel;

public class Token {
    private String access_token;

    public Token(String access_token) {
        this.access_token = access_token;
    }

    public Token() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
