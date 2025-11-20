import java.util.ArrayList;
import java.util.Scanner;

public class Clinic {
    private String clinicName;
    private String address;
    private String phone;
    private ArrayList<Doctor> doctorsClinic= new ArrayList<>();
    private ArrayList<Appointment> allAppointments= new ArrayList<>();
    private int maxPatients=0;
    private static int dayPatient=0;

    public Clinic(String clinicName, String address, String phone,int maxPatients) {
        this.clinicName = clinicName;
        this.address = address;
        this.phone = phone;
        this.maxPatients = maxPatients;
    }
    public void addDoctor(Doctor doctor) {
        doctorsClinic.add(doctor);

    }
    public boolean bookAppointment(Patient patient, int maxCapacity) {
        if (this.dayPatient >= maxCapacity) {
            System.out.println("Sorry, the clinic is at full capacity today.");
            return false;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor First name: ");
        String reqDr = sc.next();

        boolean doctorFound = false;

        for (Doctor dr : doctorsClinic) {
            if (reqDr.equalsIgnoreCase(dr.getFname())) {
                doctorFound = true;
                dr.showAvailableAppointments();

                System.out.print("\nChoose Suitable appointment number: ");
                int choice = sc.nextInt();
                System.out.println();

                int currentAvailableIndex = 0;
                Appointment selectedApp = null;

                for (Appointment app : dr.getAppointments()) {
                    if (app.isAvailable()) {
                        currentAvailableIndex++;
                        if (currentAvailableIndex == choice) {
                            selectedApp = app;
                            break;
                        }
                    }
                }

                if (selectedApp != null) {
                    selectedApp.setBooked(true);
                    // selectedApp.setPatient(patient);
                    dayPatient++;
                    System.out.println("Appointment booked successfully for Dr. " + dr.getFname());
                    return true;
                } else {
                    System.out.println("Invalid choice.");
                }
                break;
            }
        }

        if (!doctorFound) {
            System.out.println("Doctor with name " + reqDr + " not found.");
        }

        return false;
    }
        public Doctor findDoctorByName(String name){
            for (Doctor doctor : doctorsClinic) {
                if (doctor.getFname().equals(name)) {
                    return doctor;
                }
            }
            return null;

        }
        public Doctor findDoctorById ( int id){
            for (Doctor doctor : doctorsClinic) {
                if (doctor.getId() == id) {
                    return doctor;
                }
            }
            return null;
        }
        public void booking (Doctor doctorsClinic, Patient patient, Appointment appy){
            appy.book(patient.getFname());
            allAppointments.add(appy);
        }


    }



