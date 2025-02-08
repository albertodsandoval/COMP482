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

		//reading file and storing it in list
		try {
			File file = new File("input.txt");
			Scanner scanner = new Scanner(file);

			int toss = scanner.nextInt();//removes

			while(scanner.hasNextInt()){
				list.add(scanner.nextInt());
			}

			//sets the max valid frequency threshold
			if(list.size() % 2 == 0){
				maxValidFrequency = list.size()/2;
			}
			else{
				maxValidFrequency = (list.size()/2)+1;
			}

			scanner.close();

		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}

		HashMap<Integer, Integer> hashmap = new HashMap<>();

		//counting frequency
		for(int i : list){
			hashmap.put(i, hashmap.getOrDefault(i,0)+1);
		}

		//calling function to find highest frequency num
		int numWithMostFrequency = findMaxFrequencyKey(hashmap);

		//obtaining the highest frequency value
		int highestFrequency = hashmap.get(numWithMostFrequency);

		//checking if our most frequent is at or above the max valid frequency threshold
		if(highestFrequency >= maxValidFrequency){

			//loops to test if our most frequent is still above max valid frequency
			while(highestFrequency > maxValidFrequency){

				//tests if size of array is 1, no
				if(list.size()==1){
					break;
				}

				//reduces frequency in hashmap by the remove amount
				hashmap.put(numWithMostFrequency, hashmap.get(numWithMostFrequency)-(highestFrequency- maxValidFrequency));

				//removes 
				for(int j = 0; j < highestFrequency- maxValidFrequency; j++ ){
					list.remove(Integer.valueOf(numWithMostFrequency));
				}
				highestFrequency = hashmap.get(numWithMostFrequency);
				if(list.size() % 2 == 0){
					maxValidFrequency = list.size()/2;
				}
				else{
					maxValidFrequency = (list.size()/2)+1;
				}
			}
			if(list.stream().anyMatch(n -> n > numWithMostFrequency)&&list.stream().anyMatch(n -> n < numWithMostFrequency)){
				System.out.println(list.size()-2);
			}
			else {
				System.out.println(list.size()-1); 
			}
		}else {
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