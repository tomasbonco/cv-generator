/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi;

import cz.muni.fi.classes.CVSchemaValidator;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class for testing whether xml schema is correctly designed or not and
 * whether xml document is correctly validated according to defined xml schema.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class SchemaTests {
    
    private CVSchemaValidator validator;
    
    public SchemaTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.validator = new CVSchemaValidator("database.xsd");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void fullFilledCorrectDoc(){
        File xmlFile = new File("test_files","database.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation fullFilledCorrectDoc should pass.", val);
    }
    
    @Test
    public void incorrectPostalType(){
        File xmlFile = new File("test_files","invalid.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation incorrectPostalType should not pass.", val);
    }

    @Test
    public void missingCvElement(){
        File xmlFile = new File("test_files","missingcvelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingCvElement should not pass.", val);
    }

    @Test
    public void missingAddressElement(){
        File xmlFile = new File("test_files","missingaddresselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAddressElement should not pass.", val);
    }

    @Test
    public void missingAttributeEduFieldofstudy(){
        File xmlFile = new File("test_files","missingattributeedufieldofstudy.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEduFieldofstudy should not pass.", val);
    }

    @Test
    public void missingAttributeEduFrom(){
        File xmlFile = new File("test_files","missingattributeedufrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEduFrom should not pass.", val);
    }

    @Test
    public void missingAttributeEduName(){
        File xmlFile = new File("test_files","missingattributeeduname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEduName should not pass.", val);
    }

    @Test
    public void missingAttributeEduTo(){
        File xmlFile = new File("test_files","missingattributeeduto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEduTo should not pass.", val);
    }

    @Test
    public void missingAttributeEmpFrom(){
        File xmlFile = new File("test_files","missingattributeempfrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEmpFrom should not pass.", val);
    }

    @Test
    public void missingAttributeEmpName(){
        File xmlFile = new File("test_files","missingattributeempname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEmpName should not pass.", val);
    }

    @Test
    public void missingAttributeEmpPosition(){
        File xmlFile = new File("test_files","missingattributeempposition.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEmpPosition should not pass.", val);
    }

    @Test
    public void missingAttributeEmpTo(){
        File xmlFile = new File("test_files","missingattributeempto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeEmpTo should not pass.", val);
    }

    @Test
    public void missingAttributeLangLevel(){
        File xmlFile = new File("test_files","missingattributelanglevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeLangLevel should not pass.", val);
    }

    @Test
    public void missingAttributeSkLevel(){
        File xmlFile = new File("test_files","missingattributesklevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeSkLevel should not pass.", val);
    }

    @Test
    public void missingAttributeSkName(){
        File xmlFile = new File("test_files","missingattributeskname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingAttributeSkName should not pass.", val);
    }

    @Test
    public void missingCerElement(){
        File xmlFile = new File("test_files","missingcerelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingCerElement should pass.", val);
    }

    @Test
    public void missingCertificatesElement(){
        File xmlFile = new File("test_files","missingcertificateselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingCertificatesElement should not pass.", val);
    }

    @Test
    public void missingCityElement(){
        File xmlFile = new File("test_files","missingcityelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingCityElement should not pass.", val);
    }

    @Test
    public void missingEducationElement(){
        File xmlFile = new File("test_files","missingeducationelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingEducationElement should not pass.", val);
    }

    @Test
    public void missingEduElement(){
        File xmlFile = new File("test_files","missingeduelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingEduElement should pass.", val);
    }

    @Test
    public void missingEmailElement(){
        File xmlFile = new File("test_files","missingemailelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingEmailElement should not pass.", val);
    }

    @Test
    public void missingEmailsElement(){
        File xmlFile = new File("test_files","missingemailselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingEmailsElement should not pass.", val);
    }

    @Test
    public void missingEmpElement(){
        File xmlFile = new File("test_files","missingempelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingEmpElement should pass.", val);
    }

    @Test
    public void missingEmploymentElement(){
        File xmlFile = new File("test_files","missingemploymentelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingEmploymentElement should not pass.", val);
    }

    @Test
    public void missingFirstnameElement(){
        File xmlFile = new File("test_files","missingfirstnameelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingFirstnameElement should not pass.", val);
    }

    @Test
    public void missingHobbiesElement(){
        File xmlFile = new File("test_files","missinghobbieselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingHobbiesElement should not pass.", val);
    }

    @Test
    public void missingHobElement(){
        File xmlFile = new File("test_files","missinghobelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingHobElement should pass.", val);
    }

    @Test
    public void missingLangElement(){
        File xmlFile = new File("test_files","missinglangelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingLangElement should pass.", val);
    }

    @Test
    public void missingLanguagesElement(){
        File xmlFile = new File("test_files","missinglanguageselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingLanguagesElement should not pass.", val);
    }

    @Test
    public void missingLastnameElement(){
        File xmlFile = new File("test_files","missinglastnameelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingLastnameElement should not pass.", val);
    }

    @Test
    public void missingPasswdElement(){
        File xmlFile = new File("test_files","missingpasswdelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPasswdElement should not pass.", val);
    }

    @Test
    public void missingPhoneElement(){
        File xmlFile = new File("test_files","missingphoneelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPhoneElement should not pass.", val);
    }

    @Test
    public void missingPhonesElement(){
        File xmlFile = new File("test_files","missingphoneselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPhonesElement should not pass.", val);
    }

    @Test
    public void missingPostalElement(){
        File xmlFile = new File("test_files","missingpostalelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPostalElement should not pass.", val);
    }

    @Test
    public void missingPosttitleElement(){
        File xmlFile = new File("test_files","missingposttitleelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPosttitleElement should not pass.", val);
    }

    @Test
    public void missingPretitleElement(){
        File xmlFile = new File("test_files","missingpretitleelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingPretitleElement should not pass.", val);
    }

    @Test
    public void missingSkElement(){
        File xmlFile = new File("test_files","missingskelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation missingSkElement should pass.", val);
    }

    @Test
    public void missingSkillsElement(){
        File xmlFile = new File("test_files","missingskillselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingSkillsElement should not pass.", val);
    }

    @Test
    public void missingStreetElement(){
        File xmlFile = new File("test_files","missingstreetelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation missingStreetElement should not pass.", val);
    }

    @Test
    public void emptyCvElement(){
        File xmlFile = new File("test_files","emptycvelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyCvElement should not pass.", val);
    }

    @Test
    public void emptyAddressElement(){
        File xmlFile = new File("test_files","emptyaddresselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAddressElement should not pass.", val);
    }

    @Test
    public void emptyAttributeEduFieldofstudy(){
        File xmlFile = new File("test_files","emptyattributeedufieldofstudy.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyAttributeEduFieldofstudy should pass.", val);
    }

    @Test
    public void emptyAttributeEduFrom(){
        File xmlFile = new File("test_files","emptyattributeedufrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyAttributeEduFrom should pass.", val);
    }

    @Test
    public void emptyAttributeEduName(){
        File xmlFile = new File("test_files","emptyattributeeduname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeEduName should not pass.", val);
    }

    @Test
    public void emptyAttributeEduTo(){
        File xmlFile = new File("test_files","emptyattributeeduto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyAttributeEduTo should pass.", val);
    }

    @Test
    public void emptyAttributeEmpFrom(){
        File xmlFile = new File("test_files","emptyattributeempfrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyAttributeEmpFrom should pass.", val);
    }

    @Test
    public void emptyAttributeEmpName(){
        File xmlFile = new File("test_files","emptyattributeempname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeEmpName should not pass.", val);
    }

    @Test
    public void emptyAttributeEmpPosition(){
        File xmlFile = new File("test_files","emptyattributeempposition.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeEmpPosition should not pass.", val);
    }

    @Test
    public void emptyAttributeEmpTo(){
        File xmlFile = new File("test_files","emptyattributeempto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyAttributeEmpTo should pass.", val);
    }

    @Test
    public void emptyAttributeLangLevel(){
        File xmlFile = new File("test_files","emptyattributelanglevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeLangLevel should not pass.", val);
    }

    @Test
    public void emptyAttributeSkLevel(){
        File xmlFile = new File("test_files","emptyattributesklevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeSkLevel should not pass.", val);
    }

    @Test
    public void emptyAttributeSkName(){
        File xmlFile = new File("test_files","emptyattributeskname.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyAttributeSkName should not pass.", val);
    }

    @Test
    public void emptyCerElement(){
        File xmlFile = new File("test_files","emptycerelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyCerElement should not pass.", val);
    }

    @Test
    public void emptyCertificatesElement(){
        File xmlFile = new File("test_files","emptycertificateselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyCertificatesElement should not pass.", val);
    }

    @Test
    public void emptyCityElement(){
        File xmlFile = new File("test_files","emptycityelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyCityElement should not pass.", val);
    }

    @Test
    public void emptyEducationElement(){
        File xmlFile = new File("test_files","emptyeducationelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyEducationElement should pass.", val);
    }

    @Test
    public void emptyEduElement(){
        File xmlFile = new File("test_files","emptyeduelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyEduElement should pass.", val);
    }

    @Test
    public void emptyEmailElement(){
        File xmlFile = new File("test_files","emptyemailelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyEmailElement should not pass.", val);
    }

    @Test
    public void emptyEmailsElement(){
        File xmlFile = new File("test_files","emptyemailselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyEmailsElement should not pass.", val);
    }

    @Test
    public void emptyEmpElement(){
        File xmlFile = new File("test_files","emptyempelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyEmpElement should pass.", val);
    }

    @Test
    public void emptyEmploymentElement(){
        File xmlFile = new File("test_files","emptyemploymentelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyEmploymentElement should pass.", val);
    }

    @Test
    public void emptyFirstnameElement(){
        File xmlFile = new File("test_files","emptyfirstnameelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyFirstnameElement should not pass.", val);
    }

    @Test
    public void emptyHobbiesElement(){
        File xmlFile = new File("test_files","emptyhobbieselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyHobbiesElement should pass.", val);
    }

    @Test
    public void emptyHobElement(){
        File xmlFile = new File("test_files","emptyhobelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyHobElement should not pass.", val);
    }

    @Test
    public void emptyLangElement(){
        File xmlFile = new File("test_files","emptylangelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyLangElement should pass.", val);
    }

    @Test
    public void emptyLanguagesElement(){
        File xmlFile = new File("test_files","emptylanguageselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyLanguagesElement should pass.", val);
    }

    @Test
    public void emptyLastnameElement(){
        File xmlFile = new File("test_files","emptylastnameelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyLastnameElement should not pass.", val);
    }

    @Test
    public void emptyPasswdElement(){
        File xmlFile = new File("test_files","emptypasswdelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyPasswdElement should not pass.", val);
    }

    @Test
    public void emptyPhoneElement(){
        File xmlFile = new File("test_files","emptyphoneelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyPhoneElement should not pass.", val);
    }

    @Test
    public void emptyPhonesElement(){
        File xmlFile = new File("test_files","emptyphoneselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyPhonesElement should not pass.", val);
    }

    @Test
    public void emptyPostalElement(){
        File xmlFile = new File("test_files","emptypostalelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyPostalElement should not pass.", val);
    }

    @Test
    public void emptyPosttitleElement(){
        File xmlFile = new File("test_files","emptyposttitleelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyPosttitleElement should pass.", val);
    }

    @Test
    public void emptyPretitleElement(){
        File xmlFile = new File("test_files","emptypretitleelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptyPretitleElement should pass.", val);
    }

    @Test
    public void emptySkElement(){
        File xmlFile = new File("test_files","emptyskelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptySkElement should pass.", val);
    }

    @Test
    public void emptySkillsElement(){
        File xmlFile = new File("test_files","emptyskillselement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertTrue("Validation emptySkillsElement should pass.", val);
    }

    @Test
    public void emptyStreetElement(){
        File xmlFile = new File("test_files","emptystreetelement.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation emptyStreetElement should not pass.", val);
    }

    @Test
    public void invalidAttributeEduFrom(){
        File xmlFile = new File("test_files","invalidattributeedufrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeEduFrom should not pass.", val);
    }

    @Test
    public void invalidAttributeEduTo(){
        File xmlFile = new File("test_files","invalidattributeeduto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeEduTo should not pass.", val);
    }

    @Test
    public void invalidAttributeEmpFrom(){
        File xmlFile = new File("test_files","invalidattributeempfrom.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeEmpFrom should not pass.", val);
    }

    @Test
    public void invalidAttributeEmpTo(){
        File xmlFile = new File("test_files","invalidattributeempto.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeEmpTo should not pass.", val);
    }

    @Test
    public void invalidAttributeLangLevel(){
        File xmlFile = new File("test_files","invalidattributelanglevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeLangLevel should not pass.", val);
    }

    @Test
    public void invalidAttributeSkLevel(){
        File xmlFile = new File("test_files","invalidattributesklevel.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidAttributeSkLevel should not pass.", val);
    }

    @Test
    public void invalidEmail(){
        File xmlFile = new File("test_files","invalidemail.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidEmail should not pass.", val);
    }

    @Test
    public void invalidPhone(){
        File xmlFile = new File("test_files","invalidphone.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidPhone should not pass.", val);
    }

    @Test
    public void invalidPosttitle(){
        File xmlFile = new File("test_files","invalidposttitle.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidPosttitle should not pass.", val);
    }

    @Test
    public void invalidPretitle(){
        File xmlFile = new File("test_files","invalidpretitle.xml");
        boolean val = this.validator.validate(xmlFile);
        assertFalse("Validation invalidPretitle should not pass.", val);
    }

}
