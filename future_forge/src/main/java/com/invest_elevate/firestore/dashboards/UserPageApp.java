// package com.invest_elevate.firestore.dashboards;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.google.cloud.firestore.DocumentSnapshot;
// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class UserPage {

//     private DataService dataService; // DataService for Firestore operations
//     private Label dataMsg; // Label to display status messages

//     public UserPage(DataService dataService) {
//         this.dataService = dataService;
//     }

//     // Method to create and return the user interface VBox for project data entry
//     public VBox createUserScene(Runnable logoutHandler) {
//         // Initialize dataMsg for displaying status messages
//         dataMsg = new Label();

//         // UI elements for entering project details
//         Label groupLabel = new Label("Enter group name:");
//         groupLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField groupName = new TextField();
//         groupName.setPromptText("Group name");
//         VBox groupBox = new VBox(10, groupLabel, groupName);
//         groupBox.setMaxSize(300, 20);

//         Label projectLabel = new Label("Enter project name:");
//         projectLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField projectName = new TextField();
//         projectName.setPromptText("Project name");
//         VBox projectBox = new VBox(10, projectLabel, projectName);
//         projectBox.setMaxSize(300, 20);

//         Label mobileNumberLabel = new Label("Enter mobile number:");
//         mobileNumberLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField mobileNumber = new TextField();
//         mobileNumber.setPromptText("Mobile number");
//         VBox mobileNumberBox = new VBox(10, mobileNumberLabel, mobileNumber);
//         mobileNumberBox.setMaxSize(300, 20);

//         Label leaderLabel = new Label("Enter leader name:");
//         leaderLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField leaderName = new TextField();
//         leaderName.setPromptText("Leader name");
//         VBox leaderNameBox = new VBox(10, leaderLabel, leaderName);
//         leaderNameBox.setMaxSize(300, 20);

//         Label memberLabel1 = new Label("Enter member 1 name:");
//         memberLabel1.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member1 = new TextField();
//         member1.setPromptText("Member 1 name");
//         VBox member1Box = new VBox(10, memberLabel1, member1);
//         member1Box.setMaxSize(300, 20);

//         Label memberLabel2 = new Label("Enter member 2 name:");
//         memberLabel2.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member2 = new TextField();
//         member2.setPromptText("Member 2 name");
//         VBox member2Box = new VBox(10, memberLabel2, member2);
//         member2Box.setMaxSize(300, 20);

//         Label memberLabel3 = new Label("Enter member 3 name:");
//         memberLabel3.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member3 = new TextField();
//         member3.setPromptText("Member 3 name");
//         VBox member3Box = new VBox(10, memberLabel3, member3);
//         member3Box.setMaxSize(300, 20);

//         // Button to add project data
//         Button addButton = new Button("Add Data");
//         addButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         HBox buttonBox = new HBox(addButton);
//         buttonBox.setAlignment(Pos.CENTER);

//         // Button to logout
//         Button logoutButton = new Button("Logout");

//         // Action handler for addButton
//         addButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 // Create a map with project data
//                 Map<String, Object> data = new HashMap<>();
//                 data.put("groupName", groupName.getText());
//                 data.put("projectName", projectName.getText());
//                 data.put("leaderName", leaderName.getText());
//                 data.put("mobileNum", mobileNumber.getText());
//                 data.put("member1", member1.getText());
//                 data.put("member2", member2.getText());
//                 data.put("member3", member3.getText());
//                 data.put("timestamp", LocalDateTime.now());

//                 try {
//                     // Attempt to add data to Firestore
//                     dataService.addData("collectionName", leaderName.getText(), data);
//                     dataMsg.setText("Added successfully"); // Update status message
//                     // Clear input fields after successful addition
//                     groupName.clear();
//                     projectName.clear();
//                     leaderName.clear();
//                     mobileNumber.clear();
//                     member1.clear();
//                     member2.clear();
//                     member3.clear();
//                 } catch (ExecutionException | InterruptedException ex) {
//                     dataMsg.setText("Something went wrong"); // Update status message
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         // Action handler for logoutButton
//         logoutButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 logoutHandler.run(); // Execute logout handler
//             }
//         });

//         // Get the logged-in username
//         Text message = getTheLoginName();

//         // VBox for arranging UI elements
//         VBox vButton = new VBox(logoutButton);
//         vButton.setAlignment(Pos.TOP_LEFT);
//         VBox dataBox = new VBox(25, vButton, message, groupBox, projectBox, mobileNumberBox, leaderNameBox, member1Box, member2Box, member3Box, buttonBox, dataMsg);
//         dataBox.setAlignment(Pos.CENTER);
//         return dataBox;
//     }

