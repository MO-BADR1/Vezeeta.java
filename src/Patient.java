import java.util.ArrayList;
public class Patient extends User {
    private static int count=0;
    private static int id=1;
    private String medicalHistory="No medical history";
    private ArrayList<String>chrinicDiseses= new ArrayList<>();
    private ArrayList<String>surgicalOperations= new ArrayList<>();
    private ArrayList<Appointment> appointments= new ArrayList<>();



    public Patient(String fname, String lname, int ssn, String email, String password, String medicalHistory, ArrayList<String> chrinicDiseses, ArrayList<String> surgicalOperations, ArrayList<Appointment> appointments) {
        super(fname, lname, ssn, email, password);
        this.medicalHistory = medicalHistory;
        this.chrinicDiseses = chrinicDiseses;
        this.surgicalOperations = surgicalOperations;
        this.appointments = appointments;
    }
    public void addAppointment(Appointment app){

    }
    /*
   Add appointment
    * */
        /*
   edit appointment
    * */
        /*
   cancel appointment
    * */
           /*
   View appointment
    * */



    public int createReservation(String patientName, String appointmentDate,
                                    String appointmentTime, String doctorName) {

        int reservationId =id;
        id += 1;


        System.out.println("Reservation Done");
        System.out.println("   ID: " + reservationId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("   Doctor :D. " + doctorName);
        System.out.println("  Date  " + appointmentDate + " hour " + appointmentTime);

        return reservationId;
    }
}