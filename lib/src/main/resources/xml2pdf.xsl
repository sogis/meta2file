<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  version="3.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:fox="http://xmlgraphics.apache.org/fop/extensions" xmlns:array="http://www.w3.org/2005/xpath-functions/array">
  <xsl:output method="xml" indent="yes"/>
  <xsl:decimal-format name="swiss" decimal-separator="." grouping-separator="'"/>
    <xsl:template match="/themePublication">
    <fo:root language="de" font-family="Frutiger" font-weight="400" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:fox="http://xmlgraphics.apache.org/fop/extensions" xmlns:xsd="https://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xml:lang="en">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="mainPage" page-height="297mm" page-width="210mm" margin-top="10mm" margin-bottom="12mm" margin-left="18mm" margin-right="18mm">
          <fo:region-body margin-top="30mm" margin-bottom="5mm" background-color="transparent"/>
          <fo:region-before extent="19mm" background-color="transparent"/>
          <fo:region-after extent="3mm" background-color="transparent"/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="mainPage" id="page-sequence-id">
        <xsl:call-template name="insertHeaderAndFooter"/>
        <fo:flow flow-name="xsl-region-body">
          <fo:block-container height="28mm" background-color="transparent">
            <fo:block  font-weight="400" font-size="12pt">

                Beschreibung:
                <!--<xsl:value-of disable-output-escaping="yes" select="shortDescription"/>-->

                <xsl:apply-templates select="shortDescription"/> 


            </fo:block>
          </fo:block-container>
        </fo:flow>
      </fo:page-sequence>
    </fo:root>
  </xsl:template>

  <xsl:template match="shortDescription">
    <!--Dummy Element hinzufügen, damit der Text wohlgeformt ist.-->
    <xsl:variable name="shortDescriptionText">&lt;span&gt;<xsl:value-of select="."/>&lt;/span&gt;</xsl:variable> 
    <!--<xsl:message><xsl:value-of disable-output-escaping="yes" select="$shortDescriptionText"/></xsl:message>-->
  <!--<xsl:value-of disable-output-escaping="yes" select="."/>-->
          <!--<xsl:element name="{local-name()}">-->
            <xsl:apply-templates select="parse-xml($shortDescriptionText)/node()"/>
        <!--</xsl:element>-->

    <!--<xsl:apply-templates/>-->

<fo:block text-align="justify" font-size="12pt">
	<fo:list-block>
		<fo:list-item>
			<fo:list-item-label >
				<fo:block>
          &#x02022;
        </fo:block>
			</fo:list-item-label>
			<fo:list-item-body start-indent="5mm">
				<fo:block>
          Bodytext Bodytext Bodytext Bodytext Bodytext Bodytext Bodytext BodytextBodytext Bodytext Bodytext Bodytext BodytextBodytext Bodytext Bodytext Bodytext BodytextBodytext Bodytext Bodytext Bodytext Bodytext
        </fo:block>
			</fo:list-item-body>
		</fo:list-item>
	</fo:list-block>
</fo:block>



  </xsl:template>

  <xsl:template match="b">
    <fo:inline font-weight="700">
    <xsl:apply-templates/>
    </fo:inline>
  </xsl:template>

  <xsl:template match="a">
        <fo:basic-link text-decoration="none" color="rgb(198,40,40)"><xsl:attribute name="external-destination"><xsl:value-of select="@href"/></xsl:attribute><xsl:value-of select="."/></fo:basic-link>
  </xsl:template>

  <xsl:template match="br">
          <xsl:value-of select="'&#x2028;'"/>
  </xsl:template>

  <xsl:template match="ul">
<fo:block margin="0mm" padding="0mm" padding-after="0mm" text-align="justify" font-size="12pt" background-color="green">
<fo:block margin="0mm" padding="0mm" background-color="yellow" linefeed-treatment="ignore">
Hallo Welt.
</fo:block>
    <fo:list-block margin="0mm" padding="0mm" background-color="blue">
      <xsl:for-each select="li">
        <fo:list-item>
          <fo:list-item-label >
            <fo:block>&#x02022;</fo:block>
          </fo:list-item-label>
          <fo:list-item-body start-indent="5mm">
            <fo:block><xsl:value-of select="."/></fo:block>
          </fo:list-item-body>
        </fo:list-item>
      </xsl:for-each>
    </fo:list-block>

</fo:block>




    <fo:list-block padding="4pt" margin-left="10mm" margin-top="4mm">

                <fo:list-item margin-left="13mm"  margin-top="8mm" margin-right="5mm" font-size="15pt">
                    <fo:list-item-label >
                        <fo:block>&#x02022;</fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body >
                        <fo:block margin-left="10mm">
                            Hallo Stefan
                        </fo:block>
                    </fo:list-item-body>

                </fo:list-item>

    </fo:list-block>


 
  </xsl:template>



  <xsl:template name="insertHeaderAndFooter">
    <fo:static-content flow-name="xsl-region-before">
      <fo:block>
        <fo:block-container absolute-position="absolute" top="0mm" left="0mm" background-color="wheat">
            <fo:block>
              Header
            </fo:block>
        </fo:block-container>
      </fo:block>
    </fo:static-content>
  </xsl:template>

</xsl:stylesheet>
