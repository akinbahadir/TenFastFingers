import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
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

	// https://stackoverflow.com/questions/9399823/change-specific-text-color-in-java
	// buna bak
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

		temp = 0; // KAÇINCI KELİMEDE OLDUĞUNU GÖSTERİYOR
		score = 0; // DOĞRU YAZILANLARI SAYIYOR

		words = new String[] { "bir", "bu", "ne", "ve", "mi", "için", "çok", "ben", "o", "evet", "var", "ama", "mı",
				"değil", "da", "hayır", "sen", "şey", "daha", "kadar", "seni", "beni", "iyi", "tamam", "onu", "bunu",
				"gibi", "yok", "benim", "her", "sana", "ki", "sadece", "neden", "burada", "senin", "ya", "zaman", "hiç",
				"şimdi", "nasıl", "sonra", "olduğunu", "en", "mu", "mısın", "hadi", "şu", "öyle", "güzel", "biraz",
				"musun", "önce", "iyi", "lüften", "ona", "bak", "böyle", "öyle", "oldu", "hey", "istiyorum", "geri",
				"onun", "bile", "gerçekten", "artık", "kim", "eğer", "bay", "yani", "çünkü", "biliyorum", "doğru",
				"büyük", "buraya", "peki", "başka", "belki", "tanrım", "olarak", "tek", "efendim", "biri", "haydi",
				"olur", "et", "olacak", "olan", "adam", "işte", "merhaba", "sanırım", "teşekkürler", "orada", "nerede",
				"biz", "demek", "hiçbir", "yardım", "ederim", "bilmiyorum", "gün", "gece", "in", "fazla", "bütün",
				"harika", "yeni", "bunun", "iki", "gel", "biliyor", "tüm", "ile", "kötü", "oh", "olsun", "küçük", "tam",
				"ol", "son", "siz", "şeyler", "şeyi", "bayan", "hemen", "sorun", "size", "üzgünüm", "ver", "onları",
				"lanet", "teşekkür", "git", "devam", "kız", "dostum", "pekala", "sizi", "gerek", "bizi", "dur",
				"hakkında", "buna", "aynı", "oluyor", "kendi", "anne", "baba", "mısın", "diye", "iş", "kimse", "izin",
				"asla", "hala", "tabi", "al", "seninle", "selam", "olabilir", "şunu", "benimle", "söyle", "para",
				"bizim", "önemli", "yoksa", "içinde", "herkes", "ister", "değilim", "üç", "yüzden", "gidelim",
				"gidelim", "dakika", "tekrar", "bırak", "vardı", "deniyorsun", "bakalım", "istiyorsun", "yapıyorsun",
				"hep", "onlar", "buradan", "geliyor" };

		// LABELS
		wpmCounter = new JLabel("word:");
		wpmCounter.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
		wpmCounter.setBounds(30, 300, 100, 100);
		frame.add(wpmCounter);

		// KELİMELERİN OLDUĞU YER
		txtAr = new JTextArea(String.join(" ", wordRandomizer()), 2, 2);
		txtAr.setEditable(false);
		txtAr.setBounds(30, 30, 645, 100);
		txtAr.setFont(new Font(Font.SERIF, Font.PLAIN, 32));
		txtAr.setWrapStyleWord(true);
		txtAr.setLineWrap(true);

		caret = (DefaultCaret) txtAr.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);// işe yaramıyo lmao

		frame.add(txtAr);

		// YAZI YAZILAN YER
		txtField = new JTextField();
		txtField.setBounds(30, 230, 645, 50);
		txtField.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
		txtField.addKeyListener(this);
		frame.add(txtField);

		// BİR İŞE YARAMIYOR
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
			// kelimeleri scrolla
			int pos = 0;
			// kelimenin yerini bul
			for (int i = 0; i < temp; i++) {
				pos += randomWords[i].length() + 1;
			}
			try {
				// kelime görüşünü bul
				Rectangle view = txtAr.modelToView2D(pos).getBounds();
				// satır satır atlamak için offset ekle
				view.translate(0, 50);
				// kelimeyi görüşe scrolla
				txtAr.scrollRectToVisible(view);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
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