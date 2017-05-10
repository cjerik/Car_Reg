import javax.swing.*;
import java.awt.*;

/**
 * Created by carl-johaneriksson on 10/05/17.
 */
public class FrontEnd {

    public static void main(String[] args) {
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.run();
    }

    public void run() {
        setJFrame();
    }


    private void setJFrame() {
        JFrame frame = new JFrame("CarReg");
        frame.getContentPane().add(setJPanel());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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
}
