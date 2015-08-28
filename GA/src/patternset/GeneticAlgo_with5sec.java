
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;

import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Hasib
 */
public class GeneticAlgo_with5sec {

    private int noOfGeneration;
    private int populationSize;
    
    //********************************************??????????????????????????????///////////////////////////////////////
    //********************************************??????????????????????????????///////////////////////////////////////
    //********************************************??????????????????????????????///////////////////////////////////////
    public static int noOfGenerationBeforeRandomRestartInGA=100; //this variable gonna only use in GA for random restart
    //********************************************??????????????????????????????///////////////////////////////////////
    //********************************************??????????????????????????????///////////////////////////////////////
    //********************************************??????????????????????????????///////////////////////////////////////
    public static int percentOfChangeInPopulationForRandomRestart=90; 

    
    public GeneticAlgo_with5sec(int noOfGeneration, int populationSize)
    {
        this.noOfGeneration=noOfGeneration;
        this.populationSize=populationSize;
    }
    
    public GeneticAlgo_with5sec()
    {
        this.noOfGeneration=10;
        //********************************************??????????????????????????????///////////////////////////////////////
        //********************************************??????????????????????????????///////////////////////////////////////
        //********************************************??????????????????????????????///////////////////////////////////////
        this.populationSize=100;
    
    }