//     // Method to fetch and display the logged-in user's name
//     public Text getTheLoginName() {
//         Label dataLabel = new Label();
//         try {
//             String key = LoginController.key;
//             System.out.println("Value of key: " + key);
//             DocumentSnapshot dataObject = dataService.getData("users", key);
//             String userName = dataObject.getString("username");
//             System.out.println("Username fetched: " + userName);
//             dataLabel.setText(userName);
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return new Text("Welcome " + dataLabel.getText());
//     }
// }









// package com.invest_elevate.firestore.dashboards;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.google.cloud.firestore.DocumentSnapshot;
// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class UserPage {

//     private DataService dataService; // DataService for Firestore operations
//     private Label dataMsg; // Label to display status messages

//     public UserPage(DataService dataService) {
//         this.dataService = dataService;
//     }

//     // Method to create and return the user interface VBox for project data entry
//     public VBox createUserScene(Runnable logoutHandler) {
//         // Initialize dataMsg for displaying status messages
//         dataMsg = new Label();

//         // UI elements for entering project details
//         Label nameLabel = new Label("Name:");
//         nameLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField nameField = new TextField();
//         nameField.setPromptText("Name");
//         VBox nameBox = new VBox(10, nameLabel, nameField);
//         nameBox.setMaxSize(300, 20);

//         Label emailLabel = new Label("Email:");
//         emailLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField emailField = new TextField();
//         emailField.setPromptText("Email");
//         VBox emailBox = new VBox(10, emailLabel, emailField);
//         emailBox.setMaxSize(300, 20);

//         Label panLabel = new Label("Pan No:");
//         panLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField panField = new TextField();
//         panField.setPromptText("Pan No");
//         VBox panBox = new VBox(10, panLabel, panField);
//         panBox.setMaxSize(300, 20);

//         Label mobileLabel = new Label("Mobile No:");
//         mobileLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField mobileField = new TextField();
//         mobileField.setPromptText("Mobile No");
//         VBox mobileBox = new VBox(10, mobileLabel, mobileField);
//         mobileBox.setMaxSize(300, 20);

//         Label amountPerMonthLabel = new Label("SIP Amount (per Month):");
//         amountPerMonthLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField amountPerMonthField = new TextField();
//         amountPerMonthField.setPromptText("SIP Amount");
//         VBox amountPerMonthBox = new VBox(10, amountPerMonthLabel, amountPerMonthField);
//         amountPerMonthBox.setMaxSize(300, 20);

//         Label noOfYearsLabel = new Label("Investment Period (Years):");
//         noOfYearsLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField noOfYearsField = new TextField();
//         noOfYearsField.setPromptText("Investment Period");
//         VBox noOfYearsBox = new VBox(10, noOfYearsLabel, noOfYearsField);
//         noOfYearsBox.setMaxSize(300, 20);

//         Label totalAmountLabel = new Label("Total Amount:");
//         totalAmountLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField totalAmountField = new TextField();
//         totalAmountField.setPromptText("Total Amount");
//         VBox totalAmountBox = new VBox(10, totalAmountLabel, totalAmountField);
//         totalAmountBox.setMaxSize(300, 20);

//         // Button to add project data
//         Button addButton = new Button("Add Data");
//         addButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         HBox buttonBox = new HBox(addButton);
//         buttonBox.setAlignment(Pos.CENTER);

//         // Button to logout
//         Button logoutButton = new Button("Logout");

//         // Action handler for addButton
//         addButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 // Create a map with project data
//                 Map<String, Object> data = new HashMap<>();
//                 data.put("name", nameField.getText());
//                 data.put("email", emailField.getText());
//                 data.put("panNo", panField.getText());
//                 data.put("mobileNo", mobileField.getText());
//                 data.put("sipAmount", amountPerMonthField.getText());
//                 data.put("investmentPeriod", noOfYearsField.getText());
//                 data.put("totalAmount", totalAmountField.getText());
//                 data.put("timestamp", LocalDateTime.now());

//                 try {
//                     // Attempt to add data to Firestore
//                     dataService.addData("collectionName", emailField.getText(), data);
//                     dataMsg.setText("Added successfully"); // Update status message
//                     // Clear input fields after successful addition
//                     nameField.clear();
//                     emailField.clear();
//                     panField.clear();
//                     mobileField.clear();
//                     amountPerMonthField.clear();
//                     noOfYearsField.clear();
//                     totalAmountField.clear();
//                 } catch (ExecutionException | InterruptedException ex) {
//                     dataMsg.setText("Something went wrong"); // Update status message
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         // Action handler for logoutButton
//         logoutButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 logoutHandler.run(); // Execute logout handler
//             }
//         });

