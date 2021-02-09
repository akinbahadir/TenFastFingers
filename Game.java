
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

		temp = 0; // KAÇINCI KELÝMEDE OLDUÐUNU GÖSTERÝYOR
		score = 0; // DOÐRU YAZILANLARI SAYIYOR

		words = new String[] { "bir", "bu", "ne", "ve", "mi", "için", "çok", "ben", "o", "evet", "var", "ama", "mý",
				"deðil", "da", "hayýr", "sen", "þey", "daha", "kadar", "seni", "beni", "iyi", "tamam", "onu", "bunu",
				"gibi", "yok", "benim", "her", "sana", "ki", "sadece", "neden", "burada", "senin", "ya", "zaman", "hiç",
				"þimdi", "nasýl", "sonra", "olduðunu", "en", "mu", "mýsýn", "hadi", "þu", "öyle", "güzel", "biraz",
				"musun", "önce", "iyi", "lüften", "ona", "bak", "böyle", "öyle", "oldu", "hey", "istiyorum", "geri",
				"onun", "bile", "gerçekten", "artýk", "kim", "eðer", "bay", "yani", "çünkü", "biliyorum", "doðru",
				"büyük", "buraya", "peki", "baþka", "belki", "tanrým", "olarak", "tek", "efendim", "biri", "haydi",
				"olur", "et", "olacak", "olan", "adam", "iþte", "merhaba", "sanýrým", "teþekkürler", "orada", "nerede",
				"biz", "demek", "hiçbir", "yardým", "ederim", "bilmiyorum", "gün", "gece", "in", "fazla", "bütün",
				"harika", "yeni", "bunun", "iki", "gel", "biliyor", "tüm", "ile", "kötü", "oh", "olsun", "küçük", "tam",
				"ol", "son", "siz", "þeyler", "þeyi", "bayan", "hemen", "sorun", "size", "üzgünüm", "ver", "onlarý",
				"lanet", "teþekkür", "git", "devam", "kýz", "dostum", "pekala", "sizi", "gerek", "bizi", "dur",
				"hakkýnda", "buna", "ayný", "oluyor", "kendi", "anne", "baba", "mýsýn", "diye", "iþ", "kimse", "izin",
				"asla", "hala", "tabi", "al", "seninle", "selam", "olabilir", "þunu", "benimle", "söyle", "para",
				"bizim", "önemli", "yoksa", "içinde", "herkes", "ister", "deðilim", "üç", "yüzden", "gidelim",
				"gidelim", "dakika", "tekrar", "býrak", "vardý", "deniyorsun", "bakalým", "istiyorsun", "yapýyorsun",
				"hep", "onlar", "buradan", "geliyor" };

		// LABELS
		wpmCounter = new JLabel("word:");
		wpmCounter.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		wpmCounter.setBounds(30, 300, 100, 100);
		frame.add(wpmCounter);

		// KELÝMELERÝN OLDUÐU YER
		txtAr = new JTextArea(String.join(" ", wordRandomizer()), 2, 2);
		txtAr.setEditable(false);
		txtAr.setBounds(30, 30, 645, 100);
		txtAr.setFont(new Font(Font.SERIF, Font.PLAIN, 32));
		txtAr.setWrapStyleWord(true);
		txtAr.setLineWrap(true);
		
		caret = (DefaultCaret) txtAr.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);// iþe yaramýyo lmao

		frame.add(txtAr);

		// YAZI YAZILAN YER
		txtField = new JTextField();
		txtField.setBounds(30, 230, 645, 50);
		txtField.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
		txtField.addKeyListener(this);
		frame.add(txtField);

		// BÝR ÝÞE YARAMIYOR
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
