/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Jan Polišenský <polisensky88 at gmail.com>
 */
public class PersonalInfoBuilder {
    public PersonalInfo newPersonalInfo(File xmlFile){
        PersonalInfo result = new PersonalInfo();
        
        if (xmlFile.exists()) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(xmlFile);
                
                result = newPersonalInfo(doc);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(PersonalInfoBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(PersonalInfoBuilder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PersonalInfoBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        
        return result;
    }
    
    public PersonalInfo newPersonalInfo(Document doc){
        PersonalInfo result = new PersonalInfo();
        Element rootElem = doc.getDocumentElement();
        result.setPretitle(getUniqueElementText(rootElem, "pretitle"));
        result.setFirstname(getUniqueElementText(rootElem, "firstname"));
        result.setLastname(getUniqueElementText(rootElem, "lastname"));
        result.setPosttitle(getUniqueElementText(rootElem, "posttitle"));
        
        NodeList phones = rootElem.getElementsByTagName("phone");
        List<String> resultPhones = new ArrayList<String>();
        for (int i=0; i<phones.getLength(); i++) {
            resultPhones.add(phones.item(i).getTextContent());
        }
        result.setPhones(resultPhones);
        
        NodeList emails = rootElem.getElementsByTagName("email");
        List<String> resultEmails = new ArrayList<String>();
        for (int i=0; i<emails.getLength(); i++) {
            resultEmails.add(phones.item(i).getTextContent());
        }
        result.setEmails(resultEmails);
        
        result.setStreet(getUniqueElementText(rootElem, "street"));
        result.setCity(getUniqueElementText(rootElem, "city"));
        result.setPostal(getUniqueElementText(rootElem, "postal"));
        
        
        NodeList employments = rootElem.getElementsByTagName("emp");
        Map<Integer, List<String>> resultEmployments = new HashMap<Integer, List<String>>();
        for (int i=0; i<employments.getLength(); i++) {
            List<String> employment = new ArrayList<String>();
            Element emp = (Element) employments.item(i);
           employment.add( emp.getAttribute("name"));
           employment.add( emp.getAttribute("position"));
           employment.add( emp.getAttribute("from"));
           employment.add( emp.getAttribute("to"));
           employment.add( emp.getAttribute("current"));
            
            resultEmployments.put(i, employment);
        }
        result.setEmployments(resultEmployments);
        
        NodeList educations = rootElem.getElementsByTagName("edu");
        Map<Integer, List<String>> resultEducations = new HashMap<Integer, List<String>>();
        for (int i=0; i<educations.getLength(); i++) {
            List<String> education = new ArrayList<String>();
            Element edu = (Element) educations.item(i);
           education.add( edu.getAttribute("name"));
           education.add( edu.getAttribute("field-of-study"));
           education.add( edu.getAttribute("from"));
           education.add( edu.getAttribute("to"));
           education.add( edu.getAttribute("current"));
            
           resultEducations.put(i, education);
        }
        result.setEducation(resultEducations);
        
        NodeList languages = rootElem.getElementsByTagName("lang");
        Map<String, String> resultLanguages = new HashMap<String, String>();
        for (int i=0; i<languages.getLength(); i++) {
            Element lang = (Element) languages.item(i);
            resultLanguages.put(lang.getAttribute("name"), lang.getAttribute("level"));
        }
        result.setLanguages(resultLanguages);
        
        NodeList certificates = rootElem.getElementsByTagName("cer");
        List<String> resultCertificates = new ArrayList<String>();
        for (int i=0; i<certificates.getLength(); i++) {
            resultCertificates.add(certificates.item(i).getTextContent());
        }
        result.setCertificates(resultCertificates);
        
        NodeList skills = rootElem.getElementsByTagName("sk");
        Map<String, String> resultSkills = new HashMap<String, String>();
        for (int i=0; i<skills.getLength(); i++) {
            Element lang = (Element) skills.item(i);
            resultSkills.put(lang.getAttribute("name"), lang.getAttribute("level"));
        }
        result.setSkills(resultSkills);
        
        NodeList hobbies = rootElem.getElementsByTagName("hob");
        List<String> resultHobbies = new ArrayList<String>();
        for (int i=0; i<hobbies.getLength(); i++) {
            resultHobbies.add(hobbies.item(i).getTextContent());
        }
        result.setHobbies(resultHobbies);
        
        return result;
    }
    
    private String getUniqueElementText(Element rootElem, String elemName){
        String result = "";
        
        NodeList elemList = rootElem.getElementsByTagName(elemName);
        if(elemList.getLength() != 1) { 
            return result;
        } else if(elemList.getLength() > 0) {
            result = elemList.item(0).getTextContent();
        }
        
        return result;
    }
   
}
