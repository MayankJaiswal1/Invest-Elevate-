package com.invest_elevate.info;

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

public class Register {

    public static Scene getRegisterFormScene(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        Label heading = new Label("WELCOME TO REGISTER PAGE");
        heading.setStyle("-fx-font-size: 44px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        // Adding shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.4, 0.4));
        heading.setEffect(dropShadow);

        Label mobileLabel = new Label("Mobile Number:");
        TextField mobileField = new TextField();
        mobileField.setPrefWidth(350);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(350);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(350);

        Button registerButton = new Button("Register");
        registerButton.setMaxWidth(180);
        registerButton.setOnAction(e -> {
            if (mobileField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
            } else if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email");
            } else if (!mobileField.getText().matches("\\d{10}")) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid 10-digit mobile number");
            } else {
                System.out.println("Registered with email: " + emailField.getText());
                stage.setScene(KYC.getKYCFormScene(stage)); 
            }
        });

        Button backButton = new Button("Back");
        backButton.setMaxWidth(180);
        backButton.setOnAction(e -> stage.setScene(Main.getMainScene(stage)));

        // Create an HBox for buttons and align them in the center
        HBox buttonBox = new HBox(10, registerButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create a GridPane for fields and align them in the center
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(mobileLabel, 0, 0);
        gridPane.add(mobileField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(buttonBox, 0, 3, 2, 1); 
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 3px;");

        HBox hbox = new HBox(gridPane);
        hbox.setMaxWidth(900);
        hbox.setMaxHeight(500);
        hbox.setAlignment(Pos.CENTER);

        // Set initial size and bindings for resizing
        hbox.setMinSize(500, 300);  
        hbox.prefWidthProperty().bind(stage.widthProperty().multiply(0.5));  
        hbox.prefHeightProperty().bind(stage.heightProperty().multiply(0.5));  

        VBox layout = new VBox(20, heading, hbox); 
        layout.setAlignment(Pos.CENTER);

        // Background Image
        Image backgroundImage = new Image("file:future_forge//src//main//resources//ImagesA//images.jpg");
        ImageView background = new ImageView(backgroundImage);
        background.setPreserveRatio(false);

        // Bind the background image size to the scene size
        StackPane root = new StackPane(background, layout);
        Scene scene = new Scene(root, 1000, 800);
        background.fitWidthProperty().bind(scene.widthProperty());
        background.fitHeightProperty().bind(scene.heightProperty());

        return scene;
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
