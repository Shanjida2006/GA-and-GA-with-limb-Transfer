/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
//import static javafx.application.Platform.exit;
//import jdk.nashorn.internal.runtime.JSType;
/**
 *
 * @author Hasib
 */
public class FileRead {
    
    private String path;
    public static ArrayList<ItemSet> database;
    
    public static ArrayList<ArrayList<Integer>> itemRel;
    
    public FileRead(String path)
    {
        this.path=path;  
    }
    /*
    
        below method will read a file.and return value as a arrayList<TransactionItem>. which is namend  transactionItemsList 
    */
    public void openFile() throws IOException  
    {
        database=new ArrayList<ItemSet>();
        itemRel=new ArrayList<ArrayList<Integer>>();
        BufferedReader bufferedReader= new BufferedReader(new FileReader(this.path));
        Utility.numberOfItems=0;
        
        while(true)
        {
            String newLine=bufferedReader.readLine();
            if(newLine.startsWith("@"))
            {
                if(newLine.startsWith("@data"))
                    break;
                Utility.numberOfItems++;
            }
            else 
                continue;
        }
        Utility.numberOfItems-=2; // minus two @
        bufferedReader.readLine(); // read the blank line
        String aLine;
       
        boolean accType;  //boolean time 1 or 0
        boolean[] transactionValueArrayBool;
           
        
        while((aLine=bufferedReader.readLine())!=null)
        {
            String[] splitArray=aLine.split(" ");
            ItemSet tempItemSet=new ItemSet();
            for(int i=0;i<splitArray.length-1;i++)
            {
                int itemIndex=Integer.parseInt(splitArray[i]);
                tempItemSet.setItemAtIndex(itemIndex, true);
                ArrayList<Integer> itemArray;
                boolean newC=false;
                if(itemRel.size()>i)
                {
                    itemArray=itemRel.get(i);
                }
                else
                {
                    itemArray=new ArrayList<Integer>();
                    newC=true;
                }
                if(!itemArray.contains(itemIndex))
                    itemArray.add(itemIndex);
                if(newC)
                {
                    itemRel.add(itemArray);
                }
            }
            tempItemSet.setTransactionType(Integer.parseInt(splitArray[splitArray.length-1])==1);
            database.add(tempItemSet);
        }
        Utility.numberOfTransactions=database.size();
        
        for(int i=0;i<itemRel.size();i++)
        {
            System.out.println(itemRel.get(i));
        }
        
    }

    
    
}
