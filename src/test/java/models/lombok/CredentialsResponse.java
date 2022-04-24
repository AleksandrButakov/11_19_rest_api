package models.lombok;

import lombok.Data;

@Data
public class CredentialsResponse {

    private long id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    /*
        "id": 9222968140497185134,
        "petId": 0,
        "quantity": 0,
        "shipDate": "2022-04-24T12:46:04.571+0000",
        "status": "placed",
        "complete": true
     */
}
