/**
 * PremiumMember class contains gym member with a premium membership with additional benefits for the Gym Management System.
 * This class extends the abstract class GymMember and includes attributes and additional methods such as personal trainer assignment, premium payment tracking,
 * discount calculation, and extended loyalty benefits.
 * 
 * @author Rushika Magar
 */
public class PremiumMember extends GymMember {
    private static final double premiumCharge = 50000;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    
    /**
     * Constructor for PremiumMember class.
     * 
     * @param id contains the id of the premium member
     * @param name contains the name of the premium menber
     * @param location contains the location of the premium menber
     * @param phone contains the phone number of the premium menber
     * @param email contains the email address of the premium member 
     * @param gender contains the gender of the premium member
     * @param DOB contains the date of birth of the premium member
     * @param membershipStartDate contains the start date of premium member
     * @param personalTrainer contains the assigned personal trainer name of premium member
     */
    public PremiumMember(int id, String name, String location, String phone, String email, 
                         String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }
    
     /**
     * Returns the fixed charge for a premium membership.
     * 
     * @return Premium membership charge
     */
    public double getPremiumCharge() {
    return this.premiumCharge;
    }
    
     /**
     * Returns the discount amount applied after full payment.
     * 
     * @return Discount amount
     */
    public double getDiscountAmount() {
        return this.discountAmount;
    }
    
    /**
     * Checks if full payment for the premium membership has been made.
     * 
     * @return true if full payment is completed, false otherwise
     */
    public boolean isPaymentComplete() {
        return this.isFullPayment;
    }
    
    /**
     * Returns the total amount paid by the member.
     * 
     * @return Paid amount
     */
    public double getPaidAmount() {
    return this.paidAmount;
    }
    
    /**
     * Method to mark attendance for the premium member with loyalty points.
     * Each attendance adds 10 loyalty points, only if the membership is active.
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 10;
        }
    }
    
    /**
     * Method to pay due amount for a premium membership fee.
     * @param amount contains the amount being paid.
     * @return A message indicating the payment status or remaining amount
     */
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment is already completed.";
        }
        paidAmount += amount;
        if (paidAmount > premiumCharge) {
            return "Error: Paid amount exceeds premium charge.";
        }
        if (paidAmount == premiumCharge) {
            isFullPayment = true;
        }
        return "Payment successful. Remaining amount: " + (premiumCharge - paidAmount);
    }
    
    /**
     * Method to calculate and displays the discount for a premium member.
     * A 10% discount is applied only if full payment has been completed.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            discountAmount = 0.1 * premiumCharge;
            System.out.println("Discount applied: " + discountAmount);
        }
    }
    
    /**
     * Method to reset details for a premium member to default values.
     * This method reverts the premium member by:
     * -Resetting common attributes using the superclass method.
     * -Clearing the personal trainer's name.
     * -Marking the payment as not completed.
     * -Resetting the paid amount and discount amount to zero.
     * 
     */
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }
    
    /**
     * Method overrides the parent class to display the details of members including personal trainer's name, paid amount, full payment status,
     * remaining amount and discount amount.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount + ", Is Full Payment: " + isFullPayment);
        System.out.println("Remaining Amount: " + (premiumCharge - paidAmount));
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}