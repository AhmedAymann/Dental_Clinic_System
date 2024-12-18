import java.util.*;
public class Appointments {
    public Map<String, List<TimeSlot>> schedule;
    public int appId;

    public Appointments() {
        schedule = new HashMap<>();
    }

    public void addDay(String day, List<Integer> freeHours) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int hour : freeHours) {
            timeSlots.add(new TimeSlot(hour));
        }
        schedule.put(day.toLowerCase(), timeSlots);
    }

    public void displaySchedule(String day) {
        List<TimeSlot> timeSlots = schedule.get(day.toLowerCase());
        if (timeSlots == null) {
            System.out.println("No schedule available for " + day + ".");
            return;
        }

        System.out.println("Schedule for " + day.toUpperCase() + ":");
        for (TimeSlot slot : timeSlots) {
            System.out.println("  " + slot);
        }
    }

    public void bookAppointment(String day, int hour) {
        List<TimeSlot> timeSlots = schedule.get(day.toLowerCase());
        if (timeSlots == null) {
            System.out.println("No appointments available for " + day + ".");
            return;
        }

        for (TimeSlot slot : timeSlots) {
            if (slot.getHour() == hour) {
                slot.book();
                System.out.println("Appointment booked for " + day + " at " + hour + ":00.");
                return;
            }
        }
        System.out.println("The time slot " + hour + ":00 on " + day + " is not available.");
    }

}