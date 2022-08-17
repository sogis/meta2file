package ch.so.agi.meta2file;

public class Meta2FileException extends RuntimeException {

    public Meta2FileException(String msg) {
        super(msg);
    }

    public Meta2FileException(Exception inner) {
        super(inner);
    }
}
