/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java class thanks to it is hold all information about person
 * who creates new cv. Information is captured from html page (form).
 * 
 * @author Tomáš Šmíd <smid.thomas@gmail.com>
 */
public class PersonalInfo {
    
    private String pretitle = null;
    private String firstname = null;
    private String lastname = null;
    private String posttitle = null;
    private String street = null;
    private String city = null;
    private String postal = null;
    private List<String> phones = null;
    private List<String> emails = null;
    private Map<Integer, String[]> employments = null;
    private Map<Integer, String[]> education = null;
    private Map<String, String> languages = null;
    private List<String> certificates = null;
    private Map<String, String> skills = null;
    private List<String> hobbies = null;
    private String password = null;
            
    public PersonalInfo(){
        this(null);
    }
    
    public PersonalInfo(Map<String, String[]> htmlScheduleData){
        if(htmlScheduleData != null){
            this.pretitle = setStringValue(htmlScheduleData, "pretitle");
            this.firstname = setStringValue(htmlScheduleData, "name");
            this.lastname = setStringValue(htmlScheduleData, "surname");
            this.posttitle = setStringValue(htmlScheduleData, "posttitle");
            this.street = setStringValue(htmlScheduleData, "street");
            this.city = setStringValue(htmlScheduleData, "city");
            this.postal = setStringValue(htmlScheduleData, "postal");
            this.phones = setListValue(htmlScheduleData, "phone");
            this.emails = setListValue(htmlScheduleData, "email");
            this.certificates = setListValue(htmlScheduleData, "certificate");
            this.hobbies = setListValue(htmlScheduleData, "hobby");
            /*this.employments = setEmplEduValues(htmlScheduleData, "c_");
            this.education = setEmplEduValues(htmlScheduleData, "e_");*/
            this.languages = setLangOrSkillValue(htmlScheduleData, "l_language", "l_level");
            this.skills = setLangOrSkillValue(htmlScheduleData, "s_skill", "s_level");
            this.password = setStringValue(htmlScheduleData,"password");
        }
    }
    
    private String setStringValue(Map<String, String[]> htmlScheduleData, String key){
        if (htmlScheduleData.containsKey(key)) {
            return htmlScheduleData.get(key)[0];
        }
        return null;
    }
    
    private List<String> setListValue(Map<String, String[]> htmlScheduleData, String partOfKey){
        String[] values;
        List<String> dataList = new ArrayList<String>();
        
        for(String s: htmlScheduleData.keySet()){
            if(s.startsWith(partOfKey)){
                values = htmlScheduleData.get(s);
                dataList.addAll(Arrays.asList(values));
            }
        }        
        return dataList;
    }
    
    private Map<String, String> setLangOrSkillValue(Map<String, String[]> htmlScheduleData, 
                                                    String partOfKey, String partOfKey2){
        
        List<String> langs = new ArrayList<String>();
        List<String> levels = new ArrayList<String>();
        String[] val;
        Map<String, String> dataMap = new HashMap<String, String>();
        
        for(String s: htmlScheduleData.keySet()){
            if(s.startsWith(partOfKey)){
                val = htmlScheduleData.get(s);
                langs.addAll(Arrays.asList(val));
            }else{
                if(s.startsWith(partOfKey2)){
                    val = htmlScheduleData.get(s);
                    levels.addAll(Arrays.asList(val));
                }
            }
        }
        for(int i = 0; i < langs.size(); i++){
            dataMap.put(langs.get(i), levels.get(i));
        }
        return dataMap;
    }
    
    
    public String getPretitle(){
        return this.pretitle;
    }
    
    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getPosttitle() {
        return this.posttitle;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostal() {
        return this.postal;
    }

    public List<String> getPhones() {
        return this.phones;
    }

    public List<String> getEmails() {
        return this.emails;
    }

    public Map<Integer, String[]> getEmployments() {
        return employments;
    }

    public Map<Integer, String[]> getEducation() {
        return education;
    }

    public Map<String, String> getLanguages() {
        return this.languages;
    }

    public List<String> getCertificates() {
        return this.certificates;
    }

    public Map<String, String> getSkills() {
        return this.skills;
    }

    public List<String> getHobbies() {
        return this.hobbies;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPretitle(String pretitle) {
        this.pretitle = pretitle;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPosttitle(String posttitle) {
        this.posttitle = posttitle;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public void setEmployments(Map<Integer, String[]> employments) {
        this.employments = employments;
    }

    public void setEducation(Map<Integer, String[]> education) {
        this.education = education;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages =  languages;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
    
    
