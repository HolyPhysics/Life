import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;


/**
* Author: Chididebere Okafor 
* 
* Purpose: This class represents the grid (landscape) used in Conway's Game of Life.
* The landscape is implemented as a two-dimensional array of Cell objects.
* Each Cell can either be alive or dead, and the landscape evolves over
* time according to the standard Game of Life rules.
*
*/
public class Landscape {

    /**
     * The underlying grid of Cells for Conway's Game
     */
    private Cell[][] landscape;

    /**
     * The original probability each individual Cell is alive
     */
    private double initialChance;

    /**
     * The number of rows and columns in the landscape
     */
    private int rows;
    private int columns;

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * All Cells are initially dead.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     */
    public Landscape(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.landscape = new Cell[rows][columns];
        this.initialChance = 0.0;

        reset();
    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param rows    the number of rows in the Landscape
     * @param columns the number of columns in the Landscape
     * @param chance  the probability each individual Cell is initially alive
     */
    public Landscape(int rows, int columns, double chance) {
        this.rows = rows;
        this.columns = columns;
        this.landscape = new Cell[rows][columns]; // Creates 2D array that can hold Cells and iinitialize them with null
        this.initialChance = chance;

        Random random = new Random();

        for (int r =0; r < landscape.length; r++){
            for (int c = 0; c < landscape[r].length; c++){

                boolean aliveState = (random.nextDouble() < chance);
                landscape[r][c] = new Cell(aliveState); // Assigns each null cell a new cell with designated live state
            }
        }

    }

    /**
     * Constructs a Landscape of the specified number of rows and columns.
     * Each Cell is initially alive with probability specified by chance.
     * 
     * @param grid  grid of boolean containing the live state of each Cell in the Landscape.
     */
    public Landscape(boolean[][] grid) {
        this.rows = grid.length;
        this.columns = grid[0].length;
        this.landscape = new Cell[rows][columns];
        this.initialChance = 0.0;

        for (int r =0; r < landscape.length; r++){
            for (int c = 0; c < landscape[r].length; c++){

                landscape[r][c] = new Cell( grid[r][c] ); // Assigns each null cell a new cell with designated live state
            }
        }

    }

    /**
     * Recreates the Landscape according to the specifications given
     * in its initial construction.
     */
    public void reset() {

        for (int r =0; r < landscape.length; r++){
            for (int c = 0; c < landscape[r].length; c++){

                if ( landscape[r][c] == null){
                    landscape[r][c] = new Cell(false);
                }
                else{
                    landscape[r][c].setAlive(false); // Assigns each null cell a new cell with designated live state
                }
            }
        }

    }

    /**
     * Returns the number of rows in the Landscape.
     * 
     * @return the number of rows in the Landscape
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the Landscape.
     * 
     * @return the number of columns in the Landscape
     */
    public int getCols() {
        return columns; // We are assuming this is a rectangular array
    }

    /**
     * Returns the Cell specified the given row and column.
     * 
     * @param row the row of the desired Cell
     * @param col the column of the desired Cell
     * @return the Cell specified the given row and column
     */
    public Cell getCell(int row, int col) {

        Cell returnCell = null;
        
        if ( (row >= 0) && (col >= 0) && (row < getRows()) && (col < getCols() ) ){
            returnCell = landscape[row][col];
        } 

        // Cell returnCell = landscape[row][col];

        return returnCell;
    }

    /**
     * Returns a String representation of the Landscape.
     */
    @Override
    public String toString() {

        String returnLandscapeForm = "";

        for (int r =0; r < landscape.length; r++){
            for (int c = 0; c < landscape[r].length; c++){

                if (c == landscape[r].length - 1){
                    returnLandscapeForm += landscape[r][c] + "\n";
                }
                else{ 
                    returnLandscapeForm += landscape[r][c] + " ";
                }

                if (r == landscape.length -1 && c == landscape[r].length - 1){ /* Starts a new line after printing, 
                * seperating it from any arrays printed below it for clarity. 
                */
                    returnLandscapeForm += " ";
                }

            }
        }

        return returnLandscapeForm;

    }

    /**
     * Returns an ArrayList of the neighboring Cells to the specified location.
     * 
     * @param row the row of the specified Cell
     * @param col the column of the specified Cell
     * @return an ArrayList of the neighboring Cells to the specified location
     */
    public ArrayList<Cell> getNeighbors(int row, int col) {

        ArrayList<Cell> neighbors = new ArrayList<>();

        for (int r = row - 1; r < row + 2; r++){
            for (int c = col - 1; c < col + 2; c++){

                if (r == row && c == col){
                    continue;
                }

                if( (r >= 0) && (c >= 0) && (r < getRows() ) && (c < getCols()) ){
                    /*This ensures the required conditons for a cell to be anothers neighbor are satisfied */
                    neighbors.add( getCell(r,c) ); // can also replace with landscape[r][c]
                }

            }
        }

        return neighbors;
    }

    /**
     * Advances the current Landscape by one step. 
     */
    public void advance() {
        boolean[][] nextState = new boolean[getRows()][getCols()]; // temporary 2D array for each Cell's state

        for (int r = 0; r < nextState.length; r++){
            for (int c = 0; c < nextState[r].length; c++){
                
                Cell currentCell = getCell(r,c);

                // System.out.println(currentCell);

                ArrayList<Cell> currentNeighbors = getNeighbors(r,c); // returns an arraylist containing a Cell's neighbors

                // System.out.println(currentNeighbors);
                // calculate the number of live neigbors
                int liveNeighbors = 0; // This keeps track of the number of live neighbors of a Cell.

                for (int i = 0; i < currentNeighbors.size(); i++ ){
                    Cell cell = currentNeighbors.get(i);

                    if( cell.getAlive() ){
                        liveNeighbors +=1;
                    }
                }

                if ( ( currentCell.getAlive() == false) && (liveNeighbors == 3) ){ // Birth: Checks that the Cell is dead and its number of live neighbors are exactly 3 
                    nextState[r][c] = true;
                }
                else if ( (currentCell.getAlive() == true) && (liveNeighbors == 2 || liveNeighbors == 3) ){ // Survival: Checks that the Cell is alive and its number of live neighbors is either 2 or 3
                    nextState[r][c] = true;
                }
                else{ // Death(automatic for already dead cells): For a live cell, checks that the cell's number of live neighbors is not 2 and not 3
                    nextState[r][c] = false;
                }

            }
        }

        for (int r =0; r < nextState.length; r++){
            for (int c = 0; c < nextState[r].length; c++){
                landscape[r][c] = new Cell( nextState[r][c] );
            }
        } 


    }



    /**
     * Draws the Cell to the given Graphics object at the specified scale.
     * An alive Cell is drawn with a black color; a dead Cell is drawn gray.
     * 
     * @param g     the Graphics object on which to draw
     * @param scale the scale of the representation of this Cell
     */
    public void draw(Graphics g, int scale) {
        for (int x = 0; x < getRows(); x++) {
            for (int y = 0; y < getCols(); y++) {
                g.setColor(getCell(x, y).getAlive() ? Color.BLACK : Color.gray);
                g.fillOval(x * scale, y * scale, scale, scale);
            }
        }
    }

    // public int getLivingCells() {
    
    //     int count = 0;

    //     for (int r = 0; r < rows; r++) {
    //         for (int c = 0; c < columns; c++) {
    //             if (landscape[r][c].getAlive()) {
    //                 count++;
    //             }
    //         }
    //     }

    //     return count;
    // }

    public static void main(String[] args) {

        // Test constructor with random chance
        System.out.println("Testing Landscape with 50% initial alive chance:");
        Landscape landscape1 = new Landscape(5, 5, 0.5);
        System.out.println(landscape1);
        
        // Test constructor with all dead cells
        System.out.println("\nTesting Landscape with all dead cells:");
        Landscape landscape2 = new Landscape(3, 3);
        System.out.println(landscape2);
        
        // Test constructor with boolean grid
        System.out.println("\nTesting Landscape with custom boolean grid:");
        boolean[][] grid = {
            {true, false, true},
            {false, true, false},
            {true, false, true}
        };
        Landscape landscape3 = new Landscape(grid);
        System.out.println(landscape3);
        
        // Test getNeighbors method
        System.out.println("\nTesting getNeighbors for center cell (1,1):");
        ArrayList<Cell> neighbors = landscape3.getNeighbors(1, 1);
        System.out.println("Number of neighbors: " + neighbors.size());
        
        // Test advance method
        System.out.println("\nTesting advance method on landscape3:");
        System.out.println("Before advance:");
        System.out.println(landscape3);
        landscape3.advance();
        System.out.println("After advance:");
        System.out.println(landscape3);
        
        // Test reset method
        System.out.println("\nTesting reset method on landscape1:");
        System.out.println("Before reset:");
        System.out.println(landscape1);
        landscape1.reset();
        System.out.println("After reset:");
        System.out.println(landscape1);

    }
}