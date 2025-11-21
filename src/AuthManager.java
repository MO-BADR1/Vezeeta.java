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
    private boolean emailExistsClinicManagers(String email,ArrayList<ClinicManager> clinicManagers) {
        for (ClinicManager C : clinicManagers) {
            if (C.getEmail().equals(email)) return true;
        }
        return false;
    }

    // 3. Main Menu Display
    public void loginMenu() {
        System.out.println("\n=== Welcome to Vezeeta ===");
        System.out.println("1. Login as a Doctor");
        System.out.println("2. Login as a Patient");
        System.out.println("3. Login as a Clinic Manager");
        System.out.println("4. Register as a Doctor");
        System.out.println("5. Register as a Patient");
        System.out.println("6. Register as a Clinic Manager");
        System.out.println("7. Exit");
        System.out.print("Please enter your choice: ");
    }
    public void caller (ArrayList<Doctor> doctors, ArrayList<Patient> patients,ArrayList<Clinic> Clinics,ArrayList<ClinicManager> clinicManagers){
        loginMenu();
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                doctorLogin(doctors, patients,clinicManagers,Clinics);
                break;
            case 2:
                patientLogin(doctors, patients,clinicManagers,Clinics);
                break;
            case 3:

            case 4:
                registerDoctor(doctors);
                caller(doctors, patients, Clinics, clinicManagers);
                break;
            case 5:
                registerPatient(patients);
                caller(doctors, patients, Clinics, clinicManagers);
                break;
            case 6:
                RegisterClinicManager( clinicManagers);
                caller(doctors, patients, Clinics, clinicManagers);

            case 7:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                caller(doctors, patients, Clinics, clinicManagers);
        }
    }

    // Doctor Login
    public void doctorLogin (ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<ClinicManager> clinicManagers, ArrayList<Clinic> Clinics){
        while (true){
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            for (Doctor doc : doctors) {
                if (doc.getEmail().equals(email) && doc.getPassword().equals(password)) {
                    doc.dashboard();
                    caller(doctors, patients, Clinics, clinicManagers);
                    return;
                }
            }
            System.out.println("Invalid email or password");
            System.out.println("1. Continue\n " +
                    "2. Return to main menu");
            int choice = sc.nextInt();
            if (choice == 1) continue;
            else caller(doctors, patients, Clinics, clinicManagers);
        }
    }


    // Patient Login
    public Patient patientLogin (ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<ClinicManager> clinicManagers,ArrayList<Clinic> Clinics){
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
            else caller(doctors, patients, Clinics, clinicManagers);
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
    public Boolean RegisterClinicManager(ArrayList<ClinicManager> clinicManagers) {
        System.out.println("\n--- New Clinic Manager Registration ---");

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = sc.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Error: Invalid email format.");
                continue;
            }
            if (emailExistsClinicManagers(email, clinicManagers)) {
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
        System.out.print("Enter Your Clinic Name: ");
        String clinName = sc.nextLine();
        System.out.print("Enter Your Clinic Address: ");
        String clinAddr = sc.nextLine();
        System.out.print("Enter Your Clinic Phone Number: ");
        String clinPhone = sc.nextLine();
        System.out.print("Enter Your Clinic Maximum Number Of Patients Per Day: ");
        int clinMax = sc.nextInt();
        sc.nextLine(); // consume newline

        ClinicManager  newClinicManager = new ClinicManager(fname, lname, ssn, email, password,clinName,clinAddr,clinPhone,clinMax);// CREATING NEW ONE
        clinicManagers.add(newClinicManager);
        System.out.println("Clinic Manager Registered Successfully!");
        return true;
    }
    public  ClinicManager LoginClinicManager(ArrayList<Doctor> doctors, ArrayList<Patient> patients, ArrayList<ClinicManager> clinicManagers,ArrayList<Clinic> Clinics ) {
        while (true){
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            for (ClinicManager man : clinicManagers) {
                if (man.getEmail().equals(email) && man.getPassword().equals(password)) {
                    return man; // Found a patient
                }
            }
            System.out.println("Invalid email or password");
            System.out.println("1. Continue\n " +
                    "2. Return to main menu");
            int choice = sc.nextInt();
            if (choice == 1) continue;
            else caller(doctors, patients, Clinics, clinicManagers);
        }
    }
}