//         // Get the logged-in username
//         Text message = getTheLoginName();

//         // VBox for arranging UI elements
//         VBox vButton = new VBox(logoutButton);
//         vButton.setAlignment(Pos.TOP_LEFT);
//         VBox dataBox = new VBox(25, vButton, message, nameBox, emailBox, panBox, mobileBox, amountPerMonthBox, noOfYearsBox, totalAmountBox, buttonBox, dataMsg);
//         dataBox.setAlignment(Pos.CENTER);
//         return dataBox;
//     }

//     // Method to fetch and display the logged-in user's name
//     public Text getTheLoginName() {
//         Label dataLabel = new Label();
//         try {
//             String key = LoginController.key;
//             System.out.println("Value of key: " + key);
//             DocumentSnapshot dataObject = dataService.getData("users", key);
//             String userName = dataObject.getString("username");
//             System.out.println("Username fetched: " + userName);
//             dataLabel.setText(userName);
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return new Text("Welcome " + dataLabel.getText());
//     }
// }










// package com.invest_elevate.firestore.dashboards;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.google.cloud.firestore.DocumentSnapshot;
// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class UserPage {

//     private DataService dataService; // DataService for Firestore operations
//     private Label dataMsg; // Label to display status messages

//     public UserPage(DataService dataService) {
//         this.dataService = dataService;
//     }

//     // Method to create and return the user interface VBox for project data entry
//     public VBox createUserScene(Runnable logoutHandler) {
//         // Initialize dataMsg for displaying status messages
//         dataMsg = new Label();

//         // UI elements for KYC form fields
//         Label nameLabel = new Label("Name:");
//         TextField nameField = new TextField();
//         nameField.setPrefWidth(200);

//         Label emailLabel = new Label("Email:");
//         TextField emailField = new TextField();
//         emailField.setPrefWidth(200);

//         Label panLabel = new Label("Pan No:");
//         TextField panField = new TextField();
//         panField.setPrefWidth(200);

//         Label mobileLabel = new Label("Mobile No:");
//         TextField mobileField = new TextField();
//         mobileField.setPrefWidth(200);

//         Label amountPerMonthLabel = new Label("SIP Amount(per Month):");
//         TextField amountPerMonthField = new TextField();
//         amountPerMonthField.setPrefWidth(200);

//         Label interestRateLabel = new Label("Interest Rate (%):");
//         Label interestRateValue = new Label(); // To display selected interest rate

//         Label noOfYearsLabel = new Label("Investment Period (Years):");
//         TextField noOfYearsField = new TextField();
//         noOfYearsField.setPrefWidth(200);

//         Label totalAmountLabel = new Label("Total Amount:");
//         Label totalAmountValue = new Label();

//         Button calculateTotalButton = new Button("Calculate Total");
//         calculateTotalButton.setOnAction(e -> {
//             try {
//                 double amountPerMonth = Double.parseDouble(amountPerMonthField.getText());
//                 int noOfYears = Integer.parseInt(noOfYearsField.getText());
//                 double totalAmount = amountPerMonth * noOfYears * 12;
//                 totalAmountValue.setText(String.valueOf(totalAmount));
//             } catch (NumberFormatException ex) {
//                 showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for amount and years.");
//                 totalAmountValue.setText("Invalid input");
//             }
//         });

//         ComboBox<String> sipComboBox = new ComboBox<>();
//         sipComboBox.getItems().addAll("Nifty Top 10", "Bank Nifty", "IT Sector", "Pharmaceutical Sector");

//         Label sipReturnLabel = new Label("SIP Return:");
//         Label sipReturnValue = new Label();

//         sipComboBox.setOnAction(e -> {
//             String selectedSip = sipComboBox.getSelectionModel().getSelectedItem();
//             double interestRate;
//             double totalAmount;
//             try {
//                 totalAmount = Double.parseDouble(totalAmountValue.getText());
//             } catch (NumberFormatException ex) {
//                 sipReturnValue.setText("Invalid total amount");
//                 interestRateValue.setText("");
//                 return;
//             }
//             double sipReturn = 0;
//             switch (selectedSip) {
//                 case "Nifty Top 10":
//                     interestRate = 23.0;
//                     sipReturn = totalAmount * 0.23;
//                     break;
//                 case "Bank Nifty":
//                     interestRate = 32.0;
//                     sipReturn = totalAmount * 0.32;
//                     break;
//                 case "IT Sector":
//                     interestRate = 32.0;
//                     sipReturn = totalAmount * 0.32;
//                     break;
//                 case "Pharmaceutical Sector":
//                     interestRate = 45.0;
//                     sipReturn = totalAmount * 0.45;
//                     break;
//                 default:
//                     interestRate = 0;
//             }
//             interestRateValue.setText(String.valueOf(interestRate));
//             sipReturnValue.setText(String.valueOf(sipReturn));
//         });

