package units.sdm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class SwingReversi implements ReversiView {
    private Game game;
    private JFrame frame;

    private final Color bgColor = new Color(208, 206, 197);

    private final ImageCollection images;

    public SwingReversi() {
        images = new ImageCollection();

        images.add("Black");
        images.add("White");
        images.add("None");
        images.add("Allowed");

        images.add("BlackToken");
        images.add("WhiteToken");

        images.add("Top");
        images.add("Bottom");
        images.add("Right");
        images.add("Left");

        images.add("TopRight");
        images.add("TopLeft");
        images.add("BottomRight");
        images.add("BottomLeft");
    }

    @Override
    public void installLogic(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(this::displayGameStart);
    }

    @Override
    public void displayGameStart() {
        final int width = 600;
        final int length = 720;

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(width, length);

        Container mainContainer = frame.getContentPane();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(bgColor);

        //create title
        mainContainer.add(getTitlePanel(), BorderLayout.NORTH);

        //add small help label
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel label = new JLabel("Please enter player names");
        label.setFont(new Font("Calibri", Font.ITALIC, 10));
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

        buttonStart.addActionListener(s -> {
            game.setPlayerBlack(nameBlack.getText());
            game.setPlayerWhite(nameWhite.getText());
            game.turn();
        });

        southPanel.add(buttonStart, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

        mainContainer.add(southPanel, BorderLayout.SOUTH);

        //display window
        frame.setVisible(true);

        rescaleCheckerboard();
    }

    private JPanel getTitlePanel() {
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);

        JLabel title = new JLabel();
        title.setOpaque(false);

        ImageManager titleImg = new ImageManager("Title");
        titleImg.scaleIcon(150, 150);
        title.setIcon(titleImg.getIcon());

        title.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(title);

        return northPanel;
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

    private void rescaleCheckerboard() {
        Container mainContainer = frame.getContentPane();

        int width = mainContainer.getWidth() / (Checkerboard.SIZE + 4);
        int height = (mainContainer.getHeight() - 100) / (Checkerboard.SIZE + 4);

        images.rescale(width, height);
    }

    @Override
    public void displayTurn() {
        Container mainContainer = frame.getContentPane();

        //reset container
        mainContainer.removeAll();
        mainContainer.setLayout(new BorderLayout());
        mainContainer.setBackground(bgColor);

        //point board
        mainContainer.add(getTurnInfoPanel(), BorderLayout.NORTH);

        //draw board
        mainContainer.add(getCheckerboardPanel(), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridBagLayout());
        southPanel.setOpaque(false);

        JButton buttonFit = new JButton("Fit screen");
        buttonFit.setHorizontalAlignment(SwingConstants.CENTER);

        buttonFit.addActionListener(s -> {
            rescaleCheckerboard();
            displayTurn();
        });

        southPanel.add(buttonFit, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

        mainContainer.add(southPanel, BorderLayout.SOUTH);

        frame.revalidate();
    }

    private JPanel getTurnInfoPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 3));
        mainPanel.setOpaque(false);

        //Points of Black
        JPanel leftPanel = getPlayerPointsPanel(Checkerboard.B);

        mainPanel.add(leftPanel);

        //Current turn
        JPanel centerPanel = getCurrentTurnPanel();

        mainPanel.add(centerPanel);

        //Points of White
        JPanel rightPanel = getPlayerPointsPanel(Checkerboard.W);

        mainPanel.add(rightPanel);

        return mainPanel;
    }

    private JPanel getPlayerPointsPanel(int color) {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        String name;
        int points;
        Insets nameInset;
        Insets pointsInset;

        Checkerboard checkerboard = game.getCheckerboard();
        if (color == Checkerboard.B) {
            name = game.getPlayerBlack();
            points = checkerboard.getNumberOfBlacks();
            nameInset = new Insets(30, 30, 0, 0);
            pointsInset = new Insets(0, 30, 30, 0);
        } else {
            name = game.getPlayerWhite();
            points = checkerboard.getNumberOfWhites();
            nameInset = new Insets(30, 0, 0, 30);
            pointsInset = new Insets(0, 0, 30, 30);
        }

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Calibri", Font.BOLD, 18));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rightPanel.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, nameInset, 0, 0));

        JLabel pointsLabel = new JLabel("" + points);
        pointsLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rightPanel.add(pointsLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, pointsInset, 0, 0));

        return rightPanel;
    }

    private JPanel getCurrentTurnPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel turn = new JLabel("It's your turn!");
        turn.setFont(new Font("Calibri", Font.PLAIN, 15));
        turn.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(turn, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(30, 0, 0, 0), 0, 0));

        JLabel currentColor;
        String currentPlayerName = game.getCurrentPlayerName();
        if (currentPlayerName.equals(game.getPlayerBlack()))
            currentColor = images.getLabel("BlackToken");
        else
            currentColor = images.getLabel("WhiteToken");

        currentColor.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(currentColor, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel currentPlayer = new JLabel(game.getCurrentPlayerName());
        currentPlayer.setFont(new Font("Calibri", Font.BOLD, 20));
        currentPlayer.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(currentPlayer, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 30, 0), 0, 0));

        return centerPanel;
    }

    private JPanel getCheckerboardPanel() {
        JPanel centerPanel = getCheckerboardBorderPanel();

        JPanel checkerboardPanel = getCheckerboardSquaresPanel();

        centerPanel.add(checkerboardPanel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        return centerPanel;
    }

    private JPanel getCheckerboardBorderPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        JLabel topRBorder = images.getLabel("TopRight");
        centerPanel.add(topRBorder, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel topBorder = images.getLabel("Top");
        centerPanel.add(topBorder, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel topLBorder = images.getLabel("TopLeft");
        centerPanel.add(topLBorder, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomRBorder = images.getLabel("BottomRight");
        centerPanel.add(bottomRBorder, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomBorder = images.getLabel("Bottom");
        centerPanel.add(bottomBorder, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel bottomLBorder = images.getLabel("BottomLeft");
        centerPanel.add(bottomLBorder, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel rightBorder = images.getLabel("Right");
        centerPanel.add(rightBorder, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        JLabel leftBorder = images.getLabel("Left");
        centerPanel.add(leftBorder, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        return centerPanel;
    }

    private JPanel getCheckerboardSquaresPanel() {
        JPanel checkerboardPanel = new JPanel(new GridBagLayout());
        checkerboardPanel.setOpaque(false);

        Checkerboard checkerboard = game.getCheckerboard();

        for (int row = 0; row < Checkerboard.SIZE; row++) {
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
                    case Checkerboard.B -> square.setIcon(images.getIcon("Black"));
                    case Checkerboard.W -> square.setIcon(images.getIcon("White"));
                    case Checkerboard.A -> square.setIcon(images.getIcon("Allowed"));
                    default -> square.setIcon(images.getIcon("None"));
                }

                checkerboardPanel.add(square, new GridBagConstraints(column, row, 1, 1, 0.0, 0.0,
                        GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
            }
        }

        return checkerboardPanel;
    }

    @Override
    public void displayGameOver() {
        displayTurn();

        Container mainContainer = frame.getContentPane();
        JPanel component = (JPanel) mainContainer.getComponent(0);
        component.remove(1);

        Checkerboard checkerboard = game.getCheckerboard();

        showMessageDialog(null, game.getWinnerName() + " wins!\n\n" +
                game.getPlayerWhite() + " (white): " + checkerboard.getNumberOfWhites() + " disks\n" +
                game.getPlayerBlack() + " (black): " + checkerboard.getNumberOfBlacks() + " disks\n\n\n" +
                "Press OK to terminate.", "Game over", JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
    }

    @Override
    public void displayDraw() {
        displayTurn();

        Container mainContainer = frame.getContentPane();
        JPanel component = (JPanel) mainContainer.getComponent(0);
        component.remove(1);

        showMessageDialog(null, "Draw!\n\nPress OK to terminate.", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
    }

    @Override
    public void displayNoMoves() {
        displayTurn();

        showMessageDialog(null, game.getCurrentPlayerName() + ", you cannot do any move!", "No moves", JOptionPane.WARNING_MESSAGE);
        game.nextTurn();
    }

    @Override
    public void displayNotAllowed() {
        showMessageDialog(null, game.getCurrentPlayerName() + ", you cannot place here!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
