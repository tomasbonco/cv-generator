/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *  Auxilliary java class for class CVSchemaValidator which ensure collecting of errors
 *  which can appear during validation, determining exact errors which can appear during
 *  validation and better control of maintaining of validation.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class ValidationErrorsHandler implements ErrorHandler{

        private String error;
        private String warning;
        
        @Override
        public void warning(SAXParseException exception) throws SAXException {
            this.warning = exception.getMessage();
            throw new SAXException(this.warning); 
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            this.error = exception.getMessage();
            throw new SAXException(this.error);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            this.error = exception.getMessage();
            throw new SAXException(this.error);
        }
}
