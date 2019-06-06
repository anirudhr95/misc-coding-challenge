package playerTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import objectModel.playerModel.Player;
import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Check basic Attributes for the player and see if they match expectated values.
 */
public class PlayerBasicTest {

	private Player player;
	
	@Before
	public void initPlayer() {
		this.player = new Player("TestPlayer");
		this.player.makeMove(GameMoves.Strike);
		this.player.makeMove(GameMoves.Multi_Strike);
		this.player.makeMove(GameMoves.Multi_Strike);
		this.player.makeMove(GameMoves.Multi_Strike);
	}
	
	@Test
	public void playerNameTest() {
		this.player.getName().equals("TestPlayer");
	}
	
	@Test
	public void checkIfLast3ThreeMovesAreCorrect() {
		
		for(GameMoves move : this.player.getLastThreeMoves()) {
			if(move == GameMoves.Strike) {
				fail("Last three moves should never have STRIKE here");
			}
		}
		
		return;
	}
	
	@Test
	public void isScoreCorrect() {
		assertTrue( this.player.getScore() == 7 );
	}
	
	@Test
	public void isNumberOfMovesCorrect() {
		assertTrue(this.player.getMovesList().size() == 4);
	}
	
}
