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

public class UpdateBookUI extends Application {

    private LibrarianUI parent;
    private Book bookToUpdate;
    private TextField titleField;
    private TextField authorField;
    private TextField yearField;

    public UpdateBookUI(LibrarianUI parent, Book bookToUpdate) {
        this.parent = parent;
        this.bookToUpdate = bookToUpdate;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Update Book");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Title:"), 0, 0);
        titleField = new TextField(bookToUpdate.getTitle());
        gridPane.add(titleField, 1, 0);

        gridPane.add(new Label("Author:"), 0, 1);
        authorField = new TextField(bookToUpdate.getAuthor());
        gridPane.add(authorField, 1, 1);

        gridPane.add(new Label("Year:"), 0, 2);
        yearField = new TextField(String.valueOf(bookToUpdate.getYear()));
        gridPane.add(yearField, 1, 2);

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> updateBookDetails());
        gridPane.add(updateButton, 1, 3);

        primaryStage.setScene(new Scene(gridPane, 300, 200));
        primaryStage.show();
    }

    private void updateBookDetails() {
        try {
            // Remove the old book
            parent.getBookList().remove(bookToUpdate);

            // Create a new book with updated details
            Book updatedBook = new Book(
                    bookToUpdate.getId(),
                    titleField.getText(),
                    authorField.getText(),
                    Integer.parseInt(yearField.getText())
            );

            // Add the updated book to the list
            parent.getBookList().add(updatedBook);
            parent.saveBooks(); // Save the updated list to the file

            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully");
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
