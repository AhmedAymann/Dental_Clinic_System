import javax.swing.*;
import java.util.*;
public class Receptionist extends Users{
    Scanner scanner=new Scanner(System.in);
//    used in change info method
    int choice,patientid;
    private int id;
    public static int counter=1;

    public Receptionist(String username,String password,String First_name,String Last_name,String Email,String Mobile_number,int age, String gender){
        super(username, password, First_name, Last_name, Email, Mobile_number, age, gender);
        this.id=counter;
        counter++;
    }

    public void changeinfo(List<Patients> patients){
        System.out.println("Enter Patient's ID to change his/her info: ");
        patientid=scanner.nextInt();
        scanner.nextLine();
        boolean a=true;
        System.out.println("Select detail to change: ");
        System.out.println("1: Email");
        System.out.println("2: Phone Number");
        System.out.println("3: Weight and Height");
        this.choice=0;
        boolean validInput=false;
        while (!validInput) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();// Attempt to read an integer
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! That's not an integer.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        }
        validInput = false;
        while (a){
            switch (choice){
                case 1:
                    for(Patients pat:patients){
                        if(pat.getId()==patientid){
                            System.out.println("Enter new Email: ");
                            pat.Email= scanner.nextLine();
                        }
                    }
                    a=false;
                    break;
                case 2:
                    for(Patients pat:patients){
                        if(pat.getId()==patientid){
                            System.out.println("Enter new Mobile number: ");
                            pat.Mobile_number= scanner.nextLine();
                        }
                    }
                    a=false;
                    break;
                case 3:
                    for(Patients pat:patients){
                        if(pat.getId()==patientid){
                            System.out.println("Enter new weight: ");
                            pat.weight= scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Enter new height: ");
                            pat.height=scanner.nextInt();
                            scanner.nextLine();
                        }
                    }
                    a=false;
                    break;
                default:
                    System.out.println("Enter a valid choice: ");
            }
        }
    }
    public void reserve_appointment(List<Doctor> doctors,List<Patients> patients){
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.First_name + " " + doctor.Last_name + "'s Schedule:");

            // Iterate through all days in the doctor's schedule
            for (String day : doctor.appointments.schedule.keySet()) {
                System.out.println(day.toUpperCase() + ":");
                doctor.appointments.displaySchedule(day); // Display time slots for each day
            }
        }
        int patid=0;
        boolean validInput=false;
        while (!validInput) {
            System.out.println("Enter patient's id to add an appointment to:");
            try {
                patid = scanner.nextInt();
                scanner.nextLine();// Attempt to read an integer
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! That's not an integer.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        }
        validInput = false;
        for (Patients patients1:patients){
            if (patients1.getId()==patid){
                System.out.println("Enter doctor's name: ");
                String docname=scanner.nextLine();
                for (Doctor doctor:doctors){
                    System.out.print("Enter the day to book: ");
                    String day = scanner.nextLine();
                    System.out.print("Enter the hour to book: ");
                    int hour = scanner.nextInt();
                    scanner.nextLine();
                    doctor.appointments.bookAppointment(day,hour, patid);
                }
                break;
            }
        }

    }
    public void cancel_appointment(List<Doctor> doctors, List<Patients> patients){
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.First_name + " " + doctor.Last_name + "'s Schedule:");

            // Iterate through all days in the doctor's schedule
            for (String day : doctor.appointments.schedule.keySet()) {
                System.out.println(day.toUpperCase() + ":");
                doctor.appointments.displaySchedule(day); // Display time slots for each day
            }
        }

        boolean a=true;

        while (a){
            int patid=0;
            boolean validInput=false;
            while (!validInput) {
                System.out.println("Enter patient's id to cancel an appointment:");
                try {
                    patid = scanner.nextInt();
                    scanner.nextLine();// Attempt to read an integer
                    validInput = true; // Input is valid, exit the loop
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! That's not an integer.");
                    scanner.next(); // Clear the invalid input from the buffer
                }
            }
            validInput = false;

            for (Patients patients1:patients) {
                if (patients1.getId() == patid) {
                    System.out.println("Enter doctor's name to cancel appointment: ");
                    String docname = scanner.nextLine();

                    // Ask for the day and hour of the appointment to cancel
                    System.out.print("Enter the day of the appointment to cancel: ");
                    String day = scanner.nextLine();

                    System.out.print("Enter the hour of the appointment to cancel: ");
                    int hour = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    // Iterate through the list of doctors
                    for (Doctor doctor : doctors) {
                        if (doctor.First_name.equals(docname)) {
                            // Find the day in the doctor's schedule
                            List<TimeSlot> timeSlots = doctor.appointments.schedule.get(day.toLowerCase());
                            if (timeSlots != null) {
                                // Find the time slot to cancel
                                for (TimeSlot slot : timeSlots) {
                                    if (slot.getHour() == hour && slot.isBooked() && patid == slot.appointmentId) {
                                        // Cancel the appointment by unbooking the slot
                                        slot.cancel();
                                        System.out.println("Appointment with Dr. " + docname + " on " + day + " at " + hour + ":00 has been canceled.");
                                        return;  // Exit after successful cancellation
                                    }
                                }
                                System.out.println("No booked appointment found for " + hour + ":00 on " + day + "for the patient with the id " + patid + ".");
                                return;
                            } else {
                                System.out.println("No schedule available for " + day + ".");
                                return;
                            }
                        }
                    }
                    System.out.println("Doctor with name " + docname + " not found.");
                    break;
                }
            }
            System.out.println("couldn't find patient id, please try again.");
        }
    }
    public int getId(){
        return this.id;
    }
}
