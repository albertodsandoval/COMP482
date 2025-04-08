import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Project2{
	public static void main(String[] args){
		ArrayList<Integer> list = new ArrayList<>();

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
			scanner.close();

		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}

		//loops through all inputted numbers
		for(int i = 0; i < list.size();i++){

			int count = 0; //resets counter
			int value = list.get(i); //sets variable containing curr value

			//iterates until value is 1
			while(value>1){

				//value being divisible by 8 guarantees 3 consecutive halves
				if(value%8==7)
					value++;

				//halves
				else if(value%2==0)
					value /= 2;

				//decrease value
				else
					value--;
				count++;
			}
			System.out.println(count);
		}
	}
}