CV generator
============

School project for PB138 (Masaryk University, Brno, Czech republic).

Status: **finished**

# Description
Our task is to create webpage that allows visitors to create their Curriculum Vitae (CV). Data should be processed using Java and stored in XML. User should be allowed to download PDF file of his/her CV and this file should be generated using LaTeX.

Original assignment (in Czech) as stated at [MUNI IS](http://is.muni.cz):

    Webová aplikace na generování životopisů - Tým B
    ------------------------------------------------
    
    Tento projekt má za cíl vytvořit webovou aplikaci v programovacím jazyce Java, která umožní vygenerovat životopis.
  
    Aplikace umožní následující:

    - Vytvořit si profil (ten bude dostupný pod náhodným odkazem)
    - V tomto profilu vyplnit zákadní údaje obecně používané v životopisech (prac. zkušenosti, vzdělání, kontaktní informace) a tyto budou uloženy v XML souborech či XML databázi.
    - Vygenerovat si životopis ve formátu PDF
    
    K vygenerování životopisu využijte Latex. Konkrétně použijte nějaký styl, který se vám líbí (můžete nalézt pomocí http://ctan.org/). Nejdříve zkonvertujte XML pomocí XSLT transformace a následně spusťte Latex k vygenerování PDF.

    Jako servletový kontejter použijte buďto Jetty nebo Tomcat.

    Projekt musí být jednotkově testován.
    
# Documentation
[Please visit our wiki for documentation.](https://github.com/tomasbonco/cv-generator/wiki)

# Team
* Tomáš Bončo (https://github.com/tomasbonco) - front-end (HTML, CSS, JavaScript, design), team leader
* Daniel Minárik (https://github.com/danielminarik) - XML database, Schema and Transformation
* Bc. Jan Polišenský (https://github.com/Tsacek) - JAVA (server)
* Tomáš Šmíd (https://github.com/TomasSmid) - JAVA (server)
