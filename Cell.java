import java.util.ArrayList;

/**
* Author: Chididebere Okafor 
* 
* Purpose of the class: A basic implementation of an ArrayList.
* that provides basic operations
* for adding and removing elements at the end of the ArrayList
* as well as getting and setting elements at a specified index.
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
        
        if ( getAlive() ){ // Runs if the Cell is alive
            return "" + 1;
        }

        return "" + 0; 
    }
}




// /**
//      * Advances the current Landscape by one step. 
//      */
//     public void advance() {
//     }