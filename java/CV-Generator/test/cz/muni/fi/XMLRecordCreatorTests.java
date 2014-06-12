/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi;

import cz.muni.fi.classes.PersonalInfo;
import cz.muni.fi.classes.XMLRecordCreator;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This testing class verify whether java class XMLRecordCreator works
 * correctly.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class XMLRecordCreatorTests {
    
    private XMLRecordCreator xmlrc;
    private File genXmlFile;
    private Document doc;
    
    public XMLRecordCreatorTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        PersonalInfo pi = setPersonalInfo();
        this.xmlrc = new XMLRecordCreator();
        File f = new File("");
        String path = f.getAbsolutePath();
        xmlrc.generateXML(pi, path);
        this.genXmlFile = new File(path+"/database",pi.getDateHash()+".xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            this.doc = db.parse(genXmlFile);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @After
    public void tearDown() {
        this.genXmlFile.delete();
    }

    private PersonalInfo setPersonalInfo(){
        PersonalInfo person = new PersonalInfo();
        List<String> phones = new ArrayList<String>();
        List<String> emails = new ArrayList<String>();
        List<String> empl1 = new ArrayList<String>();
        List<String> empl2 = new ArrayList<String>();
        List<String> edu1 = new ArrayList<String>();
        List<String> edu2 = new ArrayList<String>();
        List<String> certs = new ArrayList<String>();
        List<String> hob = new ArrayList<String>();
        Map<Integer, List<String>> empls = new HashMap<Integer, List<String>>();
        Map<Integer, List<String>> edus = new HashMap<Integer, List<String>>();
        Map<String, String> langs = new HashMap<String, String>();
        Map<String, String> skills = new HashMap<String, String>();
        
        phones.add("+420 123 456 789");
        phones.add("+420 123 456 800");
        
        emails.add("arnoldnovak@gmail.com");
        emails.add("arnoldnovak@fi.muni.cz");
        
        empl1.add("Google");
        empl1.add("Developer");
        empl1.add("2013-01");
        empl1.add("2014-03");
        empl2.add("Apple");
        empl2.add("Manager");
        empl2.add("2014-04");
        empl2.add("");
        empls.put(0, empl1);
        empls.put(1, empl2);   
        
        edu1.add("MUNI");
        edu1.add("Graphics");
        edu1.add("2000-09");
        edu1.add("2011-06");
        edu2.add("Gymnasium X");
        edu2.add("");
        edu2.add("2004-09");
        edu2.add("2007-06");
        edus.put(0, edu1);
        edus.put(1, edu2);
        
        langs.put("Czech", "native speaker");
        langs.put("Spanish", "basic");
        langs.put("German", "intermediate");
        langs.put("English", "advanced");
        
        certs.add("CISCO networks");
        certs.add("Klingongstina pre zaciatocnikov");
        
        skills.put("Leadership skills", "basic");
        skills.put("Internet Explorer", "intermediate");        
        skills.put("MS Office", "advanced");
        
        hob.add("Eating");
        hob.add("Sleeping");
        hob.add("Waiting");
        
        person.setPretitle("Bc.");
        person.setPosttitle("");
        person.setFirstname("Arnold");
        person.setLastname("Novak");
        person.setStreet("Hrncirska 123/4");
        person.setCity("Brno");
        person.setPostal("001 01");
        person.setPhones(phones);
        person.setEmails(emails);
        person.setEmployments(empls);
        person.setEducation(edus);
        person.setLanguages(langs);
        person.setCertificates(certs);
        person.setSkills(skills);
        person.setHobbies(hob);
        person.setPasswordHash1("jej457da");
        
        return person;
    }
    
    @Test
    public void testPretitle(){        
        NodeList elemList = this.doc.getElementsByTagName("pretitle");
        String pretitle = elemList.item(0).getTextContent();
        assertEquals("testPretitle: Pretitles should be same, but they are not.",
                        "Bc.", pretitle);
    }
    
    @Test
    public void testPosttitle(){        
        NodeList elemList = this.doc.getElementsByTagName("posttitle");
        String posttitle = elemList.item(0).getTextContent();
        assertEquals("testPosttitle: Posttitles should be same, but they are not.",
                        "", posttitle);
    }
    
    @Test
    public void testFirstname(){        
        NodeList elemList = this.doc.getElementsByTagName("firstname");
        String firstname = elemList.item(0).getTextContent();
        assertEquals("testFirstname: Firstnames should be same, but they are not.",
                        "Arnold", firstname);
    }
    
    @Test
    public void testLastName(){        
        NodeList elemList = this.doc.getElementsByTagName("lastname");
        String lastname = elemList.item(0).getTextContent();
        assertEquals("testLastname: Lastnames should be same, but they are not.",
                        "Novak", lastname);
    }
    
    @Test
    public void testStreet(){        
        NodeList elemList = this.doc.getElementsByTagName("street");
        String street = elemList.item(0).getTextContent();
        assertEquals("testStreet: Streets should be same, but they are not.",
                        "Hrncirska 123/4", street);
    }
    
    @Test
    public void testCity(){        
        NodeList elemList = this.doc.getElementsByTagName("city");
        String city = elemList.item(0).getTextContent();
        assertEquals("testCity: Streets should be same, but they are not.",
                        "Brno", city);
    }
    
    @Test
    public void testPostal(){        
        NodeList elemList = this.doc.getElementsByTagName("postal");
        String postal = elemList.item(0).getTextContent();
        assertEquals("testPostal: Postals should be same, but they are not.",
                        "001 01", postal);
    }
    
    @Test
    public void testPasswordHash(){        
        NodeList elemList = this.doc.getElementsByTagName("passwd");
        String pswh = elemList.item(0).getTextContent();
        assertEquals("testPasswordHash: Password hashes should be same, but they are not.",
                        "e343c22a26d3a2592ec3175a74f3a24a", pswh);
    }
    
    @Test
    public void testPhone(){        
        NodeList elemList = this.doc.getElementsByTagName("phone");
        String[] phones = new String[2];
        for(int i = 0; i < 2; i++){
            phones[i] = elemList.item(i).getTextContent();
        }
        assertEquals("testPhone: Phones with index = 0 should be same, but they are not.",
                        "+420 123 456 789", phones[0]);
        assertEquals("testPhone: Phones with index = 1 should be same, but they are not.",
                        "+420 123 456 800", phones[1]);
    }
    
    @Test
    public void testEmail(){        
        NodeList elemList = this.doc.getElementsByTagName("email");
        String[] emails = new String[2];
        for(int i = 0; i < 2; i++){
            emails[i] = elemList.item(i).getTextContent();
        }
        assertEquals("testEmail: Emails with index = 0 should be same, but they are not.",
                        "arnoldnovak@gmail.com", emails[0]);
        assertEquals("testEmail: Emails with index = 1 should be same, but they are not.",
                        "arnoldnovak@fi.muni.cz", emails[1]);
    }
    
    @Test
    public void testEmployment(){
        NodeList elemList = this.doc.getElementsByTagName("emp");
        String[] names = new String[2];
        String[] pos = new String[2];
        String[] since = new String[2];
        String[] to = new String[2];
        for(int i = 0; i < elemList.getLength(); i++){
            Element elem = (Element) elemList.item(i);
            names[i] = elem.getAttribute("name");
            pos[i] = elem.getAttribute("position");
            since[i] = elem.getAttribute("from");
            to[i] = elem.getAttribute("to");
        }
        assertEquals("testEmployment: Names with index = 0 should be same, but they are not.",
                        "Google",names[0]);
        assertEquals("testEmployment: Names with index = 1 should be same, but they are not.",
                        "Apple",names[1]);
        assertEquals("testEmployment: Positions with index = 0 should be same, but they are not.",
                        "Developer",pos[0]);
        assertEquals("testEmployment: Positions with index = 1 should be same, but they are not.",
                        "Manager",pos[1]);
        assertEquals("testEmployment: Since with index = 0 should be same, but they are not.",
                        "2013-01",since[0]);
        assertEquals("testEmployment: Since with index = 1 should be same, but they are not.",
                        "2014-04",since[1]);
        assertEquals("testEmployment: To with index = 0 should be same, but they are not.",
                        "2014-03",to[0]);
        assertEquals("testEmployment: To with index = 1 should be same, but they are not.",
                        "",to[1]);
    }
    
    @Test
    public void testEducation(){
        NodeList elemList = this.doc.getElementsByTagName("edu");
        String[] names = new String[2];
        String[] pos = new String[2];
        String[] since = new String[2];
        String[] to = new String[2];
        for(int i = 0; i < elemList.getLength(); i++){
            Element elem = (Element) elemList.item(i);
            names[i] = elem.getAttribute("name");
            pos[i] = elem.getAttribute("field-of-study");
            since[i] = elem.getAttribute("from");
            to[i] = elem.getAttribute("to");
        }
        assertEquals("testEducation: Names with index = 0 should be same, but they are not.",
                        "MUNI",names[0]);
        assertEquals("testEducation: Names with index = 1 should be same, but they are not.",
                        "Gymnasium X",names[1]);
        assertEquals("testEducation: Positions with index = 0 should be same, but they are not.",
                        "Graphics",pos[0]);
        assertEquals("testEducation: Positions with index = 1 should be same, but they are not.",
                        "",pos[1]);
        assertEquals("testEducation: Since with index = 0 should be same, but they are not.",
                        "2000-09",since[0]);
        assertEquals("testEducation: Since with index = 1 should be same, but they are not.",
                        "2004-09",since[1]);
        assertEquals("testEducation: To with index = 0 should be same, but they are not.",
                        "2011-06",to[0]);
        assertEquals("testEmployment: To with index = 1 should be same, but they are not.",
                        "2007-06",to[1]);
    }
    
    @Test
    public void testLanguage(){
        NodeList elemList = this.doc.getElementsByTagName("lang");
        String[] names = new String[4];
        String[] levels = new String[4];
        for(int i = 0; i < elemList.getLength(); i++){
            Element elem = (Element) elemList.item(i);
            names[i] = elem.getAttribute("name");
            levels[i] = elem.getAttribute("level");
        }
        assertEquals("testLanguage: Names with index = 0 should be same, but they are not.",
                        "Spanish",names[0]);
        assertEquals("testLanguage: Names with index = 1 should be same, but they are not.",
                        "Czech",names[1]);
        assertEquals("testLanguage: Names with index = 2 should be same, but they are not.",
                        "German",names[2]);
        assertEquals("testLanguage: Names with index = 3 should be same, but they are not.",
                        "English",names[3]);
        assertEquals("testLanguage: Levels with index = 0 should be same, but they are not.",
                        "basic",levels[0]);
        assertEquals("testLanguage: Levels with index = 1 should be same, but they are not.",
                        "native speaker",levels[1]);
        assertEquals("testLanguage: Levels with index = 2 should be same, but they are not.",
                        "intermediate",levels[2]);
        assertEquals("testLanguage: Levels with index = 3 should be same, but they are not.",
                        "advanced",levels[3]);
    }
    
    @Test
    public void testCertificate(){        
        NodeList elemList = this.doc.getElementsByTagName("cer");
        String[] certs = new String[2];
        for(int i = 0; i < 2; i++){
            certs[i] = elemList.item(i).getTextContent();
        }
        assertEquals("testCertificate: Certificates with index = 0 should be same, but they are not.",
                        "CISCO networks", certs[0]);
        assertEquals("testcertificate: Certificates with index = 1 should be same, but they are not.",
                        "Klingongstina pre zaciatocnikov", certs[1]);
    }
    
    @Test
    public void testHobby(){        
        NodeList elemList = this.doc.getElementsByTagName("hob");
        String[] hobbies = new String[3];
        for(int i = 0; i < 3; i++){
            hobbies[i] = elemList.item(i).getTextContent();
        }
        assertEquals("testHobby: Hobbies with index = 0 should be same, but they are not.",
                        "Eating", hobbies[0]);
        assertEquals("testHobby: Hobbies with index = 1 should be same, but they are not.",
                        "Sleeping", hobbies[1]);
        assertEquals("testHobby: Hobbies with index = 2 should be same, but they are not.",
                        "Waiting", hobbies[2]);
    }
    
    @Test
    public void testErrorAttribute(){
        PersonalInfo pi = setPersonalInfo();
        List<String> empl = new ArrayList<String>();
        Map<Integer, List<String>> empls = new HashMap<Integer, List<String>>();
        empl.add("Google");
        empl.add("Developer");
        empl.add("2013-01");
        empl.add("2000-02");
        empls.put(0, empl);
        pi.setEmployments(empls);
        XMLRecordCreator creator = new XMLRecordCreator();
        File f = new File("");
        String path = f.getAbsolutePath();
        creator.generateXML(pi, path);
        File xmlFile = new File(path,"invalid.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            document = db.parse(xmlFile);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLRecordCreatorTests.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            xmlFile.delete();
        }
        
        NodeList elemList = document.getElementsByTagName("emp");
        String error = null;
        for(int i = 0; i < elemList.getLength(); i++){
            Element elem = (Element)elemList.item(i);
            error = elem.getAttribute("error");
        }
        assertEquals("testErrorAtribute: Error atribute should have value = 1, but it does not have.",
                        "1", error);
        xmlFile.delete();
    }
}
