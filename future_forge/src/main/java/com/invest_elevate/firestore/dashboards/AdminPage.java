// package com.invest_elevate.firestore.dashboards;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class AdminPage {

//     private final DataService dataService;
//     private final LoginController loginController;
//     private VBox adminDashboard;

//     public AdminPage(LoginController loginController, DataService dataService) {
//         this.loginController = loginController;
//         this.dataService = dataService;
//     }

//     public VBox createAdminDashboard(Runnable logoutHandler) {
//         VBox vbox = new VBox();
//         vbox.setPadding(new Insets(10));
//         vbox.setSpacing(10);

//         Button logoutButton = new Button("Logout");
//         logoutButton.setOnAction(event -> logoutHandler.run());

//         Button addButton = new Button("Add Project");
//         addButton.setOnAction(event -> handleAdd());

//         adminDashboard = new VBox();
//         adminDashboard.setSpacing(10);
//         adminDashboard.getChildren().addAll(loadProjectDetails());

//         vbox.getChildren().addAll(logoutButton, addButton, adminDashboard);

//         return vbox;
//     }

//     private VBox loadProjectDetails() {
//         VBox vbox = new VBox();
//         vbox.setSpacing(10);
//         try {
//             vbox.getChildren().clear(); // Clear existing cards
//             List<Map<String, Object>> projectDetails = dataService.getDataInDescendingOrder("collectionName", "timestamp");
//             for (Map<String, Object> projectDetail : projectDetails) {
//                 HBox projectCard = createProjectCard(projectDetail);
//                 vbox.getChildren().add(projectCard);
//             }
//         } catch (ExecutionException | InterruptedException ex) {
//             ex.printStackTrace();
//         }
//         return vbox;
//     }

//     private HBox createProjectCard(Map<String, Object> projectDetail) {
//         HBox card = new HBox();
//         card.setPadding(new Insets(10));
//         card.setSpacing(10);
//         card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

//         VBox projectVBox = new VBox();
//         projectVBox.setSpacing(5);

//         VBox membersVBox = new VBox();
//         membersVBox.setSpacing(5);

//         if (projectDetail != null) {
//             if (projectDetail.get("leaderName") != null) {
//                 Text leaderText = new Text("Leader name: ");
//                 leaderText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label leaderLabel = new Label(projectDetail.get("leaderName").toString());
//                 HBox leaderBox = new HBox(leaderText, leaderLabel);
//                 leaderBox.setSpacing(5);
//                 projectVBox.getChildren().add(leaderBox);
//             }
//             if (projectDetail.get("groupName") != null) {
//                 Text groupText = new Text("Group name: ");
//                 groupText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label groupLabel = new Label(projectDetail.get("groupName").toString());
//                 HBox groupBox = new HBox(groupText, groupLabel);
//                 groupBox.setSpacing(5);
//                 projectVBox.getChildren().add(groupBox);
//             }
//             if (projectDetail.get("projectName") != null) {
//                 Text projectText = new Text("Project: ");
//                 projectText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label projectLabel = new Label(projectDetail.get("projectName").toString());
//                 HBox projectBox = new HBox(projectText, projectLabel);
//                 projectBox.setSpacing(5);
//                 projectVBox.getChildren().add(projectBox);
//             }
//             for (int i = 1; i <= 4; i++) {
//                 String memberKey = "member" + i;
//                 if (projectDetail.get(memberKey) != null) {
//                     Text memberText = new Text("Member " + i + ": ");
//                     memberText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                     Label memberLabel = new Label(projectDetail.get(memberKey).toString());
//                     HBox memberBox = new HBox(memberText, memberLabel);
//                     memberBox.setSpacing(5);
//                     membersVBox.getChildren().add(memberBox);
//                 }
//             }
//         }

//         Button editButton = new Button("Edit");
//         Button deleteButton = new Button("Delete");

//         editButton.setOnAction(event -> handleEdit(projectDetail));
//         deleteButton.setOnAction(event -> handleDelete(projectDetail, card));

//         VBox buttonsVBox = new VBox(5, editButton, deleteButton);

//         card.getChildren().addAll(projectVBox, membersVBox, buttonsVBox);
//         return card;
//     }

//     private void handleEdit(Map<String, Object> projectDetail) {
//         if (loginController != null) {
//             VBox editScene = createUserScene(projectDetail, () -> {
//                 adminDashboard.getChildren().clear();
//                 adminDashboard.getChildren().addAll(loadProjectDetails());
//                 loginController.returnToAdminView();
//             });
//             loginController.setPrimaryStage(new Scene(editScene, 700, 700));
//         } else {
//             System.err.println("Login controller is not initialized.");
//         }
//     }