//         ComboBox<String> payComboBox = new ComboBox<>();
//         payComboBox.getItems().addAll("UPI", "Debit Card", "Credit Card", "Net Banking");

//         Button submitButton = new Button("Submit");
//         submitButton.setOnAction(e -> {
//             if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || panField.getText().isEmpty()
//                     || mobileField.getText().isEmpty() || amountPerMonthField.getText().isEmpty()
//                     || noOfYearsField.getText().isEmpty()) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
//             } else if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email");
//             } else if (!mobileField.getText().matches("\\d{10}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid 10-digit mobile number");
//             } else if (!panField.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid PAN number");
//             } else {
//                 // Create a map with KYC data
//                 Map<String, Object> data = new HashMap<>();
//                 data.put("name", nameField.getText());
//                 data.put("email", emailField.getText());
//                 data.put("pan", panField.getText());
//                 data.put("mobile", mobileField.getText());
//                 data.put("sipAmount", amountPerMonthField.getText());
//                 data.put("interestRate", interestRateValue.getText());
//                 data.put("investmentPeriod", noOfYearsField.getText());
//                 data.put("sipOptions", sipComboBox.getValue());
//                 data.put("interestRate", interestRateValue.getText());
//                 data.put("totalAmountInvested", totalAmountValue.getText());
//                 data.put("sipReturns", sipReturnValue.getText());
//                 data.put("paymentMethod", payComboBox.getValue());
//                 data.put("timestamp", LocalDateTime.now());

//                 try {
//                     // Attempt to add data to Firestore
//                     dataService.addData("kycCollection", nameField.getText(), data);
//                     showAlert(Alert.AlertType.INFORMATION, "Submission Status", "Details submitted successfully!");
//                 } catch (ExecutionException | InterruptedException ex) {
//                     showAlert(Alert.AlertType.ERROR, "Error", "Failed to submit details. Please try again.");
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         // Button to logout
//         Button logoutButton = new Button("Logout");

//         // Action handler for logoutButton
//         logoutButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 logoutHandler.run(); // Execute logout handler
//             }
//         });

//         // Get the logged-in username
//         Text message = getTheLoginName();

//         // VBox for arranging UI elements
//         VBox vButton = new VBox(logoutButton);
//         vButton.setAlignment(Pos.TOP_LEFT);

//         // GridPane for KYC form fields
//         GridPane gridPane = new GridPane();
//         gridPane.setVgap(10);
//         gridPane.setHgap(10);
//         gridPane.add(nameLabel, 0, 0);
//         gridPane.add(nameField, 1, 0);
//         gridPane.add(emailLabel, 0, 1);
//         gridPane.add(emailField, 1, 1);
//         gridPane.add(panLabel, 0, 2);
//         gridPane.add(panField, 1, 2);
//         gridPane.add(mobileLabel, 0, 3);
//         gridPane.add(mobileField, 1, 3);
//         gridPane.add(amountPerMonthLabel, 0, 4);
//         gridPane.add(amountPerMonthField, 1, 4);
//         gridPane.add(noOfYearsLabel, 0, 5);
//         gridPane.add(noOfYearsField, 1, 5);
//         gridPane.add(totalAmountLabel, 0, 6);
//         gridPane.add(totalAmountValue, 1, 6);
//         gridPane.add(calculateTotalButton, 1, 7);
//         gridPane.add(new Label("SIP Options:"), 0, 8);
//         gridPane.add(sipComboBox, 1, 8);
//         gridPane.add(interestRateLabel, 0, 9);
//         gridPane.add(interestRateValue, 1, 9);
//         gridPane.add(sipReturnLabel, 0, 10);
//         gridPane.add(sipReturnValue, 1, 10);
//         gridPane.add(new Label("Pay:"), 0, 11);
//         gridPane.add(payComboBox, 1, 11);
//         gridPane.add(submitButton, 1, 12);
//         gridPane.setAlignment(Pos.CENTER);

//         VBox dataBox = new VBox(25, vButton, message, gridPane);
//         dataBox.setAlignment(Pos.CENTER);

//         return dataBox;
//     }

