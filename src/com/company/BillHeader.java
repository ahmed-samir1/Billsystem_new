package com.company;

import java.util.ArrayList;
import java.util.Date;

public class BillHeader {
    private int num;
    private Date date;
    private String customer;
    private double invoiceTotal;
    private ArrayList<BillLine> lines;


    public BillHeader(int no, Date date, String customer) {
        this.num = no;
        this.date = date;
        this.customer = customer;

    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotal() {
        return invoiceTotal;
    }

    public void setTotal(double total) {
        this.invoiceTotal = total;
    }

    public ArrayList<BillLine> getLines() {

        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<BillLine> lines) {
        this.lines = lines;
    }

    public void addLines(BillLine tot) {
        getLines().add(tot);
        setTotal(getTotal() + tot.getItemTotal());
    }


}
