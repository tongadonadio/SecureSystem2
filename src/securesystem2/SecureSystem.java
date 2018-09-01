package securesystem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class SecureSystem {
    ReferenceMonitor monitor;
    ArrayList<SecureSubject> subjects;

    public SecureSystem() throws FileNotFoundException, UnsupportedEncodingException {
        this.monitor = new ReferenceMonitor();
        this.subjects = new ArrayList<SecureSubject>();
    }

    public void createSubject(String name, SecurityLevel level) {
        this.subjects.add(new SecureSubject(name, 0));
        this.monitor.createSubject(name, level);
    }

    public void updateSubject(String name, int x) {
        for (SecureSubject check : subjects) {
            if (name.equalsIgnoreCase(check.name)) {
                check.setTemp(x);
            }
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public ReferenceMonitor getReferenceMonitor() {
        return this.monitor;
    }
    
    public int getSubjectTemp(String name) {
        for (SecureSubject check : this.subjects) {
            if (check.name.equalsIgnoreCase(name)) {
                return check.readTemp;
            }
        }
        return 0;
    }
}
