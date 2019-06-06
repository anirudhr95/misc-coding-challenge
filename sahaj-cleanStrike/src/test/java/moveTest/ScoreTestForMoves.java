package moveTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Determine the right score for given moves
 */
public class ScoreTestForMoves {
	
	@Test
	public void checkIfStrikeHasOnePoint() {
		assertTrue("Strike should have a score of 1, instead has - " + GameMoves.Strike.getPoints(),
				GameMoves.Strike.getPoints() == 1);
	}
	
	@Test
	public void checkIfMultiStrikeHasTwoPoints() {
		assertTrue("Multi-Strike should have a score of 2, instead has - " + GameMoves.Multi_Strike.getPoints(),
				GameMoves.Multi_Strike.getPoints() == 2);
	}
	
	@Test
	public void checkIfRedStrikeHasThreePoints() {
		assertTrue("Red-Strike should have a score of 3, instead has - " + GameMoves.Red_Strike.getPoints(),
				GameMoves.Red_Strike.getPoints() == 3);
	}
	
	@Test
	public void checkIfStrikerStrikeHasNegativeOnePoint() {
		assertTrue("Striker-Strike should have a score of -1, instead has " + GameMoves.Striker_Strike.getPoints(),
				GameMoves.Striker_Strike.getPoints() == -1);
	}
	
	@Test
	public void checkIfDefunctCoinHasNegativeTwoScore() {
		assertTrue("Defunct coin should have a score of -2, instead has " + GameMoves.Defunct_Coin.getPoints(),
				GameMoves.Defunct_Coin.getPoints() == -2);
	}
	
	@Test
	public void NoneMoveHas0Points() {
		assertTrue("Empty move shoud have 0 points, instead has - " + GameMoves.None.getPoints(),
				GameMoves.None.getPoints() == 0);
	}

}
