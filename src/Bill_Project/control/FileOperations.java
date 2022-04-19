package Bill_Project.control;

import Bill_Project.view.*;
import Bill_Project.model.BillHeader;
import Bill_Project.model.BillLine;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperations extends Billsystemframe {
    public FileOperations(String title) {
        super(title);
    }

    private JLabel lT;
    private JTable rightTable;
    private ArrayList<ArrayList<String>> data = new ArrayList<>();


    private JTable leftTable;
    private ArrayList<ArrayList<String>> data1 = new ArrayList<>();
    private JButton createNew;
    private JButton delete;
    private ArrayList<BillHeader> Invoices = new ArrayList<>();
    private ArrayList<BillLine> lines = new ArrayList<>();
    private left_table_control headerModel;
    private right_table_control lineModel;
    private SimpleDateFormat dd = new SimpleDateFormat("dd-mm-yyyy");
    private Leftabledetials_frame headerDialogue;
    private Right_tabledetials_frame lineDialogue;


//    Billsystemframe operations=new Billsystemframe();


    private void loadfile() throws Exception {
        Invoices.clear();
        JOptionPane.showMessageDialog(this, "Please select invoice header file ", "Invoice Header", JOptionPane.WARNING_MESSAGE);
        JFileChooser file = new JFileChooser();
        FileReader fr = null;

        int option = file.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selected = file.getSelectedFile();
            try {
                fr = new FileReader(selected);
            }
            catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, " the File is null..check again",
                        " Invoice line  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            BufferedReader buffer = new BufferedReader(fr);
            String inv = null;
            try {
                while ((inv = buffer.readLine()) != null) {
                    String[] invoSegments = inv.split(",");
                    String invNoStr = invoSegments[0];
                    String invDateStr = invoSegments[1];
                    String customerN = invoSegments[2];

                    System.out.println(customerN);
                    int invNo = Integer.parseInt(invNoStr);   // from string to integer
                    Date invDate = dd.parse(invDateStr);
                    BillHeader header = new BillHeader(invNo, invDate, customerN);
                    Invoices.add(header);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, " Wrong File Format",
                        " Invoice Header  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();}
            finally {
                try{ buffer.close();
                    fr.close();
                } catch (Exception e) {e.printStackTrace();}
            }
            System.out.println("try");
            //reading files for the right table
            JOptionPane.showMessageDialog(this, "Please select invoice header line ", "Invoice Line", JOptionPane.WARNING_MESSAGE);
            file = new JFileChooser();
            option = file.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                selected = file.getSelectedFile();
                try {
                    fr = new FileReader(selected);
                }
                catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(this, " the File is null..check again",
                            " Invoice line  ", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                buffer = new BufferedReader(fr);
                try {
                    while ((inv = buffer.readLine()) != null) {
                        String[] invoLines = inv.split(",");
                        String invNoStr = invoLines[0];
                        String item = invoLines[1];
                        String priceStr = invoLines[2];
                        String countStr = invoLines[3];

                        int invNo = Integer.parseInt(invNoStr);   // from string to integer
                        double price = Double.parseDouble(priceStr);
                        int count = Integer.parseInt(countStr);
                        BillHeader header = findByNumber(invNo);
                        BillLine invline = new BillLine(item, price, count, header);
                        header.addLines(invline);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, " Wrong File Format",
                            " Invoice line  ", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }

            }

            buffer.close();
            fr.close();
            headerModel = new left_table_control(Invoices);
            leftTable.setModel(headerModel);
            leftTable.validate();

        }
    }

    private void saveFile() throws IOException {
        JOptionPane.showMessageDialog(this, "Save invoice header file ", "Invoice Header", JOptionPane.INFORMATION_MESSAGE);
        JFileChooser fs = new JFileChooser();
        fs.setDialogTitle("Choose File Save");
        int userSelection = fs.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fs.getSelectedFile();
            FileWriter fw = new FileWriter(fileToSave + ".csv");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < leftTable.getRowCount(); i++) {
                for (int j = 0; j < leftTable.getColumnCount(); j++) {

                    bw.write(leftTable.getValueAt(i, j).toString() + ",");
                }
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Saved Successfully ", "Save Message", JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();
            //Saving invoice line table


            JOptionPane.showMessageDialog(this, "Save invoice line file ", "Invoice Line", JOptionPane.INFORMATION_MESSAGE);
            fs = new JFileChooser();
            fs.setDialogTitle("Choose File Save");
            userSelection = fs.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                fileToSave = fs.getSelectedFile();
                fw = new FileWriter(fileToSave + ".csv");
                bw = new BufferedWriter(fw);
                for (BillHeader inv : Invoices) {
                    for (BillLine item : inv.getLines()) {
                        bw.write(inv.getNum() + "," + item.getItemName() + "," + item.getItemPrice() + "," + item.getCount());
                        bw.newLine();
                    }

                }
                JOptionPane.showMessageDialog(this, "Saved Successfully ", "Save Message", JOptionPane.INFORMATION_MESSAGE);
                bw.close();
                fw.close();

            }


        }
    }
}
