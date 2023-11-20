package carrentalsystem;

import java.util.Scanner;

public class CarRentalSystem {
    private static Admin[] admins = new Admin[100];
    private static Customer[] customers = new Customer[100];
    private static Car[] cars = new Car[100];
    private static Rental[] rentals = new Rental[100];
    private static Feedback[] feedbacks = new Feedback[100];
    private static User loggedInUser = null;
    private static Scanner scanner = new Scanner(System.in);
    private static double totalIncome = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Car Rental Management System");
            if (loggedInUser == null) {
                System.out.println("1. Sign Up as Admin");
                System.out.println("2. Sign Up as Customer");
                System.out.println("3. Login");
            } else {
                System.out.println("Logged in as: " + loggedInUser.getUsername());
                if (loggedInUser instanceof Admin) {
                    System.out.println("4. Add Car");
                    System.out.println("5. Total Income");
                    System.out.println("6. Car Info");
                    System.out.println("7. Display Feedback");
                    System.out.println("8. Logout");
                    System.out.println("10. display Customer Info");
                } else if (loggedInUser instanceof Customer) {
                    System.out.println("4. Available Cars");
                    System.out.println("5. Rent a Car");
                    System.out.println("6. Add Customer Info");
                    System.out.println("7. Give Feedback");
                    System.out.println("8. Logout");
                }
            }
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

