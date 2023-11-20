package carrentalsystem;

public class Customer extends User {
    protected String phoneNumber;
    protected String drivingLicenseNo;

    public Customer(String username, String password, String phoneNumber, String drivingLicenseNo) {
        super(username, password);
        this.phoneNumber = phoneNumber;
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setPhoneNumber(String phoneNumber2) {
    }

    public void setDrivingLicenseNo(String drivingLicenseNo2) {
    }
    
}
