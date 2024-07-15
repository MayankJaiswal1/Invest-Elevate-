package com.invest_elevate.sip;

import com.invest_elevate.FullscreenNavigation.HomePage;
import com.invest_elevate.firestore.dashboards.UserPageApp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class sip2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Setting the stage to full screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        // Root layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffffff;");

        // Title and image
        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        ImageView sipImageView = new ImageView(new Image("file:future_forge//src//main//resources//icons//bank.jpeg"));
        sipImageView.setFitHeight(100);
        sipImageView.setFitWidth(100);
        Label sipTitle = new Label("Bank Nifty");
        sipTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleBox.getChildren().addAll(sipImageView, sipTitle);

        // About Fund section
        VBox aboutFundBox = new VBox(10);
        aboutFundBox.setPadding(new Insets(10));
        aboutFundBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1; -fx-border-radius: 5;");
        Label aboutTitle = new Label("About Fund");
        aboutTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label name = new Label("Name:  Bank Nifty ");
        Label aum = new Label("AUM: 1575 CR");
        Label numStocks = new Label("No. of Stocks Included: 10");
        Label founded = new Label("Founded in: 2019");

        name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        aum.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        numStocks.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        founded.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        aboutFundBox.getChildren().addAll(aboutTitle, name, aum, numStocks, founded);

        // Key Metrics section
        VBox keyMetricsBox = new VBox(10);
        keyMetricsBox.setPadding(new Insets(10));
        keyMetricsBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1; -fx-border-radius: 5;");
        Label keyMetricsTitle = new Label("Key Metrics");
        keyMetricsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label expenseRatio = new Label("Expense Ratio: 0.23%");
        Label nav = new Label("NAV: RS 207");
        Label riskIndex = new Label("Risk Index: Medium");

        expenseRatio.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nav.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        riskIndex.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Risk Index Image
        ImageView riskImageView = new ImageView(new Image("file:future_forge//src//main//resources//medium.jpg"));
        riskImageView.setFitHeight(100);
        riskImageView.setFitWidth(100);
        VBox.setMargin(riskImageView, new Insets(0, 0, 0, 0));
        VBox.setVgrow(riskImageView, Priority.ALWAYS);

        keyMetricsBox.getChildren().addAll(keyMetricsTitle, expenseRatio, nav, riskIndex);
        keyMetricsBox.getChildren().add(riskImageView);
        VBox.setMargin(riskImageView, new Insets(0, 0, 0, 0));
        VBox.setVgrow(riskImageView, Priority.ALWAYS);
        //VBox.setAlignment(Pos.BOTTOM_CENTER);

        // Detailed Analysis section
        VBox detailedAnalysisBox = new VBox(10);
        detailedAnalysisBox.setPadding(new Insets(10));
        detailedAnalysisBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1; -fx-border-radius: 5;");
        Label detailedAnalysisTitle = new Label("Detailed Analysis");
        detailedAnalysisTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label returnConsistency = new Label("Return Consistency: This fund is one of the best in generating consistent return in its category.");
        Label returnOutperformance = new Label("Return Outperformance: This fund has generated the highest return amongst Large & MidCap funds in the last 7 years.");
        Label chance = new Label("Chance: This fund has generated a 18.27% annual return in 50% of times for investors holding for at least 5 years.");
        Label volatilityProtection = new Label("Volatility Protection: This fund ranks medium in terms of protecting against volatility within its category.");
        Label returnRisk = new Label("Return/Risk: The fund is performing good than its category.");

        returnConsistency.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        returnOutperformance.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        chance.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        volatilityProtection.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        returnRisk.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        detailedAnalysisBox.getChildren().addAll(detailedAnalysisTitle, returnConsistency, returnOutperformance, chance, volatilityProtection, returnRisk);

        // Top layout with About Fund, Key Metrics, and Detailed Analysis
        HBox topLayout = new HBox(20);
        topLayout.setPadding(new Insets(10));
        topLayout.setPrefHeight(400);
        HBox.setHgrow(aboutFundBox, Priority.ALWAYS);
        HBox.setHgrow(keyMetricsBox, Priority.ALWAYS);
        HBox.setHgrow(detailedAnalysisBox, Priority.ALWAYS);
        aboutFundBox.setMaxWidth(Double.MAX_VALUE);
        keyMetricsBox.setMaxWidth(Double.MAX_VALUE);
        detailedAnalysisBox.setMaxWidth(Double.MAX_VALUE);

        // Adjusted widths
        aboutFundBox.setPrefWidth(0.25 * primaryScreenBounds.getWidth());
        keyMetricsBox.setPrefWidth(0.25 * primaryScreenBounds.getWidth());
        detailedAnalysisBox.setPrefWidth(0.5 * primaryScreenBounds.getWidth());

        topLayout.getChildren().addAll(aboutFundBox, keyMetricsBox, detailedAnalysisBox);

        // Returns section
        VBox returnsBox = new VBox(10);
        returnsBox.setPadding(new Insets(10));
        returnsBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1; -fx-border-radius: 5;");
        Label returnsTitle = new Label("Returns");
        returnsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label oneYearReturn = new Label("1 year: 32%");
        Label threeYearReturn = new Label("3 years: 50%");
        Label fiveYearReturn = new Label("5 years: 79%");

        oneYearReturn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        threeYearReturn.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        fiveYearReturn.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Line chart for early returns
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel("Return (%)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Yearly Returns");
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Return Rate");
        series.getData().add(new XYChart.Data<>(1, 32));
        series.getData().add(new XYChart.Data<>(2, 39));
        series.getData().add(new XYChart.Data<>(3, 50));
        series.getData().add(new XYChart.Data<>(4, 64));
        series.getData().add(new XYChart.Data<>(5, 79));
        lineChart.getData().add(series);

        lineChart.setStyle("-fx-background-color: #f4f4f4;");

        returnsBox.getChildren().addAll(returnsTitle, oneYearReturn, threeYearReturn, fiveYearReturn, lineChart);

        // Stocks and Weightage section
        VBox stocksBox = new VBox(10);
        stocksBox.setPadding(new Insets(10));
        stocksBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1; -fx-border-radius: 5;");
        Label stocksTitle = new Label("Stocks and Weightage");
        stocksTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Pie Chart for Stocks and Weightage
        PieChart stocksPieChart = new PieChart();
        stocksPieChart.getData().addAll(
                new PieChart.Data("HDFC Bank (12.76%)", 12.76),
                new PieChart.Data("ICICI Bank (6.89%)", 6.89),
                new PieChart.Data("State Bank of India (5.70%)", 5.70),
                new PieChart.Data("Kotak Mahindra Bank (3.71%)", 3.71),
                new PieChart.Data("Axis Bank (3.43%)", 3.43),
                new PieChart.Data("IndusInd Bank (1.25%)", 1.25),
                new PieChart.Data("Bank of Baroda (1.20%)", 1.20),
                new PieChart.Data("Punjab National Bank (1.06%)", 1.06),
                new PieChart.Data("Union Bank of India (0.92%)", 0.92),
                new PieChart.Data("Canara Bank (0.83%)", 0.83)
        );

        stocksPieChart.setLabelsVisible(true);
        stocksPieChart.setLegendVisible(true);
        stocksPieChart.setStyle("-fx-background-color: #f4f4f4;");

        stocksBox.getChildren().addAll(stocksTitle, stocksPieChart);

        // Bottom layout with Returns and Stocks & Weightage
        HBox bottomLayout = new HBox(20);
        bottomLayout.setPadding(new Insets(10));
        HBox.setHgrow(returnsBox, Priority.ALWAYS);
        HBox.setHgrow(stocksBox, Priority.ALWAYS);
        bottomLayout.getChildren().addAll(returnsBox, stocksBox);

        // Designer Button
        Button designerButton = new Button("Invest");
        designerButton.getStyleClass().add("designer-button");
        designerButton.setOnAction(e -> {

            UserPageApp up = new UserPageApp();
            up.start(primaryStage);
            // KYC.showKYCForm(new Stage());
            // primaryStage.close();
        });
        

        // Back Button
        Button backButton = new Button("Back");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> {
            HomePage homePage = new HomePage();
            homePage.start(new Stage());
            primaryStage.close();
        });

        // Top Right Buttons
        HBox topRightButtons = new HBox(10);
        topRightButtons.setAlignment(Pos.CENTER_RIGHT);
        topRightButtons.getChildren().addAll(designerButton, backButton);

        // Title layout with buttons
        BorderPane titleLayout = new BorderPane();
        titleLayout.setLeft(titleBox);
        titleLayout.setRight(topRightButtons);

        // Adding all sections to root
        root.setTop(titleLayout);
        root.setCenter(topLayout);
        root.setBottom(bottomLayout);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("designer_button.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("SIP Dashboard");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}