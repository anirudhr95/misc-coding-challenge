package objectModel.playerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.slf4j.Logger;

import objectModel.possibleMoves.GameMoves;


/**
 * @author anirudh
 * Player has attributes
 * 	name --> Player1. Player2 etc. (Only to distinguish between multiple players)
 * 	movesList --> List of moves the player has made in the past
 * 	lastThreeMoves --> Tracking last three moves to add penalty for foul moves etc.
 * 	score --> Current score in the game
 *
 */
public class Player {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(Player.class); 
	
	private String name;
	
	private final List<GameMoves> movesList;

	/*
	 * Maintains last three moves (replaces HEAD always!)
	 */
	private final Queue<GameMoves> lastThreeMoves;
	
	private int score;
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}

	public  Player(String name) {
		this.name = name;
		this.movesList = new ArrayList<GameMoves>();
		this.score = 0;
		lastThreeMoves = new CircularFifoQueue<GameMoves>(3);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void makeMove(GameMoves move) {
		this.movesList.add(move);
		this.lastThreeMoves.add(move);
		this.updateScore();
	}
	
	public List<GameMoves> getMovesList() {
		return movesList;
	}

	public int getScore() {
		return this.score;
	}
	
	public int calculatePenalty() {
		int penalty = 0;
		if(isThereNoScoringInLastThreeMoves())
			penalty++;
		if(areThreePreviousMovesFouls())
			penalty++;
		
		return penalty;
	}
	
	public boolean areThreePreviousMovesFouls() {
		
		if(this.lastThreeMoves.size() < 3)
			return false;
		// all valid moves with scores >= 0
		
		for(GameMoves move : lastThreeMoves) {
			
			if(GameMoves.isNotFoul(move))
				return false;
		}
		log.info("All three last moves are foul, additional penalty of -1 (Total of -2");
		return true;
		
	}
	
	public void addLastThreeMoves(List<GameMoves> lastThreeMoves) {
		this.lastThreeMoves.addAll(lastThreeMoves);
	}

	public boolean isThereNoScoringInLastThreeMoves() {
		// Not a single scoring move in the last three moves
		if(this.lastThreeMoves.size() <  3)
			return false;
		
		for(GameMoves move : lastThreeMoves) {
			if(GameMoves.isScoringMove(move))
				return false;
		}
		log.info("No Scoring move in the last three moves, Penalty of -1");
		return true;
	}

	private void updateScore() {
		this.score = this.score + this.movesList.get(this.movesList.size() - 1).getPoints();
		this.score -= this.calculatePenalty();
		log.info("Score for " + this.name + " is " + this.getScore());
		System.out.println();
	}

	// Using this method only in test-cases (No use otherwise!)
	public Queue<GameMoves> getLastThreeMoves() {
		return lastThreeMoves;
	}

	public String getName() {
		return name;
	}
	

}
