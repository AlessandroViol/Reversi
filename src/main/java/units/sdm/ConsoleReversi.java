package units.sdm;

public class ConsoleReversi implements GridView {
    @Override
    public void show(Grid grid) {
        char currentSlot;

        System.out.print("  ");
        for (int i = 0; i < grid.size; i++) {
            System.out.print("|" + (char) (i + 65));
        }
        System.out.print("|\n");

        for (int i = 0; i < grid.size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < grid.size; j++) {
                switch (grid.grid[i][j]) {
                    case -1:
                        currentSlot = 'w';
                        break;
                    case 1:
                        currentSlot = 'b';
                        break;
                    default:
                        currentSlot = ' ';
                        break;
                }
                System.out.print("|" + currentSlot);
            }
            System.out.print("|\n");
        }
    }
}
