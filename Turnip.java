/**
* Random class to produce random numbers
*/
import java.util.Random;

/**
 * Class Turnip that extends an abstract class Plants where it inherits all the attributes and method.
 * Take note that the method inherited, has no body, and thus was override to have a body.
 */
public class Turnip extends Plants{
    /**
     * Constructor for the class Turnip where the values of the attributes are initialized
     * @param player
     */
    public Turnip(Farmer player){
        this.seedName = "Turnip";
        this.cropType = "Root";
        this.waterNeeded = 1;
        this.waterBonusLimit = (super.waterBonusLimit + 2) + player.waterBonusIncrease;
        this.fertilizerNeeded = 0;
        this.fertilizerBonusLimit = (super.fertilizerBonusLimit + 1) + player.fertilizerBonusIncrease;
        this.baseSelling = super.baseSelling + 6;
        this.produceCount = setPlantProduce(1, 2);
        this.xpYield = 5;
        this.harvestTimeInDays = 2;
        this.seedCost = (super.seedCost + 5) + player.seedCostReduction;
        this.witherStatus = false;
        this.produceLowerBound = 1 + player.bonusPerProduce;;
        this.produceUpperBound = 2;
        this.harvestableStatus = false;
        this.waterAndFertilized = false;
    }

    /**
     * Sets the produce counts of the crops based on the min and max.
     * @param min The min represents the minimum produce count.
     * @param max The max represents the maximum produce count.
     * @return the produce count.
     */
    @Override
    public int setPlantProduce(int min, int max) {
        Random rand = new Random();
        //Produces a random value between the min and max
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}