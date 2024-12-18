import java.util.*;
public class Doctor extends Users{
    Scanner scanner=new Scanner(System.in);
    public Appointments appointments;
    public String Specialization;
    public int appointment_price;
    public String[] validDays = {
            "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday", "Sunday"
    };

    public Doctor(String username,String password,String First_name,String Last_name,String Email,String Mobile_number,int age, String gender,String specialization,int appointment_price){
        super(username, password, First_name, Last_name, Email, Mobile_number, age, gender);
        this.Specialization=specialization;
        this.appointment_price=appointment_price;
        this.appointments= new Appointments();

    }

    public void create_prescription(List<Patients> patients){
        int patient_id;
        System.out.println("Enter patient's id: ");
        patient_id=scanner.nextInt();
        scanner.nextLine();
        for(Patients pat:patients){
            if(pat.getId()==patient_id){
                System.out.println("Enter prescription: ");
                pat.prescription= scanner.nextLine();
                pat.history.add(pat.prescription);
            }
        }
    }

    public void show_patient_history(List<Patients> patients){
        int patient_id;
        System.out.println("Enter patient's id: ");
        patient_id=scanner.nextInt();
        scanner.nextLine();
        for(Patients patients1:patients){
            if (patient_id==patients1.getId()){
                for(String pathistrory: patients1.history){
                    System.out.println(pathistrory);
                }
            }
        }
    }

    public void show_appointments(){
        System.out.print("Enter the day to display the schedule: ");
        String day = scanner.nextLine();
        appointments.displaySchedule(day);
    }
    public void get_recep_info(List<Receptionist> receps){
        int recep_id;
        System.out.println("Enter Receptionist's id: ");
        recep_id=scanner.nextInt();
        scanner.nextLine();
        for(Receptionist rece:receps){
            if(rece.getId()==recep_id){
                System.out.println("Name: "+rece.First_name+" "+rece.Last_name);
                System.out.println("Phone number: "+rece.Mobile_number);
            }
        }
    }
    public void get_patients_info(List<Patients> patients){
        int patient_id;
        System.out.println("Enter patient's id: ");
        patient_id=scanner.nextInt();
        scanner.nextLine();
        Boolean isFound = false;

        for(Patients pat:patients){
            if(pat.getId()==patient_id){
                System.out.println("Name: "+pat.First_name+" "+pat.Last_name);
                System.out.println("Phone number: "+pat.Mobile_number);
                System.out.println("Weight: " + pat.weight);
                System.out.println("Height: " + pat.height);
                System.out.println("Age: " + pat.age);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("Patient id Not found");
        }
    }

    // List of valid days







    public void Add_availability(){
        System.out.println("Enter the number of days to add availability for:");
        int numberOfDays = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        for (int i = 0; i < numberOfDays; i++) {
            boolean isValid = false;
            String day = "";
            // Check if the entered day matches any valid day
            while (!isValid){
                System.out.println("Enter the day:");
                day = scanner.nextLine();
                for (String weekDay : validDays) {
                    if (day.equalsIgnoreCase(weekDay)) {
                    isValid = true;
                    break;
                    }
                }
                if (!isValid) {
                    System.out.println("Invalid day. Please enter a correct day of the week.");
                }
            }


            System.out.println("Enter free hours for " + day + " (comma-separated, e.g., 9,10,11):");
            String[] hoursInput = scanner.nextLine().split(",");
            List<Integer> freeHours = new ArrayList<>();

            for (String hour : hoursInput) {
                freeHours.add(Integer.parseInt(hour.trim()));
            }

            appointments.addDay(day, freeHours);
        }
    }

    public void editAvailability() {
        System.out.println("Enter the day you want to edit availability for (e.g., Monday): ");
        String day = scanner.nextLine().toLowerCase(); // Make the day lowercase to match the schedule format

        // Check if the day exists in the schedule
        if (appointments.schedule.containsKey(day)) {
            System.out.println("Current available hours for " + day + ": ");
            List<TimeSlot> timeSlots = appointments.schedule.get(day);

            // Display current available hours
            for (TimeSlot slot : timeSlots) {
                System.out.println("  " + slot);
            }

            System.out.println("Would you like to add or remove a time slot?");
            System.out.println("1: Add time slot");
            System.out.println("2: Remove time slot");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add a new time slot
                    System.out.println("Enter the hour you want to add: ");
                    int newHour = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    // Check if the slot already exists
                    boolean exists = false;
                    for (TimeSlot slot : timeSlots) {
                        if (slot.getHour() == newHour) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("This time slot already exists.");
                    } else {
                        timeSlots.add(new TimeSlot(newHour));  // Add new time slot
                        System.out.println("Time slot " + newHour + ":00 has been added.");
                    }
                    break;

                case 2:
                    // Remove a time slot
                    System.out.println("Enter the hour you want to remove: ");
                    int removeHour = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    // Find the time slot to remove
                    TimeSlot slotToRemove = null;
                    for (TimeSlot slot : timeSlots) {
                        if (slot.getHour() == removeHour) {
                            slotToRemove = slot;
                            break;
                        }
                    }

                    if (slotToRemove != null) {
                        timeSlots.remove(slotToRemove);
                        System.out.println("Time slot " + removeHour + ":00 has been removed.");
                    } else {
                        System.out.println("No such time slot exists.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }

        } else {
            System.out.println("No schedule exists for " + day + ". Would you like to add a new day?");
            System.out.println("1: Yes");
            System.out.println("2: No");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                // Ask the doctor to input hours for the new day
                System.out.println("Enter free hours for " + day + " (comma-separated, e.g., 9,10,11):");
                String[] hoursInput = scanner.nextLine().split(",");
                List<Integer> freeHours = new ArrayList<>();

                for (String hour : hoursInput) {
                    freeHours.add(Integer.parseInt(hour.trim()));
                }

                appointments.addDay(day, freeHours);
                System.out.println("New day and hours have been added to the schedule.");
            }
        }
    }

    public int getAppointment_price() {
        return appointment_price;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public Appointments getAppointments() {
        return appointments;
    }
}
