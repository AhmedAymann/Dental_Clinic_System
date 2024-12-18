import java.util.ArrayList;
import java.util.List;

public class DentalClinic{
    String Location;
    List<String>Services;
    List<Integer> prices;
    List<Doctor> doctorList;
    List<Patients> patientsList;
    List<Receptionist> receptionistList;

    public DentalClinic(String Location,List<Doctor> doctors,List<Patients> patients, List<Receptionist> receptionists){
        this.Location=Location;
        this.doctorList=doctors;
        this.patientsList=patients;
        this.receptionistList=receptionists;
    }

}
