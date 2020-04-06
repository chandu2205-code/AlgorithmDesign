package com.chandu.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2-D matrix with 0's and 1's . Find number of islands i.e., all
 * connected components (1's) which are reachable in 8 dimensionally
 * 
 * @author allenkis
 *
 */
public class NumberOfIslands {

	public static void main(String[] args) {
	//construct and pass the required 2-D Array with 1's and 0's	
	}
	
	public int numIslands(char[][] grid) {

		int rows = grid.length;
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					grid[i][j] = 0;
					findConnectedComponent(i, j, grid);
				}
			}
		}
		return count;
	}

	/**
	 * uses DFS logic to identify connected components
	 * @param row
	 * @param column
	 * @param grid
	 */
	public void findConnectedComponent(int row, int column, char[][] grid) {

		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { -1, 1, 0, 0 };

		Queue<String> q = new LinkedList<>();
		q.add(row + " " + column);

		while (!q.isEmpty()) {
			String[] str = q.poll().split(" ");
			int rowIdx = Integer.parseInt(str[0]);
			int colIdx = Integer.parseInt(str[1]);
			grid[rowIdx][colIdx] = 0;
			for (int i = 0; i < 4; i++) {
				if (rowIdx + dy[i] >= 0 && rowIdx + dy[i] < grid.length && colIdx + dx[i] >= 0
						&& colIdx + dx[i] < grid[0].length && grid[rowIdx + dy[i]][colIdx + dx[i]] == '1') {
					//Late update can increase iterations to same node multiple times this improves time complexity
					grid[rowIdx + dy[i]][colIdx + dx[i]] = 0;
					q.add((rowIdx + dy[i]) + " " + (colIdx + dx[i]));
				}
			}

		}
	}
}
