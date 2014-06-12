/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi;

import cz.muni.fi.classes.PDFfromLatexBuilder;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jan Polišenský <polisensky88 at gmail.com>
 */
public class PDFfromLatexBuilderTests {
    
    private PDFfromLatexBuilder pflb;
    private File latexFile;
    private File pdfFile;
    
    public PDFfromLatexBuilderTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //path to latex must be set
        pflb = new PDFfromLatexBuilder("C:\\texlive\\2013\\bin\\win32\\");
        latexFile = new File("test_files","simple_latex_cv");
        pdfFile = new File(pflb.createPDF(latexFile));
    }
    
    @After
    public void tearDown() {
        pdfFile.delete();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void PDFCreated(){
        assertTrue(pdfFile.exists());
    }
    
    @Test
    public void intermediateDviDeleted(){
        assertFalse(new File(latexFile.getAbsolutePath()+".dvi").exists());
    }
    
    @Test
    public void intermediateLogDeleted(){
        assertFalse(new File(latexFile.getAbsolutePath()+".log").exists());
    }
    
    @Test
    public void intermediateOutDeleted(){
        assertFalse(new File(latexFile.getAbsolutePath()+".out").exists());
    }
    
    @Test
    public void intermediateAuxDeleted(){
        assertFalse(new File(latexFile.getAbsolutePath()+".aux").exists());
    }
    
}
