package com.invest_elevate.info;

import com.invest_elevate.FullscreenNavigation.HomePage;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginPage {

    public static Scene getLoginFormScene(Stage stage) {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(350);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all required fields.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Successful");
                alert.setHeaderText(null);
                alert.setContentText("Logged in successfully with email: " + emailField.getText());
                alert.showAndWait();
                stage.setScene(HomePage.getHomePageScene(stage));
            }
        });

        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(e -> stage.setScene(Main.getMainScene(stage)));

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0, 2);
        gridPane.add(backButton, 1, 2);
        gridPane.setAlignment(Pos.CENTER);

        // Apply white background to the GridPane (fields) within the HBox
        gridPane.setStyle("-fx-background-color: white;");

        HBox fieldsBox = new HBox(gridPane);
        fieldsBox.setAlignment(Pos.CENTER);
        fieldsBox.setMaxWidth(500);
        fieldsBox.setMaxHeight(300);
        fieldsBox.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-background-color: white;");

        // Set initial size and bindings for resizing
        fieldsBox.prefWidthProperty().bind(stage.widthProperty().multiply(0.5));  
        fieldsBox.prefHeightProperty().bind(stage.heightProperty().multiply(0.5));  

        // Create the heading
        Label heading = new Label("WELCOME TO LOGIN PAGE");
        heading.setStyle("-fx-font-size: 44px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        // Adding shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.4, 0.4));
        heading.setEffect(dropShadow);

        VBox layout = new VBox(20, heading, fieldsBox);
        layout.setAlignment(Pos.CENTER);

        // Background Image
        ImageView background = new ImageView(new Image("file:future_forge//src//main//resources//ImagesA//register.jpg"));
        background.setPreserveRatio(false);
        StackPane root = new StackPane(background, layout);
        Scene scene = new Scene(root, 1000, 800);
        background.fitWidthProperty().bind(scene.widthProperty());
        background.fitHeightProperty().bind(scene.heightProperty());

        return scene;
    }
}
