package com.xsdvalidator.ui;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class DashBoard extends JFrame {

	private JTextField xmlPath;
	private JTextField xsdPath;
	private JButton bouton = new JButton("Validation");
	private JButton xmlBt = new JButton("Xml choice");
	private JButton xsdBt = new JButton("Xsd choice");
	private JTextArea result;
	private String currentDir;

	public DashBoard() {
		this.setSize(450, 200);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setIconImage(new
		// ImageIcon(getClass().getResource("/images/wadoJPG.JPG"))
		// .getImage());
		this.setTitle("Validatorus xmlus with xsdus");

		bouton.addActionListener(new ValidateProcess(this));
		xmlBt.addActionListener(new PathProcess(this,"xml"));
		xsdBt.addActionListener(new PathProcess(this,"xsd"));

		this.xmlPath = new JTextField(20);
		this.xsdPath = new JTextField(20);
		this.result = new JTextArea(250, 250);

		this.result.setEditable(false);

		this.setVisible(true);
		this.setLocationRelativeTo(null);

		JLabel labelXml = new JLabel("Xml path: ");
		JLabel labelXsd = new JLabel("Xsd path: ");

		// On crée un conteneur avec gestion horizontale
		Box b1 = Box.createHorizontalBox();
		b1.add(labelXml);
		b1.add(this.xmlPath);
		b1.add(this.xmlBt);

		// Idem
		Box b2 = Box.createHorizontalBox();
		b2.add(labelXsd);
		b2.add(this.xsdPath);
		b2.add(this.xsdBt);
		// Idem
		Box b3 = Box.createHorizontalBox();
		b3.add(this.bouton);

		Box b4 = Box.createHorizontalBox();
		// b4.add(this.result);

		JScrollPane scrollRes = new JScrollPane(this.result);
		scrollRes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		b4.add(scrollRes);

		// On crée un conteneur avec gestion verticale
		Box b6 = Box.createVerticalBox();
		b6.add(b1);
		b6.add(b2);
		b6.add(b3);
		b6.add(b4);

		this.getContentPane().add(b6);

		// this.pack();
		this.setVisible(true);
	}

	public String loginText() {
		return this.xmlPath.getText();
	}

	public String passText() {
		return this.xsdPath.getText();
	}

	public void afficher(String error) {
		if (error.isEmpty()) {
			this.result.setText("Validation OK");
		} else {
			this.result.setText(error);
		}
	}

	public void displayPath(String path, String type) {
		if (type.equals("xml")) {
			this.xmlPath.setText(path);
		} else {
			this.xsdPath.setText(path);
		}
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir;
	}

}
