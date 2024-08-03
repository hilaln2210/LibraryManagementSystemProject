package com.library.management.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        Image backgroundImage = null;
        try {
            backgroundImage = new Image(new FileInputStream(new File("src/main/resources/image.jpeg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(600);
        backgroundView.setFitHeight(400);

        Label welcomeLabel = new Label("Welcome to the Library Management System");
        welcomeLabel.getStyleClass().add("welcome-label");

        Button customerButton = new Button("Customer");
        customerButton.setOnAction(e -> new CustomerUI().start(new Stage()));

        Button librarianButton = new Button("Librarian");
        librarianButton.setOnAction(e -> new LibrarianUI().start(new Stage()));

        VBox vbox = new VBox(10, customerButton, librarianButton);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcomeLabel);
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(welcomeLabel, Pos.TOP_CENTER);
        borderPane.setPrefSize(600, 400);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundView, borderPane);

        Scene scene = new Scene(stackPane, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
