import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Data.Month;
import Data.Transaction;
import Files.FileHandler;
import Utilities.Color;

public class Main {
    private static Month[] months;
    private static boolean running = true;

    private static Scanner in = new Scanner(System.in);

    private static int selectedMonth = 4;

    public static void main(String[] args){
        /* LOAD DATA */
        months = FileHandler.load();

        /* If for some reason the latest month is null, reset and create a new month */
        if(months[months.length-1] == null){
            months[months.length-1] = new Month(LocalDate.now().getMonthValue(), LocalDate.now().getYear(), 0.0);
        }

        /* If today's month is different than the most recent month in the data, create a new month to match */
        if(months[months.length-1].getMonthValue() != LocalDate.now().getMonthValue()){
            for(int i = 0; i < months.length-1; i++){
                months[i] = months[i+1];
            }
            months[months.length-1] = new Month(LocalDate.now().getMonthValue(), LocalDate.now().getYear(), months[months.length-2].getEndMoney());
        }

        /* Print all of the information */
        printMonths();


        while(running){
            runCommand(in.next());
            printSelectedMonth();
        }
    }

    public static void printSelectedMonth(){
        if(months[selectedMonth] == null){
            System.out.println(Color.BLACK + "\n\n<< empty >> " + Color.RESET);
        }else{
            months[selectedMonth].printMonth();
        }
    }

    public static void printMonths(){
        for(int i = 0; i < months.length; i++){
            if(months[i] == null){
                System.out.println(Color.BLACK + "<< empty >>\n" + Color.RESET);
            }else{
                months[i].printMonth();
            }
        }
    }

    public static void removeTransaction(){
        boolean cont = true;

        while(cont){
            System.out.println(Color.RED + "<< SELECT TRANSACTION TO REMOVE >>");
            months[selectedMonth].listTrans();
            System.out.print("\n>> ");
            String trans = in.next();

            if(trans.equalsIgnoreCase("quit") || trans.equalsIgnoreCase("done")){
                cont = false;
            }else{
                months[selectedMonth].removeTransaction(Integer.parseInt(trans));
            }
        }
    }

    public static void inputTransaction(){
        boolean cont = true;

        in.nextLine();
        while(cont){
            System.out.print("Input Amount >> ");
            double amt = Double.parseDouble(in.nextLine());
            System.out.print("Input Description >> ");
            String desc = in.nextLine();
            System.out.print("Input Date (yyyy-mm-dd) >> ");
            String inDate = in.nextLine();

            LocalDate date = (inDate.equalsIgnoreCase("today") ? LocalDate.now() : LocalDate.of(Integer.parseInt(inDate.substring(0,4)), Integer.parseInt(inDate.substring(5, 7)), Integer.parseInt(inDate.substring(8, 10))));

            months[selectedMonth].transaction(desc, amt, date);

            System.out.print("Input another ? >> ");
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")){
                cont = true;
            }else{
                cont = false;
            }
        }
    }

    public static void runCommand(String command){
        command = command.toLowerCase();

        switch(command){
            case("save"):
                FileHandler.save(months);
                break;
            case("quit"):
                running = false;
                break;
            case("transaction"):
                inputTransaction();
                break;
            case("reset"):
                months[selectedMonth].reset();
                break;
            case("remove"):
                removeTransaction();
                break;
            case("previous"):
                if(selectedMonth > 0){
                    selectedMonth--;
                }
                break;
            case("next"):
                if(selectedMonth < 4){
                    selectedMonth++;
                }
                break;
            default:
                System.out.println("Unrecognized Command");
                break;
        }
    }

    /*new Month(Month.Months.FEB, 2023, 488.90); */
}
