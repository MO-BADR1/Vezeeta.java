import java.util.Date;
public class Appointment {
    private Date date;
    private String time;
    private boolean isBooked;
    private String patientName;
    private String doctorName;
    private String prescription;



    public Appointment(Date date, String time,String doctorName) {
        this.date = date;
        this.time = time;
        this.isBooked = false;
        this.patientName = "";
        this.doctorName = "";
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}