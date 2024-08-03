package com.library.management.ui;

import com.library.management.Book;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddBookUI extends Application {

    private LibrarianUI parent;
    private TextField titleField;
    private TextField authorField;
    private TextField yearField;

    public AddBookUI(LibrarianUI parent) {
        this.parent = parent;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Book");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Title:"), 0, 0);
        titleField = new TextField();
        gridPane.add(titleField, 1, 0);

        gridPane.add(new Label("Author:"), 0, 1);
        authorField = new TextField();
        gridPane.add(authorField, 1, 1);

        gridPane.add(new Label("Year:"), 0, 2);
        yearField = new TextField();
        gridPane.add(yearField, 1, 2);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addBook());
        gridPane.add(addButton, 1, 3);

        primaryStage.setScene(new Scene(gridPane, 300, 200));
        primaryStage.show();
    }

    private void addBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());

            int newId = parent.getBookList().stream()
                .mapToInt(Book::getId)
                .max()
                .orElse(0) + 1;

            Book newBook = new Book(newId, title, author, year);
            parent.getBookList().add(newBook);
            parent.saveBooks();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Year must be a number");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
