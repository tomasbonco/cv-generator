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
        XMLRecordCreator xmlrc = new XMLRecordCreator();        
        Path cp = Paths.get(request.getServletContext().getRealPath(""));
        String contextPath = cp.getParent().getParent().toString();
        String servletPath = request.getServletPath();
        PersonalInfo person = null;
        int personObjectCreated  = 0;
        
        if(servletPath.endsWith("create-new-cv")){
            person = new PersonalInfo(request.getParameterMap());
            personObjectCreated = 1;
        }        
        if(servletPath.endsWith(".modified") && servletPath.length() == 22){
            File f = new File(contextPath, "modif.txt");
            if (f.exists() && f.isFile()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                if (br.readLine().equals("File was correctly modified.")) {
                    File oldXmlFile = new File(contextPath + "/database" + servletPath.substring(0, 13) + ".xml");
                    File oldPdfFile = new File(contextPath + "/pdf_database" + servletPath.substring(0, 13) + ".pdf");
                    if (oldXmlFile.exists()) {
                        oldXmlFile.delete();
                    }
                    if (oldPdfFile.exists()) {
                        oldPdfFile.delete();
                    }
                    if (personObjectCreated == 0) {
                        person = new PersonalInfo(request.getParameterMap());
                        personObjectCreated = 1;
                    }
                    br.close();
                    f.delete();
                } else {
                    response.sendRedirect("/CV-Generator" + servletPath.substring(0, 13) + ".profile");
                }
            } else {
                response.sendRedirect("/CV-Generator" + servletPath.substring(0, 13) + ".profile");
            }
        }
        
        if(personObjectCreated == 1){
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
