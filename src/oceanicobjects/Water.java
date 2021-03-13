package oceanicobjects;

public class Water extends CellContent {

    public static Water createWater(){
        return new Water();
    }
    public void print(){
        System.out.print("_");
    }
}
