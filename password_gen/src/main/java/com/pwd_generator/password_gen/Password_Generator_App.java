package com.pwd_generator.password_gen;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.springframework.boot.SpringApplication;
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
		//initialize(); //initialize the user interface
	}

	private void initialize(){
		lowerCaseCheckBox = new JCheckBox("Include LowerCase");
		upperCaseCheckBox = new JCheckBox("Include UpperCase");
		numbersCheckBox = new JCheckBox("Include Numbers");
		specialCharsCheckBox = new JCheckBox("Include Special Characters");

		lengthsSpinner = new JSpinner(new SpinnerNumberModel(8,1,20,1));

		passwordTextField = new JTextField(20);
		passwordTextField.setFont(new Font("Arial",Font.PLAIN,16));
		passwordTextField.setEditable(false);

		generateButton = new JButton("-Generate Password ");
		generateButton.setBackground(new Color(63,81,181));
		generateButton.setForeground(Color.white);

		
	}

	public static void main(String[] args) {
		SpringApplication.run(Password_Generator_App.class, args);
	}

}
