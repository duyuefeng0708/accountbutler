/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

import java.util.ArrayList;
import Part1.*;

/**
 *
 * @author youhan
 */
public class Store {
    private static Store instance = new Store();
    private ArrayList<CreditCard> allCards;
    
    public static Store getInstance(){
      return instance;
    }
    
    private Store(){
        allCards=new ArrayList<>();
    }
    
    public ArrayList<CreditCard> getCards(){
        return allCards;
       
    }
    
    public void addCredits(ArrayList<CreditCard> cards){
        this.allCards=cards;
    }
    
    public void clear(){
        allCards=null;
    }
    
    
}
