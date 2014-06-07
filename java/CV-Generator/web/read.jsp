<%-- 
    Document   : read
    Created on : 5.6.2014, 13:36:45
    Author     : Tomas Smid <smid.thomas at gmail.com>
--%>

<%@page import="cz.muni.fi.classes.PersonalInfo"%>
<%@page import="cz.muni.fi.classes.PersonalInfoBuilder"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% Path contextPath = Paths.get(request.getServletContext().getRealPath(""));
   String name = request.getServletPath().toString().substring(0, 13);
   File xmlFile = new File(contextPath.getParent().getParent().toString()+"/database"+name+".xml");

   PersonalInfoBuilder pib = new PersonalInfoBuilder();
   PersonalInfo person = pib.newPersonalInfo(xmlFile);%>
   
<!DOCTYPE html>
<html lang="en" ng-app>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,300,600,900&amp;subset=latin,latin-ext" type="text/css">
  </head>
  <body id="page-read">
    <section id="welcome" class="full v-center">
      <div class="background"></div>
      <div class="wrapper">
        <div class="wrapper-rs">
          <div class="container-fluid">
            <div class="row">
              <div class="col-xs-12">
                <h1><% out.println(person.getPretitle()+" "+person.getFirstname()+" "+
                       person.getLastname()+", "+person.getPosttitle());%></h1>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="row">
              <div class="col-xs-3"><% for(int i = 0; i < person.getEmails().size(); i++){
                                        out.print(person.getEmails().get(i)); %><br><% } %></div>
              <div class="col-xs-3"><% for(int i = 0; i < person.getPhones().size(); i++){
                                        out.print(person.getPhones().get(i)); %><br><% } %></div>
              <div class="col-xs-6">
                <address><% out.print(person.getCity());%><br><%out.print(person.getStreet());%><br><%out.print(person.getPostal());%></address>
              </div>
            </div>
          </div>
          <div class="next-page"><i class="icon-chevron-down"></i></div>
          <div class="edit-cv">
            <div class="button"><i class="icon-pencil"></i></div>
            <form method="POST" action="<%out.print("/CV-Generator"+name+".editing");%>">
              <input type="password" name="password" placeholder="Password">
            </form>
          </div>            
            <%String pathToPdf = name.substring(1)+".pdf";%>
          <div class="download-pdf"><a href="<%out.print(pathToPdf);%>">
              <div class="button"><i class="icon-file-text"></i></div>
              <div class="text-overflow">
                <div class="text">Download PDF</div>
              </div></a></div>
        </div>
      </div>
    </section>
    <section id="employment" class="full text-content">
      <div class="wrapper-rs">
        <div class="container">
          <div class="row">
            <div class="col-xs-6">
              <h2>Employment</h2>
              <ul>
                <%for(Integer i: person.getEmployments().keySet()){%>
                <li>
                    <%String date;%>
                    <%if(person.getEmployments().get(i).get(3) == null || person.getEmployments().get(i).get(3).trim()==""){
                        date = " up to this day";
                    }else{
                        date = " to "+person.getEmployments().get(i).get(3);}%>
                    <div class="year"><%out.print(person.getEmployments().get(i).get(2)+date);%></div>
                  <div class="company"><%out.print(person.getEmployments().get(i).get(0));%></div>
                  <p class="position"><%out.print(person.getEmployments().get(i).get(1));%></p><% } %>
                </li>                
              </ul>
            </div>
            <div class="col-xs-6">
              <h2>Education</h2>
              <ul class="green">
                <%for(Integer i: person.getEducation().keySet()){%>
                <li>
                    <%String date;%>
                    <%if(person.getEducation().get(i).get(3) == null || person.getEducation().get(i).get(3).trim()==""){
                        date = " up to this day";
                    }else{
                        date = " to "+person.getEducation().get(i).get(3);}%>
                  <div class="year"><%out.print(person.getEducation().get(i).get(2)+date);%></div>
                  <div class="company"><%out.print(person.getEducation().get(i).get(0));%></div>
                  <p class="position"><%out.print(person.getEducation().get(i).get(1));%></p><% } %>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section id="languages" class="text-content">
      <div class="background"></div>
      <div class="wrapper-rs">
        <div class="container">
          <div class="row">
            <div class="col-xs-6">
              <h2>Languages</h2>
              <ul class="faces">
                <%for(String s: person.getLanguages().keySet()){%>
                <li class="<%out.print(person.getLanguages().get(s));%>"><%out.print(s);%></li><% } %>                
              </ul>
            </div>
            <div class="col-xs-6">
              <h2>Certifications</h2>
              <ul>
                <%for(int i = 0; i < person.getCertificates().size(); i++){%>
                <li><%out.print(person.getCertificates().get(i));%></li><% } %>
              </ul>
            </div>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </section>
    <section id="skills" class="text-content">
      <div class="background"></div>
      <div class="wrapper-rs">
        <div class="container">
          <div class="row">
            <div class="col-xs-6">
              <h2>Skills</h2>
              <ul class="faces">
                <%for(String s: person.getSkills().keySet()){%>
                <li class="<%out.print(person.getSkills().get(s));%>"><%out.print(s);%></li><% } %>
              </ul>
            </div>
            <div class="col-xs-6">
              <h2>Hobbies</h2>
              <ul>
                <%for(int i = 0; i < person.getHobbies().size(); i++){%>
                <li><%out.print(person.getHobbies().get(i));%></li><% } %>
              </ul>
            </div>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </section>
    <footer>&copy;2014 Authors. All Rights Reserved.<br><a href="https://github.com/tomasbonco/cv-generator">View on Github</a></footer>
    <script src="js/jquery.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>