package com.invest_elevate.firestore.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleTabViewWithBorderPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Admin Page");

        // Create a BorderPane
        BorderPane borderPane = new BorderPane();

        // Top part: Company logo and admin page name
        //ImageView logo = new ImageView(new Image("file : future_forge\\src\\main\\resources\\icons\\java.png"));
        //logo.setFitHeight(160);  // Increase the height
        //logo.setFitWidth(350);   // Increase the width

        Label pageTitle = new Label("Welcome Admin !!!");
        pageTitle.setStyle("-fx-font-size: 72; -fx-font-weight: bold; -fx-text-fill: #005A9E;");
        pageTitle.setPadding(new Insets(0, 0, 0, 30));

        HBox topBox = new HBox(40,   pageTitle);
        topBox.setPadding(new Insets(10, 0, 0, 10));
        topBox.setStyle("-fx-alignment: center-left;");

        // Center part: Tabs
        TabPane tabPane = new TabPane();
        //tabPane.setCursor(Cursor.HAND);
        tabPane.setPadding(new Insets(5));

        // Create Tabs
        Tab tab1 = new Tab("Users");
        // Tab tab2 = new Tab("Influencers");
        // Tab tab3 = new Tab("Registrations");

        tab1.setClosable(false);
        // tab2.setClosable(false);
        // tab3.setClosable(false);

        // Create content for each tab
        AppUsersTableView appUsersTableView = new AppUsersTableView();
        VBox tab1Content = appUsersTableView.createUserTable();
        tab1.setContent(tab1Content);

        // InfluencersTableView influencersTableView = new InfluencersTableView();
        // VBox tab2Content = influencersTableView.influencersTableVBox();
        // tab2.setContent(tab2Content);

        // InfluencersRegistration influencersRegistration = new InfluencersRegistration();
        // VBox tab3Content = influencersRegistration.influencersTableVBox();
        // tab3.setContent(tab3Content);

        // Add tabs to the TabPane
        tabPane.getTabs().addAll(tab1);

        // Apply styles to TabPane and Tabs
        tabPane.setStyle("-fx-tab-min-width: 120px; -fx-tab-max-width: 200px; -fx-font-size: 18px;");
       //tabPane.getStylesheets().add("styles/tab-styles.css");

        // Add top and center parts to the BorderPane
        borderPane.setTop(topBox);
        borderPane.setCenter(tabPane);

        // Create a Scene and set it to the Stage
        Scene scene = new Scene(borderPane, 1919, 990);
        stage.setScene(scene);
        stage.show();
    }
}
