package client1;

public class Class1 extends Class2 implements Interface1 {
    public Class1() {
        super();
    }

    public static void main(String[] args){
        
        Class1 class1 = new Class1();
        int j = class1.M1(10, 20);
    }

    @Override
    public int M1(int i, int j) {
        // TODO Implement this method
        return i + j;
    }
}
