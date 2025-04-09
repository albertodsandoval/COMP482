import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Project3{

	private static final String INPUT_FILE = "input.txt";

	enum Axis {K,L,M}

	public static void main(String[] args){

		InputData input = readInput();

		int k = input.k;
		int l = input.l;
		int m = input.m;
		int[][][] arr = input.arr;

		bruteForce(arr, k, l, m);

		divideAndConquer(arr, k, l, m);
	}


	static class InputData {

		int[][][] arr;
		int k,l,m;

		InputData(int[][][] arr, int k, int l, int m){
			this.arr = arr;
			this.k = k;
			this.l = l;
			this.m = m;
		}
	}

	static InputData readInput(){

		int k = 0;
		int l = 0;
		int m = 0;
		int[][][] arr = null;

		// reading file and storing it in list
		try {

			File file = new File(INPUT_FILE);
			Scanner scanner = new Scanner(file);

			//Axiss from input
			k = scanner.nextInt();
			l = scanner.nextInt();
			m = scanner.nextInt();

			arr = new int[k][l][m];

			// iterates and inputs values
			for(int i = 0; i < k;i++){
				for(int j = 0; j<l;j++){
					for(int n = 0; n<m;n++){
						arr[i][j][n]=scanner.nextInt();
					}
				}
			}

			scanner.close();
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			e.printStackTrace();
		}


		return new InputData(arr,k,l,m);
	}


	static void bruteForce(int[][][] arr, int k, int l, int m){

		long startTime = System.currentTimeMillis();
		int zeroes = 0;

		for(int i = 0; i < k;i++){
			for(int j = 0; j<l;j++){
				for(int n = 0; n<m;n++){
					if(arr[i][j][n] == 1)
						zeroes++;
				}
			}
		}

		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(zeroes);
	}

	static void divideAndConquer(int[][][] arr, int k, int l, int m){

		long startTime = System.currentTimeMillis();

		//finds indices at the edges for all 3 Axiss. Add 1 because index is inclusive.
		int kSize = binaryEdgeSearch(arr,k,l,m,Axis.K,true)-binaryEdgeSearch(arr,k,l,m,Axis.K,false)+1;
		int lSize = binaryEdgeSearch(arr,k,l,m,Axis.L,true)-binaryEdgeSearch(arr,k,l,m,Axis.L,false)+1;
		int mSize = binaryEdgeSearch(arr,k,l,m,Axis.M,true)-binaryEdgeSearch(arr,k,l,m,Axis.M,false)+1;

		int zeroes = kSize*lSize*mSize;

		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(zeroes);
	}

	static int binaryEdgeSearch(int[][][] arr, int k, int l, int m, Axis dim, Boolean forward){

		int middleK = k/2;
		int middleL = l/2; //point with guaranteed 1
		int middleM = m/2;

		//initializes vals
		int low = 0;
		int high = 0;

		int kInc = 0;
		int lInc = 0; //so we can increment or decrement on only one axis
		int mInc = 0;

		switch(dim){
			case K:
				if(forward){
					low = middleK;
					high = k-1;
				} else{
					high = middleK-1;
				}
				kInc =1;
				break;
			case L:
				if(forward){
					low = middleL;
					high = l-1;
				} else{
					high = middleL-1;
				}
				lInc =1;
				break;
			case M:
				if(forward){
					low = middleM;
					high = m-1;
				} else{
					high = middleM-1;
				}
				mInc =1;
				break;
		}

		// checks if the guaranteed 1 middle point happens to be the edge
		if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==1 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==0 && forward)
			|| (arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==0 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==1 && !forward)){
			if(dim == Axis.K)
				return middleK;
			else if(dim == Axis.L)
				return middleL;
			else if(dim == Axis.M)
				return middleM;
		}

		// binary search
		while(low<=high){
			int mid = low + (high - low)/2;

			if(dim == Axis.K)
				middleK = mid;
			else if(dim == Axis.L)
				middleL = mid;
			else if(dim == Axis.M)
				middleM = mid;

			if((arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==1 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==0 && forward)
			|| (arr[middleK][middleL][middleM]==1 && arr[middleK-kInc][middleL-lInc][middleM-mInc]==0 && arr[middleK+kInc][middleL+lInc][middleM+mInc]==1 && !forward)){
				return mid;
			}
			else if ((arr[middleK][middleL][middleM]==1 && forward) || (arr[middleK][middleL][middleM]==0 && !forward)){
				low = mid+1;
			}
			else if ((arr[middleK][middleL][middleM]==0 && forward) || (arr[middleK][middleL][middleM]==1 && !forward)){
				high = mid-1;
			}
		}
		return -1;
	}
}