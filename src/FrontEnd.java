import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by carl-johaneriksson on 10/05/17.
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
        /*String string = System.getProperty("user.home");
        File file = new File(string + "/Documents/aleks3.png");
        BufferedImage myImage = ImageIO.read(file);
        ImagePanel image = new ImagePanel(myImage);
        image.setBounds(0, 0, 780, 200);

        cp.add(image);*/
        cp.add(setJPanel());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(780, 200);
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
        //gbc = new GridBagConstraints();
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
        btnSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double distance = Double.valueOf(txtDistance.getText());
                int speedLimit = Integer.parseInt(txtSpeedLimit.getText());
                txtDistance.setText("");
                txtSpeedLimit.setText("");
                plateRecog = new PlateRecog(distance, speedLimit);
                System.out.println("Sucsses");
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

        JList list = new JList(data.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);

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

        /*DefaultListModel listModel = new DefaultListModel();
        listModel.addListDataListener();
        */

        JButton btnChooseFile = new JButton("Pick");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.weightx = 1;

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, PNG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);

        btnChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reurnVal = chooser.showOpenDialog(btnChooseFile);
                if (reurnVal == JFileChooser.APPROVE_OPTION) {
                    File file = new File(chooser.getSelectedFile().getAbsolutePath());
                    String plate = plateRecog.findPlate(file);
                    Date date = new Date();
                    if (!plateRecog.storePlate(plate, date)) {
                        double speed = plateRecog.isSpeeding(plate, date);
                        //if (speed != -1) {
                            data.add(String.valueOf(speed));
                            System.out.println("Should Print");
                            System.out.println(speed);
                        //}
                    }
                }
            }
        });
        pnlMain.add(btnChooseFile, gbc);

        return pnlMain;
    }

    /**
     * ImagePanel, chose a file to set at background image for the JFrame
     */
    class ImagePanel extends JComponent {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}
