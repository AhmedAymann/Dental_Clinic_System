//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;


import java.util.ArrayList;
import java.lang.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

//        launch(args);

        // Lists
//        List<Doctor> doctors = new ArrayList<>();
//        List<Patients> patients = new ArrayList<>();
//        List<Receptionist> receptionists = new ArrayList<>();
        List<DentalClinic> dentalClinics = new ArrayList<>();

        //load and delete(for now)
//            FileHandler handler1 = new FileHandler(DC.doctorList, DC.patientsList, DC.receptionistList);

//         Dental clinic declaration
        DentalClinic bright_smile = new DentalClinic("New Cairo","Bright Smile", "Dental services", 50000);
        DentalClinic white_teeth = new DentalClinic("Nasr City","White Teeth", "Dental services", 40000);
        DentalClinic tooth_brush = new DentalClinic("Masr el Gededa","Tooth Brush", "Dental services", 30000);
        DentalClinic golden_tooth = new DentalClinic("Mokatam","Golden Tooth", "Dental services", 25000);
        dentalClinics.add(bright_smile);
        dentalClinics.add(white_teeth);
        dentalClinics.add(tooth_brush);
        dentalClinics.add(golden_tooth);
        DentalClinic DC = new DentalClinic();

        FileHandler handler1 = new FileHandler(dentalClinics);
        // User management declaration
        UserManager userManager = new UserManager();

        // Main UI
        Scanner scanner = new Scanner(System.in);
        boolean a = true;
//        try {// main try (code entry)

            while (a) {
                boolean b = true;
                boolean c = true;
                boolean s = true;
                while (s) {
                    System.out.println("enter which dental clinic you want: ");
                    System.out.println("1:bright smile\n2:White teeth\n3:Tooth brush\n4:Golden tooth\n5:Close");
                    int dchoice = scanner.nextInt();
                    scanner.nextLine();

                    switch (dchoice) {
                        case 1:
                            DC = bright_smile;
                            s = false;
                            break;
                        case 2:
                            DC = white_teeth;
                            s = false;
                            break;
                        case 3:
                            DC = tooth_brush;
                            s = false;
                            break;
                        case 4:
                            DC = golden_tooth;
                            s = false;
                            break;
                        case 5:
                            s=false;
                            a=false;
                            b=false;
                            c=false;
                            break;
                        default:
                            System.out.println("invalid choice!");
                    }
                }

                boolean validInput = false;
                int choice1 = 0;

                while(c)
                {
                    System.out.println("Welcome to "+DC.name+ " Clinic!");
                    System.out.println("1: Doctor");
                    System.out.println("2: Patient");
                    System.out.println("3: Receptionist");
                    System.out.println("4: Close application");


                    while (!validInput) {
                        try
                        {
                            System.out.print("Enter your choice: ");
                            choice1 = scanner.nextInt();
                            scanner.nextLine();// Attempt to read an integer
                            if (choice1 > 4 && choice1 < 1) {
                                validInput = false;
                            } else {
                                validInput = true; // Input is valid, exit the loop
                            }
                            c=false;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! That's not an integer.");
                            scanner.next(); // Clear the invalid input from the buffer
                        }
                    }
                validInput = false;
                }

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
                                DC.doctorList.add(doctor);
                                logged_doctor = userManager.doctor_login(DC.doctorList);
                            } else {
                                logged_doctor = userManager.doctor_login(DC.doctorList);
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
                                            logged_doctor.create_prescription(DC.patientsList);
                                            break;
                                        case 2:
                                            logged_doctor.show_appointments();
                                            break;
                                        case 3:
                                            logged_doctor.get_recep_info(DC.receptionistList);
                                            break;
                                        case 4:
                                            logged_doctor.get_patients_info(DC.patientsList);
                                            break;
                                        case 5:
                                            logged_doctor.show_patient_history(DC.patientsList);
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
                                DC.patientsList.add(patient);
                                logged_patient = userManager.patient_login(DC.patientsList);
                            } else {
                                logged_patient = userManager.patient_login(DC.patientsList);
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
                                            logged_patient.reserve_appointment(DC.doctorList);
                                            break;
                                        case 3:
                                            logged_patient.cancel_reservation(DC.doctorList);
                                            break;
                                        case 4:
                                            logged_patient.check_prices(DC.doctorList);
                                            break;
                                        case 5:
                                            logged_patient.search_doctor(DC.doctorList);
                                            break;
                                        case 6:
                                            logged_patient.display_available_appointments(DC.doctorList);
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
                                DC.receptionistList.add(receptionist);
                                logged_receptionist = userManager.receptionist_login(DC.receptionistList);
                            } else {
                                logged_receptionist = userManager.receptionist_login(DC.receptionistList);
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
                                            logged_receptionist.changeinfo(DC.patientsList);
                                            break;
                                        case 2:
                                            logged_receptionist.reserve_appointment(DC.doctorList, DC.patientsList);
                                            break;
                                        case 3:
                                            logged_receptionist.cancel_appointment(DC.doctorList, DC.patientsList);
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


//            FileHandler handler1 =new FileHandler()
            handler1.delete();
            handler1.saveData();
        }
    }