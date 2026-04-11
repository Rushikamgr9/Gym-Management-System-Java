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

        // Add buttons to the GUI layout
        add(addRegularBtn);
        add(addPremiumBtn);


        
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