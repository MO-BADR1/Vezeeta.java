import java.util.ArrayList;
import java.util.Scanner;

public class ClinicManager extends User {
    private Clinic clinic;

    public ClinicManager(String fname, String lname, int ssn, String email, String password, String clinicName, String address, String phone, int maxPatients, String ClinicID) {
        super(fname, lname, ssn, email, password);
        this.clinic = new Clinic(clinicName, address, phone, maxPatients, ssn, ClinicID);
    }

    @Override
    public void dashboard() {
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n========================================");
            System.out.println("   CLINIC MANAGER DASHBOARD: " + clinic.getClinicName());
            System.out.println("========================================");
            System.out.println("1. View Doctors in Clinic");
            System.out.println("2. Add Doctor to Clinic");
            System.out.println("3. Remove Doctor from Clinic");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewDoctors();
                    Main.cls();
                    break;
                case 2:
                    Doctor.getalldoctor();
                    System.out.print("Enter the ID of the doctor to add: ");
                    int doctorIdtoadd = sc.nextInt();
                    sc.nextLine();
                    addDoctorToClinic(doctorIdtoadd);
                    Main.cls();
                    break;
                case 3:
                    System.out.println(clinic.getClinicDoctors());
                    System.out.print("Enter the ID of the doctor to remove: ");
                    int doctorIdToRemove = sc.nextInt();
                    sc.nextLine();
                    removeDoctorFromClinic(doctorIdToRemove);
                    Main.cls();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public void viewDoctors() {
        System.out.println("\n--- Doctors at " + clinic.getClinicName() + " ---");
        if (clinic.getClinicDoctors().isEmpty()) {
            System.out.println("No doctors in this clinic yet.");
        } else {
            for (Doctor doctor : clinic.getClinicDoctors()) {
                System.out.println("- Dr. " + doctor.getFname() + " " + doctor.getLname() + " (ID: " + doctor.getId() + ", Speciality: " + doctor.getSpeciality() + ")");
            }
        }
    }

    public void removeDoctorFromClinic(int doctorId) {
        if (clinic.findDoctorById(doctorId) != null) {
            clinic.removeDoctor(doctorId);
            System.out.println("Doctor with ID " + doctorId + " removed successfully from "+clinic.getClinicName());
        } else {
            System.out.println("Doctor with ID " + doctorId + " not found in this clinic.");
        }
    }

    public void addDoctorToClinic(int doctorid) {
        boolean doctoradd=false;
        if (clinic.findDoctorById(doctorid) == null) {
            for(Doctor doc : Doctor.getalldoctors())
            {
                if(doc.getId()==doctorid) {
                    clinic.addDoctor(doc);
                System.out.println("Dr. " + doc.getFname() + " has been added to " + clinic.getClinicName());
                doctoradd=true;
                break;
                }
            }
            if(!doctoradd){
                System.out.println("There is no Doctor with this ID....");
            }
        } else {
            Doctor doctor = clinic.findDoctorById(doctorid);
            System.out.println("Dr. " + doctor.getFname() + " is already in this clinic.");
        }
    }
}
