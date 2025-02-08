import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Project1{
	public static void main(String args[]){

		ArrayList<Integer> list = new ArrayList<>();
		int maxValidFrequency = 0;

		// reading file and storing it in list
		try {
			File file = new File("input.txt");
			Scanner scanner = new Scanner(file);

			// since using arraylist no need to initialize size
			scanner.nextInt();

			// iterates through input and adds values to arraylist
			while(scanner.hasNextInt()){
				list.add(scanner.nextInt());
			}

			//sets the max valid frequency threshold
			if(list.size() % 2 == 0){

				// concluded that the max for even is not n/2 + 1 but rather
				// just n/2 
				maxValidFrequency = list.size()/2;
			}
			else{

				// and max frequency for odd is n/2+1
				maxValidFrequency = (list.size()/2)+1;
			}

			scanner.close();

		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}


		// easier to track frequency with hashmap
		HashMap<Integer, Integer> hashmap = new HashMap<>();

		// counting frequency
		for(int i : list){
			hashmap.put(i, hashmap.getOrDefault(i,0)+1);
		}

		// calling function to find highest frequency num
		int numWithMostFrequency = findMaxFrequencyKey(hashmap);

		// obtaining the highest frequency value
		int highestFrequency = hashmap.get(numWithMostFrequency);

		// checking if our most frequent is at or above the max valid frequency threshold
		if(highestFrequency >= maxValidFrequency){

			// loops to test if our most frequent is still above max valid frequency
			while(highestFrequency > maxValidFrequency){

				// tests if size of array is 1, no
				if(list.size()==1){
					break;
				}

				// sets the frequency to max valid frequency because we want to remove redundancies 
				hashmap.put(numWithMostFrequency, maxValidFrequency);

				// removes that same amount of elements from the array
				for(int j = 0; j < highestFrequency- maxValidFrequency; j++ ){
					list.remove(Integer.valueOf(numWithMostFrequency));
				}

				//getting new highest frequency: should be equal to ma
				highestFrequency = hashmap.get(numWithMostFrequency);

				//sets the maxfrequency again
				if(list.size() % 2 == 0){
					maxValidFrequency = list.size()/2;
				}
				else{
					maxValidFrequency = (list.size()/2)+1;
				}
			}

			// Tests if there is a number in the array that is greater than the max frequency number AND also one
			// that less than it. if there is, it guarantees that there will be a DOUBLE DECREASE in the zig zag. 
			//
			// For example: 1 1 1 1 1 2 3 4 5 represents an input where there is a max valid frequency and all numbers are 
			// 				greater than it
			// The Solution: 1 2 1 3 1 4 1 5 1 now, this solution is n-1, but thats becauses there is no DOUBLE DECREASE
			//  
			// Another example: 2 2 2 2 2 1 3 4 5 here there is a number that is below the most frequent and one that is above
			// The Solution: 2 5 2 4 2 3 2 1 2 you can see that there is a DOUBLE DECREASE on 3 2 1. 
			//
			// This is impossible to avoid because you have to alternate the 2's or else you get a straight. and there is no
			// possible way to organize the remaining numbers without a double decrease, because when the numbers go from 
			// GREATER than 2 to LESS than 2, there must be a double decrease. After this, you can treat the half with only less
			// than as the first example except opposite. so, 2 1 2. Here theres is n-1 zig zags. on there greater half, 
			// 2 5 2 4 2 3 there are also n-1 zig zags. Combining the 2 guarantees a double decrease. 
			//
			// When you double decrease, we instantly know that there will be n-1-1 (n-2) zig zags. When you do not
			// double decrease, there will be the max n-1 zig zags.
			if(list.stream().anyMatch(n -> n > numWithMostFrequency)&&list.stream().anyMatch(n -> n < numWithMostFrequency)){
				System.out.println(list.size()-2);
			} else {
				System.out.println(list.size()-1); 
			}
		} 

		// else if highestFrequency is not greater than or equal to max valid frequency.
		// in this situation we are guaranteed to have the max zig zags n-1 since we are not
		// forced to put 2 of the same numbers together or forced to double decrease
		else {
			System.out.println(list.size()-1);
		}
	}

	//method to find the number with highest frequency
	private static int findMaxFrequencyKey(HashMap<Integer,Integer> hashmap){
		int maxFrequencyKey = 0;
		int maxFrequencyValue = 0;
		for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue() > maxFrequencyValue) {
                maxFrequencyValue = entry.getValue();
                maxFrequencyKey = entry.getKey();
            }
        }
        return maxFrequencyKey;
	}
}