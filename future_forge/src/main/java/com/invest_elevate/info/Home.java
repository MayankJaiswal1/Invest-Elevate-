package com.invest_elevate.info;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Home {

    public static Scene getHomePageScene(Stage stage) {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        Label welcomeLabel = new Label("Welcome to the Home Page!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");
        
        VBox layout = new VBox(10, welcomeLabel);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout, 1000, 800);
        return scene;
    }
}
