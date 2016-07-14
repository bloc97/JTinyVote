/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Note to add system preventing repeating choices in close votes. (such as 30/20/30/10 or 50/50 votes, etc.)
package jtinyvote;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bowen
 */
public class Main {
    
    static int CHOICES;
    static float[] choiceList;
    static int chosenmap;
    static float value;
    
    public static float[] getVotes(){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of choices: ");Main.CHOICES=input.nextInt();
        
        if (Main.CHOICES<2){
        choiceList = new float[1];
        choiceList[0]=100F;
        } //Minimum 2
        else{
            choiceList = new float[Main.CHOICES];
            for (int i=0;i<Main.CHOICES;i++){
                if (i==Main.CHOICES-1){
                   choiceList[i]=100F;
                }else{
                    if (i<=0){
                        System.out.print("Enter vote percent for map "+(i+1)+": ");value=input.nextFloat();
                        if (value>100F){value=100F;}
                        else{choiceList[i]=value;}
                    }
                    else if (choiceList[i-1]<100){
                        System.out.print("Enter vote percent for map "+(i+1)+": ");value=input.nextFloat();
                        value=value+choiceList[i-1];
                        if (value>100F){value=100F;}
                        else{choiceList[i]=value;}
                    }
                    else{
                        choiceList[i]=100F;
                    }
                }
            }
        }
        return choiceList;
    }
    
    public static float randomGen(){
        Random random = new Random();
        float rand = random.nextFloat()*100;
        return rand;
    }
    
    public static int getMap(float[] table, float value){
        for (int i=0;i<table.length;i++){
            if (value>table[i]){
                chosenmap=i+1;
            }
        }
        return chosenmap;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        choiceList=getVotes();
        
        System.out.println("The Chosen Map is: "+(getMap(choiceList, randomGen())+1));

    }
    
}