//     // Method to fetch and display the logged-in user's name
//     public Text getTheLoginName() {
//         Label dataLabel = new Label();
//         try {
//             String key = LoginController.key;
//             System.out.println("Value of key: " + key);
//             DocumentSnapshot dataObject = dataService.getData("users", key);
//             String userName = dataObject.getString("username");
//             System.out.println("Username fetched: " + userName);
//             dataLabel.setText(userName);
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return new Text("Welcome " + dataLabel.getText());
//     }

//     // Method to show alerts
//     private static void showAlert(Alert.AlertType alertType, String title, String message) {
//         Alert alert = new Alert(alertType);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }









// package com.invest_elevate.firestore.dashboards;
// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.google.cloud.firestore.DocumentSnapshot;
// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class UserPage {

//     private DataService dataService; // DataService for Firestore operations
//     private Label dataMsg; // Label to display status messages

//     public UserPage(DataService dataService) {
//         this.dataService = dataService;
//     }

//     // Method to create and return the user interface VBox for project data entry
//     public VBox createUserScene(Runnable logoutHandler) {
//         // Initialize dataMsg for displaying status messages
//         dataMsg = new Label();

//         // UI elements for KYC form fields
//         Label nameLabel = new Label("Name:");
//         TextField nameField = new TextField();
//         nameField.setPrefWidth(200);

//         Label emailLabel = new Label("Email:");
//         TextField emailField = new TextField();
//         emailField.setPrefWidth(200);

//         Label panLabel = new Label("Pan No:");
//         TextField panField = new TextField();
//         panField.setPrefWidth(200);

//         Label mobileLabel = new Label("Mobile No:");
//         TextField mobileField = new TextField();
//         mobileField.setPrefWidth(200);

//         Label amountPerMonthLabel = new Label("SIP Amount (per Month):");
//         TextField amountPerMonthField = new TextField();
//         amountPerMonthField.setPrefWidth(200);

//         Label interestRateLabel = new Label("Interest Rate (%):");
//         Label interestRateValue = new Label(); // To display selected interest rate

//         Label noOfYearsLabel = new Label("Investment Period (Years):");
//         TextField noOfYearsField = new TextField();
//         noOfYearsField.setPrefWidth(200);

//         Label totalAmountLabel = new Label("Total Amount:");
//         Label totalAmountValue = new Label();

//         Button calculateTotalButton = new Button("Calculate Total");
//         calculateTotalButton.setOnAction(e -> {
//             try {
//                 double amountPerMonth = Double.parseDouble(amountPerMonthField.getText());
//                 int noOfYears = Integer.parseInt(noOfYearsField.getText());
//                 double totalAmount = amountPerMonth * noOfYears * 12;
//                 totalAmountValue.setText(String.valueOf(totalAmount));
//             } catch (NumberFormatException ex) {
//                 showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for amount and years.");
//                 totalAmountValue.setText("Invalid input");
//             }
//         });

//         ComboBox<String> sipComboBox = new ComboBox<>();
//         sipComboBox.getItems().addAll("Nifty Top 10", "Bank Nifty", "IT Sector", "Pharmaceutical Sector");

//         Label sipReturnLabel = new Label("SIP Return:");
//         Label sipReturnValue = new Label();

//         sipComboBox.setOnAction(e -> {
//             String selectedSip = sipComboBox.getSelectionModel().getSelectedItem();
//             double interestRate;
//             double totalAmount;
//             try {
//                 totalAmount = Double.parseDouble(totalAmountValue.getText());
//             } catch (NumberFormatException ex) {
//                 sipReturnValue.setText("Invalid total amount");
//                 interestRateValue.setText("");
//                 return;
//             }
//             double sipReturn = 0;
//             switch (selectedSip) {
//                 case "Nifty Top 10":
//                     interestRate = 23.0;
//                     sipReturn = totalAmount * 0.23;
//                     break;
//                 case "Bank Nifty":
//                     interestRate = 32.0;
//                     sipReturn = totalAmount * 0.32;
//                     break;
//                 case "IT Sector":
//                     interestRate = 32.0;
//                     sipReturn = totalAmount * 0.32;
//                     break;
//                 case "Pharmaceutical Sector":
//                     interestRate = 45.0;
//                     sipReturn = totalAmount * 0.45;
//                     break;
//                 default:
//                     interestRate = 0;
//             }
//             interestRateValue.setText(String.valueOf(interestRate));
//             sipReturnValue.setText(String.valueOf(sipReturn));
//         });

//         ComboBox<String> payComboBox = new ComboBox<>();
//         payComboBox.getItems().addAll("UPI", "Debit Card", "Credit Card", "Net Banking");

