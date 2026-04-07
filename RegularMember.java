/**
 * RegularMember class contains gym member with a regular membership for the Gym Management System.
 * This class extends the abstract class GymMember and includes additional attributes such as membership plan, upgrade eligibility, referral source,
 * and removal reason and methods for tracking attendance, upgrading membership plans, and resetting member details.
 * 
 * @author Rushika Magar
 */
public class RegularMember extends GymMember {
    private static final int attendanceLimit = 30;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;
    
    /**
     * Constructor for RegularMember class.
     * 
     * @param id contains the id of the regular member
     * @param name contains the name of the regular menber
     * @param location contains the location of the regular menber
     * @param phone contains the phone number of the regular menber
     * @param email contains the email address of the regular member 
     * @param gender contains the gender of the regular member
     * @param DOB contains the date of birth of the regular member
     * @param membershipStartDate contains the start date of regular member
     * @param referralSource contains the referral source of regular member
     */
    public RegularMember(int id, String name, String location, String phone, String email, 
                         String gender, String DOB, String membershipStartDate, String referralSource, String plan, double paidAmount) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = "";
        this.referralSource = referralSource;
    }
    
    /**
     * Method to mark attendance for the regular member.
     * Increase attendance count and loyalty points.
     * Also checks if the member has become eligible for an upgrade.
     */
    @Override
    public void markAttendance() {
        if (activeStatus) {
            attendance++;
            loyaltyPoints += 5;
            if (attendance >= attendanceLimit) {
                isEligibleForUpgrade = true;
            }
        }
    }
    
    
    /**
     * Method to get the price of a selected plan.
     * @param plan contains the name of the plan choosen by the member.
     * @return Price of the plan, or -1 if the plan is invalid
     */
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;
        }
    }
    
    /**
     * Method to upgrade the current membership plan to a new plan of a membership.
     * @param newPlan contains the new plan to upgrade choosen by the member.
     * @return A message indicating the result of the upgrade attempt
     */
    public String upgradePlan(String newPlan) {
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected.";
        }
        if (!isEligibleForUpgrade) {
            return "Member is not eligible for an upgrade.";
        }
        if (this.plan.equalsIgnoreCase(newPlan)) {
            return "Already subscribed to this plan.";
        }
        this.plan = newPlan;
        this.price = newPrice;
        return "Plan upgraded successfully to " + newPlan;
    }


    
   
}