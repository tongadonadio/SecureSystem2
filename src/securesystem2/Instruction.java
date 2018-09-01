package securesystem2;

public class Instruction {

    String command, subjName, objName;
    int value;

    public Instruction(String com, String subj, String obj) {
        command = com;
        subjName = subj.toLowerCase();
        objName = obj.toLowerCase();
    }

    public Instruction(String com, String subj, String obj, int val) {
        command = com;
        subjName = subj.toLowerCase();
        objName = obj.toLowerCase();
        value = val;
    }

    static final Instruction BAD_INSTRUCTION
            = new Instruction("BadInstruction", "NoSubject", "NoObject");

    public boolean isReadInstruction() {
        return (command.equalsIgnoreCase("Read"));
    }

    public boolean isWriteInstruction() {
        return (command.equalsIgnoreCase("Write"));
    }

    public boolean isValidSubject(ReferenceMonitor mon) {
        if (mon.subjects.containsKey(subjName)) {
            return true;
        }
        return false;
    }

    public boolean isValidObject(ReferenceMonitor mon) {
        if (mon.objects.containsKey(objName)) {
            return true;
        }
        return false;
    }

    public String getInstructionCommand() {
        return command;
    }

    public String getInstructionObjName() {
        return objName.toLowerCase();
    }

    public String getInstructionSubjName() {
        return subjName.toLowerCase();
    }

    public int getInstructionValue() {
        return value;
    }

    public static Instruction parseInstruction(String line) {
        return null;
    }

    public void printInstruction() {
        if (command.equals("BadInstruction")) {
            System.out.println(command);
        } else if (command.equalsIgnoreCase("read")) {
            System.out.println(subjName + " reads " + objName);
        } else {
            System.out.println(subjName + " writes value " + value + " to " + objName);
        }
    }
}
