package models.lombok;

public class GenerateTokenResponseLombok {
    /*
        HTTP/1.1 200 OK
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFsZXgiLCJwYXNzd29yZCI6ImFzZHNhZCNmcmV3X0RGUzIiLCJpYXQiOjE2NTA2NDc3NDN9.CXIMbh-UiYQXMWoiEZhy-3WhMdxGOxtlSuArOG6RLwY",
        "expires": "2022-04-29T17:15:43.209Z",
        "status": "Success",
        "result": "User authorized successfully."
    }
     */


    private String token;
    private String expires;
    private String status;
    private String result;


    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
