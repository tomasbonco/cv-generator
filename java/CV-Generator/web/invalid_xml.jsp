<%-- 
    Document   : notvalid
    Created on : 4.6.2014, 18:36:57
    Author     : Tomas Smid <smid.thomas at gmail.com>
--%>

<%@page import="java.nio.file.Paths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.nio.file.Path"%>
<%@page import="java.io.File"%>
<%@page import="cz.muni.fi.classes.PersonalInfo" %>
<%@page import="cz.muni.fi.classes.PersonalInfoBuilder" %>
<%@page import="java.util.Map.Entry" %>

<% String cvKey = request.getParameter("cvKey"); %>
<%-- load person from XML defined by cvKey --%>
<%  Path contextPath = Paths.get(request.getServletContext().getRealPath(""));
    File inXML = new File(contextPath.getParent().getParent().toString() + "/invalid.xml");

    PersonalInfoBuilder pib = new PersonalInfoBuilder();
    PersonalInfo pi = pib.newPersonalInfo(inXML);%>


<!DOCTYPE html>
<html lang="en" ng-app>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,300,600,900&amp;subset=latin,latin-ext" type="text/css">
  </head>
  <body id="page-create">
    <form name="form" ng-controller="ctrl" action="" method="POST">
      <div class="container">
        <h1>Basic information</h1>
        <div class="row">
          <div class="col-xs-6">
            <div>
              <label class="block">Prefix title</label>
              <input type="text" name="pretitle" ng-model="pretitle" ng-pattern="/^[a-zA-Z\. ]*$/" placeholder="e.g. Ing." value="<%= pi.getPretitle()%>">
              <div ng-hide="form.pretitle.$valid" class="error">Only letters, dots and spaces are allowed!</div>
            </div>
            <div>
              <label class="block">Name*</label>
              <input type="text" name="name" ng-model="name" ng-required="true" placeholder="e.g. John." value="<%= pi.getFirstname()%>">
              <div ng-hide="form.name.$valid" class="error">This is required!</div>
            </div>
          </div>
          <div class="col-xs-6">
            <div>
              <label class="block">Sufix Title</label>
              <input type="text" name="posttitle" ng-model="posttitle" ng-pattern="/^[a-zA-Z\. ]*$/" placeholder="e.g. CsC." value="<%= pi.getPosttitle()%>">
              <div ng-hide="form.posttitle.$valid" class="error">Only letters, dots and spaces are allowed!</div>
            </div>
            <div>
              <label class="block">Surname*</label>
              <input type="text" name="surname" ng-model="surname" ng-required="true" placeholder="e.g. Smith" value="<%= pi.getLastname()%>">
              <div ng-hide="form.surname.$valid" class="error">This is required!</div>
            </div>
          </div>
        </div>
        <div class="both"></div>
        <h1>Contact information</h1>
        <div class="row">
          <div ng-init="phones = []; _phone = []" class="col-xs-6">
            <div>
              <label class="block">Phone number*</label>
              <input type="text" name="phone[0]" ng-model="_phone[0]" ng-pattern="/^[\+]{0,1}[0-9\(\) ]+$/" ng-required="true" placeholder="e.g. +420 123 456 789" value="<%= pi.getPhones().get(0) %>">
              <div ng-show="valid('phone[0]').invalid.required" class="error">This is required!</div>
              <div ng-show="valid('phone[0]').invalid.pattern" class="error">Only numbers, spaces, brackets and sign plus are allowed!</div>
              <% int numPhones = 0; if(pi.getPhones() != null ) numPhones = pi.getPhones().size(); %>
              <% for( int i = 1; i< numPhones; i++) {%>
              <div ng-repeat="phone in phones">
                <input type="text" name="phone[<%= i %>]" ng-model="_phone[$index+1]" ng-pattern="/^[\+]{0,1}[0-9\(\) ]+$/" placeholder="e.g. +420 123 456 789" value="<%= pi.getPhones().get(i) %>">
                <div ng-hide="valid('phone[<%= i %>]').valid" class="error">Only numbers, spaces, brackets and sign plus are allowed!</div>
              </div>
              <% } %>
              <a ng-click="phones.push([])" class="btn pull-right">Add phone number</a>
            </div>
            <div class="both"></div>
            <div>
              <label class="block">Street*</label>
              <input type="text" name="street" ng-model="street" ng-required="true" placeholder="e.g. Bakery Street 63" value="<%= pi.getStreet()%>">
              <div ng-hide="form.street.$valid" class="error">This is required!</div>
            </div>
            <div>
              <label class="block">City*</label>
              <input type="text" name="city" ng-model="city" ng-required="true" placeholder="e.g. Boston" value="<%= pi.getCity()%>">
              <div ng-hide="form.city.$valid" class="error">This is required!</div>
            </div>
            <div>
              <label class="block">Postal Code*</label>
              <input type="text" name="postal" ng-model="postal" ng-required="true" placeholder="e.g. 405 32" value="<%= pi.getPostal()%>">
              <div ng-hide="form.postal.$valid" class="error">This is required!</div>
            </div>
          </div>
          <div ng-init="emails = []" class="col-xs-6">
            <div>
              <label class="block">Email*</label>
              <input type="email" name="email[0]" ng-model="_email" ng-required="true" placeholder="e.g. your-email@gmail.com" value="<%= pi.getEmails().get(0)%>">
              <div ng-show="valid('email[0]').invalid.required" class="error">This is required!</div>
              <% int numEmails = 0; if(pi.getEmails() != null ) numEmails = pi.getEmails().size(); %>
              <% for( int i = 1; i< numEmails; i++) { %>
              <input type="email" name="email[<%= i %>]" ng-repeat="email in emails" placeholder="e.g. your-email@gmail.com" value="<%= pi.getEmails().get(i)%>">
              <div ng-show="valid('email[<%= i %>]').invalid.required" class="error">This is required!</div>
              <% } %>
              <a ng-click="emails.push([])" class="btn pull-right">Add email</a>
            </div>
          </div>
        </div>
        <div class="both"></div>
        <h1>Employment</h1>
        <div class="row">
          <div class="col-xs-4">
            <label class="block">Company</label>
          </div>
          <div class="col-xs-4">
            <label class="block">Position</label>
          </div>
          <div class="col-xs-2">
            <label class="block">Since</label>
          </div>
          <div class="col-xs-2">
            <label class="block">To</label>
          </div>
        </div>
        <div class="both"></div>
        <% int numEmployments = 0; if(pi.getEmployments() != null ) numEmployments = pi.getEmployments().size(); %>
        <div ng-init="companies = []; _c_since = []; _c_to = []" class="row">
          <div class="col-xs-4">
            <input type="text" name="c_name[0]" placeholder="e.g. Nokia" <% if(numEmployments>0) out.println("value='"+ pi.getEmployments().get(0).get(0) + "'");%>>
          </div>
          <div class="col-xs-4">
            <input type="text" name="c_position[0]" placeholder="e.g. CEO" <% if(numEmployments>0) out.println("value='"+ pi.getEmployments().get(0).get(1) + "'");%>>
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_since[0]" ng-model="_c_since[0]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2010-10" <% if(numEmployments>0) out.println("value='"+ pi.getEmployments().get(0).get(2) + "'");%>>
            <div ng-hide="valid('c_since[0]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_to[0]" ng-model="_c_to[0]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2012-07" <% if(numEmployments>0) out.println("value='"+ pi.getEmployments().get(0).get(3) + "'");%>>
            <div ng-hide="valid('c_to[0]').valid" class="error">In format YYYY-MM!</div>
          </div>
        </div>
        <% for( int i = 1; i< numEmployments; i++) { %>
        <div ng-repeat="company in companies" class="row">
          <div class="col-xs-4">
            <input type="text" name="c_name[]" placeholder="e.g. Nokia" value="<%= pi.getEmployments().get(i).get(0) %>">
          </div>
          <div class="col-xs-4">
            <input type="text" name="c_position[]" placeholder="e.g. CEO" value="<%= pi.getEmployments().get(i).get(1) %>">
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_since[<%= i %>]" ng-model="_c_since[ $index + 1 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2010-10" value="<%= pi.getEmployments().get(i).get(2) %>">
            <div ng-hide="valid('c_since[<%= i %>]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_to[<%= i %>]" ng-model="_c_to[ $index + 1 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2012-07" value="<%= pi.getEmployments().get(i).get(3) %>">
            <div ng-hide="valid('c_to[<%= i %>]').valid" class="error">In format YYYY-MM!</div>
          </div>
        <% } %>
        </div><a ng-click="companies.push([])" class="btn pull-right">Add employment</a>
        <div class="both"></div>  
        <h1>Education</h1>
        <div class="row">
          <div class="col-xs-4">
            <label class="block">Name</label>
          </div>
          <div class="col-xs-4">
            <label class="block">Field of study</label>
          </div>
          <div class="col-xs-2">
            <label class="block">Since</label>
          </div>
          <div class="col-xs-2">
            <label class="block">To</label>
          </div>
        </div>
        <div class="both"></div>
        <% int numEdu = 0; if(pi.getEducation() != null ) numEdu = pi.getEducation().size(); %>
        <div ng-init="studies = []; _e_since = []; _e_to = []" class="row">
          <div class="col-xs-4">
            <input type="text" name="e_name[]" placeholder="e.g. Masaryk University" <% if(numEdu>0) out.println("value='"+ pi.getEducation().get(0).get(0) + "'");%>>
          </div>
          <div class="col-xs-4">
            <input type="text" name="e_position[]" placeholder="e.g. Informatics" <% if(numEdu>0) out.println("value='"+ pi.getEducation().get(0).get(1) + "'");%>>
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_since[0]" ng-model="_e_since[ 0 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2013-09" <% if(numEdu>0) out.println("value='"+ pi.getEducation().get(0).get(2) + "'");%>>
            <div ng-hide="valid('e_since[0]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_to[0]" ng-model="_e_to[ 0 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2015-05" <% if(numEdu>0) out.println("value='"+ pi.getEducation().get(0).get(3) + "'");%>>
            <div ng-hide="valid('e_to[0]').valid" class="error">In format YYYY-MM!</div>
          </div>
        </div>
        <% for( int i = 1; i< numEdu; i++) { %>   
        <div ng-repeat="study in studies" class="row">
          <div class="col-xs-4">
            <input type="text" name="e_name[]" placeholder="e.g. Masaryk University" value="<%= pi.getEducation().get(i).get(0) %>">
          </div>
          <div class="col-xs-4">
            <input type="text" name="e_position[]" placeholder="e.g. Informatics" value="<%= pi.getEducation().get(i).get(1) %>">
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_since[<%= i %>]" ng-model="_e_since[ $index + 1 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2013-09" value="<%= pi.getEducation().get(i).get(2) %>">
            <div ng-hide="valid('e_since[<%= i %>]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_to[<%= i %>]" ng-model="_e_to[ $index + 1 ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2015-05" value="<%= pi.getEducation().get(i).get(3) %>">
            <div ng-hide="valid('e_to[<%= i %>]').valid" class="error">In format YYYY-MM!</div>
          </div>
        <% } %>
        </div><a ng-click="studies.push([])" class="btn pull-right">Add education</a>
        <div class="both"></div>
        <div class="row">
          <div class="col-xs-6">
            <h1>Languages</h1>
            <div ng-init="languages = [ [] ]" class="row">
              <div class="col-xs-8">
                <label class="block">Language</label>
              </div>
              <div class="col-xs-4">
                <label class="block">Level</label>
              </div>
            </div>
            <div class="both"></div>
            <% for( Entry<String, String> entry : pi.getLanguages().entrySet()) { %>   
            <div ng-repeat="language in languages" class="row">
              <div class="col-xs-8">
                  <input type="text" name="l_language[]" placeholder="e.g. English" value="<%= entry.getKey() %>">
              </div>
              <div class="col-xs-4">
                <select name="l_level[]" value="<%= entry.getValue() %>">
                  <option>basic</option>
                  <option>intermediate</option>
                  <option>advanced</option>
                  <option>native speaker</option>
                </select>
              </div>
            </div>      
            <% } %>
            <a ng-click="languages.push([])" class="btn pull-right">Add language</a>
          </div>
          <div class="col-xs-6">
            <h1>Certificates</h1>
            <div ng-init="certificates = [ [] ]" class="row">
              <div class="col-xs-12">
                <label class="block">Certificate</label>
                <% for(String certificate : pi.getCertificates()) { %>
                <input type="text" name="certificate[]" ng-repeat="certificate in certificates" placeholder="e.g. Cisco CCNA" value="<%= certificate %>">
                <% } %>
              </div>
            </div><a ng-click="certificates.push([])" class="btn pull-right">Add certificate</a>
          </div>
        </div>
        <div class="both"></div>
        <div class="row">
          <div ng-init="skills = [ [] ]" class="col-xs-6">
            <h1>Skills</h1>
            <div class="row">
              <div class="col-xs-8">
                <label class="block">Skill</label>
              </div>
              <div class="col-xs-4">
                <label class="block">Level</label>
              </div>
            </div>
            <div class="both"></div>
            <% for( Entry<String, String> skill : pi.getSkills().entrySet()) { %>
            <div ng-repeat="skill in skills" class="row">
              <div class="col-xs-8">
                  <input type="text" name="s_skill[]" placeholder="e.g. Microsoft Office" value="<%= skill.getKey() %>">
              </div>
              <div class="col-xs-4">
                <select name="s_level[]" value="<%= skill.getValue() %>">
                  <option>basic</option>
                  <option>intermediate</option>
                  <option>advanced</option>
                </select>
              </div>
            </div>      
            <% } %>
            <a ng-click="skills.push([])" class="btn pull-right">Add skill</a>
          </div>
          <div ng-init="hobbies = [ [] ]" class="col-xs-6">
            <h1>Hobby</h1>
            <div class="row">
              <div class="col-xs-12">
                <label class="block">Hobby</label>
                <% for( String hobby : pi.getHobbies()) { %>
                <input type="text" name="hobby[]" ng-repeat="hobby in hobbies" placeholder="e.g. Swimming" value="<%= hobby %>">
                <% } %>
                <a ng-click="hobbies.push([])" class="btn pull-right">Add hobby</a>
              </div>
            </div>
          </div>
        </div>
        <div class="both"></div>
        <div class="delimiter">
          <label class="block">Password</label>
          <div class="row">
            <div class="col-xs-6">
              <input type="text" name="password" ng-model="password" ng-required="true">
            </div>
            <div class="col-xs-6"><em class="info block">Set your password, so you will be able to update your CV later.</em></div>
          </div>
          <div class="both"></div>
        </div>
        <div ng-show=" ! form.$valid" class="btn disabled block text-center">Formular is not valid. Please fill it correctly.</div>
        <input type="submit" ng-show="form.$valid" class="btn">
      </div>
    </form>
    <script src="js/angular.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/script.js"></script>
  </body>
</html>