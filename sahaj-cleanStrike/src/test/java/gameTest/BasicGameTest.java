package gameTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import objectModel.GameBoard.CleanStrikeGame;
import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.PiecesRemoveWrapper;

public class BasicGameTest {
	
	CleanStrikeGame game;
	
	@Before 
	public void init() {
		this.game = new CleanStrikeGame();
	}

	@Test
	public void isPlayerOneOnStrikeTest() {
		assertTrue(this.game.getPlayerOnStrike().getName().equals("PlayerOne"));
	}
	
	@Test
	public void areThere11CoinsTotallyTest() {
		assertTrue(this.game.getPiecesOnBoard().size() == 11);
	}
	
	@Test
	public void onlyOneRedCoinTest() {
		assertTrue(
				this.game.getPiecesOnBoard().stream().
				filter(piece -> piece.getPieceColor() == GameCoinColors.RED)
				.count() == 1
				);
	}
	
	@Test
	public void areThere9BlackCoins() {
		assertTrue(
				this.game.getPiecesOnBoard().stream().
				filter(piece -> piece.getPieceColor() == GameCoinColors.BLACK)
				.count() == 9
				);
	}
	
	@Test
	public void onlyOneStriker() {
		assertTrue(
				this.game.getPiecesOnBoard().stream().
				filter(piece -> piece.getPieceColor() == GameCoinColors.WHITE)
				.count() == 1
				);
	}
	
	@Test
	public void removePieceTest() {
		this.game.removePiece(new PiecesRemoveWrapper(GameCoinColors.RED, 1));
		
		assertTrue(
				this.game.getPiecesOnBoard().stream()
				.filter(piece -> piece.getPieceColor() == GameCoinColors.RED)
				.count() == 0
				);
		
		this.game.removePiece(new PiecesRemoveWrapper(GameCoinColors.BLACK, 1));
		
		assertTrue(
				this.game.getPiecesOnBoard().stream()
				.filter(piece -> piece.getPieceColor() == GameCoinColors.BLACK)
				.count() == 8
				);
		
		this.game.removePiece(new PiecesRemoveWrapper(GameCoinColors.WHITE, 1));
		
		assertTrue(
				this.game.getPiecesOnBoard().stream()
				.filter(piece -> piece.getPieceColor() == GameCoinColors.WHITE)
				.count() == 0);
	}
}
