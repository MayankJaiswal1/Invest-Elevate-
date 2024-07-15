package com.invest_elevate.firestore.controller;

import java.util.HashMap;
import java.util.Map;

import com.invest_elevate.firestore.firebaseConfig.DataService;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SignUpController {

    private LoginController loginController;

    public SignUpController(LoginController loginController) {
        this.loginController = loginController;
    }

    public Scene createSignupScene(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        userLabel.setStyle("-fx-font-weight:bold; -fx-font-size:16;");
        TextField userTextField = new TextField();
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight:bold; -fx-font-size:16;");
        PasswordField passField = new PasswordField();
        Button signupButton = new Button("Signup");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> loginController.showLoginScene());

        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);
        HBox buttonBox = new HBox(50, signupButton, backButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);

        signupButton.setOnAction(event -> handleSignup(primaryStage, userTextField.getText(), passField.getText()));

        ImageView profileImageView = new ImageView(
                new Image("file:future_forge\\src\\main\\resources\\icons\\signup.png"));
        profileImageView.setFitHeight(135);
        profileImageView.setFitWidth(135);
        profileImageView.setPreserveRatio(true);
        profileImageView.setStyle("-fx-background-color: transparent;");
        //VBox profileIcon = new VBox(20, profileImageView);
        //profileIcon.setPadding(new Insets(0, 0, 0, 20));

        VBox vbox = new VBox(20, profileImageView,fieldBox1, fieldBox2, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(400);
        vbox.setMaxHeight(400);
        vbox.setStyle(
                "-fx-background-color: rgba(240, 240, 240, 0.5);-fx-border-color: #B0B0B0; -fx-border-radius: 20; -fx-background-radius: 20;");

        //vbox.setStyle("-fx-background-image:url('https://img.freepik.com/free-photo/digital-world-banner-background-remixed-from-public-domain-by-nasa_53876-124622.jpg?semt=sph')");

        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:future_forge\\src\\main\\resources\\sippage\\dlog.jpeg",
                        Screen.getPrimary().getBounds().getWidth(),
                        Screen.getPrimary().getBounds().getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(backgroundImage));

                
        return new Scene(root, 700, 700);
    }

    private void handleSignup(Stage primaryStage, String username, String password) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
   
        DataService dataService;
        try {
            dataService = new DataService();
            Map<String, Object> data = new HashMap<>();
            data.put("password", password);
            data.put("username", username);
            dataService.addData("users", username, data);
            System.out.println("User registered successfully");

            loginController.showLoginScene();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
