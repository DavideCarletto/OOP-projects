package ArrayFrequencies;

import java.util.ArrayList;
import java.util.Collections;

// You are given an array nums consisting of positive integers.
// Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
// The frequency of an element is the number of occurrences of that element in the array.

public class Main {
    
    public void fillArray(ArrayList<Integer> array, int n){
        for(int i=0; i<n; i++){
            int x = (int) ((Math.random() * (10 - 1)) + 1);
            array.add(x);
        }
    }

    public void printArray(ArrayList<Integer> array){
        System.out.print("Array:[ ");
        for(Integer element: array){
            System.out.print(element+" ");
        }
        System.out.println("]");
    }

    public int maxFrequency(ArrayList<Integer> array){
        int maxF=0, currentF=1, tot=0;
        ArrayList<Integer> frequencies  = new ArrayList<Integer>();

        Collections.sort(array);
    
        for(int i=1; i<array.size();i++){

            if(array.get(i) == array.get(i-1)){
                currentF++;
            }else{
                frequencies.add(currentF);
                currentF = 1;
            }
        }
        frequencies.add(currentF);

        maxF = Collections.max(frequencies);

        for(int frequency: frequencies){
            System.out.print(frequency+", ");
            if(frequency == maxF){
                tot+=frequency;
            }
        }

        return tot;
    }
    public static void main(String[] args) {
        
        ArrayList<Integer> array = new ArrayList<Integer>();
        Main main = new Main();
        
        main.fillArray(array, 5);
        main.printArray(array);
        
        System.out.println("Total frequencies of elements with maximum frequency: " + main.maxFrequency(array));
    }
}
