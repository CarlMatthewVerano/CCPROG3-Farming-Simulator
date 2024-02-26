/**
* Random class to produce random numbers
*/
import java.util.Random;
/**
 * Class Apple that extends an abstract class Plants where it inherits all the attributes and method.
 * Take note that the method inherited, has no body, and thus was override to have a body.
 */
public class Apple extends Plants{
    public Apple(Farmer player){
        this.seedName = "Apple";
        this.cropType = "Fruit";
        this.waterNeeded = 7;
        this.waterBonusLimit = (super.waterBonusLimit + 7) + player.waterBonusIncrease;
        this.fertilizerNeeded = 5;
        this.fertilizerBonusLimit = (super.fertilizerBonusLimit + 5) + player.fertilizerBonusIncrease;
        this.baseSelling = super.baseSelling + 5;
        this.produceCount = setPlantProduce(10, 15);
        this.xpYield = 25;
        this.harvestTimeInDays = 10;
        this.seedCost = (super.seedCost + 200) + player.seedCostReduction;;
        this.witherStatus = false;
        this.produceLowerBound = 10 + player.bonusPerProduce;;
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