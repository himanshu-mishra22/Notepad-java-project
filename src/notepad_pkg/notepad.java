package notepad_pkg;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class notepad extends JFrame implements ActionListener {
    JTextArea txtArea;
    String text;
    public notepad(){
        setTitle("Notepad");
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("notepad_pkg/notepad.png"));
        Image ic = icon.getImage();
        setIconImage(ic);

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);



        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL",Font.PLAIN,14));
        menubar.add(file);
        JMenuItem newDoc = new JMenuItem("New");
        newDoc.addActionListener(this);
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newDoc);
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(open);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        file.add(save);
        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        file.add(print);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL",Font.PLAIN,14));
        menubar.add(edit);
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        edit.add(cut);
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        edit.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        edit.add(paste);
        JMenuItem slctAll = new JMenuItem("Select All");
        slctAll.addActionListener(this);
        slctAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(slctAll);

        txtArea = new JTextArea();
        txtArea.setFont(new Font("SANS-SERIF",Font.PLAIN,18));
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);


        JScrollPane pane = new JScrollPane(txtArea);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);









        setJMenuBar(menubar);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")){
            txtArea.setText("");
        }else if(e.getActionCommand().equals("Open")){
            JFileChooser choose = new JFileChooser();
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt Files","txt");
            choose.addChoosableFileFilter(restrict);
            choose.showOpenDialog(this);

            int action = choose.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = choose.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                txtArea.read(reader,null);
            }catch(Exception ae){
                ae.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Save")){
            JFileChooser saveAs = new JFileChooser();
            saveAs.setApproveButtonText("Save");

            int action = saveAs.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = new File(saveAs.getSelectedFile()+".txt");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                txtArea.write(writer);

            }catch(Exception ae){
                ae.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Print")){
            try{
                txtArea.print();
            }catch(Exception ae){
                ae.printStackTrace();
            }
        }else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }else if(e.getActionCommand().equals("Cut")){
            txtArea.cut();
        }else if(e.getActionCommand().equals("Copy")){
            txtArea.copy();
        }else if(e.getActionCommand().equals("Paste")){
            txtArea.paste();
        }else if(e.getActionCommand().equals("Select All")){
            txtArea.selectAll();
        }
    }
}
