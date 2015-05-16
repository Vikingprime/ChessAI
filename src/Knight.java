import java.util.ArrayList;


public class Knight extends Piece {

	public Knight(Square sq) {
		super(sq);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Square> getMoves(ArrayList setname) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Square current = super.getSquare();
		int x = current.getx();
		int y = current.gety();
		Square one = Board.instance().getSquare(x+1,y+2);
		Square two = Board.instance().getSquare(x-1,y+2);
		Square three = Board.instance().getSquare(x+2,y+1);
		Square four = Board.instance().getSquare(x+2,y-1);
		Square five = Board.instance().getSquare(x+1,y-2);
		Square six = Board.instance().getSquare(x-1,y-2);
		Square seven = Board.instance().getSquare(x-2,y+1);
		Square eight = Board.instance().getSquare(x-2,y-1);
		check(one,moves,setname);
		check(two,moves,setname);
		check(three,moves,setname);
		check(four,moves,setname);
		check(five,moves,setname);
		check(six,moves,setname);
		check(seven,moves,setname);
		check(eight,moves,setname);
		return moves;
	}
	static void check(Square check,ArrayList<Square> move,ArrayList setname){
		if(check!=null && Board.instance().canMove(check, setname)){
			move.add(check);
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Knight";
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new Knight(super.getSquare());
	}

	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void setPass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsetPass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dPawn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasmoved() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRook() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setmoved() {
		// TODO Auto-generated method stub
		
	}

}
