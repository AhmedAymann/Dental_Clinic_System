//import javafx.fxml.FXML;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ChoiceBox;
//import javafx.event.ActionEvent; // Added for button handling
//import java.util.ArrayList;
//import java.util.List;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import javafx.scene.Node;
//import java.io.IOException;
//
//
//public class Controller {
//
//    // Variables to store user input
//        public String username, password, First_name, Last_name, Email, Mobile_number;
//        public int age, ID;
//        public String gender;
//        public String selectedValue;
//        private static List<Users> userList = new ArrayList<>();
//
//        public ChoiceBox<String> choiceBox;
//
//        // FXML TextField references
//        @FXML
//        private TextField usernameField;
//        @FXML
//        private TextField passwordField;
//        @FXML
//        private TextField firstNameField;
//        @FXML
//        private TextField lastNameField;
//        @FXML
//        private TextField emailField;
//        @FXML
//        private TextField mobileNumberField;
//        @FXML
//        private TextField ageField;
//        @FXML
//        private TextField patientWeightField;
//        @FXML
//        private TextField patientBoodTypeField;
//        @FXML
//        private TextField patientPrescriptionField;
//
//        @FXML
//        private void handleSignUp() {
//            try {
//                // Retrieve text from each TextField and store in variables
//                selectedValue = choiceBox.getValue();
//                username = usernameField.getText();
//                password = passwordField.getText();
//                First_name = firstNameField.getText();
//                Last_name = lastNameField.getText();
//                Email = emailField.getText();
//                Mobile_number = mobileNumberField.getText();
//                age = Integer.parseInt(ageField.getText());
////            ID = Integer.parseInt(idField.getText());
//
//                Users newUser;
//
//                newUser = new Users(username, password, First_name, Last_name, Email, Mobile_number, age, gender);
//
//                // Add the user to the list
//                userList.add(newUser);
//
//                // Save the updated list to the file
//                FileHandler.saveUsersToFile(userList);
//
//                System.out.println("User added successfully: " + newUser);
//
//            } catch (NumberFormatException e) {
//                System.err.println("Invalid input for numeric fields (Age or ID).");
//            } catch (StringIndexOutOfBoundsException e) {
//                System.err.println("Invalid input for Gender (Must be a single character).");
//            }
//        }
//
//        public static List<Users> getUserList() {
//            return userList;
//        }
//
//        // Handling checkboxes selection
//        @FXML
//        private CheckBox checkBox1;
//
//        @FXML
//        private CheckBox checkBox2;
//
////        @FXML
////        private void initialize() {
////            // Add event handlers for each checkbox
////            checkBox1.setOnAction(e -> handleCheckboxSelection(checkBox1));
////            checkBox2.setOnAction(e -> handleCheckboxSelection(checkBox2));
////        }
//
//        private void handleCheckboxSelection(CheckBox selectedCheckBox) {
//            // Uncheck all checkboxes first
//            checkBox1.setSelected(false);
//            checkBox2.setSelected(false);
//            // Check only the selected checkbox
//            selectedCheckBox.setSelected(true);
//            if (selectedCheckBox == checkBox1) {
//                gender = "Male";
//            } else {
//                gender = "Female";
//            }
//        }
//
//        // New Methods for Button Redirection
//        @FXML
//        public void handleDoctorButton(ActionEvent event) {
//            // Redirect to doctorViewSignUp.fxml
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/doctorViewSignUp.fxml"));
//                Parent root = loader.load();
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.setScene(new Scene(root));
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    @FXML
//    public void handleReceptionistButton(ActionEvent event) {
//        // Redirect to receptionistSignUp.fxml
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/receptionistViewSignUp.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void handlePatientButton(ActionEvent event) {
//        // Redirect to patientViewSignUp.fxml
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/patientViewSignUp.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public TextField getPatientPrescriptionField() {
//        return patientPrescriptionField;
//    }
//
//    public void setPatientPrescriptionField(TextField patientPrescriptionField) {
//        this.patientPrescriptionField = patientPrescriptionField;
//    }
//}
