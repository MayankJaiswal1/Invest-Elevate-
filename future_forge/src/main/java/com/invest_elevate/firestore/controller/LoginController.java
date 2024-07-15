package com.invest_elevate.firestore.controller;

import java.util.concurrent.ExecutionException;

import com.invest_elevate.FullscreenNavigation.HomePage;
import com.invest_elevate.firestore.dashboards.AdminPage;
//import com.invest_elevate.firestore.dashboards.UserPage;
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

public class LoginController {

    private static Stage primaryStage;
    private static Scene loginScene;
    
    private Scene userScene;
    private Scene adminScene;
    private DataService dataService;
    public static String key;
    private Scene tempScene;
    public LoginController(){
        dataService = new DataService();
    }

    public void setScene(){
        
        primaryStage.setScene(loginScene);
    }
    public LoginController(Stage primaryStage) {

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
   
   
        this.primaryStage = primaryStage;
        dataService = new DataService();
        initScenes();

        this.tempScene=loginScene;

    }

    public void setPrimaryStage(Scene scene) {
        if (primaryStage != null) {
            primaryStage.setScene(scene);
        } else {
            System.err.println("Primary stage is not set. Cannot set scene.");
        }
    }

    private void initScenes() {
        initLoginScene();
    }

    public Scene initLoginScene() {
        Label userLabel = new Label("Username");
        TextField userTextField = new TextField();
        Label passLabel = new Label("Password");
        PasswordField passField = new PasswordField();

        Button adminLoginButton = new Button("Admin");
        Button userLoginButton = new Button("Login");
        Button signupButton = new Button("Signup");

        //adminLoginButton.setPrefSize(100, 5);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            handleLogin(userTextField.getText(), passField.getText());
            userTextField.clear();
            passField.clear();
        });

        signupButton.setOnAction(event -> {
            showSignupScene();
            userTextField.clear();
            passField.clear();
        });

        adminLoginButton.setOnAction(event -> {
            handleAdminLogin(userTextField.getText(), passField.getText());
            SimpleTabViewWithBorderPane ad = new SimpleTabViewWithBorderPane();
            ad.start(primaryStage);
            userTextField.clear();
            passField.clear();
        });

        userLoginButton.setOnAction(event -> {
            handleUserLogin(userTextField.getText(), passField.getText());
            HomePage hp = new HomePage();
            hp.start(primaryStage);
            userTextField.clear();
            passField.clear();
        });

        userLabel.setStyle("-fx-text-fill: black; -fx-font-weight:bold; -fx-font-size:16; -fx-max-width:200; -fx-max-height:50;");
        passLabel.setStyle("-fx-text-fill: black; -fx-font-weight:bold; -fx-font-size:16; -fx-max-width:200; -fx-max-height:50;");

        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);
        HBox buttonBox = new HBox(50, adminLoginButton, userLoginButton, signupButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);

        userTextField.setStyle("-fx-set-pref-width:400; -fx-set-pref-height:70");
        passField.setStyle("-fx-set-pref-width:400; -fx-set-pref-height:70");

        ImageView profileImageView = new ImageView(
                new Image("file:future_forge\\src\\main\\resources\\icons\\login.png"));
        profileImageView.setFitHeight(135);
        profileImageView.setFitWidth(135);
        profileImageView.setPreserveRatio(true);
        profileImageView.setStyle("-fx-background-color: transparent;");
        //VBox profileIcon = new VBox(20, profileImageView);
        //profileIcon.setPadding(new Insets(0, 0, 0, 20));

        BorderPane root = new BorderPane();


        VBox vbox = new VBox(20, profileImageView, fieldBox1, fieldBox2, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(400);
        vbox.setMaxHeight(400);
        root.setCenter(vbox);
        

        vbox.setStyle(
                "-fx-background-color: rgba(240, 240, 240, 0.5);-fx-border-color: #B0B0B0; -fx-border-radius: 20; -fx-background-radius: 20;");

        //vbox.setStyle("-fx-background-image:url('https://img.freepik.com/free-photo/digital-world-banner-background-remixed-from-public-domain-by-nasa_53876-124622.jpg?semt=sph')");
        

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:future_forge\\src\\main\\resources\\sippage\\userDesigner.jpeg",
                        Screen.getPrimary().getBounds().getWidth(),
                        Screen.getPrimary().getBounds().getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(backgroundImage));

        loginScene = new Scene(root, 700, 700);
        return loginScene;
    }

    // private void initUserScene() {
    //     UserPageApp userPage = new UserPageApp(dataService);
    //     userScene = new Scene(userPage.createUserScene(this::handleLogout), 700, 700);
    // }

    private void initAdminScene() {
        AdminPage adminPage = new AdminPage(this, dataService);
        adminScene = new Scene(adminPage.createAdminDashboard(this::handleLogout), 700, 700);
    }

    public Scene getLoginScene() {
        return loginScene;
    }
    public void setLoginScene(Scene scene) {
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    }
  

    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void handleUserLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password) && !dataService.isAdmin(username)) {
                key = username;
                //initUserScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("User Dashboard");
            } else {
                System.out.println("Invalid client credentials");
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password)) {
                key = username;
                //initUserScene();
                primaryStage.setScene(userScene);
                primaryStage.setTitle("User Dashboard");
            } else {
                System.out.println("Invalid credentials");
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void handleAdminLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password) && dataService.isAdmin(username)) {
                initAdminScene();
                primaryStage.setScene(adminScene);
                primaryStage.setTitle("Admin Dashboard");
            } else {
                System.out.println("Invalid admin credentials");
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void showSignupScene() {
        SignUpController signupController = new SignUpController(this);
        Scene signupScene = signupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();
    }

    private void handleLogout() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
    }

    public void returnToAdminView() {
        primaryStage.setScene(adminScene);
        primaryStage.setTitle("Admin Dashboard");
    }

    public void start(Stage primaryStage2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}