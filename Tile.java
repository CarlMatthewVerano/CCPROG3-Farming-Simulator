/**
 * This class represents the tile in which the player can plant on.
 * It contains the crop status, the plowed status, the times watered,
 * the day the crop was planted, the day the crop will be harvested,
 * the symbol of it on the game board, and the crop the tile currently
 * has.
 *
 * A tile object represent the tile to be planted on and its current
 * attributes. There are 50 tiles.
 */
public class Tile {
    private boolean cropStatus = false;
    private boolean plowedStatus = false;
    private boolean rockStatus = false;
    private int timesWatered = 0;
    private int timesFertilized = 0;
    private int dayPlanted = 1;
    private int dayToHarvest = 1;
    private String symbol = "0";
    private Plants crop;

    /** A method that returns the day the crop on the tile will
     * be harvested
     *
     * @return the day the crop on the tile will be harvested
     */
    public int getDayToHarvest() {
        return dayToHarvest;
    }
    /** A method that sets the day the crop on the tile will
     * be harvested
     *
     * @param dayToHarvest  the day the crop on the tile will be harvested
     */
    public void setDayToHarvest(int dayToHarvest) {
        this.dayToHarvest = dayToHarvest;
    }
    /** A method that returns the day the crop on the tile was planted
     *
     * @return the day the crop on the tile was planted
     */
    public int getDayPlanted() {
        return dayPlanted;
    }
    /** A method that sets the day the crop on the tile was planted
     *
     * @param dayPlanted    the day the crop on the tile was planted
     */
    public void setDayPlanted(int dayPlanted) {
        this.dayPlanted = dayPlanted;
    }
    /** A method that returns the times the crop on the tile was watered
     *
     * @return  the times the crop on the tile was watered
     */
    public int getTimesWatered() {
        return timesWatered;
    }
    /** A method that sets the times the crop on the tile was watered
     *
     * @param timesWatered  the times the crop on the tile was watered
     */
    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }
    /** A method that sets the crop on the tile
     *
     * @param crop  the crop on the tile
     */
    public void setCrop(Plants crop) {
        this.crop = crop;
    }
    /** A method that returns the crop on the tile
     *
     * @return  the crop on the tile
     */
    public Plants getCrop() {
        return crop;
    }
    /** A method that returns true if there is currently a crop
     * on the tile and false, if there is none.
     *
     * @return the status if there is a crop on the tile
     */
    public boolean isCropStatus() {return cropStatus;}
    /** A method that sets true if there is currently a crop
     * on the tile and false, if there is none.
     *
     * @param cropStatus    determines if there is crop on the tile
     */
    public void setCropStatus(boolean cropStatus) {
        this.cropStatus = cropStatus;
    }
    /** A method that returns true if the tile is plowed and
     * false, if it is not.
     *
     * @return  the status if the tile is plowed
     */
    public boolean isPlowedStatus() {
        return plowedStatus;
    }
    /** A method that sets true if the tile is plowed and
     * false, if it is not.
     *
     * @param plowedStatus  determines if the tile is plowed
     */
    public void setPlowedStatus(boolean plowedStatus) {
        this.plowedStatus = plowedStatus;
    }

    /** A method that returns the number of times fertilized
     * 
     * @return the number of times fertilized
     */
    public int getTimesFertilized() {
        return timesFertilized;
    }

    /** A method that sets the number of times fertilized
     * 
     * @param timesFertilized the number of times fertilized
     */
    public void setTimesFertilized(int timesFertilized) {
        this.timesFertilized = timesFertilized;
    }

    /** A method that returns the rock status of the tile
     * 
     * @return the rock status of the tile
     */
    public boolean isRockStatus() {
        return rockStatus;
    }

    /** A method that sets the rcok status of the tile
     * 
     * @param rockStatus the rock status of the tile
     */
    public void setRockStatus(boolean rockStatus) {
        this.rockStatus = rockStatus;
    }


}