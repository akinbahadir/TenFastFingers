import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

public class Game implements KeyListener, ActionListener {
	private JFrame frame;
	private JTextArea txtAr;
	private JTextField txtField;
	private String[] words;
	private String[] randomWords;
	private JScrollPane scrollPane;
	private JLabel wpmCounter, countDownLabel;
	private JButton restartButton;
	private int temp;
	private int score;
	private int second;
	private int pos;
	private Timer timer;
	private boolean timerStatus;
	private int spaceKeyCount;
	Rectangle view;
	
	public Game() {
		frame = new JFrame();

		init();

		frame.setTitle("10FastFingers");
		frame.setSize(720, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(183, 222, 255));
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public void init() {

		frame.setLayout(null);

		temp = 0; // follows the index of the current word on the textArea 
		score = 0; // counts the correct answers
		spaceKeyCount = 0;
		second = 60;
		timerStatus = false;

		words = new String[] { "bir", "bu", "ne", "ve", "mi", "i�in", "�ok", "ben", "o", "evet", "var", "ama", "m�",
				"de�il", "da", "hay�r", "sen", "�ey", "daha", "kadar", "seni", "beni", "iyi", "tamam", "onu", "bunu",
				"gibi", "yok", "benim", "her", "sana", "ki", "sadece", "neden", "burada", "senin", "ya", "zaman", "hi�",
				"�imdi", "nas�l", "sonra", "oldu�unu", "en", "mu", "m�s�n", "hadi", "�u", "�yle", "g�zel", "biraz",
				"musun", "�nce", "iyi", "l�tfen", "ona", "bak", "b�yle", "�yle", "oldu", "hey", "istiyorum", "geri",
				"onun", "bile", "ger�ekten", "art�k", "kim", "e�er", "bay", "yani", "��nk�", "biliyorum", "do�ru",
				"b�y�k", "buraya", "peki", "ba�ka", "belki", "tanr�m", "olarak", "tek", "efendim", "biri", "haydi",
				"olur", "et", "olacak", "olan", "adam", "i�te", "merhaba", "san�r�m", "te�ekk�rler", "orada", "nerede",
				"biz", "demek", "hi�bir", "yard�m", "ederim", "bilmiyorum", "g�n", "gece", "in", "fazla", "b�t�n",
				"harika", "yeni", "bunun", "iki", "gel", "biliyor", "t�m", "ile", "k�t�", "oh", "olsun", "k���k", "tam",
				"ol", "son", "siz", "�eyler", "�eyi", "bayan", "hemen", "sorun", "size", "�zg�n�m", "ver", "onlar�",
				"lanet", "te�ekk�r", "git", "devam", "k�z", "dostum", "pekala", "sizi", "gerek", "bizi", "dur",
				"hakk�nda", "buna", "ayn�", "oluyor", "kendi", "anne", "baba", "m�s�n", "diye", "i�", "kimse", "izin",
				"asla", "hala", "tabi", "al", "seninle", "selam", "olabilir", "�unu", "benimle", "s�yle", "para",
				"bizim", "�nemli", "yoksa", "i�inde", "herkes", "ister", "de�ilim", "��", "y�zden", "gidelim",
				"gidelim", "dakika", "tekrar", "b�rak", "vard�", "yap�lmak", "kitap", "test", "deneme", "macera",
				"patates", "domates", "biber", "patl�can", "elma", "armut", "ya�mur", "f�rt�na", "dolu", "kral",
				"deney", "deniyorsun", "bakal�m", "istiyorsun", "yap�yorsun", "hep", "onlar", "buradan", "geliyor" };

		// Labels
		wpmCounter = new JLabel("Score:");
		wpmCounter.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		wpmCounter.setBounds(30, 300, 200, 100);
		frame.add(wpmCounter);

		countDownLabel = new JLabel("Time: 60");
		countDownLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		countDownLabel.setBounds(575, 300, 100, 100);
		frame.add(countDownLabel);

		// Restart button
		restartButton = new JButton("R");
		restartButton.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		restartButton.setBounds(500, 325, 50, 50);
		restartButton.addActionListener(this);
		frame.add(restartButton);

		// TextArea
		txtAr = new JTextArea(String.join(" ", wordRandomizer()), 2, 2);
		txtAr.setEditable(false);
		txtAr.setBounds(30, 30, 645, 90);
		txtAr.setFont(new Font(Font.SERIF, Font.PLAIN, 32));
		txtAr.setWrapStyleWord(true);
		txtAr.setLineWrap(true);

		frame.add(txtAr);

		// TextField
		txtField = new JTextField();
		txtField.setBounds(30, 230, 645, 50);
		txtField.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
		txtField.addKeyListener(this);
		frame.add(txtField);

		// Scrollbar
		scrollPane = new JScrollPane(txtAr, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 30, 645, 90);
		
		frame.add(scrollPane);

	}

	public void countDown() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				if (second > 0) {
					countDownLabel.setText("Time: " + second);
				}
				if (second == 0) {
					wpmCounter.setText("Score: " + score + " WPM");
					countDownLabel.setText("Time: " + second);
					timer.stop();
				}
			}
		});

		timer.start();
	}

	public String[] wordRandomizer() {// creates a random array from the original 200 words.
		randomWords = new String[words.length];
		for (int i = 0; i < randomWords.length; i++) {
			randomWords[i] = words[new Random().nextInt(words.length)];
		}
		return randomWords;
	}

	public void restart() {

		if (timerStatus) {
			timer.stop();
			timerStatus = false;
		}

		spaceKeyCount = 0;
		txtField.setText("");
		txtAr.setText(String.join(" ", wordRandomizer()));
		pos = 0;
		temp = 0;
		score = 0;
		second = 60;
		countDownLabel.setText("Time: " + second);
		txtAr.setCaretPosition(0);
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String userText = "";

		spaceKeyCount++;

		String previous = "";

		if (spaceKeyCount == 1) {
			timerStatus = true;
			countDown();
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			userText = txtField.getText().trim();
			txtField.setText("");
			if (userText.equals(randomWords[temp]) && temp < randomWords.length - 1 && !(userText.equals(previous))
					&& second != 0) {
				score = score + 1;
				temp = temp + 1;
//				System.out.println("working");
//				System.out.println("userText = " + userText + ", score = " + score + ", temp = " + temp);
			} else if (temp < randomWords.length - 1 && !(userText.equals(previous)) && second != 0) {
				temp = temp + 1;
				System.out.println("hata");
//				System.out.println("userText = " + userText + ", score = " + score + ", temp = " + temp);
			}
			// scroll the words
			pos = 0;
			// find the place of the current word
			for (int i = 0; i < temp && temp < randomWords.length - 2; i++) {
				pos += randomWords[i].length() + 1;
			}
			try {
				Rectangle view = txtAr.modelToView2D(pos).getBounds();
				// System.out.println(view);
				view.translate(0, 45);
				txtAr.scrollRectToVisible(view);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}

	}
	// -------------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restartButton) {
			restart();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}