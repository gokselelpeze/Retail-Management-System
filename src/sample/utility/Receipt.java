package sample.utility;

import sample.company.Employee;
import sample.products.BasketItem;

import java.util.ArrayList;

public class Receipt {
    private static int _ReceiptID = 0;
    private String date;
    private double totalPrice;
    private Employee cashier;
    private ArrayList<BasketItem> cart;
    private int receiptID;

    public Receipt(String date, double totalPrice, Employee cashier, ArrayList<BasketItem> cart) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.cashier = cashier;
        this.cart = cart;
        this.receiptID = _ReceiptID++;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

    public ArrayList<BasketItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<BasketItem> cart) {
        this.cart = cart;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }
}
