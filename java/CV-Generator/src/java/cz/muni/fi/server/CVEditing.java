/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.server;

import cz.muni.fi.classes.PersonalInfo;
import cz.muni.fi.classes.PersonalInfoBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet sends redirecting to required destination according to
 * results of comparison of password hashes. If the comparison is true (the hashes
 * are same) then user can edit his own CV information. Otherwise will be redirected
 * to the page of his own information.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
 */
public class CVEditing extends HttpServlet {

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
        
        Path contextPath = Paths.get(request.getServletContext().getRealPath(""));
        String name = request.getServletPath().toString().substring(0, 13);
        File xmlFile = new File(contextPath.getParent().getParent().toString()+"/database"+name+".xml");
        PersonalInfoBuilder pib = new PersonalInfoBuilder();
        PersonalInfo person = pib.newPersonalInfo(xmlFile);
        PersonalInfo p = new PersonalInfo();
        
        p.setPasswordHash1(request.getParameter("password"));
        if(p.getPasswordHash() != null && person.getPasswordHash().equals(p.getPasswordHash())){
            BufferedWriter br = new BufferedWriter(new FileWriter(
                    new File(contextPath.getParent().getParent().toString(),"correctPassword.txt")));
            br.write("Profile can be edited by this user/program.");
            br.close();
            response.sendRedirect("/CV-Generator"+name+".editing");
        }else{
            response.sendRedirect("/CV-Generator"+name+".profile");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
