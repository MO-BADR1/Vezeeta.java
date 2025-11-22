import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
public class ClinicManager extends User {
        public ClinicManager(String fname, String lname, int ssn, String email, String password,  String clinicName, String address, String phone, int maxPatients,String ClinicID) {
            super(fname, lname, ssn, email, password);
            Clinic newclinic = new Clinic(clinicName,address,phone,maxPatients,ssn,ClinicID);
        }

    @Override
    public void dashboard() {}
    public void removeDoctorFromClinic(Doctor doctor,ArrayList<Doctor> doctors, int RemovedDoctorID,int managerSSN ) {
            for(Doctor D :  doctors ){
                if(RemovedDoctorID==D.getId()){
                    doctors.remove(D);
                }
            }
    }



}
