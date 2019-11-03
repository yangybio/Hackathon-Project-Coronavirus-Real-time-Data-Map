package ui;

import model.*;
import model.exception.MoneyException;
import model.exception.TimeFormException;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

public class ProcessMoney {
    private double money;
    public Scanner scanner = new Scanner(System.in);
    private ItemList summary;
//    private SameNameHashMap sameNameList;
//    private TimeTracker monthList;

    //MODIFIES:This
    //EFFECT: Initialize the Money
    public ProcessMoney() throws IOException, MoneyException {
        summary = new ItemList();
        summary.getData("savedFile.txt");
//        money = summary.getTotalMoney();
    }


//    //MODIFIES:This
//    //EFFECT: Set the money
//    public void setMoney(double m) {
//        money = m + money;
//    }

    //MODIFIES:This and newItem
    //EFFECT: Record the date, money and category for newItem
    public void processMoney() throws IOException, ParseException, MoneyException {
        System.out.println("Does this item need to be paid monthly? ");
        System.out.println("Enter 1 for yes, 0 for no");
        String p = scanner.nextLine();
        if (p.equals("0")) {
            processDItem();
        }
        if (p.equals("1")) {
            System.out.println("Enter how many times you need to pay:");
            int times = Integer.parseInt(scanner.nextLine());
            processMItem(times);
        }
    }

    public void process(Item newItem) {
        enterDate(newItem);
        System.out.println("Please enter the money you spent at " + newItem.getDate());
        while (true) {
            double m = Double.parseDouble(scanner.nextLine());
            try {
                newItem.setMoney(m);
                break;
            } catch (MoneyException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter again:");
            } finally {
                System.out.println("Keep Going!");
            }
        }
        System.out.println("Please enter what your money spent for (use _ instead of space):");
        newItem.setItemName(scanner.nextLine());
        System.out.println("You spent " + newItem.getMoney() + " at " + newItem.getDate());
        System.out.println("for " + newItem.getItemName());
    }

    public void processDItem() throws IOException {
        Item newDailyAddedItem = new DailyAddedItem();
        process(newDailyAddedItem);
        summary.insert(newDailyAddedItem);
        summary.record("savedFile.txt");
    }

    public void processMItem(int times) throws IOException, ParseException, MoneyException {
        Item newMItem = new MonthlyItem();
        process(newMItem);
        for (int i = 0; i < times; i++) {
            Item tempoItem = new MonthlyItem(newMItem.getDate(),newMItem.getItemName(),newMItem.getMoney());
            tempoItem.setDate(newMItem.nextMonthPay());
            newMItem.setDate(tempoItem.getDate());
            summary.insert(tempoItem);
        }
        summary.record("savedFile.txt");
    }

    public void enterDate(Item newAddedItem) {
        System.out.println("Please enter the date you spent the money(yyyy-mm-dd):");
        while (true) {
            String time = scanner.nextLine();
            try {
                newAddedItem.checkValidDate(time);
                newAddedItem.setDate(time);
                break;
            } catch (TimeFormException e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Again!");
            } finally {
                System.out.println("Keep going.");
            }
        }
    }


//    //EFFECT: Present the total money spent and summary of recorded items
//    public void presentMoney() {
//        presentTotalMoney();
//        presentSummary();
//    }
//
//    //EFFECT: print out the total money spent
//    public void presentTotalMoney() {
//        System.out.println("You spent " + money + " totally.");
//    }
//
//    //EFFECT: Print out the summary of recorded items (money, data and name)
//    public void presentSummary() {
//        for (Item i : summary.getItemList()) {
//            System.out.println("Date: " + i.getDate());
//            System.out.println("Item: " + i.getItemName());
//            System.out.println("Money: " + i.getMoney());
//            System.out.println("----------------------------");
//        }
//    }
}
