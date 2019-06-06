package objectModel.piecesOnBoard;

public class PiecesRemoveWrapper {
	
	private GameCoinColors colorToRemove;
	private int count;
	
	public PiecesRemoveWrapper(GameCoinColors colorsToRemove, int count) {
		this.colorToRemove = colorsToRemove;
		this.count = count;
	}
	
	public GameCoinColors getColorToRemove() {
		return colorToRemove;
	}
	public void setColorToRemove(GameCoinColors colorToRemove) {
		this.colorToRemove = colorToRemove;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
