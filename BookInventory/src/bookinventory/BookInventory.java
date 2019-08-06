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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Search search = new Search();
        try{
            search.searchData("Wisdom", SearchLocation.ISBN);
        }catch(FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
