import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthManager authManager = new AuthManager(sc);
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Clinic> Clinics = new ArrayList<>();
        ArrayList<ClinicManager> clinicManagers = new ArrayList<>();

        authManager.caller(doctors,patients,Clinics,clinicManagers);
    }
    public static void cls() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        sc.nextLine();
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}