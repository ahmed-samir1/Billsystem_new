package com.company;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Rtableinput extends DefaultTableModel {
    private String[] cols = {"No.", "Item Name", "Item Price", "Count", "Item Total"};
    private ArrayList<BillLine> data;

    public Rtableinput(ArrayList<BillLine> data) {
        this.data = data;
    }

    public int getRowCount() {

        if (this.data == null) {
            data = new ArrayList<>();
        }
        return data.size();
    }

    public int getColumnCount() {
        return cols.length;
    }

    public String getColumnName(int column) {
        return cols[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BillLine row = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row.getInvH().getNum();
            case 1:
                return row.getItemName();
            case 2:
                return row.getItemPrice();
            case 3:
                return row.getCount();
            case 4:
                return row.getItemTotal();
        }
        return "";
    }

    public ArrayList<BillLine> getData() {
        return data;
    }
}
