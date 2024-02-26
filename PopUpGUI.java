import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/** A class that popups as a GUI that prompts the user to decide if they
 * will continue playing or not. This only appears after game ends.
 * 
 */
public class PopUpGUI extends JFrame implements ActionListener {

    public MyFarmModel model = new MyFarmModel();
    public JButton yes;
    public JButton no;

    /** Constructor for PopUpGUI where it sets the visibility, calls the components() method, etc.
     *
     */
    public PopUpGUI(){
        super("FEEDBACK");

        setSize(400, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        components();
        setActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /** Method where it contains the components of the PopUpGUI
     *
     */
    private void components(){

        JPanel overAllPanel = new JPanel(new GridBagLayout());
        add(overAllPanel);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        overAllPanel.add(panel);

        JLabel title = new JLabel("Game Over. Start a new game?");
        panel.add(title);

        JPanel choices = new JPanel(new FlowLayout());
        yes = new JButton("Yes");
        no = new JButton("No");
        choices.add(yes);
        choices.add(no);

        overAllPanel.add(choices);
    }

    /** A method that would set the actionlisteners to all the JButtons, JComboBox, etc.
     *
     * @param listener is an object instantiated from the interface ActionListener
     *                 this checks if the event performed actually invokes the action performed method
     */
    public void setActionListener(ActionListener listener) {
        yes.addActionListener(listener);
        no.addActionListener(listener);
    }

    private MyFarmGUI gui;
    private PopUpGUI popUpGUI;

    /** The method takes all the action-related functions in this class
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Yes")){
            try {
                model.newGame(new Board(), new Farmer(), gui);
                this.setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("No")){
            System.exit(0);
        }
    }
}
