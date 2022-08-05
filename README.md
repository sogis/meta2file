# metabean2file

## Develop

### XSLT

Die XSL-Transformation kann ohne zusätzlichen Code in der Konsole getestet resp. entwickelt werden. Es muss lediglich Saxon-HE vorliegen (https://www.saxonica.com/html/download/java.html). Siehe _build.gradle_ betreffend der verwendeten Version.

```
java -jar /Users/stefan/apps/SaxonHE10-6J/saxon-he-10.6.jar -s:ch.so.agi.av_gb_administrative_einteilung.xml -xsl:xml2html.xsl -o:fubar.html
```

Die Quell-XML-Datei muss aber allenfalls aus einem Test (`runBean2Html`) hergestellt werden, da die XML-Datei nur ein internes Zwischenprodukt ist.

Einfacher, aber eventuell schwieriger zu debuggen:

```
./gradlew clean lib:test --tests ch.so.agi.metabean2file.MetaBean2FileConverterTest.bean2html_Ok
```