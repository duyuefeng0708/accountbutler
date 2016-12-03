/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

/**
 *
 * @author duyue_000
 */
public class BudgetYearMonth {
    private static String year;
    private static String month;
    
    public static void setMonth(String m){
        month = m;
    }
    
    public static void setYear(String y){
        year = y;
    }
    
    public static String getMonth(){
        return month; 
    }
    
    public static String getYear(){
        return year; 
    }
}
