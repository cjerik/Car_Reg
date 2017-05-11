import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by carl-johaneriksson on 10/05/17.
 */
public class FrontEnd {

    public static void main(String[] args) throws Exception {
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.run();
    }

    public void run() throws Exception {
        setJFrame();
    }


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


    private JPanel setJPanel() {
        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblDistance = new JLabel("Distance");
        //gbc = new GridBagConstraints(); Uppdatera vid varje ny component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1; // Typ hur det scalar eller något sånt, ganska oviktig sålänge alla har samma
        pnlMain.add(lblDistance, gbc); // Lägg till i panel

        JTextField txtName = new JTextField();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 0, 10);
        gbc.weightx = 1;
        pnlMain.add(txtName, gbc);

        JLabel lblSpeedLimit = new JLabel("Speed limit");
        //gbc = new GridBagConstraints(); Uppdatera vid varje ny component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1; // Typ hur det scalar eller något sånt, ganska oviktig sålänge alla har samma
        pnlMain.add(lblSpeedLimit, gbc); // Lägg till i panel

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
                txtSpeedLimit.getAction();
            }
        });
        pnlMain.add(btnSet, gbc);

        JLabel lblChooseFile = new JLabel("Choose picture");
        //gbc = new GridBagConstraints(); Uppdatera vid varje ny component
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // What row and col to take
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);  //Margin
        gbc.weightx = 1; // Typ hur det scalar eller något sånt, ganska oviktig sålänge alla har samma
        pnlMain.add(lblChooseFile, gbc); // Lägg till i panel

        JButton btnChooseFile = new JButton("Pick");
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.weightx = 1;

        JFileChooser chooser = new JFileChooser();

        btnChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser.showOpenDialog(btnChooseFile);
            }
        });
        pnlMain.add(btnChooseFile, gbc);


        Object[] data = new Object[4];
        data[0] = "Hej";
        data[1] = "HejHej";
        data[2] = "HejHejHej";
        data[3] = "HejHejHejHej";

        JList list = new JList(data);
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

        return pnlMain;
    }

    public String setDistanceAndSpeed() {
        return "";
    }

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