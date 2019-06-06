package objectModel.possibleMoves;

import java.util.ArrayList;
import java.util.List;

import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.PiecesRemoveWrapper;

/**
 * @author anirudh
 * Possible moves that can be made and scores for those moves.
 */
public enum GameMoves {
	
	Strike,
	Multi_Strike,
	Red_Strike,
	Striker_Strike,
	Defunct_Coin,
	None;
	
	public int getPoints() {
		switch (this) {

		case Strike:
			return 1;

		case Multi_Strike:
			return 2;

		case Red_Strike:
			return 3;

		case Striker_Strike:
			return -1;

		case Defunct_Coin:
			return -2;
			
		default:
			return 0;
		}
	}
	
	/**
	 * Decide which piece (if any) will be removed from the game.
	 * @return List<PiecesToRemove> from the game.
	 * For ex. Red_Strike --> Remove red piece from the game.
	 */
	public PiecesRemoveWrapper getPiecesToRemove() {
		PiecesRemoveWrapper piecesToRemove = null;
		
		switch(this) {
		case Strike: {
			piecesToRemove = new PiecesRemoveWrapper(GameCoinColors.BLACK, 1);
			return piecesToRemove;
		}
		
		case Multi_Strike: {
			piecesToRemove = new PiecesRemoveWrapper(GameCoinColors.BLACK , 2);
			return piecesToRemove;
		}
		
		case Red_Strike: {
			piecesToRemove = new PiecesRemoveWrapper(GameCoinColors.RED, 1);
			return piecesToRemove;
		}
		
		case Defunct_Coin: {
			piecesToRemove = new PiecesRemoveWrapper(GameCoinColors.BLACK, 2);
			return piecesToRemove;
		}
		
		default:	return piecesToRemove;
			
		}
	}
	
	/**
	 * @param move
	 * @return true if the move a non-zero positive score.
	 * false otherwise
	 * 
	 */
	public static boolean isScoringMove(GameMoves move) {
		if (move == null)
			return false;

		return (move == GameMoves.Strike || 
				move == GameMoves.Multi_Strike || 
				move == GameMoves.Red_Strike);
	}

	/**
	 * @param move
	 * @return true if legal move (Score of 0 or > 0)
	 * false if score < 0
	 */
	public static boolean isNotFoul(GameMoves move) {
		return isScoringMove(move) || move == GameMoves.None;
	}
}
