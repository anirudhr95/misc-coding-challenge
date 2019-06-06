package gameTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import objectModel.GameBoard.CleanStrikeGame;
import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.PiecesRemoveWrapper;
import objectModel.playerModel.Player;

/**
 * @author anirudh
 * Test functional aspects of the CleanStrikeGame
 * like 
 * 	1. if the winner is declared properly
 *  2. does the game know when to stop (When all the pieces are exhausted for example).
 */
public class GameFunctionalTest {
	
	CleanStrikeGame game;
	Player playerOne;
	Player playerTwo;
	
	@Before
	public void init() {
		game = new CleanStrikeGame();
		playerOne = game.getPlayerOne();
		playerTwo = game.getPlayerTwo();
	}
	
	/**
	 * All of this can be a seperate method
	 * but keeping it in one method since we are only testing
	 * isThereAWinner() method
	 */
	@Test
	public void winnerIsDeclaredCorrectTest() {
		
		playerOne.setScore(5);
		playerTwo.setScore(0);
		
		assertTrue("More than 5 point difference and one player is >= 5, Should be a winning situation",
				game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));
		
		playerTwo.setScore(2);
		assertTrue("More than 3 points difference and one player >= 5, should be declared winner",
				game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));
		
		playerTwo.setScore(3); //Atleast three point difference!
		assertFalse("Three point difference alone isn't enough to declare winner, the player also needs >= 5 points",
				game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));

		playerOne.setScore(3);
		playerTwo.setScore(0);
		//Three point different alone isn't enough, atleast one needs to be over 5
		assertFalse(game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));
		
		playerOne.setScore(8);
		playerTwo.setScore(7);
		assertFalse("Both >=5, but no 3 point difference", 
				game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));
		
		playerTwo.setScore(5);
		assertTrue("3 point difference and both players >=5, winning situation",
				game.isThereAWinner(playerOne.getScore(), playerTwo.getScore()));
	}
	
	@Test
	public void isAllPieceExhaustedWorkingTest() {
		game.removePiece(new PiecesRemoveWrapper(GameCoinColors.BLACK, 9));
		game.removePiece(new PiecesRemoveWrapper(GameCoinColors.RED, 1));
		
		assertTrue("Only striker remains, should return ALL PIECES ARE EXHAUSTED = TRUE", game.allPiecesAreExhaused() == true);
	}

}
