package bookinventory;

import java.util.*;
import java.io.*;

public abstract class Search {

    public static ArrayList<Book> searches = new ArrayList<>();

    public static void searchData(String searchKey, String searchType) {

        searches.clear();

//        String search = "";
        int index = 0;
        boolean exists = false;

        if (searchType.equals("Title")) {
            for (int i = 0; i < BookInventory.data.size(); i++) {
                if (BookInventory.data.get(i).getTitle().contains(searchKey)) {
                    index = i;
                    exists = true;
                }
            }
        } else if (searchType.equals("ISBN")) {
            for (int i = 0; i < BookInventory.data.size(); i++) {
                if (BookInventory.data.get(i).getISBN().equals(searchKey)) {
                    index = i;
                    exists = true;
                }
            }
        } else if (searchType.equals("Last Name")) {
            for (int i = 0; i < BookInventory.data.size(); i++) {
                if (BookInventory.data.get(i).getLastName().contains(searchKey)) {
                    index = i;
                    exists = true;
                }
            }
        }

        if (exists) {
            searches.add(BookInventory.data.get(index));
        }
    }

    public static ArrayList<Book> getSearches() {
        return searches;
    }
}
