/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
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
     */
    public void generateXML(PersonalInfo person){
        Document doc = this.docBuilder.newDocument();
        
        Element rootElement = doc.createElement("cv");
        /*rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xsi:noNamespaceSchemaLocation", "database.xsd");*/
        createSimpleTag(doc,rootElement,person.getPretitle(),"pretitle");
        createSimpleTag(doc,rootElement,person.getFirstname(),"firstname");
        createSimpleTag(doc,rootElement,person.getLastname(),"lastname");
        createSimpleTag(doc,rootElement,person.getPosttitle(),"posttitle");
        createAddressTag(doc,rootElement,person);
        createPhonesOrEmailsTag(doc,rootElement,person,"phone");
        createPhonesOrEmailsTag(doc,rootElement,person,"email");
        createEmploymentTag(doc,rootElement,person);
        createEducationTag(doc,rootElement,person);
        createLangOrSkillTag(doc,rootElement,person,"languages");
        createCerOrHobTag(doc,rootElement,person,"certificates");
        createLangOrSkillTag(doc,rootElement,person,"skills");
        createCerOrHobTag(doc,rootElement,person,"hobbies");
        doc.appendChild(rootElement);
        save(doc,new File(person.getFirstname()+"_"+person.getLastname()+".xml"));
    }
    
    private void createSimpleTag(Document doc, Element rootElement,
                                 String content,String elemName){
        
        Element element = doc.createElement(elemName);
        rootElement.appendChild(element);
        element.setTextContent(content);
    }
    
    private void createAddressTag(Document doc, Element rootElement, PersonalInfo person){
        
        Element addressElem = doc.createElement("address");
        rootElement.appendChild(addressElem);
        
        Element streetElem = doc.createElement("street");
        addressElem.appendChild(streetElem);
        streetElem.setTextContent(person.getStreet());
        
        Element cityElem = doc.createElement("city");
        addressElem.appendChild(cityElem);
        cityElem.setTextContent(person.getCity());
        
        Element postalElem = doc.createElement("postal");
        addressElem.appendChild(postalElem);        
        postalElem.setTextContent(person.getPostal());
    }
    
    private void createPhonesOrEmailsTag(Document doc, Element rootElement, 
                                         PersonalInfo person, String name){
        
        Element element = doc.createElement(name+"s");
        rootElement.appendChild(element);
        
        if("phone".equals(name)){
            for(int i = 0; i < person.getPhones().size(); i++){
                Element elem = doc.createElement(name);
                element.appendChild(elem);
                elem.setTextContent(person.getPhones().get(i));
            }
        }else{
            for(int i = 0; i < person.getEmails().size(); i++){
                Element elem = doc.createElement(name);
                element.appendChild(elem);
                elem.setTextContent(person.getEmails().get(i));
            }
        }        
    }
    
    private void createCerOrHobTag(Document doc, Element rootElement, 
                                   PersonalInfo person,String name){
        
        Element element = doc.createElement(name);
        rootElement.appendChild(element);        
        
        if("certificates".equals(name)){
            if(person.getCertificates() != null){
                for(int i = 0; i < person.getCertificates().size(); i++){
                    if(!person.getCertificates().get(i).equals("")){
                        Element elem = doc.createElement("cer");
                        element.appendChild(elem);
                        elem.setTextContent(person.getCertificates().get(i));
                    }
                }
            }
        }else{
            if(person.getHobbies() != null){
                for(int i = 0; i < person.getHobbies().size(); i++){
                    if(!person.getHobbies().get(i).equals("")){
                        Element elem = doc.createElement("hob");
                        element.appendChild(elem);
                        elem.setTextContent(person.getHobbies().get(i));
                    }
                }
            }
        }
    }
    
    private void createEmploymentTag(Document doc, Element rootElement,
                                     PersonalInfo person){
        int k;
        Element emplElement = doc.createElement("employment");
        rootElement.appendChild(emplElement);
        
        for(Integer i: person.getEmployments().keySet()){
            k = 0;
            for(int j = 0; j < 4; j++){
                if(person.getEmployments().get(i).get(j) == null && 
                        person.getEmployments().get(i).get(j).equals("")){
                    k++;
                }
            }
            if(k < 4){
                Element elem = doc.createElement("emp");
                emplElement.appendChild(elem);
                elem.setAttribute("name", person.getEmployments().get(i).get(0));
                elem.setAttribute("position", person.getEmployments().get(i).get(1));
                elem.setAttribute("from", person.getEmployments().get(i).get(2));
                elem.setAttribute("to", person.getEmployments().get(i).get(3));
                if(person.getEmployments().get(i).get(3).equals("")){
                    elem.setAttribute("current", "1");
                }else{
                    elem.setAttribute("current", "0");
                }
            }
        }
        
    }
    
    private void createEducationTag(Document doc, Element rootElement,
                                     PersonalInfo person){
        
        int k;
        Element eduElement = doc.createElement("education");
        rootElement.appendChild(eduElement);
        
        
        for(Integer i: person.getEducation().keySet()){
            k = 0;
            for(int j = 0; j < 4; j++){
                if(person.getEducation().get(i).get(j) == null && 
                        person.getEducation().get(i).get(j).equals("")){
                    k++;
                }
            }
            if(k < 4){
                Element elem = doc.createElement("edu");
                eduElement.appendChild(elem);
                elem.setAttribute("name", person.getEducation().get(i).get(0));
                elem.setAttribute("field-of-study", person.getEducation().get(i).get(1));
                elem.setAttribute("from", person.getEducation().get(i).get(2));
                elem.setAttribute("to", person.getEducation().get(i).get(3));
                if(person.getEducation().get(i).get(3).equals("")){
                    elem.setAttribute("current", "1");
                }else{
                    elem.setAttribute("current", "0");
                }
            }
        }
    }
    
    private void createLangOrSkillTag(Document doc, Element rootElement, 
                                      PersonalInfo person, String name){
        
        Element element = doc.createElement(name);
        rootElement.appendChild(element);
        
        if("languages".equals(name)){
            for(String s: person.getLanguages().keySet()){
                if(s != null && !s.equals("")){
                    Element elem = doc.createElement("lang");
                    element.appendChild(elem);
                    elem.setAttribute("name", s);
                    elem.setAttribute("level", person.getLanguages().get(s));
                }
            }
        }else{
            for(String s: person.getSkills().keySet()){
                if(s != null && !s.equals("")){
                    Element elem = doc.createElement("sk");
                    element.appendChild(elem);
                    elem.setAttribute("name", s);
                    elem.setAttribute("level", person.getSkills().get(s));
                }
            }
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