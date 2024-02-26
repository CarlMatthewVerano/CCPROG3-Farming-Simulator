/**
 * This class represents the tools which the player can choose to
 * use one the tiles. It contains the name of the tools, their costs,
 * and the experience the player gains when the tools are used.
 *
 * A tool object represents the tools and the attributes its attributes.
 * There are a total of 5 tools.
 */

public class Tools {

    private String toolName;
    private int toolCost;
    private float toolExperience;

    /** Constructor for the class Tools, makes it easier to initialize constant values for the attributes of tools
     *
     * @param toolName name of the tool
     * @param toolCost cost of the tool
     * @param toolExperience experience that can be gained by using the tool
     */
    public Tools (String toolName, int toolCost, float toolExperience) {
        this.toolName = toolName;
        this.toolCost = toolCost;
        this.toolExperience = toolExperience;
    }

    /** A method that plows the tile on the board and sets the appropriate symbol, status, and experience
     *
     * @param tile an object that came from the instantiation of the class Tile
     *             in which it accesses the specific tile on the board.
     * @param player an object that came from the instantiation of the class FarmerDetails
     *               that accesses all the properties of FarmerDetails through the object player.
     */
    public void plowTile(Tile tile, Farmer player) {
        if(!tile.isRockStatus()){
            tile.setPlowedStatus(true);
            player.setFarmerExperience(player.getFarmerExperience() + toolExperience);
        }
    }
    /** A method that waters the tile on the board
     * @param tile an object that came from the instantiation of the class Tile
     *             in which it accesses the specific tile on the board.
     * @param player an object that came from the instantiation of the class FarmerDetails
     *               that accesses all the properties of FarmerDetails through the object player.
     */
    public void waterPlant(Tile tile, Farmer player) {
        if (tile.isCropStatus()) {
            tile.setTimesWatered(tile.getTimesWatered()+1);
            player.setFarmerExperience(player.getFarmerExperience() + toolExperience);
        }
    }
    
    /** A method that fertilizes the tile on the board
     *
     * @param tile an object that came from the instantiation of the class Tile
     *             in which it accesses the specific tile on the board.
     * @param player an object that came from the instantiation of the class FarmerDetails
     *               that accesses all the properties of FarmerDetails through the object player.
     */
    public void fertilizePlant(Tile tile, Farmer player) {
        if (tile.isCropStatus()) {
            tile.setTimesFertilized(tile.getTimesFertilized()+1);
            player.setFarmerExperience(player.getFarmerExperience() + toolExperience);
            player.setObjectCoins(player.getObjectCoins() - toolCost);
        }
    }
    
    /** A method that removes a plant from the tile on the board
     *
     * @param tile an object that came from the instantiation of the class Tile
     *             in which it accesses the specific tile on the board.
     * @param player an object that came from the instantiation of the class FarmerDetails
     *               that accesses all the properties of FarmerDetails through the object player.
     */
    public void shovelTile(Tile tile, Farmer player) {
        if (tile.isCropStatus()) {
            if (tile.getCrop().isWitherStatus()) {
                tile.getCrop().setWitherStatus(false);
                tile.setCropStatus(false);
                tile.setPlowedStatus(false);
            }
            else{
                tile.setPlowedStatus(false);
                tile.setCropStatus(false);
            }
        }
        tile.setTimesFertilized(0);
        tile.setTimesWatered(0);
        player.setFarmerExperience(player.getFarmerExperience() + toolExperience);
        player.setObjectCoins(player.getObjectCoins() - toolCost);
    }
    
    /** A method that removes a rock from the tile
     *
     * @param tile an object that came from the instantiation of the class Tile
     *             in which it accesses the specific tile on the board.
     * @param player an object that came from the instantiation of the class FarmerDetails
     *               that accesses all the properties of FarmerDetails through the object player.
     */
    public void mineTile(Tile tile, Farmer player) {
        if (tile.isRockStatus()) {
            tile.setRockStatus(false);
            player.setFarmerExperience(player.getFarmerExperience() + toolExperience);
            player.setObjectCoins(player.getObjectCoins() - toolCost);
        }
    }
}