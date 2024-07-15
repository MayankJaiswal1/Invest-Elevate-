package com.invest_elevate.info;

import com.invest_elevate.FullscreenNavigation.HomePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class KYC {

    public static Scene getKYCFormScene(Stage stage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());

        Label heading = new Label("Please Fill the Required Information to Register in INVESTELEVATE Safely");
        heading.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: 'Arial';");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPrefWidth(200);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);

        Label panLabel = new Label("Pan No:");
        TextField panField = new TextField();
        panField.setPrefWidth(200);

        Label mobileLabel = new Label("Mobile No:");
        TextField mobileField = new TextField();
        mobileField.setPrefWidth(200);

        Label amountPerMonthLabel = new Label(" SIP Amount(per Month):");
        TextField amountPerMonthField = new TextField();
        amountPerMonthField.setPrefWidth(200);

        Label interestRateLabel = new Label("Interest Rate (%):");
        Label interestRateValue = new Label(); // To display selected interest rate

        Label noOfYearsLabel = new Label("Investment Period (Years):");
        TextField noOfYearsField = new TextField();
        noOfYearsField.setPrefWidth(200);

        Label totalAmountLabel = new Label("Total Amount:");
        Label totalAmountValue = new Label();

        Button calculateTotalButton = new Button("Calculate Total");
        calculateTotalButton.setOnAction(e -> {
            try {
                double amountPerMonth = Double.parseDouble(amountPerMonthField.getText());
                int noOfYears = Integer.parseInt(noOfYearsField.getText());
                double totalAmount = amountPerMonth * noOfYears * 12;
                totalAmountValue.setText(String.valueOf(totalAmount));
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for amount and years.");
                totalAmountValue.setText("Invalid input");
            }
        });

        ObservableList<String> sipOptions = FXCollections.observableArrayList(
                "Nifty Top 10", "Bank Nifty", "IT Sector", "Pharmaceutical Sector");
        ComboBox<String> sipComboBox = new ComboBox<>(sipOptions);

        Label sipReturnLabel = new Label("SIP Return:");
        Label sipReturnValue = new Label();

        sipComboBox.setOnAction(e -> {
            String selectedSip = sipComboBox.getSelectionModel().getSelectedItem();
            double interestRate;
            double totalAmount;
            try {
                totalAmount = Double.parseDouble(totalAmountValue.getText());
            } catch (NumberFormatException ex) {
                sipReturnValue.setText("Invalid total amount");
                interestRateValue.setText("");
                return;
            }
            double sipReturn = 0;
            switch (selectedSip) {
                case "Nifty Top 10":
                    interestRate = 23.0;
                    sipReturn = totalAmount * 0.23;
                    break;
                case "Bank Nifty":
                    interestRate = 32.0;
                    sipReturn = totalAmount * 0.32;
                    break;
                case "IT Sector":
                    interestRate = 32.0;
                    sipReturn = totalAmount * 0.32;
                    break;
                case "Pharmaceutical Sector":
                    interestRate = 45.0;
                    sipReturn = totalAmount * 0.45;
                    break;
                default:
                    interestRate = 0;
            }
            interestRateValue.setText(String.valueOf(interestRate));
            sipReturnValue.setText(String.valueOf(sipReturn));
        });

        Button payButton = new Button("Pay");
        ObservableList<String> payOptions = FXCollections.observableArrayList("UPI", "Debit Card", "Credit Card", "Net Banking");
        ComboBox<String> payComboBox = new ComboBox<>(payOptions);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || panField.getText().isEmpty()
                    || mobileField.getText().isEmpty() || amountPerMonthField.getText().isEmpty()
                    || noOfYearsField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
            } else if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email");
            } else if (!mobileField.getText().matches("\\d{10}")) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid 10-digit mobile number");
            } else if (!panField.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid PAN number");
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Submission Status", "Details submitted successfully!");
                stage.setScene(HomePage.getHomePageScene(stage)); // Navigate to home page
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(panLabel, 0, 2);
        gridPane.add(panField, 1, 2);
        gridPane.add(mobileLabel, 0, 3);
        gridPane.add(mobileField, 1, 3);
        gridPane.add(amountPerMonthLabel, 0, 4);
        gridPane.add(amountPerMonthField, 1, 4);
        gridPane.add(noOfYearsLabel, 0, 5);
        gridPane.add(noOfYearsField, 1, 5);
        gridPane.add(totalAmountLabel, 0, 6);
        gridPane.add(totalAmountValue, 1, 6);
        gridPane.add(calculateTotalButton, 1, 7);
        gridPane.add(new Label("SIP Options:"), 0, 8);
        gridPane.add(sipComboBox, 1, 8);
        gridPane.add(interestRateLabel, 0, 9);
        gridPane.add(interestRateValue, 1, 9);
        gridPane.add(sipReturnLabel, 0, 10);
        gridPane.add(sipReturnValue, 1, 10);
        gridPane.add(new Label("Pay:"), 0, 11);
        gridPane.add(payComboBox, 1, 11);
        gridPane.add(submitButton, 1, 12);
        gridPane.setAlignment(Pos.CENTER);

        // Encapsulate the GridPane in an HBox for layout consistency
        HBox hbox = new HBox(gridPane);
        hbox.setMinSize(700, 700);
        hbox.setMaxSize(700, 700);
        hbox.setAlignment(Pos.CENTER);

        hbox.setStyle("-fx-background-color: white; -fx-border-width: 1px;");

        VBox layout = new VBox(10, heading, hbox);
        layout.setAlignment(Pos.CENTER);

        // Background Image
        ImageView background = new ImageView(new Image("file:future_forge//src//main//resources//ImagesA//login.jpg"));
        background.setPreserveRatio(false);

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

    public static void showKYCForm(Stage stage) {
        if (stage == null) {
            stage = new Stage();
        }
        stage.setTitle("KYC Form");
        stage.setScene(getKYCFormScene(stage));
        stage.show();
    }

    public void start(Stage stage) {
        showKYCForm(stage);
    }
}
