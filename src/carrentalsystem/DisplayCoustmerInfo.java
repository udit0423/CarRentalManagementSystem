package carrentalsystem;

public class DisplayCoustmerInfo {
    public static void displayCustomerInfo(Customer customer) {
        System.out.println("Customer Information:");
        System.out.println("Username: " + customer.getUsername());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.println("Driving License Number: " + customer.getDrivingLicenseNo());
    }
    
}
