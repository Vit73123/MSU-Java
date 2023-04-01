package project1;

import java.awt.Component;
// Импортировал вручную
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/*
 * JDesktopPane
 */
@SuppressWarnings("oracle.jdeveloper.java.secondary-type")
class InternalFrameTest {
    public static void main(String[] args) {
        JFrame frame = new DesktopFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/*
 * Фрейм содержит панели редактирования для отображения HTML документов
 */
@SuppressWarnings("oracle.jdeveloper.java.serialversionuid-field-missing")
class DesktopFrame extends JFrame {
    private JDesktopPane desktop;
    private int nextFrameX;
    private int nextFrameY;
    private int frameDistance;
    private int counter;
    
    private static final String[] planets = {
        "Mercury",
        "Venus",
        "Earth",
        "Mars",
        "Jupiter",
        "Saturn",
        "Uranus",
        "Neptune",
        "Pluto"
    };
    
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;
    
    public DesktopFrame() {
        setTitle("InternalFrameTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);
        
        // Определяем меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        JMenuItem openItem = new JMenuItem("New");
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                createInternalFrame(
                    new JLabel(new ImageIcon("Images/" + planets[counter] + ".gif")), planets[counter]);
                counter = (counter + 1) % planets.length;
            }
        });
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)  {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        
        JMenu windowMenu = new JMenu("Window");
        menuBar.add(windowMenu);
        
        JMenuItem nextItem = new JMenuItem("Next");
        nextItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                selectNextWindow();
            }
        });
        windowMenu.add(nextItem);
        
        JMenuItem cascadeItem = new JMenuItem("Cascade");
        cascadeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cascadeWindows();
            }
        });
        windowMenu.add(cascadeItem);
        
        JMenuItem tileItem = new JMenuItem("Tile");
        tileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                tileWindows();
            }
        });
        windowMenu.add(tileItem);
    }
   
/*
 * Создаём внутренний фрейм
 * @param c - компонент для отображения во внутреннем фрейме
 * @param t - заголовок внутреннего фрейма
 */
   @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public void createInternalFrame(Component c, String t) {
        final JInternalFrame iframe = new JInternalFrame(
             t, 
             true,  // resizable
             true,  // closable
             true,  // maximizable
             true);
        iframe.add(c, BorderLayout.CENTER);
        desktop.add(iframe);
        iframe.setFrameIcon(new ImageIcon("document.gif"));
        // Обработчик события закрытия внутреннего фрейма (подтверждение)
        iframe.addVetoableChangeListener(new
             VetoableChangeListener() {
                 public void vetoableChange(PropertyChangeEvent event)
                        throws PropertyVetoException {
                     String name = event.getPropertyName();
                     Object value = event.getNewValue();
                     
                     // Мы хотим следить за попытками закрыть фрейм
                     if (name.equals("closed") && value.equals(true)) {
                         // Спрашиваем пользователя закрывать ли фрейм
                         int result = JOptionPane.showInternalConfirmDialog(
                                iframe,
                                "OK to close?",
                                "Select an Option",
                                JOptionPane.YES_NO_OPTION);
                         
                         // Если пользователь не согласен, то не закрываем
                         if (result != JOptionPane.YES_OPTION) {
                             throw new PropertyVetoException(
                                "User canceled close", event);
                         }
                     }
                 }
             });
        
        // Позиционирование фрейма
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;
        iframe.reshape(nextFrameX, nextFrameY, width, height);
        
        iframe.show();
        
        // Выбор фрейма - операция может быть запрещена
        try {
            iframe.setSelected(true);
        } catch (PropertyVetoException e) {
        }
        frameDistance = iframe.getHeight() - iframe.getContentPane().getHeight();
        
        // Вычисляем позицию следующего фрейма
        nextFrameX += frameDistance;
        nextFrameY += frameDistance;
        if (nextFrameX + width > desktop.getWidth()) {
            nextFrameX = 0;
        }
        if (nextFrameY + height > desktop.getHeight()) {
            nextFrameY = 0;
        }
    }
    
   @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
   public void cascadeWindows() {
        int x = 0;
        int y = 0;
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;
        
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) {
                try {
                    // Пытаемся установить нормальные размеры для
                    // максимизированного фрейма
                    // Операция может быть запрещена
                    frame.setMaximum(false);
                    frame.reshape(x, y, width, height);
                    
                    x += frameDistance;
                    y += frameDistance;
                    
                    // wrap around at the desktop edge
                    if (x + width > desktop.getWidth()) {
                        x = 0;
                    }
                    if (y + height > desktop.getHeight()) {
                        y = 0;
                    }
                } catch (PropertyVetoException e) {
                }
            }
        }
    }
    
    public void tileWindows() {
        // Количество фреймов, которые не свёрнуты
        int frameCount = 0;
        
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) {
                frameCount++;
            }
        }
        if (frameCount == 0) {
            return;
        }
        
        int rows = (int) Math.sqrt(frameCount);
        int cols = frameCount / rows;
        int extra = frameCount % rows;
        
        // Количество столбцов в незаполненной строке
        int width = desktop.getWidth() / cols;
        int height = desktop.getHeight() / rows;
        int r = 0;
        int c = 0;
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (!frame.isIcon()) {
                try {
                    frame.setMaximum(false);
                    frame.reshape(c * width, r * height, width, height);
                    r++;
                    if (r == rows) {
                        r = 0;
                        c++;
                        if (c == cols - extra) {
                            // Добавляем доп. строку
                            rows++;
                            height = desktop.getHeight() / rows;
                        }
                    }
                } catch (PropertyVetoException e) {
                }
            }
        }
    }

    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public void selectNextWindow() {
        JInternalFrame[] frames = desktop.getAllFrames();
        
        int indexCurrent = 0;
        for(JInternalFrame frame : frames) {
            if (frame.isSelected()) {
                break;
            }
            indexCurrent++;
        }

        int indexNext = (indexCurrent + 1) % counter;
        while (frames[indexNext].isIcon() && indexNext != indexCurrent) {
            indexNext++;
            indexNext %= counter;
        }
        if (indexNext != indexCurrent) {
            try {
                frames[indexNext].setSelected(true);
            } catch (PropertyVetoException e) {
            }
            frames[indexNext].toFront();
        }
    }
}
