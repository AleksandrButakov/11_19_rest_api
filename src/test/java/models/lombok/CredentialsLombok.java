package models.lombok;

public class CredentialsLombok {
    /*
    Request URI:	https://demoqa.com/Account/v1/GenerateToken
    Request method:	POST
    Body:
    {
        "userName": "alex",
        "password": "asdsad#frew_DFS2"
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
