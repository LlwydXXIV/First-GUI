package FinalPT;

import java.io.*;
import java.util.ArrayList;

public class TextFileManager {

    ArrayList<String> values = new ArrayList<>();
    String filename, Name, Type, Product, ExpDate, Quantity, ProductID;

    void bufferedWrite(String filename, String Name, String Type, String Product, String ExpDate, String Quantity, String ProductID) {
        // Parameters :D
        this.filename = filename;
        this.Name = Name;
        this.Type = Type;
        this.Product = Product;
        this.ExpDate = ExpDate;
        this.Quantity = Quantity;
        this.ProductID = ProductID;

        System.out.println();
        System.out.println("SYSTEM: Writing...");
        try {
            //object
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            //write Strings to text file as lines of text
            bw.write(Name);
            bw.newLine();
            bw.write(Type);
            bw.newLine();
            bw.write(Product);
            bw.newLine();
            bw.write(ExpDate);
            bw.newLine();
            bw.write(Quantity);
            bw.newLine();
            bw.write(ProductID);

            bw.flush();
            bw.close();

            //exception handling
        } catch (IOException e) {
            System.err.println("SYSTEM: " + e.getMessage());
        } finally {
            System.out.println("SYSTEM: Text File Written.");
        }
    }

    ArrayList<String> bufferedReader(String filename) {
        try {
            this.filename = filename;
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                values.add(line);

            }
        } catch (IOException e) {
            System.err.println("ERROR: Missing file or non-existent.");
        }
        return values;
    }

}
