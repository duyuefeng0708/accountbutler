/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1;

import Part2.Expense;
import java.util.*;

/**
 *
 * @author youhan
 * 
 * this class is optional, for your preference,
 * if you have time, it's better to implement the GUI for this class,
 * but should cancel the other functions for credit card,
 * just preserve necessary functions.
 */
public class CreditCard {
    //link it to one Assets and pay from assets
    private Assets associatedAccount;
    
    //maintain lists of all expenses
    private ArrayList<Expense> unPaidExpenses;

    private ArrayList<Expense> pastExpenses;
    
    //end Date of every month, from 1 to 30
    private int endDate;
    
    //the date to pay, from 1 to 30, will require some adjustment for February
    private int payDate;
    
    //the limit amount for one period
    private float limit;
    
    private float singleLimit;
    
    private float balance;
    
    //card type, "Visa" for example, will be passed from GUI side
    private String type;
    
    private int cardNumber;
    
    private String cardName;
    
    
    public ArrayList<Expense> getUnPaidExpenses(){return unPaidExpenses;}
    public ArrayList<Expense> getPastExpenses(){return pastExpenses;}
    public int getPayDate(){return payDate;}
    public float getLimit(){return limit;}
    public float getSingle(){return singleLimit;}
    public float getBalance(){return balance;}
            
    public CreditCard(int endDate, int payDate, float limit, float single, String type, float balance, int cardN, String name){
        this.endDate=endDate;
        this.payDate=payDate;
        this.limit=limit;
        this.singleLimit=single;
        this.type=type;
        this.balance=balance;
        this.cardNumber=cardN;
        this.cardName=name;
    }
    
    public static String validate(int endDate, int payDate, float limit, float single, String type, float balance, int cardN){
        if (type.equals("VISA")){
            if (((Math.floor(Math.log10(cardN) + 1))==16 || (Math.floor(Math.log10(cardN) + 1))==13) 
                    && Integer.parseInt(Integer.toString(cardN).substring(0, 1))==4){
                if (0<payDate && payDate <=28)
                    return "wrong pay date";
                if (0<endDate && endDate<=28)
                    return "wrong end date";
                if (single>limit)
                    return "single paymeny limit cannot be higher than period limit";
                if (limit<balance)
                    return "balance shouldn't be larger than limit";
                return "Valid VISA card";
            }else
                return "Invalid VISA card number";
            
        }else if (type.equals("Master")){
            if ((Math.floor(Math.log10(cardN) + 1))==16 
                    && Integer.parseInt(Integer.toString(cardN).substring(0, 1))==5
                    && 1<=Integer.parseInt(Integer.toString(cardN).substring(1, 2))
                    && 5>=Integer.parseInt(Integer.toString(cardN).substring(1, 2))){
                if (0<payDate && payDate <=28)
                    return "wrong pay date";
                if (0<endDate && endDate<=28)
                    return "wrong end date";
                if (single>limit)
                    return "single paymeny limit cannot be higher than period limit";
                if (limit<balance)
                    return "balance shouldn't be larger than limit";
                return "Valid Master card";
            }else
                return "Invalid Master card number";

        }else if (type.equals("American Express")){
            if ((Math.floor(Math.log10(cardN) + 1))==15
                    && Integer.parseInt(Integer.toString(cardN).substring(0, 1))==3
                    && (Integer.parseInt(Integer.toString(cardN).substring(1, 2))==4
                    || Integer.parseInt(Integer.toString(cardN).substring(1, 2))==7)){
                if (0<payDate && payDate <=28)
                    return "wrong pay date";
                if (0<endDate && endDate<=28)
                    return "wrong end date";
                if (single>limit)
                    return "single paymeny limit cannot be higher than period limit";
                if (limit<balance)
                    return "balance shouldn't be larger than limit";
                return "Valid American Express card";
                
            }else
                return "Invalid American Express number";
                    
        }else
            return "Please choose right card type";
    }
    
    public String addPayment(float payment){
        if (payment>=singleLimit)
            return "Payment Larger than limit!";
        else if (payment+balance>limit)
            return "Exceed month limit!";
        else
            return "Added!";
    }
    
    public void addNewExpense(Expense e){
        if (balance+e.getAmount()<=limit){
            unPaidExpenses.add(e);
            balance+=e.getAmount();
        }else
            reachLimit();
    }
    
    public void reachLimit(){
        //to handle and output message in GUI
    }
    //move to New period
    public void moveToNew(){
        Expense expense=new Expense();
        associatedAccount.applyExchange(expense);
        balance=0;
    }
    
}
