package securesystem2;

public class SecureSubject {
    int readTemp;
    String name;
    static String build = "";

    SecureSubject(String name, int readTemp) {
        this.name = name;
        this.readTemp = readTemp;
    }

    public boolean equals(SecureSubject check) {
        if (this.name.equalsIgnoreCase(check.name) && this.readTemp == check.readTemp) {
            return true;
        }
        return false;
    }

    public void setTemp(int x) {
        this.readTemp = x;
    }

    public int getTemp() {
        return this.readTemp;
    }

    public static void run(int add) {
        build += String.valueOf(add);

        if (build.length() == 8) {
            int x = Integer.parseInt(build, 2);
            if (build.equals("00001010")) {
                CovertChannel.printChar((char) x, true);
                build = "";
            } else {
                CovertChannel.printChar((char) x, false);
                build = "";
            }
        }
    }
}
