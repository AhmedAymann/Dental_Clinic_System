import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Controller {
    private List<Doctor> doctors;
    private List<Patients> patients;
    private List<Receptionist> receptionists;
    private UserManager userManager;
    private FileHandler fileHandler;
    private DentalClinic brightSmileClinic;

    public void initializeData(List<Doctor> doctors, List<Patients> patients, List<Receptionist> receptionists,
                               UserManager userManager, FileHandler fileHandler, DentalClinic brightSmileClinic) {
        this.doctors = doctors;
        this.patients = patients;
        this.receptionists = receptionists;
        this.userManager = userManager;
        this.fileHandler = fileHandler;
        this.brightSmileClinic = brightSmileClinic;
    }

    public void onDoctorButtonClick() {
        loadScene("DoctorMenu.fxml");
    }

    public void onPatientButtonClick() {
        loadScene("PatientMenu.fxml");
    }

    public void onReceptionistButtonClick() {
        loadScene("ReceptionistMenu.fxml");
    }

    public void onExitButtonClick() {
        fileHandler.saveData();
        System.exit(0);
    }

    private void loadScene(String fxmlFileName) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            // Pass shared resources to the new controller
            Controller newController = loader.getController();
            newController.initializeData(doctors, patients, receptionists, userManager, fileHandler, brightSmileClinic);

            // Get the current stage and set the new scene
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}