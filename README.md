# meta2file

## Develop

### Packages and jars

Die Artefakte werden als executable fat-jar auf GitHub deployt (Modul _app_). Die Teilfunktionalität für das Erzeugen der HTML-Datenbeschreibung (Module _lib_) als normales jar auf dem Maven-Repo "Maven Central" (https://mvnrepository.com/artifact/io.github.sogis/meta2file/).

### Package-Struktur

Der im Ganzen Repo gleichbleibende Präfix **`ch.so.agi.meta2file_`** wird folgend als BASE abgekürzt.

* `lib`
  * `BASE.libmain`: Enthält das Java-Interface, über welches die "Lib-Nutzer" app (in diesem Repo) und SIMI die Funktionen anstossen.  
  * `BASE.in.json`: Liest die erforderlichen Metadaten einer Themenbereitstellung aus einem Json-Objektstring in die Java-Beans des model ein.  
    * `BASE.in.json.from_meta`:  Liest die Metadaten von 1-n Themenbereitstellungen aus SIMI ein und wandelt sie in den Json-Objektstring um.
  * `BASE.model`: Java-Beans Domain-Model der Metainformationen. Wird in allen packages verwendet.
  * `BASE.out.html`: Erzeugt aus den Beans die HTML-Datenbeschreibung. Hinweis: Eventuell folgt später out.pdf für die Erzeugung des pdf (Alternativ: out.text.html und out.text.pdf).   
* `app`
  * `BASE.appmain`: Eingangsklasse und CLI-parsing der Kommandozeilen-App.
  * `BASE.out.metaapp`: Generiert aus den Beans die XML-Konfiguration für die Applikation "Datensuche".
  * `BASE.out.geocat`: Generiert aus den Beans die ISO-CH XML-Dateien für geocat (Eine Datei pro Themen-Bereitstellung).

### XSLT

Die XSL-Transformation kann ohne zusätzlichen Code in der Konsole getestet resp. entwickelt werden. Es muss lediglich Saxon-HE vorliegen (https://www.saxonica.com/html/download/java.html). Siehe _build.gradle_ betreffend der verwendeten Version.

```
java -jar /Users/stefan/apps/SaxonHE10-6J/saxon-he-10.6.jar -s:ch.so.agi.av_gb_administrative_einteilung.xml -xsl:xml2html.xsl -o:fubar.html
```

Die Quell-XML-Datei muss aber allenfalls aus einem Test (`bean2html_Ok`) hergestellt werden und dabei in einen persistenten Ordner kopiert werden, da die XML-Datei nur ein internes Zwischenprodukt ist. 

Einfacher, aber eventuell schwieriger zu debuggen:

```
./gradlew clean lib:test --tests ch.so.agi.meta2file.MetaBean2FileConverterTest.bean2html_Ok
```

### XSL-FO (PDF)

Alpha stuff.

```
java -jar /Users/stefan/apps/SaxonHE10-6J/saxon-he-10.6.jar -s:ch.so.agi.av_gb_administrative_einteilung.xml -xsl:xml2pdf.xsl -o:ch.so.agi.av_gb_administrative_einteilung.fo

/Users/stefan/apps/fop-2.7/fop/fop -fo ch.so.agi.av_gb_administrative_einteilung.fo -pdf fubar.pdf -c fop.xconf
```
