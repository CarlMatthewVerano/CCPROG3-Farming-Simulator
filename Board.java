import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Board {
    private final int rowBoard = 10;
    private final int colBoard = 5;
    private Tile[][] tiles = new Tile[rowBoard][colBoard];

    /** Initializes the tiles on the game board
     *
     */
    public void setTile(){
        for (int rowTile = 0; rowTile < rowBoard; rowTile++) {
            for (int colTile = 0; colTile < colBoard; colTile++) {
                tiles[rowTile][colTile] = new Tile();
            }
        }
    }

    /** Returns the number of rows in the game board
     * 
     * @return rowBoard - number of rows in the game board
     */
    public int getRowBoard() {
        return rowBoard;
    }
    /** Returns the number of columns in the game board
     *
     * @return colBoard - number of columns in the game board
     */
    public int getColBoard() {
        return colBoard;
    }

    /** Returns the game board
     * 
     * @return tiles - the game board
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /** Returns true, if the tile can be planted on and
     *      false, if the tile cannot be planted on
     * 
     * @param plantRow  - the row number of the tile to plant on
     * @param plantCol  - the column number of the tile to plant on
     * @return canPlantTree - the boolean that symbolizes if the tile
     *                          can be planted on
     */
    public Boolean checkTreeEligibility(int plantRow, int plantCol) {

        Boolean canPlantTree = true;
        
        // CHECKS IF THE TILE IS ON THE SIDES
        if (plantRow == 0 || plantCol == 0 || plantRow >= this.rowBoard - 1
                || plantCol >= this.colBoard - 1) {
            canPlantTree = false;
        }
        // CHECKS IF THE SURROUNDING TILES ARE OCCUPIED
        else{
            for (int treeRow = plantRow - 1; treeRow <= plantRow + 1; treeRow++) {
                for (int treeCol = plantCol - 1; treeCol <= plantCol + 1; treeCol++) {
                    if (this.tiles[treeRow][treeCol].isRockStatus() ||
                            this.tiles[treeRow][treeCol].isCropStatus()) {
                        canPlantTree = false;
                    }
                }
            }
        }

        return canPlantTree;
    }

}