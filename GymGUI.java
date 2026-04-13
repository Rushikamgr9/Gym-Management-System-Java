/**
 * GUI class is for the Gym Management System.
 * This class manages the gym system using a GUI and an array list to store all (regular and premium) gym members.
 * 
 * @author Rushika Magar
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.table.DefaultTableModel;


/**
 * The GUI interface includes various input fields, buttons, combo boxes and controls member details.
 * ArrayList stores gym member (both regular and premium) information.
 */

public class GymGUI extends JFrame {
    private ArrayList<GymMember> members = new ArrayList<>();

    private JTextField idField, nameField, locationField, phoneField, emailField,
            referralField, paidField, removalReasonField, trainerField;
    private JTextField regularPriceField, premiumPriceField, discountField;

    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;

    private JComboBox<String> dobYear, dobMonth, dobDay;
    private JComboBox<String> msYear, msMonth, msDay;
    private JComboBox<String> planBox;

    /**
     * Constructor for the GymGUI class.
     * 
     * This constructor initializes the main user interface for the Gym Membership Management System.
     * It sets up the JFrame properties and adds all necessary form fields, labels, combo boxes,
     * radio buttons, and buttons to the layout using a GridLayout for structured alignment.
     * 
     * Components initialized include:
     * - Text fields for member details (ID, name, location, phone, email, etc.)
     * - Combo boxes for selecting date of birth and membership start date
     * - Radio buttons for selecting gender
     * - Non-editable fields for displaying predefined prices and discount
     * - A combo box for selecting membership plans (Regular members only)
     * - Buttons for various operations (e.g., adding members, activating/deactivating membership,
     *   marking attendance, upgrading plans, calculating discounts, reverting members, 
     *   payment handling, displaying data, saving to and reading from a file)
     * 
     * The constructor builds the GUI form and prepares it for user interaction.
     */
    public GymGUI() {
        setTitle("Gym Membership GUI");
        setLayout(new GridLayout(0, 4, 10, 10));
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fields setup
        idField = new JTextField();
        nameField = new JTextField();
        locationField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        referralField = new JTextField();
        paidField = new JTextField();
        removalReasonField = new JTextField();
        trainerField = new JTextField();

        regularPriceField = new JTextField("50");
        regularPriceField.setEditable(false);
        premiumPriceField = new JTextField("100");
        premiumPriceField.setEditable(false);
        discountField = new JTextField("10");
        discountField.setEditable(false);

        // Gender radio buttons
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Combo boxes
        dobYear = new JComboBox<>(generateNumbers(1950, 2025));
        dobMonth = new JComboBox<>(generateNumbers(1, 12));
        dobDay = new JComboBox<>(generateNumbers(1, 31));
        msYear = new JComboBox<>(generateNumbers(2020, 2030));
        msMonth = new JComboBox<>(generateNumbers(1, 12));
        msDay = new JComboBox<>(generateNumbers(1, 31));
        planBox = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});

        // Add fields
        add(new JLabel("Member ID:")); add(idField);
        add(new JLabel("Name:")); add(nameField);
        add(new JLabel("Location:")); add(locationField);
        add(new JLabel("Phone:")); add(phoneField);
        add(new JLabel("Email:")); add(emailField);

        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadio); genderPanel.add(femaleRadio); 
        add(genderPanel);

        add(new JLabel("DOB:"));
        JPanel dobPanel = new JPanel();
        dobPanel.add(dobYear); dobPanel.add(dobMonth); dobPanel.add(dobDay);
        add(dobPanel);

        add(new JLabel("Membership Start Date:"));
        JPanel msPanel = new JPanel();
        msPanel.add(msYear); msPanel.add(msMonth); msPanel.add(msDay);
        add(msPanel);
       
        add(new JLabel("Referral Source:")); add(referralField);
        add(new JLabel("Paid Amount:")); add(paidField);
        add(new JLabel("Removal Reason:")); add(removalReasonField);
        add(new JLabel("Trainer's Name:")); add(trainerField);

        add(new JLabel("Regular Price:")); add(regularPriceField);
        add(new JLabel("Premium Price:")); add(premiumPriceField);
        add(new JLabel("Discount Amount:")); add(discountField);
        add(new JLabel("Plan (Regular Only):")); add(planBox);
    
        // Buttons
        JButton addRegularBtn = new JButton("Add Regular Member");
        JButton addPremiumBtn = new JButton("Add Premium Member");
        JButton activateBtn = new JButton("Activate Membership");
        JButton deactivateBtn = new JButton("Deactivate Membership");
        JButton markAttendanceBtn = new JButton("Mark Attendance");
        JButton upgradePlanBtn = new JButton("Upgrade Plan");
        JButton calculateDiscountBtn = new JButton("Calculate Discount");
        JButton revertRegularBtn = new JButton("Revert Regular Member");
        JButton revertPremiumBtn = new JButton("Revert Premium Member");
        JButton payDueBtn = new JButton("Pay Due Amount");
        JButton displayBtn = new JButton("Display Members");
        JButton clearBtn = new JButton("Clear Fields");
        JButton saveToFileBtn = new JButton("Save to File");
        JButton readFromFileBtn = new JButton("Read from File");

        /**
         * Adds action listeners to all buttons in the Gym Management System GUI.
         * Each listener defines the operation to be performed when the corresponding button is clicked.
         * 
         * The functionalities handled include:
         * - Adding Regular and Premium members
         * - Activating and deactivating memberships
         * - Marking attendance for members
         * - Upgrading membership plans
         * - Calculating discounts for premium members
         * - Reverting member details upon removal
         * - Paying dues for premium members
         * - Displaying all members
         * - Clearing form fields
         * - Saving and reading member data to/from a file
         * 
         * This setup ensures all user interactions via buttons are appropriately handled
         * and delegated to the corresponding methods in the application.
         */
        addRegularBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRegularMember();
            }
        });
        
        addPremiumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPremiumMember();
            }
        });

        activateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activateMembership();
            }
        });

        deactivateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deactivateMembership();
            }
        });
        
        markAttendanceBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        upgradePlanBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                upgradePlan();
            }
        });

        calculateDiscountBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                calculateDiscount();
            }
        });

        revertRegularBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                revertRegularMember();
            }
        });

        revertPremiumBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                revertPremiumMember();
            }
        });

        payDueBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                payDueAmount();
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMembers();
            }
        });


        // Add buttons to the GUI layout
        add(addRegularBtn);
        add(addPremiumBtn);
        add(activateBtn); 
        add(deactivateBtn);
        add(markAttendanceBtn); 
        add(upgradePlanBtn);
        add(calculateDiscountBtn); 
        add(revertRegularBtn);
        add(revertPremiumBtn); 
        add(payDueBtn);
        add(displayBtn); 
        add(clearBtn);
        add(saveToFileBtn); 
        add(readFromFileBtn);

        // Display the GUI
        setVisible(true);
        
    }

    /**
     * Runs the array of string representation of numbers within a given range.
     * 
     * @param int start includes starting number.
     * @param int end includes ending number.
     * @return string array including start to end numbers as string
     */
    private String[] generateNumbers(int start, int end) {
        String[] items = new String[end - start + 1];
        for (int i = 0; i < items.length; i++) items[i] = String.valueOf(start + i);
        return items;
    }

    /**
     * Adds new regular member to the gym member list.
     * This method checks all the required input fields which are filled are valid or not.
     * Adds the provided details to the member list if the ID is unique.
     * If the Id already exits or the paid amount is invalid then error message is displayed.
     */
    private void addRegularMember() {
        if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() || paidField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(idField.getText().trim()); 
        if (memberExists(id)) {
            JOptionPane.showMessageDialog(this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String referral = referralField.getText().trim();
            double paidAmount = Double.parseDouble(paidField.getText().trim());
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String dob = dobYear.getSelectedItem() + "-" + dobMonth.getSelectedItem() + "-" + dobDay.getSelectedItem();
            String msDate = msYear.getSelectedItem() + "-" + msMonth.getSelectedItem() + "-" + msDay.getSelectedItem();
            String plan = planBox.getSelectedItem().toString();

            RegularMember rm = new RegularMember(id, name, location, phone, email, gender, dob, msDate, referral, plan, paidAmount);
            members.add(rm);

            JOptionPane.showMessageDialog(this, "Regular Member is added!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid paid amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds new premium member to the gym member list.
     * This method checks all the required input fields which are filled are valid or not.
     * If the Id already exits or exception during member creation then error message is displayed.
     */
    private void addPremiumMember() {
        if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() ||
            locationField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() || trainerField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(idField.getText().trim());
        if (memberExists(id)) {
            JOptionPane.showMessageDialog(this, "Member ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String trainer = trainerField.getText().trim();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            String dob = dobYear.getSelectedItem() + "-" + dobMonth.getSelectedItem() + "-" + dobDay.getSelectedItem();
            String msDate = msYear.getSelectedItem() + "-" + msMonth.getSelectedItem() + "-" + msDay.getSelectedItem();

            PremiumMember pm = new PremiumMember(id, name, location, phone, email, gender, dob, msDate, trainer);
            members.add(pm);

            JOptionPane.showMessageDialog(this, "Premium Member is added!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding Premium Member!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Activates the membership of a gym member.
     * This method asks the user to input a valid ID number through the dialog box.
     * It checks the input, changes into integer and searches for the corresponding member.
     * Call the method to activate the membership if the member is found or error message is displayed.
     */
    private void activateMembership(){
        String input = JOptionPane.showInputDialog(this, "Enter the Member ID to Activate:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You must enter a valid Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Member ID! Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GymMember member = getMemberById(id);
        if (member == null) {
            JOptionPane.showMessageDialog(this, "Member ID is not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            member.activateMembership();
            JOptionPane.showMessageDialog(this, "Membership Activated for Member ID: " + id);
        }
    }
    
    /**
     * Deactivates the membership of gym member.
     * This method asks the user to input a valid ID number through the dialog box.
     * It checks the input, changes into integer and searches for the corresponding member.
     * Call the method to deactivate the membership if the member is found or error message is displayed.
     */
    private void deactivateMembership() {
        String input = JOptionPane.showInputDialog(this, "Enter the Member ID to Deactivate:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You must enter a valid Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id;
        try {
            id = Integer.parseInt(input.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Member ID! Please enter a valid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        GymMember member = getMemberById(id);
        if (member == null) {
            JOptionPane.showMessageDialog(this, "Member ID is not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            member.deactivateMembership();
            JOptionPane.showMessageDialog(this, "Membership Deactivated for Member ID: " + id);
        }
    }

    /**
     * Marks the attendance for a gym member.
     * This method asks the user to input a valid ID number through the dialog box.
     * It checks the input, it's not empty and changes into integer and then searches for the corresponding member.
     * Call the method to mark the attendance of the member, if the member is found else error message is displayed.
     */
    private void markAttendance() {
        String idInput = JOptionPane.showInputDialog(this, "Enter Member ID to Mark Attendance:");
        if (idInput == null || idInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You must enter a valid Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(idInput.trim());
        GymMember member = getMemberById(id);
        if (member == null) {
            JOptionPane.showMessageDialog(this, "Member ID is not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            member.markAttendance();
            JOptionPane.showMessageDialog(this, "Attendance marked for Member ID: " + id);
        }
    }

    /**
     * Manages the upgrade of a regular member's plan.
     * 
     * This method prompts the user to input a member ID, validates it, and checks if the corresponding
     * member exists, is a regular member, and has an active membership. If all checks pass, it retrieves
     * the new plan selected from the combo box and upgrades the member's plan accordingly using the
     * {@code upgradePlan} method of the {@code RegularMember} class.
     * 
     * Feedback and error messages are shown using dialog boxes for the following cases:
     * - Empty or invalid ID input
     * - Member not found
     * - Member is not a regular member
     * - Membership is not active
     * - Plan upgrade result
     */
    private void upgradePlan() {
        String idInput = JOptionPane.showInputDialog(GymGUI.this, "Enter Member ID to Upgrade Plan:");
        
        if (idInput == null || idInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(GymGUI.this, "Please enter a valid Member ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idInput.trim());
            GymMember member = getMemberById(id);
            
            if (member == null) {
                JOptionPane.showMessageDialog(GymGUI.this, "Member ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!(member instanceof RegularMember)) {
                JOptionPane.showMessageDialog(GymGUI.this, "Only regular members can upgrade plans!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!member.activeStatus) {
                JOptionPane.showMessageDialog(GymGUI.this, "Member must be active to upgrade plan!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String newPlan = planBox.getSelectedItem().toString();
            
            RegularMember regularMember = (RegularMember) member;
            String result = regularMember.upgradePlan(newPlan);
            
            JOptionPane.showMessageDialog(GymGUI.this, result);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(GymGUI.this, "Invalid Member ID format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the discount calculation for a premium member in the gym management system.
     * 
     * This method prompts the user to enter a member ID and verifies the following:
     *  - The input is not empty or invalid.
     *  - The member exists in the system.
     *  - The member is an instance of {@code PremiumMember}.
     *  - The member has completed full payment for the premium membership.
     *
     * If all conditions are satisfied, a 10% discount is applied to the premium charge,
     * and the discount amount is displayed to the user via a dialog box.
     * 
     * Validation and exception handling ensure smooth user experience and prevent misuse.
     */
    private void calculateDiscount() {
        String idInput = JOptionPane.showInputDialog(
            GymGUI.this, 
            "Enter Premium Member ID to Calculate Discount:"
        );

        if (idInput == null || idInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                GymGUI.this, 
                "Please enter a valid Member ID.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            int id = Integer.parseInt(idInput.trim());
            GymMember member = getMemberById(id);

            if (member == null) {
                JOptionPane.showMessageDialog(
                    GymGUI.this, 
                    "Member ID not found!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(
                    GymGUI.this, 
                    "Only Premium Members are eligible for discounts!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            PremiumMember premiumMember = (PremiumMember) member;

            if (!premiumMember.isPaymentComplete()) {
                JOptionPane.showMessageDialog(
                    GymGUI.this, 
                    "Discount can only be applied after full payment is made!", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            premiumMember.calculateDiscount();

            JOptionPane.showMessageDialog(
                GymGUI.this, 
                "Discount Applied: " + premiumMember.getDiscountAmount(), 
                "Discount Calculated", 
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                GymGUI.this, 
                "Invalid Member ID format! Please enter a number.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Reverts a regular member's status based on a given removal reason.
     * 
     * This method prompts the user to enter a Member ID and verifies:
     *  - The input is not empty or invalid.
     *  - The member exists in the system.
     *  - The member is an instance of {@code RegularMember}.
     *  
     * If valid, it prompts the user to enter a removal reason and calls
     * the {@code revertRegularMember(String reason)} method on the {@code RegularMember} instance.
     * A success message is shown upon successful reversion.
     * 
     * Proper validation and error dialogs are used to handle invalid inputs and user cancellations.
     */
    private void revertRegularMember() {
        // Get Member ID
        String idInput = JOptionPane.showInputDialog("Enter Regular Member ID to revert:");
        if (idInput == null || idInput.isEmpty()) return;
        
        try {
            int memberId = Integer.parseInt(idInput);
            GymMember member = getMemberById(memberId);
            
            if (member == null) {
                JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!(member instanceof RegularMember)) {
                JOptionPane.showMessageDialog(null, "Only regular members can be reverted!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
   
            String reason = JOptionPane.showInputDialog("Enter removal reason:");
            if (reason == null || reason.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Reason required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            ((RegularMember)member).revertRegularMember(reason);
            JOptionPane.showMessageDialog(null, "Member reverted successfully!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reverts a premium member's details to default values.
     * 
     * This method prompts the user to enter a Premium Member ID and verifies:
     *  - The input is not empty or invalid.
     *  - The member exists in the system.
     *  - The member is an instance of {@code PremiumMember}.
     * 
     * If valid, it calls the {@code revertPremiumMember()} method on the {@code PremiumMember} instance
     * to reset the member's details, then shows a confirmation message.
     * 
     * Appropriate error dialogs are shown for invalid input, non-existent members,
     * or if the member is not premium.
     */
    private void revertPremiumMember() {
        String idInput = JOptionPane.showInputDialog("Enter Premium Member ID to revert:");
        if (idInput == null || idInput.isEmpty()) return;
        
        try {
            int memberId = Integer.parseInt(idInput);
            GymMember member = getMemberById(memberId);
         
            if (member == null) {
                JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(null, "Only premium members can be reverted!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
   
            ((PremiumMember)member).revertPremiumMember();
            JOptionPane.showMessageDialog(null, "Premium member reverted successfully!");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid ID!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Processes a payment towards the due amount for a premium member.
     * 
     * This method prompts the user to enter:
     * - The Premium Member ID
     * - The amount to pay
     *
     * It validates the inputs, checks that the member exists and is a {@code PremiumMember},
     * and then applies the payment by calling {@code payDueAmount} on the member object.
     * The result of the payment is displayed in a message dialog.
     * 
     * If inputs are invalid, or the member does not exist or is not premium,
     * appropriate error messages are shown.
     * 
     */
    private void payDueAmount() {
        String idInput = JOptionPane.showInputDialog("Enter Premium Member ID:");
        if (idInput == null || idInput.isEmpty()) return;
        
        // Get Payment Amount
        String amountInput = JOptionPane.showInputDialog("Enter Amount to Pay:");
        if (amountInput == null || amountInput.isEmpty()) return;
        
        try {
            int memberId = Integer.parseInt(idInput);
            double amount = Double.parseDouble(amountInput);
            GymMember member = getMemberById(memberId);
            
            // Validate member
            if (member == null) {
                JOptionPane.showMessageDialog(null, "Member not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!(member instanceof PremiumMember)) {
                JOptionPane.showMessageDialog(null, "Only premium members can make payments!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Process payment
            String result = ((PremiumMember)member).payDueAmount(amount);
            JOptionPane.showMessageDialog(null, result);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays the details of all the gym members in a new frame.
     * This method creates new JFrame including text area for details of each gym member.
     * It display the information of member by using getDetails() method.
     * Display Frame is fixed size and non-editable.
     */
    private void displayMembers() {
        JFrame displayFrame = new JFrame("Member List");
        displayFrame.setLayout(new BorderLayout());
        JTextArea displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false); 
                for (GymMember member : members) {
            displayArea.append(member.getDetails() + "\n\n");
        }        

        JScrollPane scrollPane = new JScrollPane(displayArea);
        displayFrame.add(scrollPane, BorderLayout.CENTER);
        displayFrame.setSize(600, 400);
        displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        displayFrame.setVisible(true);
    }

    /**
     * It searches for a gym member by thier ID.
     * This method iterates by the list of gym members and returns member whose ID matches the given parameter.
     * @param id includes the id of a gym member to check.
     * @return m returns object with matching id or null returns if not found.
     */
    private GymMember getMemberById(int id) {
        for (GymMember m : members) {
            if (m.getId()==(id)) {
                return m;
            }
        }
        return null; 
    }

    /**
     * It checks a gym member with the given ID already exists in the gym member list.
     * This method iterates by the list of gym members.
     * @param id includes the id of gym member to check.
     * @return true if a ID matches,otherwise return false. 
     */
    private boolean memberExists(int id) {
        for (GymMember m : members) {
            if (m.getId()==(id)) return true;
        }
        return false;
    }

}