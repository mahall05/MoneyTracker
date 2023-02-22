package Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import Data.Month;

public class FileHandler {
    public static void save(Month[] months){
        try (FileOutputStream fos = new FileOutputStream("Exports/savedata.ser"); ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(months);
            System.out.println("\n << Save successful >>\n");
        }catch (IOException ioe){
            System.out.println("Problem saving data");
            ioe.printStackTrace();
        }
    }

    public static Month[] load(){
        Month[] months = new Month[5];

        try (FileInputStream fis = new FileInputStream("Exports/savedata.ser"); ObjectInputStream ois = new ObjectInputStream(fis);) {
            months = (Month[]) ois.readObject();
        } catch (IOException ioe){
            System.out.println("Error reading savedata");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Error loading savedata");
            cnfe.printStackTrace();
        }
        return months;
    }
}
