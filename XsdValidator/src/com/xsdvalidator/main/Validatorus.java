/**
 * 
 */
package com.xsdvalidator.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 *
 */
public class Validatorus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String path = args[0];
		final String xmlFile = args[1];
		final String xsdFile = args[2];
		
		final Validatorus valid = new Validatorus();
		
		InputStream xml = null;
		InputStream xsd = null;
		try {
			xml = new FileInputStream(path+xmlFile);
			xsd = new FileInputStream(path+xsdFile);
			
			valid.valideMoiLa(xml, xsd);
		} catch (FileNotFoundException e) {
			 System.err.println(e);
		} catch (IOException e) {
			 System.err.println(e);
		}finally{
	    	if(xml != null)
				try {
					xml.close();
				} catch (IOException e) {
					 System.err.println(e);
				}
	    	if(xsd != null)
				try {
					xsd.close();
				} catch (IOException e) {
					 System.err.println(e);
				}
	    }
		

	}
	
	
	private void valideMoiLa(InputStream xml, InputStream xsd) throws IOException{
		    try {
		        final SchemaFactory factory = 
		            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		        final Schema schema = factory.newSchema(new StreamSource(xsd));
		        Validator validator = schema.newValidator();
		        validator.validate(new StreamSource(xml));
		        
		    }
		    catch(Exception ex) {
		        System.err.println(ex);
		    }
	} 
}