//     private void handleDelete(Map<String, Object> projectDetail, HBox card) {
//         try {
//             dataService.deleteProject("collectionName", projectDetail.get("leaderName").toString());
//             VBox parentVBox = (VBox) card.getParent();
//             parentVBox.getChildren().remove(card);
//         } catch (ExecutionException | InterruptedException ex) {
//             ex.printStackTrace();
//         }
//     }

//     private VBox createUserScene(Map<String, Object> projectDetail, Runnable returnToAdminView) {
//         Label groupLabel = new Label("Enter group name:");
//         groupLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField groupName = new TextField((String) projectDetail.getOrDefault("groupName", ""));
//         groupName.setPromptText("Group name");
//         VBox groupBox = new VBox(10, groupLabel, groupName);
//         groupBox.setMaxSize(300, 20);

//         Label projectLabel = new Label("Enter project name:");
//         projectLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField projectName = new TextField((String) projectDetail.getOrDefault("projectName", ""));
//         projectName.setPromptText("Project name");
//         VBox projectBox = new VBox(10, projectLabel, projectName);
//         projectBox.setMaxSize(300, 20);

//         Label mobileNumber = new Label("Enter mobile number:");
//         mobileNumber.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField mobTextField = new TextField((String) projectDetail.getOrDefault("mobileNum", ""));
//         mobTextField.setPromptText("Mobile number");
//         VBox mobBox = new VBox(10, mobileNumber, mobTextField);
//         mobBox.setMaxSize(300, 20);

//         Label leaderLabel = new Label("Enter leader name:");
//         leaderLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField leaderName = new TextField((String) projectDetail.getOrDefault("leaderName", ""));
//         leaderName.setPromptText("Leader name");
//         VBox leaderNameBox = new VBox(10, leaderLabel, leaderName);
//         leaderNameBox.setMaxSize(300, 20);

//         Label memberLabel1 = new Label("Enter member 1 name:");
//         memberLabel1.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member1 = new TextField((String) projectDetail.getOrDefault("member1", ""));
//         member1.setPromptText("Member 1 name");
//         VBox mem1Box = new VBox(10, memberLabel1, member1);
//         mem1Box.setMaxSize(300, 20);

//         Label memberLabel2 = new Label("Enter member 2 name:");
//         memberLabel2.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member2 = new TextField((String) projectDetail.getOrDefault("member2", ""));
//         member2.setPromptText("Member 2 name");
//         VBox mem2Box = new VBox(10, memberLabel2, member2);
//         mem2Box.setMaxSize(300, 20);

//         Label memberLabel3 = new Label("Enter member 3 name:");
//         memberLabel3.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField member3 = new TextField((String) projectDetail.getOrDefault("member3", ""));
//         member3.setPromptText("Member 3 name");
//         VBox mem3Box = new VBox(10, memberLabel3, member3);
//         mem3Box.setMaxSize(300, 20);

//         Button updateButton = new Button("Update Data");
//         updateButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         HBox buttonBox = new HBox(updateButton);
//         buttonBox.setAlignment(Pos.CENTER);

//         Button returnButton = new Button("Return to Admin View");
//         returnButton.setOnAction(event -> returnToAdminView.run());

//         VBox dataBox = new VBox(25, groupBox, projectBox, mobBox, leaderNameBox, mem1Box, mem2Box, mem3Box, buttonBox, returnButton);
//         dataBox.setAlignment(Pos.CENTER);

//         updateButton.setOnAction(event -> {
//             Map<String, Object> updatedData = new HashMap<>();
//             updatedData.put("groupName", groupName.getText());
//             updatedData.put("projectName", projectName.getText());
//             updatedData.put("leaderName", leaderName.getText());
//             updatedData.put("mobileNum", mobTextField.getText());
//             updatedData.put("member1", member1.getText());
//             updatedData.put("member2", member2.getText());
//             updatedData.put("member3", member3.getText());
//             updatedData.put("timestamp", LocalDateTime.now());

//             try {
//                 dataService.updateData("collectionName", projectDetail.get("leaderName").toString(), updatedData);
//             } catch (ExecutionException | InterruptedException ex) {
//                 ex.printStackTrace();
//             }

