import java.sql.Date;

public class Appointment {
    private Date date;
    private String time;
    private boolean isBooked;
    private String patientName;


    public Appointment(Date date, String time) {
        this.date = date;
        this.time = time;
        this.isBooked = false;
        this.patientName = "";
    }

    public void book(String patientName) {
        this.isBooked = true;
        this.patientName = patientName;
    }

    public void cancel() {
        this.isBooked = false;
        this.patientName = "";
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    // this is to make the patient access his appointment and the doctor access patient
    public String getPatientName() { return patientName; }
    public String getTime() { return time; }
    public Date getDate() {
        return date;
    }
}