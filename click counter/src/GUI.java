import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//Coded by Arces https://github.com/arces/OSU-BPM-Counter

public class GUI extends JDialog {
    private JPanel contentPane;
    private JButton endButton;
    private JButton buttonCancel;
    private JLabel CurrentBPM;
    private JLabel MaxBPM;
    private JButton startButton;
    public JTextField textField1;
    private JButton clearMaxBPMButton;
    public boolean breakKey = true;
    public long time;
    public long end;
    public int timesClicked = 0;
    public int maxClicks = 0;
    public final int SECONDS = 60;

    public static void main(String[] args) {
        GUI dialog = new GUI();
        dialog.go();
        dialog.pack();
        dialog.setVisible(true);

    }


        public void go() {
            textField1.addKeyListener(new EnterListener());
            textField1.addKeyListener(new XListener());
            setContentPane(contentPane);
            setModal(true);
            getRootPane().setDefaultButton(endButton);

            clearMaxBPMButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   clearMaxButt();
                }
            });

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    countKey();
                }
            });

            endButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    breakKey=true;
                    textField1.setText("");
                    clearCurrentBPM();
                    timesClicked = 0;
                }
            });

            buttonCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }

            });

// call onCancel() when cross is clicked
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onCancel();
                }
            });

// call onCancel() on ESCAPE
            contentPane.registerKeyboardAction(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onCancel();
                }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        }

        private void countKey() {
            breakKey = false;
            time = System.currentTimeMillis();
            end = time + 1000;

        }

        private void clearMaxButt(){
            MaxBPM.setText("0");
            maxClicks = 0;
        }

        private void clearCurrentBPM(){
            CurrentBPM.setText("0");
        }



        private void onOK() {
// add your code here
            dispose();
        }

        private void onCancel() {
// add your code here if necessary
            System.exit(0);
        }

        private void click(){
            if(System.currentTimeMillis() < end && breakKey==false){
                timesClicked++;
               CurrentBPM.setText(String.valueOf(timesClicked*SECONDS));
               // CurrentBPM.setText(String.valueOf(System.currentTimeMillis()));
               // MaxBPM.setText(String.valueOf(time));
            }
            else if(System.currentTimeMillis() > end && breakKey==false){
               if((timesClicked*SECONDS)> maxClicks){
                   maxClicks = timesClicked * SECONDS;
                   MaxBPM.setText(String.valueOf(maxClicks));
               }
                time = System.currentTimeMillis();
                end = time + 1000;
                timesClicked = 0;
            }
            else{CurrentBPM.setText("Press Start");}
        }






    private class EnterListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0){
            if (arg0.getKeyCode() == 90) {
            click();
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub


        }
    }

    private class XListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent arg0){
            if (arg0.getKeyCode() == 88) {
                click();
            }
        }

        @Override
        public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub


        }
    }
}

