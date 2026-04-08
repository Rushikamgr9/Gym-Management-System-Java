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
    
   
}