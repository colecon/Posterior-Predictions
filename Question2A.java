//Cole Constantino | Posterior Data Calculations
import java.util.*;
import java.text.DecimalFormat;
public class Question2A{
  
  static double[] hypos = {1, .75, .5, .25, 0};
  static double[] priors = {.1, .2, .4, .2, .1};
  static List<List<Double>> posteriorData = new ArrayList<>();
  static int[] lineData = {};
  
  //generate random coin flip data given index of chosen h and n number of samples
  public static int[] randomData(int hIndex, int n){
    double h = hypos[hIndex];
    int[] lineData = new int[n];
    Random r = new Random();
    
    for(int i = 0; i < n;i++){
      lineData[i] = r.nextDouble() >= h ? 1 : 0;
    }
    return lineData;
  }
  
  public static void findProbabilities(int n){
    double[] iPosteriors = new double[5];
    DecimalFormat df = new DecimalFormat("0.000000000");
    
    for(int i = 0; i < hypos.length;i++){
      double h = hypos[i];
      double prior = priors[i];
      //init prob is just the prior
      double probH = prior;

      
      for(int j = 0; j < n; j++){
        int d = lineData[j];
        double likely = d == 1 ? h : 1 - h;
        //bayes rule
        probH = probH * likely;
      }
      //add to certain posterior array data
      iPosteriors[i] = probH;
    }
     
    //normalize data
    iPosteriors = normalize(iPosteriors);
    
    for(int x = 0; x < iPosteriors.length; x++){
      posteriorData.get(x).add(Double.valueOf(df.format(iPosteriors[x])));
      priors[x] = iPosteriors[x];
    }
    
    
  }
  
  //divide every element in array by the sum
  public static double[] normalize(double[] a){
    double sum = 0;
    for(int i = 0; i < a.length; i++) sum = sum + a[i];
    for(int j = 0; j < a.length; j++) a[j] = a[j]/sum; 
    
    return a;
  }
  
  

  public static void main(String[] args){
    posteriorData.add(new ArrayList<Double>());
    posteriorData.add(new ArrayList<Double>());
    posteriorData.add(new ArrayList<Double>());
    posteriorData.add(new ArrayList<Double>());
    posteriorData.add(new ArrayList<Double>());
    posteriorData.get(0).add(priors[0]);
    posteriorData.get(1).add(priors[1]);
    posteriorData.get(2).add(priors[2]);
    posteriorData.get(3).add(priors[3]);
    posteriorData.get(4).add(priors[4]);
    //x10
    int n = 10;
    lineData = randomData(3,n);
    for(int x = 0; x < n; x++) findProbabilities(n);
    
    for(int i = 0; i < 5; i++)
      System.out.println("h" + (i+1) + " = " + posteriorData.get(i) + "\n");
    
  }
}