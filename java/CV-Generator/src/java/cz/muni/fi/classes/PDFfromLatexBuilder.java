/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used for creating PDF output from Latex file by using pdflatex. Pdflatex must be installed.
 * @author Jan Polišenský <polisensky88 at gmail.com>
 */
public class PDFfromLatexBuilder {
    
    String pathToLatexBin;
    
    /**
    * @param    latexPath   Path to latex binaries as latex and dvipdfm
    */
    public PDFfromLatexBuilder(String latexPath){
        pathToLatexBin = latexPath;
    }
    /**
    * @param    latexFile   File used only as a path container. Tex source file has .tex extension, but latexFile path must be without extension (eg. file instead of file.tex)
    * @return               absolute path to output PDF file. will be empty if creating fails.
    */
    public String createPDF(File latexFile){
        String result = "";
        //set path where the latex and other binaries are located //moved to constructor
        //String pathToPdflatex = "C:\\texlive\\2013\\bin\\win32\\";
        
        //set command for .tex to .dvi conversion
        List<String> dviParams = new ArrayList<String>();
        dviParams.add(pathToLatexBin + "latex");
        dviParams.add(latexFile.getName());
        
        //set command for .dvi to .pdf conversion
        List<String> pdfParams = new ArrayList<String>();
        pdfParams.add(pathToLatexBin + "dvipdfm");
        pdfParams.add(latexFile.getName());
        
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(latexFile.getParentFile());
        
        
        try {
            //start latex to generate .dvi file
            pb.command(dviParams);
            System.out.println("Starting:" + pb.command());
            pb.start().waitFor();            
            System.out.println("Output:" + latexFile.getAbsolutePath()+".dvi");
            
            //start dvipdfm to generate .pdf
            pb.command(pdfParams);
            System.out.println("Starting:" + pb.command());
            pb.start().waitFor();
            System.out.println("Output:" + latexFile.getAbsolutePath()+".pdf");
            
            //set path of resulting pdf
            result = latexFile.getAbsolutePath()+".pdf";
        } catch (IOException ex) {
            Logger.getLogger(PDFfromLatexBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Can not create PDF file from " + latexFile.getName() + ".tex due to error: " + ex.getMessage());
        }catch (InterruptedException ex) {
            Logger.getLogger(PDFfromLatexBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //cleanup
            //--------------------------------------

            //delete intermediate .dvi file
            File dvi = new File(latexFile.getAbsolutePath()+".dvi");
            dvi.delete();
            
            //delete intermediate .aux file
            File aux = new File(latexFile.getAbsolutePath()+".aux");
            aux.delete();
            
            //delete intermediate .log file
            File log = new File(latexFile.getAbsolutePath()+".log");
            log.delete();
        
        return result;
    };
    
    /**
     * Example of using this class
     */
    public static void example(){
        //File created only as path container. Path is same as tex source, but without extension
        File texFile = new File("C:\\Users\\Honza\\Disk Google\\Tex\\test");
        
        //PDFfromLatexBuilder constructor takes as argument path to latex binaries, because enviroment PATH is not yet working.
        PDFfromLatexBuilder pflb = new PDFfromLatexBuilder("C:\\texlive\\2013\\bin\\win32\\");
        //creates PDF file from .tex source in same directory
        pflb.createPDF(texFile);
    }
}