//         Button submitButton = new Button("Submit");
//         submitButton.setOnAction(e -> {
//             if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || panField.getText().isEmpty()
//                     || mobileField.getText().isEmpty() || amountPerMonthField.getText().isEmpty()
//                     || noOfYearsField.getText().isEmpty()) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
//             } else if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email");
//             } else if (!mobileField.getText().matches("\\d{10}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid 10-digit mobile number");
//             } else if (!panField.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
//                 showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid PAN number");
//             } else {
//                 // Create a map with KYC data
//                 Map<String, Object> data = new HashMap<>();
//                 data.put("name", nameField.getText());
//                 data.put("email", emailField.getText());
//                 data.put("pan", panField.getText());
//                 data.put("mobile", mobileField.getText());
//                 data.put("sipAmount", amountPerMonthField.getText());
//                 data.put("interestRate", interestRateValue.getText());
//                 data.put("investmentPeriod", noOfYearsField.getText());
//                 data.put("sipOptions", sipComboBox.getValue());
//                 data.put("interestRate", interestRateValue.getText());
//                 data.put("totalAmountInvested", totalAmountValue.getText());
//                 data.put("sipReturns", sipReturnValue.getText());
//                 data.put("paymentMethod", payComboBox.getValue());
//                 data.put("timestamp", LocalDateTime.now());

//                 try {
//                     // Attempt to add data to Firestore
//                     dataService.addData("kycCollection", nameField.getText(), data);
//                     showAlert(Alert.AlertType.INFORMATION, "Submission Status", "Details submitted successfully!");
//                 } catch (ExecutionException | InterruptedException ex) {
//                     showAlert(Alert.AlertType.ERROR, "Error", "Failed to submit details. Please try again.");
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         // Button to logout
//         Button logoutButton = new Button("Logout");
//         logoutButton.getStyleClass().add("logout-button");
//         logoutButton.setOnAction(new EventHandler<ActionEvent>() {
//             @Override
//             public void handle(ActionEvent event) {
//                 logoutHandler.run(); // Execute logout handler
//             }
//         });

//         // Button to navigate to HomePage.java
//         Button homeButton = new Button("Home");
//         homeButton.getStyleClass().add("home-button");
//         homeButton.setOnAction(e -> {
//             // Navigate to HomePage.java
//             // Replace with your navigation logic
//             System.out.println("Navigate to HomePage.java");
//         });

//         // Get the logged-in username
//         Text message = getTheLoginName();

//         // GridPane for KYC form fields
//         GridPane gridPane = new GridPane();
//         gridPane.setVgap(10);
//         gridPane.setHgap(10);
//         gridPane.add(nameLabel, 0, 0);
//         gridPane.add(nameField, 1, 0);
//         gridPane.add(emailLabel, 0, 1);
//         gridPane.add(emailField, 1, 1);
//         gridPane.add(panLabel, 0, 2);
//         gridPane.add(panField, 1, 2);
//         gridPane.add(mobileLabel, 0, 3);
//         gridPane.add(mobileField, 1, 3);
//         gridPane.add(amountPerMonthLabel, 0, 4);
//         gridPane.add(amountPerMonthField, 1, 4);
//         gridPane.add(noOfYearsLabel, 0, 5);
//         gridPane.add(noOfYearsField, 1, 5);
//         gridPane.add(totalAmountLabel, 0, 6);
//         gridPane.add(totalAmountValue, 1, 6);
//         gridPane.add(calculateTotalButton, 1, 7);
//         gridPane.add(new Label("SIP Options:"), 0, 8);
//         gridPane.add(sipComboBox, 1, 8);
//         gridPane.add(interestRateLabel, 0, 9);
//         gridPane.add(interestRateValue, 1, 9);
//         gridPane.add(sipReturnLabel, 0, 10);
//         gridPane.add(sipReturnValue, 1, 10);
//         gridPane.add(new Label("Pay:"), 0, 11);
//         gridPane.add(payComboBox, 1, 11);
//         gridPane.add(submitButton, 1, 12);
//         gridPane.add(homeButton,1,14);
//         gridPane.setAlignment(Pos.CENTER);

//         // StackPane to contain the logout and home buttons
//         StackPane buttonPane1 = new StackPane(logoutButton );
//         buttonPane1.setAlignment(Pos.TOP_LEFT);

//         // StackPane buttonPane2 = new StackPane(homeButton);
//         // buttonPane2.setAlignment(Pos.TOP_RIGHT);

//         // VBox to arrange all UI elements
//         VBox mainBox = new VBox(20, buttonPane1, message, gridPane);
//         mainBox.setAlignment(Pos.CENTER);

//         // Create a card-like styling for the mainBox
//         mainBox.getStyleClass().add("card");

//         return mainBox;
//     }

