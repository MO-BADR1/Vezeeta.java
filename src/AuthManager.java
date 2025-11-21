import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AuthManager {

    private Scanner sc;

    // Constructor Injection for Scanner
    public AuthManager(Scanner sc) {
        this.sc = sc;
    }

    // Email validation using a simple regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
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
    public void loginMenu() {
        System.out.println("\n=== Welcome to Vezeeta ===");
        System.out.println("1. Login as a Doctor");
        System.out.println("2. Login as a Patient");
        System.out.println("3. Register as a Doctor");
        System.out.println("4. Register as a Patient");
        System.out.println("5. Exit");
        System.out.print("Please enter your choice: ");
    }
    public void caller (ArrayList<Doctor> doctors, ArrayList<Patient> patients){
        loginMenu();
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                doctorLogin(doctors, patients);
                break;
            case 2:
                patientLogin(doctors, patients);
                break;
            case 3:
                registerDoctor(doctors);
                caller(doctors, patients);
                break;
            case 4:
                registerPatient(patients);
                caller(doctors, patients);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                caller(doctors, patients);
        }
    }

    // Doctor Login
    public void doctorLogin (ArrayList<Doctor> doctors, ArrayList<Patient> patients){
        while (true){
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            for (Doctor doc : doctors) {
                if (doc.getEmail().equals(email) && doc.getPassword().equals(password)) {
                    doc.dashboard();
                    caller(doctors, patients);
                    return;
                }
            }
            System.out.println("Invalid email or password");
            System.out.println("1. Continue\n " +
                    "2. Return to main menu");
            int choice = sc.nextInt();
            if (choice == 1) continue;
            else caller(doctors, patients);
        }
    }


    // Patient Login
    public Patient patientLogin (ArrayList<Doctor> doctors, ArrayList<Patient> patients){
        while (true){
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            for (Patient pat : patients) {
                if (pat.getEmail().equals(email) && pat.getPassword().equals(password)) {
                    return pat; // Found a patient
                }
            }
            System.out.println("Invalid email or password");
            System.out.println("1. Continue\n " +
                    "2. Return to main menu");
            int choice = sc.nextInt();
            if (choice == 1) continue;
            else caller(doctors, patients);
        }
    }

    // 5. Patient Registration
    public boolean registerPatient(ArrayList<Patient> patients) {
        System.out.println("\n--- New Patient Registration ---");

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format.");
                continue;
            }
            if (emailExistsPatients(email, patients)) {
                System.out.println("Error: Email Already Exists! Try logging in.");
                return false;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("Enter Password (at least 8 characters, with numbers and letters): ");
            password = sc.nextLine();
            if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                System.out.println("Error: Password must be at least 8 characters long and contain both letters and numbers.");
            } else {
                break;
            }
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
        return true;
    }

    // 6. Doctor Registration
    public boolean registerDoctor(ArrayList<Doctor> doctors) {
        System.out.println("\n--- New Doctor Registration ---");

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format.");
                continue;
            }
            if (emailExistsDoctors(email, doctors)) {
                System.out.println("Error: Email Already Exists! Try logging in.");
                return false;
            }
            break;
        }

        String password;
        while (true) {
            System.out.print("Enter Password (at least 8 characters, with numbers and letters): ");
            password = sc.nextLine();
            if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                System.out.println("Error: Password must be at least 8 characters long and contain both letters and numbers.");
            } else {
                break;
            }
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
        ArrayList<Appointment> appointments = new ArrayList<>();

        Doctor newDoctor = new Doctor(fname, lname, ssn, email, password, id, spec, price, appointments);
        doctors.add(newDoctor);
        System.out.println("Doctor Registered Successfully!");
        return true;
    }
}