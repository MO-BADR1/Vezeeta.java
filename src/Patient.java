import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Patient extends User {
    private String medicalHistory = "No medical history";
    private ArrayList<String> chronicDiseases = new ArrayList<>();
    private ArrayList<String> surgicalOperations = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    public Patient(String fname, String lname, int ssn, String email, String password) {
        super(fname, lname, ssn, email, password);
    }

    @Override
    public void dashboard() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Patient Dashboard =====");
            System.out.println("1. View Appointments");
            System.out.println("2. Add Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. View Medical History");
            System.out.println("5. Exit Dashboard");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAppointments();
                    Main.cls();
                    break;
                case 2:
                    addAppointmentFlow(sc);
                    Main.cls();
                    break;
                case 3:
                    cancelAppointmentFlow(sc);
                    Main.cls();
                    break;
                case 4:
                    viewMedicalHistory();
                    Main.cls();
                    break;
                case 5:
                    System.out.println("Exiting dashboard...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    // ====== Appointment Management ======
    private void addAppointmentFlow(Scanner sc) {
        System.out.print("Enter Doctor First Name: ");
        String doctorName = sc.nextLine();

        Doctor doctor = null;
        for (Doctor d : Doctor.getalldoctors()) {
            if (d.getFname().equalsIgnoreCase(doctorName)) {
                doctor = d;
                break;
            }
        }

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        doctor.showAvailableAppointments();
        System.out.print("Choose appointment number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        int currentIndex = 0;
        Appointment selectedApp = null;
        for (Appointment app : doctor.getAppointments()) {
            if (app.isAvailable()) {
                currentIndex++;
                if (currentIndex == choice) {
                    selectedApp = app;
                    break;
                }
            }
        }

        if (selectedApp != null) {
            selectedApp.book(this.getFname());
            appointments.add(selectedApp);
            System.out.println("Appointment booked successfully with Dr. " + doctor.getFname());
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void cancelAppointmentFlow(Scanner sc) {
        if (appointments.isEmpty()) {
            System.out.println("No appointments to cancel.");
            return;
        }
        viewAppointments();
        System.out.print("Enter index of appointment to cancel (starting from 0): ");
        int idx = sc.nextInt();
        sc.nextLine();
        if (idx >= 0 && idx < appointments.size()) {
            Appointment appToCancel = appointments.get(idx);
            appToCancel.cancel();
            appointments.remove(appToCancel);
            System.out.println("Appointment canceled.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments for " + getFname());
            return;
        }
        System.out.println("Appointments for " + getFname() + ":");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(i + ". " + appointments.get(i).toString());
        }
    }

    // ====== Medical History ======
    public void viewMedicalHistory() {
        System.out.println("\n===== Medical History =====");
        System.out.println("General History: " + medicalHistory);

        System.out.println("\nChronic Diseases:");
        if (chronicDiseases.isEmpty()) System.out.println(" - None");
        else chronicDiseases.forEach(d -> System.out.println(" - " + d));

        System.out.println("\nSurgical Operations:");
        if (surgicalOperations.isEmpty()) System.out.println(" - None");
        else surgicalOperations.forEach(op -> System.out.println(" - " + op));
        System.out.println("===========================");
    }

    // ====== Getters & Setters ======
    public ArrayList<Appointment> getAppointments() { return appointments; }
    public void setAppointments(ArrayList<Appointment> appointments) { this.appointments = appointments != null ? appointments : new ArrayList<>(); }
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
}