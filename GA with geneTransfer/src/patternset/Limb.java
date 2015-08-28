/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;

import java.util.ArrayList;

/** 
 *
 * @author Hasib
 */
public class Limb {
    
    private int sizeOfLimb;
    private int startPosition;
    private int endPosition;
    private Individual individualLimb;
    private int scoreOfLimb;
    
    public Limb(int sizeOfLimb , Individual individual)
    {
        this.sizeOfLimb=sizeOfLimb;
        this.startPosition=Utility.randomNoGenerator.nextInt(Utility.numberOfItems-this.sizeOfLimb);
        this.endPosition=startPosition+sizeOfLimb-1;       
        this.individualLimb= new Individual();
       
        for(int patternSetNo=0;patternSetNo<individual.getSize();patternSetNo++)
            for(int itemIndex=startPosition;itemIndex<=endPosition;itemIndex++)
            {
                boolean boolValue=individual.getPatternSetnAtIndex(patternSetNo).getItemAtIndex(itemIndex);
                individualLimb.getPatternSetnAtIndex(patternSetNo).setItemAtIndex(itemIndex,boolValue);
            }
         
        individualLimb.setIndividualScoe(individualLimb.getXOR_ObjectiveScore());
        this.scoreOfLimb=individualLimb.getIndividualScoe();
        
        
    }

    
    public Limb(Limb givenLimb)
    {
        this.sizeOfLimb=givenLimb.getSizeOfLimb();
        this.startPosition=givenLimb.startPosition;
        this.endPosition=givenLimb.endPosition;
        this.individualLimb=new Individual(individualLimb);
        this.scoreOfLimb=givenLimb.sizeOfLimb;
    }
    

    
    
    public Individual getIndividualLimb() {
        return individualLimb;
    }

    public void setIndividualLimb(Individual individualLimb) {
        this.individualLimb = individualLimb;
    }

    
    
    
    public int getSizeOfLimb() {
        return sizeOfLimb;
    }

    public void setSizeOfLimb(int sizeOfLimb) {
        this.sizeOfLimb = sizeOfLimb;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getScoreOfLimb() {
        return this.scoreOfLimb;
    }

    public void setScoreOfLimb(int scoreOfLimb) {
        this.scoreOfLimb = scoreOfLimb;
    }
    
  
    
    
    
}
