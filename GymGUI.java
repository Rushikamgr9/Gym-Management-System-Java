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
}