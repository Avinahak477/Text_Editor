import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

// File Menu Items
    JMenuItem newFile, openFile, saveFile;
    // Edit Menu Item
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;



    TextEditor(){
        //Initialise a frame
        frame = new JFrame();

        ///Initialise menubar
        menuBar = new JMenuBar();

        // Initialise Text Area
         textArea = new JTextArea();

        //Initislise menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialise file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Add action listeners to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialise edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Adding Action listeners to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        // Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        // set Menubar to frame
        frame.setJMenuBar(menuBar);
         //Add text Area to frame
        //frame.add(textArea);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);

        //Set dimension of frame
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
     if(actionEvent.getSource()==cut){
         textArea.cut();
     }
     if(actionEvent.getSource()==copy){
         textArea.copy();
     }
     if(actionEvent.getSource()==paste){
         textArea.paste();
     }
     if(actionEvent.getSource()==selectAll){
         textArea.selectAll();
     }
     if(actionEvent.getSource()==close){
         System.exit(0);
     }
     if (actionEvent.getSource()==openFile){
         //open a file chooer
         JFileChooser fileChooser = new JFileChooser("C:");
         int chooseOption = fileChooser.showOpenDialog(null);

         if(chooseOption==JFileChooser.APPROVE_OPTION){
             //Getting selected files
             File file = fileChooser.getSelectedFile();
             String filePath = file.getPath();
             try{
                 FileReader fileReader = new FileReader(filePath);
                 BufferedReader bufferedReader = new BufferedReader(fileReader);
                 String intermediate = "",output = "";
                 while((intermediate = bufferedReader.readLine())!=null){
                     output+=intermediate+"\n";
                 }
                 textArea.setText(output);
             } catch (IOException fileNotFoundException){
                 fileNotFoundException.printStackTrace();
             }
         }
     }

     if(actionEvent.getSource()==saveFile){
         JFileChooser fileChooser = new JFileChooser("C:");
         int chooseOption = fileChooser.showSaveDialog(null);
         if(chooseOption==JFileChooser.APPROVE_OPTION){
             File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
             try{
                 FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                 textArea.write(bufferedWriter);
                 bufferedWriter.close();
             }
             catch (IOException ioException){
                 ioException.printStackTrace();
             }
         }
     }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }

    }

    public static void main(String[] args) {
         TextEditor textEditor = new TextEditor();
    }
}