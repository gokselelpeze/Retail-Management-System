package sample.Interfaces;

import sample.company.Company;
import sample.utility.Receipt;

import java.util.ArrayList;

//TODO Yeniden düzenlendi
public interface ReportsManager {
    /**
     * Create a receipts list for the selected date
     */
    ArrayList<Receipt> createReport(Company company, int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear);

    /**
     * Fetches specified report
     */
    void displayTotalIncome(Company company, int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear);


}
