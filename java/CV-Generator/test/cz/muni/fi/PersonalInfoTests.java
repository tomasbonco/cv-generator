/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi;

import cz.muni.fi.classes.PersonalInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This testing java class tests whether the filling of PersonalInfo object is
 * correct or not (important for acquirement of personal info from html page).
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class PersonalInfoTests {
    
    private Map<String, String[]> multiTestData;
    private Map<String, String[]> singleTestData;
    
    public PersonalInfoTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.multiTestData = setMapData("multiTestData.txt");
        this.singleTestData = setMapData("singleTestData.txt");
    }
    
    @After
    public void tearDown() {
    }
    
    private Map<String, String[]> setMapData(String filename){
        File inputFile = new File("test_files",filename);
        Map<String, String[]> mapData = new TreeMap<String, String[]>();
        String line;
        String[] sLine;
        String[] ssLine;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
            while((line = br.readLine()) != null){
                sLine = line.split(";", 2);                
                ssLine = sLine[1].split(";");
                mapData.put(sLine[0], ssLine);                
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Such a file does not exist: "+ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }finally {
            if(br != null)
                try {
                    br.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return mapData;
    }
    
    //================ Testing of null values =====================
    @Test
    public void testNullPretitle(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullPretitle: Value of pretitle should be null, but it is not.",
                        person.getPretitle());
    }
    
    @Test
    public void testNullPosttitle(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullPosttitle: Value of posttitle should be null, but it is not.",
                        person.getPosttitle());
    }
    
    @Test
    public void testNullFirstname(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullFirstname: Value of firstname should be null, but it is not.",
                        person.getFirstname());
    }
    
    @Test
    public void testNullLastname(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullLastname: Value of lastname should be null, but it is not.",
                        person.getLastname());
    }
    
    @Test
    public void testNullStreet(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullStreet: Value of street should be null, but it is not.",
                        person.getStreet());
    }
    
    @Test
    public void testNullCity(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullCity: Value of city should be null, but it is not.",
                        person.getCity());
    }
    
    @Test
    public void testNullPostal(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullPostal: Value of postal should be null, but it is not.",
                        person.getPostal());
    }
    
    @Test
    public void testNullPhone(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullPhone: Value of phone should be null, but it is not.",
                        person.getPhones());
    }
    
    @Test
    public void testNullEmail(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullEmail: Value of email should be null, but it is not.",
                        person.getEmails());
    }
    
    @Test
    public void testNullCertificate(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullCertificate: Value of certificate should be null, but it is not.",
                        person.getCertificates());
    }
    
    @Test
    public void testNullHobby(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullHobby: Value of hobby should be null, but it is not.",
                        person.getHobbies());
    }
    
    @Test
    public void testNullEmployment(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullEmployment: Value of employment should be null, but it is not.",
                        person.getEmployments());
    }
    
    @Test
    public void testNullEducation(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullEducation: Value of education should be null, but it is not.",
                        person.getEducation());
    }
    
    @Test
    public void testNullLanguage(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullLanguage: Value of language should be null, but it is not.",
                        person.getLanguages());
    }
    
    @Test
    public void testNullSkill(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullSkill: Value of skill should be null, but it is not.",
                        person.getSkills());
    }
    
    @Test
    public void testNullPasswordHash(){
        PersonalInfo person = new PersonalInfo();
        
        assertNull("testNullPasswordHash: Value of password hash should be null, but it is not.",
                        person.getPasswordHash());
    }
    
    @Test
    public void testNotNullDateHash(){
        PersonalInfo person = new PersonalInfo();
        
        assertNotNull("testNullDateHash: Value of date hash should be null, but it is not.",
                        person.getDateHash());
    }
    
    //===== Testing of assigning single value to each PersonalInfo parameter ====
    
    @Test
    public void testSingleTestDataPretitle(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataPretitle: Values (Ing., person.getPretitle()) should be same, but they are not.",
                        "Ing.",person.getPretitle());
    }
    
    @Test
    public void testSingleTestDataPosttitle(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataPosttitle: Values (\"\", person.getPosttitle()) should be same, but they are not.",
                        "",person.getPosttitle());
    }
    
    @Test
    public void testSingleTestDataFirstname(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataFirstname: Values (Marek, person.getFirstname()) should be same, but they are not.",
                        "Marek",person.getFirstname());
    }
    
    @Test
    public void testSingleTestDataLastname(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataLastname: Values (Miguel Nedoma, person.getLastname()) should be same, but they are not.",
                        "Miguel Nedoma",person.getLastname());
    }
    
    @Test
    public void testSingleTestDataStreet(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataStreet: Values (Kardanova 1547/365, person.getStreet()) should be same, but they are not.",
                        "Kardanova 1547/365",person.getStreet());
    }
    
    @Test
    public void testSingleTestDataCity(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataCity: Values (Praha, person.getCity()) should be same, but they are not.",
                        "Praha",person.getCity());
    }
    
    @Test
    public void testSingleTestDataPostal(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataPostal: Values (587 47, person.getPostal()) should be same, but they are not.",
                        "587 47",person.getPostal());
    }
    
    @Test
    public void testSingleTestDataPhone(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataPhone: Values (+420 731 589 487, person.getPhones().get(0)) should be same, but they are not.",
                        "+420 731 589 487",person.getPhones().get(0));
    }
    
    @Test
    public void testSingleTestDataEmail(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEmail: Values (karate@kid.com, person.getEmails().get(0)) should be same, but they are not.",
                        "karate@kid.com",person.getEmails().get(0));
    }
    
    @Test
    public void testSingleTestDataCertificate(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataCertificate: Values (ISTQB, person.getCertificates().get(0)) should be same, but they are not.",
                        "ISTQB",person.getCertificates().get(0));
    }
    
    @Test
    public void testSingleTestDataHobby(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataHobby: Values (golf, person.getHobbies().get(0)) should be same, but they are not.",
                        "golf",person.getHobbies().get(0));
    }
    
    @Test
    public void testSingleTestDataEmplName(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEmplName: Values (Nokia, person.getEmployments().get(0).get(0)) should be same, but they are not.",
                        "Nokia",person.getEmployments().get(0).get(0));
        assertEquals("testSingleTestDataEmplName: Values (Manager, person.getEmployments().get(0).get(1)) should be same, but they are not.",
                        "Nokia",person.getEmployments().get(0).get(0));
    }
    
    @Test
    public void testSingleTestDataEmplPosition(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEmplPosition: Values (Manager, person.getEmployments().get(0).get(1)) should be same, but they are not.",
                        "Manager",person.getEmployments().get(0).get(1));
    }
    
    @Test
    public void testSingleTestDataEmplSince(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEmplSince: Values (2010-01, person.getEmployments().get(0).get(2)) should be same, but they are not.",
                        "2010-01",person.getEmployments().get(0).get(2));
    }
    
    @Test
    public void testSingleTestDataEmplTo(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEmplTo: Values (2011-02, person.getEmployments().get(0).get(3)) should be same, but they are not.",
                        "2011-02",person.getEmployments().get(0).get(3));
    }
    
    @Test
    public void testSingleTestDataEduName(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEduName: Values (Komenskeho gymnazium, Pribram, person.getEducation().get(0).get(0)) should be same, but they are not.",
                        "Komenskeho gymnazium, Pribram",person.getEducation().get(0).get(0));
    }
    
    @Test
    public void testSingleTestDataEduFieldOfStudy(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEduFieldOfStudy: Values (\"\", person.getEducation().get(0).get(1)) should be same, but they are not.",
                        "",person.getEducation().get(0).get(1));
    }
    
    @Test
    public void testSingleTestDataEduSince(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEduSince: Values (1996-09, person.getEducation().get(0).get(2)) should be same, but they are not.",
                        "1996-09",person.getEducation().get(0).get(2));
    }
    
    @Test
    public void testSingleTestDataEduTo(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataEduTo: Values (2000-06, person.getEducation().get(0).get(3)) should be same, but they are not.",
                        "2000-06",person.getEducation().get(0).get(3));
    }
    
    @Test
    public void testSingleTestDataLanguage(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        String[] lang = new String[1];
        int i = 0;
        for(String s: person.getLanguages().keySet()){
            lang[i] = s;
            i++;
        }
        
        assertEquals("testSingleTestDataLanguage: Values (Czech, lang[0]) should be same, but they are not.",
                        "Czech",lang[0]);
    }
    
    @Test
    public void testSingleTestDataLangLevel(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataLangLevel: Values (native speaker, person.getLanguages().get(\"Czech\")) should be same, but they are not.",
                        "native speaker",person.getLanguages().get("Czech"));
    }
    
    @Test
    public void testSingleTestDataSkill(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        String[] skill = new String[1];
        int i = 0;
        for(String s: person.getSkills().keySet()){
            skill[i] = s;
            i++;
        }
        
        assertEquals("testSingleTestDataSkill: Values (MS Office, skill[0]) should be same, but they are not.",
                        "MS Office",skill[0]);
    }
    
    @Test
    public void testSingleTestDataSkillLevel(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        assertEquals("testSingleTestDataSkillLevel: Values (basic, person.getSkills().get(\"MS Office\")) should be same, but they are not.",
                        "basic",person.getSkills().get("MS Office"));
    }
    
    @Test
    public void testSingleTestDataPasswordHash(){
        PersonalInfo person = new PersonalInfo(this.singleTestData);
        
        assertEquals("testSingleTestDataPasswordHash: Values (e343c22a26d3a2592ec3175a74f3a24a, person.getPasswordHash()) should be same, but they are not.",
                        "e343c22a26d3a2592ec3175a74f3a24a",person.getPasswordHash());
    }
    
    //===== Testing of assigning more values to the most PersonalInfo parameters ====
    
    @Test
    public void testMultiTestDataPhone(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataPhone: Values (+420 731 589 487, person.getPhones().get(0)) should be same, but they are not.",
                        "+420 731 589 487",person.getPhones().get(0));
        assertEquals("testMultiTestDataPhone: Values (+421122544879, person.getPhones().get(1)) should be same, but they are not.",
                        "+421122544879",person.getPhones().get(1));
        assertEquals("testMultiTestDataPhone: Values (654874124, person.getPhones().get(2)) should be same, but they are not.",
                        "654874124",person.getPhones().get(2));
        assertEquals("testMultiTestDataPhone: Values (+420879111000, person.getPhones().get(3)) should be same, but they are not.",
                        "+420879111000",person.getPhones().get(3));
    }
    
    @Test
    public void testMultiTestDataEmail(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataEmail: Values (karate@kid.com, person.getEmails().get(0)) should be same, but they are not.",
                        "karate@kid.com",person.getEmails().get(0));
        assertEquals("testMultiTestDataEmail: Values (pfot@gmail.com, person.getEmails().get(1)) should be same, but they are not.",
                        "pfot@gmail.com",person.getEmails().get(1));
        assertEquals("testMultiTestDataEmail: Values (josmas@masopust.cz, person.getEmails().get(2)) should be same, but they are not.",
                        "josmas@masopust.cz",person.getEmails().get(2));
    }
    
    @Test
    public void testMultiTestDataCompNames(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataCompNames: Values (Nokia, person.getEmployments().get(0).get(0)) should be same, but they are not.",
                        "Nokia",person.getEmployments().get(0).get(0));
        assertEquals("testMultiTestDataCompNames: Values (Samsung, person.getEmployments().get(1).get(0)) should be same, but they are not.",
                        "Samsung",person.getEmployments().get(1).get(0));
        assertEquals("testMultiTestDataCompNames: Values (ABB, person.getEmployments().get(2).get(0)) should be same, but they are not.",
                        "ABB",person.getEmployments().get(2).get(0));
        assertEquals("testMultiTestDataCompNames: Values (AT&T, person.getEmployments().get(3).get(0)) should be same, but they are not.",
                        "AT&T",person.getEmployments().get(3).get(0));
        assertEquals("testMultiTestDataCompNames: Values (Google, person.getEmployments().get(4).get(0)) should be same, but they are not.",
                        "Google",person.getEmployments().get(4).get(0));
    }
    
    @Test
    public void testMultiTestDataCompPositions(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataCompPositions: Values (Manager, person.getEmployments().get(0).get(1)) should be same, but they are not.",
                        "Manager",person.getEmployments().get(0).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (Manager, person.getEmployments().get(1).get(1)) should be same, but they are not.",
                        "Manager",person.getEmployments().get(1).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (Programmer, person.getEmployments().get(2).get(1)) should be same, but they are not.",
                        "Programmer",person.getEmployments().get(2).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (Programmer, person.getEmployments().get(3).get(1)) should be same, but they are not.",
                        "Programmer",person.getEmployments().get(3).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (Developer, person.getEmployments().get(4).get(1)) should be same, but they are not.",
                        "Developer",person.getEmployments().get(4).get(1));
    }
    
    @Test
    public void testMultiTestDataCompSince(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataCompSince: Values (2010-01, person.getEmployments().get(0).get(2)) should be same, but they are not.",
                        "2010-01",person.getEmployments().get(0).get(2));
        assertEquals("testMultiTestDataCompSince: Values (2011-02, person.getEmployments().get(1).get(2)) should be same, but they are not.",
                        "2011-02",person.getEmployments().get(1).get(2));
        assertEquals("testMultiTestDataCompSince: Values (2012-05, person.getEmployments().get(2).get(2)) should be same, but they are not.",
                        "2012-05",person.getEmployments().get(2).get(2));
        assertEquals("testMultiTestDataCompSince: Values (2012-08, person.getEmployments().get(3).get(2)) should be same, but they are not.",
                        "2012-08",person.getEmployments().get(3).get(2));
        assertEquals("testMultiTestDataCompSince: Values (2014-03, person.getEmployments().get(4).get(2)) should be same, but they are not.",
                        "2014-03",person.getEmployments().get(4).get(2));
    }
    
    @Test
    public void testMultiTestDataCompTo(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataCompTo: Values (2011-02, person.getEmployments().get(0).get(3)) should be same, but they are not.",
                        "2011-02",person.getEmployments().get(0).get(3));
        assertEquals("testMultiTestDataCompTo: Values (2012-04, person.getEmployments().get(1).get(3)) should be same, but they are not.",
                        "2012-04",person.getEmployments().get(1).get(3));
        assertEquals("testMultiTestDataCompTo: Values (2013-06, person.getEmployments().get(2).get(3)) should be same, but they are not.",
                        "2013-06",person.getEmployments().get(2).get(3));
        assertEquals("testMultiTestDataCompTo: Values (\"\", person.getEmployments().get(3).get(3)) should be same, but they are not.",
                        "",person.getEmployments().get(3).get(3));
        assertEquals("testMultiTestDataCompTo: Values (\"\", person.getEmployments().get(4).get(3)) should be same, but they are not.",
                        "",person.getEmployments().get(4).get(3));
    }
    
    @Test
    public void testMultiTestDataEduNames(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataEduNames: Values (Komenskeho gymnazium, Pribram, person.getEducation().get(0).get(0)) should be same, but they are not.",
                        "Komenskeho gymnazium, Pribram",person.getEducation().get(0).get(0));
        assertEquals("testMultiTestDataEduNames: Values (Masarykova Univerzita, Brno, person.getEducation().get(1).get(0)) should be same, but they are not.",
                        "Masarykova Univerzita, Brno",person.getEducation().get(1).get(0));
        assertEquals("testMultiTestDataEduNames: Values (Masarykova Univerzita, Brno, person.getEducation().get(2).get(0)) should be same, but they are not.",
                        "Masarykova Univerzita, Brno",person.getEducation().get(2).get(0));
    }
    
    @Test
    public void testMultiTestDataEduPositions(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataEduPositions: Values (\"\", person.getEducation().get(0).get(1)) should be same, but they are not.",
                        "",person.getEducation().get(0).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (informatics, person.getEducation().get(1).get(1)) should be same, but they are not.",
                        "informatics",person.getEducation().get(1).get(1));
        assertEquals("testMultiTestDataCompPositions: Values (mathematics, person.getEducation().get(2).get(1)) should be same, but they are not.",
                        "mathematics",person.getEducation().get(2).get(1));        
    }
    
    @Test
    public void testMultiTestDataEduSince(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataEduSince: Values (1996-09, person.getEducation().get(0).get(2)) should be same, but they are not.",
                        "1996-09",person.getEducation().get(0).get(2));
        assertEquals("testMultiTestDataEduSince: Values (2000-09, person.getEducation().get(1).get(2)) should be same, but they are not.",
                        "2000-09",person.getEducation().get(1).get(2));
        assertEquals("testMultiTestDataEduSince: Values (2001-09, person.getEducation().get(2).get(2)) should be same, but they are not.",
                        "2001-09",person.getEducation().get(2).get(2));
    }
    
    @Test
    public void testMultiTestDataEduTo(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataEduTo: Values (2000-06, person.getEducation().get(0).get(3)) should be same, but they are not.",
                        "2000-06",person.getEducation().get(0).get(3));
        assertEquals("testMultiTestDataEduTo: Values (2010-06, person.getEducation().get(1).get(3)) should be same, but they are not.",
                        "2010-06",person.getEducation().get(1).get(3));
        assertEquals("testMultiTestDataEduTo: Values (2006-06, person.getEducation().get(2).get(3)) should be same, but they are not.",
                        "2006-06",person.getEducation().get(2).get(3));
    }
    
    @Test
    public void testMultiTestDataLanguages(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);        
        String[] lang = new String[4];
        int i = 0;
        for(String s: person.getLanguages().keySet()){            
            lang[i] = s;
            i++;
        }
        
        assertEquals("testMultiTestDataLanguages: Values (Czech, lang[2]) should be same, but they are not.",
                        "Czech",lang[2]);
        assertEquals("testMultiTestDataLanguages: Values (English, lang[3]) should be same, but they are not.",
                        "English",lang[3]);
        assertEquals("testMultiTestDataLanguages: Values (Spanish, lang[0]) should be same, but they are not.",
                        "Spanish",lang[0]);
        assertEquals("testMultiTestDataLanguages: Values (Russian, lang[1]) should be same, but they are not.",
                        "Russian",lang[1]);
    }
    
    @Test
    public void testMultiTestDataLangLevels(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataLangLevels: Values (native speaker, person.getLanguages().get(\"Czech\")) should be same, but they are not.",
                        "native speaker",person.getLanguages().get("Czech"));
        assertEquals("testMultiTestDataLangLevels: Values (advanced, person.getLanguages().get(\"English\")) should be same, but they are not.",
                        "advanced",person.getLanguages().get("English"));
        assertEquals("testMultiTestDataLangLevels: Values (basic, person.getLanguages().get(\"Spanish\")) should be same, but they are not.",
                        "basic",person.getLanguages().get("Spanish"));
        assertEquals("testMultiTestDataLangLevels: Values (intermediate, person.getLanguages().get(\"Russian\")) should be same, but they are not.",
                        "intermediate",person.getLanguages().get("Russian"));
    }
    
    @Test
    public void testMultiTestDataCertificates(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataCertificates: Values (ISTQB, person.getCertificates().get(0)) should be same, but they are not.",
                        "ISTQB",person.getCertificates().get(0));
        assertEquals("testMultiTestDataCertificates: Values (Cisco CNNA, person.getCertificates().get(1)) should be same, but they are not.",
                        "Cisco CNNA",person.getCertificates().get(1));
    }
    
    @Test
    public void testMultiTestDataHobbies(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataHobbies: Values (golf, person.getHobbies().get(0)) should be same, but they are not.",
                        "golf",person.getHobbies().get(0));
        assertEquals("testMultiTestDataHobbies: Values (football, person.getHobbies().get(0)) should be same, but they are not.",
                        "football",person.getHobbies().get(1));
        assertEquals("testMultiTestDataHobbies: Values (tennis, person.getHobbies().get(0)) should be same, but they are not.",
                        "tennis",person.getHobbies().get(2));
    }
    
    @Test
    public void testMultiTestDataSkills(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        String[] skill = new String[4];
        int i = 0;
        for(String s: person.getSkills().keySet()){
            skill[i] = s;
            i++;
        }
        
        assertEquals("testMultiTestDataSkills: Values (MS Office, skill[1]) should be same, but they are not.",
                        "MS Office",skill[1]);
        assertEquals("testMultiTestDataSkills: Values (Java, skill[3]) should be same, but they are not.",
                        "Java",skill[3]);
        assertEquals("testMultiTestDataSkills: Values (Perl, skill[2]) should be same, but they are not.",
                        "Perl",skill[2]);
        assertEquals("testMultiTestDataSkills: Values (PHP, skill[0]) should be same, but they are not.",
                        "PHP",skill[0]);
    }
    
    @Test
    public void testMultiTestDataSkillLevels(){
        PersonalInfo person = new PersonalInfo(this.multiTestData);
        
        assertEquals("testMultiTestDataSkillLevesls: Values (basic, person.getSkills().get(\"MS Office\")) should be same, but they are not.",
                        "basic",person.getSkills().get("MS Office"));
        assertEquals("testMultiTestDataSkillLevels: Values (advanced, person.getSkills().get(\"Java\")) should be same, but they are not.",
                        "advanced",person.getSkills().get("Java"));
        assertEquals("testMultiTestDataSkillLevels: Values (intermediate, person.getSkills().get(\"Perl\")) should be same, but they are not.",
                        "intermediate",person.getSkills().get("Perl"));
        assertEquals("testMultiTestDataSkillLevels: Values (advanced, person.getSkills().get(\"PHP\")) should be same, but they are not.",
                        "advanced",person.getSkills().get("PHP"));
    }
}