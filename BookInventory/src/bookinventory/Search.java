/*
 * @author Brydon Parsons
 */
package bookinventory;

import java.util.*;
import java.io.*;

public class Search{

    private String searchKey;
    private SearchLocation searchLocation;

    public void searchData(String searchKey, SearchLocation searchLocation){

        // System.out.println(BookInventory.data.get(0).get(0));
    }

}

//        searchKey = this.searchKey;
//        searchLocation = this.searchLocation;
//
//        Scanner input = new Scanner(dataFile);
//        boolean isFound = false;
//
//        while(input.hasNext()){// find way to search by specific location, unsure how we want to handle it
//            String temp = input.next();
//            if(temp.equals(searchKey)){
//                System.out.println(input.nextLine());
//
//                isFound = true;
//                System.out.println("Match found!");
//                break; // this should end the outer while loop search???
//            }
//        }
//        if(isFound == false){
//            System.out.println("Nothing was found!");
//        }
