/**
* Random class to produce random numbers
*/
import java.util.Random;
/**
 * Class Mango that extends an abstract class Plants where it inherits all the attributes and method.
 * Take note that the method inherited, has no body, and thus was override to have a body.
 */
public class Mango extends Plants{
    public Mango(Farmer player){
        this.seedName = "Mango";
        this.cropType = "Fruit";
        this.waterNeeded = 7;
        this.waterBonusLimit = (super.waterBonusLimit + 7) + player.waterBonusIncrease;
        this.fertilizerNeeded = 4;
        this.fertilizerBonusLimit = (super.fertilizerBonusLimit + 4) + player.fertilizerBonusIncrease;
        this.baseSelling = super.baseSelling + 8;
        this.produceCount = setPlantProduce(5, 15);
        this.xpYield = 25;
        this.harvestTimeInDays = 10;
        this.seedCost = (super.seedCost + 100) + player.seedCostReduction;;
        this.witherStatus = false;
        this.produceLowerBound = 5 + player.bonusPerProduce;;
        this.produceUpperBound = 15;
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