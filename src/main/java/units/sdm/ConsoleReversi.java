package units.sdm;

public class ConsoleReversi implements GridView {
    @Override
    public void show(Grid grid) {
        String currentSlot;

        System.out.print((char) 27 + "[4m  ");
        for (int i = 0; i < grid.size; i++) {
            System.out.print("|" + (char) (i + 65));
        }
        System.out.print((char) 27 + "[0m|\n");

        for (int i = 0; i < grid.size; i++) {
            System.out.print((char) 27 + "[4m" + (i + 1) + " ");
            for (int j = 0; j < grid.size; j++) {
                switch (grid.grid[i][j]) {
                    case -1:
                        currentSlot = "o";
                        break;
                    case 1:
                        currentSlot = "\u001B[31mo\u001B[0m" + (char) 27 + "[4m";
                        break;
                    default:
                        currentSlot = " ";
                        break;
                }
                System.out.print("|" + currentSlot);
            }
            System.out.print((char) 27 + "[0m|\n");
        }
    }
}
