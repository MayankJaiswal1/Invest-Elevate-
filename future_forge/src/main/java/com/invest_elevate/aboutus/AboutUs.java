package com.invest_elevate.aboutus;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AboutUs extends Application {

    @Override
    public void start(Stage primaryStage) {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
   
    
        // Load the image
        Image image = new Image("file:future_forge\\src\\main\\resources\\about.jpg"); // Replace with your image path

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Make the ImageView preserve the image ratio and fit within the viewport
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(1920); // You can set the initial fit width as desired
        imageView.setFitHeight(990); // You can set the initial fit height as desired
        
        StackPane stackPane = new StackPane(imageView);
        stackPane.setAlignment(Pos.CENTER);
        // Create a ScrollPane to make the image scrollable
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(stackPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create a StackPane to hold the ScrollPane
        StackPane root = new StackPane(scrollPane);

        // Create the Scene
        Scene scene = new Scene(root, 800, 600); // Set initial size

        // Set the stage to full screen
        //primaryStage.setFullScreen(true);

        // Set up the stage
        primaryStage.setTitle("Full Screen Image Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
