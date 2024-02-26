/** Imports classes as required
 *
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/** MyFarmController represents the Controller of the MVC structure of the game
 * 
 */
public class MyFarmController implements ActionListener{
    private Farmer player = new Farmer();
    private Board board = new Board();
    private MyFarmGUI gui;
    private MyFarmModel model;
    private String tileMsg;

    /** Constructor for MyFarmController
     * 
     * @param model represents MODEL
     * @param gui represents GUI
     */
    public MyFarmController(MyFarmModel model, MyFarmGUI gui) {
        this.gui = gui;
        this.model = model;

        board.setTile();
        model.setInventory();

        updateView();

        gui.setActionListener(this);
    }

    /** Updates the view, such as day, obj coins, etc.
     * 
     */
    public void updateView(){
        gui.setDay(player.getDay());
        gui.displayObjectCoins(player.getObjectCoins());
        gui.displayFarmerStatus(player.statusName);
        gui.displayExperience(player.getFarmerExperience());
        gui.displayLevel(player.getFarmerLevel());
        model.setGameEnd(false);
        model.checkGameEndStatus(board, player);
        if(model.isGameEnd()){
            gui.setVisible(false);
            new PopUpGUI();
        }
    }

    /** The method takes all the action-related functions in the program
     * 
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        model.levelUp(player);
        updateView();

        for (int i = 0; i < board.getRowBoard(); i++) {
            for (int j = 0; j < board.getColBoard(); j++) {
                if(gui.plantArea[i][j].getIcon().equals(gui.rock))
                    board.getTiles()[i][j].setRockStatus(true);
            }
        }
        if(e.getActionCommand().equals("New Game!")){

            gui.setVisible(false);
            try {
                model.newGame(board, player, gui);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            updateView();
        }
        if(e.getActionCommand().equals("Zzz...(Sleep)")){
            player.nextDay();
            updateView();
        }
        if(e.getActionCommand().equals("Upgrade Status")){
            String status = player.statusName;
            switch(status){
                case "":
                    if(player.getFarmerLevel() >= new RegisteredFarmer().levelRequirement){
                        player = player.registerStatus(player);
                        player.setObjectCoins(player.getObjectCoins() - player.registrationFee);
                        updateView();
                    }
                    break;
                case "Registered":
                    if(player.getFarmerLevel() >= new DistinguishedFarmer().levelRequirement){
                        player = player.registerStatus(player);
                        player.setObjectCoins(player.getObjectCoins() - player.registrationFee);
                        updateView();
                    }
                    break;
                case "Distinguished":
                    if(player.getFarmerLevel() >= new LegendaryFarmer().levelRequirement){
                        player = player.registerStatus(player);
                        player.setObjectCoins(player.getObjectCoins() - player.registrationFee);
                        updateView();
                    }
                    break;
            }
            updateView();
        }

        if(e.getSource() == gui.tileActions) {
            JComboBox cb = (JComboBox) e.getSource();
            tileMsg = (String) cb.getSelectedItem();
        }
        if(tileMsg != null) {
            if (tileMsg.equals("Plow")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            if (!board.getTiles()[i][j].isPlowedStatus() && !board.getTiles()[i][j].isRockStatus()) {
                                model.toolSet[0].plowTile(board.getTiles()[i][j], player);
                                gui.plantArea[i][j].setIcon(gui.plowed);
                                gui.getGameFeedback().setText("Plowed: " + i + " : " + j);
                                updateView();
                            }
                            else
                                gui.getGameFeedback().setText("Plowed Already at tile: " + i + " : " + j);
                        }
                    }
                }
            } else if (tileMsg.equals("Water")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            if (board.getTiles()[i][j].isPlowedStatus()) {
                                if (board.getTiles()[i][j].isCropStatus()) {
                                    gui.getGameFeedback().setText("Watered: " + i + " : " + j);
                                    model.toolSet[1].waterPlant(board.getTiles()[i][j], player);
                                    if (board.getTiles()[i][j].getTimesWatered() == board.getTiles()[i][j].getCrop().waterNeeded) {
                                        gui.plantArea[i][j].setIcon(gui.watered);
                                    }
                                    updateView();
                                }
                            }
                        }
                    }
                }
            } else if (tileMsg.equals("Harvest")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            if (board.getTiles()[i][j].isCropStatus()) {
                                if (!board.getTiles()[i][j].getCrop().isWitherStatus()) {
                                    if(board.getTiles()[i][j].getCrop().isHarvestableStatus()){
                                        gui.getGameFeedback().setText("Harvested at: " + i + " : " + j);
                                        player.harvest(board.getTiles()[i][j]);
                                        gui.plantArea[i][j].setIcon(gui.unplowed);
                                        updateView();
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (tileMsg.equals("Fertilizer")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            if (board.getTiles()[i][j].isPlowedStatus()) {
                                if (board.getTiles()[i][j].isCropStatus()) {
                                    model.toolSet[2].fertilizePlant(board.getTiles()[i][j], player);
                                    gui.getGameFeedback().setText("Fertilized at: " + i + " : " + j);
                                    if (board.getTiles()[i][j].getTimesFertilized() >= board.getTiles()[i][j].getCrop().fertilizerNeeded) {
                                        gui.plantArea[i][j].setIcon(gui.fertilized);
                                    }
                                    updateView();
                                }
                            }
                        }
                    }
                }
            } else if (tileMsg.equals("Shovel")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            model.toolSet[4].shovelTile(board.getTiles()[i][j], player);
                            gui.getGameFeedback().setText("Shovelled at: " + i + " : " + j);
                            if(!board.getTiles()[i][j].isRockStatus()){
                                if(!board.getTiles()[i][j].isPlowedStatus()){
                                    gui.plantArea[i][j].setIcon(gui.unplowed);
                                }
                            }
                            updateView();
                        }
                    }
                }
            } else if (tileMsg.equals("Pick Axe")) {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            gui.getGameFeedback().setText("Pick Axed at: " + i + " : " + j);
                            model.toolSet[3].mineTile(board.getTiles()[i][j], player);
                            gui.plantArea[i][j].setIcon(gui.unplowed);
                            updateView();
                        }
                    }
                }
            }
            //planting
            else {
                for (int i = 0; i < board.getRowBoard(); i++) {
                    for (int j = 0; j < board.getColBoard(); j++) {
                        if (e.getSource() == gui.plantArea[i][j]) {
                            if (board.getTiles()[i][j].isPlowedStatus()) {
                                if (!board.getTiles()[i][j].isCropStatus()) {
                                    if(tileMsg.equals("Apple") || tileMsg.equals("Mango")){
                                        if(board.checkTreeEligibility(i, j)){
                                            gui.getGameFeedback().setText("Planted at: " + i + " : " + j);
                                            player.plant(board.getTiles()[i][j], tileMsg, player);
                                            gui.plantArea[i][j].setIcon(gui.seed);
                                            updateView();
                                        }
                                        else
                                            gui.getGameFeedback().setText("Can't plant at: " + i + " : " + j);
                                    }
                                    else{
                                        gui.getGameFeedback().setText("Planted at: " + i + " : " + j);
                                        player.plant(board.getTiles()[i][j], tileMsg, player);
                                        gui.plantArea[i][j].setIcon(gui.seed);
                                        updateView();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < board.getRowBoard(); i++) {
                for (int j = 0; j < board.getColBoard(); j++) {
                    if (board.getTiles()[i][j].isCropStatus()) {
                        model.levelUp(player);
                        model.checkHarvestWither(board, player, i, j);
                        model.checkWateredAndFertlized(board, player, i, j);
                        if (board.getTiles()[i][j].getCrop().isWitherStatus()) {
                            gui.plantArea[i][j].setIcon(gui.withered);
                        }
                        if (board.getTiles()[i][j].getCrop().isWaterAndFertilized()) {
                            gui.plantArea[i][j].setIcon(gui.wateredAndFertilized);
                        }
                        if (board.getTiles()[i][j].getCrop().isHarvestableStatus()) {
                            String namePlant = board.getTiles()[i][j].getCrop().getSeedName();
                            switch (namePlant) {
                                case "Apple" -> gui.plantArea[i][j].setIcon(gui.apple);
                                case "Mango" -> gui.plantArea[i][j].setIcon(gui.mango);
                                case "Carrot" -> gui.plantArea[i][j].setIcon(gui.carrot);
                                case "Rose" -> gui.plantArea[i][j].setIcon(gui.rose);
                                case "Sunflower" -> gui.plantArea[i][j].setIcon(gui.sunflower);
                                case "Tulips" -> gui.plantArea[i][j].setIcon(gui.tulips);
                                case "Turnip" -> gui.plantArea[i][j].setIcon(gui.turnip);
                                case "Potato" -> gui.plantArea[i][j].setIcon(gui.potato);
                            }
                        }
                    }
                }
            }
        }
    }
}