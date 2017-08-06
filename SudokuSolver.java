public class SudokuSolver{
	
	int[][] sudokuPuzzle = new int[][]{
		{0,0,0, 0,0,7, 0,6,0},
		{4,1,9, 2,8,0, 0,0,0},
		{0,0,0, 0,3,0, 0,0,0},
		
		{0,2,0, 0,0,0, 0,0,6},
		{5,7,6, 0,0,0, 1,2,4},
		{3,0,0, 0,0,0, 0,9,0},
		
		{0,0,0, 0,7,0, 0,0,0},
		{0,0,0, 0,1,9, 5,8,3},
		{0,9,0, 8,0,0, 0,0,0}
	};

	public void printSudoku(int[][] sudokuPuzzle){
		System.out.println(" _ _ _  _ _ _  _ _ _");
		for (int i=0;i<9;i++) {
			System.out.print("|");
			for (int j=0;j<9;j++) {
				int value = sudokuPuzzle[i][j];
				if(value != 0){
					System.out.print(Integer.toString(sudokuPuzzle[i][j]) + " ");
				}else{
					System.out.print("  ");
				}
				
				if(j%3 == 2){
					System.out.print("|");
				}
			}

			if(i%3 == 2){
				System.out.print("\n _ _ _  _ _ _  _ _ _");
			}
			System.out.println();
		}
	}

	SudokuSolver(){
		System.out.println("Input: ");
		printSudoku(sudokuPuzzle);
		if(solveSudoku(sudokuPuzzle)){
			System.out.println("\nSolution: ");
			printSudoku(sudokuPuzzle);
		}else{
			System.out.println("No solution found for sudoku");			
		}
	}

	public boolean solveSudoku(int[][] sudokuPuzzle){
		// find the next empty cell
		for(int i = 0;i<9;i++){
			for(int j = 0;j<9;j++){
				if(sudokuPuzzle[i][j] == 0){
					// test value k=1:9 at cell i,j. 
					for(int k = 1;k<=9;k++){
						if(meetsConstraints(sudokuPuzzle,i,j,k)){
							sudokuPuzzle[i][j] = k;
							// move forward
							if(solveSudoku(sudokuPuzzle)){
								return true;
							}else{
								// move back
								sudokuPuzzle[i][j] = 0;
							}
						}
					}
					return false; // if no value works in current cell
				}
			}
		}
		return true; // all cells have been filled
	}

	// check that number in cell meets constraints
	public boolean meetsConstraints(int[][] sudokuPuzzle,int row,int col,int value){

 		// which 3x3 box current value is in 
		int boxRow = row/3;
		int boxCol = col/3;
		
		boolean passed = true; // if any constraint violation change to false
		for(int i = 0;i<9;i++){	

			//check same box
			int currentRow = (boxRow*3)+i/3;
			int currentCol = (boxCol*3)+i%3;
			if(sudokuPuzzle[currentRow][currentCol] == value && value != 0 && currentRow!=row && currentCol!=col){
				passed = false;
			}

			// check same row
			else if(sudokuPuzzle[row][i] == value && value != 0 && i!=col){
				passed = false;
			}
			// check same column
			else if(sudokuPuzzle[i][col] == value && value != 0 && i!=row){
				passed = false;
			}
		}
		return passed;
	}

	public static void main(String[] args){
		new SudokuSolver();
	}
}
