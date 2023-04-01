package client1;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;

/*
class FrameGlassPane {
    private static MyGlassPane myGlassPane;
    
    JFrame frame = new JFrame("MyGlassPane");
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    });

    JCheckBox checkBox1 = new JCheckBox("Выключатель уровня GlassPane");
    checkBox1.setSelected(false);
    checkBox1.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            myGlassPane.setVisible(e.getStateChange() == ItemEvent.SELECTED);
        }
    });
    Container contentPane = frame.getContentPane();
    contentPane.setLayout(new FlowLayout());
    contentPane.add(checkBox1);
    contentPane.add(new JButton("Кнопка"));
    contentPane.add(new JTextField(" Текстовое поле "));
    
    // Создание и установка уровня glassPane
    myGlassPane = new MyGlassPane(checkBox1, frame.getContentPane());
    frame.setGlassPane(myGlassPane);
    frame.pack();
    frame.setVisieble(true);
}

class MyGlassPane extends JComponent {
    Point point;
        
    public void paint(Graphics g) {
        if (point != null) {
            g.setColor(Color.re);
            g.drawString("GlassPane", point.x, point.y);
        }
    }
    
    public void setPoint(Point p) {
        point = p;                  // Точка, в которой выполне щелчок мышью
    }
    
    public MyGlassPane(AbstractButton aButton, Container contentPane) {
        MyListener listener = new MyListener(eButton, this, contentPane);
        addMouseListener(listener);
    }
}

class MyListener extends MouseInputAdapter {
    Toolkit toolkit;
}
*/