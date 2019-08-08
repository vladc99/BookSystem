package bookinventory;

import static bookinventory.BookInventory.data;
import java.io.*;

/**
 * @author Brydon Parsons
 * @author Vlad Crihan
 * @author Adit Tandon
 * 
 * Models an admin user and provides the methods they can use. Inherits the 
 * updatefile() method from user
 */
public class Admin extends User {

    public Admin() {

    }
    
    File dataFile = new File("books.csv");
    File dataFileTemp = new File("tempbooks.csv");
    
    /**
     * Adds a book to the Data ArrayList using all the data fields described
     * in the Book class
     * @param title
     * @param lastName
     * @param firstName
     * @param genre
     * @param ISBN
     * @param price
     * @param numCopies
    */
    public void addBook(
            String title, String lastName, String firstName, Genre genre, 
            String ISBN, double price, double numCopies) {

        BookInventory.data.add(new Book(
                title, lastName, firstName, genre, ISBN, price, numCopies));
        updateFile();
    }
    
    /**
     * @param searchKey
     * Deletes a specific book who's ISBN matches the searchKey entered and 
     * then updates the ArrayList and the csv file accordingly.
    */
    public boolean deleteBook(String searchKey) {
        //Delete entry from ArrayList
        Search.searchData(searchKey, "ISBN");
        for (int i = 0; i < BookInventory.data.size(); i++) {
            if (BookInventory.data.get(i).getISBN().equals(searchKey)) {
                Search.searches.remove(0);
                return true;
            }
        }

        try (//Re-Create the file without the line to be deleted
                //Write wanted data into temp file
                PrintWriter output = new PrintWriter(dataFileTemp);) {
            for (int i = 0; i < data.size(); i++) {
                if (!searchKey.equals(data.get(i).getISBN())) {
                    output.print(data.get(i).getTitle() + ",");
                    output.print(data.get(i).getLastName() + ",");
                    output.print(data.get(i).getFirstName() + ",");
                    output.print(data.get(i).getGenre() + ",");
                    output.print(data.get(i).getISBN() + ",");
                    output.print(data.get(i).getPrice() + ",");
                    output.print(data.get(i).getNumCopies() + ",");
                    output.println();
                }
            }

            //overwrite original file with temp file
            PrintWriter output2 = new PrintWriter(dataFile);
            output.close();
            output2.close();
            dataFile.delete();
            dataFileTemp.renameTo(new File("books.csv"));

        } catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());

        }
        return false;
    }
    
    /**
     * @param searchKey
     * @param title
     * @param lastName
     * @param firstName
     * @param genre
     * @param ISBN
     * @param price
     * @param numCopies
     * 
     * Allows the admin to edit a book by entering all the appropriate fields
     * and then changing the data fields in that Book according to the setters
     * described in the Book.java class.
    */
    public void editBook(String searchKey, String title, String lastName, 
            String firstName, String genre, String ISBN, String price, 
            String numCopies) {
//        Search.searchData(searchKey, "ISBN");
        for (int i = 0; i < BookInventory.data.size(); i++) {
            if (searchKey.equals(BookInventory.data.get(i).getISBN())) {
                BookInventory.data.get(i).setTitle(title);
                BookInventory.data.get(i).setLastName(lastName);
                BookInventory.data.get(i).setFirstName(firstName);
                BookInventory.data.get(i).setGenre(Genre.valueOf(genre));
                BookInventory.data.get(i).setISBN(ISBN);
                BookInventory.data.get(i).setPrice(Double.parseDouble(price));
                BookInventory.data.get(i).setNumCopies(
                        Double.parseDouble(numCopies));
            }
        }
        updateFile();
    }
    
    /**
     * @param n int representing the number of orders
     * @param searchKey
     * 
     * Searches for the correct book using the searchKey entered and by ISBN.
     * Increases the number of books according to @param n and then updates the
     * file accordingly
    */
    public void orderBook(int n, String searchKey) {
        Search.searchData(searchKey, "ISBN");
        for (int i = 0; i < BookInventory.data.size(); i++) {
            if (searchKey.equals(BookInventory.data.get(i).getISBN())) {
                BookInventory.data.get(i).setNumCopies(
                        n + BookInventory.data.get(i).getNumCopies());
            }
        }
        updateFile();
    }
}
