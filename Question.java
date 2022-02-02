
public class Question {
	String quesName; 
	String[] quesAnswer;
	int correctAnswerIndex;
	int points;
	String category;

	/**
	 * Main constructor
	 * @param aQuesName
	 * @param aQuesAnswer1
	 * @param aQuesAnswer2
	 * @param aQuesAnswer3
	 * @param aQuesAnswer4
	 * @param aCorrectAnswer
	 * @param aPoints
	 * @param aCategory
	 */
	Question(String aQuesName, String aQuesAnswer1, String aQuesAnswer2, String aQuesAnswer3, String aQuesAnswer4, int aCorrectAnswer, int aPoints, String aCategory){
		quesName = aQuesName;
		quesAnswer = new String[4];
		quesAnswer[0] = aQuesAnswer1;
		quesAnswer[1] = aQuesAnswer2;
		quesAnswer[2] = aQuesAnswer3;
		quesAnswer[3] = aQuesAnswer4;
		// SInce we have an array decrement correct answer by 1
		correctAnswerIndex = aCorrectAnswer - 1;
		points = aPoints;
		category = aCategory;
	}
	public String getQuesName(){
		return quesName;
	}
	public String[] getQuesAnswer(){
		return quesAnswer;
	}
	public int getCorrectAnswerIndex(){
		return correctAnswerIndex;
	}
	public String getCorrectAnswer(){
		return quesAnswer[correctAnswerIndex];
	}

	public int getPoints(){
		return points;
	}
	public String getCategory(){
		return category;
	}

}
