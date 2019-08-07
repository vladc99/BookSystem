package bookinventory;

import java.util.*;
import java.io.*;

public abstract class Search{

    public static ArrayList<Book> searches = new ArrayList<>();

    public static String searchData(String searchKey, String searchType){

        String search = "";
        int index = 0;
        boolean exists = false;

        if(searchType.equals("Title")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getTitle().contains(searchKey)){
                    index = i;
                    exists = true;
                }
            }
        }else if(searchType.equals("ISBN")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getISBN().contains(searchKey)){
                    index = i;
                    exists = true;
                }
            }
        }else if(searchType.equals("Last Name")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getLastName().contains(searchKey)){
                    index = i;
                    exists = true;
                }
            }
        }

        if(exists){
            searches.add(BookInventory.data.get(index));
            search += BookInventory.data.get(index) + "\n";
            return search;
        }else{
            return "No such book exists in our system.";
        }
    }

    public static ArrayList<Book> getSearches(){
        return searches;
    }
}
