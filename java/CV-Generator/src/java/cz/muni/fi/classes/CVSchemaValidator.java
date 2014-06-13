
package cz.muni.fi.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Java class for an xml document validation.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class CVSchemaValidator {
    
    private Schema schema = null;
    private String contextPath = null;
    
    /**
     * Constructor of this class in which schema, according to which a xml
     * document will be validated, is set.
     * 
     * @param schemaName    name of the .xsd file, i.e. XML schema which will be used
     *                      for validation
     * @param contextPath   determine the directory destination of work files
     */
    public CVSchemaValidator(String schemaName, String contextPath){
        this.contextPath = contextPath;
        try{
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            this.schema = factory.newSchema(new File(schemaName));
        }catch(SAXException ex){
            System.err.println("Error at setting schema for validation: "+ex.getMessage());
        }
    }
    /**
     * This method just extends method validate(Document doc), but as input
     * paramater takes a xml file  from which creates xml document in memory (type Document)
     * and that document pass in validate(Document doc).
     * 
     * @param xmlFile   input xml file which will be validated
     * @return          true - xml file exists and is valid
     *                  false - otherwise
     */
    public boolean validate(File xmlFile){
        Document doc;
        if (xmlFile.exists()) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(xmlFile);
            } catch (ParserConfigurationException ex) {
                System.err.println(ex.getMessage());
                return false;
            } catch (SAXException ex) {
                System.err.println(ex.getMessage());
                return false;
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
            return (this.validate(doc));
        }
        return false;
    }
    
    /**
     * This class determine whether a xml document is valid or not.
     * 
     * @param doc   xml document which is validated according to xml schema
     * @return      true - xml document is valid
     *              false - xml document is not valid
     */
    public boolean validate(Document doc){
        //validator creation from schema and setting of errorhandler for collecting of validation errors
        Validator validator = this.schema.newValidator();
        validator.setErrorHandler(new ValidationErrorsHandler());
        
        //validation itself, if any exception appears, xml document is not valid
        try {
            validator.validate(new DOMSource(doc));
        } catch (SAXException ex) {
            System.err.println("XML document is not valid: "+ex.getMessage());
            saveValidationError(ex.getMessage());
            return false;
        } catch (IOException ex) {
            System.err.println("DOMSource error: "+ex.getMessage());
            return false;
        }
        return true;
    }
    /*
     * This method finds why an xml document is not valid and create an overview,
     * which will be used as an indicator for user for better, quicker and easier
     * orientation during the correction phase. 
     *
     * @param msg   message about the validation error
     */
    private void saveValidationError(String msg){
        if(this.contextPath != null && !this.contextPath.trim().equals("")){
            File fout = new File(this.contextPath,"validation_error.txt");
            String feName = null;
            try {                
                BufferedWriter bw = new BufferedWriter(new FileWriter(fout));
                bw.write("Check you correctly filled the following boxes:");
                bw.newLine();
                if(msg.contains("'typePostal'")){
                    feName = "typePostal.txt";
                }
                if(msg.contains("'typeNonEmptyString'")){
                    feName = "typeNonEmptyString.txt";
                }
                if(msg.contains("'typeEmail'")){
                    feName = "typeEmail.txt";
                }
                if(msg.contains("'typeMyDate'")){
                    feName = "typeMyDate.txt";
                }
                if(msg.contains("'typePhone'")){
                    feName = "typePhone.txt";
                }
                if(msg.contains("'typeTitle'")){
                    feName = "typeTitle.txt";
                }
                if(msg.contains("Attribute 'error' is not allowed to appear in element 'emp'.")){
                    feName = "emplDateError.txt";
                }
                if(msg.contains("Attribute 'error' is not allowed to appear in element 'edu'.")){
                    feName = "eduDateError.txt";
                }
                if(feName != null){
                    File fin = new File(this.contextPath+"/error_messages",feName);
                    BufferedReader br = new BufferedReader(new FileReader(fin));
                    String line;
                    while((line = br.readLine()) != null){
                        bw.write(line);
                        bw.newLine();
                    }
                    br.close();
                }
                bw.close();                
            } catch (FileNotFoundException ex) {
                System.err.println("File does not exist: "+ex.getMessage());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
