
package cz.muni.fi.classes;

import java.io.File;
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
 * Java class for validating any xml document.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class CVSchemaValidator {
    
    private Schema schema = null;
    
    /**
     * Constructor of this class in which schema, according to which a xml
     * document will be validated, is set.
     * 
     * @param schemaName    name of the .xsd file, i.e. XML schema which will be used
     *                      for validation
     */
    public CVSchemaValidator(String schemaName){
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
            return false;
        } catch (IOException ex) {
            System.err.println("DOMSource error: "+ex.getMessage());
            return false;
        }
        return true;
    }
}
