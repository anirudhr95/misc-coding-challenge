package moveTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Determine if the right moves are found to be foul
 */
public class FoulMoveTest {
	
	@Test
	public void StrikeNotFoulTest() {
		assertTrue("Strike should not be a foul move", GameMoves.isNotFoul(GameMoves.Strike));
	}
	
	@Test
	public void multiStrikeNotAFoulMoveTest() {
		assertTrue("Multi-Strike should not be a foul-move", GameMoves.isNotFoul(GameMoves.Multi_Strike));
	}
	
	@Test
	public void redStrikeNotAFoulMoveTest() {
		assertTrue("Red-Strike should not be a foul move", GameMoves.isNotFoul(GameMoves.Red_Strike));
	}
	
	@Test
	public void noneIsNotAFoulMoveTest() {
		assertTrue("Blank move (NONE) should not be a foul", GameMoves.isNotFoul(GameMoves.None));
	}
	
	@Test
	public void strikeStrikeisAFoulMoveTest() {
		assertFalse("Striker-Strike should be a foul", GameMoves.isNotFoul(GameMoves.Striker_Strike));
	}
	
	@Test
	public void defunctCoinisAFoulMoveTest() {
		assertFalse("Defunct-Coin should be a foul move", GameMoves.isNotFoul(GameMoves.Defunct_Coin));
	}
}
