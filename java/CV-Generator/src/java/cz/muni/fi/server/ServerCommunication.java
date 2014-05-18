/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.server;


import cz.muni.fi.classes.CVSchemaValidator;
import cz.muni.fi.classes.PersonalInfo;
import cz.muni.fi.classes.XMLRecordCreator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is used for loading information about a person, who want to create
 * a new curriculum vitae and who fills needed information in a html form, and 
 * creating needed xml document which is important for further process.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
@WebServlet("/create-new-profile")
public class ServerCommunication extends HttpServlet {
    
   /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PersonalInfo person = new PersonalInfo(request.getParameterMap());        
        XMLRecordCreator xmlrc = new XMLRecordCreator();
        CVSchemaValidator sv = new CVSchemaValidator("database.xsd");
        
        xmlrc.generateXML(person);
        try {
            if(sv.validate(person.getFirstname()+"_"+person.getLastname()+".xml")==null){
                System.out.println("Vse ok.");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
