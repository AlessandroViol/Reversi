package units.sdm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SwingReversi implements ReversiView {
    private Game game;
    private JFrame frame;

    Image blackImg;
    private ImageIcon blackIcon;
    Image whiteImg;
    private ImageIcon whiteIcon;
    Image noneImg;
    private ImageIcon noneIcon;
    Image allowedImg;
    private ImageIcon allowedIcon;

    public SwingReversi() {
        try {
            File file = new File("src/main/img/Black.png");
            blackImg = ImageIO.read(file);

            file = new File("src/main/img/White.png");
            whiteImg = ImageIO.read(file);

            file = new File("src/main/img/None.png");
            noneImg = ImageIO.read(file);

            file = new File("src/main/img/Allowed.png");
            allowedImg = ImageIO.read(file);

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

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

        //create title
        JPanel northPanel = new JPanel();

        JLabel title = new JLabel("Reversi!");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        northPanel.add(title, BorderLayout.NORTH);

        mainContainer.add(northPanel, BorderLayout.NORTH);

        //add button to start the game
        JButton startGameButton = new JButton("Start the game!");
        startGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        startGameButton.addActionListener(s -> game.turn());

        mainContainer.add(startGameButton, BorderLayout.SOUTH);

        //display window
        frame.setSize(480, 360);
        frame.setVisible(true);
    }

    @Override
    public void displayTurn() {
        Checkerboard checkerboard = game.getCheckerboard();
        Container mainContainer = frame.getContentPane();

        //resize the images to the container size
        int width = mainContainer.getWidth() / Checkerboard.SIZE;
        int height = mainContainer.getHeight() / Checkerboard.SIZE;
        loadScaledImages(width, height);

        //reset container
        mainContainer.removeAll();
        mainContainer.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridBagLayout());

        //draw squares
        for (int row = 0; row < Checkerboard.SIZE; row++)
            for (int column = 0; column < Checkerboard.SIZE; column++) {
                JLabel square = new JLabel();

                switch (checkerboard.getMatrix()[row][column]) {
                    case Checkerboard.B -> square.setIcon(blackIcon);
                    case Checkerboard.W -> square.setIcon(whiteIcon);
                    case Checkerboard.A -> square.setIcon(allowedIcon);
                    default -> square.setIcon(noneIcon);
                }

                centerPanel.add(square, new GridBagConstraints(column, row, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            }

        mainContainer.add(centerPanel, BorderLayout.CENTER);
        frame.revalidate();
    }

    private void loadScaledImages(int width, int height){
        int fitSize = width;
        if (width > height)
            fitSize = height;

        blackIcon = new ImageIcon(blackImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));

        whiteIcon = new ImageIcon(whiteImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));

        noneIcon = new ImageIcon(noneImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));

        allowedIcon = new ImageIcon(allowedImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));
    }

    @Override
    public void displayGameOver() {
    }

    @Override
    public void displayDraw() {
    }

    @Override
    public void displayNoMoves() {
    }

    @Override
    public void displayNotAllowed() {
    }
}
