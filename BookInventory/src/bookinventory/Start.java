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

    Stage window;
    ImageView logo = new ImageView(new Image("file:///D://School//Semester 2//Java 2//Assignment2//BookInventory//BookInventory//src//bookImage.jpg"));

    ComboBox<String> sortBy = new ComboBox();
    TextField searchBar = new TextField();

    Alert a = new Alert(AlertType.NONE);

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
            System.out.println("Can't load the first window");
        }
    }

    public void guestLogIn() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(logo, 1, 0);

        sortBy.getItems().addAll("ISBN", "Last Name", "Title");
        sortBy.setValue("Sort By");
        gridPane.add(sortBy, 0, 1);

        searchBar.setPromptText("Search");
        gridPane.add(searchBar, 1, 1);

        Button btSearch = new Button("Search");
        gridPane.add(btSearch, 2, 1);

        Button btListAll = new Button("List All");
        gridPane.add(btListAll, 1, 2);
        gridPane.setHalignment(btListAll, HPos.CENTER);
        btListAll.setOnAction(e -> listAll());

        Scene firstPageUser = new Scene(gridPane, 500, 500);
        window.setScene(firstPageUser);
    }

    public void listAll() {
        GridPane gp = new GridPane();

        gp.add(new Label("ISBN"), 0, 0);
        gp.add(new Label("Last Name"), 1, 0);
        gp.add(new Label("Title"), 2, 0);
        gp.add(new Label("Genre"), 3, 0);
        gp.add(new Label("Price"), 4, 0);
        gp.add(new Label("No Copies"), 5, 0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 25; j++) {
                gp.add(new Label(), i, i);
            }
        }
    }

    public void adminLogIn() {

        Admin admin = new Admin();

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);

        TextField isbn = new TextField();
        isbn.setPromptText("ISBN");
        isbn.setMaxSize(70, 10);
        gp.add(isbn, 0, 0);

        TextField title = new TextField();
        title.setMaxSize(70, 10);
        title.setPromptText("Title");
        gp.add(title, 1, 0);

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");
        firstName.setMaxSize(80, 10);
        gp.add(firstName, 2, 0);

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        lastName.setMaxSize(80, 10);
        gp.add(lastName, 3, 0);

        TextField genre = new TextField();
        genre.setPromptText("Genre");
        genre.setMaxSize(70, 10);
        gp.add(genre, 4, 0);

        TextField price = new TextField();
        price.setPromptText("Price");
        price.setMaxSize(70, 10);
        gp.add(price, 5, 0);

        TextField noCopies = new TextField();
        noCopies.setPromptText("No. Copies");
        noCopies.setMaxSize(70, 10);
        gp.add(noCopies, 6, 0);

        Button btAddBook = new Button("Add Book");
        gp.add(btAddBook, 7, 0);
        btAddBook.setOnAction(e -> addBook(title, lastName, firstName, genre,
                isbn, price, noCopies, admin));

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

        Button btBack = new Button("Back");
        btBack.setMaxSize(80, 200);
        gp.add(btBack, 3, 3);

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

            TextField isbn1 = new TextField();
            isbn1.setText(isbn.getText());
            isbn1.setPromptText("ISBN");
            gridp.add(isbn1, 0, 0);

            Book temp = new Book();
            Search.searchData(isbn.getText(), "ISBN");
            temp = Search.getSearches().get(0);

            TextField title = new TextField();
            title.setPromptText("Title");
            title.setText(temp.getTitle());
            gridp.add(title, 1, 0);

            TextField firstN = new TextField();
            firstN.setPromptText("First Name");
            firstN.setText(temp.getFirstName());
            gridp.add(firstN, 2, 0);

            TextField lastN = new TextField();
            lastN.setPromptText("Last Name");
            lastN.setText(temp.getLastName());
            gridp.add(lastN, 3, 0);

            TextField genre = new TextField();
            genre.setPromptText("Genre");
            genre.setText(String.valueOf(temp.getGenre()));
            genre.setMaxSize(80, 10);
            gridp.add(genre, 4, 0);

            TextField price = new TextField();
            price.setPromptText("Price");
            price.setMaxSize(80, 10);
            price.setText(String.valueOf(temp.getPrice()));
            gridp.add(price, 5, 0);

            TextField noCopies = new TextField();
            noCopies.setPromptText("No. Copies");
            noCopies.setMaxSize(80, 10);
            noCopies.setText(String.valueOf(temp.getNumCopies()));
            gridp.add(noCopies, 6, 0);

            Button btSave = new Button("Save");
            gridp.add(btSave, 2, 1);
            gridp.setHalignment(btSave, HPos.RIGHT);
            btSave.setMaxSize(80, 10);
            btSave.setOnAction(e -> adminEdit(admin, isbn, title, lastN, firstN, genre, price,
                    noCopies, isbn1));

            Button btBack = new Button("Back");
            gridp.add(btBack, 3, 1);
            gridp.setHalignment(btBack, HPos.LEFT);
            btBack.setMaxSize(80, 10);
            btBack.setOnAction(e -> adminLogIn());

            Scene scene = new Scene(gridp, 800, 100);
            window.setScene(scene);
        }catch(Exception e){
            a.setAlertType(AlertType.ERROR);
            a.setContentText("ISBN not found!");
            a.show();
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
