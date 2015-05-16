


public class Square implements Cloneable{
private int x;
private int y;
private Piece mPiece;

public Square(int a, int b){
	x = a;
	y = b;
	mPiece = null;
}
public int getx(){
	return x;
}
public int gety(){
	return y;
}
public void addPiece(Piece p1){
	mPiece = p1;
}
public void removePiece(){
	mPiece = null;
}
public Piece getPiece(){
	return mPiece;
}
public boolean hasPiece(){
	return (getPiece()!=null);
}
public boolean equals(Square sq){
	return(this.toString().toUpperCase().equals(sq.toString().toUpperCase()));
}
public String toString(){
	char letter = (char)(x+64);
	return (""+letter+y);
}
public Square moveUp(){
	return Board.instance().moveUp(this);
}
public Square moveDown(){
	return Board.instance().moveDown(this);
}
public Square moveRight(){
	return Board.instance().moveRight(this);
}
public Square moveLeft(){
	return Board.instance().moveLeft(this);
}
public class Memento {
	int a=x;
	int b=y;
	Piece nPiece=null ;
	public Memento(){
		if (mPiece!=null){
			nPiece = mPiece;
		}
	}
	public void apply(){
	x = a;
	y = b;
	mPiece = nPiece;
	}
}
public Memento update() {
	return new Memento();
}
}
