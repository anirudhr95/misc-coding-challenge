package playerTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import objectModel.playerModel.Player;
import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Class to test
 * 	1. Scoring logic for player.
 *  2. Penalty scoring (based on last three moves).
 */
public class PlayerFunctionalTest {

	Player player;
	
	@Before
	public void init() {
		player = new Player("PlayerTest");
	}
	
	@Test
	public void lastThreeMovesAreFoulTest() {
		List<GameMoves> lastThreeMoves = new ArrayList<GameMoves>();
		lastThreeMoves.add(GameMoves.Defunct_Coin);
		lastThreeMoves.add(GameMoves.Defunct_Coin);
		lastThreeMoves.add(GameMoves.Defunct_Coin);
		
		player.addLastThreeMoves(lastThreeMoves);
		assertTrue("Last three moves should be foul",
				player.areThreePreviousMovesFouls());
		
		assertTrue("Last three moves should be non-scoring as well", player.isThereNoScoringInLastThreeMoves() == true);
		assertTrue("1 penalty for no-score in last 3 moves, additional 1 penalty for foul last three moves" ,player.calculatePenalty() == 2);
	}
	
	@Test
	public void lastThreeMovesNonScoringTest() {
		List<GameMoves> lastThreeMoves = new ArrayList<GameMoves>();

		lastThreeMoves.add(GameMoves.None);
		lastThreeMoves.add(GameMoves.None);
		lastThreeMoves.add(GameMoves.None);
		player.addLastThreeMoves(lastThreeMoves);

		assertTrue("1 penalty because no scoring move in the last three move", player.calculatePenalty() == 1);
		assertTrue("Last three move should have no fouls", player.areThreePreviousMovesFouls() == false);
		assertTrue("Last three move should be scoring", player.isThereNoScoringInLastThreeMoves() == true);
	}
	
	@Test
	public void lastThreeMovesAreScoringMovesTest() {
		
		List<GameMoves> lastThreeMoves = new ArrayList<GameMoves>();
		
		lastThreeMoves.add(GameMoves.Strike);
		lastThreeMoves.add(GameMoves.Multi_Strike);
		lastThreeMoves.add(GameMoves.None);
		
		player.addLastThreeMoves(lastThreeMoves);
		
		assertTrue("No penalty, all last three moves are scoring", player.calculatePenalty() == 0);
		assertTrue("Last three move should have no fouls", player.areThreePreviousMovesFouls() == false);
		assertTrue("Last three move should be scoring", player.isThereNoScoringInLastThreeMoves() == false);

	}
	
	@Test
	public void doesScoringWorkProperly() {
		
		player.addLastThreeMoves(Collections.emptyList()); // clearing any penalty
		player.makeMove(GameMoves.Strike);
		
		assertTrue(player.getScore() == 1);
		
		player.makeMove(GameMoves.Multi_Strike);
		assertTrue(player.getScore() == 3);
		
		player.makeMove(GameMoves.Red_Strike);
		assertTrue(player.getScore() == 6);
		
		player.makeMove(GameMoves.None);
		assertTrue(player.getScore() == 6);
		
		player.makeMove(GameMoves.Striker_Strike); // -1
		assertTrue(player.getScore() == 5);
		
		player.makeMove(GameMoves.Defunct_Coin);
		assertTrue(player.getScore() == 2); // additional one penalty coz last three non-scoring move
		
		// Striker strike -1, last 3 non-scoring -1, last 3 foul -1
		// 2 -3 
		player.makeMove(GameMoves.Striker_Strike);
		assertTrue(player.getScore() == -1);
	}
	
	
	
}
