package bookinventory;

import static bookinventory.BookInventory.data;
import java.io.*;
import java.util.*;

/**
 * @author Brydon Parsons
 * @author Vlad Crihan
 * @author Adit Tandon
 * 
 * Models a guest account that is able to search in the program
*/

public class User{
    
    /**
     * @param searchKey What the guest wants to search
     * @param location What the user wants to search by
     * @return ArrayList<Book> Returns an ArrayList of searches done by the
     * guest
     * 
     * This method allows the user to search what they want
    */
    public ArrayList<Book> searchBooks(String searchKey, String location){
        Search.searchData(searchKey, location);
        return Search.getSearches();
    }
    
    /**
     * This method is responsible for updating the books.csv file with updated
     * information. A File object is created using the existing file and a 
     * temporary file is also created. The updated information is added to the
     * temporary file and the original file's content is erased and then the 
     * file is deleted. The temporary file is then renamed to the name of the
     * original file to keep the program consistent.
    */
    protected void updateFile(){
        File dataFile = new File("books.csv");
        File dataFileTemp = new File("tempbooks.csv");
        try(//Re-Create the file without the line to be deleted
                //Write wanted data into temp file
                PrintWriter output = new PrintWriter(dataFileTemp);){
            for(int i = 0; i < data.size(); i++){
                output.print(data.get(i).getTitle() + ",");
                output.print(data.get(i).getLastName() + ",");
                output.print(data.get(i).getFirstName() + ",");
                output.print(data.get(i).getGenre() + ",");
                output.print(data.get(i).getISBN() + ",");
                output.print(data.get(i).getPrice() + ",");
                output.print(data.get(i).getNumCopies());
                output.println();
            }

            //overwrite original file with temp file
            PrintWriter output2 = new PrintWriter(dataFile);
            output.close();
            output2.close();
            dataFile.delete();
            dataFileTemp.renameTo(new File("books.csv"));

        }catch(FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());

        }
    }
}