import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main{





    public static void main(String[] args) {

//        launch(args);

        // Lists
        List<Doctor> doctors = new ArrayList<>();
        List<Patients> patients = new ArrayList<>();
        List<Receptionist> receptionists = new ArrayList<>();


        //load and delete(for now)
        FileHandler handler1= new FileHandler(doctors,patients,receptionists);
//        handler1.delete();
//         Dental clinic declaration
        DentalClinic bright_smile = new DentalClinic("New Cairo", doctors, patients, receptionists);

        // User management declaration
        UserManager userManager = new UserManager();

        // Main UI
        Scanner scanner = new Scanner(System.in);
        boolean a = true;
        try{ // main try (code entry)
            while (a) {
                System.out.println("Welcome to Bright Smile Clinic!");
                System.out.println("1: Doctor");
                System.out.println("2: Patient");
                System.out.println("3: Receptionist");
                System.out.println("4: Close application");
                int choice1 = 0;

                boolean validInput = false;

                while (!validInput) {
                    try {
                        System.out.print("Enter your choice: ");
                        choice1 = scanner.nextInt();
                        scanner.nextLine();// Attempt to read an integer
                        if(choice1 > 4 && choice1 < 1){
                            validInput = false;
                        }else{
                        validInput = true; // Input is valid, exit the loop
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! That's not an integer.");
                        scanner.next(); // Clear the invalid input from the buffer
                    }
                }
                validInput = false;
                boolean b = true;

                while (b) {
                    switch (choice1) {
                        case 1: // Doctor Section
                            System.out.println("1: Login");
                            System.out.println("2: Signup");
                            int choice2 = 0;

                            while (!validInput) {
                                try {
                                    choice2 = scanner.nextInt();
                                    scanner.nextLine();// Attempt to read an integer
                                    validInput = true; // Input is valid, exit the loop
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! That's not an integer.");
                                    scanner.next(); // Clear the invalid input from the buffer
                                }
                            }
                            validInput = false;



                            Doctor logged_doctor = null;
                            if (choice2 == 2) {
                                Doctor doctor = userManager.doctor_signup();
                                doctors.add(doctor);
                                logged_doctor = userManager.doctor_login(doctors);
                            } else {
                                logged_doctor = userManager.doctor_login(doctors);
                            }

                            if (logged_doctor != null) {
                                boolean doctor_loop = true;
                                while (doctor_loop) {
                                    System.out.println("\nWelcome, Dr. " + logged_doctor.First_name + "!");
                                    System.out.println("Choose an action:");
                                    System.out.println("1: Create Prescription");
                                    System.out.println("2: Show Appointments");
                                    System.out.println("3: Show Receptionist's info");
                                    System.out.println("4: Show Patient's info");
                                    System.out.println("5: Show Patient's history");
                                    System.out.println("6: Add Available Time");
                                    System.out.println("7: Edit Available Time");
                                    System.out.println("8: Log out");
                                    int doctorAction = 0;
                                    while (!validInput) {
                                        try {
                                            System.out.print("Enter your doctorAction: ");
                                            doctorAction = scanner.nextInt();
                                            scanner.nextLine();// Attempt to read an integer
                                            validInput = true; // Input is valid, exit the loop
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! That's not an integer.");
                                            scanner.next(); // Clear the invalid input from the buffer
                                        }
                                    }
                                    validInput = false;

                                    switch (doctorAction) {
                                        case 1:
                                            logged_doctor.create_prescription(patients);
                                            break;
                                        case 2:
                                            logged_doctor.show_appointments();
                                            break;
                                        case 3:
                                            logged_doctor.get_recep_info(receptionists);
                                            break;
                                        case 4:
                                            logged_doctor.get_patients_info(patients);
                                            break;
                                        case 5:
                                            logged_doctor.show_patient_history(patients);
                                            break;
                                        case 6:
                                            logged_doctor.Add_availability();
                                            break;
                                        case 7:
                                            logged_doctor.editAvailability();
                                            break;
                                        case 8:
                                            doctor_loop = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice.");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("Login failed. Please try again.");
                            }
                            b = false;
                            break;

                        case 2: // Patient Section
                            System.out.println("1: Login");
                            System.out.println("2: Signup");
                            int choice3 = 0;

                            while (!validInput) {
                                try {
                                    choice3 = scanner.nextInt();
                                    scanner.nextLine();// Attempt to read an integer
                                    validInput = true; // Input is valid, exit the loop
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! That's not an integer.");
                                    scanner.next(); // Clear the invalid input from the buffer
                                }
                            }
                            validInput = false;

                            Patients logged_patient = null;
                            if (choice3 == 2) {
                                Patients patient = userManager.patient_signup();
                                patients.add(patient);
                                logged_patient = userManager.patient_login(patients);
                            } else {
                                logged_patient = userManager.patient_login(patients);
                            }

                            if (logged_patient != null) {
                                boolean patient_loop = true;
                                while (patient_loop) {
                                    System.out.println("\nWelcome " + logged_patient.First_name + "!");
                                    System.out.println("Choose an action: ");
                                    System.out.println("1: Change info");
                                    System.out.println("2: Reserve an Appointment");
                                    System.out.println("3: Cancel Reservation");
                                    System.out.println("4: Check prices");
                                    System.out.println("5: Search Doctor");
                                    System.out.println("6: Display Available Time");
                                    System.out.println("7: Log out");
                                    int patient_action = 0;

                                    while (!validInput) {
                                        try {
                                            patient_action = scanner.nextInt();
                                            scanner.nextLine();// Attempt to read an integer
                                            validInput = true; // Input is valid, exit the loop
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! That's not an integer.");
                                            scanner.next(); // Clear the invalid input from the buffer
                                        }
                                    }
                                    validInput = false;



                                    switch (patient_action) {
                                        case 1:
                                            logged_patient.change_info();
                                            break;
                                        case 2:
                                            logged_patient.reserve_appointment(doctors);
                                            break;
                                        case 3:
                                            logged_patient.cancel_reservation(doctors);
                                            break;
                                        case 4:
                                            logged_patient.check_prices(doctors);
                                            break;
                                        case 5:
                                            logged_patient.search_doctor(doctors);
                                            break;
                                        case 6:
                                            logged_patient.display_available_appointments(doctors);
                                            break;
                                        case 7:
                                            patient_loop = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("Login failed. Please try again.");
                            }
                            b = false;
                            break;

                        case 3: // Receptionist Section
                            System.out.println("1: Login");
                            System.out.println("2: Signup");
                            int choice4 = 0;

                            while (!validInput) {
                                try {
                                    choice4 = scanner.nextInt();
                                    scanner.nextLine();// Attempt to read an integer
                                    validInput = true; // Input is valid, exit the loop
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! That's not an integer.");
                                    scanner.next(); // Clear the invalid input from the buffer
                                }
                            }
                            validInput = false;

                            Receptionist logged_receptionist = null;
                            if (choice4 == 2) {
                                Receptionist receptionist = userManager.receptionist_signup();
                                receptionists.add(receptionist);
                                logged_receptionist = userManager.receptionist_login(receptionists);
                            } else {
                                logged_receptionist = userManager.receptionist_login(receptionists);
                            }

                            if (logged_receptionist != null) {
                                boolean receptionist_loop = true;
                                while (receptionist_loop) {
                                    System.out.println("\nWelcome " + logged_receptionist.First_name);
                                    System.out.println("Choose an action: ");
                                    System.out.println("1: Change info");
                                    System.out.println("2: Reserve Appointment");
                                    System.out.println("3: Cancel Appointment");
                                    System.out.println("4: Log out");
                                    int receptionist_action = 0;

                                    while (!validInput) {
                                        try {
                                            receptionist_action = scanner.nextInt();
                                            scanner.nextLine();// Attempt to read an integer
                                            validInput = true; // Input is valid, exit the loop
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! That's not an integer.");
                                            scanner.next(); // Clear the invalid input from the buffer
                                        }
                                    }
                                    validInput = false;

                                    switch (receptionist_action) {
                                        case 1:
                                            logged_receptionist.changeinfo(patients);
                                            break;
                                        case 2:
                                            logged_receptionist.reserve_appointment(doctors, patients);
                                            break;
                                        case 3:
                                            logged_receptionist.cancel_appointment(doctors, patients);
                                            break;
                                        case 4:
                                            receptionist_loop = false;
                                            break;
                                        default:
                                            System.out.println("Invalid choice");
                                            break;
                                    }
                                }
                            } else {
                                System.out.println("Login failed. Please try again.");
                            }
                            b = false;
                            break;

                        case 4:
                            b = false;
                            a = false;
                            break;

                        default:
                            System.out.println("Invalid choice");
                            b = false;
                            break;
                    }
                }
            }

        } finally {
            handler1.delete();
            handler1.saveData();
        }
    }

}


