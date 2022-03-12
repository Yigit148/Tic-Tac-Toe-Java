import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    static int endgame = 0 ;


    TicTacToe(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(40,23,50));
        textfield.setFont(new Font("Ink Free", Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3,3));
        textfield.setBackground(new Color(50, 100, 150));

        for (int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);


        firstTurn();


    }




    @Override
    public void actionPerformed(ActionEvent e){

        for (int i = 0; i < 9; i++){
            if (e.getSource() == buttons[i]){
                if (player1_turn){
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(25,50,55));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check("X");
                        endgame++;
                        EndGame();

                    }
                }else {
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(55,0,25));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check("O");
                        endgame++;
                        EndGame();
                }
                }
            }
        }

    }

    public void firstTurn(){

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            textfield.setText("X turn");
        }else{
            player1_turn = false;
            textfield.setText("O turn");
        }

    }




    public void check(String player){

        int[][] win = {{0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};


        for (int i = 0; i < win.length; i++) {
            int q = win[i][0];
            int w = win[i][1];
            int e = win[i][2];
            if ((buttons[q].getText().equals(player)) && (buttons[w].getText().equals(player)) && (buttons[e].getText().equals(player))) {
                textfield.setText(player + " wins");


                buttons[q].setBackground(Color.GREEN);
                buttons[w].setBackground(Color.GREEN);
                buttons[e].setBackground(Color.GREEN);


                for(int s=0;s<9;s++) {
                    buttons[s].setEnabled(false);
                }
            }



        }
    }

    public void EndGame(){
        if (endgame == 9){
            textfield.setText("Tie");
            for (int i = 0; i < 9; i++){
                buttons[i].setBackground(Color.GREEN);
            }
        }
    }

    }