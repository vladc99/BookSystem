/*
 * @author Brydon Parsons
 */
package bookinventory;

import java.io.*;
import java.util.*;

/**
 *
 * @author Brydon
 */
public class BookInventory{

    public static void main(String[] args) throws FileNotFoundException, IOException{
        Search search = new Search();
        try{
            search.searchData("Wisdom", SearchLocation.ISBN);
        }catch(FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //import text file into array list
        List<List<String>> data = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("books.csv"))){
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                data.add(Arrays.asList(values));
            }
        }
    }
}