//     // Method to fetch and display the logged-in user's name
//     public Text getTheLoginName() {
//         Label dataLabel = new Label();
//         try {
//             String key = LoginController.key;
//             System.out.println("Value of key: " + key);
//             DocumentSnapshot dataObject = dataService.getData("users", key);
//             String userName = dataObject.getString("username");
//             System.out.println("Username fetched: " + userName);
//             dataLabel.setText(userName);
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return new Text("Welcome " + dataLabel.getText());
//     }

//     // Method to show alerts
//     private static void showAlert(Alert.AlertType alertType, String title, String message) {
//         Alert alert = new Alert(alertType);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }



package com.invest_elevate.firestore.dashboards;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.cloud.firestore.DocumentSnapshot;
import com.invest_elevate.FullscreenNavigation.HomePage;
import com.invest_elevate.firestore.controller.LoginController;
import com.invest_elevate.firestore.firebaseConfig.DataService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

class UserPage {

    private DataService dataService; // DataService for Firestore operations
    private Label dataMsg; // Label to display status messages

    public UserPage(DataService dataService) {
        this.dataService = dataService;
    }

    // Method to create and return the user interface VBox for project data entry
    public BorderPane createUserScene(Runnable logoutHandler) {
        // Initialize dataMsg for displaying status messages
        dataMsg = new Label();

        // Heading
        Text heading = new Text("KYC Page");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        heading.setFill(Color.DARKBLUE);

        // UI elements for KYC form fields
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

        Label amountPerMonthLabel = new Label("SIP Amount (per Month):");
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
        calculateTotalButton.getStyleClass().add("calculate-button");
        calculateTotalButton.setOnAction(e -> {
            try {
                double amountPerMonth = Double.parseDouble(amountPerMonthField.getText());
                try {
                    sipValue sv = new sipValue();
                    sv.booking(amountPerMonth);
                    int noOfYears = Integer.parseInt(noOfYearsField.getText());
                    double totalAmount = amountPerMonth * noOfYears * 12;
                    totalAmountValue.setText(String.valueOf(totalAmount));
                } catch (ValidValueException ve) {
                    sipValue.showAlert1(ve.getMessage());
                }

            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Please enter valid numbers for amount and years.");
                totalAmountValue.setText("Invalid input");
            }
        });

        ComboBox<String> sipComboBox = new ComboBox<>();
        sipComboBox.getItems().addAll("Nifty Top 10", "Bank Nifty", "IT Sector", "Pharmaceutical Sector");

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

        ComboBox<String> payComboBox = new ComboBox<>();
        payComboBox.getItems().addAll("UPI", "Debit Card", "Credit Card", "Net Banking");

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submit-button");
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
                // Create a map with KYC data
                Map<String, Object> data = new HashMap<>();
                data.put("name", nameField.getText());
                data.put("email", emailField.getText());
                data.put("pan", panField.getText());
                data.put("mobile", mobileField.getText());
                data.put("sipAmount", amountPerMonthField.getText());
                data.put("interestRate", interestRateValue.getText());
                data.put("investmentPeriod", noOfYearsField.getText());
                data.put("sipOptions", sipComboBox.getValue());
                data.put("interestRate", interestRateValue.getText());
                data.put("totalAmountInvested", totalAmountValue.getText());
                data.put("sipReturns", sipReturnValue.getText());
                data.put("paymentMethod", payComboBox.getValue());
                data.put("timestamp", LocalDateTime.now());

                try {
                    // Attempt to add data to Firestore
                    dataService.addData("kycCollection", nameField.getText(), data);
                    showAlert(Alert.AlertType.INFORMATION, "Submission Status", "Details submitted successfully!\n Accept the Mandate");
                } catch (ExecutionException | InterruptedException ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to submit details. Please try again.");
                    ex.printStackTrace();
                }
            }
        });

        // Button to logout
        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("logout-button");
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logoutHandler.run(); // Execute logout handler
                LoginController loginController  = new LoginController();
                Scene scene = loginController.initLoginScene();
                loginController.setLoginScene(scene);
            }   
        });
        //elevating generation through investment 
        //Button to navigate to HomePage.java
        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("home-button");
        homeButton.setOnAction(e -> {
            // Navigate to HomePage.java
            // Replace with your navigation logic
            //HomePage homePage = new HomePage();
            HomePage.setScene();
            //homePage.start(new Stage());
            System.out.println("Navigate to HomePage.java");
        });

        // Get the logged-in username
        Text message = getTheLoginName();

        // GridPane for KYC form fields
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
        gridPane.add(homeButton, 1, 13);
        gridPane.setAlignment(Pos.CENTER);

        // StackPane to contain the logout and home buttons
        StackPane buttonPane = new StackPane(logoutButton);
        buttonPane.setAlignment(Pos.TOP_LEFT);

        VBox logOut = new VBox(buttonPane);
        logOut.setPadding(new Insets(40, 0, 0, 50));
        logOut.setAlignment(Pos.TOP_LEFT);

        // VBox to arrange all UI elements
        VBox mainBox = new VBox(20, heading, message, gridPane);
        mainBox.setAlignment(Pos.CENTER);

        // Create a card-like styling for the mainBox
        // mainBox.getStyleClass().add("card");
        mainBox.setMaxWidth(600);
        mainBox.setMaxHeight(800);
        mainBox.setStyle(
                "-fx-background-color: rgba(240, 240, 240, 0.7);-fx-border-color: #B0B0B0; -fx-border-radius: 20; -fx-background-radius: 20;");

        BorderPane root = new BorderPane();
        root.setCenter(mainBox);
        root.setLeft(logOut);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("file:future_forge\\src\\main\\resources\\sippage\\user.jpeg",
                        Screen.getPrimary().getBounds().getWidth(),
                        Screen.getPrimary().getBounds().getHeight(), false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        root.setBackground(new Background(backgroundImage));

        // Set background image
        // mainBox.setStyle("-fx-background-image: url('background.jpg');
        // -fx-background-size: cover;");

        return root;
    }

    // Method to fetch and display the logged-in user's name
    public Text getTheLoginName() {
        Label dataLabel = new Label();
            try {
                String key = LoginController.key;
                System.out.println("Value of key: " + key);
                DocumentSnapshot dataObject = dataService.getData("users", key);
                String userName = dataObject.getString("username");
                System.out.println("Username fetched: " + userName);
                dataLabel.setText(userName);
            } catch (Exception ex) {
                ex.printStackTrace();
            };

        return new Text("Welcome " + dataLabel.getText());
    }

    // Method to show alerts
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        javafx.application.Application.launch(UserPageApp.class, args);
    }
}

