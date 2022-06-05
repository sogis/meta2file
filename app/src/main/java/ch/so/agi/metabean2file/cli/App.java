package ch.so.agi.metabean2file.cli;

public class App {
    public static void main(String[] args) {        
        String dburl = null;
        String foo = null;
        
        int argi = 0;
        for(;argi<args.length;argi++) {
            String arg = args[argi];
            
            if(arg.equals("--dburl")) {
                argi++;
                dburl = args[argi];
            } else if (arg.equals("--foo")) {
                argi++;
                foo = args[argi];
            } else if (arg.equals("--help")) {
                System.err.println();
                System.err.println("--dburl     The database jdbc url (required).");
                System.err.println("--foo       Lorem ipsum.");
                System.err.println();
                return;
            }
        }
        
        if (dburl == null) {
            System.err.println("dburl is required.");
            System.exit(2);
        }
    }
}
