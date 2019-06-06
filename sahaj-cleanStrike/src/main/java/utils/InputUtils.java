package utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import objectModel.piecesOnBoard.AbstractCoin;
import objectModel.piecesOnBoard.GameCoinColors;
import objectModel.possibleMoves.GameMoves;

/**
 * @author anirudh
 * Helps parse input from the User.
 * Reason for making it a Util class separately --
 * 	1. can handle wrong input (Not int or invalid option).
 *  2. Returns action based on Input 
 * All of this needed can now be reusable for the future also.
 */
public class InputUtils {
	
	// LinkedHashMap because it maintains the order of insertion.
	
	private static final Scanner scan = new Scanner(System.in);
	
	private static final Logger log = LoggerFactory.getLogger(InputUtils.class);
	
	/**
	 * @param piecesOnBoard
	 * @return Possible moves in the format of
	 * 	INDEX : MOVE 	[1. Strike] like so
	 * 
	 * Uses piecesOnBoard to determine possible move.
	 * For example, after red-strike, red coin is not on board
	 * so for future operations, red-strike option will not be present.
	 * (I found this approach better than a. throwing exception or 
	 *  b. printing it is a invalid option because Red is already removed).
	 */
	public static GameMoves parseUserInputForMap(List<AbstractCoin> piecesOnBoard) {
		Map<Integer, GameMoves> inputOperationMap = generateInputOperationMenuMap(piecesOnBoard);
		
		inputOperationMap.forEach((key, value) -> log.info(key + " : " + value));

		int option;

		do {
			log.info("Pick a Number from the List^");
			while (!scan.hasNextInt()) {
				log.info("Enter a valid INTEGER that is in the LIST");
				scan.next();
			}
			
			option = scan.nextInt();
		} while (isNotAValidSelection(inputOperationMap, option));
		
		GameMoves move = inputOperationMap.get(option);
		
		log.debug("Making move " + move + " for " + move.getPoints() + " points ") ;
		return move;

	}
	
	/**
	 * @param piecesOnBoard
	 * @return Menu in the Index : operation format
	 */
	private static Map<Integer, GameMoves> generateInputOperationMenuMap(List<AbstractCoin> piecesOnBoard) {
		int index = 1;
		
		Map<Integer, GameMoves> inputOperationMap = new LinkedHashMap<Integer, GameMoves>();
		
		if(boardContainsAtleastOneBlackCoin(piecesOnBoard))
			inputOperationMap.put(index++, GameMoves.Strike);
		
		if(boardContainsMultipleBlackCoins(piecesOnBoard))
			inputOperationMap.put(index++, GameMoves.Multi_Strike);
		
		if(boardContainsRedCoin(piecesOnBoard))
			inputOperationMap.put(index++, GameMoves.Red_Strike);
		
		inputOperationMap.put(index++, GameMoves.Striker_Strike);
		inputOperationMap.put(index++, GameMoves.Defunct_Coin);
		inputOperationMap.put(index++, GameMoves.None);
		
		return inputOperationMap;
	}


	private static boolean isNotAValidSelection(Map<Integer, GameMoves> operationMap, int option) {
		if (operationMap.get(option) == null) {
			log.warn("This is not a valid input - Number not present in the list, Please Re-enter");
			return true;
		}
		return false;
	}

	private static boolean boardContainsRedCoin(List<AbstractCoin> piecesOnBoard) {
		return piecesOnBoard.stream().
		filter(piece -> piece.getPieceColor() == GameCoinColors.RED)
		.count() > 0;
	}

	private static boolean boardContainsMultipleBlackCoins(List<AbstractCoin> piecesOnBoard) {
		return piecesOnBoard.stream().
		filter(piece -> piece.getPieceColor() == GameCoinColors.BLACK)
		.count() > 1;
	}

	private static boolean boardContainsAtleastOneBlackCoin(List<AbstractCoin> piecesOnBoard) {
		return piecesOnBoard.stream().
		filter(piece -> piece.getPieceColor() == GameCoinColors.BLACK)
		.count() > 0;
	}	
	
}
