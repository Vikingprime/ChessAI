import java.util.ArrayList;
import java.util.Scanner;


//Maybe to make it faster, readd piece instead of Memento for Board?
public class Board {
volatile static Board mBoard = null;
//setA starts at line 1
private ArrayList<Piece> setA = new ArrayList<Piece>();
int Ascore = 8;
King AKing;
//setB starts at line 8
private ArrayList<Piece> setB = new ArrayList<Piece>();
int Bscore = 8;
King BKing;
private Square[][] mSquares = new Square[8][8];
private Piece[] promote = new Piece[4];
private Square.Memento castle = null;
private Square.Memento castle2 = null;
private Piece.Memento rook = null;

private Board(){
	for(int x = 0;x<8;x++){
	   for(int y = 0;y<8;y++){
		  mSquares[x][y] = new Square(x+1,8-y);
	   }
	}
	promote[0] = new Queen(null);
	promote[1] = new Knight(null);
	promote[2] = new Rook(null,true);
	promote[3] = new Bishop(null);
}
public static Board instance() {
	// TODO: Fill in here, if necessary
	if (mBoard == null) {
		mBoard = new Board();
	}
	return mBoard;
}
public static void release(){
	mBoard = null;
}
public void addPiece(Piece p1,ArrayList list){
	if(p1.getSquare()!=null){
		list.add(p1);
		Square tmp = getSquare(p1.getX(),p1.getY());
		tmp.addPiece(p1);
	}
}
//Separate method for calc and non calc?
public void movePiece(Piece p1,Square sq){
//	System.out.println("Removing Piece from: "+mBoard.getSquare(p1.getSquare()));
	Square current = p1.getSquare();
	Square left = current.moveLeft();
	Square right = current.moveRight();
	if(p1.isKing() && Math.abs(p1.getX()-sq.getx())>1){
		Square cur = p1.getSquare();
		if(sq.getx()==7){
			System.out.println("THIS IS KING'S LOCATION "+p1.getY());
			castle = getSquare(8,p1.getY()).update();
			castle2 = getSquare(6,p1.getY()).update();
			rook = getSquare(8,p1.getY()).getPiece().update();
			movePiece(getSquare(8,p1.getY()).getPiece(),getSquare(6,p1.getY()));
		}
		else if(sq.getx()==3){
			castle = getSquare(1,p1.getY()).update();
			castle2 = getSquare(4,p1.getY()).update();
			rook = getSquare(1,p1.getY()).getPiece().update();
			movePiece(getSquare(1,p1.getY()).getPiece(),getSquare(4,p1.getY()));
		}
	}
	if(p1.isPawn() && sq.getx()==current.getx()+1 && (sq.gety() == current.gety()+1||sq.gety()==current.gety()-1)
			&& !sq.hasPiece() ){
		setA.remove(right.getPiece());
		setB.remove(right.getPiece());
		right.removePiece();
	}
	else if(p1.isPawn() && sq.getx()==current.getx()-1 && (sq.gety() == current.gety()-1||sq.gety()==current.gety()+1)
			&& !sq.hasPiece() ){
		setA.remove(left.getPiece());
		setB.remove(left.getPiece());
		left.removePiece();
	}
	p1.getSquare().removePiece();
	if(sq.getPiece()!=null){
//		System.out.println("THE PIECE ON "+sq+" was taken");
		setA.remove(sq.getPiece());
		setB.remove(sq.getPiece());
		sq.removePiece();
	}
	sq.addPiece(p1);
//	mBoard.getPiece(p1).setSquare(sq);
	p1.setSquare(sq);
//	System.out.println("I'm setting square: "+sq);
}
public boolean canMove(Square sq,ArrayList setname){
	return !(sq.hasPiece() && setname.contains(sq.getPiece()));
}
public Square moveUp(Square sq){
	if(sq.gety()+1<=8){
	return getSquare(sq.getx(),sq.gety()+1);
	}
	return null;
}
public Square moveDown(Square sq){
	if(sq.gety()-1>=1){
	return getSquare(sq.getx(),sq.gety()-1);
	}
	return null;
}
public Square moveRight(Square sq){
	if(sq.getx()+1<=8){
	return getSquare(sq.getx()+1,sq.gety());
	}
	return null;
}
public Square moveLeft(Square sq){
	if(sq.getx()-1>=1) {
	return getSquare(sq.getx()-1,sq.gety());
	}
	return null;
}
public Square getSquare(Square mSquare){
	return getSquare(mSquare.getx(),mSquare.gety());
}
public Square getSquare(int x,int y){
	if(x>8 || x<1 || y>8 || y<1){
		return null;
	}
	return mSquares[x-1][8-y];
}
public ArrayList getSetA(){
	return setA;
}
public ArrayList getSetB(){
	return setB;
}

public double bestmoveA(int counter){
	if(counter == 2){
	//	System.out.println("Value is: "+(double)(getvalueA()-getvalueB())+(distancediff()/100));
		return ((double)(getvalueA()-getvalueB())+(distancediff()/100));
	}
	Piece p1=null;
	Square s1=null;
	double maxval = -500;
	counter++;
	ArrayList<Piece> tmpo = (ArrayList) setA.clone();
	for(Piece p:tmpo){
	p.unsetPass();
	ArrayList<Square> move = p.getMoves(setA);
		for(Square s:move){
			if(s.equals(BKing.getSquare())){
				return 400;
			}
			boolean king = p.isKing();
			if(king){
				AKing.setmoved();
			}
			boolean diff = Math.abs(p.getX()-s.getx())>1;
			Square.Memento sql = p.getSquare().update();
			Square.Memento sqq = s.update();
			Piece.Memento piece = p.update();
			Memento first = this.update();
			double val = 0;
			if(p.isPawn() && s.gety()==p.getSquare().gety()+2){
				p.setPass();
			}
			if(p.isPawn() && s.gety()==8){
				for(Piece q:promote){
					movePiece(p,s);
					s.removePiece();
					p = q.clone();
					p.setSquare(s);
					s.addPiece(p);
					val = bestmoveB(counter);
				}
			}
			else {
			    movePiece(p,s);
				val = bestmoveB(counter);
			}
			if(val > maxval){
				maxval = val;
				p1 = p;
				s1 = s;
			}
			first.apply();
			piece.apply();
			sql.apply();
			sqq.apply();
			if(king && diff){
				castle.apply();
				castle2.apply();
				rook.apply();
			}
			//PROBABLY WON'T WORK FOR DOUBLE CASTLES B/C OF VARIABLE
			
		}
		}
	if(counter == 1){
		movePiece(p1,s1);
		System.out.print("I moved "+p1+" to "+s1);
	}
	return maxval;
}
public double bestmoveB(int counter){
	double maxval = 500;
	ArrayList<Piece> tmpo = (ArrayList) setB.clone();
	for(Piece p:tmpo){
		p.unsetPass();
		ArrayList<Square> move = p.getMoves(setB);
		for(Square s:move){
			if(s.equals(AKing.getSquare())){
				return -400;
			}
			boolean king = p.isKing();
			if(king){
				BKing.setmoved();
			}
			boolean diff = Math.abs(p.getX()-s.getx())>1;
			Square.Memento sqq = s.update();
			Square.Memento sql = p.getSquare().update();
			Piece.Memento piece = p.update();
			Memento second = this.update();
			double val = 0;
			if(p.isPawn() && s.gety()==p.getSquare().gety()-2){
				p.setPass();
			}
			if(p.isPawn() && s.gety()==1){
				for(Piece q:promote){
					System.out.println("Promote to "+q);
					movePiece(p,s);
					s.removePiece();
					p = q.clone();
					p.setSquare(s);
					s.addPiece(p);
					val = bestmoveA(counter);
				}
			}
			else {
			    movePiece(p,s);
				val = bestmoveA(counter);
			}
			if( val < maxval){
				maxval = val;
			}
			second.apply();
			piece.apply();
			sql.apply();
			sqq.apply();
			if(king && diff){
				castle.apply();
				castle2.apply();
				rook.apply();
			}
			//THIS MAY NOT WORK FOR DOUBLE CASTLES ON BOTH SIDES
			
		}
		}
	return maxval;
}
public void addAKing(Square sq,boolean move){
	AKing = new King(sq,move);
	mBoard.addPiece(AKing,getSetA());
}
public void addBKing(Square sq,boolean move){
	BKing = new King(sq,move);
	mBoard.addPiece(BKing,getSetB());
}

public class Memento {
	private ArrayList<Piece> reSetA = (ArrayList<Piece>) setA.clone();
	private ArrayList<Piece> reSetB = (ArrayList<Piece>) setB.clone();
public void apply(){
	setA = reSetA;
	setB = reSetB;
}
 }
public Memento update(){
	return new Memento();
}
public int getvalueA(){
	int sum = 0;
	for(Piece p:setA){
		sum+=p.value();
	}
	return sum;
}
public int getvalueB(){
	int sum = 0;
	for(Piece p:setB){
		sum+=p.value();
	}
	return sum;
}
public double distancediff(){
	double sum=0;
	for(Piece p:setA){
		sum+=Math.sqrt((p.getX()-BKing.getX())*
				(p.getX()-BKing.getX())+(p.getY()-BKing.getY())*(p.getY()-BKing.getY()));
	}
	double sum2 = 0;
	for(Piece p:setB){
		sum2+=Math.sqrt((p.getX()-AKing.getX())*
				(p.getX()-AKing.getX())+(p.getY()-AKing.getY())*(p.getY()-AKing.getY()));
	}
	return sum2-sum;
}
public void testprint(){
	for(Piece p:setA){
		System.out.println("White"+p+" on "+p.getSquare());
	}
	for(Piece b:setB){
		System.out.println("Black"+b+" on "+b.getSquare());
	}
/*	System.out.println("THIS IS WHAT THE SQUARE SAY");
	for(int i=0;i<8;i++){
		for(int z=0;z<8;z++){
			if(mSquares[i][z].hasPiece()){
			System.out.println("Piece on "+mSquares[i][z]+" that believes it's on "+mSquares[i][z].getPiece().getSquare());
			}
		}
	}
	*/
}
public void promote(Piece p,ArrayList<Piece> setname,Scanner console){
	if(p.isPawn() && (p.getY()==1||p.getY()==8) ){
		Piece finish = null;
		System.out.println("What do you want to promote to? ");
		String input = console.next();
		if(input.toUpperCase().startsWith("Q")){
			finish = new Queen(p.getSquare());
		}
		else if(input.toUpperCase().startsWith("B")){
			finish = new Bishop(p.getSquare());
		}
		else if(input.toUpperCase().startsWith("R")){
			finish = new Rook(p.getSquare(),true);
		}
		else if(input.toUpperCase().startsWith("K")){
			finish = new Knight(p.getSquare());
		}
		for(Piece b:setname){
			if(b.equals(p)){
				b.getSquare().addPiece(finish);
				b= finish;
			}
		}
	}
}

}
