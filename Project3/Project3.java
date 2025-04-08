import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Project3{
	public static void main(String[] args){

		int k = 0;
		int l = 0;
		int m = 0;

		int[][][] arr = null;

		// reading file and storing it in list
		try {
			File file = new File("input.txt");
			Scanner scanner = new Scanner(file);

			//dimensions from input
			k = scanner.nextInt();
			l = scanner.nextInt();
			m = scanner.nextInt();

			arr = new int[k][l][m];

			// iterates and inputs values
			while(scanner.hasNextInt()){
				for(int i = 0; i < k;i++){
					for(int j = 0; j<l;j++){
						for(int n = 0; n<m;n++){
							arr[i][j][n]=scanner.nextInt();
						}
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}

		//prints out sheets in the same manner they were given
		//by the input generator

		long startTime = System.currentTimeMillis();
		int count = 0;
		for(int i = 0; i < k;i++){
			for(int j = 0; j<l;j++){
				for(int n = 0; n<m;n++){
					if(arr[i][j][n] == 1)
						count++;
				}
			}
		}
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(count);


		System.out.println(binaryForwardSearch(arr,k,l,m));
		System.out.println(binaryBackwardSearch(arr,k,l,m));




	}
	static int binaryForwardSearchK(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = middleK;
		int high = k-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1) || (arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==1 && arr[middleK+1][middleL][middleM]==0)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1) || (arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==1 && arr[mid+1][middleL][middleM]==0)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
	static int binaryBackwardSearchK(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = 0;
		int high = middleK-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
	static int binaryForwardSearchL(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = middleL;
		int high = l-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1) || (arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==1 && arr[middleK+1][middleL][middleM]==0)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1) || (arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==1 && arr[mid+1][middleL][middleM]==0)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
	static int binaryBackwardSearchL(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = 0;
		int high = middleK-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
		static int binaryForwardSearchM(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = middleK;
		int high = k-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1) || (arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==1 && arr[middleK+1][middleL][middleM]==0)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1) || (arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==1 && arr[mid+1][middleL][middleM]==0)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
	static int binaryBackwardSearchM(int[][][] arr, int k, int l, int m){

		int middleK = k/2;
		int middleL = l/2;
		int middleM = m/2;
		int low = 0;
		int high = middleK-1;

		if((arr[middleK][middleL][middleM]==1 && arr[middleK-1][middleL][middleM]==0 && arr[middleK+1][middleL][middleM]==1)){
			System.out.println("K, L, M: "+middleK+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[middleK][middleL][middleM]);
			return middleK;
		}

		while(low<=high){
			int mid = low + (high - low)/2;
			System.out.println("K, L, M: "+mid+" "+middleL+" "+middleM);
			System.out.println("Value: "+ arr[mid][middleL][middleM]);



			if((arr[mid][middleL][middleM]==1 && arr[mid-1][middleL][middleM]==0 && arr[mid+1][middleL][middleM]==1)){
				return mid;
			}
			else if (arr[mid][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[mid][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}

}