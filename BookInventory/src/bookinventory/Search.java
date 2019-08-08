package bookinventory;

import java.util.*;

/**
 * @author Brydon Parsons
 * @author Vlad Crihan
 * @author Adit Tandon
 * 
 * Search class that is used to search through the data ArrayList (defined in
 * BookInventory.java) 
*/
public abstract class Search{
    
    /**
     * ArrayList that contains Book objects that are the result of the 
     * searchData() method
    */
    public static ArrayList<Book> searches = new ArrayList<>();
    
    /**
     * @param searchKey What the guest wants to search
     * @param searchType What the user wants to search by 
     * 
     * ArrayList is first cleared to prevent any conflicts from previous 
     * searches. The conditional is used to check the searchType and then 
     * iterate through the data ArrayList to find the appropriate match to the
     * searchKey
    */
    public static boolean searchData(String searchKey, String searchType){

        searches.clear();

        if(searchType.equals("Title")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getTitle().contains(searchKey)){
                    searches.add(BookInventory.data.get(i));
                    return true;
                }
            }
        }else if(searchType.equals("ISBN")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getISBN().equals(searchKey)){
                    searches.add(BookInventory.data.get(i));
                    return true;
                }
            }
        }else if(searchType.equals("Last Name")){
            for(int i = 0; i < BookInventory.data.size(); i++){
                if(BookInventory.data.get(i).getLastName().contains(searchKey)){
                    searches.add(BookInventory.data.get(i));
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Book> getSearches(){
        return searches;
    }
}