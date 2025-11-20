

public class Patient extends User {
    static int count=0;
    private String patientId;
    private String name;
    private String phone;
    private String address;


    public Patient(String patientId, String name, String phone, String address) {
        this.patientId = patientId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        count++;
    }


    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}