# Reversi Game

This is a Java application that allows two players to locally play a game of [Reversi](https://en.wikipedia.org/wiki/Reversi). The game is played through the terminal and the game's rules are applyed automatically.

## Basic rules
Two players take turns placing one disk of their colour (black or white) on a checkerboard. The player with the black disks takes the first turn.

On his turn, a player must place a piece on the board so that there exists at least one straight occupied line between the new piece and another piece of his colour, with one or more contiguous pieces of opposite colour between them. The lines can be horizontal, vertical, or diagonal.

If the player has no allowed pieces to place, he skips the turn. When both players don't have allowed move, for example when the board is filled, the game ends. The player with the higher number of disks of his colour wins. A game may result in a draw.
## Features

- A command-line application with a simple interface
- An alternative more user-friendly interface can be selected (works only for terminals that support ANSI-escape formatting)  
- Displays available moves
- Two local player support



## Run Locally

Download the .jar file of a release and run it with the command

```bash
  java -jar reversi-VERSION.jar
```

If your terminal support ANSI-escape formatting you may want to run it with the enhanced interface by passing the parameter `f`
```bash
  java -jar reversi-VERSION.jar f
```


## Authors

- Alessandro Viol: [@AlessandroViol](https://github.com/AlessandroViol)
- Federica Azzalini: [F1397](https://github.com/F1397)
- Marco Lo Giudice: [@marcolg26](https://github.com/marcolg26)