            switch (choice) {
                case 1:
                    signUpAsAdmin();
                    break;
                case 2:
                    signUpAsCustomer();
                    break;
                case 3:
                    login();
                    break;
                case 4:
                    if (loggedInUser != null) {
                        if (loggedInUser instanceof Admin) {
                            addCar();
                        } else {
                            displayAvailableCars();
                        }
                    } else {
                        System.out.println("Login required.");
                    }
                    break;
                case 5:
                    if (loggedInUser != null) {
                        if (loggedInUser instanceof Admin) {
                            calculateTotalIncome();
                        } else {
                            rentCar();
                        }
                    } else {
                        System.out.println("Login required.");
                    }
                    break;
                case 6:
                    if (loggedInUser != null) {
                        if (loggedInUser instanceof Admin) {
                            displayCarInfo();
                        } else if (loggedInUser instanceof Customer) {
                            addCustomerInfo();
                        }
                    } else {
                        System.out.println("Login required.");
                    }
                    break;
                case 7:
                    if (loggedInUser != null) {
                        if (loggedInUser instanceof Admin) {
                            displayFeedback();
                        } else if (loggedInUser instanceof Customer) {
                            giveFeedback();
                        }
                    } else {
                        System.out.println("Login required.");
                    }
                    break;
                case 8:
                    logout();
                    break;
                case 9:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                case 10: // Add this new case
                DisplayCustomerInfo();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  
            }
        }
    }

    private static void DisplayCustomerInfo() {
    }

    private static void signUpAsAdmin() {
        if (loggedInUser == null) {
            try {
                System.out.print("Enter a new admin username: ");
                String username = scanner.nextLine();
                
                // Validate if the username already exists
                if (findUser(username) != null) {
                    throw new IllegalArgumentException("Username already exists.");
                }
    
                System.out.print("Enter a password (8 characters with both alphabets and numbers): ");
                String password = scanner.nextLine();
    
                // Validate password format
                if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")) {
                    throw new IllegalArgumentException("Password must be 8 characters with both alphabets and numbers.");
                }
    
                for (int i = 0; i < admins.length; i++) {
                    if (admins[i] == null) {
                        admins[i] = new Admin(username, password);
                        System.out.println("Admin sign up successful!");
                        return;
                    }
                }
                System.out.println("Array size exceeded. Cannot add more admins.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("You are already logged in. Logout to sign up as an admin.");
        }
    }
    
    private static void signUpAsCustomer() {
        if (loggedInUser == null) {
            try {
                System.out.print("Enter a new customer username: ");
                String username = scanner.nextLine();
                
                // Validate if the username already exists
                if (findUser(username) != null) {
                    throw new IllegalArgumentException("Username already exists.");
                }
    
                System.out.print("Enter a password (8 characters with both alphabets and numbers): ");
                String password = scanner.nextLine();
    
                // Validate password format
                if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")) {
                    throw new IllegalArgumentException("Password must be 8 characters with both alphabets and numbers.");
                }
    
                for (int i = 0; i < customers.length; i++) {
                    if (customers[i] == null) {
                        customers[i] = new Customer(username, password, "", "");
                        System.out.println("Customer sign up successful!");
                        return;
                    }
                }
                System.out.println("Array size exceeded. Cannot add more customers.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("You are already logged in. Logout to sign up as a customer.");
        }
    }
    
    private static void login() {
        if (loggedInUser == null) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = findUser(username);
            if (user != null && user.verifyPassword(password)) {
                loggedInUser = user;
                System.out.println("Logged in as " + loggedInUser.getUsername());
            } else {
                System.out.println("Invalid username or password.");
            }
        } else {
            System.out.println("You are already logged in.");
        }
    }

    private static void logout() {
        if (loggedInUser != null) {
            System.out.println("Logged out successfully.");
            loggedInUser = null;
        } else {
            System.out.println("You are not logged in.");
        }
    }

    private static User findUser(String username) {
        for (Admin admin : admins) {
            if (admin != null && admin.getUsername().equals(username)) {
                return admin;
            }
        }
        for (Customer customer : customers) {
            if (customer != null && customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }
    
    private static void addCar() {
        if (loggedInUser instanceof Admin) {
            System.out.print("Enter car name: ");
            String carName = scanner.nextLine();
    
            // Validate that car name is not empty
            if (carName.trim().isEmpty()) {
                System.out.println("Car name cannot be empty. Please try again.");
                return;
            }
    
            System.out.println("Select car type: Economy, Standard, Luxury");
            System.out.print("Enter car type: ");
            String carType = scanner.nextLine();
    
            // Validate that car type is not empty
            if (carType.trim().isEmpty()) {
                System.out.println("Car type cannot be empty. Please try again.");
                return;
            }
    
            System.out.print("Enter daily rate: ");
    
            // Validate that daily rate is a valid double
            double dailyRate;
            try {
                dailyRate = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid daily rate. Please enter a valid number.");
                return;
            }
    
            // Validate that daily rate is not negative
            if (dailyRate < 0) {
                System.out.println("Daily rate cannot be negative. Please try again.");
                return;
            }
    
            for (int i = 0; i < cars.length; i++) {
                if (cars[i] == null) {
                    cars[i] = new Car(carName, carType, dailyRate);
                    System.out.println("Car added successfully.");
                    return;
                }
            }
            System.out.println("Array size exceeded. Cannot add more cars.");
        } else {
            System.out.println("Admin login required to add a car.");
        }
    }
    
    
    private static void calculateTotalIncome() {
        if (loggedInUser instanceof Admin) {
            System.out.println("Total Income: $" + totalIncome);
        } else {
            System.out.println("Admin login required to calculate total income.");
        }
    }
    
    private static void displayCarInfo() {
        for (Car car : cars) {
            if (car != null) {
                System.out.println("Car: " + car.getCarName() + ", Type: " + car.getCarType() + ", Daily Rate: $" + car.getDailyRate());
            }
        }
    }
    
    private static void addCustomerInfo() {
        if (loggedInUser instanceof Customer) {
            Customer customer = (Customer) loggedInUser;
            
            // Validate phone number
            System.out.print("Enter phone number (10 digits): ");
            String phoneNumber = scanner.nextLine();
            if (!phoneNumber.matches("\\d{10}")) {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
                return;
            }
    
            // Validate driving license number
            System.out.print("Enter driving license number (uppercase alphabets and integers): ");
            String drivingLicenseNo = scanner.nextLine();
            if (!drivingLicenseNo.matches("[A-Z\\d]+")) {
                System.out.println("Invalid driving license number. Use uppercase alphabets and integers only.");
                return;
            }
    
            customer.setPhoneNumber(phoneNumber);
            customer.setDrivingLicenseNo(drivingLicenseNo);
            System.out.println("Customer information updated.");
        } else {
            System.out.println("Customer login required to add customer info.");
        }
    }    
    
    private static void displayAvailableCars() {
        System.out.println("Available Cars:");
        for (Car car : cars) {
            if (car != null) {
                System.out.println("Car: " + car.getCarName() + ", Type: " + car.getCarType() + ", Daily Rate: $" + car.getDailyRate());
            }
        }
    }
    
    private static void rentCar() {
        if (loggedInUser instanceof Customer) {
            System.out.print("Enter car name: ");
            String carName = scanner.nextLine();
            System.out.print("Enter rental date (YYYY-MM-DD): ");
            String rentalDate = scanner.nextLine();
            System.out.print("Enter rental days: ");
            int rentalDays = scanner.nextInt();
    
            Car car = null;
            for (Car c : cars) {
                if (c != null && c.getCarName().equals(carName)) {
                    car = c;
                    break;
                }
            }
    
            if (car != null) {
                for (int i = 0; i < rentals.length; i++) {
                    if (rentals[i] == null) {
                        rentals[i] = new Rental(loggedInUser, car, rentalDate, rentalDays);
                        totalIncome += rentalDays * car.getDailyRate();
                        System.out.println("Car rented successfully. Total charge: $" + rentalDays * car.getDailyRate());
                        return;
                    }
                }
                System.out.println("Array size exceeded. Cannot add more rentals.");
            } else {
                System.out.println("Car not found.");
            }
        } else {
            System.out.println("Customer login required to rent a car.");
        }
    }
    
    private static void giveFeedback() {
        if (loggedInUser instanceof Customer) {
            System.out.print("Enter your feedback (minimum 20 characters): ");
            String feedbackText = scanner.nextLine();
            
            try {
                // Validate feedback length
                if (feedbackText.length() < 20) {
                    throw new IllegalArgumentException("Feedback must be at least 20 characters.");
                }
    
                for (int i = 0; i < feedbacks.length; i++) {
                    if (feedbacks[i] == null) {
                        feedbacks[i] = new Feedback(loggedInUser, feedbackText);
                        System.out.println("Thank you for your feedback!");
                        System.out.println(" ");
                        return;
                    }
                }
                System.out.println("Array size exceeded. Cannot add more feedbacks.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Customer login required to provide feedback.");
        }
    }
     
    private static void displayFeedback() {
        for (Feedback feedback : feedbacks) {
            if (feedback != null) {
                System.out.println("User: " + feedback.getUser().getUsername() + ", Feedback: " + feedback.getFeedback());
            }
        }
    }
    
}
