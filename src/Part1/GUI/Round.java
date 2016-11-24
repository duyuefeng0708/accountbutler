/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part1.GUI;

import java.math.BigDecimal;

/**
 *
 * @author duyue_000
 */
public class Round {
    public static float round(float d, int decimalPlace){
        BigDecimal bd = new BigDecimal(Float.toString(d)); 
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
