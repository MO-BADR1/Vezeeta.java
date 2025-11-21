import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
public class ClinicManager extends User {
        public ClinicManager(String fname, String lname, int ssn, String email, String password,  String clinicName, String address, String phone, int maxPatients) {
            super(fname, lname, ssn, email, password);
            Clinic newclinic = new Clinic(clinicName,address,phone,maxPatients);
        }

    @Override
    public void dashboard() {}

}