//             adminDashboard.getChildren().clear();
//             adminDashboard.getChildren().addAll(loadProjectDetails());

//             returnToAdminView.run();
//         });

//         return dataBox;
//     }

//     private void handleAdd() {
//         VBox addScene = createUserScene(new HashMap<>(), () -> {
//             adminDashboard.getChildren().clear();
//             adminDashboard.getChildren().addAll(loadProjectDetails());
//             loginController.returnToAdminView();
//         });
//         loginController.setPrimaryStage(new Scene(addScene, 700, 700));
//     }
// }

// package com.invest_elevate.firestore.dashboards;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.geometry.Pos;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;

// public class AdminPage {

//     private DataService dataService; // DataService for Firestore operations
//     private LoginController loginController; // LoginController for user management
//     private Label dataMsg; // Label to display status messages

//     public AdminPage(LoginController loginController, DataService dataService) {
//         this.loginController = loginController;
//         this.dataService = dataService;
//     }

//     // Method to create and return the admin interface VBox for project data entry
//     public VBox createAdminDashboard(Runnable logoutHandler) {
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
//         Label totalAmountValue = new Label();
//         VBox totalAmountBox = new VBox(10, totalAmountLabel, totalAmountValue);
//         totalAmountBox.setMaxSize(300, 20);

//         Button calculateTotalButton = new Button("Calculate Total");
//         calculateTotalButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
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

//         ObservableList<String> sipOptions = FXCollections.observableArrayList(
//                 "Nifty Top 10", "Bank Nifty", "IT Sector", "Pharmaceutical Sector");
//         ComboBox<String> sipComboBox = new ComboBox<>(sipOptions);

//         Label interestRateLabel = new Label("Interest Rate (%):");
//         Label interestRateValue = new Label(); // To display selected interest rate
//         VBox interestRateBox = new VBox(10, interestRateLabel, interestRateValue);
//         interestRateBox.setMaxSize(300, 20);

//         Label sipReturnLabel = new Label("SIP Return:");
//         Label sipReturnValue = new Label();
//         VBox sipReturnBox = new VBox(10, sipReturnLabel, sipReturnValue);
//         sipReturnBox.setMaxSize(300, 20);

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
//                 if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || panField.getText().isEmpty()
//                         || mobileField.getText().isEmpty() || amountPerMonthField.getText().isEmpty()
//                         || noOfYearsField.getText().isEmpty()) {
//                     showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
//                     return;
//                 } else if (!emailField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
//                     showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid email");
//                     return;
//                 } else if (!mobileField.getText().matches("\\d{10}")) {
//                     showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid 10-digit mobile number");
//                     return;
//                 } else if (!panField.getText().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
//                     showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a valid PAN number");
//                     return;
//                 }

//                 // Create a map with project data
//                 Map<String, Object> data = new HashMap<>();
//                 data.put("name", nameField.getText());
//                 data.put("email", emailField.getText());
//                 data.put("panNo", panField.getText());
//                 data.put("mobileNo", mobileField.getText());
//                 data.put("sipAmount", amountPerMonthField.getText());
//                 data.put("investmentPeriod", noOfYearsField.getText());
//                 data.put("totalAmount", totalAmountValue.getText());
//                 data.put("timestamp", LocalDateTime.now());

//                 try {
//                     // Attempt to add data to Firestore
//                     dataService.addData("collectionName", emailField.getText(), data);
//                     dataMsg.setText("Data added successfully"); // Update status message
//                     // Clear input fields after successful addition
//                     nameField.clear();
//                     emailField.clear();
//                     panField.clear();
//                     mobileField.clear();
//                     amountPerMonthField.clear();
//                     noOfYearsField.clear();
//                     totalAmountValue.setText("");
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

//         // VBox for arranging UI elements
//         VBox vButton = new VBox(logoutButton);
//         vButton.setAlignment(Pos.TOP_LEFT);
//         VBox adminBox = new VBox(25, vButton, nameBox, emailBox, panBox, mobileBox, amountPerMonthBox,
//                 noOfYearsBox, totalAmountBox, calculateTotalButton, sipComboBox, interestRateBox,
//                 sipReturnBox, buttonBox, dataMsg);
//         adminBox.setAlignment(Pos.CENTER);
//         return adminBox;
//     }

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
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.ExecutionException;

// import com.invest_elevate.firestore.controller.LoginController;
// import com.invest_elevate.firestore.firebaseConfig.DataService;

// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;

// public class AdminPage {

