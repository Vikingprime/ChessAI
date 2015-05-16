import java.util.ArrayList;

public class Queen extends Piece {
	public Queen(Square sq) {
		super(sq);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Square> getMoves(ArrayList setname) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Square current = super.getSquare();
		while(current.gety()<8){
			current = Board.instance().moveUp(current);
			if(Board.instance().canMove(current, setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.gety()>1){
			current = Board.instance().moveDown(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()>1){
			current = Board.instance().moveLeft(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()<8){
			current = Board.instance().moveRight(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()<8 && current.gety()<8){
			current = Board.instance().moveRight(current);
			current = Board.instance().moveUp(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()>1 && current.gety()>1){
			current = Board.instance().moveLeft(current);
			current = Board.instance().moveDown(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		current = super.getSquare();
		while(current.getx()<8 && current.gety()>1){
			current = Board.instance().moveRight(current);
			current = Board.instance().moveDown(current);
			if(Board.instance().canMove(current,setname)){
				moves.add(current);
			}
			if(current.hasPiece()){
				break;
			}
		}
		while(current.getx()>1 && current.gety()<8){
			current = Board.instance().moveLeft(current);
			current = Board.instance().moveUp(current);
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
		return "Queen";
	}

	@Override
	public boolean isPawn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new Queen(super.getSquare());
	}

	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 9;
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
