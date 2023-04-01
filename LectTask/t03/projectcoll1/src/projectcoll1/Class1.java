package projectcoll1;

import java.util.ArrayList;
import java.util.Iterator;

public class Class1 {
    public Class1() {
        super();
    }

    public static void main(String[] args) {
//        Class1 class1 = new Class1();
        ArrayList<String> a1 = new ArrayList<String>();
        a1.add("1 элемент");
        a1.add("2");
        a1.add("3 элемент");
        a1.add(1, "New");
        Iterator it = a1.iterator();
        System.out.println();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        a1.add(2, "Вставка");
        it = a1.iterator();
        System.out.println();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        Object[] ob = a1.toArray();
//        
        System.out.println();
        for(Object a : ob) {
            System.out.println(a);
        }
    }
}
