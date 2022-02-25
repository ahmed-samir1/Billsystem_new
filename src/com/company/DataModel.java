package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DataModel extends AbstractTableModel {

    String[] cols;
    private ArrayList<ArrayList<String>> data;

    public DataModel(String[] cols, ArrayList<ArrayList<String>> data) {
        this.cols = cols;
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex); //return array of string for each row
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
}
