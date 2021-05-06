package sample.company;

import sample.Interfaces.ReportsManager;
import sample.utility.Receipt;

import java.util.ArrayList;

//TODO This Class is new
public class Report implements ReportsManager {
    @Override
    public ArrayList<Receipt> createReport(Company company, int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear) {
        ArrayList<Receipt> receiptList = new ArrayList<>();
        for (Shop shop : company.getShopList()) {
            for (Receipt receipt : shop.getReceiptHashMap().values()) {
                //Get date d,m,y
                String[] splitDate = receipt.getDate().split(" ")[0].split("/");
                if (Integer.parseInt(splitDate[2]) >= sYear && Integer.parseInt(splitDate[1]) >= sMonth && Integer.parseInt(splitDate[0]) >= sDay &&
                    Integer.parseInt(splitDate[2]) <= eYear && Integer.parseInt(splitDate[1]) <= eMonth && Integer.parseInt(splitDate[0]) <= eDay) {
                    receiptList.add(receipt);
                }
            }
        }

        return receiptList;
    }

    @Override
    public void displayTotalIncome(Company company, int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear) {
        ArrayList<Receipt> receiptList = createReport(company, sDay, sMonth, sYear, eDay, eMonth, eYear);
        int totalPrice = 0;

        for (Receipt receipt : receiptList) {
            totalPrice += receipt.getTotalPrice();
        }

        System.out.println("Total Income: " + totalPrice);
    }

}
