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

    public static List<Book> data = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException{

        //import text file into array list of Book objects
        try(BufferedReader br = new BufferedReader(new FileReader("books.csv"))){
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
