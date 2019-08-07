package bookinventory;

import java.io.*;
import java.util.*;

public class User{

    private ArrayList<Book> cart = new ArrayList<Book>();
    private double totalCost;

    public void checkout(){
        for(int i = 0; i < cart.size(); i++){
            totalCost += cart.get(i).getPrice();
        }
    }

    public void addToCart(){
        //cart.add(searchBooks().get(selectedBook));
    }

    public void removeFromCart(){
        //card.remove(searchBooks().get(selectedBook));
    }

    public ArrayList<Book> searchBooks(){
        Search.searches.clear();
        // String searchKey = gui get field
        // String searchType = gui get field
        // Search.searchData(String searchKey, String searchType);
        return Search.getSearches();
    }
}
