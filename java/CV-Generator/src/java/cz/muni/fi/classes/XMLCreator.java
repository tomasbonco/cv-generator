/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Java class for creating a new or modifying an existing xml document. 
 * 
 * @author Tomáš Šmíd <smid.thomas@gmail.com>
 */
public class XMLCreator {
    
    private Document document = null;
    private DocumentBuilder docBuilder = null;
    
    public XMLCreator(File xmlFilename) throws SAXException{
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);        
        try {        
            this.docBuilder = docBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            this.document = this.docBuilder.parse(xmlFilename);
        } catch (IOException ex) {
            this.document = null;
            System.out.println(ex.getMessage());
        }
        }
    }
    /**
     * This method saves an xml document to the specified destination.
     * 
     * @param doc   an xml document which will be saved
     * @param file  destination where xml document will be saved
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    private void saveToFile(Document doc, File file) throws TransformerConfigurationException, TransformerException{
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes"); 
        DOMSource domSource = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(domSource, result); 
    }
    
    /**
     * This method generates new or modifies existing xml document by adding value
     * "firstname" to it.
     * 
     * @param firstName name captured from html page which is planned to add to the xml document
     * @throws ParserConfigurationException
     * @throws XPathExpressionException 
     */
    public void generateXML(String firstName) throws ParserConfigurationException, XPathExpressionException {        
        Document newDoc;
        Element rootElement;
        String id;
        
        if(this.document != null){
            newDoc = this.document;
            
            rootElement = newDoc.getDocumentElement();
             id = setCVElementId(newDoc);
        }else{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            newDoc = db.newDocument();
            
            rootElement = newDoc.createElement("data");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "simple_database.xsd");
            
            id = "0";
        }
            
            Element cvElement = newDoc.createElement("cv");
            Element firstNameElement = newDoc.createElement("firstname");
            
            rootElement.appendChild(cvElement);            
            cvElement.setAttribute("id", id);
            cvElement.appendChild(firstNameElement);
            firstNameElement.setTextContent(firstName);
            if(this.document == null)
                newDoc.appendChild(rootElement);
            try {
                saveToFile(newDoc, new File("C:\\Users\\Tom\\Documents\\NetBeansProjects\\CV-Generator\\data.xml"));
            } catch (TransformerException ex) {
                ex.getMessageAndLocation();
            }
    }
    
    /**
     * This method determine next value of unique atribute id in the raw for new cv.
     * 
     * @param newDoc    document from which is determined needed id
     * @return needed id value
     */
    private String setCVElementId(Document newDoc) {
        
        XPathFactory xpFactory = XPathFactory.newInstance();
        XPath xPath = xpFactory.newXPath();
        XPathExpression xpExpression;
        String lastCVElement = null;
        try {
            xpExpression = xPath.compile("data/cv[last()]/@id");
            lastCVElement = (String)xpExpression.evaluate(newDoc, XPathConstants.STRING);
        } catch (XPathExpressionException ex) {
            System.out.println(ex.getMessage());
        }
        if(lastCVElement == null || "".equals(lastCVElement.trim())){
            return ("0");
        }
        Integer id = Integer.parseInt(lastCVElement)+1;        
        return (id.toString());        
    }
    
}
