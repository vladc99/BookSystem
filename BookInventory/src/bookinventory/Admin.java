/*
 * @author Brydon Parsons
 * @author
 * @author
 */
package bookinventory;

import java.io.*;
import java.util.*;

public class Admin extends User{

    int IDNumber;
    String password;

    File dataFile = new File("books.csv");

    public void addBook(String title, String lastName, String firstName, Genre genre, String ISBN, double price, double numCopies){
        try(
                PrintWriter output = new PrintWriter(dataFile);){
            //Code Execution
            output.println(title + "," + lastName + "," + firstName + "," + genre + "," + ISBN + "," + price + "," + numCopies);// Info for new book, correct format???
            BookInventory.data.add(new Book(title, lastName, firstName, genre, ISBN, price, numCopies));

        }catch(FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteBook(){// This should call the search class in someway...
    }

    public void editBook(){

    }

    // This should do a similar seach to the deleteBook,
    // then just modify the line to increase the numCopies
    public void orderBook(){
    }
}
