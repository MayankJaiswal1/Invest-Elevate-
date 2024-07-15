package com.invest_elevate.info;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
   
        primaryStage.setTitle("InvestElevate");
        primaryStage.getIcons().add(new Image("file:src/main/resources/images/icon.png"));

        primaryStage.setScene(getMainScene(primaryStage));
        primaryStage.show();
    }

    public static Scene getMainScene(Stage stage) {
        // Logo and Heading
        ImageView logo = new ImageView(new Image("file:future_forge//src//main//resources//ImagesA//INVESTELEVATE.png"));
        logo.setFitWidth(800); 
        logo.setFitHeight(250); 
        logo.setPreserveRatio(true); 

        Label heading = new Label("WELCOME TO INVESTELEVATE");
        heading.setStyle("-fx-font-size: 44px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.4, 0.4));
        heading.setEffect(dropShadow);

        // Buttons
        Button loginButton = new Button("Login");
        loginButton.setPrefSize(300, 50);
        loginButton.setOnAction(e -> stage.setScene(LoginPage.getLoginFormScene(stage)));

        Button registerButton = new Button("Register");
        registerButton.setPrefSize(300, 50);
        registerButton.setOnAction(e -> stage.setScene(Register.getRegisterFormScene(stage)));

        // HBox for Buttons
        HBox buttonBox = new HBox(20, loginButton, registerButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(20, logo, heading, buttonBox);
        mainBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(mainBox);

        // Background Image
        Image backgroundImage = new Image("file:future_forge//src//main//resources//ImagesA//main.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        
        StackPane mainRoot = new StackPane(root);
        mainRoot.setBackground(new Background(background));
        
        Scene scene = new Scene(mainRoot, 1000, 800);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
