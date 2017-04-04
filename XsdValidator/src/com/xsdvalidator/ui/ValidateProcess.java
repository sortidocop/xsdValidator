package com.xsdvalidator.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidateProcess implements ActionListener {

	private DashBoard dash;

	public ValidateProcess(DashBoard dash) {
		this.dash = dash;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		final String pathXml = dash.loginText();
		final String pathXsd = dash.passText();

		String error = "";

		InputStream xml = null;
		InputStream xsd = null;
		try {
			xml = new FileInputStream(pathXml);
			xsd = new FileInputStream(pathXsd);

			try {
				final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
				final Schema schema = factory.newSchema(new StreamSource(xsd));
				final Validator validator = schema.newValidator();
				validator.validate(new StreamSource(xml));

			} catch (Exception ex) {
				System.err.println(ex);
				error += ex.toString();
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			error += e.getMessage();
		} finally {
			if (xml != null)
				try {
					xml.close();
				} catch (IOException e) {
					System.err.println(e);
					error += e.getMessage();
				}
			if (xsd != null)
				try {
					xsd.close();
				} catch (IOException e) {
					System.err.println(e);
					error += e.getMessage();
				}
		}

		dash.afficher(error);

	}
}
