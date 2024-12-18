// Main.java rewritten for JavaFX GUI integration

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    // Lists to store clinic data
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patients> patients = new ArrayList<>();
    private static List<Receptionist> receptionists = new ArrayList<>();
    private static DentalClinic brightSmileClinic;
    private static UserManager userManager;
    private static FileHandler fileHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize shared resources
        userManager = new UserManager();
        fileHandler = new FileHandler(doctors, patients, receptionists);
        brightSmileClinic = new DentalClinic("New Cairo", doctors, patients, receptionists);

        // Load the Main Menu FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("typePicker.fxml"));
        Parent root = loader.load();

        // Pass shared resources to the controller
        Controller controller = loader.getController();
        controller.initializeData(doctors, patients, receptionists, userManager, fileHandler, brightSmileClinic);

        // Set up the primary stage
        primaryStage.setTitle("Bright Smile Clinic");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
