/**
 * Imported this class to know if there's an I/O exception
 * that may have occurred.
 */
import java.io.IOException;
/**
 * Imports multiple classes, such as arraylist, etc.
 */
import java.util.*;

/** This represents the model of the MVC structure of the program, where most if not
 * all data are modified.
 */
public class MyFarmModel {
    private Scanner input = new Scanner(System.in);
    private Farmer player = new Farmer();
    private boolean gameEnd;

    //List of seeds of plants
    public final ArrayList<Plants> seedsList = new ArrayList<>(Arrays.asList(
            new Apple(player), new Carrot(player), new Potato(player), new Rose(player),
            new Sunflower(player), new Tulips(player), new Turnip(player), new Mango(player)
    ));

    /** Checks the whole board for any tile that is watered and fertilized
     *
     * @param gameBoard represents Board
     * @param player represents Farmer
     * @param row represents the current row
     * @param col represents the current column
     */
    public void checkWateredAndFertlized(Board gameBoard, Farmer player, int row, int col){
        if((gameBoard.getTiles()[row][col].getTimesFertilized() >= gameBoard.getTiles()[row][col].getCrop().fertilizerNeeded) && (gameBoard.getTiles()[row][col].getTimesWatered() >= gameBoard.getTiles()[row][col].getCrop().waterNeeded)){
            gameBoard.getTiles()[row][col].getCrop().setWaterAndFertilized(true);
        }
    }

    /** Checks the whole board for any tile that is harvestable or withered
     *
     * @param gameBoard represents Board
     * @param player represents Farmer
     * @param row represents the current row
     * @param col represents the current column
     */
    public void checkHarvestWither(Board gameBoard, Farmer player, int row, int col){
        if (!gameBoard.getTiles()[row][col].getCrop().isWitherStatus()) {
            if (player.getDay() == gameBoard.getTiles()[row][col].getDayToHarvest()) {
                if(gameBoard.getTiles()[row][col].getCrop().isWaterAndFertilized()){
                    gameBoard.getTiles()[row][col].getCrop().setHarvestableStatus(true);
                }
                else{
                    gameBoard.getTiles()[row][col].getCrop().setWitherStatus(true);
                    gameBoard.getTiles()[row][col].getCrop().setHarvestableStatus(false);}
            }
            else if (player.getDay() < gameBoard.getTiles()[row][col].getDayToHarvest()){
            }
            else if (player.getDay() > gameBoard.getTiles()[row][col].getDayToHarvest()){
                gameBoard.getTiles()[row][col].getCrop().setWitherStatus(true);
                gameBoard.getTiles()[row][col].getCrop().setHarvestableStatus(false);
            }
        }
    }

    /** Checks the game end status
     *
     * @param gameBoard represents Board
     * @param player represents Farmer
     */
    public void checkGameEndStatus(Board gameBoard, Farmer player){
        int count;
        boolean lessThanCheapestSeed = false;
        int witherCtr = 0;
        int noCropCtr = 0;

        //If player's Objectcoins is lesser than all the seedCost in the seedsList, then lesserSeedCostCtr increments
        for(count = 0; count < seedsList.size(); count++){
            if (player.getObjectCoins() < new Turnip(player).getSeedCost()) {
                lessThanCheapestSeed = true;
            }
        }
        //If all the crops on the board is withered then witherCtr increments
        for (int rowTile = 0; rowTile < gameBoard.getRowBoard(); rowTile++) {
            for (int colTile = 0; colTile < gameBoard.getColBoard(); colTile++) {
                if(gameBoard.getTiles()[rowTile][colTile].isCropStatus()){
                    if(gameBoard.getTiles()[rowTile][colTile].getCrop().isWitherStatus()){
                        witherCtr++;
                    }
                }
                else {
                    noCropCtr++;
                }
            }
        }
        //If player can no longer buy any seeds with the Objectcoins left and all the tiles are withered, then game ends
        if((lessThanCheapestSeed && noCropCtr == (gameBoard.getRowBoard() * gameBoard.getColBoard())) || (witherCtr == (gameBoard.getRowBoard() * gameBoard.getColBoard()))){
            setGameEnd(true);
        }
        if(player.objectCoins <= 0){
            setGameEnd(true);
        }
    }

    /** A method that when called resets the game and all values are reset
     *
     * @param gameBoard represents Board
     * @param player represents Farmer
     * @param gui represents the GUI of the game
     * @throws IOException signals if there are any I/O exception
     */
    public void newGame (Board gameBoard, Farmer player, MyFarmGUI gui) throws IOException {
        setGameEnd(false);
        gameBoard.setTile();
        player.setObjectCoins(100);
        player.setNextDay(false);
        player.setDay(1);
        player.setFarmerLevel(0);
        player.setFarmerExperience(0);
        MyFarmController myFarmController = new MyFarmController(new MyFarmModel(), new MyFarmGUI());
    }

    /** Levels up the player whenever they reach 100 or over
     *
     * @param player represents Farmer
     */
    public void levelUp(Farmer player){
        if(player.getFarmerExperience() >= 100.0){
            player.setFarmerLevel(player.getFarmerLevel() + 1);
            player.setFarmerExperience(player.getFarmerExperience() - 100);
        }
    }
    public Tools[] toolSet = new Tools[5];
    public Tools wateringCan = new Tools("Watering Can", 0, 0.5f);
    public Tools plow = new Tools("Plow", 0, 0.5f);
    public Tools fertilize = new Tools("Fertilize", 10, 4);
    public Tools pickaxe = new Tools("Pickaxe", 50, 15);
    public Tools shovel = new Tools("Shovel", 7, 2);

    /** Sets the inventory of the tools
     *
     */
    public void setInventory(){
        toolSet[0] = wateringCan;
        toolSet[1] = plow;
        toolSet[2] = fertilize;
        toolSet[3] = pickaxe;
        toolSet[4] = shovel;
    }

    /** A method that checks if game end is true/false
     *
     * @return the boolean value true/false
     */
    public boolean isGameEnd() {
        return gameEnd;
    }

    /** A method that sets the game end if true/false
     *
     * @param gameEnd the boolean value true/false
     */
    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
}