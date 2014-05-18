<?xml version="1.0" encoding="UTF-8"?>
<!--
 *  xml_to_tex.xslt - Transformation schema for one CV from database.xml
 *  Transforms XML document to TeX using "ecv" template from http://ctan.org
 *
 *  This file is a part of the CV generator - School project
 *  for PB138 (Masaryk University, Brno, Czech republic)
 *
 *  Copyright (C) 2014  Daniel Minarik <daniel.minarik@gmail.com>
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:output method="xml"
                encoding="UTF-8"
                indent="yes"
                omit-xml-declaration="yes"
    />

    <xsl:template match="cv">
        <!--Document beginning-->
        <xsl:text>
\documentclass[english]{ecv}
\ecvName{</xsl:text>
        <!--Document name-->
        <xsl:value-of select="lastname"/>
        <xsl:text>, </xsl:text>
        <xsl:value-of select="firstname"/>
        <xsl:text>}
\begin{document}
\begin{ecv}
\ecvEPR{Name}   {\textsc{</xsl:text>

        <!--Last name-->
        <xsl:value-of select="lastname"/>
        <xsl:text>}, </xsl:text>
        
        <!--First name-->
        <xsl:value-of select="firstname"/>
        <xsl:text>}</xsl:text>
        
        <!--Address-->
        <xsl:text>
\ecvEPR{Address}{</xsl:text>
        <xsl:value-of select="address/street"/>
        <xsl:text>\ecvNewLine</xsl:text>
        <xsl:value-of select="address/postal"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="address/city"/>
        <xsl:text>}</xsl:text>
        
        <!--Phones-->
        <xsl:text>
\ecvEPR{Phone} {</xsl:text>
        <xsl:for-each select="phones/phone">
            <xsl:value-of select="text()"/>
            <xsl:if test="position() != last()">
                <xsl:text>\ecvNewLine </xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:text>}</xsl:text>
        
        <!--Emails-->
        <xsl:text>
\ecvEPR{E-Mail} {</xsl:text>
        <xsl:for-each select="emails/email">
            <xsl:text>\ecvHyperEMail{</xsl:text>
            <xsl:value-of select="text()"/>
            <xsl:text>}</xsl:text>
            <xsl:if test="position() != last()">
                <xsl:text>\ecvNewLine </xsl:text>
            </xsl:if>
        </xsl:for-each>
        <xsl:text>}</xsl:text>

        <!--Employment-->
        <xsl:if test="employment[count(*) &gt; 0]">
            <xsl:text>
\ecvBSec{\hypertarget{hypertarget:\ecvProfession}{\ecvProfession}}</xsl:text>
            <xsl:for-each select="employment/emp">
                <xsl:text>
\ecvEFR{Period} {</xsl:text>
                <xsl:value-of select="@from"/>
                <xsl:text> -- </xsl:text>
                <xsl:value-of select="@to"/>
                <xsl:text>}
\ecvENR{Employer} {\ecvBold{</xsl:text>
                <xsl:value-of select="@name"/>
                <xsl:text>}}
\ecvENR{Position} {</xsl:text>
                <xsl:value-of select="@position"/>
                <xsl:text>}
\ecvENR{Description} {</xsl:text>
                <xsl:value-of select="text()"/>
                <xsl:text>}</xsl:text>
            </xsl:for-each>
        </xsl:if>

        <!--Education-->
        <xsl:if test="education[count(*) &gt; 0]">
            <xsl:text>
\ecvBSec{\hypertarget{hypertarget:\ecvEducation}{\ecvEducation}}</xsl:text>
            <xsl:for-each select="education/edu">
                <xsl:text>
\ecvEFR{Period} {</xsl:text>
                <xsl:value-of select="@from"/>
                <xsl:text> -- </xsl:text>
                <xsl:value-of select="@to"/>
                <xsl:text>}
\ecvENR{Institute} {\ecvBold{</xsl:text>
                <xsl:value-of select="@name"/>
                <xsl:text>}}</xsl:text>
                <xsl:if test="@field-of-study != ''">
                    <xsl:text>
\ecvENR{Field of study} {</xsl:text>
                    <xsl:value-of select="@field-of-study"/>
                    <xsl:text>}</xsl:text>
                </xsl:if>
                <xsl:text>
\ecvENR{Description} {</xsl:text>
                <xsl:value-of select="text()"/>
                <xsl:text>}</xsl:text>
            </xsl:for-each>
        </xsl:if>
        
        <!--Languages-->
        <xsl:if test="languages[count(*) &gt; 0]">
            <xsl:text>
\ecvBSec{\hypertarget{hypertarget:\ecvLanguages}{\ecvLanguages}}</xsl:text>
            <!--Native-->
            <xsl:if test="languages[count(lang[@level='native']) &gt; 0]">
                <xsl:text>
\ecvERSub{Native}{</xsl:text>                
                <xsl:for-each select="languages/lang[@level='native']">
                    <xsl:text>\ecvBold{</xsl:text>
                    <xsl:value-of select="@name"/>
                    <xsl:text>}</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
            <!--Advanced-->
            <xsl:if test="languages[count(lang[@level='advanced']) &gt; 0]">
                <xsl:text>
\ecvERSub{Advanced}{</xsl:text>                
                <xsl:for-each select="languages/lang[@level='advanced']">
                    <xsl:text>\ecvBold{</xsl:text>
                    <xsl:value-of select="@name"/>
                    <xsl:text>}</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
            <!--Intermediate-->
            <xsl:if test="languages[count(lang[@level='intermediate']) &gt; 0]">
                <xsl:text>
\ecvERSub{Intermediate}{</xsl:text>                
                <xsl:for-each select="languages/lang[@level='intermediate']">
                    <xsl:text>\ecvBold{</xsl:text>
                    <xsl:value-of select="@name"/>
                    <xsl:text>}</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
            <!--Basic-->
            <xsl:if test="languages[count(lang[@level='basic']) &gt; 0]">
                <xsl:text>
\ecvERSub{Basic}{</xsl:text>                
                <xsl:for-each select="languages/lang[@level='basic']">
                    <xsl:text>\ecvBold{</xsl:text>
                    <xsl:value-of select="@name"/>
                    <xsl:text>}</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine</xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
        </xsl:if>
        
        <!--Abilities-->
        <xsl:if test="skills[count(*) &gt; 0] or hobbies[count(*) &gt; 0]">
            <xsl:text>
\ecvBSec{\hypertarget{hypertarget:\ecvAbilities}{\ecvAbilities}}</xsl:text>
            <!--Skills-->
            <xsl:if test="skills[count(*) &gt; 0]">
                <xsl:text>
\ecvBEBSub{Skills} {</xsl:text>                
                <xsl:for-each select="skills/sk">
                    <xsl:value-of select="@name"/>
                    <xsl:text> -- </xsl:text>
                    <xsl:value-of select="@level"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
            <!--Hobbies-->
            <xsl:if test="hobbies[count(*) &gt; 0]">
                <xsl:text>
\ecvBEBSub{Hobbies} {</xsl:text>                
                <xsl:for-each select="hobbies/hob">
                    <xsl:value-of select="text()"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>\ecvNewLine </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text>}</xsl:text>
            </xsl:if>
        </xsl:if>
        
        <!--End of document-->
        <xsl:text>
\end{ecv}
\ecvSig{</xsl:text>
        <!--Signature-->
        <xsl:value-of select="firstname"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="lastname"/>
        <xsl:text>}{Brno}
\end{document}
\end</xsl:text>

    </xsl:template>

</xsl:stylesheet>
