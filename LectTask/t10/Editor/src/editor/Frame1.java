
package editor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;

import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Keymap;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Vladimir
 */
public class Frame1 extends javax.swing.JFrame {
    @SuppressWarnings({ "compatibility:2468855637402546655", "oracle.jdeveloper.java.serialversionuid-stale" })
    private static final long serialVersionUID = -5303910090152705509L;

    StyledDocument1 styleDoc1 = new StyledDocument1(500);
    // ��� ���������� undo/redo
    protected MyUndoAction myUndoAction = new MyUndoAction();
    protected MyRedoAction myRedoAction = new MyRedoAction();
    protected UndoManager undo = new UndoManager();

    /** Creates new form Frame1 */
    public Frame1() {
        initComponents();
        initComponents1();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        statusPane = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        editMenu = new javax.swing.JMenu();
        styleMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        textPane.setDocument(styleDoc1);
        jScrollPane1.setViewportView(textPane);

        jSplitPane1.setTopComponent(jScrollPane1);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        jSplitPane1.setRightComponent(jScrollPane2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(statusPane, java.awt.BorderLayout.SOUTH);

        editMenu.setText("������");
        mainMenuBar.add(editMenu);

        styleMenu.setText("�����");
        mainMenuBar.add(styleMenu);

        setJMenuBar(mainMenuBar);

        pack();
    }//GEN-END:initComponents

    @SuppressWarnings({ "unused", "unchecked" })
    private void initComponents1() {
/*
 *      ����
 */
        Hashtable actions;
        actions = new Hashtable();
        
        Action[] actionArrays = textPane.getActions();
        for (int i = 0; i < actionArrays.length; i++) {
            Action a = actionArrays[i];
            actions.put(a.getValue(Action.NAME), a);
        }
        
        // ���� ������
        editMenu.add((Action) (actions.get(DefaultEditorKit.cutAction)));
        editMenu.add((Action) (actions.get(DefaultEditorKit.copyAction)));
        editMenu.add((Action) (actions.get(DefaultEditorKit.pasteAction)));
        
        editMenu.addSeparator();

        editMenu.add((Action) (actions.get(DefaultEditorKit.selectAllAction)));
        
        editMenu.addSeparator();

        this.myUndoAction = new MyUndoAction();
        editMenu.add(myUndoAction);
        this.myRedoAction = new MyRedoAction();
        editMenu.add(myRedoAction);

        // ���� �����
        Action action;
        
        action = new StyledEditorKit.BoldAction();
        action.putValue(Action.NAME, "����������");
        styleMenu.add(action);
        
        action = new StyledEditorKit.ItalicAction();
        action.putValue(Action.NAME, "������");
        styleMenu.add(action);
        
        action = new StyledEditorKit.UnderlineAction();
        action.putValue(Action.NAME, "������������");
        styleMenu.add(action);
        
        styleMenu.addSeparator();
        
        styleMenu.add(new StyledEditorKit.FontSizeAction("12", 12));
        styleMenu.add(new StyledEditorKit.FontSizeAction("14", 14));
        styleMenu.add(new StyledEditorKit.FontSizeAction("16", 16));
        styleMenu.add(new StyledEditorKit.FontSizeAction("18", 18));
        
        styleMenu.addSeparator();
        
        styleMenu.add(new StyledEditorKit.FontFamilyAction("����� Times", "Serif"));
        styleMenu.add(new StyledEditorKit.FontFamilyAction("����� Arial", "SansSerif"));
        
        styleMenu.addSeparator();
        
        styleMenu.add(new StyledEditorKit.ForegroundAction("�������", Color.red));
        styleMenu.add(new StyledEditorKit.ForegroundAction("������", Color.green));
        styleMenu.add(new StyledEditorKit.ForegroundAction("RGB(100, 100, 100", new Color(100, 100, 100)));
        styleMenu.add(new StyledEditorKit.ForegroundAction("׸����", Color.black));

/*
 *      ������� ������ �������������
 */
        @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
        Keymap keymap = textPane.addKeymap("MyKeyMap", textPane.getKeymap());
        KeyStroke key;
        
        // Ctrl+B
        action = (Action) (actions.get(DefaultEditorKit.backwardAction));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);
        
        // Ctrl+F
        action = (Action) (actions.get(DefaultEditorKit.forwardAction));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);
        
        // Ctrl+U
        action = (Action) (actions.get(DefaultEditorKit.upAction));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

        // Ctrl+D
        action = (Action) (actions.get(DefaultEditorKit.downAction));
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK);
        keymap.addActionForKeyStroke(key, action);

        textPane.setKeymap(keymap);
        
/*
 *      ������� �������������� ������
 */
        String stringForPane[] = {
            "������1",
            "������2",
            "������3",
            "��� ����������� �� ������",
            "���������� ��������� ����� ������������",
            "Ctrl+b � Ctrl+f",
            "111111"};
        SimpleAttributeSet[] attrs = new SimpleAttributeSet[stringForPane.length + 1];
        attrs[0] = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrs[0], "SansSerif");
        StyleConstants.setFontSize(attrs[0], 14);
        
        attrs[1] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[1], Color.cyan);
        
        attrs[2] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[2], Color.magenta);
        StyleConstants.setFontSize(attrs[2], 20);
        
        attrs[3] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[3], Color.green);
        StyleConstants.setFontSize(attrs[2], 20);
        
        attrs[4] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[4], Color.yellow);

        attrs[5] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[5], Color.orange);
        
        attrs[6] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[6], Color.blue);

        attrs[7] = new SimpleAttributeSet(attrs[0]);
        StyleConstants.setForeground(attrs[7], Color.pink);
        
        try {
            for (int j = 0; j < stringForPane.length; j++) {
                    styleDoc1.insertString(styleDoc1.getLength(), stringForPane[j] + "\n", attrs[j + 1]);
            }
        } catch (BadLocationException e) {
            System.err.println("������ ��� ������� ������");
        }
        
