/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookinventory;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Vlad Crihan
 */
public class Start extends Application {

    public static void main(String[] args) throws IOException {

        BookInventory inventory = new BookInventory();

        launch(args);
    }

    ImageView logo = new ImageView(new Image("https://i.imgur.com/OUzMOtx.jpg"));

    ComboBox<String> sortBy = new ComboBox();
    int sortByTemp = 0;
    TextField searchBar = new TextField();
    Alert a = new Alert(AlertType.NONE);

    int temp = 0; //for the listAll loop

    int pages = (BookInventory.data.size() / 25);
    int currentPage = 0;

    Stage window;

    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(5);
            gridPane.setVgap(5);

            gridPane.add(logo, 0, 0);

            Button btAdmin = new Button("Admin");
            gridPane.add(btAdmin, 0, 1);
            gridPane.setHalignment(btAdmin, HPos.CENTER);
            btAdmin.setOnAction(e -> adminLogIn());

            Button btGuest = new Button("Guest");
            gridPane.add(btGuest, 0, 2);
            gridPane.setHalignment(btGuest, HPos.CENTER);
            btGuest.setOnAction(e -> guestLogIn());

            window = primaryStage;
            Scene logIn = new Scene(gridPane, 600, 500);

            window.setTitle("Log In");
            window.setScene(logIn);
            window.show();
        } catch (Exception e) {
            badAlert("Can't load the first window");
        }
    }

    public void guestLogIn() {
        try{
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(logo, 1, 0);

        if (sortByTemp == 0) {
            sortBy.getItems().addAll("ISBN", "Last Name", "Title");
            sortBy.setValue("Sort By");
            gridPane.add(sortBy, 0, 1);
        }

        searchBar.setPromptText("Search");
        gridPane.add(searchBar, 1, 1);

        Button btSearch = new Button("Search");
        gridPane.add(btSearch, 2, 1);
        btSearch.setOnAction(e -> search(sortBy.getValue(), searchBar.getText()));

        Button btListAll = new Button("List All");
        gridPane.add(btListAll, 1, 2);
        gridPane.setHalignment(btListAll, HPos.CENTER);
        btListAll.setOnAction(e -> listAll());
        btListAll.setMaxSize(80, 10);

        Button btAdmin = new Button("Admin");
        gridPane.add(btAdmin, 1, 3);
        gridPane.setHalignment(btAdmin,HPos.CENTER);
        btAdmin.setOnAction(e->adminLogIn());
        btAdmin.setMaxSize(80, 10);

        Scene firstPageUser = new Scene(gridPane, 500, 500);
        window.setScene(firstPageUser);
    }catch(Exception e){
        badAlert("Book not in the system");
        }
    }

    public void search(String searchLocation, String searchKey) {
        Search.searchData(searchKey, searchLocation);
        try {
            GridPane gp = new GridPane();
            gp.setAlignment(Pos.CENTER);
            gp.setHgap(10);
            gp.setVgap(5);

            //The order of the array is: ISBN,Last Name,Title,Genre,Price,Number of Copies
            Label[] labelList = new Label[6];

            labelList[0] = new Label("ISBN");
            labelList[1] = new Label("Last Name");
            labelList[2] = new Label("Title");
            labelList[3] = new Label("Genre");
            labelList[4] = new Label("Price");
            labelList[5] = new Label("No. Copies");

            for (int i = 0; i < labelList.length; i++) {
                labelList[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                gp.add(labelList[i], i, 0);
            }

            for (int i = 0; i < Search.searches.size(); i++) {
                gp.add(new Label(Search.searches.get(i).getISBN()), 0, 1 + i);
                gp.add(new Label(Search.searches.get(i).getLastName()), 1, 1 + i);
                gp.add(new Label(Search.searches.get(i).getTitle()), 2, 1 + i);
                gp.add(new Label(String.valueOf(Search.searches.get(i).getGenre())), 3, 1 + i);
                gp.add(new Label(String.valueOf(Search.searches.get(i).getPrice())), 4, 1 + i);
                gp.add(new Label(String.valueOf(Search.searches.get(i).getNumCopies())), 5, 1 + i);
            }

            Button btBack = new Button("Back");
            btBack.setMaxSize(90, 10);
            gp.add(btBack, 2, 2 + Search.searches.size());
            gp.setHalignment(btBack, HPos.CENTER);
            btBack.setOnAction(e -> guestLogIn());

            Scene scene = new Scene(gp, 900, 300);
            window.setScene(scene);
        } catch (Exception e) {
            badAlert("No books found!");
        }
    }

    public void listAll() {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(5);

        //The order of the array is: ISBN,Last Name,Title,Genre,Price,Number of Copies
        Label[] list = new Label[6];

        list[0] = new Label("ISBN");
        list[1] = new Label("Last Name");
        list[2] = new Label("Title");
        list[3] = new Label("Genre");
        list[4] = new Label("Price");
        list[5] = new Label("No. Copies");

        for (int i = 0; i < list.length; i++) {
            list[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            gp.add(list[i], i, 0);
        }

        int a;

        if ((BookInventory.data.size() - (currentPage * 25)) < 25) {
            a = BookInventory.data.size() - (currentPage * 25);
            a += temp;
        } else {
            a = 26 + temp;
        }
        for (int i = 0 + temp; i < a; i++) {
            gp.add(new Label(BookInventory.data.get(i).getISBN()), 0, i + 1 - temp);
            gp.add(new Label(BookInventory.data.get(i).getLastName()), 1, i + 1 - temp);
            gp.add(new Label(BookInventory.data.get(i).getTitle()), 2, i + 1 - temp);
            gp.add(new Label(String.valueOf(BookInventory.data.get(i).getGenre())), 3, i + 1 - temp);
            gp.add(new Label(String.valueOf(BookInventory.data.get(i).getPrice())), 4, i + 1 - temp);
            gp.add(new Label(String.valueOf(BookInventory.data.get(i).getNumCopies())), 5, i + 1 - temp);
        }

        Button btBack = new Button("Back");
        btBack.setMaxSize(50, 10);
        gp.setHalignment(btBack, HPos.LEFT);
        gp.add(btBack, 2, 27);
        btBack.setOnAction(e -> listBackPage());

        Button btNext = new Button("Next");

        btNext.setMaxSize(50, 10);
        gp.setHalignment(btNext, HPos.RIGHT);
        gp.add(btNext, 2, 27);
        btNext.setOnAction(e -> listNextPage(gp));
        
        if(currentPage > 0){
            Button btHome = new Button("Home");
            gp.add(btHome, 2, 27);
            gp.setHalignment(btHome,HPos.CENTER);
            btHome.setOnAction(e->guestLogIn());
        }

        Scene listAll = new Scene(gp, 800, 650);
        window.setScene(listAll);
    }

    public void listNextPage(GridPane gp) {
        gp.getChildren().clear();
        if (currentPage >= pages) {
            a.setAlertType(AlertType.ERROR);
            a.setContentText("No more pages left");
            a.show();
            listAll();
        } else {
            System.out.println(pages + "Page");
            currentPage++;
            temp += 25;
            listAll();
        }
    }

    public void listBackPage() {
        if (temp == 0) {
            guestLogIn();
            currentPage = 0;
        } else {
            temp -= 25;
            currentPage--;
            listAll();
        }
    }

    public void badAlert(String message) {
        a.setAlertType(AlertType.ERROR);
        a.setContentText("message");
        a.show();
    }

    public void adminLogIn() {

        Admin admin = new Admin();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);

        //the order of the Array is as follows: ISBN,Title,First Name,Last Name,Genre,Price and number of Copies
        TextField[] list = new TextField[7];
        for (int i = 0; i < list.length; i++) {
            list[i] = new TextField();
            list[i].setMaxSize(70, 10);
            gp.add(list[i], i, 0);
        }

        list[0].setPromptText("ISBN");
        list[1].setPromptText("Title");
        list[2].setPromptText("First Name");
        list[3].setPromptText("Last Name");
        list[3].setMaxSize(300, 10);
        list[4].setPromptText("Genre");
        list[5].setPromptText("Price");
        list[6].setPromptText("No. Copies");

        Button btAddBook = new Button("Add Book");
        gp.add(btAddBook, 7, 0);
        btAddBook.setOnAction(e -> addBook(list[1], list[3], list[2], list[4],
                list[0], list[5], list[6], admin));

        try {
            Button btEdit = new Button("Edit");
            btEdit.setMaxSize(80, 10);
            gp.add(btEdit, 0, 1);

            TextField isbn1 = new TextField();
            isbn1.setMaxSize(70, 10);
            isbn1.setPromptText("ISBN");
            gp.add(isbn1, 1, 1);

            btEdit.setOnAction(e -> adminEdit(admin, isbn1));

            Button btDelete = new Button("Delete");
            btDelete.setMaxSize(80, 10);
            gp.add(btDelete, 2, 1);
            btDelete.setOnAction(e -> deleteBook(admin, isbn1));
        } catch (Exception e) {
            badAlert("ISBN not found");
        }

        TextField noBooks = new TextField();
        noBooks.setPromptText("No. Books");
        noBooks.setMaxSize(70, 10);
        gp.add(noBooks, 0, 2);

        TextField isbn3 = new TextField();
        isbn3.setPromptText("ISBN");
        isbn3.setMaxSize(70, 10);
        gp.add(isbn3, 1, 2);

        Button btOrder = new Button("Order");
        btOrder.setMaxSize(80, 10);
        gp.add(btOrder, 2, 2);
        btOrder.setOnAction(e -> orderBook(admin, isbn3, noBooks));

        Button btBack = new Button("Go to Search");
        btBack.setMaxSize(300, 10);
        gp.add(btBack, 3, 3);
        btBack.setOnAction(e -> guestLogIn());

        Scene adminFirstPage = new Scene(gp, 800, 300);
        window.setScene(adminFirstPage);
    }

    public void orderBook(Admin admin, TextField isbn, TextField noBooks) {
        admin.orderBook(Integer.parseInt(noBooks.getText()), isbn.getText());
        alertMessage("The book has been Ordered!");
    }

    public void alertMessage(String message) {
        a.setAlertType(AlertType.INFORMATION);
        a.setContentText(message);
        a.show();
    }

    public void addBook(TextField title, TextField lastName, TextField firstName,
            TextField genre, TextField isbn, TextField price, TextField noCopies,
            Admin admin) {
        admin.addBook(title.getText(), lastName.getText(), firstName.getText(),
                Genre.valueOf(genre.getText().toUpperCase()), isbn.getText(),
                Double.parseDouble(price.getText()),
                Double.parseDouble(noCopies.getText()));
        alertMessage(title.getText() + " has been added to the library");
    }

    public void deleteBook(Admin admin, TextField isbn) {
        admin.deleteBook(isbn.getText());
    }

    public void adminEdit(Admin admin, TextField isbn) {
        try {
            GridPane gridp = new GridPane();

            Book temp = new Book();
            Search.searchData(isbn.getText(), "ISBN");
            temp = Search.getSearches().get(0);

            //The order is ISBN,Title,First Name,Last Name, Genre,Price,No copies
            TextField list[] = new TextField[7];

            list[0].setPromptText("ISBN");
            list[0].setText(isbn.getText());

            list[1].setPromptText("Title");
            list[1].setText(temp.getTitle());

            list[2].setPromptText("First Name");
            list[2].setText(temp.getFirstName());

            list[3].setPromptText("Last Name");
            list[3].setText(temp.getLastName());

            list[4].setPromptText("Genre");
            list[4].setText(String.valueOf(temp.getGenre()));

            list[5].setPromptText("Price");
            list[5].setText(String.valueOf(temp.getPrice()));

            list[6].setPromptText("No. Copies");
            list[6].setText(String.valueOf(temp.getNumCopies()));

            for (int i = 0; i < list.length; i++) {
                gridp.add(list[i], i, 0);
                list[i].setMaxSize(80, 10);
            }

            Button btSave = new Button("Save");
            gridp.add(btSave, 2, 1);
            gridp.setHalignment(btSave, HPos.RIGHT);
            btSave.setMaxSize(80, 10);
            btSave.setOnAction(e -> adminEdit(admin, isbn, list[1], list[3], list[2], list[4], list[5],
                    list[6], list[0]));

            Button btBack = new Button("Back");
            gridp.add(btBack, 3, 1);
            gridp.setHalignment(btBack, HPos.LEFT);
            btBack.setMaxSize(80, 10);
            btBack.setOnAction(e -> adminLogIn());

            Scene scene = new Scene(gridp, 800, 100);
            window.setScene(scene);
        } catch (Exception e) {
            badAlert("ISBN not found");
        }
    }

    public void adminEdit(Admin admin, TextField isbn, TextField title, TextField lastN,
            TextField firstN, TextField genre, TextField price, TextField noCopies,
            TextField newISBN) {
        admin.editBook(String.valueOf(isbn.getText()),
                String.valueOf(title.getText()),
                String.valueOf(lastN.getText()),
                String.valueOf(firstN.getText()),
                String.valueOf(genre.getText().toUpperCase()),
                String.valueOf(newISBN.getText()),
                String.valueOf(price.getText()),
                String.valueOf(noCopies.getText()));
        alertMessage("Book has been edited!");
        adminLogIn();
    }
}
