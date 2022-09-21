# meta2file

## Konfigurieren und Starten

### Kommandozeilen-Tool

Dieses ist direkt via Releases im Repo als "Fat-Jar" verfügbar und umfasst alle notwendigen Drittbibliotheken. Das aktuellste jar ist via [latest Release](releases/latest) unter dem Namen meta2file-cli-\[Version\]-all.jar verfügbar.

### Lib

Die Teilfunktionalität für das Erzeugen der HTML-Datenbeschreibung (Module _lib_) ist als normales jar auf dem Maven-Repo "Maven Central" publiziert. (https://mvnrepository.com/artifact/io.github.sogis/meta2file/).

Methode für das Erzeugen der Datenbeschreibung: `Meta2Html.renderDataSheet(...)`

Abhängigkeiten der Lib siehe [build.gradle](lib/build.gradle) des lib Subprojektes.

## Develop

### Package-Struktur

Der im Ganzen Repo gleichbleibende Präfix **`ch.so.agi.meta2file_`** wird folgend als BASE abgekürzt.

* `lib`
  * `BASE.except`: Exception-Klassen. 
  * `BASE.libmain`: Enthält das Java-Interface, über welches die "Lib-Nutzer" app (in diesem Repo) und SIMI die Funktionen anstossen.  
  * `BASE.in`: Liest die erforderlichen Metadaten einer Themenbereitstellung aus DB oder Json in die Java-Beans des model ein.  
    * `BASE.in.db`:  Klassen für das Einlesen aus der Datenbank.
  * `BASE.model`: Java-Beans Domain-Model der Metainformationen. Wird in allen packages verwendet.
  * `BASE.out`: Klassen für das Generieren der HTML und XML outputs.   
* Zusätzliche Packages in `app`
  * `BASE.appmain`: Eingangsklasse und CLI-parsing der Kommandozeilen-App.
  * `BASE.model.geocat`: Viewmodel für das Generieren der ISO-CH XML-Dateien mittels [Apache Freemarker](https://freemarker.apache.org/).
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
