package securesystem2;

public class SecurityLevel {
    static final SecurityLevel LOW = new SecurityLevel(0);
    static final SecurityLevel HIGH = new SecurityLevel(1);
    int value;

    public SecurityLevel(int x) {
        this.value = x;
    }

    public boolean compareLess(SecurityLevel x) {
        if (this.value <= x.value) {
            return true;
        } else {
            return false;
        }
    }

    public boolean compareGreat(SecurityLevel x) {
        if (this.value >= x.value) {
            return true;
        } else {
            return false;
        }
    }
}
