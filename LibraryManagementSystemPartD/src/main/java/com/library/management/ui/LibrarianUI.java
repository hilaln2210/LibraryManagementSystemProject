package com.library.management.ui;

import com.library.management.Book;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;

public class LibrarianUI extends Application {

    private TableView<Book> tableView;
    private ObservableList<Book> bookList;
    private TextField searchField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System - Librarian");

        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream(new File("src/main/resources/image.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(800);
        backgroundView.setFitHeight(600);

        BorderPane borderPane = new BorderPane();

        tableView = new TableView<>();
        tableView.setPlaceholder(new Label("No books available"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        TableColumn<Book, Integer> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getYear()).asObject());
        tableView.getColumns().addAll(titleColumn, authorColumn, yearColumn);
        borderPane.setCenter(tableView);

        loadBooks();

        HBox topControls = new HBox(10);
        topControls.setPadding(new Insets(10));
        topControls.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add Book");
        addButton.getStyleClass().add("librarian-button");
        addButton.setOnAction(e -> new AddBookUI(this).start(new Stage()));
        Button removeButton = new Button("Remove Book");
        removeButton.getStyleClass().add("librarian-button");
        removeButton.setOnAction(e -> removeBook());
        Button updateButton = new Button("Update Book");
        updateButton.getStyleClass().add("librarian-button");
        updateButton.setOnAction(e -> updateBook());
        Button showAllButton = new Button("Show All Books");
        showAllButton.getStyleClass().add("librarian-button");
        showAllButton.setOnAction(e -> tableView.setItems(bookList));

        searchField = new TextField();
        searchField.setPromptText("Search by title, author, or year");
        Button searchButton = new Button("Search");
        searchButton.getStyleClass().add("librarian-button");
        searchButton.setOnAction(e -> searchBook());

        topControls.getChildren().addAll(addButton, removeButton, updateButton, showAllButton, searchField, searchButton);
        borderPane.setTop(topControls);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView, borderPane);

        Scene scene = new Scene(stackPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void loadBooks() {
        bookList = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    bookList.add(new Book(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Error reading from file: " + e.getMessage());
        }
        tableView.setItems(bookList);
    }

    private void removeBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookList.remove(selectedBook);
            saveBooks();
        } else {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a book to remove");
        }
    }

    private void updateBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            new UpdateBookUI(this, selectedBook).start(new Stage());
        } else {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a book to update");
        }
    }

    private void searchBook() {
        String searchTerm = searchField.getText().toLowerCase();
        ObservableList<Book> filteredList = FXCollections.observableArrayList();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                book.getAuthor().toLowerCase().contains(searchTerm) ||
                String.valueOf(book.getYear()).contains(searchTerm)) {
                filteredList.add(book);
            }
        }
        tableView.setItems(filteredList);
    }

    public void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))) {
            for (Book book : bookList) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Error writing to file: " + e.getMessage());
        }
    }

    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public ObservableList<Book> getBookList() {
        return bookList;
    }
}
