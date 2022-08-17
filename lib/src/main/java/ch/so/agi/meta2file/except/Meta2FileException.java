package ch.so.agi.meta2file.except;

import java.text.MessageFormat;

public class Meta2FileException extends RuntimeException {

    public Meta2FileException(String msg) {
        super(msg);
    }

    public Meta2FileException(String msgTemplate, Object... msgArgs) {
        super(MessageFormat.format(msgTemplate, msgArgs));
    }

    public Meta2FileException(Exception inner) {
        super(inner);
    }
}
