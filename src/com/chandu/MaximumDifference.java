package com.chandu;

public  class   MaximumDifference {

    public static void main(String[] args) {
        System.out.println(maximumDifference(new int[]{2, 3, 10, 6, 4, 8, 1}));
    }

    public static int maximumDifference(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i=0;i<nums.length;i++) {
            if(nums[i] < min) {
                min = nums[i];
            } else if(nums[i]-min > max) {
                max = nums[i]-min;
            }
        }
        return  max;
    }
}