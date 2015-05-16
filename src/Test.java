import java.util.Scanner;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board mBoard = Board.instance();
		mBoard.addPiece(new Pawn(mBoard.getSquare(1, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(2, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(3, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(4, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(5, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(6, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(7, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Pawn(mBoard.getSquare(8, 2),true),mBoard.getSetA());
		mBoard.addPiece(new Rook(mBoard.getSquare(1, 1),false),mBoard.getSetA());
		mBoard.addPiece(new Knight(mBoard.getSquare(2, 1)),mBoard.getSetA());
		mBoard.addPiece(new Bishop(mBoard.getSquare(3, 1)),mBoard.getSetA());
		mBoard.addPiece(new Queen(mBoard.getSquare(4, 1)),mBoard.getSetA());
		mBoard.addAKing(mBoard.getSquare(5,1),false);
		mBoard.addPiece(new Bishop(mBoard.getSquare(6, 1)),mBoard.getSetA());
		mBoard.addPiece(new Knight(mBoard.getSquare(7, 1)),mBoard.getSetA());
		mBoard.addPiece(new Rook(mBoard.getSquare(8, 1),false),mBoard.getSetA());
		
		mBoard.addPiece(new Pawn(mBoard.getSquare(1, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(2, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(3, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(4, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(5, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(6, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(7, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Pawn(mBoard.getSquare(8, 7),false),mBoard.getSetB());
		mBoard.addPiece(new Rook(mBoard.getSquare(1, 8),false),mBoard.getSetB());
		mBoard.addPiece(new Knight(mBoard.getSquare(2, 8)),mBoard.getSetB());
		mBoard.addPiece(new Bishop(mBoard.getSquare(3, 8)),mBoard.getSetB());
		mBoard.addPiece(new Queen(mBoard.getSquare(4, 8)),mBoard.getSetB());
		mBoard.addBKing(mBoard.getSquare(5,8),false);
		mBoard.addPiece(new Bishop(mBoard.getSquare(6, 8)),mBoard.getSetB());
		mBoard.addPiece(new Knight(mBoard.getSquare(7, 8)),mBoard.getSetB());
		mBoard.addPiece(new Rook(mBoard.getSquare(8, 8),false),mBoard.getSetB());
	while(true){
		mBoard.testprint();
		Board.instance().bestmoveA(0);
		System.out.println();
	//	Board.instance().testprint();
		Scanner console = new Scanner(System.in);
		System.out.println();
		System.out.print("Please make your move: ");
		String startsquare = console.next().toUpperCase();
		String endsquare = console.next().toUpperCase();
		int coordinatea = (int)startsquare.charAt(0)-64;
		int coordinateb = Integer.parseInt(startsquare.substring(1));
		Piece pawn = Board.instance().getSquare(coordinatea, coordinateb).getPiece();
		int x = (int)endsquare.charAt(0)-64;
		int y = Integer.parseInt(endsquare.substring(1));
		Square end = mBoard.getSquare(x,y);
		while(end==null || pawn==null){
		System.out.println("Illegal. Please try again: ");
		startsquare = console.next().toUpperCase();
		endsquare = console.next().toUpperCase();
		coordinatea = (int)startsquare.charAt(0)-64;
		coordinateb = Integer.parseInt(startsquare.substring(1));
		pawn = Board.instance().getSquare(coordinatea, coordinateb).getPiece();
		x = (int)endsquare.charAt(0)-64;
		y = Integer.parseInt(endsquare.substring(1));
		end = mBoard.getSquare(x,y);
		}

		mBoard.movePiece(pawn,end);
		mBoard.promote(pawn,Board.instance().getSetB(),console);
		System.out.println("You moved "+pawn+" to "+end);
	
	}
		
	}

}
