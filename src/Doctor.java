import java.util.ArrayList;
public class Doctor extends User {

        private int id;
        private String speciality;
        private int price;
        private String prescription;
        private ArrayList<Appointment> appointments;

    public Doctor(String fname, String lname, int ssn, String email, String password, int id, String speciality, int price , ArrayList<Appointment> appointments) {
        super(fname, lname, ssn, email, password);
        this.id = id;
        this.speciality = speciality;
        this.price = price;
        this.appointments = appointments;
    }

    public boolean addapointment(Appointment newApp) {
        for (Appointment existingApp : appointments) {
            boolean sameDate = existingApp.getDate().toString().equals(newApp.getDate().toString());
            boolean sameTime = existingApp.getTime().equals(newApp.getTime());

            if (sameDate && sameTime) {
                System.out.println("Error: This slot (" + newApp.getDate() + " at " + newApp.getTime() + ") already exists!");
                return false;
            }
        }
        appointments.add(newApp);
        System.out.println("Success: Slot added.");
        return true;
    }
    public void cancelapointment(Appointment app) {
        appointments.remove(app);
        app.cancel();
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