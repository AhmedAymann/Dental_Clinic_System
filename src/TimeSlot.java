public class TimeSlot {
    private int hour;
    private boolean isBooked;

    public TimeSlot(int hour) {
        this.hour = hour;
        this.isBooked = false;
    }

    public int getHour() {
        return hour;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        if (!isBooked) {
            isBooked = true;
        } else {
            System.out.println("This time slot is already booked.");
        }
    }

    public void cancel() {
        if (isBooked) {
            isBooked = false;
            System.out.println("The appointment has been successfully canceled.");
        } else {
            System.out.println("This time slot is already available.");
        }
    }

    @Override
    public String toString() {
        return hour + ":00 - " + (isBooked ? "Booked" : "Available");
    }
}
