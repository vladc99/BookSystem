package bookinventory;

/**
 * @author Brydon Parsons
 * @author Vlad Crihan
 * @author Adit Tandon
 * 
 * Class represents a single Book
*/
public class Book{

    private String title;
    private String firstName;
    private String lastName;
    private Genre genre;
    private String ISBN;
    private double price;
    private double numCopies;
    
    /**
     * @param title Title of the book
     * @param firstName Author's first name
     * @param lastName Author's last name
     * @param genre of the book (using one of the enum values)
     * @param ISBN ISBN associated with that book
     * @param price The cost of the book
     * @param numCopies The number of copies of that book available 
    */
    public Book(String title, String firstName, String lastName, Genre genre, 
            String ISBN, double price, double numCopies){
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
        this.ISBN = ISBN;
        this.price = price;
        this.numCopies = numCopies;
    }

    public Book(){
    }

    public String getISBN(){
        return ISBN;
    }

    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public double getNumCopies(){
        return numCopies;
    }

    public void setNumCopies(double numCopies){
        this.numCopies = numCopies;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Genre getGenre(){
        return genre;
    }

    public void setGenre(Genre genre){
        this.genre = genre;
    }

}