/*
 *      ������������ ������� �������
 */
        MyCaretListener myCaretListener = new MyCaretListener("��������� ������� �����");
        
        // Listener
        textPane.addCaretListener(myCaretListener);
        statusPane.add(myCaretListener, BorderLayout.CENTER);

/*
 *      ���� ������������� DocumentListener
 */
        styleDoc1.addDocumentListener(new MyDocumentListener());

/*
*      ���� ������������� UndoableEditListener
*/
        styleDoc1.addUndoableEditListener(new MyUndoableEditListener());
    }

    @SuppressWarnings({ "oracle.jdeveloper.java.inner-class-serializable",
                        "oracle.jdeveloper.java.serialversionuid-field-missing" })
    public class StyledDocument1 extends DefaultStyledDocument {
        
        int maxCharacter;
        
        public StyledDocument1(int ii) {
            maxCharacter = ii;
        }
        
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if ((this.getLength() + str.length()) <= maxCharacter) {
                super.insertString(offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @SuppressWarnings({ "oracle.jdeveloper.java.inner-class-serializable",
                        "oracle.jdeveloper.java.serialversionuid-field-missing" })
    protected class MyCaretListener extends JLabel implements CaretListener {
        
        public MyCaretListener(String label) {
            super(label);
        }

        @Override
        public void caretUpdate(CaretEvent caretEvent) {
            // TODO Implement this method
            // ��������� ���
            if (caretEvent.getDot() == caretEvent.getMark()) {
                try {
                    Rectangle caretCoord = textPane.modelToView(caretEvent.getDot());
                    this.setText("������ �����: �������: " + caretEvent.getDot() +
                                 "   ����������: [" + caretCoord.x + ", " + caretCoord.y + "[\n");
                } catch (BadLocationException e) {
                    setText("������ �����: �������: " + caretEvent.getDot());
                }
            } else if (caretEvent.getDot() < caretEvent.getMark()) {
                this.setText("�������� ������� � " + caretEvent.getDot() + 
                             " �� " + caretEvent.getMark() + "\n");
            } else {
                this.setText("�������� ������� �" + caretEvent.getMark() +
                             " �� " + caretEvent.getDot() + "\n");
            }
        }
    }
    
    protected class MyDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            // TODO Implement this method
            printTo(documentEvent);
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            // TODO Implement this method
            printTo(documentEvent);
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            // TODO Implement this method
            printTo(documentEvent);
        }
        
        private void printTo(DocumentEvent documentEvent) {
            Document doc = documentEvent.getDocument();
            int changeLength = documentEvent.getLength();
            textArea.append(documentEvent.getType().toString() + ":" +
                            changeLength + " ����. " +
                            "����� ����� ������: " + 
                            doc.getLength() + "\n");
        }
    }

/*
 *      ��� ���������� undo/redo
 */
    // ��� �������� ������������ ��������
    @SuppressWarnings({ "oracle.jdeveloper.java.serialversionuid-field-missing",
                        "oracle.jdeveloper.java.inner-class-serializable" })
    class MyUndoAction extends AbstractAction {
        
        public MyUndoAction() {
            super("Undo");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO Implement this method
            try {
                // ����� ������������ ��������
                undo.undo();
            } catch (CannotUndoException e) {
                System.out.println("������ ����������: " + e);
                e.printStackTrace();
            }
            myUpdateUndo();
            myRedoAction.myUpdateRedo();
        }
        
        protected void myUpdateUndo() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    @SuppressWarnings({ "oracle.jdeveloper.java.serialversionuid-field-missing",
                        "oracle.jdeveloper.java.inner-class-serializable" })
    class MyRedoAction extends AbstractAction {
               
       public MyRedoAction() {
           super("Redo");
           setEnabled(false);
       }
       
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // TODO Implement this method
            try {
                undo.redo();
            } catch (CannotRedoException e) {
                System.out.println("������ ����������: " + e);
                e.printStackTrace();
            }
        }
        
        protected void myUpdateRedo() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
    
    protected class MyUndoableEditListener implements UndoableEditListener {

        @Override
        public void undoableEditHappened(UndoableEditEvent undoableEditEvent) {
            // TODO Implement this method
            undo.addEdit(undoableEditEvent.getEdit());
            myUndoAction.myUpdateUndo();
            myRedoAction.myUpdateRedo();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing
                                                                   .UIManager
                                                                   .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing
                         .UIManager
                         .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util
                .logging
                .Logger
                .getLogger(Frame1.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(Frame1.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(Frame1.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(Frame1.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
            .EventQueue
            .invokeLater(new Runnable() {
                public void run() {
                    new Frame1().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel statusPane;
    private javax.swing.JMenu styleMenu;
    private javax.swing.JTextArea textArea;
    private javax.swing.JTextPane textPane;
    // End of variables declaration//GEN-END:variables

}