import java.util.ArrayList;


public class Pawn extends Piece {
	boolean forwards;
	boolean dmove = false;
	public Pawn(Square sq,boolean move) {
		super(sq);
		forwards = move;
	}

	@Override
	public ArrayList<Square> getMoves(ArrayList setname) {
		// TODO Auto-generated method stub
		ArrayList<Square> moves = new ArrayList<Square>();
		Square nextsquare;
		Square takeright=null;
		Square takeleft=null;
		
		if(forwards){
		nextsquare = Board.instance().moveUp(super.getSquare());
		if(nextsquare!=null){
		takeright = Board.instance().moveRight(nextsquare);
		takeleft = Board.instance().moveLeft(nextsquare);
		}
		}
		else {
		nextsquare = Board.instance().moveDown(super.getSquare());
		if(nextsquare!=null){
		takeright = Board.instance().moveLeft(nextsquare);
		takeleft = Board.instance().moveRight(nextsquare);
		}
		}
		if(nextsquare!=null && !nextsquare.hasPiece() && Board.instance().canMove(nextsquare, setname)){
			moves.add(nextsquare);
			if(forwards && super.getSquare().gety()==2){
				nextsquare = Board.instance().moveUp(nextsquare);
				if(nextsquare!=null && !nextsquare.hasPiece()){
					moves.add(nextsquare);
				}
			}
			else if(super.getSquare().gety()==7 && !forwards){
				nextsquare = Board.instance().moveDown(nextsquare);
				if(nextsquare!=null && !nextsquare.hasPiece()){
					moves.add(nextsquare);
				}
			}
		}
		Piece p1 = null;
		Piece p2 = null;
		Square current = super.getSquare();
		Square left = current.moveLeft();
		Square right = current.moveRight();
		if(takeright!=null) {
			p1 = takeright.getPiece();	
			}
		if(takeleft!=null){
			p2 = takeleft.getPiece();
		}
		boolean conditionone = (forwards && right!=null && right.hasPiece() && 
				!setname.contains(right.getPiece()) && right.getPiece().dPawn());
		boolean conditiontwo = (!forwards && left!=null && left.hasPiece() && 
				!setname.contains(left.getPiece()) && left.getPiece().dPawn());
		if((p1 !=null && !setname.contains(p1))||conditionone||conditiontwo){
			moves.add(takeright);
		}
		boolean conditionthree = (forwards && left!=null && left.hasPiece() && 
				!setname.contains(left.getPiece()) && left.getPiece().dPawn());
		boolean conditionfour = (!forwards && right!=null && right.hasPiece() && 
				!setname.contains(right.getPiece()) && right.getPiece().dPawn());
		if((p2!=null && !setname.contains(p2))||conditionthree||conditionfour){
			moves.add(takeleft);
		}
		return moves;
	}
//MAYBE JUST MOVE PIECE BACK????
//PLAN: MOVE BACK PIECE. Use SQUARE MEMENTO to restore Square
//Finally, add back to setA, Square.getPiece() IF square.getPiece()!=null
//How does Square Memento work? It literally stores reference to the Piece taken + x,y coordinates. 
	public String toString(){
		return "Pawn";
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public Piece clone() {
		// TODO Auto-generated method stub
		return new Pawn(super.getSquare(),forwards);
	}

	@Override
	public int value() {
		// TODO Auto-generated method stub
		return 1;
	}
	public void setPass(){
		dmove = true;
	}
	public void unsetPass(){
		dmove = false;
	}

	@Override
	public boolean dPawn() {
		// TODO Auto-generated method stub
		return dmove;
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
