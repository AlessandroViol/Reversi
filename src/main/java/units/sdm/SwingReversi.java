package units.sdm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class SwingReversi implements ReversiView {
    private Game game;
    private JFrame frame;

    private final Color BG_COLOR = new Color(208, 206, 197);

    private Image blackImg;
    private ImageIcon blackIcon;
    private Image whiteImg;
    private ImageIcon whiteIcon;
    private Image noneImg;
    private ImageIcon noneIcon;
    private Image allowedImg;
    private ImageIcon allowedIcon;

    private Image blackTokenImg;
    private ImageIcon blackTokenIcon;
    private Image whiteTokenImg;
    private ImageIcon whiteTokenIcon;

    private Image topImg;
    private ImageIcon topIcon;
    private Image bottomImg;
    private ImageIcon bottomIcon;
    private Image rightImg;
    private ImageIcon rightIcon;
    private Image leftImg;
    private ImageIcon leftIcon;

    private Image topRImg;
    private ImageIcon topRIcon;
    private Image topLImg;
    private ImageIcon topLIcon;
    private Image bottomRImg;
    private ImageIcon bottomRIcon;
    private Image bottomLImg;
    private ImageIcon bottomLIcon;

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

            file = new File("src/main/img/BlackToken.png");
            blackTokenImg = ImageIO.read(file);

            file = new File("src/main/img/WhiteToken.png");
            whiteTokenImg = ImageIO.read(file);

            file = new File("src/main/img/Top.png");
            topImg = ImageIO.read(file);

            file = new File("src/main/img/Bottom.png");
            bottomImg = ImageIO.read(file);

            file = new File("src/main/img/Right.png");
            rightImg = ImageIO.read(file);

            file = new File("src/main/img/Left.png");
            leftImg = ImageIO.read(file);

            file = new File("src/main/img/TopRight.png");
            topRImg = ImageIO.read(file);

            file = new File("src/main/img/TopLeft.png");
            topLImg = ImageIO.read(file);

            file = new File("src/main/img/BottomRight.png");
            bottomRImg = ImageIO.read(file);

            file = new File("src/main/img/BottomLeft.png");
            bottomLImg = ImageIO.read(file);

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
        frame.setSize(1080, 720);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(BG_COLOR);

        //create title
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);

        JLabel title = new JLabel("REVERSI!");
        title.setOpaque(false);
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        northPanel.add(title);

        mainContainer.add(northPanel, BorderLayout.NORTH);

        //add small help label
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel label = new JLabel("Please enter player names");
        label.setFont(new Font("Calibri", Font.ITALIC, 10));
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(label, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 5));

        //add text fields and labels to get player names
        JPanel panelNameBlack = getNameQueryPanel("Black");
        JTextField nameBlack = (JTextField) panelNameBlack.getComponent(1);
        centerPanel.add(panelNameBlack, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 10));

        JPanel panelNameWhite = getNameQueryPanel("White");
        JTextField nameWhite = (JTextField) panelNameWhite.getComponent(1);
        centerPanel.add(panelNameWhite, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 10));

        mainContainer.add(centerPanel, BorderLayout.CENTER);

        //add button to start the game
        JPanel southPanel = new JPanel(new GridBagLayout());
        southPanel.setOpaque(false);

        JButton buttonStart = new JButton("Start the game!");
        buttonStart.setHorizontalAlignment(SwingConstants.CENTER);

        buttonStart.addActionListener(s -> game.turn());
        buttonStart.addActionListener(s -> game.setPlayerBlack(nameBlack.getText()));
        buttonStart.addActionListener(s -> game.setPlayerWhite(nameWhite.getText()));

        southPanel.add(buttonStart, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

        mainContainer.add(southPanel, BorderLayout.SOUTH);

        //display window
        frame.setVisible(true);
    }

    private JPanel getNameQueryPanel(String player) {
        JPanel namePanel = new JPanel(new GridBagLayout());
        namePanel.setOpaque(false);

        JLabel labelPlayerName = new JLabel("Player " + player);
        namePanel.add(labelPlayerName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), frame.getWidth() / 3, 0));

        JTextField txtFieldPlayerName = new JTextField(player, 5);
        txtFieldPlayerName.setHorizontalAlignment(SwingConstants.CENTER);

        namePanel.add(txtFieldPlayerName, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), frame.getWidth() / 3, 0));

        return namePanel;
    }

    @Override
    public void displayTurn() {
        Checkerboard checkerboard = game.getCheckerboard();
        Container mainContainer = frame.getContentPane();

        //resize the images to the container size
        int width = mainContainer.getWidth() / (Checkerboard.SIZE + 2);
        int height = (mainContainer.getHeight() - 10) / (Checkerboard.SIZE + 2);
        loadScaledImages(width, height);

        //reset container
        mainContainer.removeAll();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(BG_COLOR);

        //point board
        JPanel northPanel = getPointPanel();
        mainContainer.add(northPanel, BorderLayout.NORTH);

        //draw board
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel topRBorder = new JLabel();
        topRBorder.setIcon(topRIcon);
        centerPanel.add(topRBorder, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel topBorder = new JLabel();
        topBorder.setIcon(topIcon);
        centerPanel.add(topBorder, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel topLBorder = new JLabel();
        topLBorder.setIcon(topLIcon);
        centerPanel.add(topLBorder, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomRBorder = new JLabel();
        bottomRBorder.setIcon(bottomRIcon);
        centerPanel.add(bottomRBorder, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomBorder = new JLabel();
        bottomBorder.setIcon(bottomIcon);
        centerPanel.add(bottomBorder, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomLBorder = new JLabel();
        bottomLBorder.setIcon(bottomLIcon);
        centerPanel.add(bottomLBorder, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel rightBorder = new JLabel();
        rightBorder.setIcon(rightIcon);
        centerPanel.add(rightBorder, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel leftBorder = new JLabel();
        leftBorder.setIcon(leftIcon);
        centerPanel.add(leftBorder, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        //draw squares
        JPanel checkerboardPanel = new JPanel(new GridBagLayout());
        checkerboardPanel.setOpaque(false);
        for (int row = 0; row < Checkerboard.SIZE; row++)
            for (int column = 0; column < Checkerboard.SIZE; column++) {
                JLabel square = new JLabel();
                final int rowIndex = row;
                final int columnIndex = column;

                //listen to onclick events to get the player moves
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        game.tryPlace(rowIndex, columnIndex);
                    }
                });

                switch (checkerboard.getMatrix()[row][column]) {
                    case Checkerboard.B -> square.setIcon(blackIcon);
                    case Checkerboard.W -> square.setIcon(whiteIcon);
                    case Checkerboard.A -> square.setIcon(allowedIcon);
                    default -> square.setIcon(noneIcon);
                }

                checkerboardPanel.add(square, new GridBagConstraints(column, row, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            }

        centerPanel.add(checkerboardPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        mainContainer.add(centerPanel, BorderLayout.CENTER);

        frame.revalidate();
    }

    private void loadScaledImages(int width, int height) {
        int fitSize = width;
        if (width > height)
            fitSize = height;
        fitSize = 51;

        blackIcon = new ImageIcon(blackImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));
        whiteIcon = new ImageIcon(whiteImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));
        noneIcon = new ImageIcon(noneImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));
        allowedIcon = new ImageIcon(allowedImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));

        blackTokenIcon = new ImageIcon(blackTokenImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));
        whiteTokenIcon = new ImageIcon(whiteTokenImg.getScaledInstance(fitSize, fitSize, Image.SCALE_SMOOTH));

        double ratio = fitSize / 512;
        /*
        topIcon = new ImageIcon(topImg.getScaledInstance((int) (5200 * ratio), (int) (552 * ratio), Image.SCALE_SMOOTH));
        bottomIcon = new ImageIcon(bottomImg.getScaledInstance((int) (5200 * ratio), (int) (552 * ratio), Image.SCALE_SMOOTH));
        rightIcon = new ImageIcon(rightImg.getScaledInstance((int) (552 * ratio), (int) (4096 * ratio), Image.SCALE_SMOOTH));
        leftIcon = new ImageIcon(leftImg.getScaledInstance((int) (552 * ratio), (int) (4096 * ratio), Image.SCALE_SMOOTH));*/

        topIcon = new ImageIcon(topImg.getScaledInstance(408, 55, Image.SCALE_SMOOTH));
        bottomIcon = new ImageIcon(bottomImg.getScaledInstance(408, 55, Image.SCALE_SMOOTH));
        rightIcon = new ImageIcon(rightImg.getScaledInstance(55, 408, Image.SCALE_SMOOTH));
        leftIcon = new ImageIcon(leftImg.getScaledInstance(55, 408, Image.SCALE_SMOOTH));

        topRIcon = new ImageIcon(topRImg.getScaledInstance(55, 55, Image.SCALE_SMOOTH));
        topLIcon = new ImageIcon(topLImg.getScaledInstance(55, 55, Image.SCALE_SMOOTH));
        bottomRIcon = new ImageIcon(bottomRImg.getScaledInstance(55, 55, Image.SCALE_SMOOTH));
        bottomLIcon = new ImageIcon(bottomLImg.getScaledInstance(55, 55, Image.SCALE_SMOOTH));
    }

    private JPanel getPointPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);

        //Points of Black
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setOpaque(false);

        JLabel blackName = new JLabel(game.getPlayerBlack());
        blackName.setFont(new Font("Calibri", Font.BOLD, 18));
        blackName.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(blackName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 30, 0, 0), 0, 0));

        JLabel pointsBlack = new JLabel("" + game.getCheckerboard().getNumberOfBlacks());
        pointsBlack.setFont(new Font("Calibri", Font.PLAIN, 20));
        pointsBlack.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(pointsBlack, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 30, 30, 0), 0, 0));

        mainPanel.add(leftPanel, BorderLayout.WEST);

        //Current turn
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel turn = new JLabel("It's your turn!");
        turn.setFont(new Font("Calibri", Font.PLAIN, 15));
        turn.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(turn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));

        JLabel currentColor = new JLabel();
        if (game.getCurrentPlayerName().equals(game.getPlayerBlack()))
            currentColor.setIcon(blackTokenIcon);
        else
            currentColor.setIcon(whiteTokenIcon);
        currentColor.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(currentColor, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel currentPlayer = new JLabel(game.getCurrentPlayerName());
        currentPlayer.setFont(new Font("Calibri", Font.BOLD, 20));
        currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(currentPlayer, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //Points of White
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        JLabel whiteName = new JLabel(game.getPlayerWhite());
        whiteName.setFont(new Font("Calibri", Font.BOLD, 18));
        whiteName.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(whiteName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 30), 0, 0));

        JLabel pointsWhite = new JLabel("" + game.getCheckerboard().getNumberOfWhites());
        pointsWhite.setFont(new Font("Calibri", Font.PLAIN, 20));
        pointsWhite.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(pointsWhite, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 30, 30), 0, 0));

        mainPanel.add(rightPanel, BorderLayout.EAST);

        return mainPanel;
    }

    @Override
    public void displayGameOver() {
        showMessageDialog(null, game.getWinnerName() + " wins!\nWhites: " + game.getCheckerboard().getNumberOfWhites() +
                "\nBlacks: " + game.getCheckerboard().getNumberOfBlacks(), "Game over", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);

    }

    @Override
    public void displayDraw() {
        showMessageDialog(null, "Draw!");

        frame.dispose();
    }

    @Override
    public void displayNoMoves() {
        showMessageDialog(null, game.getCurrentPlayerName() + ", you cannot do any move!", "No moves", JOptionPane.WARNING_MESSAGE);
        game.nextTurn();
    }

    @Override
    public void displayNotAllowed() {
        showMessageDialog(null, game.getCurrentPlayerName() + ", you cannot place here!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
