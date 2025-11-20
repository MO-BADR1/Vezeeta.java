import java.util.ArrayList;
public class Clinic {
    private String clinicName;
    private String address;
    private String phone;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> allAppointments;
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }
    public Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getFname().equals(name)) {
                return doctor;
            }
        }
        return null;

    }
    public Doctor findDoctorById(int id) {
    for (Doctor doctor : doctors) {
        if (doctor.getId() == id) {
            return doctor;
        }
    }
    return null;
    }
    public void  booking(Doctor doctor, Patient patient,Appointment appy) {
        appy.book(patient.getFname());
        allAppointments.add(appy);
    }





}
