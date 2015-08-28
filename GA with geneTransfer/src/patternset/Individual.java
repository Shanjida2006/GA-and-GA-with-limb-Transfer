/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patternset;

//import java.time.Clock;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Hasib
 * @param ArrayList<ItemSet> patternSet
 * @param int individualScore
 *
 *
 */
public class Individual {

    private ArrayList<ItemSet> patternSet;
    private int individualScore;

    public int getIndividualScoe() {

        return individualScore;
    }

    public void setIndividualScoe(int individualScoe) {
        this.individualScore = individualScoe;
    }

    
    /**
     * adding limb in a individual
     * 
     */
    public void addLimb(Limb limb)
    {     
        for(int patternSetNo=0;patternSetNo<Utility.noOfPatternSetInEachIndividuals;patternSetNo++)
            for(int itemIndex=limb.getStartPosition();itemIndex<limb.getEndPosition()-1;itemIndex++)
            {
                boolean boolValue=limb.getIndividualLimb().getPatternSetnAtIndex(patternSetNo).getItemAtIndex(itemIndex);
                getPatternSetnAtIndex(patternSetNo).setItemAtIndex(itemIndex,boolValue);
            }
        setIndividualScoe(getXOR_ObjectiveScore());
    }
    
    
    /**
     * default constractor
     */
    public Individual() {
        patternSet = new ArrayList<ItemSet>(Utility.noOfPatternSetInEachIndividuals);
        for (int i = 0; i < Utility.noOfPatternSetInEachIndividuals; i++) {
            patternSet.add(new ItemSet());
        }

        individualScore = 0;

    }

    /**
     * constructor
     *
     * @param noOfPatternSet create a individual with given no of patternSet
     */
    public Individual(int noOfPatternSet) {
        patternSet = new ArrayList<ItemSet>(noOfPatternSet);
        for (int i = 0; i < noOfPatternSet; i++) {
            patternSet.add(new ItemSet());
        }

        individualScore = 0;
    }

    /**
     * copy constructor
     *
     * @param other
     */
    public Individual(Individual other) {

        patternSet = new ArrayList<ItemSet>(other.getSize());
        for (int i = 0; i < other.getSize(); i++) {
            ItemSet iNew = new ItemSet(other.getPatternSetnAtIndex(i));
            patternSet.add(iNew);
        }
        individualScore = other.getIndividualScoe();
    }
    
    
    public boolean equalIndividual(Individual other) {

        //patternSet = new ArrayList<ItemSet>(other.getSize());
        for (int i = 0; i < other.getSize(); i++) {
            
            for(int j=0;j<other.getPatternSetnAtIndex(i).getSize();j++)
                if(other.getPatternSetnAtIndex(i).getItemAtIndex(j)!= patternSet.get(i).getItemAtIndex(j))
                    return false;
        }
       return true;
    }
    

    /**
     * @return arrayList<ItemSet>
     */
    public ArrayList<ItemSet> getPatternSet() {
        return patternSet;
    }

    /**
     * @return no of patternSet in a individual.
     */
    public int getSize() {
        return patternSet.size();
    }

    /**
     *
     * @param index
     * @return ItemSet for a particular index
     */
    public ItemSet getPatternSetnAtIndex(int index) {
        return patternSet.get(index);

    }

    /**
     * set the value of itemset true for given index
     *
     * @param indexofItemSet
     * @param itemset
     */
    public void setPatternSetnAtIndex(int indexInItemSet, ItemSet itemset) {
        itemset.setItemAtIndex(indexInItemSet, true);
    }

    /**
     * set the value in itemset for given index
     *
     * @param indexInItemSet
     * @param itemset
     * @param value
     */
    public void setPatternSetnAtIndex(int indexInItemSet, ItemSet itemset, boolean value) {
        itemset.setItemAtIndex(indexInItemSet, value);
    }

     //if value is either true or false,  use in initializeRandom method
    /**
     * randomly initialize a individual
     */
    public void initializeRandom() {
        for (ItemSet pSet : patternSet) {
            int noOfCgangeInEachPattern;
            for (int i = 0; i < pSet.getSize(); i++) //for each value of arraylist
            {
                noOfCgangeInEachPattern = Utility.randomNoGenerator.nextInt(Utility.numberOfItems);
                for (int j = 0; j < noOfCgangeInEachPattern; j++) {
                    pSet.setItemAtIndex(Utility.randomNoGenerator.nextInt(Utility.numberOfItems), Utility.randomNoGenerator.nextBoolean());
                }
            }
        }

    }

