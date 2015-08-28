/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;

/**
 *
 * @author Hasib
 * 
 * a basic limb 
 * 
 */
public class LimbBasic {
    private int sizeOfLimb;
    private int startPosition;
    private int endPosition;
    private boolean[] valueArray;
    private int scoreOfLimb;
    
    /**
     * copy constructor
     * @param limb 
     */
    public LimbBasic(LimbBasic limb)
    {
        this.sizeOfLimb= limb.getSizeOfLimb();
        this.startPosition= limb.getStartPosition();
        this.endPosition=limb.getEndPosition();
        this.valueArray=new boolean[sizeOfLimb];
        for(int i=0;i<limb.sizeOfLimb;i++)
            valueArray[i]=limb.getLimbValueAtIndex(i);   
        
        this.scoreOfLimb=limb.getScoreOfLimb();
    }

    
       /**
        * creating a limb from given limb size and a Individual
        * we ll randomly take a start point .and use that to create a limb
        * from Individual
        * @param sizeOfLimb
        * @param indi 
        */
    public LimbBasic(int sizeOfLimb,Individual indi) 
    {
        this.sizeOfLimb=sizeOfLimb;
        this.startPosition=Utility.randomNoGenerator.nextInt(indi.getSize()-this.sizeOfLimb);
        this.endPosition=startPosition+sizeOfLimb-1;
        this.valueArray= new boolean[sizeOfLimb];
        int index=startPosition;
        Individual individal= new Individual(indi);
        ItemSet itemset=new ItemSet(individal.getPatternSetnAtIndex(Utility.noOfPatternSetInEachIndividuals-1));
        for(int i=0;i<sizeOfLimb;i++)
            valueArray[i]=itemset.getItemAtIndex(index++);
        
        
        //???????????????????????????????????????//////////////
        this.scoreOfLimb= 0;
    }

    
    
    
    
    
    /**
     * getter for limb score**************************not complete
     * @return 
     */
    public int getScoreOfLimb() {
        return scoreOfLimb;
    }
    
    
    /**
     * setter for limb score
     * @param scoreOfLimb 
     */ 
    public void setScoreOfLimb(int scoreOfLimb) {
        this.scoreOfLimb = scoreOfLimb;
    }

   /**
    * getter for limb size
    * @return 
    */    
    public int getSizeOfLimb() {
        return sizeOfLimb;
    }
   /**
    * setter for limb size
    * @param sizeOfLimb 
    */
    public void setSizeOfLimb(int sizeOfLimb) {
        this.sizeOfLimb = sizeOfLimb;
    }
   /**
    * getter for starting psition
    * @return 
    */
    public int getStartPosition() {
        return startPosition;
    }
   /**
    * setter for starting position
    * @param startPosition 
    */
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
   /**
    * getter for end position
    * @return 
    */
    public int getEndPosition() {
        return endPosition;
    }
   /**
    * setter for end position
    * @param endPosition 
    */
    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }
   /**
    * 
    * @return 
    */
    public boolean[] getValueArray() {
        return valueArray;
    }
   /**
    * 
    * @param valueArray 
    */
    public void setValueArray(boolean[] valueArray) {
        this.valueArray = valueArray;
    }
   /**
    *  ???????????????????? is this method is ok? 
    * @param index
    * @return 
    */
   public boolean getLimbValueAtIndex(int index)
   {

        //return valueArray[index];
        return valueArray[index-startPosition];
   
   
   }
   
   
   /**
    * ???????????????????? is this method is ok? 
    * @param index
    * @param value 
    */
   public void setLimbValueAtIndex(int index, boolean value)
   {
       
       this.valueArray[index-startPosition]=value;

   }
    
    
    
}
