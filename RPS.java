
/**
 * Write a description of class RPS here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RPS extends JPanel implements ActionListener {
  public static String[] choices = new String[] {
    "rock",
    "paper",
    "scissors"
  };
  ButtonGroup buttonGroup = new ButtonGroup();
  JLabel status = new JLabel();
  JLabel scores = new JLabel();
  JLabel userPicture = new JLabel();
  JLabel computerPicture = new JLabel();
  int wins = 0;
  int losses = 0;

  public static void main(String[] args) {
    JPanel content = new RPS();
    JFrame window = new JFrame();
    window.setContentPane(content);
    window.setSize(500, 300);
    window.setLocation(100, 100);
    window.setVisible(true);
  }

  public RPS() {
    this.setLayout(new GridLayout(2, 2));

    JPanel player = new JPanel();
    player.add(new JLabel("    Your choice:    "));
    player.add(userPicture);
    this.add(player);

    JPanel computer = new JPanel();
    computer.add(new JLabel("Computer's choice:"));
    computer.add(computerPicture);
    this.add(computer);

    JPanel choose = new JPanel();
    choose.add(new JLabel("        Pick one:        "));
    JPanel choices = new JPanel();
    choices.setLayout(new GridLayout(4, 1));

    JRadioButton rock = new JRadioButton("Rock");
    rock.setSelected(true);
    rock.setMnemonic(KeyEvent.VK_R);
    rock.setActionCommand("rock");
    rock.addActionListener(this);
    choices.add(rock);
    JRadioButton paper = new JRadioButton("Paper");
    paper.setMnemonic(KeyEvent.VK_P);
    paper.setActionCommand("paper");
    paper.addActionListener(this);
    choices.add(paper);
    JRadioButton scissors = new JRadioButton("Scissors");
    scissors.setMnemonic(KeyEvent.VK_S);
    scissors.setActionCommand("scissors");
    scissors.addActionListener(this);
    choices.add(scissors);

    buttonGroup = new ButtonGroup();
    buttonGroup.add(rock);
    buttonGroup.add(paper);
    buttonGroup.add(scissors);

    choose.add(choices);
    this.add(choose);

    JPanel results = new JPanel();
    results.setLayout(new GridLayout(4, 1));
    JButton submit = new JButton("Play");
    submit.setActionCommand("play");
    submit.addActionListener(this);
    results.add(submit);
    JButton reset = new JButton("Reset Scores");
    reset.setActionCommand("reset");
    reset.addActionListener(this);
    results.add(reset);
    results.add(status);
    status.setHorizontalAlignment(JLabel.CENTER);
    results.add(scores);
    scores.setHorizontalAlignment(JLabel.CENTER);
    this.add(results);
  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if ("play".equals(command)) {
      String selected = buttonGroup.getSelection().getActionCommand();
      // System.out.println(selected);
      int userChoice = choiceToIndex(selected);
      userPicture.setIcon(createImageIcon(selected + ".png"));
      int computerChoice = (int) (Math.random() * 3);
      computerPicture.setIcon(createImageIcon(choices[computerChoice] + ".png"));
      int result = getResult(userChoice, computerChoice);

      String message = "";

      if (result == 1) {
        message = "You win!";
        wins++;
      } else if (result == -1) {
        message = "You lose.";
        losses++;
      } else {
        message = "It was a tie.";
      }

      status.setText(message);
      scores.setText("wins: " + wins + ", " + "losses: " + losses);
    } else if ("reset".equals(command)) {
      wins = 0;
      losses = 0;
      userPicture.setIcon(null);
      computerPicture.setIcon(null);
      status.setText("");
      scores.setText("");
    } else {
      userPicture.setIcon(createImageIcon(command + ".png"));
      computerPicture.setIcon(null);
    }
  }

  private static int getResult(int choice1, int choice2) {
    if (choice1 == choice2) {
      return 0;
    }

    int diff = 1 - choice1;
    int adj1 = choice1 + diff;
    int adj2 = Math.floorMod(choice2 + diff, 3);

    // Returns 1 if adj1 > adj2
    // Returns -1 if adj1 < adj2
    return adj1 - adj2;
  }

  private static int choiceToIndex(String choice) {
    if ("rock".equals(choice)) {
      return 0;
    } else if ("paper".equals(choice)) {
      return 1;
    } else {
      return 2;
    }
  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL url = RPS.class.getResource(path);

    if (url != null) {
      return new ImageIcon(url);
    } else {
      System.out.println("Could not find \"" + path + "\"");
      return null;
    }
  }
}
//variable.setOpaque(true);
//variable.BorderPainted(false);