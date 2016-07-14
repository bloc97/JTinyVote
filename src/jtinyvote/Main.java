/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Note to add system preventing repeating choices in close votes. (such as 30/20/30/10 or 50/50 votes, etc.)
// Currently if there are a lot of similar votes, some choices might be selected much more often.
// 100% Randomness is not reliable nor acceptable.
// Multipling the tables by 2 (or a bigger number) gives a higher chance to the most voted choice.
package jtinyvote;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bowen
 */
public class Main {
    
    
    public static int[] inputVotes(){ //Creates a table by console input.
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of choices: ");
        int[] voteList = new int[input.nextInt()];
        for (int i=0;i<voteList.length;i++){
            System.out.print("Enter votes for choice "+i+": ");voteList[i]=input.nextInt();
        }
        return voteList;
    }
    
    
    public static int getVotes(int[] table){ //Returns total number of votes.
        int total = 0;
        for (int i=0;i<table.length;i++){
            total=total+table[i];
        }
        return total;
    }
    
    public static int[] getSumTable(int[] table, int total){ //Gets the series of the vote sequence table. Can be used to import a table.
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
    
    public static int randomGen(int total){ //Generates a random number between 0 and a max integer.
        Random random = new Random();
        return random.nextInt(total);
    }
    
    public static int getChosen(int[] table, float value){ //Checks which portion of the sum table the value falls on.
        int chosenmap = 0;
        for (int i=0;i<table.length;i++){
            if (table[i]<value){ //Checks and knows if the value is situated in the next chunk of voters, until it is not anymore.
                chosenmap = i+1; 
            }
        }
        return chosenmap;
    }
    
    public static void printTable(int[] table, String text){ //Prints an entire table.
        for (int i=0;i<table.length;i++){
            System.out.println(text+table[i]);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { //Main Program
        // TODO code application logic here
        
        int[] votelist = inputVotes(); //Only used to test this program. Comment out when not needed.
        int votes = getVotes(votelist); //Call in the table here, or use inputVotes to input manually.
        int[] sumlist = getSumTable(votelist, votes);
        
        //printTable(votelist,"Vote Table: ");
        printTable(sumlist,"Sum Table: ");
        
        for (int i=0;i<10;i++){
            int random = randomGen(votes);
            int choice = getChosen(sumlist, random);
            //System.out.println("Random: "+random);
            System.out.println("Choice: "+choice);
        }
        

    }
    
}
