/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Java class for transformation xml file to tex file.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class XSLTransformer {
    
    /**
     * This method transforms xml file to tex file.
     * 
     * @param xslFile   XSLT according to is processed the transformation
     * @param xmlFile   xml document which will be transformed to tex file
     * @param destFile  tex file, result of transformation
     */
    public void transformToTex(String xslFile, String xmlFile, String destFile){
        
        TransformerFactory tf = TransformerFactory.newInstance();        
        Transformer xsltProc;
        File dir = new File("pdf_database");
        if(!(dir.exists() && dir.isDirectory())){
            dir.mkdir();
        }
        try {        
            xsltProc = tf.newTransformer(new StreamSource(new File(xslFile)));
            xsltProc.transform(new StreamSource(new File("database",xmlFile)),
                               new StreamResult(new File(dir,destFile)));
        } catch (TransformerConfigurationException ex) {
            System.err.println("Transformer initialization error: "+ex.getMessage());
        } catch (TransformerException ex) {
            System.err.println("Transformation error: "+ex.getMessage());
        }        
    }
}
