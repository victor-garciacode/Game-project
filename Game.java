
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * 
 * @author Victor
 * @version 11/27/2021
 *
 */
public class Game implements ActionListener{
	int totalScore;
	String currentAnswer;
	int quesValue;
	int currentQues;

	ArrayList<Question> trivia;

	JLabel welcomeMsg;
	JLabel questionCategory;
	JLabel questionLbl;

	ButtonGroup bg;
	JRadioButton firstOption;
	JRadioButton secOption;
	JRadioButton thirdOption;
	JRadioButton forthOption;

	JLabel pointsWorth;
	JLabel currentScore;
	JLabel userMsg;
	JLabel finalMsg;
	JFrame frame;
	JPanel overallPanel;
	JPanel welcomePanel;
	JButton quit;

	// UI to get user's name
	JLabel nameLabl;
	JTextField userNameTxt;
	JButton acceptUser;
	String userName;

	/**
	 * Default Constructor
	 */
	Game(){
		currentQues = 0;
		trivia = new ArrayList<Question>();
		// First read in the questions - so we know how many are there
		makeQuestions();

		frame = new JFrame("Group 9's Trivia Game");
		// create the welcome Panel
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new FlowLayout());

		// Create the overall Game Panel
		overallPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(boxlayout);
		BoxLayout boxlayout2 = new BoxLayout(overallPanel, BoxLayout.Y_AXIS);
		overallPanel.setLayout(boxlayout2);
		overallPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		overallPanel.setPreferredSize(new Dimension(300, 250));
		overallPanel.setMaximumSize(new Dimension(300, 250));
		overallPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		frame.setSize(400, 400);
		frame.add(welcomePanel);

