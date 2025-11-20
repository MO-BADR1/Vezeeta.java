import java.util.ArrayList;
public class Doctor extends User {

        private int id;
        private String speciality;
        private int price;
        private String prescription;
        private ArrayList<Appointment> appointments;
        Clinic c1 = new Clinic();

    public Doctor(String fname, String lname, int ssn, String email, String password, int id, String speciality, int price, String prescription, ArrayList<Appointment> appointments, Clinic c1) {
        super(fname, lname, ssn, email, password);
        this.id = id;
        this.speciality = speciality;
        this.price = price;
        this.prescription = prescription;
        this.appointments = appointments;
        this.c1 = c1;
    }

    public void addapointment(Appointment app) {
            appointments.add(app);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getPrescription() {
            return prescription;
        }

        public void setPrescription(String prescription) {
            this.prescription = prescription;
        }


    }