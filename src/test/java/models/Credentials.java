package models;

public class Credentials {
    /*
    Request URI:	https://demoqa.com/Account/v1/GenerateToken
    Request method:	POST
    Body:
    {
        "userName": "alex",
        "password": "asdsad#frew_DFS2"
    }
    HTTP/1.1 200 OK
    {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImFsZXgiLCJwYXNzd29yZCI6ImFzZHNhZCNmcmV3X0RGUzIiLCJpYXQiOjE2NTA2NDc3NDN9.CXIMbh-UiYQXMWoiEZhy-3WhMdxGOxtlSuArOG6RLwY",
        "expires": "2022-04-29T17:15:43.209Z",
        "status": "Success",
        "result": "User authorized successfully."
    }
     */

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
