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


		startTime = System.currentTimeMillis();

		int kSize = binaryForwardSearch(arr,k,l,m,"k")-binaryBackwardSearch(arr,k,l,m,"k")+1;
		int lSize = binaryForwardSearch(arr,k,l,m,"l")-binaryBackwardSearch(arr,k,l,m,"l")+1;
		int mSize = binaryForwardSearch(arr,k,l,m,"m")-binaryBackwardSearch(arr,k,l,m,"m")+1;
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(kSize*lSize*mSize);

	}
	static int binaryForwardSearch(int[][][] arr, int k, int l, int m, String dim){

		int middleK = k/2;
		int middleL = l/2; //point with guaranteed 1
		int middleM = m/2;

		//initializes vals
		int low = 0;
		int high = 0;

		int kInc = 0;
		int lInc = 0; //so we can increment or decrement on only one axis
		int mInc = 0;

		//sets values based on param
		switch(dim){
			case "k":
				low = middleK;
				high = k-1;
				kInc =1;
				break;
			case "l":
				low = middleL;
				high = l-1;
				lInc = 1;
				break;
			case "m":
				low = middleM;
				high = m-1;
				mInc = 1;
				break;
		}

		// checks if the guaranteed 1 middle point happens to be the edge
		if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==1 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==0)){
			if(dim == "k")
				return middleK;
			else if(dim == "l")
				return middleL;
			else if(dim == "m")
				return middleM;
		}

		// binary search
		while(low<=high){
			int mid = low + (high - low)/2;

			if(dim == "k")
				middleK = mid;
			else if(dim == "l")
				middleL = mid;
			else if(dim == "m")
				middleM = mid;

			if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==1 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==0)){
				return mid;
			}
			else if (arr[middleK][middleL][middleM]==1){
				low = mid+1;
			}
			else if (arr[middleK][middleL][middleM]==0){
				high = mid-1;
			}
		}
		return -1;
	}
	static int binaryBackwardSearch(int[][][] arr, int k, int l, int m, String dim){

		int middleK = k/2;
		int middleL = l/2;  //the point with a guaranteed 1
		int middleM = m/2;

		//initializing val's for modularity
		int low = 0;
		int high = 0;

		int kInc = 0;
		int lInc = 0;  //so we can test next/previous on only one axis
		int mInc = 0;

		//sets values based on param
		switch(dim){
			case "k":
				high = middleK-1;
				kInc =1;
				break;
			case "l":
				high = middleL-1;
				lInc = 1;
				break;
			case "m":
				high = middleM-1;
				mInc = 1;
				break;
		}

		// checks if the guaranteed 1 middle point happens to be the edge
		if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==0 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==1)){
			if(dim == "k")
				return middleK;
			else if(dim == "l")
				return middleL;
			else if(dim == "m")
				return middleM;
		}

		//binary search
		while(low<=high){
			int mid = low + (high - low)/2;

			if(dim == "k")
				middleK = mid;
			else if(dim == "l")
				middleL = mid;
			else if(dim == "m")
				middleM = mid;

			if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==0 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==1)){
				return mid;
			}
			else if (arr[middleK][middleL][middleM]==1){
				high = mid-1;
			}
			else if (arr[middleK][middleL][middleM]==0){
				low = mid+1;
			}
		}
		return -1;
	}
}