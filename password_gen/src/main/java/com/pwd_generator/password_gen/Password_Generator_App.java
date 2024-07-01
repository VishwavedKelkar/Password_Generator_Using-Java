package com.pwd_generator.password_gen;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Password_Generator_App extends JFrame{

	private JCheckBox lowerCaseCheckBox;
	private JCheckBox upperCaseCheckBox;
	private JCheckBox numbersCheckBox;
	private JCheckBox specialCharsCheckBox;
	private JSpinner lengthsSpinner;
	private JTextField passwordTextField;
	private JButton generateButton;

	public Password_Generator_App(){

		setTitle("Password Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setLocationRelativeTo(null);
		initialize(); //initialize the user interface
	}

	private void initialize(){

		lowerCaseCheckBox = new JCheckBox("Include LowerCase");
		upperCaseCheckBox = new JCheckBox("Include UpperCase");
		numbersCheckBox = new JCheckBox("Include Numbers");
		specialCharsCheckBox = new JCheckBox("Include Special Characters");

		lowerCaseCheckBox.setFocusPainted(false);
		lowerCaseCheckBox.setBorderPainted(false);
		lowerCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		upperCaseCheckBox.setFocusPainted(false);
		upperCaseCheckBox.setBorderPainted(false);
		upperCaseCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		numbersCheckBox.setFocusPainted(false);
		numbersCheckBox.setBorderPainted(false);
		numbersCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		specialCharsCheckBox.setFocusPainted(false);
		specialCharsCheckBox.setBorderPainted(false);
		specialCharsCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));

		//Create a spinner for passsword length selection
		lengthsSpinner = new JSpinner(new SpinnerNumberModel(8,4,20,1));

		//create a text field to dsisplay the generated password
		passwordTextField = new JTextField(20);
		passwordTextField.setFont(new Font("Arial",Font.PLAIN,16));
		passwordTextField.setEditable(false);

		//Create a button to generate a password
		generateButton = new JButton("GENERATE PASSWORD ");
		generateButton.setBackground(new Color(63,81,181));
		generateButton.setForeground(Color.white);
		generateButton.setFocusPainted(false);
		generateButton.setBorderPainted(false);
		generateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		generateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generatePassword();
			}
		});

		//panels to hold the UI components
		JPanel mainPanel = new JPanel(new GridLayout(8,1,10,10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		mainPanel.setBackground(Color.white);

		mainPanel.add(lowerCaseCheckBox);
		mainPanel.add(upperCaseCheckBox);
		mainPanel.add(numbersCheckBox);
		mainPanel.add(specialCharsCheckBox);

		JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lengthPanel.setBackground(Color.white);
		lengthPanel.add(new JLabel("Password Length"));
		lengthPanel.add(lengthsSpinner);
		mainPanel.add(lengthPanel);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(generateButton);
		mainPanel.add(buttonPanel);
		mainPanel.add(passwordTextField);

		getContentPane().setBackground(Color.white);
		add(mainPanel);
	}

	private String generatePassword(){

		//get the desired password length from the spinner
		int passwordLength = (int) lengthsSpinner.getValue();

		//Character sets for password generation
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "0123456789";
		String specailChars = "!@#$%^&*?";

		
		//Initialize the characters based on the user selection
		String characters = "";

		if(lowerCaseCheckBox.isSelected()) {
			characters += lowerCase;
		}

		if(upperCaseCheckBox.isSelected()) {
			characters += upperCase;
		}

		if(numbersCheckBox.isSelected()) {
			characters += numbers;
		}

		if(specialCharsCheckBox.isSelected()) {
			characters += specailChars;
		}

		//if no character is selected , show this error message
		if(characters.isEmpty()){
			JOptionPane.showMessageDialog(this, "Please select at least one option");
			return "";
		}

		//Generate the password by selecting random characters from the characters string
		Random random = new Random();
		StringBuilder password = new StringBuilder();

		for(int i = 0; i < passwordLength;i++){
			int randomIndex = random.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			password.append(randomChar);		
		}
	
		//Display the generated password on the textField
		passwordTextField.setText(password.toString());
		return password.toString();
	}

	public static void main(String[] args) {

		//SpringApplication.run(Password_Generator_App.class, args);

		SwingUtilities.invokeLater( () ->{
			try
			{
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			Password_Generator_App app = new Password_Generator_App();
			app.setVisible(true);
		});
	}

}
