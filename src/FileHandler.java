import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    private static final String DOCTOR_FILE = "doctors.csv";
    private static final String PATIENT_FILE = "patients.csv";
    private static final String RECEPTIONIST_FILE = "receptionists.csv";
    private static final String APPOINTMENT_FILE = "appointments.csv";


    private List<Doctor> doctors;
    private List<Patients> patients;
    private List<Receptionist> receptionists;

    public FileHandler() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        receptionists = new ArrayList<>();
        loadData();
    }

    public FileHandler(List<Doctor> doctors, List<Patients> patients, List<Receptionist> receptionists) {
        this.doctors = doctors;
        this.patients = patients;
        this.receptionists = receptionists;
        loadData();

    }

    public void delete() {
        try {
            Files.delete(Path.of("receptionists.csv"));
        } catch (IOException e) {
            System.out.println("Error deleting receptionists file: " + e.getMessage());
        }

        try {
            Files.delete(Path.of("doctors.csv"));
        } catch (IOException e) {
            System.out.println("Error deleting doctors file: " + e.getMessage());
        }

        try {
            Files.delete(Path.of("patients.csv"));
        } catch (IOException e) {
            System.out.println("Error deleting patients file: " + e.getMessage());
        }

        try {
            Files.delete(Path.of("appointments.csv"));
        } catch (IOException e) {
            System.out.println("Error deleting patients file: " + e.getMessage());
        }
    }


    // Load data from CSV files
    private void loadData() {
        loadDoctors();
        loadPatients();
        loadReceptionists();
        loadAppointments();
    }

    private void loadDoctors() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = null;
                String First_name = fields[0];
                String Last_name = fields[1];
                String Email = fields[2];
                int age = Integer.parseInt(fields[3]);
                String gender = fields[4];
                String Mobile_number = fields[5];
                String password = fields[6];
                String specialization = fields[7];
                int app_price = Integer.parseInt(fields[8]);
                doctors.add(new Doctor(username, password, First_name, Last_name, Email, Mobile_number, age, gender, specialization, app_price));
            }
        } catch (IOException e) {
            System.out.println("Error loading doctors data: " + e.getMessage());
        }
    }

    private void loadPatients() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = null;
                String First_name = fields[0];
                String Last_name = fields[1];
                String Email = fields[2];
                int age = Integer.parseInt(fields[3]);
                String gender = fields[4];
                String Mobile_number = fields[5];
                String password = fields[6];
                int weight = Integer.parseInt(fields[7]);
                int height = Integer.parseInt(fields[8]);
                String blood_type = fields[9];
                int id = Integer.parseInt(fields[10]);
                List<String> history = getStrings(fields);
                patients.add(new Patients(username, password, First_name, Last_name, Email, Mobile_number, age, gender, weight, height, blood_type, id, history));
            }
        } catch (IOException e) {
            System.out.println("Error loading patients data: " + e.getMessage());
        }
    }

    private static List<String> getStrings(String[] fields) {
        String historyField = fields[11].trim();
        List<String> history = new ArrayList<>();
        if (historyField.startsWith("[") && historyField.endsWith("]") && !historyField.equals("[]")) {
            // Remove the brackets and split by commas
            String[] historyItems = historyField.substring(1, historyField.length() - 1).split(",");
            for (String item : historyItems) {
                history.add(item.trim());
            }
        } else {
            history = null;
        }
        return history;
    }

    private void loadReceptionists() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RECEPTIONIST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String username = null;
                String First_name = fields[0];
                String Last_name = fields[1];
                String Email = fields[2];
                int age = Integer.parseInt(fields[3]);
                String gender = fields[4];
                String Mobile_number = fields[5];
                String password = fields[6];
                receptionists.add(new Receptionist(username, password, First_name, Last_name, Email, Mobile_number, age, gender));
            }
        } catch (IOException e) {
            System.out.println("Error loading receptionists data: " + e.getMessage());
        }
    }

    private void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(APPOINTMENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 4) continue;

                String fname = fields[0];
                String day = fields[1];
                int hour = Integer.parseInt(fields[2]);
                boolean notBooked = fields[3].equalsIgnoreCase("Booked");

                // Find the doctor by username
                for (Doctor doctor : doctors) {
                    if (doctor.getFirst_name().equals(fname)) {
                        // Add day if not present
                        if (!doctor.appointments.schedule.containsKey(day.toLowerCase())) {
                            doctor.appointments.schedule.put(day.toLowerCase(), new ArrayList<>());
                        }

                        // Add the time slot
                        List<TimeSlot> timeSlots = doctor.appointments.schedule.get(day.toLowerCase());
                        TimeSlot newSlot = new TimeSlot(hour);
                        if (notBooked) {
                            newSlot.book(); // Mark as booked
                        }
                        timeSlots.add(newSlot);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments data: " + e.getMessage());
        }
    }


    // Save data to CSV files
    public void saveData() {
        saveDoctors();
        savePatients();
        saveReceptionists();
        saveAppointments();
    }

    private void saveDoctors() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getFirst_name() + "," + doctor.getLast_name() + "," + doctor.getEmail() + "," + doctor.getAge() + "," + doctor.getGender() + "," + doctor.getMobile_number() + "," + doctor.getPassword() + "," + doctor.getSpecialization() + "," + doctor.getAppointment_price());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors data: " + e.getMessage());
        }
    }

    private void savePatients() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (Patients patient : patients) {
                writer.write(patient.getFirst_name() + "," + patient.getLast_name() + "," + patient.getEmail() + "," + patient.getAge() + "," + patient.getGender() + "," + patient.getMobile_number() + "," + patient.getPassword() + "," + patient.getWeight() + "," + patient.getHeight() + "," + patient.getBlood_type() + "," + patient.getId() + "," + patient.getHistory());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients data: " + e.getMessage());
        }
    }

    private void saveReceptionists() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECEPTIONIST_FILE))) {
            for (Receptionist receptionist : receptionists) {
                writer.write(receptionist.getFirst_name() + "," + receptionist.getLast_name() + "," + receptionist.getEmail() + "," + receptionist.getAge() + "," + receptionist.getGender() + "," + receptionist.getMobile_number() + "," + receptionist.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving receptionists data: " + e.getMessage());
        }
    }

    private void saveAppointments() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE))) {
            // Write appointments for each doctor
            for (Doctor doctor : doctors) {
                for (Map.Entry<String, List<TimeSlot>> entry : doctor.appointments.schedule.entrySet()) {
                    String day = entry.getKey();
                    List<TimeSlot> timeSlots = entry.getValue();

                    for (TimeSlot slot : timeSlots) {
                        writer.write(doctor.getFirst_name() + "," + day + "," + slot.getHour() + "," + (slot.isBooked() ? "Booked" : "Available"));
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments data: " + e.getMessage());
        }
    }
}
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileHandler {
//
//    private static final String DOCTORS_FILE = "doctors.dat";
//    private static final String PATIENTS_FILE = "patients.dat";
//    private static final String RECEPTIONISTS_FILE = "receptionists.dat";
//
//    public void saveData(List<Doctor> doctors, List<Patients> patients, List<Receptionist> receptionists) {
//        saveListToFile(doctors, DOCTORS_FILE);
//        saveListToFile(patients, PATIENTS_FILE);
//        saveListToFile(receptionists, RECEPTIONISTS_FILE);
//    }
//
//    public List<Doctor> loadDoctors() {
//        return loadListFromFile(DOCTORS_FILE);
//    }
//
//    public List<Patients> loadPatients() {
//        return loadListFromFile(PATIENTS_FILE);
//    }
//
//    public List<Receptionist> loadReceptionists() {
//        return loadListFromFile(RECEPTIONISTS_FILE);
//    }
//
//    private <T> void saveListToFile(List<T> list, String fileName) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            oos.writeObject(list);
//        } catch (IOException e) {
//            System.err.println("Error saving to file: " + fileName);
//            e.printStackTrace();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private <T> List<T> loadListFromFile(String fileName) {
//        File file = new File(fileName);
//        if (!file.exists()) {
//            return new ArrayList<>(); // Return empty list if file does not exist
//        }
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
//            return (List<T>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading from file: " + fileName);
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
//}
