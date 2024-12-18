import java.util.*;
public class Patients extends Users{
    Scanner scanner=new Scanner(System.in);
    public int weight,height;
    public String blood_type;
    public List<String>history;
    public String prescription;
//  used in the change info method and search doctor method
    int choice;
//  used in the check prices method and search doctor method
    String docname;
//  used in search doctor method
    String docnum;
    private int id;
    public static int counter=1;

    public Patients(String username,String password,String First_name,String Last_name,String Email,String Mobile_number,int age, String gender,int weight ,int height,String blood_type){
        super(username, password, First_name, Last_name, Email, Mobile_number, age, gender);
        this.weight=weight;
        this.height=height;
        this.blood_type=blood_type;
        this.history=new ArrayList<>();
        this.id=counter;
        counter++;
    }

    public Patients(String username,String password,String First_name,String Last_name,String Email,String Mobile_number,int age, String gender, int weight, int height, String blood_type, int id, List<String> history){
        super(username, password, First_name, Last_name, Email, Mobile_number, age, gender);
        counter= -1;
        this.weight=weight;
        this.height=height;
        this.blood_type=blood_type;
//        this.history=history;
        this.id=id;
        if (counter<=id)
        {
            counter=id+1;
        }
        if(history == null|| history.isEmpty())
        {
            this.history=new ArrayList<>();
        }
        else
        {
            this.history=history;
        }
    }

    public void change_info(){
        System.out.println("Select detail to change: ");
        System.out.println("1: Email");
        System.out.println("2: Phone Number");
        System.out.println("3: Weight and Height");
        this.choice=scanner.nextInt();
        scanner.nextLine();
        boolean a=true;
        while(a){
            switch (choice){
                case 1:
                    System.out.println("Enter New Email: ");
                    this.Email=scanner.nextLine();
                    System.out.println("Email changed successfully");
                    a=false;
                    break;
                case 2:
                    System.out.println("Enter New Phone Number: ");
                    this.Mobile_number= scanner.nextLine();
                    System.out.println("Phone Number changed successfully");
                    a=false;
                    break;
                case 3:
                    System.out.println("Enter Weight: ");
                    this.weight= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Height: ");
                    this.height= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Weight & Height changed successfully");
                    a=false;
                    break;
                default:
                    System.out.println("Select a valid option");
                    break;
            }
        }
    }
    public void reserve_appointment(List<Doctor> doctors){
        this.display_available_appointments(doctors);
        System.out.println("Enter doctor's name: ");
        String docname=scanner.nextLine();
        System.out.print("Enter the day to book: ");
        String day = scanner.nextLine();

        System.out.print("Enter the hour to book: ");
        int hour = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for(Doctor doctor:doctors){
            if(doctor.First_name.equals(docname)){
                doctor.appointments.bookAppointment(day,hour);
                doctor.appointments.appId = this.id;

            }
        }
    }
    public void cancel_reservation(List<Doctor> doctors) {
        // Ask for the doctor's name
        this.display_available_appointments(doctors);
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
                        if (slot.getHour() == hour && slot.isBooked() && this.id == doctor.appointments.appId){
                            // Cancel the appointment by unbooking the slot
                            slot.cancel();
                            System.out.println("Appointment with Dr. " + docname + " on " + day + " at " + hour + ":00 has been canceled.");
                            return;  // Exit after successful cancellation
                        }
                    }
                    System.out.println("No booked appointment found for " + hour + ":00 on " + day + ".");
                    return;
                } else {
                    System.out.println("No schedule available for " + day + ".");
                    return;
                }
            }
        }
        System.out.println("Doctor with name " + docname + " not found.");
    }

    public void check_prices(List<Doctor> doctors){
        System.out.println("Enter doctor's name: ");
        docname= scanner.nextLine();
        for(Doctor doc:doctors){
            if(doc.First_name.equals(docname)){
                System.out.println("Doctor Name: "+doc.First_name+" "+doc.Last_name);
                System.out.println("Price: "+doc.appointment_price);
            }
        }
    }

    public void search_doctor(List<Doctor> doctors){
        boolean a=true;
        System.out.println("Select way to search for doctor: ");
        System.out.println("1: By Name");
        System.out.println("2: By Mobile Number");
        this.choice=scanner.nextInt();
        scanner.nextLine();
        while(a){
            switch (choice){
                case 1:
                    System.out.println("Enter doctor's name: ");
                    docname= scanner.nextLine();
                    for(Doctor doc:doctors){
                        if(doc.First_name.equals(docname)){
                            System.out.println("Doctor Name: "+doc.First_name+" "+doc.Last_name);
                            System.out.println("Specialization: "+doc.Specialization);
                            System.out.println("Mobile Number: "+doc.Mobile_number);
                            System.out.println("Price: "+doc.appointment_price);
                        }
                    }
                    a=false;
                    break;
                case 2:
                    System.out.println("Enter doctor's number:");
                    docnum=scanner.nextLine();
                    for(Doctor doc:doctors){
                        if(doc.Mobile_number.equals(docnum)){
                            System.out.println("Doctor Name: "+doc.First_name+" "+doc.Last_name);
                            System.out.println("Specialization: "+doc.Specialization);
                            System.out.println("Mobile Number: "+doc.Mobile_number);
                            System.out.println("Price: "+doc.appointment_price);
                        }
                    }
                    a=false;
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        }
    }
    public void display_available_appointments(List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            System.out.println("Doctor " + doctor.First_name + " " + doctor.Last_name + "'s Schedule:");

            // Iterate through all days in the doctor's schedule
            for (String day : doctor.appointments.schedule.keySet()) {
                System.out.println(day.toUpperCase() + ":");
                doctor.appointments.displaySchedule(day); // Display time slots for each day
            }
        }
    }

    public int getId(){
        return this.id;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public List<String> getHistory() {
        return history;
    }

    public String getPrescription() {
        return prescription;
    }
}
