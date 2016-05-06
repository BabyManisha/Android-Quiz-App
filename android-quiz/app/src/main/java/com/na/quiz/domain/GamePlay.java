package com.na.quiz.domain;
import java.util.ArrayList;
import java.util.List;

/**
 * @author robert.hinds
 * 
 * This class represents the current game being played
 * tracks the score and player details
 *
 */
public class GamePlay {
	private int numRounds;
	private int right=0;
	private int wrong=0;
	private int round=0;
    private int skip=0;
	
	private List<Question> questions = new ArrayList<Question>();

	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getWrong() {
		return wrong;
	}
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}
    public int getSkipped() {
        return skip;
    }
    public void setSkipped(int right) {
        this.skip = skip;
    }
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	
	public Question getNextQuestion(){
		//get the question
       // Math.round((Math.random()*10)+1);

		// Question next = questions.get(this.getRound());

       // Math.round((Math.random()*4)+1);
        int l = (int)Math.round(Math.random()*5);
        Question next = questions.get(l);
		//update the round number to the next round
		this.setRound(this.getRound()+1);
		return next;
	}
	
	public void incrementRightAnswers(){
		right ++;
	}
	public void incrementWrongAnswers(){
		wrong ++;
	}
    public void incrementSkipAnswers(){
        skip ++;
    }
	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}
	public int getNumRounds() {
		return numRounds;
	}
	/* public boolean isGameOver(){
		return (getRound() >= getNumRounds());
	}
	*/
    public boolean isGameOver(){
        return (getRound() >= 5);
    }

}