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

        JLabel title = new JLabel("REVERSI!");
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        northPanel.add(title);

        mainContainer.add(northPanel, BorderLayout.NORTH);

        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());


        
        JPanel centerPanel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel("Please enter player names");
        label.setFont(new Font("Calibri", Font.ITALIC, 10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(label);

        JLabel labelPlayerBlack = new JLabel("Player Black");
        centerPanel.add(labelPlayerBlack, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 4, 0, 0), 0, 0));

        JTextField playerBlackName = new JTextField("", 5);
        playerBlackName.setHorizontalAlignment(SwingConstants.CENTER);
        playerBlackName.addActionListener(b -> game.setPlayerBlack(playerBlackName.getText()));
        centerPanel.add(playerBlackName, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));


        JLabel labelPlayerWhite = new JLabel("Player White");
        centerPanel.add(labelPlayerWhite, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 4, 0, 0), 0, 0));

        JTextField playerWhiteName = new JTextField("", 5);
        playerBlackName.setHorizontalAlignment(SwingConstants.CENTER);
        playerWhiteName.addActionListener(w -> game.setPlayerWhite(playerWhiteName.getText()));
        centerPanel.add(playerWhiteName, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));


        //add button to start the game
        JButton startGameButton = new JButton("Start the game!");
        startGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        startGameButton.addActionListener(s -> game.turn());

        mainContainer.add(startGameButton, BorderLayout.SOUTH);

        cp.add(startGameButton, BorderLayout.SOUTH);
        cp.add(northPanel, BorderLayout.NORTH);
        cp.add(centerPanel, BorderLayout.CENTER);

        //display window
        frame.setSize(500, 500);
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
