package Data;
import java.io.Serializable;
import java.time.LocalDate;

import Utilities.Color;

public class Transaction implements Serializable{
    private String description;
    private double amount;
    private LocalDate date;

    public Transaction(String description, double amount, LocalDate date){
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(String description, double amount){
        this(description, amount, LocalDate.now());
    }

    public String toString(){
        StringBuilder string = new StringBuilder();
        string.append(" " + String.format("%-20s", description));
        string.append(amount > 0.0 ? Color.GREEN : Color.RED);
        string.append(String.format("%10.2f", amount));
        string.append(Color.RESET);
        string.append(String.format("%15s", date.toString()));

        return string.toString();
    }
}
