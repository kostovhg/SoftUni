package p01_OverloadMethod;

public class MathOperation {
    public int add(int a, int b){
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int add(int a, int b, int c, int d) {
        return add(a, b, c) + c;
    }
}
