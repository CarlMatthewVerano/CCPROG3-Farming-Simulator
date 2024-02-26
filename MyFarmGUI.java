import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

/** This class represents the GUI of the game. This
 * would be what the player sees and the medium on
 * how they would interact and play the game.
 *
 */
public class MyFarmGUI extends JFrame{

    final int rowBoard = 10;
    final int colBoard = 5;
    private JButton Sleep;
    private JButton NewGame;
    private JButton UpgradeStatus;

    private final String[] rockScatter = new String[10];
    public JButton[][] plantArea;
    private JLabel CurrentDay;
    private JLabel FarmerObjectCoins;
    private JLabel FarmerExperience;
    private JLabel FarmerLevel;
    private JLabel FarmerStatus;
    private JLabel GameFeedback;

    /*
    The declarations of the icons/images
    */
    public Icon rock = new ImageIcon(new ImageIcon("ASSETS/ROCK.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon plowed = new ImageIcon(new ImageIcon("ASSETS/PLOWED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon water = new ImageIcon(new ImageIcon("ASSETS/WATER.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon watered = new ImageIcon(new ImageIcon("ASSETS/WATERED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon apple = new ImageIcon(new ImageIcon("ASSETS/APPLE.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon carrot = new ImageIcon(new ImageIcon("ASSETS/CARROT.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon mango = new ImageIcon(new ImageIcon("ASSETS/MANGO.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon potato = new ImageIcon(new ImageIcon("ASSETS/POTATO.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon rose = new ImageIcon(new ImageIcon("ASSETS/ROSE.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon sunflower = new ImageIcon(new ImageIcon("ASSETS/SUNFLOWER.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon tulips = new ImageIcon(new ImageIcon("ASSETS/TULIPS.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon turnip = new ImageIcon(new ImageIcon("ASSETS/TURNIP.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon seed = new ImageIcon(new ImageIcon("ASSETS/SEED PLANT.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon wateredAndFertilized = new ImageIcon(new ImageIcon("ASSETS/WATEREDANDFERTILIZED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon fertilized = new ImageIcon(new ImageIcon("ASSETS/FERTILIZED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon withered = new ImageIcon(new ImageIcon("ASSETS/WITHERED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));
    public Icon unplowed = new ImageIcon(new ImageIcon("ASSETS/UNPLOWED.png").getImage().getScaledInstance(50,
            50, Image.SCALE_DEFAULT));

    public JComboBox tileActions;

    public String specificTileActions;

    /** Constructor for the GUI of main game
     *
     * @throws IOException
     */
    public MyFarmGUI() throws IOException {
        super("My Farm");

        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        components();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /** Method wherein it contains the components (e.g. button, label, combobox)
     *  that would be shown in the GUI
     *
     * @throws IOException
     */
    private void components() throws IOException {

        JPanel North = new JPanel();
        North.setLayout(new BoxLayout(North, BoxLayout.PAGE_AXIS));

        JPanel Header = new JPanel();
        Header.setLayout(new FlowLayout());
        Header.setBackground(Color.PINK);

        JPanel SubHeader = new JPanel();

        CurrentDay = new JLabel("Day 1");
        CurrentDay.setForeground(Color.BLACK);
        CurrentDay.setFont(new Font("Arial", Font.BOLD, 20));
        Header.add(CurrentDay);

        Sleep = new JButton("Zzz...(Sleep)");
        Sleep.setSize(10, 10);
        SubHeader.add(Sleep);

        NewGame = new JButton("New Game!");
        NewGame.setSize(10, 10);
        SubHeader.add(NewGame);

        North.add(Header);
        North.add(SubHeader);

        add(North, BorderLayout.NORTH);

        /*
        Farmer Profile part of the GUI
         */

        JPanel FarmerProfile = new JPanel();
        FarmerProfile.setLayout(new BoxLayout(FarmerProfile, BoxLayout.PAGE_AXIS));

        JLabel FarmerProfileTitle = new JLabel("Farmer Profile");

        FarmerProfile.add(FarmerProfileTitle);

        FarmerObjectCoins = new JLabel("ObjectCoins: ");

        FarmerProfile.add(FarmerObjectCoins);

        FarmerExperience = new JLabel("Farmer Experience: ");

        FarmerProfile.add(FarmerExperience);

        FarmerLevel = new JLabel("Farmer Level: ");

        FarmerProfile.add(FarmerLevel);

        FarmerStatus = new JLabel("Farmer Status: ");

        FarmerProfile.add(FarmerStatus);

        UpgradeStatus = new JButton("Upgrade Status");
        FarmerProfile.add(UpgradeStatus);

        GameFeedback = new JLabel("Feedback: ");
        FarmerProfile.add(GameFeedback);

        add(FarmerProfile, BorderLayout.WEST);

        /*
        Tools and Plants part of the GUI
         */

        JPanel playerActions = new JPanel();
        playerActions.setLayout(new BoxLayout(playerActions, BoxLayout.PAGE_AXIS));

        String arrTileActions[] = {"Plow",
                "Water",
                "Fertilizer",
                "Pick Axe",
                "Shovel",
                "Harvest",
                "Turnip",
                "Carrot",
                "Potato",
                "Rose",
                "Tulips",
                "Sunflower",
                "Mango",
                "Apple"};

        tileActions = new JComboBox<>(arrTileActions);
        JLabel titleTileActions = new JLabel("PLAYER ACTIONS!");
        tileActions.setMaximumSize(new Dimension(125, 30));
        titleTileActions.setAlignmentX(tileActions.getAlignmentX());

        playerActions.add(titleTileActions);
        playerActions.add(Box.createVerticalStrut(10));
        playerActions.add(tileActions);

        add(playerActions, BorderLayout.EAST);
        
        /*
        Plant Area of the GUI
         */
        JPanel PlantArea = new JPanel();
        PlantArea.setLayout(new BoxLayout(PlantArea, BoxLayout.Y_AXIS));

        plantArea = new JButton[rowBoard][colBoard];

        JPanel[] rowPanels = new JPanel[10];
        Border tileBorder = BorderFactory.createLineBorder(Color.black);

        setRockScatter(rockScatter);
        
        /*
        Loop that sets the tiles as buttons
         */
        for(int rowPanel = 0; rowPanel < rowBoard; rowPanel++){
            rowPanels[rowPanel] = new JPanel();
            rowPanels[rowPanel].setLayout(new GridBagLayout());

            for(int colLabels = 0; colLabels < colBoard; colLabels++){
                plantArea[rowPanel][colLabels] = new JButton();
                plantArea[rowPanel][colLabels].setMaximumSize(new Dimension(unplowed.getIconWidth(), unplowed.getIconHeight()));

                if(rockScatter[rowPanel].charAt(colLabels) == '1'){
                    plantArea[rowPanel][colLabels].setIcon(rock);
                }
                else
                    plantArea[rowPanel][colLabels].setIcon(unplowed);

                plantArea[rowPanel][colLabels].setOpaque(true);
                rowPanels[rowPanel].add(plantArea[rowPanel][colLabels]);
                rowPanels[rowPanel].add(Box.createHorizontalStrut(10));
                plantArea[rowPanel][colLabels].setBorder(tileBorder);
            }

            PlantArea.add(rowPanels[rowPanel]);
        }

        add(PlantArea, BorderLayout.CENTER);
    }

    /** Method wherein it reads 10 random lines from an existing
     *  text file which would then be used to create the rock
     *  layout for the game
     *
     * @param rockScatter   array of strings that symbolize the 
     *                      rock layout of each line
     * @throws IOException
     */
    public void setRockScatter(String[] rockScatter) throws IOException {
        RandomAccessFile fileCombination = new RandomAccessFile("RockCombinations.txt", "r");

        do {
            for (int i = 0; i < 10; i++) {
                int randNumber = randomNumber();
                int pos = randomLine(randNumber);

                fileCombination.seek(pos);
                String tempScatter = fileCombination.readLine();
                rockScatter[i] = tempScatter;
            }
        } while (!checkRockCount(rockScatter));

        fileCombination.close();
    }

    /** Returns a random number from 1 to 32
     *
     * @return a random number from 1 to 32
     */
    public int randomNumber() {

        Random rand = new Random();
        int randomNumber = rand.nextInt(1, 32);

        return randomNumber;
    }

    /** Returns a line number
     *
     * Each line number is symbolized in multiples of 10.
     * For example: Line 1 would be Line 7, and Line 2 would
     *  be line 14, and so on...
     *
     * @param n that symbolizes line number in the txt file
     * @return the random line
     */
    public int randomLine(int n) {

        n = (7 * n) - 7;

        return n;

    }

    /** A method that checks if the rock count is from
     *      10 to 30. Returns true if the rock count is within
     *      the range. Ohterwise, returns false.
     *
     * @param rockArea  array of strings that symbolize the 
     *                      rock layout of each line
     * @return the boolean value that symbolizes the rock count
     */
    public static Boolean checkRockCount(String[] rockArea) {

        Boolean withinCount = false;
        int rockCount = 0;

        for (int words = 0; words < 10; words++) {
            for (int letter = 0; letter < 5; letter++) {
                if (rockArea[words].charAt(letter) == '1') {
                    rockCount++;
                }
            }
        }
        if (rockCount >= 10 && rockCount <= 30)
            withinCount = true;

        System.out.println(rockCount);

        return withinCount;
    }

    /** A method that sets the current day of the game
     *
     * @param day   the current day of the game
     */
    public void setDay(int day){
        CurrentDay.setText("Day " + day);
    }

    /** A method that displays the object coins of the player
     *
     * @param obC   the object coins of the player
     */
    public void displayObjectCoins(float obC){
        FarmerObjectCoins.setText("ObjectCoins: " + obC);
    }

    /** A method that that displays the experience coins of the player
     *
     * @param xp    the experience of the player
     */
    public void displayExperience(double xp){
        FarmerExperience.setText("Experience: " + xp);
    }

    /** A method that displays the level of the player
     *
     * @param level the level of the player
     */
    public void displayLevel(int level){ FarmerLevel.setText("Farmer Level: " + level); }

    /** A method that displays the farmer status of the player
     *
     * @param status the farmer status of the player
     */
    public void displayFarmerStatus(String status){
        FarmerStatus.setText("Farmer Status: " + status);
    }

    /** A method that would set the actionlisteners to all the JButtons, JComboBox, etc.
     *
     * @param listener is an object instantiated from the interface ActionListener
     *                 this checks if the event performed actually invokes the action performed method
     */
    public void setActionListener(ActionListener listener) {
        Sleep.addActionListener(listener);
        NewGame.addActionListener(listener);
        UpgradeStatus.addActionListener(listener);
        for(int i = 0; i < rowBoard; i++){
            for(int j = 0; j < colBoard; j++){
                plantArea[i][j].addActionListener(listener);
            }
        }
        tileActions.addActionListener(listener);
    }

    /** A method that gets the GameFeedBack
     *
     * @return the GameFeedBack text
     */
    public JLabel getGameFeedback() {
        return GameFeedback;
    }

    /** A method that sets the GameFeedBack
     *
     * @param gameFeedback represents the text that this label shows in the gui
     */
    public void setGameFeedback(JLabel gameFeedback) {
        GameFeedback = gameFeedback;
    }
}