//     private final DataService dataService;
//     private final LoginController loginController;
//     private VBox adminDashboard;

//     public AdminPage(LoginController loginController, DataService dataService) {
//         this.loginController = loginController;
//         this.dataService = dataService;
//     }

//     public VBox createAdminDashboard(Runnable logoutHandler) {
//         VBox vbox = new VBox();
//         vbox.setPadding(new Insets(10));
//         vbox.setSpacing(10);

//         Button logoutButton = new Button("Logout");
//         logoutButton.setOnAction(event -> logoutHandler.run());

//         Button addButton = new Button("Add User");
//         addButton.setOnAction(event -> handleAdd());

//         adminDashboard = new VBox();
//         adminDashboard.setSpacing(10);
//         adminDashboard.getChildren().addAll(loadUserDetails());

//         vbox.getChildren().addAll(logoutButton, addButton, adminDashboard);

//         return vbox;
//     }

//     private VBox loadUserDetails() {
//         VBox vbox = new VBox();
//         vbox.setSpacing(10);
//         try {
//             vbox.getChildren().clear(); // Clear existing cards
//             List<Map<String, Object>> userDetails = dataService.getDataInDescendingOrder("users", "timestamp");
//             for (Map<String, Object> userDetail : userDetails) {
//                 HBox userCard = createUserCard(userDetail);
//                 vbox.getChildren().add(userCard);
//             }
//         } catch (ExecutionException | InterruptedException ex) {
//             ex.printStackTrace();
//         }
//         return vbox;
//     }

//     private HBox createUserCard(Map<String, Object> userDetail) {
//         HBox card = new HBox();
//         card.setPadding(new Insets(10));
//         card.setSpacing(10);
//         card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

//         VBox userVBox = new VBox();
//         userVBox.setSpacing(5);

//         if (userDetail != null) {
//             if (userDetail.get("name") != null) {
//                 Text nameText = new Text("Name: ");
//                 nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label nameLabel = new Label(userDetail.get("name").toString());
//                 HBox nameBox = new HBox(nameText, nameLabel);
//                 nameBox.setSpacing(5);
//                 userVBox.getChildren().add(nameBox);
//             }
//             if (userDetail.get("email") != null) {
//                 Text emailText = new Text("Email: ");
//                 emailText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label emailLabel = new Label(userDetail.get("email").toString());
//                 HBox emailBox = new HBox(emailText, emailLabel);
//                 emailBox.setSpacing(5);
//                 userVBox.getChildren().add(emailBox);
//             }
//             if (userDetail.get("pan") != null) {
//                 Text panText = new Text("PAN No: ");
//                 panText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label panLabel = new Label(userDetail.get("pan").toString());
//                 HBox panBox = new HBox(panText, panLabel);
//                 panBox.setSpacing(5);
//                 userVBox.getChildren().add(panBox);
//             }
//             if (userDetail.get("mobile") != null) {
//                 Text mobileText = new Text("Mobile No: ");
//                 mobileText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label mobileLabel = new Label(userDetail.get("mobile").toString());
//                 HBox mobileBox = new HBox(mobileText, mobileLabel);
//                 mobileBox.setSpacing(5);
//                 userVBox.getChildren().add(mobileBox);
//             }
//             if (userDetail.get("sipAmount") != null) {
//                 Text amountText = new Text("SIP Amount: ");
//                 amountText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label amountLabel = new Label(userDetail.get("sipAmount").toString());
//                 HBox amountBox = new HBox(amountText, amountLabel);
//                 amountBox.setSpacing(5);
//                 userVBox.getChildren().add(amountBox);
//             }
//             if (userDetail.get("interestRate") != null) {
//                 Text interestText = new Text("Interest Rate: ");
//                 interestText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label interestLabel = new Label(userDetail.get("interestRate").toString());
//                 HBox interestBox = new HBox(interestText, interestLabel);
//                 interestBox.setSpacing(5);
//                 userVBox.getChildren().add(interestBox);
//             }
//             if (userDetail.get("investmentPeriod") != null) {
//                 Text periodText = new Text("Investment Period: ");
//                 periodText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label periodLabel = new Label(userDetail.get("investmentPeriod").toString());
//                 HBox periodBox = new HBox(periodText, periodLabel);
//                 periodBox.setSpacing(5);
//                 userVBox.getChildren().add(periodBox);
//             }
//             if (userDetail.get("totalAmount") != null) {
//                 Text totalText = new Text("Total Amount: ");
//                 totalText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label totalLabel = new Label(userDetail.get("totalAmount").toString());
//                 HBox totalBox = new HBox(totalText, totalLabel);
//                 totalBox.setSpacing(5);
//                 userVBox.getChildren().add(totalBox);
//             }
//             if (userDetail.get("paymentMethod") != null) {
//                 Text paymentText = new Text("Payment Method: ");
//                 paymentText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
//                 Label paymentLabel = new Label(userDetail.get("paymentMethod").toString());
//                 HBox paymentBox = new HBox(paymentText, paymentLabel);
//                 paymentBox.setSpacing(5);
//                 userVBox.getChildren().add(paymentBox);
//             }
//         }

