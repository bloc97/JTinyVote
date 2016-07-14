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
    
    
    public static int[] inputVotes(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of choices: ");
        int[] voteList = new int[input.nextInt()];
        for (int i=0;i<voteList.length;i++){
            System.out.print("Enter votes for choice "+i+": ");voteList[i]=input.nextInt();
        }
        return voteList;
    }
    
    
    public static int getVotes(int[] table){
        int total = 0;
        for (int i=0;i<table.length;i++){
            total=total+table[i];
        }
        return total;
    }
    
    public static int[] getSumTable(int[] table, int total){
        int[] voteList;
        if (table.length<2){
            voteList = new int[1];
            voteList[0]=total;
        }else{
            voteList = table.clone();
            for (int i=1;i<voteList.length;i++){
                voteList[i]=voteList[i]+voteList[i-1];
            }
        }
        return voteList;
    }
    
    public static int randomGen(int total){
        Random random = new Random();
        return random.nextInt(total);
    }
    
    public static int getChosen(int[] table, float value){
        int chosenmap = 0;
        for (int i=0;i<table.length;i++){
            if (value>table[i]){
                chosenmap = i;
            }
        }
        return chosenmap;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int[] votelist = inputVotes();
        int votes = getVotes(votelist);
        int[] sumlist = getSumTable(votelist, votes);
        
        
        for (int i=0;i<10;i++){
            int random = randomGen(votes);
            int choice = getChosen(sumlist, random);
            System.out.println(choice);
        }
        

    }
    
}
