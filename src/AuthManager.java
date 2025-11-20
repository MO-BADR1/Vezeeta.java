import java.util.ArrayList;
import java.util.Scanner;

public class AuthManager {

    private Scanner sc;

    // Constructor Injection for Scanner
    public AuthManager(Scanner sc) {
        this.sc = sc;
    }

    // 1. Helper Method: Check in the doctors list
    private boolean emailExistsDoctors(String email, ArrayList<Doctor> doctors) {
        for (Doctor d : doctors) {
            if (d.getEmail().equals(email)) return true;
        }
        return false;
    }

    // 2. Helper Method: Check in the patients list
    private boolean emailExistsPatients(String email, ArrayList<Patient> patients) {
        for (Patient p : patients) {
            if (p.getEmail().equals(email)) return true;
        }
        return false;
    }

    // 3. Main Menu Display
    public int loginMenu() {
        System.out.println("\n=== Welcome to Vezeeta Clone ===");
        System.out.println("1. Login");
        System.out.println("2. Register as a Patient");
        System.out.println("3. Register as a Doctor");
        System.out.print("Please enter your choice: ");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline
        return choice;
    }

    // 4. Login Logic (Updated for two lists)
    // Return type is still 'User' because both Doctor and Patient extend User
    public User login(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // First: Search in the doctors list
        for (Doctor doc : doctors) {
            if (doc.getEmail().equals(email) && doc.getPassword().equals(password)) {
                return doc; // Found a doctor
            }
        }

        // Second: Search in the patients list
        for (Patient pat : patients) {
            if (pat.getEmail().equals(email) && pat.getPassword().equals(password)) {
                return pat; // Found a patient
            }
        }

        System.out.println("Invalid email or password!");
        return null;
    }

    // 5. Patient Registration
    public void registerPatient(ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
        System.out.println("\n--- New Patient Registration ---");

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (emailExistsPatients(email, patients) || emailExistsDoctors(email, doctors)) {
            System.out.println("Error: Email Already Exists! Try logging in.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        if (password.length() < 6) {
            System.out.println("Error: Password too short! Must be at least 6 chars.");
            return;
        }

        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter SSN: ");
        int ssn = sc.nextInt();
        sc.nextLine(); // consume newline

        Patient newPatient = new Patient(fname, lname, ssn, email, password);
        patients.add(newPatient);
        System.out.println("Patient Registered Successfully!");
    }

    // 6. Doctor Registration
    public void registerDoctor(ArrayList<Doctor> doctors, ArrayList<Patient> patients) {
        System.out.println("\n--- New Doctor Registration ---");

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (emailExistsDoctors(email, doctors) || emailExistsPatients(email, patients)) {
            System.out.println("Error: Email Already Exists! Try logging in.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        if (password.length() < 6) {
            System.out.println("Error: Password too short! Must be at least 6 chars.");
            return;
        }

        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter SSN: ");
        int ssn = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Specialization: ");
        String spec = sc.nextLine();

        System.out.print("Enter Consultation Price: ");
        int price = sc.nextInt();
        sc.nextLine();

        int id = doctors.size() + 1; // Simple ID generation
        String prescription = ""; // Default value
        ArrayList<Appointment> appointments = new ArrayList<>();
        Clinic c1 = new Clinic();

        Doctor newDoctor = new Doctor(fname, lname, ssn, email, password, id, spec, price, prescription, appointments, c1);
        doctors.add(newDoctor);
        System.out.println("Doctor Registered Successfully!");
    }
}