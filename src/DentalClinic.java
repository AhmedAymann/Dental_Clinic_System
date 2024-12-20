import java.util.ArrayList;
import java.util.List;

public class DentalClinic{
    String name;
    String Location;
    String Services;
    int RentPrice;
    List<Doctor> doctorList = new ArrayList<>();
    List<Patients> patientsList = new ArrayList<>();
    List<Receptionist> receptionistList = new ArrayList<>();

    public DentalClinic(String Location, String name, String serv, int Rent){
        this.name=name;
        this.Location=Location;
        this.Services=serv;
        this.RentPrice=Rent;
    }
    public DentalClinic(){
    }
}
