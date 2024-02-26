/** A child class of the Farmer class. This class
 * represents a player that has the Legendary
 * Farmer Status with its corresponding details.
 *
 */
public class LegendaryFarmer extends Farmer{
    /**
     * Farmer constructor that sets the initial values of the farmer.
     */
    public LegendaryFarmer() {
        this.statusName = "Legendary";
        this.levelRequirement = 15;
        this.bonusPerProduce = 4;
        this.seedCostReduction = -3;
        this.waterBonusIncrease = 2;
        this.fertilizerBonusIncrease = 1;
        this.registrationFee = 400;
    }
    /** Method that increments the day
     *
     */
    public void nextDay(){
        day++;
    }
    /** A method that computes the total harvest price
     *
     * @param tile is representative of a tile of the board
     * @return final harvest price
     */
    public float totalHarvestPrice(Tile tile){
        float finalHarvestPrice = harvestTotal(tile) + waterBonus(tile) + fertilizerBonus(tile);

        if(tile.getCrop().getCropType().equals("Flower")){
            finalHarvestPrice = finalHarvestPrice * 1.1f;
        }

        return finalHarvestPrice;
    }
    /** A method that computes the total harvest value
     *
     * @param tile is representative of a tile of the board
     * @return total harvest value
     */
    public float harvestTotal(Tile tile){
        float harvestTotal = tile.getCrop().getProduceCount() * tile.getCrop().getBaseSelling();

        return harvestTotal;
    }
    /** A method that computes the water bonus
     *
     * @param tile is representative of a tile of the board
     * @return water bonus
     */
    public float waterBonus(Tile tile){
        float waterBonus = 0;

        if(tile.getTimesWatered() > tile.getCrop().getWaterBonusLimit()){
            tile.setTimesWatered(tile.getCrop().getWaterBonusLimit());
        }

        waterBonus = harvestTotal(tile) * 0.2f * (tile.getTimesWatered() - 1);

        return waterBonus;
    }

    /** A method that computes fertilizer bonus
     *
     * @param tile is representative of a tile of the board
     * @return fertilizer bonus
     */
    public float fertilizerBonus(Tile tile){
        float fertilizerBonus = 0;

        if(tile.getTimesFertilized() > tile.getCrop().getFertilizerBonusLimit()){
            tile.setTimesFertilized(tile.getCrop().getFertilizerBonusLimit());
        }

        fertilizerBonus = harvestTotal(tile) * 0.5f * (tile.getTimesFertilized());

        return fertilizerBonus;
    }

    /** A method that plants a crop to the tile
     *
     * @param tile is representative of a tile of the board
     * @param plantName name of the crop
     * @param player represents the Farmer
     */
    @Override
    public void plant(Tile tile, String plantName, Farmer player){
        tile.setCropStatus(true);

        switch (plantName) {
            case "Apple" -> tile.setCrop(new Apple(player));
            case "Mango" -> tile.setCrop(new Mango(player));
            case "Carrot" -> tile.setCrop(new Carrot(player));
            case "Rose" -> tile.setCrop(new Rose(player));
            case "Sunflower" -> tile.setCrop(new Sunflower(player));
            case "Tulips" -> tile.setCrop(new Tulips(player));
            case "Turnip" -> tile.setCrop(new Turnip(player));
            case "Potato" -> tile.setCrop(new Potato(player));
        }
        tile.setDayPlanted(day);
        tile.setDayToHarvest(tile.getDayPlanted() + tile.getCrop().getHarvestTimeInDays());
        objectCoins -= tile.getCrop().getSeedCost();
        System.out.println(objectCoins);
    }

    /** A method that harvests a crop from a tile
     *
     * @param tile is representative of a tile of the board
     */
    public void harvest(Tile tile){
        objectCoins += totalHarvestPrice(tile);
        farmerExperience += tile.getCrop().getXpYield();
        tile.setTimesWatered(0);
        tile.setTimesFertilized(0);
        tile.setCropStatus(false);
        tile.setPlowedStatus(false);
        tile.setCrop(null);
    }
}