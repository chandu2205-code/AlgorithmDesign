package com.chandu;

/*
 * Square Root Decomposition can be used to find range Queries
 * 1 . Sum of given numbers in a range
 * 2 . Max or Min of given numbers in range
 */
public class SquareRootDecomposition {

	static int input[] = { 1, 5, 2, 4, 6, 1, 3, 5, 7, 10 };
	static int[] decomposedArray = null;
	static int blockSize;
	
	public static void main(String[] args) {

		decomposedArray = buildBlocks(input);
		resultInRange(3, 8);
		resultInRange(1, 6);
		update(8, 0);
		resultInRange(8, 8);
	}

	public static int[] buildBlocks(int[] ar) {

		int size = ar.length;
		blockSize = (int) Math.sqrt(size);

		int[] blockSum = new int[(size / blockSize) + 1];

		for (int i = 0; i < ar.length; i++) {
			blockSum[i / blockSize] += ar[i];
		}

		return blockSum;
	}

	public static void update(int idx, int num) {
		
		decomposedArray[idx/blockSize] = decomposedArray[idx/blockSize] - input[idx] + num;
		input[idx] = num;
	}

	public static void resultInRange(int lo, int hi) {
		
		int sum = 0;
		
		if(hi>=input.length || lo>hi) return;
		
		while(lo<=hi && (lo%blockSize)!=0){
			sum+=input[lo];
			lo+=1;
		}
		
		while(lo+blockSize<=hi){
			sum+=decomposedArray[lo/blockSize];
			lo=lo+blockSize;
		}
		
		while(lo<=hi){
			sum+=input[lo];
			lo+=1;
		}
		System.out.println(sum);
	}
}
