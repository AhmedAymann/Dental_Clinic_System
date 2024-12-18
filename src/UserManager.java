import java.util.*;
public class UserManager {
    Scanner scanner=new Scanner(System.in);
    //common data
    String username;
    String password;
    String First_name;String Last_name;
    String Email;String Mobile_number;
    int age; String gender;
    //patient data
    int weight ;int height;String blood_type;
    //doctor data
    public String Specialization;
    public int price;

    public Doctor doctor_signup(){
        System.out.println("Enter your First Name:");
        First_name = scanner.nextLine();

        System.out.println("Enter your Last Name:");
        Last_name = scanner.nextLine();

        boolean validemail=false;
        while(!validemail){
            System.out.println("Enter your Email:");
            Email=scanner.nextLine();
            if(Email.contains("@")){
                validemail=true;
            }
            else{
                System.out.println("Invalid email");
            }
        }
        username=Email; // Assigning email as the username

        do {
            System.out.println("Create your password:");
            String temppassword = scanner.nextLine();
            System.out.println("Re-enter your password:");
            password = scanner.nextLine();
            if (temppassword.equals(password)) {
                break;
            }
            System.out.println("Passwords do not match. Please try again.");
        } while (true);

        System.out.println("Enter your Mobile Number:");
        Mobile_number = scanner.nextLine();

        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your age: ");
                age = scanner.nextInt();
                scanner.nextLine();// Attempt to read an integer
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! That's not an integer.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        }
        validInput = false;

        System.out.println("Enter your gender (Male/Female):");
        gender = scanner.nextLine();

        System.out.println("Enter your Specialization: ");
        Specialization = scanner.nextLine();

        while (!validInput) {
            try {
                System.out.print("Enter Appointment price: ");
                price = scanner.nextInt();
                scanner.nextLine();// Attempt to read an integer
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! That's not an integer.");
                scanner.next(); // Clear the invalid input from the buffer
            }
        }

        // Creating and returning a new Doctor object
        return new Doctor(username, password, First_name, Last_name, Email, Mobile_number, age, gender, Specialization, price);
    }

     public Patients patient_signup(){
         System.out.println("Enter your First Name:");
         First_name = scanner.nextLine();

         System.out.println("Enter your Last Name:");
         Last_name = scanner.nextLine();

         boolean validemail=false;
         while(!validemail){
             System.out.println("Enter your Email:");
             Email=scanner.nextLine();
             if(Email.contains("@")){
                 validemail=true;
             }
             else{
                 System.out.println("Invalid email");
             }
         }
         username=Email; // Assigning email as the username

         do {
             System.out.println("Create your password:");
             String temppassword = scanner.nextLine();
             System.out.println("Re-enter your password:");
             password = scanner.nextLine();
             if (temppassword.equals(password)) {
                 break;
             }
             System.out.println("Passwords do not match. Please try again.");
         } while (true);

         System.out.println("Enter your Mobile Number:");
         Mobile_number = scanner.nextLine();

         boolean validInput = false;

         while (!validInput) {
             try {
                 System.out.print("Enter your age: ");
                 age = scanner.nextInt();
                 scanner.nextLine();// Attempt to read an integer
                 validInput = true; // Input is valid, exit the loop
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input! That's not an integer.");
                 scanner.next(); // Clear the invalid input from the buffer
             }
         }
         validInput = false;// Consume newline left by nextInt

         System.out.println("Enter your gender (Male/Female):");
         gender = scanner.nextLine();



         while (!validInput) {
             try {
                 System.out.print("Enter your weight: ");
                 weight = scanner.nextInt();
                 scanner.nextLine();// Attempt to read an integer
                 validInput = true; // Input is valid, exit the loop
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input! That's not an integer.");
                 scanner.next(); // Clear the invalid input from the buffer
             }
         }
         validInput = false;


         while (!validInput) {
             try {
                 System.out.print("Enter your height: ");
                 height = scanner.nextInt();
                 scanner.nextLine();// Attempt to read an integer
                 validInput = true; // Input is valid, exit the loop
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input! That's not an integer.");
                 scanner.next(); // Clear the invalid input from the buffer
             }
         }
         validInput = false;

         System.out.println("Enter your blood type: ");
         blood_type=scanner.nextLine();

         return new Patients(username,password,First_name,Last_name,Email,Mobile_number,age,gender,weight,height,blood_type);
     }

     public Receptionist receptionist_signup(){
         System.out.println("Enter your First Name:");
         First_name = scanner.nextLine();

         System.out.println("Enter your Last Name:");
         Last_name = scanner.nextLine();

         boolean validemail=false;
         while(!validemail){
             System.out.println("Enter your Email:");
             Email=scanner.nextLine();
             if(Email.contains("@")){
                 validemail=true;
             }
             else{
                 System.out.println("Invalid email");
             }
         }
         username=Email; // Assigning email as the username

         do {
             System.out.println("Create your password:");
             String temppassword = scanner.nextLine();
             System.out.println("Re-enter your password:");
             password = scanner.nextLine();
             if (temppassword.equals(password)) {
                 break;
             }
             System.out.println("Passwords do not match. Please try again.");
         } while (true);

         System.out.println("Enter your Mobile Number:");
         Mobile_number = scanner.nextLine();

         // Consume newline left by nextInt
         boolean validInput = false;

         while (!validInput) {
             try {
                 System.out.print("Enter your age: ");
                 age = scanner.nextInt();
                 scanner.nextLine();// Attempt to read an integer
                 validInput = true; // Input is valid, exit the loop
             } catch (InputMismatchException e) {
                 System.out.println("Invalid input! That's not an integer.");
                 scanner.next(); // Clear the invalid input from the buffer
             }
         }

         validInput = false;

         System.out.println("Enter your gender (Male/Female):");
         gender = scanner.nextLine();


         return new Receptionist(username,password,First_name,Last_name,Email,Mobile_number,age,gender);
     }

    public Doctor doctor_login(List<Doctor> doctors) {
        System.out.println("Logging in:");
        System.out.println("Enter your email:");
        String login_email = scanner.nextLine();

        System.out.println("Enter your password:");
        String login_password = scanner.nextLine();

        for (Doctor doctor : doctors) {
            if (login_email.equals(doctor.Email) && login_password.equals(doctor.getPassword())) {
                return doctor; // Return the matching doctor
            }
        }
        System.out.println("Account not found. Please check your credentials.");
        return null;
    }

    public Patients patient_login(List<Patients> patients){
        System.out.println("Logging in:");
        System.out.println("Enter your email:");
        String login_email = scanner.nextLine();

        System.out.println("Enter your password:");
        String login_password = scanner.nextLine();

        for (Patients patients1 : patients) {
            if (login_email.equals(patients1.Email) && login_password.equals(patients1.getPassword())) {
                return patients1; // Return the matching doctor
            }
        }
        System.out.println("Account not found. Please check your credentials.");
        return null;
    }

    public Receptionist receptionist_login(List<Receptionist> receptionists){
        System.out.println("Logging in:");
        System.out.println("Enter your email:");
        String login_email = scanner.nextLine();

        System.out.println("Enter your password:");
        String login_password = scanner.nextLine();

        for (Receptionist receptionist : receptionists) {
            if (login_email.equals(receptionist.Email) && login_password.equals(receptionist.getPassword())) {
                return receptionist; // Return the matching doctor
            }
        }
        System.out.println("Account not found. Please check your credentials.");
        return null;
    }
}
