package ch.so.agi.metabean2file;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.text.StringEscapeUtils;
import org.codehaus.stax2.io.EscapingWriterFactory;

public class CustomXmlEscapingWriterFactory implements EscapingWriterFactory {

    @Override
    public Writer createEscapingWriterFor(Writer out, String enc) throws UnsupportedEncodingException {
        return new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {
                String val = "";
                for (int i = off; i < len; i++) {
                    val += cbuf[i];
                }
                String escapedStr = StringEscapeUtils.escapeXml10(val);
                out.write(escapedStr);
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                out.close();
            }
        };
    }

    @Override
    public Writer createEscapingWriterFor(OutputStream out, String enc) throws UnsupportedEncodingException {
        throw new IllegalArgumentException("not supported");
    }

}
