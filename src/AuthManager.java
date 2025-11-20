import java.util.ArrayList;
import java.util.Scanner;

public class AuthManager {
    public int loginMenu (){
        System.out.println("1. Login" +
                "\n2. SignUp");
        int choice;
        System.out.println("Please enter your choice : ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        sc.nextLine();
        return (choice);
    }
    public User login(String email, String password, ArrayList<User> allUsers) {
        for (User user : allUsers) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // patient registration
    public boolean PatientRegister(Patient newPatient, ArrayList<User> allUsers) {
        // checking if the email is found
        for (User user : allUsers) {
            if (user.getEmail().equals(newPatient.getEmail())) {
                System.out.println("Email Already Exists");
                return false;
            }
        }

        // password validation
        if (newPatient.getPassword().length() < 6) {
            System.out.println("Password too short!");
            return false;
        }
        // if valid add new patient
        allUsers.add(newPatient);
        System.out.println("Register successfully!");
        return true;
    }
    // Dr registration
    public boolean DrRegister(Doctor newDoctor, ArrayList<User> allUsers) {
        // checking if the email is found
        for (User user : allUsers) {
            if (user.getEmail().equals(newDoctor.getEmail())) {
                System.out.println("Email Already Exists");
                return false;
            }
        }

        // password validation
        if (newDoctor.getPassword().length() < 6) {
            System.out.println("Password too short!");
            return false;
        }
        // if valid add new patient
        allUsers.add(newDoctor);
        System.out.println("Register successfully!");
        return true;
    }
}
