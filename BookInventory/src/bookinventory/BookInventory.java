/*
 * @author Brydon Parsons
 */
package bookinventory;

import java.io.*;
import java.util.*;

/**
 *
 * @author Brydon Parsons
 * @author Vlad Crihan
 * @author Adit Tandon
 * 
 * Models an inventory that consists of all the books available
 */
public class BookInventory{
    
    /**
     * ArrayList that consists of Book objects and represents all the books in 
     * the system
    */
    public static List<Book> data = new ArrayList<>();
    
    /**
     * Reads data from the books.csv line by line. Each line represents one 
     * book. Data is added to a book object and then added to the ArrayList.
     * This happens until the end of the file is reached.
    */
    public BookInventory() throws IOException{

        //import text file into array list of Book objects
        try(BufferedReader br = new BufferedReader(new FileReader(
                "books.csv"))){
            String line;
            int counter = 0;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                data.add(new Book());
                data.get(counter).setTitle(values[0]);
                data.get(counter).setLastName(values[1]);
                data.get(counter).setFirstName(values[2].trim());
                data.get(counter).setGenre(Genre.valueOf(values[3]));
                data.get(counter).setISBN(values[4]);
                data.get(counter).setPrice(Double.parseDouble(values[5]));
                data.get(counter).setNumCopies(Double.parseDouble(values[6]));
                counter++;
            }
        }
    }

}
