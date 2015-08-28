/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;

import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Hasib
 * 
 */
public class Utility {
    public static int numberOfItems;
    public static int numberOfTransactions;
    public static Random randomNoGenerator = new Random();
    public static int noOfPatternSetInEachIndividuals=15;
    public static float timeToRunCode=1;//total time to run the code
        //********************************************??????????????????????????????///////////////////////////////////////
        //********************************************??????????????????????????????///////////////////////////////////////
        //********************************************??????????????????????????????///////////////////////////////////////
    public static int sizeOfLnsForMutation=3;//its for GA doing lns in mutation
   // public static ArrayList <int> score= new ArrayList<int>();
   
    
   //********************************************??????????????????????????????///////////////////////////////////////
   //********************************************??????????????????????????????//////////////////////////////////////    
   public static int noOfLimbInGA=15;

  /**
   * method to find coverage  
  */
    public static TransactionSet getCoverage(ItemSet itemSet)
    {
         
        TransactionSet transactionSet=new TransactionSet();
        int i =0;
        for(ItemSet transaction : FileRead.database)
        {
            if(itemSet.covers(transaction))
                transactionSet.setTransactionAtIndex(i, true);
            i++;
        }
        return transactionSet;
    }//end of getCovrage method;*/
    
    
    
    /*
       
            method to find dispersion
    
    */
    
    public static int  getDispersionScore(TransactionSet tSet1,TransactionSet tSet2)
    {
        int sum=0,ts1,ts2,dispersionScore;
        for(int i=0;i<tSet1.getSize();i++)
        {
            ts1=(2*convertBoolToInt(tSet1.getTransactionAtIndex(i)))-1;
            ts2=(2*convertBoolToInt(tSet2.getTransactionAtIndex(i)))-1;
            sum=sum+(ts1*ts2);
        }           
        dispersionScore=(int) pow(sum,2);    
        return dispersionScore;
    }
    
    
    
    public static int getDispersionScore(TransactionSet tSet1)
    {
            int dispersionScore;
            boolean[] t2bool=new boolean[tSet1.getSize()];
            for(int i=0;i<t2bool.length;i++)
                t2bool[i]=true;
            TransactionSet tSet2= new TransactionSet(t2bool);
            dispersionScore=getDispersionScore(tSet1,tSet2);
            return dispersionScore;
    }
    
    
    public static int  getDispersionScore(ArrayList<TransactionSet> tSetList)
    {
            int dispersionScore=0;
            int size=tSetList.size();
            for(int tSet1=0;tSet1<size-1;tSet1++)
                for(int tSet2=tSet1+1;tSet2<size;tSet2++ )
                    dispersionScore=dispersionScore+getDispersionScore(tSetList.get(tSet1),tSetList.get(tSet2));
                
            return dispersionScore;
    }
    
   
    
    /*
                methods for XOR SCORE    
    */
    
    /** 
     *   give the XOR dispersion score for 2 transaction
     * @param tSet1  an transaction
     * @param tSet2  an transaction
     * @return an integer value score 
     */
     public static int  getXOR_DispersionScore(TransactionSet tSet1,TransactionSet tSet2)
    {
        int sum=0,ts1,ts2,dispersionScoreForXOR; 
        for(int i=0;i<tSet1.getSize();i++)
        {
            int xorVal=0;
          //  ts1=convertBoolToInt(tSet1.getTransactionAtIndex(i));
          //  ts2=convertBoolToInt(tSet2.getTransactionAtIndex(i));
            if(tSet1.getTransactionAtIndex(i)==tSet2.getTransactionAtIndex(i))//doing XOR. 
                xorVal=0;
            else 
                xorVal=1;
            sum=sum+xorVal;                
        }           
        dispersionScoreForXOR=sum;    
        return dispersionScoreForXOR;
    }
    
    
    
    public static int getXOR_DispersionScore(TransactionSet tSet1)
    {
            int dispersionScoreForXOR;
            boolean[] t2bool=new boolean[tSet1.getSize()];
            for(int i=0;i<t2bool.length;i++)
                t2bool[i]=true;
            TransactionSet tSet2= new TransactionSet(t2bool);
            dispersionScoreForXOR=getXOR_DispersionScore(tSet1,tSet2);
            return dispersionScoreForXOR;
    }
    
    public static int  getXOR_DispersionScore(ArrayList<TransactionSet> tSetList)
    {
            int dispersionScoreForXOR=0;
            int size=tSetList.size();
            for(int tSet1Index=0;tSet1Index<size-1;tSet1Index++)
                for(int tSet2Index=tSet1Index+1;tSet2Index<size;tSet2Index++ )
                    dispersionScoreForXOR=dispersionScoreForXOR+getXOR_DispersionScore(tSetList.get(tSet1Index),tSetList.get(tSet2Index));
                
            return dispersionScoreForXOR;
    }
    
    /**
     * this method ll fing maxIndividual using individual Score
     * from arraylist of individual
     * 
     * @return maximum Individual  from list of individual
     */
    public static Individual findMaxIndividual( ArrayList<Individual> individualList)
    {
    
        Individual maximumIndividual=new Individual();
        for(Individual currentIndividual: individualList )
        {
            if(currentIndividual.getIndividualScoe()>maximumIndividual.getIndividualScoe())
                maximumIndividual=new Individual(currentIndividual);
        }
        return maximumIndividual;
    }
    
    
    /**
     * this method ll fing maxIndividual using individual Score
     * from arraylist of individual
     * @param ArrayList<Individual> individualList
     * @return int  index of minIndividual from list
     */
    public static int findMinIndexOfIndividual( ArrayList<Individual> individualList)
    {
        int index =0,minIndex=0;
        Individual minimumIndividual=new Individual(individualList.get(0));
        for(Individual currentIndividual: individualList )
        {
            if(currentIndividual.getIndividualScoe()<minimumIndividual.getIndividualScoe())
            {
                minimumIndividual=new Individual(currentIndividual);
                minIndex= index;
                
            }   
            index++;
        }
        
        return minIndex;
    }
    
    
   
    
    
    
    static int convertBoolToInt(boolean value)
    {
        return (value? 1:0);
    }
    
    

}
