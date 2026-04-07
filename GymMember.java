/**
 * Abstract class representing a gym member in the Gym Management System.
 * This class defines common attributes and behaviors for all types of gym members
 * such as RegularMember and PremiumMember. It includes fields like personal details,
 * attendance, loyalty points, and membership status.
 * 
 * @author Rushika Magar
 */
public abstract class GymMember {
    protected int id,attendance;
    protected String name, location, phone, email, gender, DOB, membershipStartDate;
    protected double loyaltyPoints;
    protected boolean activeStatus;
    
    /**
     * Constructor for GymMember class.
     * 
     * @param id contains the unique id of the gym member
     * @param name contains the full name of the gym menber
     * @param location contains the location of the gym menber
     * @param phone contains the phone number of the gym menber
     * @param email contains the email address of the gym member 
     * @param gender contains the gender of the gym member
     * @param DOB contains the date of birth of the gym member
     * @param membershipStartDate contains the start date of gym member
     */
    public GymMember(int id, String name, String location, String phone, String email, 
                     String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
        this.activeStatus = false;
    }

    /**
     * Returns the unique ID of the gym member.
     * 
     * @return Member ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Abstract method to mark attendance for the memeber.
     * This is an abstract method to be implemented by subclasses depending
     * on the membership type (e.g., Regular or Premium).
     */
    public abstract void markAttendance();
    
    /**
     * Method to activate the gym membership for the member.
     */
    public void activateMembership() {
        this.activeStatus = true;
        
    }
    
    /**
     * Method to deactivate the gym membership for the member.
     */
    public void deactivateMembership() {
        if (activeStatus) {
            this.activeStatus = false;
        }
    }
    
    /**
     * Resets the member's status by clearing attendance and loyalty points,
     * and setting the membership as inactive.
     */
    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0.0;
    }
    
     /**
     * Returns a formatted string of the member's details for GUI display.
     * 
     * @return Formatted member information string
     */
    public String getDetails() {
        return "ID: " + id + "\n" + "Name: " + name + "\n" + "Location: " + location + "\n" + "Phone: " + phone + "\n" + "Email: " + email + "\n" + "Gender: " + gender + "\n" + "DOB: " + DOB + "\n" + "Start Date: " + membershipStartDate + "\n" + "Attendance: " + attendance + "\n" + "Loyalty Points: " + loyaltyPoints + "\n" + "Active: " + activeStatus + "\n";
    }
    
    /**
     * Method to display member's complete details.
     */
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Location: " + location);
        System.out.println("Phone: " + phone + ", Email: " + email + ", Gender: " + gender);
        System.out.println("DOB: " + DOB + ", Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance + ", Loyalty Points: " + loyaltyPoints);
        System.out.println("Active: " + activeStatus);
    }
}