//         Button editButton = new Button("Edit");
//         Button deleteButton = new Button("Delete");

//         editButton.setOnAction(event -> handleEdit(userDetail));
//         deleteButton.setOnAction(event -> handleDelete(userDetail, card));

//         VBox buttonsVBox = new VBox(5, editButton, deleteButton);

//         card.getChildren().addAll(userVBox, buttonsVBox);
//         return card;
//     }

//     private void handleEdit(Map<String, Object> userDetail) {
//         if (loginController != null) {
//             VBox editScene = createUserScene(userDetail, () -> {
//                 adminDashboard.getChildren().clear();
//                 adminDashboard.getChildren().addAll(loadUserDetails());
//                 loginController.returnToAdminView();
//             });
//             loginController.setPrimaryStage(new Scene(editScene, 700, 700));
//         } else {
//             System.err.println("Login controller is not initialized.");
//         }
//     }

//     private void handleDelete(Map<String, Object> userDetail, HBox card) {
//         try {
//             dataService.deleteProject("users", userDetail.get("name").toString());
//             VBox parentVBox = (VBox) card.getParent();
//             parentVBox.getChildren().remove(card);
//         } catch (ExecutionException | InterruptedException ex) {
//             ex.printStackTrace();
//         }
//     }

//     private VBox createUserScene(Map<String, Object> userDetail, Runnable returnToAdminView) {
//         Label nameLabel = new Label("Enter name:");
//         nameLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField nameField = new TextField((String) userDetail.getOrDefault("name", ""));
//         nameField.setPromptText("Name");
//         VBox nameBox = new VBox(10, nameLabel, nameField);
//         nameBox.setMaxSize(300, 20);

//         Label emailLabel = new Label("Enter email:");
//         emailLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField emailField = new TextField((String) userDetail.getOrDefault("email", ""));
//         emailField.setPromptText("Email");
//         VBox emailBox = new VBox(10, emailLabel, emailField);
//         emailBox.setMaxSize(300, 20);

//         Label panLabel = new Label("Enter PAN No:");
//         panLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField panField = new TextField((String) userDetail.getOrDefault("pan", ""));
//         panField.setPromptText("PAN No");
//         VBox panBox = new VBox(10, panLabel, panField);
//         panBox.setMaxSize(300, 20);

//         Label mobileLabel = new Label("Enter mobile number:");
//         mobileLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField mobileField = new TextField((String) userDetail.getOrDefault("mobile", ""));
//         mobileField.setPromptText("Mobile number");
//         VBox mobileBox = new VBox(10, mobileLabel, mobileField);
//         mobileBox.setMaxSize(300, 20);

//         Label amountLabel = new Label("Enter SIP amount:");
//         amountLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField amountField = new TextField((String) userDetail.getOrDefault("sipAmount", ""));
//         amountField.setPromptText("SIP amount");
//         VBox amountBox = new VBox(10, amountLabel, amountField);
//         amountBox.setMaxSize(300, 20);

//         Label interestLabel = new Label("Enter interest rate:");
//         interestLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField interestField = new TextField((String) userDetail.getOrDefault("interestRate", ""));
//         interestField.setPromptText("Interest rate");
//         VBox interestBox = new VBox(10, interestLabel, interestField);
//         interestBox.setMaxSize(300, 20);

//         Label periodLabel = new Label("Enter investment period:");
//         periodLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField periodField = new TextField((String) userDetail.getOrDefault("investmentPeriod", ""));
//         periodField.setPromptText("Investment period");
//         VBox periodBox = new VBox(10, periodLabel, periodField);
//         periodBox.setMaxSize(300, 20);

//         Label totalLabel = new Label("Enter total amount:");
//         totalLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField totalField = new TextField((String) userDetail.getOrDefault("totalAmount", ""));
//         totalField.setPromptText("Total amount");
//         VBox totalBox = new VBox(10, totalLabel, totalField);
//         totalBox.setMaxSize(300, 20);

