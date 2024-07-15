package com.invest_elevate.firestore.initialize;

import com.invest_elevate.firestore.controller.LoginController;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class initializeApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
   
        LoginController loginController = new LoginController(primaryStage);
         primaryStage.setScene(loginController.getLoginScene());
         primaryStage.setTitle("Login");
         primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
