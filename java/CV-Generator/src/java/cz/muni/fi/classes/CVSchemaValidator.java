
package cz.muni.fi.classes;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Java class for validating any xml document.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class CVSchemaValidator {
    
    private DocumentBuilder docBuilder = null;
    
    
    public CVSchemaValidator(String schemaName){
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(schemaName));
            
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            
            dbf.setSchema(schema);
            docBuilder = dbf.newDocumentBuilder();
        } catch (SAXException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        } catch (ParserConfigurationException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * This method determines whether a xml document is valid or not.
     * 
     * @param xmlFilename   input xml file (document) which will be validated
     * @return  null if xml file is valid,
     *          error message otherwise
     * @throws IOException if there does not manage to open the xml file
     */
    public String validate(String xmlFilename) throws IOException{
        try {
            Document doc = docBuilder.parse(new File(xmlFilename));
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
            return (ex.getMessage());
        } 
        return null;
    }
}
