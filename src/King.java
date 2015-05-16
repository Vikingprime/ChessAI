import java.util.ArrayList;


public class King extends Piece{
	boolean hasmoved;
	public King(Square sq,boolean move) {
		super(sq);
		hasmoved = move;
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Square> getMoves(ArrayList setname) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Square current = super.getSquare();
		int x = current.getx();
		int y = current.gety();
		Square one = current.moveUp();
		Square two = current.moveRight();
		Square three = current.moveLeft();
		Square four = current.moveDown();
		Square five = Board.instance().getSquare(x+1,y+1);
		Square six = Board.instance().getSquare(x-1,y+1);
		Square seven = Board.instance().getSquare(x-1,y-1);
		Square eight = Board.instance().getSquare(x+1,y-1);
		Knight.check(one,moves,setname);
		Knight.check(two,moves,setname);
		Knight.check(three,moves,setname);
		Knight.check(four,moves,setname);
		Knight.check(five,moves,setname);
		Knight.check(six,moves,setname);
		Knight.check(seven,moves,setname);
		Knight.check(eight,moves,setname);
		Square castle = null;
		if(two!=null){
			castle = two.moveRight();
		}
		if(!hasmoved && two.getPiece()==null && castle.getPiece()==null && 
				castle.moveRight().getPiece()!=null && !castle.moveRight().getPiece().hasmoved()){
			moves.add(castle);
		}
		if(three!=null){
			castle = three.moveLeft();
		}
		if(!hasmoved && three.getPiece()==null && castle.getPiece()==null && 
				castle.moveLeft().getPiece()==null && castle.moveLeft().moveLeft().getPiece()!=null && 
				!castle.moveLeft().getPiece().hasmoved()){
			moves.add(castle);
		}
		
		return moves;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "King";
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new King(super.getSquare(),hasmoved);
	}

	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 0;
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
	public void setmoved(){
		hasmoved = true;
	}

	@Override
	public boolean hasmoved() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isKing() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isRook() {
		// TODO Auto-generated method stub
		return false;
	}

}
