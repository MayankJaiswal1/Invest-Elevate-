package com.invest_elevate.FullscreenNavigation;

import com.invest_elevate.aboutus.AboutUs;
import com.invest_elevate.firestore.controller.LoginController;
import com.invest_elevate.firestore.dashboards.UserPageApp;
import com.invest_elevate.sip.sip1;
import com.invest_elevate.sip.sip2;
import com.invest_elevate.sip.sip3;
import com.invest_elevate.sip.sip4;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomePage extends Application {

    private static Stage primaryStage;
    private Scene loginScene;
    private LoginController loginController;
    private static Scene homepageScene;

    public static void setScene(){
        primaryStage.setScene(homepageScene);
    }

    // LoginPage lp = new LoginPage();
    static String userName = LoginController.key;

    private static boolean isSidebarVisible = true;

    // public HomePage(DataService dataService){
    // this.dataService = dataService;
    // }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        // Top Layout
        HBox topLayout = createTopLayout();

        // Sidebar Menu
        VBox sidebarMenu = createSidebarMenu();

        Button hiddenButton = new Button("Hidden Button");
        hiddenButton.setVisible(false); // Make the button invisible

        // Set the action for the hidden button
        hiddenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer cardIndex = (Integer) hiddenButton.getUserData();
                sip1 bn = new sip1();
                sip3 n = new sip3();
                sip2 it = new sip2();
                sip4 p = new sip4();

                switch (cardIndex) {
                    case 1:
                        try {
                            bn.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            n.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            it.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            p.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });

        // Cards for events
        VBox card1 = createCard("Nifty", "file:future_forge\\src\\main\\resources\\images\\nifty.jpeg", "0.15%",
                "₹2575 Cr", "23%",
                hiddenButton, 1);
        VBox card2 = createCard("Pharma", "file:future_forge\\src\\main\\resources\\images\\pharma.jpeg", "0.39%",
                "₹3075 Cr", "45%",
                hiddenButton, 2);
        VBox card3 = createCard("BankNifty", "file:future_forge\\src\\main\\resources\\images\\banknifty.jpeg", "0.23%",
                "₹1575 Cr", "32%", hiddenButton, 3);
        VBox card4 = createCard("IT", "file:future_forge\\src\\main\\resources\\images\\it.jpeg", "0.27%", "₹2200 Cr",
                "32%",
                hiddenButton, 4);

        // Grid for the cards
        GridPane centerGrid = createCardGrid(card1, card2, card3, card4);

        // Root layout
        BorderPane root = new BorderPane();
        root.setTop(topLayout);
        root.setLeft(sidebarMenu);
        root.setCenter(centerGrid);
        root.setPadding(new Insets(10));

        // Sidebar Toggle Button Action
        javafx.scene.Node firstChild = topLayout.getChildren().get(0);
        if (firstChild instanceof HBox) {
            HBox hbox = (HBox) firstChild;
            if (!hbox.getChildren().isEmpty() && hbox.getChildren().get(0) instanceof Button) {
                Button menuButton = (Button) hbox.getChildren().get(0);
                menuButton.setOnAction(e -> toggleSidebar(root, sidebarMenu));
            } else {
                // Handle the case where the expected structure is not as assumed
                System.err.println("Expected Button not found in HBox");
            }
        } else {
            // Handle the case where the first child of topLayout is not an HBox
            System.err.println("Expected HBox not found in topLayout");
        }

        // Adding responsive background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:future_forge\\src\\main\\resources\\images\\house.png",
                        Screen.getPrimary().getBounds().getWidth(),
                        Screen.getPrimary().getBounds().getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        // Scene and Stage
        Scene scene = new Scene(root, 800, 600);

        this.homepageScene=scene;
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();
    }

    private static void toggleSidebar(BorderPane root, VBox sidebarMenu) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidebarMenu);
        if (isSidebarVisible) {
            transition.setToX(-sidebarMenu.getWidth());
            transition.setOnFinished(event -> root.setLeft(null));
        } else {
            root.setLeft(sidebarMenu);
            transition.setFromX(-sidebarMenu.getWidth());
            transition.setToX(0);
        }
        isSidebarVisible = !isSidebarVisible;
        transition.play();
    }

    private static HBox createTopLayout() {
        HBox topLayout = new HBox(10);
        topLayout.setPadding(new Insets(20));
        topLayout.setAlignment(Pos.CENTER_RIGHT);

        HBox topLeftSection = new HBox(10);
        topLeftSection.setAlignment(Pos.BOTTOM_LEFT);
        Button menuButton = new Button("☰ Menu");
        menuButton.setAlignment(Pos.BOTTOM_LEFT);
        menuButton.setStyle("-fx-font-size: 14px; -fx-background-color: transparent");

        Text companyLogoView = new Text();
        companyLogoView.setStyle("-fx-text-fill: white;-fx-font-weight:bold;-fx-font-size:40");

        DropShadow ds = new DropShadow();
        ds.setOffsetY(12.0f);
        ds.setColor(Color.color(0.9f, 0.9f, 0.9f));

        companyLogoView.setText("Invest \nElevate");
        companyLogoView.setEffect(ds);
        companyLogoView.setCache(true);
        companyLogoView.setX(10.0f);
        companyLogoView.setY(270.0f);
        companyLogoView.setFill(Color.BLACK);
        companyLogoView.setFont(Font.font(null, FontWeight.BOLD, 32));

        HBox.setMargin(companyLogoView, new Insets(0, 0, 0, 100));
        HBox logoHBox = new HBox(companyLogoView);

        ImageView profileImageView = new ImageView(
                new Image("file:future_forge\\src\\main\\resources\\images\\user.png"));
        profileImageView.setFitHeight(30);
        profileImageView.setFitWidth(30);
        profileImageView.setPreserveRatio(true);
        profileImageView.setStyle("-fx-background-color: transparent;");
        VBox userIcon = new VBox(20, profileImageView);
        userIcon.setPadding(new Insets(0, 0, 0, 20));

        VBox profileBox = new VBox(20, userIcon, menuButton);
        profileBox.setAlignment(Pos.TOP_LEFT);
        profileBox.setPadding(new Insets(5));

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search...");
        searchBox.setStyle("-fx-prompt-text-fill: black;-fx-background-color: rgba(240, 240, 240, 0.7); fx-border-radius :20px ;-fx-background-radius: 20;-fx-padding: 10px; -fx-font-size: 14px;");
        // searchBox.setPrefWidth(30);
        searchBox.setMinWidth(100);
        

        VBox searchBoxContainer = new VBox(searchBox);
        // searchBoxContainer.setAlignment(Pos.BOTTOM_LEFT);
        // searchBoxContainer.setPadding(new Insets(0, 0, 0, 0));

        Text message = new Text("HI " + userName);
        message.setStyle("-fx-text-fill: white;-fx-font-weight:bold;");
        message.setFont(Font.font("Ink Free", FontWeight.BOLD, 40));
        VBox messageBox = new VBox(message);
        messageBox.setAlignment(Pos.TOP_LEFT);

        // VBox userBox = new VBox(messageBox);
        // userBox.setAlignment(Pos.TOP_LEFT);
        // userBox.setSpacing(5);

        VBox rightAlignBox = new VBox(20);
        rightAlignBox.setAlignment(Pos.TOP_LEFT);
        rightAlignBox.getChildren().addAll(messageBox, searchBoxContainer);

        HBox userBox = new HBox(profileBox, rightAlignBox);
        userBox.setAlignment(Pos.TOP_LEFT);
        userBox.setSpacing(20);

        HBox topFinal = new HBox(1220, userBox, logoHBox);
        topFinal.setAlignment(Pos.CENTER);

        topLayout.setStyle(
                "-fx-background-color: rgba(255,255,255,0.3); -fx-border-radius: 5; -fx-background-radius: 5;");
        topLayout.getChildren().addAll(topFinal);
        // HBox.setHgrow(rightAlignBox, Priority.ALWAYS);

        return topLayout;
    }

    private static VBox createSidebarMenu() {
        VBox sidebarMenu = new VBox(25);
        sidebarMenu.setPadding(new Insets(50, 40, 25, 40));
        sidebarMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);-fx-border-radius: 5;-fx-background-radius: 5;");

        VBox topButtons = new VBox(15);
        Button sip1Button = new Button("Nifty Top 10");
        Button sip2Button = new Button("Bank Nifty");
        Button sip3Button = new Button("Pharma");
        Button sip4Button = new Button("IT Sector");
        Button bookingButton = new Button("KYC Page");

        sip1Button.setMaxWidth(Double.MAX_VALUE);
        sip1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                sip1 Top_10 = new sip1();
                Top_10.start(primaryStage); 
            }
        });

        sip2Button.setMaxWidth(Double.MAX_VALUE);
        sip2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                sip2 Bank = new sip2();
                Bank.start(primaryStage); 
            }
        });

        sip3Button.setMaxWidth(Double.MAX_VALUE);
        sip3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                sip3 pharma = new sip3();
                pharma.start(primaryStage); 
            }
        });

        sip4Button.setMaxWidth(Double.MAX_VALUE);
        sip4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                sip4 IT = new sip4();
                IT.start(primaryStage); 
            }
        });

        bookingButton.setMaxWidth(Double.MAX_VALUE);
        bookingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                UserPageApp kyc = new UserPageApp();
                kyc.start(primaryStage); 
            }
        });

        topButtons.getChildren().addAll(sip1Button, sip2Button, sip3Button, sip4Button, bookingButton);

        VBox bottomButtons = new VBox(15);
        Button aboutusButton = new Button("About Us");
        Button helpCenterButton = new Button("Help Center");
        Button settingsButton = new Button("Settings");
        Button logOutButton = new Button("Log out");
        aboutusButton.setMaxWidth(Double.MAX_VALUE);
        helpCenterButton.setMaxWidth(Double.MAX_VALUE);
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        logOutButton.setMaxWidth(Double.MAX_VALUE);

        aboutusButton.setMaxWidth(Double.MAX_VALUE);
        aboutusButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                AboutUs about = new AboutUs();
                about.start(primaryStage); 
            }
        });

        //logOutButton.setOnAction(event -> loginController.showLoginScene());
        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            //LoginController lc = new LoginController();
            @Override
            public void handle(ActionEvent event) {
                LoginController lc = new LoginController();
                //LoginController.getLoginScene();
                Scene sc =lc.initLoginScene();
                lc.setLoginScene(sc);
            }
            
        });
        bottomButtons.getChildren().addAll(aboutusButton, helpCenterButton, settingsButton, logOutButton);

        Pane spacer = new Pane();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        sidebarMenu.getChildren().addAll(topButtons, spacer, bottomButtons);

        String buttonStyle = "-fx-font-size: 14px; -fx-background-color: #333; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;";

        sip1Button.setOnMouseEntered(e -> sip1Button.setStyle(
                "-fx-background-color: Green; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        sip1Button.setOnMouseExited(e -> sip1Button.setStyle(buttonStyle));
        
        sip2Button.setOnMouseEntered(e -> sip2Button.setStyle(
            "-fx-background-color: Green; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        sip2Button.setOnMouseExited(e -> sip2Button.setStyle(buttonStyle));

        sip3Button.setOnMouseEntered(e -> sip3Button.setStyle(
            "-fx-background-color: Green; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        sip3Button.setOnMouseExited(e -> sip3Button.setStyle(buttonStyle));

        sip4Button.setOnMouseEntered(e -> sip4Button.setStyle(
            "-fx-background-color: Green; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        sip4Button.setOnMouseExited(e -> sip4Button.setStyle(buttonStyle));

        bookingButton.setOnMouseEntered(e -> bookingButton.setStyle(
            "-fx-background-color: Orange; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        bookingButton.setOnMouseExited(e -> bookingButton.setStyle(buttonStyle));

        sip1Button.setStyle(buttonStyle);
        sip2Button.setStyle(buttonStyle);
        sip3Button.setStyle(buttonStyle);
        sip4Button.setStyle(buttonStyle);
        bookingButton.setStyle(buttonStyle);
        aboutusButton.setStyle(buttonStyle);
        helpCenterButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);
        logOutButton.setStyle(buttonStyle);

        aboutusButton.setOnMouseEntered(e -> aboutusButton.setStyle(
            "-fx-background-color: Blue; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        aboutusButton.setOnMouseExited(e -> aboutusButton.setStyle(buttonStyle));

        helpCenterButton.setOnMouseEntered(e -> helpCenterButton.setStyle(
            "-fx-background-color: Blue; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        helpCenterButton.setOnMouseExited(e -> helpCenterButton.setStyle(buttonStyle));

        settingsButton.setOnMouseEntered(e -> settingsButton.setStyle(
            "-fx-background-color: Blue; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        settingsButton.setOnMouseExited(e -> settingsButton.setStyle(buttonStyle));

        logOutButton.setOnMouseEntered(e -> logOutButton.setStyle(
                "-fx-background-color: RED; -fx-font-size: 14px; -fx-text-fill: white; -fx-border-color: #555; -fx-border-radius: 5; -fx-background-radius: 5;"));
        logOutButton.setOnMouseExited(e -> logOutButton.setStyle(buttonStyle));

        return sidebarMenu;
    }

    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private static GridPane createCardGrid(VBox... cards) {
        GridPane centerGrid = new GridPane();
        centerGrid.setHgap(30);
        centerGrid.setVgap(30);
        centerGrid.setPadding(new Insets(10, 40, 25, 30));

        centerGrid.add(cards[0], 0, 0);
        centerGrid.add(cards[1], 1, 0);
        centerGrid.add(cards[2], 0, 1);
        centerGrid.add(cards[3], 1, 1);

        // Make the cards equally divided and responsive
        for (VBox card : cards) {
            GridPane.setHgrow(card, Priority.ALWAYS);
            GridPane.setVgrow(card, Priority.ALWAYS);
            card.prefWidthProperty().bind(centerGrid.widthProperty().divide(2).subtract(30));
            card.prefHeightProperty().bind(centerGrid.heightProperty().divide(1.6).subtract(30));
        }

        return centerGrid;
    }

    private static VBox createCard(String title, String imagePath, String expenseRatio, String aum,
            String returns,
            Button hiddenButton, int i) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.TOP_LEFT);
        card.setPadding(new Insets(15));
        card.setStyle(
                "-fx-background-color: rgba(240, 240, 240, 0.6); -fx-border-color: #B0B0B0; -fx-border-radius: 20; -fx-background-radius: 20;");

        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(card.widthProperty().divide(2));
        imageView.setFitHeight(150);
        imageView.setSmooth(true);

        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font("Arial", 35));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10, 0, 0, 0));

        // Create labels for Expense Ratio, AUM, and 3Y Returns
        HBox expenseRatioBox = createInfoBox("Expense Ratio", expenseRatio);
        HBox aumBox = createInfoBox("AUM", aum);
        HBox returnsBox = createInfoBox("1Y Returns", returns, Color.GREEN);

        VBox infoBox = new VBox(10, expenseRatioBox, aumBox, returnsBox);
        infoBox.setPadding(new Insets(10, 0, 0, 0));

        // Add hover effect
        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: #B0B0B0; -fx-border-radius: 15; -fx-background-radius: 15; -fx-scale-x: 1.05; -fx-scale-y:1.05;"));
        card.setOnMouseExited(e -> card.setStyle(
                "-fx-background-color: rgba(240, 240, 240, 0.6); -fx-border-color: #B0B0B0; -fx-border-radius: 15; -fx-background-radius: 15; -fx-scale-x: 1; -fx-scale-y:1;"));

        // Set card click action
        card.setOnMouseClicked(event -> {
            hiddenButton.setUserData(i);
            hiddenButton.fire();
        });

        card.getChildren().addAll(imageView, titleLabel, infoBox);

        return card;
    }

    private static HBox createInfoBox(String label, String value) {
        return createInfoBox(label, value, Color.BLACK);
    }

    private static HBox createInfoBox(String label, String value, Color valueColor) {
        Label labelNode = new Label(label);
        labelNode.setFont(new Font("Arial", 17));
        labelNode.setTextFill(Color.GRAY);

        Label valueNode = new Label(value);
        valueNode.setFont(new Font("Arial", 17));
        valueNode.setTextFill(valueColor);
        valueNode.setStyle("-fx-font-weight: bold;");

        HBox infoBox = new HBox(10, labelNode, valueNode);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(labelNode, Priority.ALWAYS);
        valueNode.setAlignment(Pos.CENTER_RIGHT);

        return infoBox;
    }

    public static void main(String[] args) {
        launch(args);
    }

    

    public static Scene getHomePageScene(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        // Top Layout
        HBox topLayout = createTopLayout();

        // Sidebar Menu
        VBox sidebarMenu = createSidebarMenu();

        Button hiddenButton = new Button("Hidden Button");
        hiddenButton.setVisible(false); // Make the button invisible

        // Set the action for the hidden button
        hiddenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer cardIndex = (Integer) hiddenButton.getUserData();
                sip1 bn = new sip1();
                sip3 n = new sip3();
                sip2 it = new sip2();
                sip4 p = new sip4();

                switch (cardIndex) {
                    case 1:
                        try {
                            bn.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            n.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        try {
                            it.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        try {
                            p.start(primaryStage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });

        // Cards for events
        VBox card1 = createCard("Bank Nifty", "file:future_forge//src//main//resources//images//banknifty.jpeg", "0.2%",
                "₹664 Cr", "15.6%",
                hiddenButton, 1);
        VBox card2 = createCard("Nifty", "file:future_forge//src//main//resources//images//nifty.jpeg", "0.3%",
                "₹700 Cr", "12.3%",
                hiddenButton, 2);
        VBox card3 = createCard("IT", "file:future_forge//src//main//resources//images//it.jpeg", "0.1%", "₹800 Cr",
                "18.9%", hiddenButton, 3);
        VBox card4 = createCard("Pharma", "file:future_forge//src//main//resources//images//pharma.jpeg", "0.4%",
                "₹900 Cr", "10.2%",
                hiddenButton, 4);

        // Grid for the cards
        GridPane centerGrid = createCardGrid(card1, card2, card3, card4);

        // Root layout
        BorderPane root = new BorderPane();
        root.setTop(topLayout);
        root.setLeft(sidebarMenu);
        root.setCenter(centerGrid);
        root.setPadding(new Insets(10));

        // Sidebar Toggle Button Action
        javafx.scene.Node firstChild = topLayout.getChildren().get(0);
        if (firstChild instanceof HBox) {
            HBox hbox = (HBox) firstChild;
            if (!hbox.getChildren().isEmpty() && hbox.getChildren().get(0) instanceof Button) {
                Button menuButton = (Button) hbox.getChildren().get(0);
                menuButton.setOnAction(e -> toggleSidebar(root, sidebarMenu));
            } else {
                // Handle the case where the expected structure is not as assumed
                System.err.println("Expected Button not found in HBox");
            }
        } else {
            // Handle the case where the first child of topLayout is not an HBox
            System.err.println("Expected HBox not found in topLayout");
        }

        // Adding responsive background image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:invest_elevate\\future_forge\\src\\main\\resources\\images\\house.png",
                        Screen.getPrimary().getBounds().getWidth(),
                        Screen.getPrimary().getBounds().getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        // Scene
        Scene scene = new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        return scene;
    }

    public Scene getHomePageScene1(Stage primaryStage) {
        start(primaryStage);
        return primaryStage.getScene();
    }

}