    /**
     * Randomly initialize with relativity
     */
    public void initializeRelRandom() {
        for (ItemSet pSet : patternSet) {
            ///  int  noOfCgangeInEachPattern=Utility.randomNoGenerator.nextInt(FileRead.itemRel.size()); /*     shouldnt we do it randomly?          */
            for (int k = 0; k < FileRead.itemRel.size(); k++) {
                if (Utility.randomNoGenerator.nextBoolean() == false) {
                    continue;
                }
                ArrayList<Integer> itemArray = FileRead.itemRel.get(k);
                int itemIndex = itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size()));
                pSet.setItemAtIndex(itemIndex, true);
            }

        }
    }

    /**
     * flip a single bit randomly with relatively
     */
    void flipSingleBitRelRandomly() {
        for (ItemSet pSet : patternSet) {
            //it will randomly give a index form itemRel size. which ll we use to get a list from itemRel
            int randNo = Utility.randomNoGenerator.nextInt(FileRead.itemRel.size());
            ArrayList<Integer> itemArray = FileRead.itemRel.get(randNo);
            int indexPreviouslyContainTrueVal = -1;
            for (int i = 0; i < itemArray.size(); i++) {
                if (pSet.getItemAtIndex(itemArray.get(i)) == true) {
                    indexPreviouslyContainTrueVal = itemArray.get(i);
                    break;
                }

            }
            if (indexPreviouslyContainTrueVal == -1) {
                pSet.setItemAtIndex(itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size())), true);
            } else {
                int indexPreviouslyContainFalseVal = itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size()));
                while (indexPreviouslyContainTrueVal == indexPreviouslyContainFalseVal && itemArray.size() > 1) {
                    indexPreviouslyContainFalseVal = itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size()));
                }
                pSet.setItemAtIndex(indexPreviouslyContainFalseVal, true);
                pSet.setItemAtIndex(indexPreviouslyContainTrueVal, false);
            }

        }
    }

    /**
     * flip a single bit randomly
     */
   
 /**   
    void flipSingleBitRandomlyGreedy() {
        int patternIndex = Utility.randomNoGenerator.nextInt(getSize());
        int itemIndex = Utility.randomNoGenerator.nextInt(Utility.numberOfItems);
        ((ItemSet) patternSet.get(patternIndex)).flipItemAtIndex(itemIndex);
    }

    void flipSingleBitRelRandomlyGreedy() {
        for (ItemSet pSet : patternSet) {
            //it will randomly give a index form itemRel size. which ll we use to get a list from itemRel
            int randNo = Utility.randomNoGenerator.nextInt(FileRead.itemRel.size());
            ArrayList<Integer> itemArray = FileRead.itemRel.get(randNo);
            int indexPreviouslyContainTrueVal = -1;
            for (int i = 0; i < itemArray.size(); i++) {
                if (pSet.getItemAtIndex(itemArray.get(i)) == true) {
                    indexPreviouslyContainTrueVal = itemArray.get(i);
                    break;
                }

            }
            if (indexPreviouslyContainTrueVal == -1) {
                pSet.setItemAtIndex(itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size())), true);
            } else {
                int indexPreviouslyContainFalseVal = itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size()));
                while (indexPreviouslyContainTrueVal == indexPreviouslyContainFalseVal && itemArray.size() > 1) {
                    indexPreviouslyContainFalseVal = itemArray.get(Utility.randomNoGenerator.nextInt(itemArray.size()));
                }
                pSet.setItemAtIndex(indexPreviouslyContainFalseVal, true);
                pSet.setItemAtIndex(indexPreviouslyContainTrueVal, false);
            }

        }
    }
*/
    
    /**
     * greedy sleep 
     * 
     */
    
    void flipSingleBitRandomly() {
        int patternIndex = Utility.randomNoGenerator.nextInt(getSize());
        int itemIndex = Utility.randomNoGenerator.nextInt(Utility.numberOfItems);
        ((ItemSet) patternSet.get(patternIndex)).flipItemAtIndex(itemIndex);
    }

    
    
    
    
    
    
    
    /**
     * LNS_Operator
     *
     * @param index type ArrayList<ArrayList<Integer>> 
     * @
     * return ArrayList<Individual>
     */
    public ArrayList<Individual> LNS_Operator(ArrayList<ArrayList<Integer>> index) {
        ArrayList<Individual> individualList = new ArrayList<Individual>();
        individualList.add(this);
        int patternIndex = 0, itemIndex = 0;

        for (ArrayList aList : index) {
            patternIndex = (int) aList.get(0);
            itemIndex = (int) aList.get(1);

            int size = individualList.size();
            for (int i = 0; i < size; i++) {
                Individual individual = new Individual(individualList.get(i));
                //?????should i keep this or below?  individual.setPatternSetnAtIndex(itemIndex, individual.getPatternSetnAtIndex(patternIndex),!individual.getPatternSetnAtIndex(patternIndex).getItemAtIndex(itemIndex));               
                individual.getPatternSetnAtIndex(patternIndex).flipItemAtIndex(itemIndex);
                individual.setIndividualScoe(individual.getXOR_ObjectiveScore());
                individualList.add(individual);

            }
        }

        return individualList;
    }

    /**
     * create neighbour for individual
     *
     * @return ArrayList<ArrayList<Integer>>
     */
    public ArrayList<ArrayList<Integer>> createNeighborOperatorForIndividual(int noOfNeighbour) {

        ArrayList<ArrayList<Integer>> neighborsArray = new ArrayList<ArrayList<Integer>>();
      //  System.out.println("Utility.numberOfItems:   " + Utility.numberOfItems);

        for (int i = 0; i < noOfNeighbour; i++) {
            ArrayList<Integer> arrayInt = new ArrayList<Integer>();
            Integer patternNoInINdividual = Utility.randomNoGenerator.nextInt(Utility.noOfPatternSetInEachIndividuals);
            arrayInt.add(patternNoInINdividual);
            Integer itemIndex = Utility.randomNoGenerator.nextInt(Utility.numberOfItems);
            arrayInt.add(itemIndex);

            neighborsArray.add(arrayInt);
        }

        return neighborsArray;
    }

    /**
     * method for finding objectivescore using formula from paper given by sir
     *
     * @return objectiveScore
     */
    int getObjectiveScore() {
        ArrayList<TransactionSet> transList = new ArrayList<TransactionSet>();
        System.out.println("in ob score ");
        System.out.println("patternset  " + patternSet);
        int i = 0;
        for (ItemSet itemSet : patternSet) {
            transList.add(Utility.getCoverage(itemSet));
            System.out.println("itemset  " + itemSet);
            System.out.println("tranlist " + transList.get(i).getSize());
            i++;
        }

        int score = Utility.getDispersionScore(transList);
        ///  setIndividualScoe(score);///setting the value .
        return score;
    }

    /**
     * method to find objectiveScore using XOR
     *
     * @return objectiveScore type int
     */
    int getXOR_ObjectiveScore() {

        ArrayList<TransactionSet> transList = new ArrayList<TransactionSet>();
//        System.out.println("in getXOR_ObjectiveScore ");
//        System.out.println("patternset  " + patternSet);
        int i = 0;
        for (ItemSet itemSet : patternSet) {
            transList.add(Utility.getCoverage(itemSet));
//            System.out.println("itemset  " + itemSet);
//            System.out.println("tranlist " + transList.get(i).getSize());
            i++;
        }

        int score = Utility.getXOR_DispersionScore(transList);
        return score;
    }



public boolean isAnyPatternZero()
{
    boolean boolValue= false;
    for(ItemSet iSet : patternSet)
    {
      int sizeOfiSet=iSet.getSize();
      int sumOFValue=0;
      for(int index=0;index<sizeOfiSet;index++)
      {
          if(iSet.getItemAtIndex(index)==true)
              sumOFValue++;
      
      }      
      if(sumOFValue==0)
      {
          boolValue=true;
          break;
      
      }
    
    }

 return boolValue;
}



}
