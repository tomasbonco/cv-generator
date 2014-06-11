/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi;

import cz.muni.fi.classes.PersonalInfo;
import cz.muni.fi.classes.PersonalInfoBuilder;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This testing class verifies whether object's methods of
 * PersonalInfoBuilder work correctly.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class PersonalInfoBuilderTests {
    
    private PersonalInfoBuilder pib;
    private File xmlFile;
    
    public PersonalInfoBuilderTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.pib = new PersonalInfoBuilder();
        xmlFile = new File("test_files","testPIB.xml");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testPretitle(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testPretitle: pretitles should be same, but they are not.",
                        "Bc.",person.getPretitle());
    }
    
    @Test
    public void testPosttitle(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testPosttitle: posttitles should be same, but they are not.",
                        "",person.getPosttitle());
    }
    
    @Test
    public void testFirstname(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testFirstname: firsnames should be same, but they are not.",
                        "Arnold",person.getFirstname());
    }
    
    @Test
    public void testLastname(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testLastname: lastnames should be same, but they are not.",
                        "Novak",person.getLastname());
    }
    
    @Test
    public void testStreet(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testStreet: streets should be same, but they are not.",
                        "Hrncirska 123/4",person.getStreet());
    }
    
    @Test
    public void testCity(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testCity: cities should be same, but they are not.",
                        "Brno",person.getCity());
    }
    
    @Test
    public void testPostal(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testPostal:postals should be same, but they are not.",
                        "001 01",person.getPostal());
    }
    
    @Test
    public void testPhones(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testPhones: phones with index = 0 should be same, but they are not.",
                        "+420 123 456 789",person.getPhones().get(0));
        assertEquals("testPhones: phones with index = 1 should be same, but they are not.",
                        "+420 123 456 800",person.getPhones().get(1));
    }
    
    @Test
    public void testEmails(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEmails: emails with index = 0 should be same, but they are not.",
                        "arnoldnovak@gmail.com",person.getEmails().get(0));
        assertEquals("testEmails: emails with index = 1 should be same, but they are not.",
                        "arnoldnovak@fi.muni.cz",person.getEmails().get(1));
    }
    
    @Test
    public void testEmplNames(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEmplNames: emplNames with index = 0 should be same, but they are not.",
                        "Google",person.getEmployments().get(0).get(0));
        assertEquals("testEmplNames: emplNames with index = 1 should be same, but they are not.",
                        "Apple",person.getEmployments().get(1).get(0));
    }
    
    @Test
    public void testEmplPositions(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEmplPositions: emplPositions with index = 0 should be same, but they are not.",
                        "Developer",person.getEmployments().get(0).get(1));
        assertEquals("testEmplPositions: emplPositions with index = 1 should be same, but they are not.",
                        "Manager",person.getEmployments().get(1).get(1));
    }
    
    @Test
    public void testEmplSince(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEmplSince: emplSince with index = 0 should be same, but they are not.",
                        "2013-01",person.getEmployments().get(0).get(2));
        assertEquals("testEmplSince: emplSince with index = 1 should be same, but they are not.",
                        "2014-04",person.getEmployments().get(1).get(2));
    }
    
    @Test
    public void testEmplTo(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);        
        assertEquals("testEmplTo: emplTo with index = 0 should be same, but they are not.",
                        "2014-03",person.getEmployments().get(0).get(3));
        assertEquals("testEmplTo: emplTo with index = 1 should be same, but they are not.",
                        "",person.getEmployments().get(1).get(3));
    }
    
    @Test
    public void testEduNames(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEduNames: eduNames with index = 0 should be same, but they are not.",
                        "MUNI",person.getEducation().get(0).get(0));
        assertEquals("testEduNames: eduNames with index = 1 should be same, but they are not.",
                        "Gymnasium X",person.getEducation().get(1).get(0));
    }
    
    @Test
    public void testEduPositions(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEduPositions: eduPositions with index = 0 should be same, but they are not.",
                        "Graphics",person.getEducation().get(0).get(1));
        assertEquals("testEduPositions: eduPositions with index = 1 should be same, but they are not.",
                        "",person.getEducation().get(1).get(1));
    }
    
    @Test
    public void testEduSince(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testEduSince: eduSince with index = 0 should be same, but they are not.",
                        "2000-09",person.getEducation().get(0).get(2));
        assertEquals("testEduSince: eduSince with index = 1 should be same, but they are not.",
                        "2004-09",person.getEducation().get(1).get(2));
    }
    
    @Test
    public void testEduTo(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);        
        assertEquals("testEduTo: eduTo with index = 0 should be same, but they are not.",
                        "2011-06",person.getEducation().get(0).get(3));
        assertEquals("testEduTo: eduTo with index = 1 should be same, but they are not.",
                        "2007-06",person.getEducation().get(1).get(3));
    }
    
    @Test
    public void testCertificates(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testCertificates: certificates with index = 0 should be same, but they are not.",
                        "CISCO networks",person.getCertificates().get(0));
        assertEquals("testCertificates: certificates with index = 1 should be same, but they are not.",
                        "Klingongstina pre zaciatocnikov",person.getCertificates().get(1));
    }
    
    @Test
    public void testHobbies(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        assertEquals("testHobbies: hobbies with index = 0 should be same, but they are not.",
                        "Eating",person.getHobbies().get(0));
        assertEquals("testHobbies: hobbies with index = 1 should be same, but they are not.",
                        "Sleeping",person.getHobbies().get(1));
        assertEquals("testHobbies: hobbies with index = 2 should be same, but they are not.",
                        "Waiting",person.getHobbies().get(2));
    }
    
    @Test
    public void testLanguages(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        String[] perLang = new String[4];
        int i = 0;
        for(String s: person.getLanguages().keySet()){
            perLang[i] = s;
            i++;
        }
        
        assertEquals("testLanguages: languages with index = 0 should be same, but the are not.",
                        "Spanish", perLang[0]);
        assertEquals("testLanguages: languages with index = 1 should be same, but the are not.",
                        "Czech", perLang[1]);
        assertEquals("testLanguages: languages with index = 2 should be same, but the are not.",
                        "German", perLang[2]);
        assertEquals("testLanguages: languages with index = 3 should be same, but the are not.",
                        "English", perLang[3]);
    }
    
    @Test
    public void testLangLevels(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        String[] perLevels = new String[4];
        int i = 0;
        for(String s: person.getLanguages().keySet()){
            perLevels[i] = person.getLanguages().get(s);
            i++;
        }
        
        assertEquals("testLangLevels: levels with index = 0 should be same, but they are not.",
                        "basic",perLevels[0]);
        assertEquals("testLangLevels: levels with index = 1 should be same, but they are not.",
                        "native speaker",perLevels[1]);
        assertEquals("testLangLevels: levels with index = 2 should be same, but they are not.",
                        "intermediate",perLevels[2]);
        assertEquals("testLangLevels: levels with index = 3 should be same, but they are not.",
                        "advanced",perLevels[3]);
    }
    
    @Test
    public void testSkills(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        String[] perSkill = new String[3];
        int i = 0;
        for(String s: person.getSkills().keySet()){
            perSkill[i] = s;
            i++;
        }
        
        assertEquals("testSkills: skills with index = 0 should be same, but the are not.",
                        "Leadership skills", perSkill[0]);
        assertEquals("testSkills: skills with index = 1 should be same, but the are not.",
                        "MS Office", perSkill[1]);
        assertEquals("testSkills: skills with index = 2 should be same, but the are not.",
                        "Internet Explorer", perSkill[2]);
    }
    
    @Test
    public void testSkillLevels(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        String[] perLevels = new String[4];
        int i = 0;
        for(String s: person.getSkills().keySet()){
            perLevels[i] = person.getSkills().get(s);
            i++;
        }
        
        assertEquals("testSkillLevels: levels with index = 0 should be same, but they are not.",
                        "basic",perLevels[0]);
        assertEquals("testSkillLevels: levels with index = 1 should be same, but they are not.",
                        "advanced",perLevels[1]);
        assertEquals("testSkillLevels: levels with index = 2 should be same, but they are not.",
                        "intermediate",perLevels[2]);
    }
    
    @Test
    public void testPasswordHash(){
        PersonalInfo person = this.pib.newPersonalInfo(this.xmlFile);
        
        assertEquals("testPasswordHash: password hashes should be same, but they are not.",
                        "e343c22a26d3a2592ec3175a74f3a24a", person.getPasswordHash());
    }
}
