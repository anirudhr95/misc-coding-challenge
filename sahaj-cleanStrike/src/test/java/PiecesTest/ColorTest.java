package PiecesTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import objectModel.piecesOnBoard.BlackCoin;
import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.RedCoin;
import objectModel.piecesOnBoard.Striker;

/**
 * @author anirudh
 *	Determine if the right coins have the right colors (Basic sanity tests) in case someone change the color in the class.
 */
public class ColorTest {

	@Test
	public void blackCoinHasColorBlack() {
		BlackCoin blackCoin = new BlackCoin();
		assertTrue(
				"BlackCoin Object should have color black, instead has Color" + blackCoin.getPieceColor().getColorInString(),
				blackCoin.getPieceColor() == GameCoinColors.BLACK);
	}

	@Test
	public void redCoinHasColorRed() {
		RedCoin redCoin = new RedCoin();
		assertTrue("RedCoin should have color red, instead has color " + redCoin.getPieceColor().getColorInString(), 
				redCoin.getPieceColor() == GameCoinColors.RED);
	}
	
	@Test
	public void strikerHasColorWhite() {
		Striker striker = new Striker();
		assertTrue("Striker should have color white, instead has color " + striker.getPieceColor().getColorInString(),
				striker.getPieceColor() == GameCoinColors.WHITE);
		
	}

}
