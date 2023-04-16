package GameObjects;

public class Designations {
    private char designation;
    private int code;

    public Designations(char designation, int code) {
        this.designation = designation;
        this.code = code;
    }

    public char getDesignation() {
        return designation;
    }

    public int getCode() {
        return code;
    }
}