    public int getNoOfGeneration() {
        return noOfGeneration;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    
   
   
   
    int evolve_time() throws IOException
   {
   
       int noOfMinToRunTheCode=(int)Utility.timeToRunCode;
       long startTime = System.currentTimeMillis();
       System.out.println("\t\t\t ENTERING GA:");
      
       //this array safe all max for random restart; 
       ArrayList<Individual> maxPopulationListForRandomRestart= new ArrayList<Individual>(populationSize);     

       
        ArrayList<Individual> initialPopulationList= new ArrayList<Individual>(populationSize);     
        ArrayList<Individual> mutationPopulationList= new ArrayList<Individual>(populationSize); 
        ArrayList<Individual> crossOverPopulationList= new ArrayList<Individual>(populationSize);
        ArrayList<Individual> maxPopulationList= new ArrayList<Individual>(populationSize);
        
  
        //initialize all the population
        for(int i=0;i<populationSize;i++)
        {
            initialPopulationList.add(new Individual());
            mutationPopulationList.add(new Individual());
            crossOverPopulationList.add(new Individual());  
            maxPopulationList.add(new Individual());
        }
        
        //initializing initial population as random. and put the value to max also
        for(int i=0;i<populationSize;i++)
        {
            do{
                initialPopulationList.set(i, new Individual());
                initialPopulationList.get(i).initializeRelRandom();  /////////////////////////////////// 
                initialPopulationList.get(i).setIndividualScoe(initialPopulationList.get(i).getXOR_ObjectiveScore());
            }
            while(initialPopulationList.get(i).isAnyPatternZero());
            
            Individual indi=new Individual(initialPopulationList.get(i));

            if(!containValueInList(maxPopulationList,indi))
                maxPopulationList.set(i, new Individual(indi));
            else
                i--;
            
        }
        
        
        int count=1;
        int bestValueinGeneration=0;
        int bestvalue=0;
        int countToRandomRestart=1;
        
        int count_=1;
        while (count_<13)
        {         
            int countEqual=0;

             for(int index=0;index<maxPopulationList.size();index++)
            {
                int indexPlusOne=index+1;
                while(indexPlusOne<maxPopulationList.size())
                {
                    if(maxPopulationList.get(index).equalIndividual(maxPopulationList.get(indexPlusOne)))
                    {
                        countEqual++;
                    }
                    indexPlusOne++;
                }
            }

             
             /*
             * START OF MUTATION PROCESS
             */ 
             
              //  mutation_with_Lns(initialPopulationList, mutationPopulationList, maxPopulationList);
                simpleMutation(initialPopulationList, mutationPopulationList, maxPopulationList);

            // Utility.sizeOfLnsForMutation++;
                        
            
           /*
            * START OF CROSS OVER 
            */        
            for(int index=0;index<populationSize;index++)
            {      
                //take parent randomly and create child from them
                Individual parentMale=new Individual(initialPopulationList.get(Utility.randomNoGenerator.nextInt(populationSize-1)));
                Individual parentFemale;
                do
                {
                    parentFemale=new Individual(initialPopulationList.get(Utility.randomNoGenerator.nextInt(populationSize-1)));                
                }while(parentMale.equalIndividual(parentFemale));
                
                Individual offspringIndividual=new Individual(uniformCrossover(parentMale,parentFemale));//////////////////////////////
                offspringIndividual.setIndividualScoe(offspringIndividual.getXOR_ObjectiveScore());
                
                crossOverPopulationList.set(index,new Individual(offspringIndividual));                                
                //put value in max by checking if offspring's score is greater than the sore of min indiviual in max population list AND
                //if offspring has atleast 1 value in every patternset AND
                //if offspring has alread inserted in maxpopulation laist 
                int indexOfMinValInMaxList=Utility.findMinIndexOfIndividual(maxPopulationList);
                if(offspringIndividual.getIndividualScoe()>maxPopulationList.get(indexOfMinValInMaxList).getIndividualScoe()&& !offspringIndividual.isAnyPatternZero()&&!containValueInList(maxPopulationList,offspringIndividual))
                    maxPopulationList.set(indexOfMinValInMaxList,new Individual(offspringIndividual));                        
            
            }       
            
            
            //initialize initialPopulation list to maxPopulationList
            initialPopulationList.clear();
            for(int index=0;index<maxPopulationList.size();index++)
            {
                initialPopulationList.add(new Individual(maxPopulationList.get(index)));
            }
                        
            
            for(int i=0;i<maxPopulationList.size();i++)
                System.out.println("score  "+maxPopulationList.get(i).getIndividualScoe()+"indi  "+maxPopulationList.get(i).getPatternSet());

            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            System.out.println("*************************************");
            
            
             
            
            
            count++;
            
            /**
             * 
             * start of random restart********************************
             * 
             */
            countToRandomRestart++;
            if(Utility.findMaxIndividual(maxPopulationList).getIndividualScoe()>bestvalue)
            {
                bestvalue=Utility.findMaxIndividual(maxPopulationList).getIndividualScoe();
                bestValueinGeneration=count;
            }
            
            if(countToRandomRestart>bestValueinGeneration+noOfGenerationBeforeRandomRestartInGA)
            {
           
                
                System.out.println("#####################################");
                maxPopulationListForRandomRestart.add(new Individual(Utility.findMaxIndividual(maxPopulationList)));
                System.out.println("all time best value  "+bestvalue);
                System.out.println("currennt best value   "+Utility.findMaxIndividual(maxPopulationList).getIndividualScoe());
                System.out.println("*************************************");
           
                bestValueinGeneration=0;
                countToRandomRestart=0;
                randomRestart(percentOfChangeInPopulationForRandomRestart,initialPopulationList,maxPopulationList); 
            
            
//                Scanner in = new Scanner(System.in);
//                in.nextLine();

            }
            
            boolean bVal=  ((System.currentTimeMillis()-startTime)> noOfMinToRunTheCode*4*1000)  && ((System.currentTimeMillis()-startTime) <= noOfMinToRunTheCode*6*1000)    ;
            if(bVal==true)
            {
                switch (count_)
                {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        System.out.println("\n\n");
                        System.out.println("SCORE   "+bestvalue+" count = "+count_); 
                       
                        Scanner in = new Scanner(System.in);
                        in.nextLine();
                        count_++;
                        startTime=System.currentTimeMillis();
                        break;
                }
            
            }

            
            System.out.println("all time best value  "+bestvalue);

            
         
         
//                     Scanner in = new Scanner(System.in);
//                  in.nextLine();

        }/// end of  while((System.currentTimeMillis()-startTime)< noOfMinToRunTheCode*60*1000)

        
        
//        System.out.println("no of generation   "+count);
//        System.out.println("best gene:  "+bestValueinGeneration);
//        System.out.println("maximum Score: "+Utility.findMaxIndividual(maxPopulationList).getIndividualScoe());
//   
        
        System.out.println(" "+Utility.findMaxIndividual(maxPopulationList).getIndividualScoe()+"  "+count+"  "+bestValueinGeneration);

        //to add the last maxvalue in the array
        maxPopulationListForRandomRestart.add(new Individual(Utility.findMaxIndividual(maxPopulationList)));
                
        System.out.println("Scores for all max individual After random restart:  ");
        for(int i=0;i<maxPopulationListForRandomRestart.size();i++)
            System.out.println("score:  "+maxPopulationListForRandomRestart.get(i).getIndividualScoe());
        System.out.println("msx of all=  "+Utility.findMaxIndividual(maxPopulationListForRandomRestart).getIndividualScoe());
        
        
//        Sound.SoundClass.sound();
       return Utility.findMaxIndividual(maxPopulationListForRandomRestart).getIndividualScoe();
        
   }


  
    
    
public void simpleMutation(ArrayList<Individual> initialPopulationList,ArrayList<Individual> mutationPopulationList,ArrayList<Individual> maxPopulationList)
{
    for(int index=0;index<populationSize;index++)
    {
        //initialize mutation population                            
        mutationPopulationList.set(index,new Individual(initialPopulationList.get(index)));
        //mutating 
        mutationPopulationList.get(index).flipSingleBitRelRandomly();
        mutationPopulationList.get(index).setIndividualScoe(mutationPopulationList.get(index).getXOR_ObjectiveScore());

        int indexOfMinValInMaxList= Utility.findMinIndexOfIndividual(maxPopulationList);
          //updating maxList
        if(mutationPopulationList.get(index).getIndividualScoe()>maxPopulationList.get(indexOfMinValInMaxList).getIndividualScoe()&& !mutationPopulationList.get(index).isAnyPatternZero())
        {
                System.out.println("##############################################################################################################################################");

            if(!containValueInList(maxPopulationList,mutationPopulationList.get(index)))
           {     maxPopulationList.set(indexOfMinValInMaxList,new Individual(mutationPopulationList.get(index)));
       
                System.out.println("************************************************************************************************************************************");
//                  Scanner nxt = new Scanner(System.in);
//                 nxt.nextLine();

           }       
        }                      
                    ////System.out.println("after  mutationPopulationList:  "+mutationPopulationList.get(index).getPatternSet()+" getIndividualScoe "+mutationPopulationList.get(index).getIndividualScoe());
    }
    System.out.println("Mutation finish");
}

public void mutation_with_Lns(ArrayList<Individual> initialPopulationList,ArrayList<Individual> mutationPopulationList,ArrayList<Individual> maxPopulationList)
{
    for(int index=0;index<populationSize;index++)
    {
        //initialize mutation population                            
        mutationPopulationList.set(index,new Individual(initialPopulationList.get(index)));
        //mutating 
        mutationPopulationList.get(index).flipSingleBitRelRandomly();
         ArrayList<Individual> neighbourListIndividual= new ArrayList<Individual>();           
           //creaying neighbour
          neighbourListIndividual=mutationPopulationList.get(index).LNS_Operator(mutationPopulationList.get(index).createNeighborOperatorForIndividual(Utility.sizeOfLnsForMutation));          
          //deleting neighbour who has null pattern set in individual
          int indexToRemoveIndividual=0; 
          int size=neighbourListIndividual.size();
          for(int i_=0;i_<size;i_++)
          {
              if(neighbourListIndividual.get(indexToRemoveIndividual).isAnyPatternZero()==true)
                  neighbourListIndividual.remove(indexToRemoveIndividual);
              else
                  indexToRemoveIndividual++;
          }
          
        //take the max value from neighbours
        mutationPopulationList.set(index,new Individual(Utility.findMaxIndividual(neighbourListIndividual)));    
        mutationPopulationList.get(index).setIndividualScoe(mutationPopulationList.get(index).getXOR_ObjectiveScore());

        int indexOfMinValInMaxList= Utility.findMinIndexOfIndividual(maxPopulationList);
          //updating maxList
        if(mutationPopulationList.get(index).getIndividualScoe()>maxPopulationList.get(indexOfMinValInMaxList).getIndividualScoe()&& !mutationPopulationList.get(index).isAnyPatternZero())
        {
                System.out.println("##############################################################################################################################################");

            if(!containValueInList(maxPopulationList,mutationPopulationList.get(index)))
           {     maxPopulationList.set(indexOfMinValInMaxList,new Individual(mutationPopulationList.get(index)));
       
                System.out.println("************************************************************************************************************************************");
//                  Scanner nxt = new Scanner(System.in);
//                 nxt.nextLine();

           
           
           }
        
             
        
        }                      
                    ////System.out.println("after  mutationPopulationList:  "+mutationPopulationList.get(index).getPatternSet()+" getIndividualScoe "+mutationPopulationList.get(index).getIndividualScoe());
    }
    System.out.println("Mutation finish");
}


  
 public void randomRestart(int percentOfChange,ArrayList<Individual> populationList,ArrayList<Individual> maxPopulationList) 
 {
     int noOfchange=(int)((percentOfChange*populationSize)/100);
     //deleting node
    int indexForRemoveIndividual=0;
     for(int i=0;i<noOfchange;i++)
     {
         indexForRemoveIndividual=Utility.findMinIndexOfIndividual(populationList);
         populationList.remove(indexForRemoveIndividual);
         maxPopulationList.remove(indexForRemoveIndividual);
     }
    //initialize new node
     for(int i=0;i<noOfchange;i++)
     {
         Individual individual;
         do{
             individual=new Individual();
             individual.initializeRelRandom();
         }while(containValueInList(populationList, individual)|| individual.isAnyPatternZero());       
         populationList.add(new Individual(individual));
         maxPopulationList.add(new Individual(individual));
     } 
 }



//   
//   code below.......................
//   
   public Individual uniformCrossover(Individual i1,Individual i2)
   {
   
       Individual indiCrossover= new Individual();
       
//       System.out.println("i1 in unicrossover \t\t"+i1.getPatternSet());
//       System.out.println("i2 in unicrossover \t\t"+i2.getPatternSet());

       for(int indexOfPatternSet=0;indexOfPatternSet<Utility.noOfPatternSetInEachIndividuals;indexOfPatternSet++)
       {
           for(int index=0;index<Utility.numberOfItems;index++)
           {
              Boolean bool=Utility.randomNoGenerator.nextBoolean();
              if(bool==true && i1.getPatternSetnAtIndex(indexOfPatternSet).getItemAtIndex(index)==true)
                indiCrossover.setPatternSetnAtIndex(index, indiCrossover.getPatternSetnAtIndex(indexOfPatternSet));
              else if(bool==false && i2.getPatternSetnAtIndex(indexOfPatternSet).getItemAtIndex(index)==true)
                indiCrossover.setPatternSetnAtIndex(index,indiCrossover.getPatternSetnAtIndex(indexOfPatternSet));             
           }   
       }
//       System.out.println("indiCrossover   "+indiCrossover.getPatternSet());

       return indiCrossover;
   }
 
   
   public void testMethod()
   {
       ArrayList<Individual> listOfIndividual= new ArrayList<Individual>(populationSize);
       Individual indi=new Individual();
      indi.flipSingleBitRelRandomly();
       
       for(int i=0;i<populationSize;i++)
       {
           listOfIndividual.add(new Individual(indi));
           System.out.println(" "+listOfIndividual.get(i).getPatternSet());
       }
          System.out.println("*******************************");

       indi=new Individual();
       System.out.println(""+indi.getPatternSet());
       for(int i=0;i<populationSize;i++)
       {    
           listOfIndividual.set(i,new Individual());
           System.out.println(" "+listOfIndividual.get(i).getPatternSet());
       }
       
       System.out.println("  "+containValueInList(listOfIndividual, indi));
   
   
   }
   
   
   
   
   
   /**
    * 
    * return true if the value of an individual stays in a list; 
    * 
    * @param individualList
    * @param individual
    * @return 
    */
   boolean containValueInList(ArrayList<Individual> individualList, Individual individual)
   {
            boolean found=false;
            for(Individual listItem: individualList)
            {
                boolean unEqual=false;
                int index=0;
                for(ItemSet itemset: listItem.getPatternSet())
                {
                    for(int itemIndex=0;itemIndex<itemset.getSize();itemIndex++)
                        if(itemset.getItemAtIndex(itemIndex)!=individual.getPatternSetnAtIndex(index).getItemAtIndex(itemIndex))
                        {
                            unEqual = true;
                            break;
                        }         
                    if (unEqual)
                        break;
                    index++;
                }   
                if (unEqual==false)
                {
                    found =true;
                    break;
                }
                
            }
            return found;
   }
   
   
   

   
   
   
   public Individual uniformReCrossover(Individual i1,Individual i2)
   {
    int actualIndex;
       Individual indiReCrossover= new Individual();
       
//       System.out.println("\t\t******relative indi crossover************");
//       System.out.println("indi \t\t"+i1.getPatternSet());
//       System.out.println("indi \t\t"+i2.getPatternSet());
       int sizeOfItemRel=FileRead.itemRel.size();
       
       for(int indexForPatternSet=0;indexForPatternSet<Utility.noOfPatternSetInEachIndividuals;indexForPatternSet++)
       {     actualIndex=0;
           for(int i=0;i<FileRead.itemRel.size();i++)
            {   
                Boolean bool=Utility.randomNoGenerator.nextBoolean();
                for(int index=0;index<FileRead.itemRel.get(i).size() ;index++)
                {     
                    if(bool==true  && i1.getPatternSetnAtIndex(indexForPatternSet).getItemAtIndex(actualIndex)==true)
                        indiReCrossover.setPatternSetnAtIndex(actualIndex ,indiReCrossover.getPatternSetnAtIndex(indexForPatternSet));
                    else if (bool==false && i2.getPatternSetnAtIndex(indexForPatternSet).getItemAtIndex(actualIndex)==true)
                        indiReCrossover.setPatternSetnAtIndex(actualIndex,indiReCrossover.getPatternSetnAtIndex(indexForPatternSet));             
                    
                    actualIndex++;
                }   
            }
        }
       
    //   System.out.println("indiReCross     "+indiReCrossover.getPatternSet());

       return indiReCrossover;
   }
   
   
   
   
   
   
    void evolve()
   {
   
       long startTime = System.currentTimeMillis();
       long endTime=0;
       System.out.println("\t\t\t ENTERING GA:");
      
        ArrayList<Individual> initialPopulationList= new ArrayList<Individual>(populationSize);     
        ArrayList<Individual> mutationPopulationList= new ArrayList<Individual>(populationSize); 
        ArrayList<Individual> crossOverPopulationList= new ArrayList<Individual>(populationSize);
        ArrayList<Individual> maxPopulationList= new ArrayList<Individual>(populationSize);
        
  
        //initialize all the population
        for(int i=0;i<populationSize;i++)
        {
            initialPopulationList.add(new Individual());
            mutationPopulationList.add(new Individual());
            crossOverPopulationList.add(new Individual());  
            maxPopulationList.add(new Individual());
        }
        
        //initializing initial population as random. and put the value to max also
        for(int i=0;i<populationSize;i++)
        {
            initialPopulationList.get(i).initializeRandom();   
            initialPopulationList.get(i).setIndividualScoe(initialPopulationList.get(i).getXOR_ObjectiveScore());
           
            Individual indi=new Individual(initialPopulationList.get(i));
            maxPopulationList.set(i, indi);
        }
        
        
        int count=1;
        
        while(count<=noOfGeneration)
        {
        
            /*
             * START OF MUTATION PROCESS
             */ 
            for(int index=0;index<populationSize;index++)
            {
                    //initialize mutation population
                Individual individualForSet4= new Individual(initialPopulationList.get(index));
                mutationPopulationList.set(index,individualForSet4);
                    
                   //mutating 
                    mutationPopulationList.get(index).flipSingleBitRandomly();
                    mutationPopulationList.get(index).setIndividualScoe(mutationPopulationList.get(index).getXOR_ObjectiveScore());

                    int indexOfMinValInMaxList= Utility.findMinIndexOfIndividual(maxPopulationList);

                    //updating maxList
                    if(mutationPopulationList.get(index).getIndividualScoe()>maxPopulationList.get(indexOfMinValInMaxList).getIndividualScoe())
                    {
                        Individual in1=new Individual(mutationPopulationList.get(index));
                        maxPopulationList.set(indexOfMinValInMaxList, in1);
                    
                    }
                                 
                    ////System.out.println("after  mutationPopulationList:  "+mutationPopulationList.get(index).getPatternSet()+" getIndividualScoe "+mutationPopulationList.get(index).getIndividualScoe());
           }
       
           //end of mutation
            
            
           /*
            * START OF CROSS OVER
            * 
            */        
            for(int index=0;index<populationSize;index++)
            {      
                //take parent randomly and create child from them
                Individual parentMale=new Individual(initialPopulationList.get(Utility.randomNoGenerator.nextInt(populationSize)));
                Individual parentFemale=new Individual(initialPopulationList.get(Utility.randomNoGenerator.nextInt(populationSize)));
                Individual offspringIndividual=new Individual(uniformCrossover(parentMale,parentFemale));
                offspringIndividual.setIndividualScoe(offspringIndividual.getXOR_ObjectiveScore());
                
                
                
                Individual individualForSet_= new Individual(offspringIndividual);
//                //crossOverPopulationList.set(index, offspringIndividual);//kept it in crossover;
                crossOverPopulationList.set(index,individualForSet_);
                
                
                //put value in max
                int indexOfMinValInMaxList=Utility.findMinIndexOfIndividual(maxPopulationList);
                if(offspringIndividual.getIndividualScoe()>maxPopulationList.get(indexOfMinValInMaxList).getIndividualScoe())
                {
                   
                    Individual individualForSet1= new Individual(offspringIndividual);
//                    //maxPopulationList.set(indexOfMinValInMaxList,offspringIndividual);
                    maxPopulationList.set(indexOfMinValInMaxList,individualForSet1);                    
                }  
            }       
            
            //copy maxIndividual list population in  initialpopulationList
            System.out.println("\t\t\t\t\t\t\t\t\t\tMAX POPULATIONLIST :  "+count);
            
            
            for(int index=0;index<maxPopulationList.size();index++)
            {
                int indexPlusOne=index+1;
                while(indexPlusOne<maxPopulationList.size())
                {
                    if(maxPopulationList.get(index).equalIndividual(maxPopulationList.get(indexPlusOne)))
                        maxPopulationList.remove(indexPlusOne);
                    else
                        indexPlusOne++;
                }
            
            }
            for(int index=0;index<maxPopulationList.size();index++)
            {
                Individual individualForSet2= new Individual(maxPopulationList.get(index));
//                //initialPopulationList.set(index, maxPopulationList.get(index));
                 initialPopulationList.set(index, individualForSet2);
                 System.out.println(" "+individualForSet2.getIndividualScoe());
            
            }
                        
            count++;
        
        }
        
        
        endTime=System.currentTimeMillis()-startTime;
        System.out.println("END TIME   "+endTime);
        System.out.println("maximum Score: "+Utility.findMaxIndividual(maxPopulationList).getIndividualScoe());
   
   }
  
   
   
   
   
   
}
