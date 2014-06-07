/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.server;


import cz.muni.fi.classes.PDFfromLatexBuilder;
import cz.muni.fi.classes.PersonalInfo;
import cz.muni.fi.classes.XMLRecordCreator;
import cz.muni.fi.classes.XSLTransformer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@WebServlet("/create-new-cv")
public class NewCVCreation extends HttpServlet {
    
   /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        PersonalInfo person = new PersonalInfo(request.getParameterMap());        
        XMLRecordCreator xmlrc = new XMLRecordCreator();        
        Path cp = Paths.get(request.getServletContext().getRealPath(""));
        String contextPath = cp.getParent().getParent().toString();
        
        if(xmlrc.generateXML(person,contextPath) == true){
            XSLTransformer xslt = new XSLTransformer();
            xslt.transformToTex("xml_to_tex.xslt", person.getDateHash()+".xml", 
                                person.getDateHash()+".tex",contextPath);
            File texFile = new File(contextPath+"/pdf_database",person.getDateHash());
            PDFfromLatexBuilder pflb = new PDFfromLatexBuilder("C:\\texlive\\2013\\bin\\win32\\");
            pflb.createPDF(texFile);
            File fOutToDelete = new File(texFile+".out");
            File fTexToDelete = new File(texFile+".tex");
            fOutToDelete.delete();
            fTexToDelete.delete();
            File invalidFile = new File(contextPath,"invalid.xml");
            if(invalidFile.exists()){
                invalidFile.delete();
            }
            response.sendRedirect(person.getDateHash()+".profile");
        }else{            
            response.sendRedirect("invalid_xml.jsp");            
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
