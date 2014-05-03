/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.server;


import cz.muni.fi.classes.CVSchemaValidator;
import cz.muni.fi.classes.XMLCreator;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 * For this moment, this web servlet just loads a name from an html page, saves
 * that name to the xml document (if exists) or generate new xml document with
 * that name. Then that new or changed xml document is validated and if there 
 * is no problem, web servlet send to the server answer that everything is correct
 * and show that response on html page.
 * 
 * @author Tomáš Šmíd <smid.thomas@gmail.com>
 */
@WebServlet("/create-new-profile")
public class ServerCommunication extends HttpServlet {
    
   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        
        String name = request.getParameter("name"); //getting a name from html page
        try{            
            XMLCreator xmlc = new XMLCreator(new File("C:\\Users\\Tom\\Documents\\NetBeansProjects\\CV-Generator\\data.xml"));            
            CVSchemaValidator nv = new CVSchemaValidator("C:\\Users\\Tom\\Documents\\NetBeansProjects\\CV-Generator\\simple_database.xsd");
            
            xmlc.generateXML(name); //name is added to the xml document
            
            //xml document is checked if is valid
            if(nv.validate("C:\\Users\\Tom\\Documents\\NetBeansProjects\\CV-Generator\\data.xml") == null){
                response.sendRedirect("success.jsp"); //send "successful" response if there is everything correct
            }else{
                response.sendRedirect("index.html"); //send "origin" html page for name entry
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (XPathExpressionException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
