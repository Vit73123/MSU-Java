package projectcoll3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Class1 {
    
    class TestObject {
//        String text = "";
        private String text = "";
        
        public TestObject(String text) {
            this.text = text;
        }
        
        public String getText() {
            return text;
        }
        
        public void setText(String text) {
            this.text = text;
        }
    }
    
    public Class1() {
        super();
    }

    public static void main(String[] args) {
        Class1 c1 = new Class1();
        HashMap hm = new HashMap();     // <String, TestObject>
        TestObject to = null;
        hm.put("Key1", c1.new TestObject("�������� 1"));
        hm.put("Key2", c1.new TestObject("�������� 2"));
        hm.put("Key3", c1.new TestObject("�������� 3"));
        to = (TestObject) hm.get("Key1");
        System.out.println("�������� ������� ��� Key1 = " + to.getText());
        Map.Entry entry = null;
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            entry = (Map.Entry) it.next();
            System.out.println("��� ����� " + entry.getKey() +
                               " �������� = " +
                               ((TestObject) entry.getValue()).getText());
        }
        System.out.println();
        String key = "";
        it = hm.keySet().iterator();
        while (it.hasNext()) {
            key = (String) it.next();
            System.out.println("��� ����� " + key + " �������� = " +
                               ((TestObject) hm.get(key)).getText());
        }
    }
}
