package oceanicobjects;

public class Rock extends CellContent {
    public static Rock createRock(){
        return new Rock();
    }

    public void print(){
        System.out.print("R");
    }
}
