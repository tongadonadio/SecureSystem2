package securesystem2;

import java.util.HashMap;
import java.util.LinkedList;

public class ReferenceMonitor {

    ObjectManager manage = new ObjectManager();
    HashMap<String, SecurityLevel> objects = new HashMap<String, SecurityLevel>();
    HashMap<String, SecurityLevel> subjects = new HashMap<String, SecurityLevel>();

    public void createNewObject(String name, SecurityLevel level) {
        this.objects.put(name.toLowerCase(), level);
        this.manage.createObject(name, 0);
    }

    public void createSubject(String name, SecurityLevel level) {
        this.subjects.put(name.toLowerCase(), level);
    }

    public int checkRead(String sub, String obj) {
        sub = sub.toLowerCase();
        obj = obj.toLowerCase();
        if (this.subjects.get(sub).compareGreat(this.objects.get(obj))) {
            return this.manage.executeRead(obj);
        }
        return 0;
    }

    public void checkDestroy(String sub, String obj) {
        if (this.subjects.containsKey(sub.toLowerCase())
                && this.objects.containsKey(obj.toLowerCase())) {
            if (this.subjects.get(sub.toLowerCase()).compareLess(
                    this.objects.get(obj.toLowerCase()))) {

                this.objects.remove(obj);
                this.manage.executeDestroy(obj.toLowerCase());
            }
        }
    }

    public boolean checkWrite(String sub, String obj, int value) {
        sub = sub.toLowerCase();
        obj = obj.toLowerCase();

        if (this.subjects.get(sub).compareLess(this.objects.get(obj))) {
            this.manage.executeWrite(obj, value);
            return true;
        }
        return false;
    }

    class ObjectManager {
        HashMap<String, Integer> objects = new HashMap<String, Integer>();

        public void createObject(String add, int value) {
            add = add.toLowerCase();
            this.objects.put(add, value);
        }

        public void executeDestroy(String obj) {
            this.objects.remove(obj);
        }

        public int executeRead(String obj) {
            return this.objects.get(obj);
        }

        public void executeWrite(String write, int value) {
            this.objects.put(write, value);
        }
    }
}