class ValidValueException extends Exception {

    ValidValueException(String str) {

        super(str);
    }

}

class sipValue {

    Double value = 500.00;

    void booking(Double val) throws ValidValueException {

        if (val < value) {
            // showAlert(Alert.AlertType.ERROR, "Input Error", "At least the value must be
            // 500/-");
            throw new ValidValueException("At least the value must be 500/-");
        } else
            System.out.println("Invested successfully");
    }

    public static void showAlert1(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid value");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

// Separate class to launch the application
public class UserPageApp extends javafx.application.Application {

    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        DataService dataService = new DataService(); // Initialize DataService
        UserPage userPage = new UserPage(dataService);
        // VBox mainBox = userPage.createUserScene(() -> System.out.println("Logout
        // action"));
        BorderPane root = userPage.createUserScene(() -> System.out.println("Logout action"));

        Button homeButton = new Button("Home");
        homeButton.getStyleClass().add("home-button");
        homeButton.setPrefWidth(150); // Set preferred width
        homeButton.setPrefHeight(50); // Set preferred height
        homeButton.setPadding(new Insets(40, 50, 0, 0));
        homeButton.setAlignment(Pos.TOP_RIGHT);
        homeButton.setOnAction(e -> {
            // Navigate to HomePage.java
            //Replace with your navigation logic
            HomePage homePage = new HomePage();
            homePage.start(new Stage());
            primaryStage.close();
            //HomePage.getHomePageScene();
            // System.out.println("Navigate to HomePage.java");
        });

        // Button lout = new Button("Home");
        // lout.getStyleClass().add("home-button");
        // lout.setPrefWidth(150); // Set preferred width
        // lout.setPrefHeight(50); // Set preferred height
        // lout.setPadding(new Insets(40, 50, 0, 0));
        // lout.setAlignment(Pos.TOP_RIGHT);
        // lout.setOnAction(e -> {
        //     // Navigate to HomePage.java
        //     //Replace with your navigation logic
        //     LoginController homePage = new LoginController(primaryStage);
        //     homePage.start(new Stage());
        //     primaryStage.close();
        //     //HomePage.getHomePageScene();
        //     // System.out.println("Navigate to HomePage.java");
        // });

        // root.setRight(homeButton);

        Scene scene = new Scene(root, 700, 700);
        // scene.getStylesheets().add("future_forgesrc//main//java//com//invest_elevate//firestore//dashboards//styles.css");
        // // Link to the external CSS file

        primaryStage.setScene(scene);
        primaryStage.setTitle("KYC Page");
        primaryStage.show();
    }
}