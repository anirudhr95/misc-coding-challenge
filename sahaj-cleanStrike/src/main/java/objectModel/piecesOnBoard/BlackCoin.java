package objectModel.piecesOnBoard;

public class BlackCoin extends AbstractCoin{

	public BlackCoin() {
		super(GameCoinColors.BLACK);
	}

	@Override
	public String toString() {
		return "BlackCoin [Color: " + this.getPieceColor() + "]";
	}
}
