import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Doctor extends User {
        private ArrayList<Appointment> appointments=new ArrayList<>();

    public static ArrayList<Doctor> getAlldoctors() {
        return alldoctors;
    }

    public static void setAlldoctors(ArrayList<Doctor> alldoctors) {
        Doctor.alldoctors = alldoctors;
    }

    Scanner sc=new Scanner(System.in);
        private int id;
        private String speciality;
        private int price;
        private static ArrayList<Doctor> alldoctors=new ArrayList<>();

    public Doctor(String fname, String lname, int ssn, String email, String password, int id, String speciality, int price , ArrayList<Appointment> appointments) {
        super(fname, lname, ssn, email, password);
        this.id = id;
        this.speciality = speciality;
        this.price = price;
        this.appointments = appointments;
        alldoctors.add(this);
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
     //   app.cancel();
    }
    public void showAvailableAppointments() {
        System.out.println("\n--- Available appointments for Dr. " + this.getFname() + " ---");

        boolean found = false;

        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);

            if (app.isAvailable()) {
                System.out.println("[" + (i+1) + "] " + app.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no available slots for this doctor currently.");
        }
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

        public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    int choice=-1;

    public void showAllAppointmentsForCancellation() {
        if (appointments.isEmpty()) {
            System.out.println("You currently have no appointment slots scheduled.");
            return;
        }

        System.out.println("\n--- All Scheduled Slots for Dr. " + this.getFname() + " ---");
        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);
            // Uses the toString() method you fixed in Appointment.java
            System.out.println("[" + (i + 1) + "] " + app.toString());
        }
    }
    @Override
    public void dashboard() {
       
        do {
            System.out.println("\n========================================");
            System.out.println("   DOCTOR DASHBOARD: Dr. " + this.getFname());
            System.out.println("========================================");
            System.out.println("1. View My Available Appointments");
            System.out.println("2. Add New Appointment Slot");
            System.out.println("3. Cancel an Appointment");
            System.out.println("4. Update Consultation Price");
            System.out.println("5. View My Profile");
            System.out.println("6. Add Prescription");
            System.out.println("7. Find Doctor's Clinics");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    this.showAvailableAppointments();
                    Main.cls();
                    break;
                case 2:
                    System.out.println("Enter Date Details:");
                    System.out.print("Day: ");
                    int day = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    System.out.print("Month: ");
                    int month = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    System.out.print("Enter Time (e.g. 5:00PM): ");
                    String time = sc.nextLine();
                    Date reqDate = new Date(year - 1900, month - 1, day);
                    Appointment newApp = new Appointment(reqDate, time, this.getFname());
                    this.addapointment(newApp);
                    Main.cls();
                    break;
                case 3:
                    this.showAllAppointmentsForCancellation();

                    if (appointments.isEmpty()) {
                        System.out.println("There is no appointment slot to cancel.");
                        Main.cls();
                        break;
                    }

                    System.out.print("Enter the number of the appointment slot to cancel (1-" + appointments.size() + "): ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        sc.nextLine();
                        Main.cls();
                        break;
                    }
                    int indexToCancel = sc.nextInt();
                    sc.nextLine();

                    if (indexToCancel >= 1 && indexToCancel <= appointments.size()) {
                        Appointment toRemove = appointments.get(indexToCancel - 1);
                        cancelapointment(toRemove);
                        System.out.println("Success: Slot [" + indexToCancel + "] deleted: " + toRemove.toString());
                    } else {
                        System.out.println("Error: Invalid number. No slot found for that index.");
                    }
                    Main.cls();
                    break;

                case 4:
                    System.out.print("Enter new price: ");
                    price = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                    this.setPrice(price);
                    Main.cls();
                    break;
                case 5:
                    System.out.println(this.toString());
                    Main.cls();
                    break;
                case 6:
                    System.out.println("Add Prescription - Not implemented yet.");
                    Main.cls();
                    break;
                case 7:
                    System.out.println("Find Doctor's Clinics");
                    Clinic.viewDoctorClinics(this.getId());
                    Main.cls();
                    break;
                case 0:
                    System.out.println("Logging out...");

                    break;

                default:
                    System.out.println("Invalid choice!");
            }


        }
            while(choice!=0);
    }

    @Override
    public String toString() {
        return "Doctor"+this.getFname()+"{" +
                "id=" + id +
                ", speciality='" + speciality + '\'' +
                ", price=" + price +
                "} ";
    }
    public static ArrayList<Doctor> getalldoctors(){
       return alldoctors;
    }
    //This is for printing all doctors
    public static void getalldoctor(){
        for (int i = 0; i < alldoctors.size(); i++) {
            Doctor doc = alldoctors.get(i);
            // Uses the toString() method you fixed in Appointment.java
            System.out.println("[" + (i + 1) + "] " + doc.toString());
        }
    }
//    public void getDoctorClinics(){
//        for ()
//    }
}
