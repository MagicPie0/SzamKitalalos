package SzamKitalalos;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

public class SzamKitalalos {

    private JFrame frame;
    private JTextField startNumber;
    private JTextField endNumber;
    private JTextField tippBevitel;
    private int count = 0;
    private int randomSzam = 0;
    private static long startIdo; 
    private static Timer timer;
    private JTextPane output; // Osztály szintű deklaráció
    private JLabel elteltIdo; // Osztály szintű deklaráció

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SzamKitalalos window = new SzamKitalalos();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SzamKitalalos() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 543, 440);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        output = new JTextPane(); // Inicializálás itt
        output.setEditable(false);
        output.setFont(new Font("Tahoma", Font.PLAIN, 13));
        output.setBounds(243, 210, 255, 128);
        frame.getContentPane().add(output);
        
        elteltIdo = new JLabel("Eltelt idő: "); // Inicializálás itt
        elteltIdo.setHorizontalAlignment(SwingConstants.CENTER);
        elteltIdo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        elteltIdo.setBounds(329, 11, 142, 28);
        frame.getContentPane().add(elteltIdo);
        
        JLabel lblNewLabel = new JLabel("Kezdő szám");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(10, 11, 88, 28);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblZrSzm = new JLabel("Záró szám");
        lblZrSzm.setHorizontalAlignment(SwingConstants.CENTER);
        lblZrSzm.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblZrSzm.setBounds(10, 72, 88, 28);
        frame.getContentPane().add(lblZrSzm);
        
        startNumber = new JTextField();
        startNumber.setBounds(10, 41, 86, 20);
        frame.getContentPane().add(startNumber);
        startNumber.setColumns(10);
        
        endNumber = new JTextField();
        endNumber.setColumns(10);
        endNumber.setBounds(10, 101, 86, 20);
        frame.getContentPane().add(endNumber);
        
        JTextPane txtpnAzjJtk = new JTextPane();
        txtpnAzjJtk.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtpnAzjJtk.setText("Az Új játék megnyomásával generál a szoftver egy számot a Kezdő és a Záró szám között. A játék méri az eltelt időt és a tippek számát, valamint tárolja az eddigi tippeket is. Jó szórakozát!");
        txtpnAzjJtk.setBounds(10, 210, 201, 128);
        frame.getContentPane().add(txtpnAzjJtk);
        
        JLabel tippekSzama = new JLabel("Tippek száma:" + count);
        tippekSzama.setHorizontalAlignment(SwingConstants.CENTER);
        tippekSzama.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tippekSzama.setBounds(329, 50, 142, 28);
        frame.getContentPane().add(tippekSzama);
        
        tippBevitel = new JTextField();
        tippBevitel.setEnabled(false);
        tippBevitel.setColumns(10);
        tippBevitel.setBounds(353, 89, 86, 20);
        frame.getContentPane().add(tippBevitel);
        
        JButton tippButton = new JButton("Tippelek");
        JButton feladomButton = new JButton("Feladom");
        
        feladomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNumber.setText("");
                endNumber.setText("");
                count = 0;
                tippekSzama.setText("Tippek száma: " + count);
                tippBevitel.setText("");
                tippBevitel.setEnabled(false);
                tippButton.setEnabled(false);
                feladomButton.setEnabled(false);
                output.setText("");
                JOptionPane.showMessageDialog(null, "Feladtad a játékot! A szám: " + randomSzam + " volt.", "Feladtad", JOptionPane.WARNING_MESSAGE);
                startNumber.requestFocus();
            }
        });
        
        JButton newGameButton = new JButton("Új játék");
        newGameButton.setBounds(10, 157, 89, 23);
        frame.getContentPane().add(newGameButton);
        
        tippButton.setEnabled(false);
        tippButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {			
                if(!tippBevitel.getText().equals("")) {
                    count++;
                    tippekSzama.setText("Tippek száma:" + count);

                    int tipp = Integer.parseInt(tippBevitel.getText());
                    if(tipp == randomSzam) {
                        output.setText(output.getText() + "Talált!\tA szám: " + tipp);
                        tippButton.setEnabled(false);
                        feladomButton.setEnabled(false);
                        tippBevitel.setEnabled(false);
                        tippBevitel.setText("");
                        startNumber.setText("");
                        startNumber.requestFocus();
                        endNumber.setText("");
                        
                    } else if(tipp > randomSzam) {
                        output.setText(output.getText() + "A szám kisebb!\tTippelt szám: " + tipp + "\n");
                        tippBevitel.setText("");
                        tippBevitel.requestFocus();
                    } else {
                        output.setText(output.getText() + "A szám nagyobb!\tTippelt szám: " + tipp + "\n");
                        tippBevitel.setText("");
                        tippBevitel.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "A tipp mező nem lehet üres!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
                    tippBevitel.requestFocus();
                }
            }
        });
        
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!startNumber.getText().equals("")) {
                    if(!endNumber.getText().equals("")) {
                        int start = Integer.parseInt(startNumber.getText());
                        int end = Integer.parseInt(endNumber.getText());
                        
                        if (start > end) {
                            JOptionPane.showMessageDialog(null, "A Kezdő szám nem lehet nagyobb a Záró számnál!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
                        } else {
                            randomSzam = (int)(Math.random() * ((end - start) + 1)) + start;
                            System.out.println(randomSzam);
                            tippBevitel.setEnabled(true);
                            tippBevitel.requestFocus();
                            tippButton.setEnabled(true);
                            feladomButton.setEnabled(true);
                            output.setText("");
                            count = 0;
                            tippekSzama.setText("Tippek száma:" + count);
                            startTimer(); // Hívjuk meg a startTimer metódust
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "A Záró szám nem lehet üres!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
                        endNumber.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "A Kezdő szám nem lehet üres!", "Figyelmeztetés", JOptionPane.WARNING_MESSAGE);
                    startNumber.requestFocus();
                }
            }
        });
        
        tippButton.setBounds(353, 120, 89, 23);
        frame.getContentPane().add(tippButton);
        
        feladomButton.setEnabled(false);
        feladomButton.setBounds(353, 157, 89, 23);
        frame.getContentPane().add(feladomButton);
    }

    private void startTimer() {
        startIdo = System.currentTimeMillis(); 
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - startIdo; 
                SwingUtilities.invokeLater(() -> {
                    elteltIdo.setText("Eltelt idő: " + (elapsedTime / 1000.0) + "mp");
                });
            }
        }, 0, 1000); 
    }
}