//         Label paymentLabel = new Label("Enter payment method:");
//         paymentLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         TextField paymentField = new TextField((String) userDetail.getOrDefault("paymentMethod", ""));
//         paymentField.setPromptText("Payment method");
//         VBox paymentBox = new VBox(10, paymentLabel, paymentField);
//         paymentBox.setMaxSize(300, 20);

//         Button updateButton = new Button("Update Data");
//         updateButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
//         HBox buttonBox = new HBox(updateButton);
//         buttonBox.setAlignment(Pos.CENTER);

//         Button returnButton = new Button("Return to Admin View");
//         returnButton.setOnAction(event -> returnToAdminView.run());

//         VBox dataBox = new VBox(25, nameBox, emailBox, panBox, mobileBox, amountBox, interestBox, periodBox, totalBox, paymentBox, buttonBox, returnButton);
//         dataBox.setAlignment(Pos.CENTER);

//         updateButton.setOnAction(event -> {
//             Map<String, Object> updatedData = new HashMap<>();
//             updatedData.put("name", nameField.getText());
//             updatedData.put("email", emailField.getText());
//             updatedData.put("pan", panField.getText());
//             updatedData.put("mobile", mobileField.getText());
//             updatedData.put("sipAmount", amountField.getText());
//             updatedData.put("interestRate", interestField.getText());
//             updatedData.put("investmentPeriod", periodField.getText());
//             updatedData.put("totalAmount", totalField.getText());
//             updatedData.put("paymentMethod", paymentField.getText());
//             updatedData.put("timestamp", LocalDateTime.now());

//             try {
//                 dataService.updateData("users", userDetail.get("name").toString(), updatedData);
//             } catch (ExecutionException | InterruptedException ex) {
//                 ex.printStackTrace();
//             }

//             adminDashboard.getChildren().clear();
//             adminDashboard.getChildren().addAll(loadUserDetails());

//             returnToAdminView.run();
//         });

//         return dataBox;
//     }

//     private void handleAdd() {
//         VBox addScene = createUserScene(new HashMap<>(), () -> {
//             adminDashboard.getChildren().clear();
//             adminDashboard.getChildren().addAll(loadUserDetails());
//             loginController.returnToAdminView();
//         });
//         loginController.setPrimaryStage(new Scene(addScene, 700, 700));
//     }
    
// }

