/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package patternset;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import jdk.nashorn.internal.codegen.CompilerConstants;
/**
 *
 * @author Hasib
 */
public class PatternSet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException  {
        // TODO code application logic here
           
        String path="./mushroom.txt";
        //       String path="./TicTacToe.txt";
        try{
        
            FileRead fileRead = new FileRead(path);
            fileRead.openFile();
            
            for (ItemSet itemSet:fileRead.database) {  // d gets successively each value in ar.
                System.out.println(itemSet+" "+itemSet.getTransactionType());
                //boolean[] transactionSetPrint=t.getTransactionSet();                
            }
        
 
          
//      int a= randomWalk();
//      int b= randomWalk();
//      int c= randomWalk();
//      int d=randomWalk();
//      int e=randomWalk();
//      
    // System.out.println("a="+a+" b="+b+" c="+c+" d="+d+" e="+e);
   //   simulatedAnnealing();
//       algorithm1();        
  
            
// 
            
int a=0,b=0,c=0,d=0,e=0;
int a2=0,b2=0,c2=0,d2=0,e2=0;
            
// GeneticAlgorithm ga_1= new GeneticAlgorithm();
//   int  a=ga_1.evolve_time();
//            System.out.println("output   "+a);

            
    GeneticAlgorithm ga_1= new GeneticAlgorithm();
    a=ga_1.evolve_time();

    GeneticAlgorithm ga_2= new GeneticAlgorithm();
    b=ga_2.evolve_time();
    
    GeneticAlgorithm ga_3= new GeneticAlgorithm();
    c=ga_3.evolve_time();

    GeneticAlgorithm ga_4= new GeneticAlgorithm();
    d=ga_4.evolve_time();
    
    GeneticAlgorithm ga_5= new GeneticAlgorithm();
    e=ga_5.evolve_time();

System.out.println(a+"  "+b+"  "+c+"  "+d+"  "+e);
        
/*
    a=largeNeigbourhoodSeach();
    b=largeNeigbourhoodSeach();
    c=largeNeigbourhoodSeach();
    d=largeNeigbourhoodSeach();
    e=largeNeigbourhoodSeach();

            */ 
     
    
     //  hillClimbingWithCreatingSingleNeighbor();

    
//    System.out.println("a="+a+"    b="+b+"     c="+c+"    d= "+d+"      e="+e);


 //   a1=randomWalk();
//    b1=randomWalk();
//    c1=randomWalk();
//    d1=randomWalk();
//    e1=randomWalk();
//    
 //   a2=hillClimbingWithCreatingSingleNeighbor();
//    b2=hillClimbingWithCreatingSingleNeighbor();
//    c2=hillClimbingWithCreatingSingleNeighbor();
//    d2=hillClimbingWithCreatingSingleNeighbor();
//    e2=hillClimbingWithCreatingSingleNeighbor();



//    System.out.println("random walk:  "+a1+" "+b1+" "+c1+" "+d1+" "+e1);
 //   System.out.println("hillClimb: "+a2+" "+b2+" "+c2+" "+d2+" "+e2);

   // System.out.println(" "+a+" "+b);
   // System.out.println(" "+a+" "+b);

//    GeneticAlgorithm ga_1= new GeneticAlgorithm();
//    int a=ga_1.evolve_time();
    Sound.SoundClass.sound();  
    
////            
//ga.testMethod();
//
//  
  
            
            
            
        
        
    }
        catch(IOException io)
        {
            System.out.println(io.getMessage());
        }
    
    }
    
    
    
    /**
     *  method for random walk  
     */
    public static int randomWalk()
    { 
        int bestScoreForParameter=0;
        int noOfMinToRunTheCode=(int)Utility.timeToRunCode;///////////////////////////////////////hv to be global///////////////////////////
        Individual bestIndividual=new Individual();        
        long startTime = System.currentTimeMillis();
        int count=1;
        while (count<13)
        {
            Individual currentIndividual=new Individual();
            currentIndividual.initializeRelRandom();
            currentIndividual.setIndividualScoe(currentIndividual.getXOR_ObjectiveScore());  
            System.out.println("current indi "+currentIndividual.getIndividualScoe()+" best indi  "+bestIndividual.getIndividualScoe());
            System.out.println(" current   "+currentIndividual.getPatternSet());
            
            
            
            Limb limb= new Limb(5,currentIndividual);
            System.out.println("limb score   "+limb.getScoreOfLimb());
            System.out.println("size of limb "+limb.getSizeOfLimb());
            System.out.println("start pos    "+limb.getStartPosition());
            System.out.println("end pos      "+limb.getEndPosition());
            System.out.println("indi limb    "+limb.getIndividualLimb());
            System.out.println("limb         "+limb.getIndividualLimb().getPatternSet());
            Individual currentIndi=new Individual();
            currentIndi.initializeRelRandom();
            System.out.println("old          "+currentIndi.getPatternSet());
            currentIndi.addLimb(limb);
            System.out.println("new          "+currentIndi.getPatternSet());
            System.out.println("score          "+currentIndi.getIndividualScoe());            
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            
            if(currentIndividual.getIndividualScoe()>bestIndividual.getIndividualScoe() && !currentIndividual.isAnyPatternZero())
            {
                System.out.println("change  patternset   "+currentIndividual.isAnyPatternZero()); 
                bestIndividual= new Individual(currentIndividual);            
                bestScoreForParameter=bestIndividual.getIndividualScoe();
            }                   
            
            
            boolean bVal=  ((System.currentTimeMillis()-startTime)> noOfMinToRunTheCode*4*1000)  && ((System.currentTimeMillis()-startTime) <= noOfMinToRunTheCode*6*1000)    ;
            if(bVal==true)
            {
                switch (count)
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
                        System.out.println("SCORE   "+bestIndividual.getIndividualScoe()+" count = "+count); 
                       
                        Scanner in = new Scanner(System.in);
                        in.nextLine();
                        count++;
                        startTime=System.currentTimeMillis();
                        break;
                }
            
            }

            
            
            
                // System.out.println("\n\n");

//              Scanner in = new Scanner(System.in);
//                  in.nextLine();

         }
   
   // return bestIndividual.getIndividualScoe();
    
        return bestScoreForParameter;
    }   
    /**
     * method for hill climbing algorithm
     */
    public static int hillClimbingWithCreatingSingleNeighbor()
    {
        int bestScoreForParameter=0;
        int noOfMinToRunTheCode=(int)Utility.timeToRunCode;;
        Random randdomNoGenerator = new Random(); 
        
        Individual bestIndividual;
        do
        {
            bestIndividual=new Individual();
            bestIndividual.initializeRelRandom();
        }
        while(bestIndividual.isAnyPatternZero());       
        
        
        
        bestIndividual.setIndividualScoe(bestIndividual.getXOR_ObjectiveScore());
        System.out.println("score   "+bestIndividual.isAnyPatternZero()); 

        bestScoreForParameter=bestIndividual.getIndividualScoe();
        
                int count=1;

        long startTime = System.currentTimeMillis();
        while (count<13)
        {
            Individual currentIndividual=new Individual(bestIndividual);            
            currentIndividual.flipSingleBitRandomly();
            currentIndividual.setIndividualScoe(currentIndividual.getXOR_ObjectiveScore());                        

            System.out.println("current indi "+currentIndividual.getIndividualScoe()+" best indi  "+bestIndividual.getIndividualScoe());
            System.out.println("set:  "+bestIndividual.getPatternSet());
            
            if(currentIndividual.getIndividualScoe()>bestIndividual.getIndividualScoe()&& !currentIndividual.isAnyPatternZero())
            {//muktadir uses >= in if condition, should i do the same?
                
                System.out.println("change  patternset   "+currentIndividual.isAnyPatternZero()); 
                bestIndividual=new Individual(currentIndividual);
                bestScoreForParameter=bestIndividual.getIndividualScoe();
                
            }

            boolean bVal=  ((System.currentTimeMillis()-startTime)> noOfMinToRunTheCode*4*1000)  && ((System.currentTimeMillis()-startTime) <= noOfMinToRunTheCode*6*1000)    ;
            if(bVal==true)
            {
                switch (count)
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
                        System.out.println("SCORE   "+bestIndividual.getIndividualScoe()+" count = "+count); 
                       
                        Scanner in = new Scanner(System.in);
                        in.nextLine();
                        count++;
                        startTime=System.currentTimeMillis();
                        break;
                }
            
            }

            
            
            
            
            
            
            
            
            
            
            
            
            //Scanner in = new Scanner(System.in);
//                  in.nextLine();       
        }   
        return bestScoreForParameter;
    }
    
    /**
     * method for simulatedAnnealing
     */
    public static void simulatedAnnealing()
    {
        
        int noOfMinToRunTheCode=(int)Utility.timeToRunCode;;///////////////////////////////////////hv to be global///////////////////////////
                //initialization

        double temp=1,alpha=.95;
        temp=temp*alpha;
        
        Individual bestIndividual=new Individual();        
        Individual currentIndividual;        
        do
        {
            currentIndividual=new Individual();
            currentIndividual.initializeRelRandom();
        }
        while(currentIndividual.isAnyPatternZero());       
        currentIndividual.setIndividualScoe(currentIndividual.getXOR_ObjectiveScore());
        bestIndividual=new Individual(currentIndividual);
        
        
        int terminationCount=0; /////********??????????termination condition for successive steps///////////////////////////////
        int noOfStep=100;
        
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis()-startTime)< noOfMinToRunTheCode*60*1000)
        {
            double R= Utility.randomNoGenerator.nextDouble();
            currentIndividual.flipSingleBitRelRandomly();
            currentIndividual.setIndividualScoe(currentIndividual.getXOR_ObjectiveScore());
            System.out.println("currIndividual "+currentIndividual.getPatternSet()+"\tscore  "+currentIndividual.getIndividualScoe());
            System.out.println("BestIndividual "+bestIndividual.getPatternSet()+"\tscore  "+bestIndividual.getIndividualScoe());
            System.out.println("");        
            if(currentIndividual.getIndividualScoe()>bestIndividual.getIndividualScoe()&&!currentIndividual.isAnyPatternZero())
            {
                bestIndividual= new Individual(currentIndividual);  
                terminationCount=0;
            }               
            else
            {
                terminationCount++;
                if(terminationCount>noOfStep)
                    alpha=Utility.randomNoGenerator.nextDouble();
                
                int currScore=currentIndividual.getIndividualScoe();
                int bestSore=bestIndividual.getIndividualScoe();       
                double val = Math.exp((-1*(bestSore-currScore))/temp);
                temp=temp*alpha;
                if(val>R)
                    bestIndividual= new Individual(currentIndividual);             
            }
//            Scanner in = new Scanner(System.in);
//                  in.nextLine();
        }
    }
    
    
    
    /**
     * Large neighbourhood seach
     * 
     */
    public static int largeNeigbourhoodSeach()
    {
       int bestScoreToReturn=0;//*****************
       int noOfMinToRunTheCode=(int)Utility.timeToRunCode;
       int sizeOfNeighbour=1;
       int countToUpdateSizeOfNeighbour=0;
       int maxCountToUpdate=100;
       long startTime = System.currentTimeMillis();
        
       Individual bestIndividual;
        do
        {
            bestIndividual=new Individual();
            bestIndividual.initializeRelRandom();
        }
        while(bestIndividual.isAnyPatternZero());         
       bestIndividual.setIndividualScoe(bestIndividual.getXOR_ObjectiveScore());
       
       
       
        int count=1;

        while (count<13)
       {
    
           ArrayList<Individual> neighbourListIndividual= new ArrayList<Individual>();
           
           //creaying neighbour
           neighbourListIndividual=bestIndividual.LNS_Operator(bestIndividual.createNeighborOperatorForIndividual(sizeOfNeighbour));
          
          //deleting neighbour who has null pattern set in individual
          int indexToRemoveIndividual=0; 
          int size=neighbourListIndividual.size();
          for(int i=0;i<size;i++)
          {
              if(neighbourListIndividual.get(indexToRemoveIndividual).isAnyPatternZero()==true)
                  neighbourListIndividual.remove(indexToRemoveIndividual);
              else
                  indexToRemoveIndividual++;
          }
           
          
                    
          Individual bestIndividualInNeighbors= new Individual(Utility.findMaxIndividual(neighbourListIndividual));
           System.out.println("size "+neighbourListIndividual.size()+"  current score "+bestIndividualInNeighbors.getIndividualScoe()+"best score "+bestIndividual.getIndividualScoe()+"sizeOfNeighbour:  "+sizeOfNeighbour);
            //   System.out.println("\t\t "+bestIndividual.getPatternSet()+"score "+bestIndividual.getIndividualScoe());

            //printing the member of neighbour
           System.out.println("***********************************************************************start");
           for(int j=0;j<neighbourListIndividual.size();j++)
           {
           
               System.out.println("\t\t "+neighbourListIndividual.get(j).getPatternSet()+"score "+neighbourListIndividual.get(j).getIndividualScoe());
           }
           
           System.out.println("*******************************************************************************finish");
           if(bestIndividualInNeighbors.getIndividualScoe()>bestIndividual.getIndividualScoe()) 
          {
              System.out.println("change ");
              bestIndividual=new Individual(bestIndividualInNeighbors);
              countToUpdateSizeOfNeighbour=0;
              bestScoreToReturn=bestIndividual.getIndividualScoe();//********************
          }
          else
          {
              countToUpdateSizeOfNeighbour++;
          }
           
          if(countToUpdateSizeOfNeighbour>maxCountToUpdate)
          {
              sizeOfNeighbour++;
              System.out.println("****************************** "+sizeOfNeighbour);
              countToUpdateSizeOfNeighbour=0;
          }
              
//        Scanner in = new Scanner(System.in);
//                  in.nextLine();
//        
          
          
            boolean bVal=  ((System.currentTimeMillis()-startTime)> noOfMinToRunTheCode*4*1000)  && ((System.currentTimeMillis()-startTime) <= noOfMinToRunTheCode*6*1000)    ;
            if(bVal==true)
            {
                switch (count)
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
                        System.out.println("SCORE   "+bestIndividual.getIndividualScoe()+" count = "+count); 
                       
                        Scanner in = new Scanner(System.in);
                        in.nextLine();
                        count++;
                        startTime=System.currentTimeMillis();
                        break;
                }
            
            }

            
          
          
          
          
          
       }
    
    return bestScoreToReturn; //**************
    }
    
    
    /**
     * algrithm1
     */
    public static void algorithm1()
    {
        int noOfMinToRunTheCode=(int)Utility.timeToRunCode;;
         Random randdomNoGenerator = new Random();
         
        Individual individual=new Individual();
        
//        individual.initializeRandom();
        individual.initializeRelRandom();
        System.out.println("");

        
        
        int currentBestScore=individual.getObjectiveScore();
        
        int newScore;
        int count=0;
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis()-startTime)< noOfMinToRunTheCode*60*1000)
        {
            
            Individual individual_=new Individual(individual);
            System.out.println("before_individual_: "+individual_.getPatternSet());
            
            individual_.flipSingleBitRandomly();
            System.out.println("after_individual_ : "+individual_.getPatternSet());
            
            newScore=individual_.getObjectiveScore();
            System.out.println("new score "+newScore+"  curret bestscore:  "+currentBestScore+" temp Score:"+individual_.getObjectiveScore());
            
            
            Scanner in = new Scanner(System.in);
                  in.nextLine();
                    
            if(newScore>=currentBestScore)
            {
                currentBestScore=newScore;
                individual=new Individual(individual_);
            }
            
        }        
        
        
        
    }
}
