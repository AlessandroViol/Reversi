package units.sdm;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showConfirmDialog;

public class SwingReversi implements ReversiView {
    private Game game;
    private JFrame frame;

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(this::displayGameStart);
    }

    public void displayGameStart() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();

        JLabel title = new JLabel("Reversi!");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        northPanel.add(title, BorderLayout.NORTH);

        mainContainer.add(northPanel, BorderLayout.NORTH);

        frame.setSize(480, 360);
        frame.setVisible(true);
    }

    @Override
    public void displayTurn(){}

    @Override
    public void displayGameOver(){}

    @Override
    public void displayDraw(){}

    @Override
    public void displayNoMoves(){}

    @Override
    public void displayNotAllowed(){}
}
