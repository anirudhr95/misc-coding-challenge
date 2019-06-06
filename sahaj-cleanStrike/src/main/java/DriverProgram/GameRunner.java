package DriverProgram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import objectModel.GameBoard.CleanStrikeGame;
import objectModel.possibleMoves.GameMoves;
import utils.InputUtils;

public class GameRunner {
	
	private static final Logger log = LoggerFactory.getLogger(GameRunner.class);
	
	public static void main(String[] args) {
		CleanStrikeGame game = new CleanStrikeGame();
		
		log.info("Beginning Game - Player 1 on Strike \n\n");
		
		while(!game.isGameOver()) {
			GameMoves move = InputUtils.parseUserInputForMap(game.getPiecesOnBoard());
			game.playerOnStrikesMakesMove(move);
			game.swapPlayerOnStrike();
		}
		
		game.declareResult();
	}

}
