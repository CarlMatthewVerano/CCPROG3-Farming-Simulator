/**
 * Random class to produce random numbers
 */
import java.util.Random;

/**
 * This class represents the properties of the plants present in the game.
 * It contains the seed name, seed cost, crop type, water needed to grow,
 * the water bonus and its limit, minimum and maximum produce count per harvest,
 * its actual produce count, experience yield upon harvest, base selling price,
 * and wither status. It's an asbtract class where it will be used by 8 different plants
 * as a form of template.
 */
public abstract class Plants {
    protected String seedName;
    protected int seedCost;
    protected String cropType;
    protected int waterNeeded;
    protected int fertilizerNeeded;
    protected int waterBonusLimit;
    protected int fertilizerBonusLimit;
    protected int produceCount;
    protected float xpYield;
    protected float baseSelling;
    protected boolean witherStatus;
    protected boolean harvestableStatus;
    protected int harvestTimeInDays;
    protected int produceLowerBound;
    protected int produceUpperBound;

    protected boolean waterAndFertilized;

    /**
     * Constructor for abstract class Plants, to initially set all variables to default values
     */
    public Plants(){
        this.waterNeeded = 0;
        this.waterBonusLimit = 0;
        this.fertilizerNeeded = 0;
        this.fertilizerBonusLimit = 0;
        this.baseSelling = 0;
        this.produceCount = 0;
        this.xpYield = 0;
        this.harvestTimeInDays = 0;
        this.seedCost = 0;
        this.produceLowerBound = 0;
        this.produceUpperBound = 0;
    }

    /**
     * Abstract method that takes two int parameters, min and max
     * @param min represents the lower bound product count
     * @param max represents the upper bound product count
     * @return an int type data
     */
    public abstract int setPlantProduce(int min, int max);


    /** A method that gets the seed name of a crop
     *
     * @return the seend name of a crop
     */
    public String getSeedName() {
        return seedName;
    }

    /** A method that gets the experience that can be yielded from a crop
     *
     * @return the experience that can be yielded from a crop
     */
    public float getXpYield() {
        return xpYield;
    }

    /** A method that gets the seed cost of a crop
     *
     * @return the seed cost of a crop
     */
    public int getSeedCost() {
        return seedCost;
    }

    /** A method that gets the crop type
     *
     * @return the crop type
     */
    public String getCropType() {
        return cropType;
    }

    /** A method that gets the harvest time in days that's needed for a crop to grow
     *
     * @return the harvest time in days that's needed for a crop to grow
     */
    public int getHarvestTimeInDays() {
        return harvestTimeInDays;
    }

    /** A method that gets the water a crop needs
     *
     * @return the water a crop needs
     */
    public int getWaterNeeded() {
        return waterNeeded;
    }

    /** A method that gets the limit of how many times one can water a crop
     *
     * @return the limit on how many times one can water a crop
     */
    public int getWaterBonusLimit() {
        return waterBonusLimit;
    }

    /** A method that gets the produce count of a crop
     *
     * @return the produce count of a crop
     */
    public int getProduceCount() {
        return produceCount;
    }

    /** A method that gets the base selling price of a crop for when a player will buy seeds
     *
     * @return the base selling price of a crop
     */
    public float getBaseSelling() {
        return baseSelling;
    }

    /** A method that gets the wither status of a crop, true if it's withered, false if not
     *
     * @return the wither status of a crop
     */
    public boolean isWitherStatus() {
        return witherStatus;
    }

    /** A method that sets the wither status of a crop
     *
     * @param witherStatus the wither status of a crop
     */
    public void setWitherStatus(boolean witherStatus) {
        this.witherStatus = witherStatus;
    }

    /** A method that sets the water needed
     *
     * @param waterNeeded the water needed of a crop
     */
    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }

    /** A method that gets the fertilizer needed of a crop
     *
     * @return the fertilizer needed of a crop
     */
    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    /** A method that sets the fertilizer needed of a crop
     *
     * @param fertilizerNeeded the fertilizer needed of a crop
     */
    public void setFertilizerNeeded(int fertilizerNeeded) {
        this.fertilizerNeeded = fertilizerNeeded;
    }

    /** A method that gets the fertilizer bonus limit
     *
     * @return the fertilizer bonus limit needed of a crop
     */
    public int getFertilizerBonusLimit() {
        return fertilizerBonusLimit;
    }

    /** A method that returns true if a crop is harvestable false if not
     *
     * @return the harvestable status of a crop
     */
    public boolean isHarvestableStatus() {
        return harvestableStatus;
    }

    /** A method that sets the harvestable status if it's true or not
     *
     * @param harvestableStatus the harvestable status of a crop
     */
    public void setHarvestableStatus(boolean harvestableStatus) {
        this.harvestableStatus = harvestableStatus;
    }

    /** A method that gets the the water and fertilized status if it's true or not
     *
     * @return the water and fertilized status of a crop
     */
    public boolean isWaterAndFertilized() {
        return waterAndFertilized;
    }

    /** A method that sets the the water and fertilized status if it's true or not
     *
     * @param waterAndFertilized the water and fertilized status of a crop
     */
    public void setWaterAndFertilized(boolean waterAndFertilized) {
        this.waterAndFertilized = waterAndFertilized;
    }

}