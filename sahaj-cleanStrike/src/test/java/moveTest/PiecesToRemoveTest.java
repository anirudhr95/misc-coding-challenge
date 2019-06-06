package moveTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.piecesOnBoard.PiecesRemoveWrapper;
import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Determine if the right piece are to be remove (if any) for given moes
 */
public class PiecesToRemoveTest {
	
	@Test
	public void StrikeRemovesABlackCoinTest() {
		PiecesRemoveWrapper wrapper = GameMoves.Strike.getPiecesToRemove();
		
		assertTrue("Strike should remove a black-coin only, instead removes - " +
				StringUtils.join(wrapper, ", "),
				wrapper.getCount() == 1 && wrapper.getColorToRemove() == GameCoinColors.BLACK);
	}
	
	
	private boolean doesListOnlyContainBlackPieces(PiecesRemoveWrapper list) {
		return list.getColorToRemove() == GameCoinColors.BLACK;
	}
	
	@Test
	public void MultiStrikeRemovesTwoBlackCoinsTest() {
		PiecesRemoveWrapper piecesToRemove = GameMoves.Multi_Strike.getPiecesToRemove();
		
		assertTrue("Multi-Strike should remove two black-coin only, instead removes - " +
				StringUtils.join(piecesToRemove, ", "),
				piecesToRemove.getCount() == 2 && doesListOnlyContainBlackPieces(piecesToRemove));
	}
	
	@Test
	public void RedStrikeRemovesRedCoinOnlyTest() {
		PiecesRemoveWrapper piecesToRemove = GameMoves.Red_Strike.getPiecesToRemove();
		
		assertTrue("Red-Strike should remove red-coin only, instead removes - " + 
		StringUtils.join(piecesToRemove, ", " ),
		piecesToRemove.getCount() == 1 && piecesToRemove.getColorToRemove() == GameCoinColors.RED);
		
	}
	
	@Test
	public void StrikerStrikeRemovesNoPiecesTest() {
		PiecesRemoveWrapper piecesToRemove = GameMoves.Striker_Strike.getPiecesToRemove();
		
		assertTrue("Striker-Strike should remove no piece instead removes - " + 
		StringUtils.join(piecesToRemove, ", "),
		piecesToRemove == null);
	}
	
	@Test
	public void DefunctCoinRemovesOnePieceTest() {
		PiecesRemoveWrapper piecesToRemove = GameMoves.Defunct_Coin.getPiecesToRemove();
		
		assertTrue("Defunct-Coin should remove one black coin only, instead removes - " + 
		StringUtils.join(piecesToRemove, ", " ),
		piecesToRemove.getCount() == 2 && piecesToRemove.getColorToRemove() == GameCoinColors.BLACK);	
		
	}
	
	@Test
	public void NoneMoveRemovesNoCoinTest() {
		PiecesRemoveWrapper piecesToRemove= GameMoves.None.getPiecesToRemove();
		
		assertTrue("None move should remove no coin, instead removes - " + 
		StringUtils.join(piecesToRemove, ", " ),
		piecesToRemove == null);	
		
	}

}
