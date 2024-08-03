package com.library.management.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;

public class CustomerUI extends Application {

    private TextField nameField;
    private TextField idField;
    private TextField searchField;
    private TableView<Customer> customerTable;
    private ObservableList<Customer> customerList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System - Customer");

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

        VBox topControls = new VBox(10);
        topControls.setPadding(new Insets(10));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        nameField = new TextField();
        nameField.setPromptText("Customer Name");
        idField = new TextField();
        idField.setPromptText("Customer ID");

        Button addButton = new Button("Add Customer");
        addButton.getStyleClass().add("librarian-button");
        addButton.setOnAction(e -> addCustomer(nameField.getText(), idField.getText()));

        Button removeButton = new Button("Remove Customer");
        removeButton.getStyleClass().add("librarian-button");
        removeButton.setOnAction(e -> removeCustomer(idField.getText()));

        Button updateButton = new Button("Update Customer");
        updateButton.getStyleClass().add("librarian-button");
        updateButton.setOnAction(e -> {
            Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                new UpdateCustomerUI(this, selectedCustomer).start(new Stage());
            } else {
                showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a customer to update");
            }
        });

        searchField = new TextField();
        searchField.setPromptText("Search by Name or ID");
        Button searchButton = new Button("Search Customer");
        searchButton.getStyleClass().add("librarian-button");
        searchButton.setOnAction(e -> searchCustomer(searchField.getText()));

        Button showAllButton = new Button("Show All Customers");
        showAllButton.getStyleClass().add("librarian-button");
        showAllButton.setOnAction(e -> showAllCustomers());

        gridPane.addRow(0, nameField, idField, addButton);
        gridPane.addRow(1, removeButton, searchField, searchButton, updateButton, showAllButton);

        topControls.getChildren().addAll(gridPane);
        borderPane.setTop(topControls);

        customerTable = new TableView<>();
        customerTable.setPlaceholder(new Label("No customers available"));
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        TableColumn<Customer, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        customerTable.getColumns().addAll(nameColumn, idColumn);
        borderPane.setCenter(customerTable);

        customerList = FXCollections.observableArrayList();
        loadCustomers();
        customerTable.setItems(customerList);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView, borderPane);

        Scene scene = new Scene(stackPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addCustomer(String name, String id) {
        if (!name.isEmpty() && !id.isEmpty()) {
            customerList.add(new Customer(name, id));
            saveCustomers();
            showAlert(Alert.AlertType.INFORMATION, "Customer Added", "Customer added successfully");
        } else {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter both name and ID");
        }
    }

    private void removeCustomer(String id) {
        if (!id.isEmpty()) {
            boolean found = customerList.removeIf(customer -> customer.getId().equals(id));
            if (found) {
                saveCustomers();
                showAlert(Alert.AlertType.INFORMATION, "Customer Removed", "Customer removed successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "Not Found", "Customer not found");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter a valid ID");
        }
    }

    private void searchCustomer(String searchTerm) {
        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            if (customer.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                customer.getId().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredList.add(customer);
            }
        }
        customerTable.setItems(filteredList);
    }

    private void showAllCustomers() {
        customerTable.setItems(customerList);
    }

    private void loadCustomers() {
        customerList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    customerList.add(new Customer(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Error reading from file: " + e.getMessage());
        }
    }

    public void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt"))) {
            for (Customer customer : customerList) {
                writer.write(customer.getName() + "," + customer.getId());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Error writing to file: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Customer {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;

        public Customer(String name, String id) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getId() {
            return id.get();
        }

        public SimpleStringProperty idProperty() {
            return id;
        }

        public void setId(String id) {
            this.id.set(id);
        }
    }

    public class UpdateCustomerUI extends Application {
        private CustomerUI parent;
        private Customer customer;

        public UpdateCustomerUI(CustomerUI parent, Customer customer) {
            this.parent = parent;
            this.customer = customer;
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Update Customer");

            TextField nameField = new TextField(customer.getName());
            TextField idField = new TextField(customer.getId());

            Button updateButton = new Button("Update");
            updateButton.setOnAction(e -> {
                customer.setName(nameField.getText());
                customer.setId(idField.getText());
                parent.saveCustomers();
                parent.showAllCustomers();
                primaryStage.close();
            });

            VBox vbox = new VBox(10, new Label("Customer Name:"), nameField, new Label("Customer ID:"), idField, updateButton);
            vbox.setPadding(new Insets(10));

            Scene scene = new Scene(vbox, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
