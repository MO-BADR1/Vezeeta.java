public class Patient extends User {
    static int count=0;
    static int id=1;

    public int createReservation(String patientName, String appointmentDate,
                                    String appointmentTime, String doctorName) {

        int reservationId =id;
        id += 1;


        System.out.println("Reservation Done");
        System.out.println("   ID: " + reservationId);
        System.out.println("Patient Name: " + patientName);
        System.out.println("   Doctor :D. " + doctorName);
        System.out.println("  Date  " + appointmentDate + " hour " + appointmentTime);

        return reservationId;
    }
}