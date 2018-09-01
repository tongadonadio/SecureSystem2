package securesystem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CovertChannel {
    SecureSystem sys;
    static boolean vflag;
    static PrintWriter logWriter;
    static PrintWriter outWriter;
    static int bytes = 0;
    static long bits = 0;

    public CovertChannel(boolean flag, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        vflag = flag;
        this.sys = new SecureSystem();
        logWriter = new PrintWriter("log", "UTF-8");
        outWriter = new PrintWriter(fileName + ".out", "UTF-8");
    }

    public static void printChar(char x, boolean end) {
        bytes++;
        if (end) {
            outWriter.println();
        } else {
            outWriter.print(x);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        boolean check = false;
        String fileName = "";
        if (args[0].equals("v")) {
            check = true;
            fileName = args[1];
        } else {
            fileName = args[0];
        }

        CovertChannel chan = new CovertChannel(check, fileName);
        chan.sys.createSubject("Lyle", SecurityLevel.LOW);
        chan.sys.createSubject("Hal", SecurityLevel.HIGH);
        File test = new File(fileName);
        Scanner sc = new Scanner(test);
        long timeTaken = System.currentTimeMillis();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            for (int i = 0; i < line.length() + 1; i++) {
                String compute = "";
                if (i == line.length()) {
                    compute = "00001010";
                } else {
                    compute = Integer.toBinaryString((Integer) (int) line.charAt(i));
                }
                
                compute = addLeftZero(compute);

                for (int k = 0; k < compute.length(); k++) {
                    bits++;
                    if (compute.charAt(k) == '0') {
                        chan.sys.getReferenceMonitor().createNewObject("Obj",
                                SecurityLevel.HIGH);
                        chan.sys.getReferenceMonitor().createNewObject("Obj", SecurityLevel.LOW);
                        chan.sys.getReferenceMonitor().checkWrite("lyle", "Obj", 1);
                        chan.sys.updateSubject("lyle",
                                chan.sys.getReferenceMonitor().checkRead("lyle", "Obj"));
                        chan.sys.getReferenceMonitor().checkDestroy("Lyle", "Obj");

                        SecureSubject.run(chan.sys.subjects.get(1).getTemp());
                        if (vflag) {
                            logWriter.println("CREATE HAL OBJ");
                            logWriter.println("CREATE LYLE OBJ");
                            logWriter.println("WRITE LYLE OBJ 1");
                            logWriter.println("READ LYLE OBJ");
                            logWriter.println("DESTROY LYLE OBJ");
                            logWriter.println("RUN LYLE");
                        }

                    } else if (compute.charAt(k) == '1') {
                        chan.sys.getReferenceMonitor().createNewObject("Obj", SecurityLevel.LOW);
                        chan.sys.getReferenceMonitor().checkWrite("Lyle", "Obj", 1);
                        chan.sys.updateSubject("Lyle",
                                chan.sys.getReferenceMonitor().checkRead("Lyle", "Obj"));
                        chan.sys.getReferenceMonitor().checkDestroy("Lyle", "Obj");

                        SecureSubject.run(chan.sys.subjects.get(0).getTemp());
                        if (vflag) {
                            logWriter.println("CREATE LYLE OBJ");
                            logWriter.println("WRITE LYLE OBJ 1");
                            logWriter.println("READ LYLE OBJ");
                            logWriter.println("DESTROY LYLE OBJ");
                            logWriter.println("RUN LYLE");
                        }
                    }
                }
            }
        }
        sc.close();
        outWriter.close();
        logWriter.close();
        timeTaken = Math.abs(timeTaken - System.currentTimeMillis());
        System.out.println("Bits: " + bits + " Bytes: " + bytes + " Bits/Ms: " + bits / (timeTaken));
    }
    
    private static String addLeftZero(String compute){
        if (compute.length() < 8) {
            String add = "";
            int c = compute.length();
            while (c < 8) {
                add += "0";
                c++;
            }
            return add + compute;
        }
        return "";
    }
}
