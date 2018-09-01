package securesystem2;

public class SecureObject {
    String name;
    int value;

    public SecureObject(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public boolean equals(SecureObject check) {
        if (this.name.equalsIgnoreCase(check.name) && this.value == check.value) {
            return true;
        }
        return false;
    }

    public void updateObject(int x) {
        this.value = x;
    }
}
