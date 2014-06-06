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
}
