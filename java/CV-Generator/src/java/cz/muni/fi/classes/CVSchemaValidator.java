
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
 *
 * @author Tomáš Šmíd
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
            docBuilder.setErrorHandler(new ValidationErrorsHandler());
        } catch (SAXException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        } catch (ParserConfigurationException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
