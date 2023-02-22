package Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Utilities.Color;

public class Month implements Serializable{
    private double startMoney;
    private double endMoney;

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    private Months month;
    private int monthInt;
    private int year;

    public Month(Months m, int year, double startMoney){
        this.month = m;
        monthInt = month.ordinal();
        this.year = year;
        this.startMoney = startMoney;
        endMoney = startMoney;
    }

    public Month(int m, int year, double startMoney){
        Months[] months = Months.values();
        for(int i = 0; i < months.length; i++){
            if(i == m-1)
                month = months[i];
        }
        monthInt = m;
        this.year = year;
        this.startMoney = startMoney;
        endMoney = startMoney;
    }

    public int getMonthValue(){
        return monthInt;
    }

    public double getEndMoney(){
        return endMoney;
    }

    public void reset(){
        transactions = new ArrayList<Transaction>();
        endMoney = startMoney;
    }

    public void transaction(String description, double amount, LocalDate date){
        transactions.add(new Transaction(description, amount, date));

        endMoney += amount;
    }
    public void transaction(String description, double amount){
        transaction(description, amount, LocalDate.now());
    }

    public void printMonth(){
        System.out.println(month.toString() + " " +year);
        System.out.println(data());
    }

    private String data(){
        StringBuilder string = new StringBuilder();
        string.append(String.format("%n%10.2f%6s%s%10.2f%s%n", startMoney, "->", (endMoney > startMoney ? Color.GREEN : Color.RED), endMoney, Color.RESET));
        string.append("======================================================\n");
        for(int i = 0; i < transactions.size(); i++){
            string.append(transactions.get(i).toString()+"\n");
        }

        return string.toString();
    }

    public String toString(){
        return (month.toString() + " " + year + " " + startMoney + " " + endMoney);
    }

    public void listTrans(){
        for(int i = 0; i < transactions.size(); i++){
            System.out.println(i + ". " + transactions.get(i).toString());
        }
    }

    public void removeTransaction(int index){
        transactions.remove(index);
    }

    public enum Months{
        JAN,
        FEB,
        MAR,
        APR,
        MAY,
        JUN,
        JUL,
        AUG,
        SEP,
        OCT,
        NOV,
        DEC,
    }
}
