package environment;

import oceanicobjects.CellContent;
import oceanicobjects.Lifeform;
import oceanicobjects.Water;

public class Cell {
    private Integer planktonUnits;
    private CellContent cellContent;
    private Position position;

    protected static Cell createCellAtPosition(Position position) {
        Cell cell = new Cell();
        cell.planktonUnits = 0;
        cell.position = position;
        return cell;
    }

    public CellContent getCellContent() {
        return cellContent;
    }

    public void setCellContent(CellContent cellContent) {
        this.cellContent = cellContent;
    }

    protected void addPlanktonUnit() {
        planktonUnits++;
    }

    public Integer getPlanktonUnits() {
        return planktonUnits;
    }

    public void removePlankton() {
        planktonUnits = 0;
    }

    public Boolean isEmpty() {
        return cellContent instanceof Water;
    }

    public void enter(Lifeform lifeform) {
        cellContent = lifeform;
    }

    public void leave() {
        cellContent = Water.createWater();
    }
}
