<%-- 
    Document   : invalid_xml
    Created on : 7.6.2014, 02:23:48
    Author     : Tomas Smid <smid.thomas at gmail.com>
--%>

<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.nio.file.Paths"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.nio.file.Path"%>
<%@page import="java.io.File"%>
<%@page import="cz.muni.fi.classes.PersonalInfo" %>
<%@page import="cz.muni.fi.classes.PersonalInfoBuilder" %>

<%  Path contextPath = Paths.get(request.getServletContext().getRealPath(""));
    File invalidXML = new File(contextPath.getParent().getParent().toString() + "/invalid.xml");

    PersonalInfoBuilder pib = new PersonalInfoBuilder();
    PersonalInfo person = pib.newPersonalInfo(invalidXML);%>


<!DOCTYPE html>
<html lang="en" ng-app>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,300,600,900&amp;subset=latin,latin-ext" type="text/css">
  </head>
  <body id="page-create">
    <form name="form" ng-controller="ctrl" action="create-new-cv" method="POST">
      <% File fin = new File(contextPath.getParent().getParent().toString(),"validation_error.txt");
         BufferedReader br = new BufferedReader(new FileReader(fin));%>
      <div class="container">
        <strong style="color: darkred"><% out.println(br.readLine()); %></strong>
        <lu>
        <% String line; 
           while((line = br.readLine()) != null){
                String[] splitLine = line.split("-", 2);             
        %>
          <li style="color: red">
             <strong><% out.println(splitLine[0]); %></strong>
             <% out.println(" - "+splitLine[1]); %>
          </li>
        </lu>
      </div>
      <% }
         br.close();
         fin.delete();
      %>
      <div class="container">
        <h1>Basic information</h1>
        <div class="row">
          <div class="col-xs-6">
            <div>
              <label class="block">Prefix title</label>
              <input type="text" name="pretitle" ng-model="pretitle" ng-pattern="/^[a-zA-Z\. ]*$/" placeholder="e.g. Ing." ng-init="pretitle='<%out.print(person.getPretitle());%>'">
              <div ng-hide="form.pretitle.$valid" class="error">Only letters, dots and spaces are allowed!</div>
            </div>
              <div>
              <label class="block">Name*</label>
              <input type="text" name="name" ng-model="name" ng-required="true" placeholder="e.g. John." ng-init="name='<%out.print(person.getFirstname());%>'">
              <div ng-hide="form.name.$valid" class="error">This is required!</div>
            </div>
          </div>
          <div class="col-xs-6">
            <div>
              <label class="block">Sufix Title</label>
              <input type="text" name="posttitle" ng-model="posttitle" ng-pattern="/^[a-zA-Z\. ]*$/" placeholder="e.g. CsC." ng-init="posttitle='<%out.print(person.getPosttitle());%>'">
              <div ng-hide="form.posttitle.$valid" class="error">Only letters, dots and spaces are allowed!</div>
            </div>
            <div>
              <label class="block">Surname*</label>
              <input type="text" name="surname" ng-model="surname" ng-required="true" placeholder="e.g. Smith" ng-init="surname='<%out.print(person.getLastname());%>'">
              <div ng-hide="form.surname.$valid" class="error">This is required!</div>
            </div>
          </div>
        </div>
        <div class="both"></div>
        <h1>Contact information</h1>
        <div class="row">
          <% int phonesNum = person.getPhones().size(); %>
          <div ng-init="phones = [ [], <% for(int i = 1; i < phonesNum; i++){out.print("[], ");} %> ]; 
              _phone = [<% for(int i = 0; i < phonesNum; i++){ out.print("'"+person.getPhones().get(i)+"',");}%>]" class="col-xs-6">
            <div>
              <label class="block">Phone number*</label>
              <input type="text" name="phone[0]" ng-model="_phone[0]" ng-pattern="/^[\+]{0,1}[0-9\(\) ]+$/" ng-required="true" placeholder="e.g. +420 123 456 789">
              <div ng-show="valid('phone[0]').invalid.required" class="error">This is required!</div>
              <div ng-show="valid('phone[0]').invalid.pattern" class="error">Only numbers, spaces, brackets and sign plus are allowed!</div>
              <div ng-repeat="phone in phones">
                <input type="text" name="phone[{{$index + 1}}]" ng-model="_phone[ $index + 1 ]" ng-pattern="/^[\+]{0,1}[0-9\(\) ]+$/" placeholder="e.g. +420 123 456 789">
                <div ng-hide="valid('phone[{{$index + 1}}]').valid" class="error">Only numbers, spaces, brackets and sign plus are allowed!</div>
              </div><a ng-click="phones.push([])" class="btn pull-right">Add phone number</a>
            </div>
            <div class="both"></div>
            <div>
              <label class="block">Street*</label>
              <input type="text" name="street" ng-model="street" ng-required="true" placeholder="e.g. Bakery Street 63" ng-init="street = '<% out.print(person.getStreet()); %>'">
              <div ng-hide="form.street.$valid" class="error">This is required!</div>
            </div>
            <div>
              <label class="block">City*</label>
              <input type="text" name="city" ng-model="city" ng-required="true" placeholder="e.g. Boston" ng-init="city = '<% out.print(person.getCity()); %>'">
              <div ng-hide="form.city.$valid" class="error">This is required!</div>
            </div>
            <div>
              <label class="block">Postal Code*</label>
              <input type="text" name="postal" ng-model="postal" ng-required="true" placeholder="e.g. 405 32" ng-init="postal = '<% out.print(person.getPostal()); %>'">
              <div ng-hide="form.postal.$valid" class="error">This is required!</div>
            </div>
          </div>
          <% int emailsNum = person.getEmails().size(); %>
          <div ng-init="emails = [ [], <% for(int i = 1; i < emailsNum; i++){out.print("[], ");} %> ]; 
              _email = [<% for(int i = 0; i < emailsNum; i++){ out.print("'"+person.getEmails().get(i)+"',");} %>]" class="col-xs-6">
            <div>
              <label class="block">Email*</label>
              <input type="email" name="email[0]" ng-model="_email[0]" ng-required="true" placeholder="e.g. your-email@gmail.com">
              <div ng-show="valid('email[0]').invalid.required" class="error">This is required!</div>
              <input type="email" name="email[{{ $index + 1}}]" ng_model="_email[ $index + 1]" ng-repeat="email in emails" placeholder="e.g. your-email@gmail.com"><a ng-click="emails.push([])" class="btn pull-right">Add email</a>
            </div>
          </div>
        </div>
        <div class="both"></div>
        <h1>Employment</h1>
        <% int empNum = person.getEmployments().size(); %>
        <div ng-init="companies = [ <%for(int i = 0; i < empNum+1; i++){out.print("[], ");}%> ];           
            _c_name = [<%for(int i = 0; i < empNum; i++){out.print("'"+person.getEmployments().get(i).get(0)+"', ");} %>]; 
            _c_position = [<%for(int i = 0; i < empNum; i++){out.print("'"+person.getEmployments().get(i).get(1)+"', ");} %>]; 
            _c_since = [<%for(int i = 0; i < empNum; i++){out.print("'"+person.getEmployments().get(i).get(2)+"', ");} %>]; 
            _c_to = [<%for(int i = 0; i < empNum; i++){out.print("'"+person.getEmployments().get(i).get(3)+"', ");} %>]" class="row">
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
        <div ng-repeat="company in companies" class="row">
          <div class="col-xs-4">
            <input type="text" name="c_name[]" ng-model="_c_name[ $index ]" placeholder="e.g. Nokia">
          </div>
          <div class="col-xs-4">
            <input type="text" name="c_position[]" ng-model="_c_position[ $index ]" placeholder="e.g. CEO">
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_since[{{$index}}]" ng-model="_c_since[ $index ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2010-10">
            <div ng-hide="valid('c_since[{{$index}}]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="c_to[{{$index}}]" ng-model="_c_to[ $index ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2012-07">
            <div ng-hide="valid('c_to[{{$index}}]').valid" class="error">In format YYYY-MM!</div>
          </div>
        </div><a ng-click="companies.push([])" class="btn pull-right">Add employment</a>
        <div class="both"></div>
        <h1>Education</h1>
        <% int eduNum = person.getEducation().size(); %>
        <div ng-init="studies = [ <%for(int i = 0; i < eduNum+1; i++){out.print("[], ");}%> ]; 
            _e_name = [<%for(int i = 0; i < eduNum; i++){out.print("'"+person.getEducation().get(i).get(0)+"', ");} %>]; 
            _e_position = [<%for(int i = 0; i < eduNum; i++){out.print("'"+person.getEducation().get(i).get(1)+"', ");} %>]; 
            _e_since = [<%for(int i = 0; i < eduNum; i++){out.print("'"+person.getEducation().get(i).get(2)+"', ");} %>]; 
            _e_to = [<%for(int i = 0; i < eduNum; i++){out.print("'"+person.getEducation().get(i).get(3)+"', ");} %>]" class="row">
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
        <div ng-repeat="study in studies" class="row">
          <div class="col-xs-4">
            <input type="text" name="e_name[]" ng-model="_e_name[ $index ]" placeholder="e.g. Masaryk University">
          </div>
          <div class="col-xs-4">
            <input type="text" name="e_position[]" ng-model="_e_position[ $index ]" placeholder="e.g. Informatics">
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_since[{{$index}}]" ng-model="_e_since[ $index ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2013-09">
            <div ng-hide="valid('e_since[{{$index}}]').valid" class="error">In format YYYY-MM!</div>
          </div>
          <div class="col-xs-2">
            <input type="text" name="e_to[{{$index}}]" ng-model="_e_to[ $index ]" ng-pattern="/^[0-9]{4}\-[0-9]{2}$/" placeholder="e.g. 2015-05">
            <div ng-hide="valid('e_to[{{$index}}]').valid" class="error">In format YYYY-MM!</div>
          </div>
        </div><a ng-click="studies.push([])" class="btn pull-right">Add education</a>
        <div class="both"></div>
        <div class="row">
          <div class="col-xs-6">
            <h1>Languages</h1>
            <% int langNum = person.getLanguages().size(); %>
            <div ng-init="languages = [ <% for(int i = 0; i <= langNum; i++){out.print("[], ");} %> ]; 
                _l_language = [<% for(String s: person.getLanguages().keySet()){out.print("'"+s+"', ");} %>]; 
                _l_level = [<% for(String s: person.getLanguages().keySet()){out.print("'"+person.getLanguages().get(s)+"', ");} %> 'basic']" class="row">
              <div class="col-xs-8">
                <label class="block">Language</label>
              </div>
              <div class="col-xs-4">
                <label class="block">Level</label>
              </div>
            </div>
            <div class="both"></div>
            <div ng-repeat="language in languages" class="row">
              <div class="col-xs-8">
                <input type="text" name="l_language[]" ng-model="_l_language[$index]" placeholder="e.g. English">
              </div>
              <div class="col-xs-4">
                <select name="l_level[]" ng-model="_l_level[ $index ]">
                  <option>basic</option>
                  <option>intermediate</option>
                  <option>advanced</option>
                  <option>native speaker</option>
                </select>
              </div>
            </div><a ng-click="languages.push([]); _l_level[ _l_level.length ] = 'basic'" class="btn pull-right">Add language</a>
          </div>
          <div class="col-xs-6">
            <h1>Certificates</h1>
            <% int certNum = person.getCertificates().size(); %>
            <div ng-init="certificates = [ <% for(int i = 0; i < certNum+1; i++){out.print("[], ");} %> ]; 
                _c_certificate = [<% for(int i = 0; i < certNum; i++){out.print("'"+person.getCertificates().get(i)+"', ");} %>]" class="row">
              <div class="col-xs-12">
                <label class="block">Certificate</label>
                <input type="text" name="certificate[]" ng-repeat="certificate in certificates" ng-model="_c_certificate[$index]" placeholder="e.g. Cisco CCNA">
              </div>
            </div><a ng-click="certificates.push([])" class="btn pull-right">Add certificate</a>
          </div>
        </div>
        <div class="both"></div>
        <div class="row">
          <% int skillsNum = person.getSkills().size(); %>
          <div ng-init="skills = [ <% for(int i = 0; i <= skillsNum; i++){out.print("[], ");} %> ]; 
              _s_skill = [<% for(String s: person.getSkills().keySet()){out.print("'"+s+"', ");} %>]; 
              _s_level = [<% for(String s: person.getSkills().keySet()){out.print("'"+person.getSkills().get(s)+"', ");} %> 'basic']" class="col-xs-6">
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
            <div ng-repeat="skill in skills" class="row">
              <div class="col-xs-8">
                <input type="text" name="s_skill[]" ng-model="_s_skill[ $index ]" placeholder="e.g. Microsoft Office">
              </div>
              <div class="col-xs-4">
                <select name="s_level[]" ng-model="_s_level[ $index ]">
                  <option>basic</option>
                  <option>intermediate</option>
                  <option>advanced</option>
                </select>
              </div>
            </div><a ng-click="skills.push([]); _s_level[ _s_level.length ] = 'basic'" class="btn pull-right">Add skill</a>
          </div>
          <% int hobNum = person.getHobbies().size(); %>  
          <div ng-init="hobbies = [ <% for(int i = 0; i < hobNum+1; i++){out.print("[], ");} %> ]; 
              _hobbies = [<% for(int i = 0; i < hobNum; i++){out.print("'"+person.getHobbies().get(i)+"', ");} %>]" class="col-xs-6">
            <h1>Hobby</h1>
            <div class="row">
              <div class="col-xs-12">
                <label class="block">Hobby</label>
                <input type="text" name="hobby[]" ng-repeat="hobby in hobbies" ng-model="_hobbies[ $index ]" placeholder="e.g. Swimming"><a ng-click="hobbies.push([])" class="btn pull-right">Add hobby</a>
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