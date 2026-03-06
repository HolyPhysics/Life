import java.util.ArrayList;

/**
* Author: Chididebere Okafor 
* 
* Purpose: Represents a single cell in Conway's Game of Life.
* Each cell stores whether it is alive or dead and updates its
* state based on neighboring cells.
*
*/
public class Cell {

    /**
     * The status of the Cell.
     */
    private boolean alive;

    /**
     * Constructs a dead cell.
     */
    public Cell() {

        this(false);
    }

    /**
     * Constructs a cell with the specified status.
     * 
     * @param status a boolean to specify if the Cell is initially alive
     */
    public Cell(boolean status) {

        this.alive = status;
    }

    /**
     * Returns whether the cell is currently alive.
     * 
     * @return whether the cell is currently alive
     */
    public boolean getAlive() {
        return this.alive;
    }

    /**
     * Sets the current status of the cell to the specified status.
     * 
     * @param status a boolean to specify if the Cell is alive or dead
     */
    public void setAlive(boolean status) {
        this.alive = status;
    }

    /**
     * Updates the state of the Cell.
     * 
     * If this Cell is alive and if there are 2 or 3 alive neighbors,
     * this Cell stays alive. Otherwise, it dies.
     * 
     * If this Cell is dead and there are 3 alive neighbors,
     * this Cell comes back to life. Otherwise, it stays dead.
     * 
     * @param neighbors An ArrayList of Cells
     */
    public void updateState(ArrayList<Cell> neighbors) {

        int liveNeighbors = 0; // This keeps track of the number of live neighbors of a Cell.

        for (int i = 0; i < neighbors.size(); i++ ){
            Cell cell = neighbors.get(i);

            if( cell.getAlive() ){
                liveNeighbors +=1;
            }
        }

        if ( (getAlive() == false) && (liveNeighbors == 3) ){ // Birth: Checks that the Cell is dead and its number of live neighbors are exactly 3 
            setAlive(true);
        }
        else if ( (getAlive() == true) && (liveNeighbors == 2 || liveNeighbors == 3) ){ // Survival: Checks that the Cell is alive and its number of live neighbors is either 2 or 3
            setAlive(true);
        }
        else{ // Death(automatic for already dead cells): For a live cell, checks that the cell's number of live neighbors is not 2 and not 3
            setAlive(false);
        }
    }

    /**
     * Returns a String representation of this Cell.
     * 
     * @return 1 if this Cell is alive, otherwise 0.
     */
    @Override
    public String toString() {

        return getAlive() ? "1" : "0"; // returns 1 as a string if true and 0 as a string otherwise
    }
}

