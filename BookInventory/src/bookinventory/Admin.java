/*
 * @author Brydon Parsons
 * @author
 * @author
 */
package bookinventory;

import static bookinventory.BookInventory.data;
import java.io.*;
import java.util.*;

public class Admin extends User{

    public Admin(){

    }

    int IDNumber;
    String password;

    File dataFile = new File("books.csv");
    File dataFileTemp = new File("tempbooks.csv");

    public void addBook(String title, String lastName, String firstName, Genre genre, String ISBN, double price, double numCopies){

        BookInventory.data.add(new Book(title, lastName, firstName, genre, ISBN, price, numCopies));
        updateFile();
    }

    public void deleteBook(String searchKey){
        //Delete entry from ArrayList
        Search.searchData(searchKey, "ISBN");
        for(int i = 0; i < BookInventory.data.size(); i++){
            if(BookInventory.data.get(i).getISBN().equals(searchKey)){
                Search.searches.remove(i);
                break;
            }
        }

        try(//Re-Create the file without the line to be deleted
                //Write wanted data into temp file
                PrintWriter output = new PrintWriter(dataFileTemp);){
            for(int i = 0; i < data.size(); i++){
                if(!searchKey.equals(data.get(i).getISBN())){
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

        }catch(FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());

        }
    }

    public void editBook(String searchKey, String item, String value){
        if(item.equals("Title")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getTitle().equals(searchKey)){
                    BookInventory.data.get(i).setTitle(value);
                    break;
                }
            }
        }else if(item.equals("lastName")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getLastName().equals(searchKey)){
                    BookInventory.data.get(i).setLastName(value);
                    break;
                }
            }
        }else if(item.equals("firstName")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getFirstName().equals(searchKey)){
                    BookInventory.data.get(i).setFirstName(value);
                    break;
                }
            }
        }else if(item.equals("Genre")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getGenre().equals(searchKey)){
                    BookInventory.data.get(i).setGenre(Genre.valueOf(value));
                    break;
                }
            }
        }else if(item.equals("Price")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getPrice() == Double.parseDouble(searchKey)){
                    BookInventory.data.get(i).setPrice(Double.parseDouble(value));
                    break;
                }
            }
        }else if(item.equals("ISBN")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getISBN().equals(searchKey)){
                    BookInventory.data.get(i).setISBN(value);
                    break;
                }
            }
        }else{
            System.out.println("Error: Edit of file wasnt executed properly, check admin file.");
        }
        updateFile();
    }

    public void orderBook(int n, String searchKey){
        Search.searchData(searchKey, "ISBN");
        for(int i = 0; i < BookInventory.data.size(); i++){
            if(searchKey.equals(BookInventory.data.get(i).getISBN())){
                BookInventory.data.get(i).setNumCopies(n);
            }
        }
        updateFile();
    }

    private void updateFile(){
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
