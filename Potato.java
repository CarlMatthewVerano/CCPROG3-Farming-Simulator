/**
* Random class to produce random numbers
*/
import java.util.Random;
/**
 * Class Potato that extends an abstract class Plants where it inherits all the attributes and method.
 * Take note that the method inherited, has no body, and thus was override to have a body.
 */
public class Potato extends Plants{

    public Potato(Farmer player){
        this.seedName = "Potato";
        this.cropType = "Root";
        this.waterNeeded = 3;
        this.waterBonusLimit = (super.waterBonusLimit + 4) + player.waterBonusIncrease;
        this.fertilizerNeeded = 1;
        this.fertilizerBonusLimit = (super.fertilizerBonusLimit + 2) + player.fertilizerBonusIncrease;
        this.baseSelling = super.baseSelling + 3;
        this.produceCount = setPlantProduce(1, 10);
        this.xpYield = 12.5f;
        this.harvestTimeInDays = 5;
        this.seedCost = (super.seedCost + 5) + player.seedCostReduction;
        this.witherStatus = false;
        this.produceLowerBound = 1 + player.bonusPerProduce;
        this.produceUpperBound = 10;
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
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}