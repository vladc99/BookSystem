/*
 * @author Brydon Parsons
 */
package bookinventory;

import java.util.*;
import java.io.*;

public class Search{

    private String searchKey;
    private SearchLocation searchLocation;

    File dataFile = new File("data.txt");

    public void searchData(String searchKey, SearchLocation searchLocation) throws FileNotFoundException{
        searchKey = this.searchKey;
        searchLocation = this.searchLocation;

        Scanner input = new Scanner(dataFile);
        boolean isFound = false;
        while(input.hasNext()){// find way to search by specific location, unsure how we want to handle it
            if(input.next().matches(searchKey)){
                // idk do something that returns the whole line of data
                isFound = true;
                System.out.println("Match found!");
                break; // this should end the outer while loop search???
            }

        }
        System.out.println("Nothing was found!");
    }

}