		welcomeUser();
	}

	/**
	 * Panel to get user's name
	 */
	private void welcomeUser() {
		userName = "";
	nameLabl = new JLabel("Please Enter your name: ");
		userNameTxt = new JTextField(30);
		userNameTxt.setActionCommand("myTF");
		acceptUser = new JButton("Continue");
		acceptUser.addActionListener(this);

		welcomePanel.add(nameLabl);
		welcomePanel.add(userNameTxt);
		welcomePanel.add(acceptUser);

		frame.setVisible(true);
	}

	/**
	 * Read questions from text file
	 */
	void makeQuestions(){
		try{
			FileReader myTrivia = new FileReader("trivia.txt");
			BufferedReader myReader = new BufferedReader(myTrivia);

			while(myReader.ready()){
				String quesName = myReader.readLine();
				String quesOptOne = myReader.readLine();
				String quesOptTwo = myReader.readLine();
				String quesOptThree = myReader.readLine();
				String quesOptFour = myReader.readLine();
				int correctAnswer = Integer.parseInt(myReader.readLine());
				int quesValue = Integer.parseInt(myReader.readLine());
				String quesCat = myReader.readLine();

				Question storeQues = new Question(quesName, quesOptOne, quesOptTwo, quesOptThree, quesOptFour, correctAnswer, quesValue, quesCat);

				trivia.add(storeQues);
			}
			myReader.close();
		}
		catch(IOException exception){
			System.out.println("An error occured:" + exception);
		}

	}

	/**
	 * Initialize all components of the game
	 */
	private void initGame() {

		// Create the empty buttons and Labels. Will be filled in later
		questionCategory = new JLabel();
		questionLbl = new JLabel();

		firstOption = new JRadioButton();
		firstOption.addActionListener(this);
		secOption = new JRadioButton();
		secOption.addActionListener(this);
		thirdOption = new JRadioButton();
		thirdOption.addActionListener(this);
		forthOption = new JRadioButton();
		forthOption.addActionListener(this);
		bg = new ButtonGroup();
		bg.add(firstOption);
		bg.add(secOption);
		bg.add(thirdOption);
		bg.add(forthOption);

		pointsWorth = new JLabel();
		currentScore = new JLabel("Current Score: " + totalScore);
		if(!userName.equals("")) {
			welcomeMsg = new JLabel("Hello " + userName + "!");
			welcomeMsg.setBackground(Color.GREEN);
			welcomeMsg.setOpaque(true);
			welcomeMsg.setHorizontalAlignment(JLabel.CENTER);
			overallPanel.add(Box.createHorizontalStrut(10));
			overallPanel.add(welcomeMsg);
		}

		userMsg = new JLabel();
		finalMsg = new JLabel();
		quit = new JButton("Quit");
		quit.addActionListener(this);

    // Add components to the overall JPanel
		overallPanel.add(Box.createHorizontalStrut(10));
		overallPanel.add(questionCategory);
		overallPanel.add(Box.createHorizontalStrut(2));
		overallPanel.add(questionLbl);
		overallPanel.add(Box.createHorizontalStrut(10));
		overallPanel.add(firstOption);
		overallPanel.add(secOption);
		overallPanel.add(thirdOption);
		overallPanel.add(forthOption);
		overallPanel.add(Box.createHorizontalStrut(10));
		overallPanel.add(pointsWorth);
		overallPanel.add(Box.createHorizontalStrut(10));
		overallPanel.add(currentScore);
		overallPanel.add(Box.createHorizontalStrut(2));
		overallPanel.add(userMsg);
		overallPanel.add(Box.createHorizontalStrut(2));
		overallPanel.add(finalMsg);
		frame.add(quit);

		// This is required since user name UI is removed 
		// and new UI elements added
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * Move on to next question
	 */
	private void nextQuestion() {

		bg.clearSelection();
		Question question = trivia.get(currentQues);
    // Read next question and set Category, Question and answers
		questionCategory.setText("Category: " + question.getCategory());
		questionLbl.setText(question.getQuesName());
		firstOption.setText(question.getQuesAnswer()[0]);
		secOption.setText(question.getQuesAnswer()[1]);
		thirdOption.setText(question.getQuesAnswer()[2]);
		forthOption.setText(question.getQuesAnswer()[3]);

	}


	/**
	 * Handle events from button clicks
	 * @param ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		String btnClicked = ae.getActionCommand();
		if(btnClicked.equals("Quit")) {
      // Close frame on Quit
			frame.dispose();
		} else	if(btnClicked.equals("Continue")){
      // Process Continue from welcome page
			userName = userNameTxt.getText();
			frame.remove(welcomePanel);
			frame.add(overallPanel);
			initGame();
			nextQuestion();
		} else {
      // Process answers
			Question question = trivia.get(currentQues);
			if(btnClicked.equals(question.getCorrectAnswer())) {
				totalScore = totalScore + question.getPoints();
				currentScore.setText("Current Score: " + totalScore);
				userMsg.setText("You got the right answer!");
				userMsg.setForeground(Color.GREEN);
			} else {
				userMsg.setText("Sorry wrong answer!");
				userMsg.setForeground(Color.RED);
			}
			currentQues++;
			if(currentQues == trivia.size()) {
				finalMsg.setText("Game Over! Thanks for playing");
				disableComponent(overallPanel);
				writeToFile();
			} else {
				nextQuestion();
			}
		}

	}

	/**
	 * once game is over disable the components
	 * @param c
	 */
	void disableComponent(Container c) {
		Component[] components = c.getComponents();

		for(int i=0;i<components.length; i++) {
			components[i].setEnabled(false);
			if(components[i] instanceof Container) {
				disableComponent((Container)components[i]);
			}
		}

	}

	/**
	 * Write results to a file
	 */
	private void writeToFile() {
		try {
			FileWriter toWriteFile;
			toWriteFile = new FileWriter("data.txt", true);
			BufferedWriter output = new BufferedWriter(toWriteFile);
			output.write(userName);
			output.newLine();
			output.write(Integer.toString(totalScore));
			output.newLine();
			output.flush();
			output.close();
		}
		catch (IOException exception) {
			System.out.println("An error occurred when writing: " + exception);
		}
	}

}
