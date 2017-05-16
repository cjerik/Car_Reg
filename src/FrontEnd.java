import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Front-end for the CarReg application
 * Start by calling Frontend.run()
 */
public class FrontEnd {
    PlateRecog plateRecog;
    ArrayList<String> data;

    public FrontEnd() {
        this.data = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.run();
    }

    /**
     * Runs the acutal program, public method, can be called from a object.
     * @throws Exception
     */
    public void run() throws Exception {
        setJFrame();
    }

    /**
     * Sets the GUI frame. Calls for setJPanel() which intiates the
     * GUI.
     * @throws Exception
     */
    private void setJFrame() throws Exception {
        JFrame frame = new JFrame("CarReg");
        Container cp = frame.getContentPane();
        cp.add(setJPanel());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Sets the JPanel, with all buttons and Text Fields.
     * @return the JPanel for the GUI.
     */
    private JPanel setJPanel() {
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblDistance = new JLabel("Distance");
        //gbc = new GridBagConstraints(); Update with each new component.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1; // How it scales, irrelevant as long as all buttons is the same
        pnlMain.add(lblDistance, gbc); // Add to panel.

        JTextField txtDistance = new JTextField();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 0, 10);
        gbc.weightx = 1;
        pnlMain.add(txtDistance, gbc);

        JLabel lblSpeedLimit = new JLabel("Speed limit");
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1;
        pnlMain.add(lblSpeedLimit, gbc);

        JTextField txtSpeedLimit = new JTextField();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 0, 10);
        gbc.weightx = 1;

        pnlMain.add(txtSpeedLimit, gbc);

        JButton btnSet = new JButton("Set");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.weightx = 1;
        btnSet.addActionListener(new ActionListener() { // Listen for button-click
            @Override
            public void actionPerformed(ActionEvent e) {
                double distance = Double.valueOf(txtDistance.getText());
                int speedLimit = Integer.parseInt(txtSpeedLimit.getText());
                txtDistance.setText(""); // Reset field
                txtSpeedLimit.setText(""); // Reset field
                plateRecog = new PlateRecog(distance, speedLimit); // Initiat plateRacog
            }
        });
        pnlMain.add(btnSet, gbc);

        JLabel lblChooseFile = new JLabel("Choose picture");
        //gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1;
        pnlMain.add(lblChooseFile, gbc);

        DefaultListModel listModel = new DefaultListModel();
        JList list = new JList(listModel);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(180, 100));
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1;
        pnlMain.add(listScroller, gbc);

        JButton btnChooseFile = new JButton("Pick");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.weightx = 1;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);

        btnChooseFile.addActionListener(new ActionListener() { // If user sends car
            @Override
            public void actionPerformed(ActionEvent e) {
                int reurnVal = chooser.showOpenDialog(btnChooseFile);
                if (reurnVal == JFileChooser.APPROVE_OPTION) {
                    File file = new File(chooser.getSelectedFile().getAbsolutePath());
                    String plate = plateRecog.findPlate(chooser.getSelectedFile());
                    Date date = new Date();
                    if (!plateRecog.storePlate(plate, date)) {
                        double speed = plateRecog.isSpeeding(plate, date);
                        if (speed != -1) {
                            listModel.addElement(plate + " " + Math.floor(speed) + "km/h"); // Show the platenumber and speed
                        }
                    }
                }
            }
        });
        pnlMain.add(btnChooseFile, gbc);

        return pnlMain;
    }
}
