import java.util.ArrayList;


public abstract class Piece implements Cloneable {
private Square space;
private ArrayList<Square> moves = new ArrayList<Square>();
public Piece(Square sq){
	space = sq;
}
public Square getSquare(){
	return space;
}
public int getX() {
	return space.getx();
}
public int getY(){
	return space.gety();
}
public void setSquare(Square sq){
	space = sq;
}
public void addMove(Square sq){
	moves.add(sq);
}
public abstract ArrayList<Square> getMoves(ArrayList setname);
public abstract String toString();
public abstract boolean isPawn();
public abstract Piece clone();
public abstract int value();
public abstract void setPass();
public abstract void unsetPass();
public abstract boolean dPawn();
public abstract boolean hasmoved();
public abstract boolean isKing();
public abstract boolean isRook();
public abstract void setmoved();
public class Memento {
	Square respace = space;
	public void apply(){
		space = Board.instance().getSquare(respace);
	}
}
public Memento update() {
	return new Memento();
}
 
}
