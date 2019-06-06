package objectModel.piecesOnBoard;

/*
 * Possible color of various pieces
 * 	White for Striker (It can be a separate coin without any colors, but I'm maintaining it as a white colored coin).
 */

public enum GameCoinColors {
	
	RED("red"),
	BLACK("black"),
	WHITE("white"),
	BLUE("Blue");
	
	String color;
	
	GameCoinColors(String color) {
		this.color = color;
	}
	
	public String getColorInString() {
		return this.color;
	}

}
