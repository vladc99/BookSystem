package bookinventory;

public class Book{

    private String ISBN;
    private String lastName;
    private String firstName;
    private String title;
    private double numCopies;
    private double price;
    private Genre genre;

    public Book(String ISBN, String lastName, String title, Genre genre){
        this.ISBN = ISBN;
        this.lastName = lastName;
        this.title = title;
        this.genre = genre;
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
