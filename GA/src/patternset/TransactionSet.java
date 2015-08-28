/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

data type for returning value of getCoverage; 
*/
package patternset;

/**
 *
 * @author Hasib
 */
public class TransactionSet {
    
    private boolean [] transaction ;

    public TransactionSet()
    {
        transaction=new boolean[Utility.numberOfTransactions];
    }
    
    public int getSize()
    {
        return transaction.length;
    }
    public boolean getTransactionAtIndex(int index)
    {
        return transaction[index];
    }
    public void setTransactionAtIndex(int index,boolean value)
    {
        transaction[index]=value;
    }
    public TransactionSet(TransactionSet other)
    {
        transaction=new boolean[other.getSize()];
        for(int i=0;i<transaction.length;i++)
            transaction[i]=other.getTransactionAtIndex(i);
    }
    public TransactionSet(boolean other[])
    {
        transaction=new boolean[other.length];
        for(int i=0;i<transaction.length;i++)
            transaction[i]=other[i];
    }
}
