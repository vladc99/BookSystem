package bookinventory;

import java.util.*;
import java.io.*;

public abstract class Search{

    public static ArrayList<Book> searches = new ArrayList<>();

    public static void searchData(String searchKey, String searchType){

        searches.clear();

        if(searchType.equals("Title")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getTitle().contains(searchKey)){
                    searches.add(BookInventory.data.get(i));
                }
            }
        }else if(searchType.equals("ISBN")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getISBN().equals(searchKey)){
                    searches.add(BookInventory.data.get(i));

                }
            }
        }else if(searchType.equals("Last Name")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getLastName().contains(searchKey)){
                    searches.add(BookInventory.data.get(i));
                }
            }
        }

    }

    public static ArrayList<Book> getSearches(){
        return searches;
    }
}