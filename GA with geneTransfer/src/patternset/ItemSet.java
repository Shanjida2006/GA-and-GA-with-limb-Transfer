/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patternset;

/**
 *
 * @author Hasib
 */

/**
 *   @param boolean[] itemSet
 *   @param boolean transactionType
 *   this class store the data in as unit of TransactionItem.
 *    every line of input is 1 TransactionItem.
 *   data set get 2 type of data in each line. 
 *  last data is class data in each line which is taken by accType. 
 */
public class ItemSet {
    
    private boolean transactionType;
    private boolean[] itemSet;

    /**
     * defalult constructor
     * initialize value of Itemset array
     * it does not initialize transactionType
     */
    public ItemSet()
    {
        itemSet=new boolean[Utility.numberOfItems];
    }
    
    /**
     * 
     * @return size of itemset array 
     */
    public int getSize()
    {
        return itemSet.length;
    }
    
    public boolean getItemAtIndex(int index)
    {
        return itemSet[index];
    }
    public void setItemAtIndex(int index,boolean value)
    {
        itemSet[index]=value;
    }
    public void flipItemAtIndex(int index)
    {
        itemSet[index]=!itemSet[index];
    }
    
    /**
     * copy constructor
     * @param other 
     */
    public ItemSet(ItemSet other)
    {
        transactionType=other.transactionType;
        itemSet=new boolean[other.getSize()];
        for(int i=0;i<itemSet.length;i++)
        {
            itemSet[i]=other.getItemAtIndex(i);
        }     
    }
    
    public ItemSet(boolean accType, boolean[] transactionSet) {
        this.transactionType = accType;
        this.itemSet=new boolean[transactionSet.length];
        for(int i=0;i<itemSet.length;i++)
        {
            itemSet[i]=transactionSet[i];
        }
    }


    public boolean getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(boolean accType) {
        this.transactionType = accType;
    }

    public boolean[] getItemSet() {
        return itemSet;
    }

    public void setTransactionSet(boolean[] transactionSet) {
        itemSet=new boolean[transactionSet.length];
        for(int i=0;i<itemSet.length;i++)
        {
            itemSet[i]=transactionSet[i];
        }
    }
    public String toString()
    {
        String toReturn="";
        for(int i=0;i<getSize();i++)
        {
            if(getItemAtIndex(i))
                toReturn+="1";
            else
                toReturn+="0";
        }
        return toReturn;
    }
    
    public boolean covers(ItemSet you)
    {
        for(int i=0;i<getSize();i++)
        {
            if(getItemAtIndex(i)==true && you.getItemAtIndex(i)==false)
                return false;
        }
        return true;
    }
}
