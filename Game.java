
import java.awt.Color;
import java.awt.Font;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class Game implements KeyListener {
	private JFrame frame;
	private JTextArea txtAr;
	private JTextField txtField;
	private String[] words;
	private String[] randomWords;
	private JScrollPane scrollPane;
	private JLabel wpmCounter;
	private DefaultCaret caret;
	private int temp;
	private int score;

// https://stackoverflow.com/questions/9399823/change-specific-text-color-in-java   buna bak 
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

		temp = 0; // KA�INCI KEL�MEDE OLDU�UNU G�STER�YOR
		score = 0; // DO�RU YAZILANLARI SAYIYOR

		words = new String[] { "bir", "bu", "ne", "ve", "mi", "i�in", "�ok", "ben", "o", "evet", "var", "ama", "m�",
				"de�il", "da", "hay�r", "sen", "�ey", "daha", "kadar", "seni", "beni", "iyi", "tamam", "onu", "bunu",
				"gibi", "yok", "benim", "her", "sana", "ki", "sadece", "neden", "burada", "senin", "ya", "zaman", "hi�",
				"�imdi", "nas�l", "sonra", "oldu�unu", "en", "mu", "m�s�n", "hadi", "�u", "�yle", "g�zel", "biraz",
				"musun", "�nce", "iyi", "l�ften", "ona", "bak", "b�yle", "�yle", "oldu", "hey", "istiyorum", "geri",
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
				"gidelim", "dakika", "tekrar", "b�rak", "vard�", "deniyorsun", "bakal�m", "istiyorsun", "yap�yorsun",
				"hep", "onlar", "buradan", "geliyor" };

		// LABELS
		wpmCounter = new JLabel("word:");
		wpmCounter.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		wpmCounter.setBounds(30, 300, 100, 100);
		frame.add(wpmCounter);

		// KEL�MELER�N OLDU�U YER
		txtAr = new JTextArea(String.join(" ", wordRandomizer()), 2, 2);
		txtAr.setEditable(false);
		txtAr.setBounds(30, 30, 645, 100);
		txtAr.setFont(new Font(Font.SERIF, Font.PLAIN, 32));
		txtAr.setWrapStyleWord(true);
		txtAr.setLineWrap(true);
		
		caret = (DefaultCaret) txtAr.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);// i�e yaram�yo lmao

		frame.add(txtAr);

		// YAZI YAZILAN YER
		txtField = new JTextField();
		txtField.setBounds(30, 230, 645, 50);
		txtField.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
		txtField.addKeyListener(this);
		frame.add(txtField);

		// B�R ��E YARAMIYOR
		scrollPane = new JScrollPane(txtAr, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 30, 645, 100);

		frame.add(scrollPane);

	}

	public String[] wordRandomizer() {// creates a random array from the original 200 words.
		randomWords = new String[words.length];
		for (int i = 0; i < randomWords.length; i++) {
			randomWords[i] = words[new Random().nextInt(words.length)];
		}
		return randomWords;
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String userText = "";
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			userText = txtField.getText().trim();
			txtField.setText("");
			if (userText.equals(randomWords[temp])) {
				score = score + 1;
				temp = temp + 1;
				wpmCounter.setText("score " + score);
				System.out.println("working");
				System.out.println("userText = " + userText + ", score = " + score + ", temp = " + temp);
			} else {
				System.out.println("hata");
				temp = temp + 1;
				System.out.println("userText = " + userText + ", score = " + score + ", temp = " + temp);
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
