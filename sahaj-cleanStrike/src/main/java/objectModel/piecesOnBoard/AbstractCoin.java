package objectModel.piecesOnBoard;

/*
 * Most of the problem here could be solved by keeping color as a final string
 * The argument against it is that, I can pass in ANY string in the constructor parameter and have a piece of that color (Say PINK)
 * ie. Create a class PinkCoin:
 * 	AbstractPiece pinkCoin = new PinkCoin("PinkCoin") like so.
 * 
 * if I make it static final (Constant) I have to hardcode the String during variable declaration. So I think enum is the best way here.
 *
 * Hence, I have to go through all these roundabouts.
 */

/*
 * Reason for AbstractClass:
 * 	If in the future all the coins need some property - we can add it in this class and
 *  have compile time warnings if we forget to override / enforce it on all the child-classes.
 */

public abstract class AbstractCoin {
	
	private final GameCoinColors piece;
	
	public AbstractCoin(GameCoinColors piece) {
		this.piece = piece;
	}
	
	public GameCoinColors getPieceColor() {
		return this.piece;
	}

}