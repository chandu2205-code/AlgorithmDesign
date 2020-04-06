package com.chandu;

import java.util.Arrays;

public class SegmentTrees {

	static int[] st = null;
	static int arr[] = { 1, 3, 5, 7, 9, 11 };
	
	public static void main(String[] args) {
		
		constructSTUtil(arr);
		System.out.println(Arrays.toString(st));
		updateValue(0, arr.length-1, 3, 13, 0);
		System.out.println("Updated value..."+arr[3]);
		System.out.println(Arrays.toString(st));
		System.out.println(Arrays.toString(arr));
		System.out.println("Max value "+getMax(1, 3, 0, arr.length-1, 0));
		//

	}
	
	public static void constructSTUtil(int[] ar){
		
		int nextPerfectSquare = (int)Math.ceil(Math.sqrt(ar.length));
		
		int size  = (int)(2*(int)Math.pow(2,nextPerfectSquare)-1);
		System.out.println(size);
		st = new int[size];
		
		for(int i=0;i<st.length;i++){
			st[i] = Integer.MIN_VALUE;
		}
		
		constructST(st, 0, ar.length-1, 0);
	}

	public static void constructST(int[] st, int lo, int hi, int idx){
		System.out.println(lo+".........."+hi);
		if(lo == hi){
			//System.out.println(idx+"............"+lo);
			st[idx] = arr[lo];
			return;
		}
		
		int mid = (lo+hi)/2;
		constructST(st, lo, mid, 2*idx+1);
		constructST(st, mid+1, hi, 2*idx+2);
		
		st[idx] = Math.max(st[2*idx+1], st[2*idx+2]);
		return;
		
	}
	
	public static void updateValue(int ss, int se, int index, int newValue, int startPosition){
		
		if(index < ss || index > se)return;
		
		if(index == ss && index == se){
			st[startPosition] = newValue;
			arr[index] = newValue;
			return;
		}
		int mid = (ss+se)/2;
		st[startPosition] = Math.max(newValue, st[startPosition]);
		if(index >= ss && index <= mid){
			 updateValue(ss, mid, index, newValue, 2*startPosition+1);
		}else if(index>=mid+1 && index<=se){
			updateValue(mid+1, se, index, newValue, 2*startPosition+2);
		}
		return;
		
	}
	
	public static int getMax(int qs, int qe, int startPosition, int endPosition, int current){
		//Complete overlap
		if(qs<=startPosition && qe >= endPosition){
			//System.out.println(lo+"........."+hi+"..........."+current+"......"+startPosition+"........"+endPosition+"..."+st.length);
			return st[current];
		}//No overlap
		if(qs > endPosition || qe < startPosition){
			return Integer.MIN_VALUE;
		}
		//Partial overlap
		int mid = (startPosition+endPosition)/2;
		return Math.max(getMax(qs, qe, startPosition, mid, 2*current+1), getMax(qs, qe, mid+1, endPosition, 2*current+2));
			
		
	}
}
