package objectModel.GameBoard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import objectModel.piecesOnBoard.AbstractCoin;
import objectModel.piecesOnBoard.BlackCoin;
import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.PiecesRemoveWrapper;
import objectModel.piecesOnBoard.RedCoin;
import objectModel.piecesOnBoard.Striker;
import objectModel.playerModel.Player;
import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Maintains the state for the game.
 * @attributes
 * 	PlayerOne
 *  PlayerTwo
 *  
 *  PlayerOnStrike
 *  
 *  pieceOnBoard
 *  
 *  Not maintaining winning / loser player (Calculating it on-demand). 
 *  I don't see any reason to track this in a member variable separately.
 *  
 */

public class CleanStrikeGame {

	private static final Logger log = LoggerFactory.getLogger(CleanStrikeGame.class);
	
	private Player playerOne;
	private Player playerTwo;
	
	private Player playerOnStrike;
	
	private List<AbstractCoin> piecesOnBoard;
	
	public CleanStrikeGame() {
		this.playerOne = new Player("PlayerOne");
		this.playerTwo = new Player("PlayerTwo");
		this.playerOnStrike = playerOne;
		
		this.piecesOnBoard = new ArrayList<AbstractCoin>();
		
		for(int i = 0; i < 9; i++)
			piecesOnBoard.add(new BlackCoin());
		piecesOnBoard.add(new RedCoin());
		
		piecesOnBoard.add(new Striker());
	}

	public Player getPlayerOnStrike() {
		return playerOnStrike;
	}

	public void setPlayerOnStrike(Player playerOnStrike) {
		this.playerOnStrike = playerOnStrike;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Player getPlayerTwo() {
		return playerTwo;
	}

	public List<AbstractCoin> getPiecesOnBoard() {
		return piecesOnBoard;
	}
	
	public void swapPlayerOnStrike() {
		if(this.playerOnStrike == playerOne)
			playerOnStrike = playerTwo;
		else
			playerOnStrike = playerOne;
		
		System.out.println("------------------------------------------------------------");
		log.debug("Strike Swapped to - " + playerOnStrike.getName());
		System.out.println();
	}
	
	public void removePiece(PiecesRemoveWrapper piece) {
		
		if(piece == null)
			return;

		for (int i = 0; i < piece.getCount(); i++) {

			for (Iterator<AbstractCoin> it = this.piecesOnBoard.listIterator(); it.hasNext();) {

				AbstractCoin item = it.next();

				if (item.getPieceColor() == piece.getColorToRemove()) {
					it.remove();
					break; // Remove only one item!!
				}
			}
		}
		
	}
	
	
	public boolean isGameOver() {
		int playerOneScore = playerOne.getScore();
		int playerTwoScore = playerTwo.getScore();
		
		return isThereAWinner(playerOneScore, playerTwoScore) || allPiecesAreExhaused();
	}

	public boolean isThereAWinner(int playerOneScore, int playerTwoScore) {
		return isScoreDifferenceIsGreaterThanThree(playerOneScore, playerTwoScore) && 
				isAtleastOnePlayerAboveFivePoints(playerOneScore, playerTwoScore);
	}

	public boolean allPiecesAreExhaused() {
		return onlyStrikerRemainOnTheBoard();
	}

	public boolean onlyStrikerRemainOnTheBoard() {
		return this.piecesOnBoard.size() == 1 && this.piecesOnBoard.get(0).getPieceColor() == GameCoinColors.WHITE;
	}

	private boolean isAtleastOnePlayerAboveFivePoints(int playerOneScore, int playerTwoScore) {
		return playerOneScore >= 5 || playerTwoScore >= 5;
	}

	private boolean isScoreDifferenceIsGreaterThanThree(int playerOneScore, int playerTwoScore) {
		return Math.abs(playerTwoScore - playerOneScore) >= 3.0;
	}
	
	public void playerOnStrikesMakesMove(GameMoves move) {
		PiecesRemoveWrapper piecesToRemove = move.getPiecesToRemove();
		
		log.debug("Removing pieces -{} from the board", piecesToRemove);
		
//		for(GameCoinColors piece : piecesToRemove) {
		this.removePiece(piecesToRemove);
//		}
		this.getPlayerOnStrike().makeMove(move);
		log.debug("There are {} pieces on board", this.getPiecesOnBoard().size());
	}

	public void declareResult() {
		if(isThereAWinner(playerOne.getScore(), playerTwo.getScore())) {
			if(playerOne.getScore() > playerTwo.getScore())
				log.info("Player 1 wins, [Player 1 :" + playerOne.getScore() + " points, Player 2 : " + playerTwo.getScore() + "]");
			else
				log.info("Player 2 wins, [Player 2 :" + playerTwo.getScore() + " points, Player 1 : " + playerOne.getScore() + "]");
		}
		
		else
			log.info("Game is Drawn, [Player 1 Points: " + playerOne.getScore() + ", Player 2 Points: " + playerTwo.getScore() + "]");
	}
	
}
