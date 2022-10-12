import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NotePadApp extends JFrame implements ActionListener {
//initialising menu Items
    JMenuBar menuBar;

    JMenu fileMenu;

    JMenuItem newFile;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem print;
    JMenuItem exitItem;

    JMenu edit;

    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem selectAll;

    //initialising other items like textarea
    JTextArea textArea;
    JScrollPane scrollPane;

    public NotePadApp(){
        this.setTitle("Notepad");
        this.setBounds(100,100,800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);



        //------fixing up menu-bar-------
        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");

        newFile=new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        print=new JMenuItem("Print");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(newFile);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(print);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        edit=new JMenu("Edit");

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste= new JMenuItem("Paste");
        selectAll= new JMenuItem("Select All");

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        menuBar.add(edit);

        newFile.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        print.addActionListener(this);
        exitItem.addActionListener(this);

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        // ------menu bar---

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        this.setJMenuBar(menuBar);
        this.add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newFile){
            textArea.setText(null);
        }
        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {

                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                    fileIn.close();
                }

            }

        }

        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(filter);

            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                    fileOut.close();
                }
            }
        }
        if(e.getSource()==print){
            try {
                textArea.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == exitItem) {
            System.exit(0);
        }

        if(e.getSource()==cut){
            textArea.cut();
        }
        if(e.getSource()==copy){
            textArea.copy();
        }
        if(e.getSource()==paste){
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
    }
}