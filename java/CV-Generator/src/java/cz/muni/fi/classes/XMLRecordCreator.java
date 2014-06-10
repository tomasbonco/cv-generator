/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Java class for creating a new xml document with personal information
 * which is captured from html page (form) and which is kept in variable 
 * person. 
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class XMLRecordCreator {
    
    private DocumentBuilder docBuilder = null;
    
    public XMLRecordCreator(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            this.docBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
    }    
    
    /**
     * This method is intended for generating a new xml document with personal
     * information of person who filled in the html form and wants to create
     * a new curriculum vitae.
     * 
     * @param person    variable in which is kept all neccessary information about
     *                  person who wants to create a new cv
     * @param contextPath
     * @return 
     */
    public boolean generateXML(PersonalInfo person, String contextPath){
        Document doc = this.docBuilder.newDocument();
        CVSchemaValidator sv = new CVSchemaValidator(contextPath+"/database.xsd", contextPath);
        File dir = new File(contextPath,"database");
        int i = 0;
        
        //proceed creation of xml document, for this moment just in main memory
        Element rootElement = doc.createElement("cv");
        createPasswordTag(doc,rootElement,person);
        createSimpleTag(doc,rootElement,person.getPretitle(),"pretitle");
        createSimpleTag(doc,rootElement,person.getFirstname(),"firstname");
        createSimpleTag(doc,rootElement,person.getLastname(),"lastname");
        createSimpleTag(doc,rootElement,person.getPosttitle(),"posttitle");
        createAddressTag(doc,rootElement,person);
        createPhoneTag(doc,rootElement,person);
        createEmailTag(doc,rootElement,person);
        createEmploymentTag(doc,rootElement,person);
        createEducationTag(doc,rootElement,person);
        createLanguageTag(doc,rootElement,person);
        createCertificateTag(doc,rootElement,person);
        createSkillTag(doc,rootElement,person);
        createHobbyTag(doc,rootElement,person);        
        doc.appendChild(rootElement);        
        
        /*create new directory database (destination for all xml and pdf files) 
        if it does not exist*/
        if(!(dir.exists() && dir.isDirectory())){
            dir.mkdir();
        }
        //creating physical xml file if valid
        if(sv.validate(doc) == true){
            File xmlFile = new File(dir,person.getDateHash()+".xml");            
            //check if such a file already exists
            if(!xmlFile.exists()){
                //simply saved with original name if it is the first appearance
                save(doc,xmlFile);
            }else{
                //must change the name of file because such a file already exists
                do{
                    person.setDateHash(new Date().toString());                    
                    xmlFile = new File(dir,person.getDateHash()+".xml");
                    if(!xmlFile.exists()){
                        save(doc,xmlFile);
                        i = 1;
                    }
                }while(i != 1);
            }
            return true;
        }else{
            //xml file is created only because of further filling info to the form
            //(new attempt after unsuccessful filling), will be removed            
            save(doc,new File(contextPath,"invalid.xml"));
            return false;
        }
    }
    
    private void createSimpleTag(Document doc,Element rootElement,String content,String elemName){        
        Element element = doc.createElement(elemName);
        rootElement.appendChild(element);
        element.setTextContent(content);
    }
    
    private void createAddressTag(Document doc,Element rootElement,PersonalInfo person){
        
        Element addressElem = doc.createElement("address");
        rootElement.appendChild(addressElem);
        
        if(!person.getStreet().trim().equals("") && person.getStreet() != null){
            Element streetElem = doc.createElement("street");
            addressElem.appendChild(streetElem);
            streetElem.setTextContent(person.getStreet());
        }
        
        if(!person.getCity().trim().equals("") && person.getCity() != null){
            Element cityElem = doc.createElement("city");
            addressElem.appendChild(cityElem);
            cityElem.setTextContent(person.getCity());
        }
        
        if(!person.getPostal().trim().equals("") && person.getPostal() != null){
            Element postalElem = doc.createElement("postal");
            addressElem.appendChild(postalElem);        
            postalElem.setTextContent(person.getPostal());
        }
    }
    
    private void createPhoneTag(Document doc,Element rootElement,PersonalInfo person){
        
        Element element = doc.createElement("phones");
        rootElement.appendChild(element);        
        
        for(int i = 0; i < person.getPhones().size(); i++){
            if(!person.getPhones().get(i).trim().equals("") && person.getPhones().get(i) != null){
                Element elem = doc.createElement("phone");
                element.appendChild(elem);
                elem.setTextContent(person.getPhones().get(i));
            }
        }
    }
    
    private void createEmailTag(Document doc,Element rootElement,PersonalInfo person){
        
        Element element = doc.createElement("emails");
        rootElement.appendChild(element);
        
        for(int i = 0; i < person.getEmails().size(); i++){
            if(!person.getEmails().get(i).trim().equals("") && person.getEmails().get(i) != null){
                Element elem = doc.createElement("email");
                element.appendChild(elem);
                elem.setTextContent(person.getEmails().get(i));
            }
        }
    }
    
    private void createCertificateTag(Document doc,Element rootElement,PersonalInfo person){
        Element element = doc.createElement("certificates");
        rootElement.appendChild(element);
        if (person.getCertificates() != null) {
            for (int i = 0; i < person.getCertificates().size(); i++) {
                if (!person.getCertificates().get(i).trim().equals("") && person.getCertificates().get(i) != null) {
                    Element elem = doc.createElement("cer");
                    element.appendChild(elem);
                    elem.setTextContent(person.getCertificates().get(i));
                }
            }
        }
    }
    
    private void createHobbyTag(Document doc,Element rootElement,PersonalInfo person){
        Element element = doc.createElement("hobbies");
        rootElement.appendChild(element);
        if (person.getHobbies() != null) {
            for (int i = 0; i < person.getHobbies().size(); i++) {
                if (!person.getHobbies().get(i).trim().equals("") && person.getHobbies().get(i) != null) {
                    Element elem = doc.createElement("hob");
                    element.appendChild(elem);
                    elem.setTextContent(person.getHobbies().get(i));
                }
            }
        }
    }
    
    private void createEmploymentTag(Document doc,Element rootElement,PersonalInfo person){
        int k;
        
        Element emplElement = doc.createElement("employment");
        rootElement.appendChild(emplElement);        
        for(Integer i: person.getEmployments().keySet()){
            k = 0;
            for(int j = 0; j < 2; j++){
                if(person.getEmployments().get(i).get(j) != null && 
                        !person.getEmployments().get(i).get(j).trim().equals("")){
                    k++;
                }
            }
            if(k == 2){
                Element elem = doc.createElement("emp");
                emplElement.appendChild(elem);
                elem.setAttribute("name", person.getEmployments().get(i).get(0));
                elem.setAttribute("position", person.getEmployments().get(i).get(1));
                elem.setAttribute("from", person.getEmployments().get(i).get(2));
                elem.setAttribute("to", person.getEmployments().get(i).get(3));
            }
        }        
    }
    
    private void createEducationTag(Document doc,Element rootElement,PersonalInfo person){        
        int k;
        
        Element eduElement = doc.createElement("education");
        rootElement.appendChild(eduElement);
        for(Integer i: person.getEducation().keySet()){
            k = 0;
            for(int j = 0; j < 4; j++){
                if(person.getEducation().get(i).get(j) == null || 
                        person.getEducation().get(i).get(j).trim().equals("")){
                    k++;
                }
            }
            if(k < 4){
                if(!person.getEducation().get(i).get(0).trim().equals("")){
                    Element elem = doc.createElement("edu");
                    eduElement.appendChild(elem);
                    elem.setAttribute("name", person.getEducation().get(i).get(0));
                    elem.setAttribute("field-of-study", person.getEducation().get(i).get(1));
                    elem.setAttribute("from", person.getEducation().get(i).get(2));
                    elem.setAttribute("to", person.getEducation().get(i).get(3));
                }
            }
        }
    }
    
    private void createLanguageTag(Document doc,Element rootElement,PersonalInfo person){        
        Element element = doc.createElement("languages");
        rootElement.appendChild(element);        
        for (String s : person.getLanguages().keySet()) {
            if (s != null && !s.trim().equals("")) {
                Element elem = doc.createElement("lang");
                element.appendChild(elem);
                elem.setAttribute("name", s);
                elem.setAttribute("level", person.getLanguages().get(s));
            }
        }
    }
    
    private void createSkillTag(Document doc,Element rootElement,PersonalInfo person){        
        Element element = doc.createElement("skills");
        rootElement.appendChild(element);        
        for (String s : person.getSkills().keySet()) {
            if (s != null && !s.trim().equals("")) {
                Element elem = doc.createElement("sk");
                element.appendChild(elem);
                elem.setAttribute("name", s);
                elem.setAttribute("level", person.getSkills().get(s));
            }
        }
    }
    
    private void createPasswordTag(Document doc,Element rootElement,PersonalInfo person){        
        if(!person.getPasswordHash().trim().equals("") && person.getPasswordHash() != null){
            Element pswElement = doc.createElement("passwd");
            rootElement.appendChild(pswElement);
            pswElement.setTextContent(person.getPasswordHash());
        }
    }
    
    private void save(Document doc, File file){
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(domSource, result);
        } catch (TransformerConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (TransformerException ex) {
            System.out.println(ex.getMessage());
        }         
    }
}