package com.invest_elevate.firestore.dashboards;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.invest_elevate.firestore.controller.LoginController;
import com.invest_elevate.firestore.firebaseConfig.DataService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AdminPage {

    private final DataService dataService;
    private final LoginController loginController;
    private VBox adminDashboard;

    public AdminPage(LoginController loginController, DataService dataService) {
        this.loginController = loginController;
        this.dataService = dataService;
    }

    public VBox createAdminDashboard(Runnable logoutHandler) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> logoutHandler.run());

        Button addButton = new Button("Add User");
        addButton.setOnAction(event -> handleAdd());

        adminDashboard = new VBox();
        adminDashboard.setSpacing(10);
        adminDashboard.getChildren().addAll(loadUserDetails());

        vbox.getChildren().addAll(logoutButton, addButton, adminDashboard);

        return vbox;
    }

    private VBox loadUserDetails() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        try {
            vbox.getChildren().clear(); // Clear existing cards
            List<Map<String, Object>> userDetails = dataService.getDataInDescendingOrder("users", "timestamp");
            for (Map<String, Object> userDetail : userDetails) {
                HBox userCard = createUserCard(userDetail);
                vbox.getChildren().add(userCard);
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return vbox;
    }

    private HBox createUserCard(Map<String, Object> userDetail) {
        HBox card = new HBox();
        card.setPadding(new Insets(10));
        card.setSpacing(10);
        card.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

        VBox userVBox = new VBox();
        userVBox.setSpacing(5);

        VBox membersVBox = new VBox();
        membersVBox.setSpacing(5);

        if (userDetail != null) {
            if (userDetail.get("name") != null) {
                Text nameText = new Text("Name: ");
                nameText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label nameLabel = new Label(userDetail.get("name").toString());
                HBox nameBox = new HBox(nameText, nameLabel);
                nameBox.setSpacing(5);
                userVBox.getChildren().add(nameBox);
            }
            if (userDetail.get("email") != null) {
                Text emailText = new Text("Email: ");
                emailText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label emailLabel = new Label(userDetail.get("email").toString());
                HBox emailBox = new HBox(emailText, emailLabel);
                emailBox.setSpacing(5);
                userVBox.getChildren().add(emailBox);
            }
            if (userDetail.get("pan") != null) {
                Text panText = new Text("PAN No: ");
                panText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label panLabel = new Label(userDetail.get("pan").toString());
                HBox panBox = new HBox(panText, panLabel);
                panBox.setSpacing(5);
                userVBox.getChildren().add(panBox);
            }
            if (userDetail.get("mobile") != null) {
                Text mobileText = new Text("Mobile No: ");
                mobileText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label mobileLabel = new Label(userDetail.get("mobile").toString());
                HBox mobileBox = new HBox(mobileText, mobileLabel);
                mobileBox.setSpacing(5);
                userVBox.getChildren().add(mobileBox);
            }
            if (userDetail.get("sipAmount") != null) {
                Text amountText = new Text("SIP Amount: ");
                amountText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label amountLabel = new Label(userDetail.get("sipAmount").toString());
                HBox amountBox = new HBox(amountText, amountLabel);
                amountBox.setSpacing(5);
                userVBox.getChildren().add(amountBox);
            }
            if (userDetail.get("interestRate") != null) {
                Text interestText = new Text("Interest Rate: ");
                interestText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label interestLabel = new Label(userDetail.get("interestRate").toString());
                HBox interestBox = new HBox(interestText, interestLabel);
                interestBox.setSpacing(5);
                userVBox.getChildren().add(interestBox);
            }
            if (userDetail.get("investmentPeriod") != null) {
                Text periodText = new Text("Investment Period: ");
                periodText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label periodLabel = new Label(userDetail.get("investmentPeriod").toString());
                HBox periodBox = new HBox(periodText, periodLabel);
                periodBox.setSpacing(5);
                userVBox.getChildren().add(periodBox);
            }
            if (userDetail.get("totalAmount") != null) {
                Text totalText = new Text("Total Amount: ");
                totalText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label totalLabel = new Label(userDetail.get("totalAmount").toString());
                HBox totalBox = new HBox(totalText, totalLabel);
                totalBox.setSpacing(5);
                userVBox.getChildren().add(totalBox);
            }
            if (userDetail.get("paymentMethod") != null) {
                Text paymentText = new Text("Payment Method: ");
                paymentText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                Label paymentLabel = new Label(userDetail.get("paymentMethod").toString());
                HBox paymentBox = new HBox(paymentText, paymentLabel);
                paymentBox.setSpacing(5);
                userVBox.getChildren().add(paymentBox);
            }
            for (int i = 1; i <= 15; i++) {
                String memberKey = "member" + i;
                if (userDetail.get(memberKey) != null) {
                    Text memberText = new Text("Member " + i + ": ");
                    memberText.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
                    Label memberLabel = new Label(userDetail.get(memberKey).toString());
                    HBox memberBox = new HBox(memberText, memberLabel);
                    memberBox.setSpacing(5);
                    membersVBox.getChildren().add(memberBox);
                }
            }
        }

        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        editButton.setOnAction(event -> handleEdit(userDetail));
        deleteButton.setOnAction(event -> handleDelete(userDetail, card));

        VBox buttonsVBox = new VBox(5, editButton, deleteButton);

        card.getChildren().addAll(userVBox, buttonsVBox);
        return card;
    }

    private void handleEdit(Map<String, Object> userDetail) {
        if (loginController != null) {
            VBox editScene = createUserScene(userDetail, () -> {
                adminDashboard.getChildren().clear();
                adminDashboard.getChildren().addAll(loadUserDetails());
                loginController.returnToAdminView();
            });
            loginController.setPrimaryStage(new Scene(editScene, 700, 700));
        } else {
            System.err.println("Login controller is not initialized.");
        }
    }

    private void handleDelete(Map<String, Object> userDetail, HBox card) {
        try {
            dataService.deleteProject("users", userDetail.get("name").toString());
            VBox parentVBox = (VBox) card.getParent();
            parentVBox.getChildren().remove(card);
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    
    private VBox createUserScene(Map<String, Object> userDetail, Runnable returnToAdminView) {
        Label nameLabel = new Label("Enter name:");
        nameLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField nameField = new TextField((String) userDetail.getOrDefault("name", ""));
        nameField.setPromptText("Name");
        VBox nameBox = new VBox(10, nameLabel, nameField);
        nameBox.setMaxSize(300, 20);

        Label emailLabel = new Label("Enter email:");
        emailLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField emailField = new TextField((String) userDetail.getOrDefault("email", ""));
        emailField.setPromptText("Email");
        VBox emailBox = new VBox(10, emailLabel, emailField);
        emailBox.setMaxSize(300, 20);

        Label panLabel = new Label("Enter PAN No:");
        panLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField panField = new TextField((String) userDetail.getOrDefault("pan", ""));
        panField.setPromptText("PAN No");
        VBox panBox = new VBox(10, panLabel, panField);
        panBox.setMaxSize(300, 20);

        Label mobileLabel = new Label("Enter mobile number:");
        mobileLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField mobileField = new TextField((String) userDetail.getOrDefault("mobile", ""));
        mobileField.setPromptText("Mobile number");
        VBox mobileBox = new VBox(10, mobileLabel, mobileField);
        mobileBox.setMaxSize(300, 20);

        Label amountLabel = new Label("Enter SIP amount:");
        amountLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField amountField = new TextField((String) userDetail.getOrDefault("sipAmount", ""));
        amountField.setPromptText("SIP amount");
        VBox amountBox = new VBox(10, amountLabel, amountField);
        amountBox.setMaxSize(300, 20);

        Label interestLabel = new Label("Enter interest rate:");
        interestLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField interestField = new TextField((String) userDetail.getOrDefault("interestRate", ""));
        interestField.setPromptText("Interest rate");
        VBox interestBox = new VBox(10, interestLabel, interestField);
        interestBox.setMaxSize(300, 20);

        Label periodLabel = new Label("Enter investment period:");
        periodLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField periodField = new TextField((String) userDetail.getOrDefault("investmentPeriod", ""));
        periodField.setPromptText("Investment period");
        VBox periodBox = new VBox(10, periodLabel, periodField);
        periodBox.setMaxSize(300, 20);

        Label totalLabel = new Label("Enter total amount:");
        totalLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField totalField = new TextField((String) userDetail.getOrDefault("totalAmount", ""));
        totalField.setPromptText("Total amount");
        VBox totalBox = new VBox(10, totalLabel, totalField);
        totalBox.setMaxSize(300, 20);

        Label paymentLabel = new Label("Enter payment method:");
        paymentLabel.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        TextField paymentField = new TextField((String) userDetail.getOrDefault("paymentMethod", ""));
        paymentField.setPromptText("Payment method");
        VBox paymentBox = new VBox(10, paymentLabel, paymentField);
        paymentBox.setMaxSize(300, 20);

        Button updateButton = new Button("Update Data");
        updateButton.setStyle("-fx-font-size:12;-fx-font-weight:bold");
        HBox buttonBox = new HBox(updateButton);
        buttonBox.setAlignment(Pos.CENTER);

        Button returnButton = new Button("Return to Admin View");
        returnButton.setOnAction(event -> returnToAdminView.run());

        VBox dataBox = new VBox(25, nameBox, emailBox, panBox, mobileBox, amountBox, interestBox, periodBox, totalBox, paymentBox, buttonBox, returnButton);
        dataBox.setAlignment(Pos.CENTER);

        updateButton.setOnAction(event -> {
            Map<String, Object> updatedData = new HashMap<>();
            updatedData.put("name", nameField.getText());
            updatedData.put("email", emailField.getText());
            updatedData.put("pan", panField.getText());
            updatedData.put("mobile", mobileField.getText());
            updatedData.put("sipAmount", amountField.getText());
            updatedData.put("interestRate", interestField.getText());
            updatedData.put("investmentPeriod", periodField.getText());
            updatedData.put("totalAmount", totalField.getText());
            updatedData.put("paymentMethod", paymentField.getText());
            updatedData.put("timestamp", LocalDateTime.now());

            try {
                dataService.updateData("users", userDetail.get("name").toString(), updatedData);
            } catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }

            adminDashboard.getChildren().clear();
            adminDashboard.getChildren().addAll(loadUserDetails());

            returnToAdminView.run();
        });

        return dataBox;
    }

    private void handleAdd() {
        VBox addScene = createUserScene(new HashMap<>(), () -> {
            adminDashboard.getChildren().clear();
            adminDashboard.getChildren().addAll(loadUserDetails());
            loginController.returnToAdminView();
        });
        loginController.setPrimaryStage(new Scene(addScene, 700, 700));
    }
}
