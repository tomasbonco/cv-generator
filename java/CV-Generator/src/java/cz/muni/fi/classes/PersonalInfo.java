

package cz.muni.fi.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java class for keeping all information (captured from html page) about a person 
 * who wants to create his own curriculum vitae.
 * 
 * @author Tomas Smid <smid.thomas at gmail.com>
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
    private Map<Integer, List<String>> employments = null;
    private Map<Integer, List<String>> education = null;
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
            this.employments = setEmplEduValues(htmlScheduleData, "c_");
            this.education = setEmplEduValues(htmlScheduleData, "e_");
            this.languages = setLangOrSkillValue(htmlScheduleData, "l_language", "l_level");
            this.skills = setLangOrSkillValue(htmlScheduleData, "s_skill", "s_level");
            this.password = setStringValue(htmlScheduleData,"password");
        }
    }
    
    /**
     * This method is used for correct setting of any string values
     * (e.g. firstname, lastname, street).
     * 
     * @param htmlScheduleData  input map with all information captured from
     *                          html page from which are gained particular data
     *                          for each particular string variable
     * @param key   says which value will be tried to gain from
     *              input data which are in htmlScheduleData
     * @return  requested string if present in htmlScheduleData,
     *          otherwise null
     */
    private String setStringValue(Map<String, String[]> htmlScheduleData, String key){
        if (htmlScheduleData.containsKey(key)) {
            return htmlScheduleData.get(key)[0];
        }
        return null;
    }
    
    /**
     * This method is used for correct setting of any list which is formed by
     * string values. So it is used for values which can repeatedly appear on
     * html page - cv form (e.g. phones, e-mails).
     * 
     * @param htmlScheduleData  input map with all information captured from
     *                          html page from which are gained particular data
     *                          for each particular string variable
     * @param partOfKey part of value (key) which is supposed to gain from
     *                  htmlScheduleData, this value is completed to the right form
     *                  and then searched in htmlScheduleData
     * @return  list of requested particular string values if present in htmlScheduleData
     */
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
    
    /**
     * This method is used for correct setting of languages and skills of person
     * who the information is connected to.
     * 
     * @param htmlScheduleData  input map with all information captured from
     *                          html page from which are gained particular data
     *                          for each particular string variable
     * @param partOfKey part of key word which is related to languages and which
     *                  will be completed to the right form a then searched in
     *                  htmlScheduleData
     * @param partOfKey2    part of key word which is related to levels of language
     *                      skill or another skill
     * @return  map of languages or skills together with level of language skill if present
     *          in htmlScheduleData
     */
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
    
    /**
     * This method is used for correct setting of employment and education
     * values.
     * 
     * @param htmlScheduleData  input map with all information captured from
     *                          html page from which are gained particular data
     *                          for each particular string variable
     * @param partOfKey part of key word which will be completed to the right form
     *                  and searched in htmlScheduleData
     * @return  map of employment or education information
     */
    private Map<Integer, List<String>> setEmplEduValues (Map<String, String[]> htmlScheduleData, String partOfKey){
        
        String[] keys = {(partOfKey+"name"),(partOfKey+"position"),
                           (partOfKey+"since"),(partOfKey+"to")};
        Map<Integer, List<String>> dataMap = new HashMap<Integer, List<String>>();
        List<String> names = getEmplEduPosOrNames(htmlScheduleData,keys[0]);
        List<String> positions = getEmplEduPosOrNames(htmlScheduleData,keys[1]);
        List<String> since = getEmplEduSinceOrTo(htmlScheduleData, keys[2]);
        List<String> to = getEmplEduSinceOrTo(htmlScheduleData, keys[3]);
        
        for(int i = 0; i < names.size(); i++){
            List<String> temp = new ArrayList<String>();
            temp.add(names.get(i));
            temp.add(positions.get(i));
            temp.add(since.get(i));
            temp.add(to.get(i));
            dataMap.put(i,temp);
        }
        return dataMap;
        
    }
    
    private List<String> getEmplEduPosOrNames(Map<String, String[]> htmlScheduleData,
                                              String partOfKey){
        
        List<String> retVal = new ArrayList<String>();
        String val1 = partOfKey+"[0]";
        String anotherValues = partOfKey+"[]";
        
        if(htmlScheduleData.containsKey(val1)){
            retVal.addAll(Arrays.asList(htmlScheduleData.get(val1)));
        }
        if(htmlScheduleData.containsKey(anotherValues)){
            retVal.addAll(Arrays.asList(htmlScheduleData.get(anotherValues)));
        }
        return retVal;
    }
    
    private List<String> getEmplEduSinceOrTo(Map<String, String[]> htmlScheduleData,
                                             String partOfKey){
        
        List<String> retVal = new ArrayList<String>();
        int con = 0;
        int k = 0;
        String key;
        
        while(con == 0){
            con = 1;
            key = partOfKey+"["+k+"]";
            if(htmlScheduleData.containsKey(key)){
                retVal.addAll(Arrays.asList(htmlScheduleData.get(key)));
                con = 0;
                k++;
            }
        }
        return retVal;
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

    public Map<Integer, List<String>> getEmployments() {
        return employments;
    }

    public Map<Integer, List<String>> getEducation() {
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

    public void setEmployments(Map<Integer, List<String>> employments) {
        this.employments = employments;
    }

    public void setEducation(Map<Integer, List<String>> education) {
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
    
    
