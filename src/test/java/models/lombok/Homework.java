package models.lombok;

import lombok.Data;

@Data
public class Homework {

    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

}