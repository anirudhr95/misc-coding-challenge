package moveTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Determine if move is Scorable or not.
 */
public class MoveScorableTest {
	
	@Test
	public void isStrikeAScoringMove() {
		assertTrue("Strike should be a scoring move", GameMoves.isScoringMove(GameMoves.Strike));
	}
	
	@Test
	public void isMultiStrikeAScoringMove() {
		assertTrue("Multi-Strike is a scoring move", GameMoves.isScoringMove(GameMoves.Multi_Strike));
	}
	
	@Test
	public void isRedStrikeAScoringMove() {
		assertTrue("Red-Strike should be a scoring move", GameMoves.isScoringMove(GameMoves.Red_Strike));
	}
	
	@Test
	public void noneIsNotAScoringMove() {
		assertFalse("Blank move should not be scoring", GameMoves.isScoringMove(GameMoves.None));
	}
	
	@Test
	public void strikeStrikeisNotAScoringMove() {
		assertFalse("Striker-Strike should not be a scoring move", GameMoves.isScoringMove(GameMoves.Striker_Strike));
	}
	
	@Test
	public void defunctCoinisNotAScoringMove() {
		assertFalse("Defunct-Coin should not be a scoring move", GameMoves.isScoringMove(GameMoves.Defunct_Coin));
	}

}
