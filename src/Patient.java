import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class Patient extends User {
    private String medicalHistory = "No medical history";
    private ArrayList<String> chrinicDiseses = new ArrayList<>();
    private ArrayList<String> surgicalOperations = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Patient(String fname, String lname, int ssn, String email, String password,
                   String medicalHistory, ArrayList<String> chrinicDiseses,
                   ArrayList<String> surgicalOperations, ArrayList<Appointment> appointments) {
        super(fname, lname, ssn, email, password);
        this.medicalHistory = medicalHistory != null ? medicalHistory : "No medical history";
        this.chrinicDiseses = chrinicDiseses != null ? chrinicDiseses : new ArrayList<>();
        this.surgicalOperations = surgicalOperations != null ? surgicalOperations : new ArrayList<>();
        this.appointments = appointments != null ? appointments : new ArrayList<>();
    }

    public Patient(String fname, String lname, int ssn, String email, String password) {
        super(fname, lname, ssn, email, password);
    }

    public boolean addAppointment(Doctor doctor, Appointment newApp, Clinic clinic) {
        if (doctor == null || newApp == null || clinic == null) return false;

        boolean addedToDoctor = doctor.addapointment(newApp);
        if (!addedToDoctor) return false;

        newApp.setBooked(true);
        clinic.booking(doctor, this, newApp);

        appointments.add(newApp);
        return true;
    }

    public boolean editAppointment(Doctor doctor, Appointment existingApp, Date newDate, String newTime, Clinic clinic) {
        if (doctor == null || existingApp == null || clinic == null) return false;
        if (!appointments.contains(existingApp)) return false;

        // remove old slot from doctor
        doctor.cancelapointment(existingApp);
        
        Appointment modified = new Appointment(newDate, newTime, doctor.getFname());

        boolean added = doctor.addapointment(modified);
        if (!added) {
            // restore original slot
            doctor.addapointment(existingApp);
            return false;
        }

        modified.setBooked(true);
        clinic.booking(doctor, this, modified);

        // replace reference in patient's list (by reference equality)
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i) == existingApp) {
                appointments.set(i, modified);
                break;
            }
        }
        return true;
    }

    public void cancelAppointment(Doctor doctor, Appointment app) {
        if (doctor == null || app == null) return;
        if (!appointments.contains(app)) return;

        doctor.cancelapointment(app);
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments for " + getFname());
            return;
        }
        System.out.println("Appointments for " + getFname() + ":");
        for (Appointment a : appointments) {
            System.out.println(" - Date: " + a.getDate() + " Time: " + a.getTime()
                    + " Doctor: " + (a.getDoctorName() != null && !a.getDoctorName().isEmpty() ? a.getDoctorName() : "N/A")
                    + " Booked: " + (!a.isAvailable()));
        }
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments != null ? appointments : new ArrayList<>();
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
