import java.util.ArrayList;


public class Bishop extends Piece {

	public Bishop(Square sq) {
		super(sq);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Square> getMoves(ArrayList setname) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Square current = super.getSquare();
		while(current.getx()<8 && current.gety()<8){
			current = current.moveRight().moveUp();
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()>1 && current.gety()>1){
			current = current.moveLeft().moveDown();
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()<8 && current.gety()>1){
			current = current.moveRight().moveDown();
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()>1 && current.gety()<8){
			current = current.moveLeft().moveUp();
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		return moves;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new Bishop(super.getSquare());
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
