/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *  Auxilliary java class for class CVSchemaValidator which ensure
 *  determining exact errors which can appear during validation and better control
 *  of maintaining of validation.
 * 
 * @author Tomáš Šmíd <smid.thomas@gmail.com>
 */
public class ValidationErrorsHandler implements ErrorHandler{

        private String error;
        
        @Override
        public void warning(SAXParseException exception) throws SAXException {
            Logger.getAnonymousLogger(CVSchemaValidator.class.getName()).log(Level.INFO,exception.getMessage());
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            error = exception.getMessage();
            throw new SAXException(error);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            error = exception.getMessage();
            throw new SAXException(error);
        }
}