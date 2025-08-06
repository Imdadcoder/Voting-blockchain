/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import java.util.Random;

/**
 *
 * @author sumit
 */
public class otp {
    public String get_otp(){
   String s="";
    
    Random r=new Random();
     int j1=r.nextInt(10);
     int j2=r.nextInt(10);
     int j3=r.nextInt(10);
     int j4=r.nextInt(10);
    s=j1+""+j2+""+j3+""+j4;
    
    return s;
    }
}
