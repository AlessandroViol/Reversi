package units.sdm;

public class Player {

    private String name;
    private int colour;

    public Player(String name, int colour) {
        this.name = name;
        this.colour = colour;


    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getColour(){
        return colour;
    }
}


