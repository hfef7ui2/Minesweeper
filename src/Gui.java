import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.Printable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Gui {
	int BUTTONSIZE1 = 30;
	int BUTTONSIZE2 = 30;
	int ROW = 20;
	int COLUMN = 20;
	int MINE = 75;
	int need = ROW * COLUMN - MINE;
	int mineLeft;
	// int overflow=0;
	ButtonListener buttonListener;
	RestartListener restartListener;
	JFrame frame;
	JPanel panel,panel2;
	ButtonMine[][] buttons;
	JLabel mineLeftLabel;
	JButton restartButton;
	boolean firstClicked = false;
	boolean firstTimeRun=true;
	boolean canClaimWin=true;
	

	// Font font;
	public Gui() {
		initGui();
	}

	public void createField(int row, int column) {
		buttons = new ButtonMine[column][row];
		buttonListener = new ButtonListener();
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				buttons[i][j] = new ButtonMine("", i, j);
				buttons[i][j].setPreferredSize(new Dimension(BUTTONSIZE1,
						BUTTONSIZE2));
				//buttons[i][j].addActionListener(buttonListener);
				buttons[i][j].addMouseListener(buttonListener);
				buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
				// buttons[i][j].setFont(font);
				panel.add(buttons[i][j]);
			}
		}
	}

	
	class RestartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			init();
		}
		
	}
	
	
	class ButtonListener implements MouseListener {
		
		boolean pressed;
		boolean doubleClick;
		//boolean nextDouble;
		ButtonMine tempButton;
//		public void actionPerformed(ActionEvent e) {
//			// TODO Auto-generated method stub
//			tempButton = (ButtonMine) e.getSource();
//			tempButton.isClicked = true;
//			tempButton.setBackground(Color.white);
//			if (!firstClicked) {
//				// set mines
//				tempButton.isInited = true;
//				for (int i = 0; i < MINE;) {
//					ButtonMine tempMine = buttons[(int) (Math.random() * COLUMN)][(int) (Math
//							.random() * ROW)];
//					if (!tempMine.isInited) {
//						tempMine.isMine = true;
//						// tempMine.setText("*");
//						i++;
//					}
//				}
//				for (int i = 0; i < COLUMN; i++) {
//					for (int j = 0; j < ROW; j++) {
//						// buttons[i][j].surrounded=
//
//						getSurroundedMineAmount(buttons[i][j]);
//						// if(!buttons[i][j].isMine){
//						// buttons[i][j].setText(new
//						// Integer(buttons[i][j].surrounded).toString());
//						// }
//					}
//				}
//				firstClicked = true;
//			}
//			if (tempButton.isMine) {
//				gg(tempButton);
//			} else {
//				need--;
//				int surrounded = tempButton.surrounded;
//				if (surrounded > 0) {
//					tempButton.setText(new Integer(surrounded).toString());
//				} else {
//					exploreField(tempButton);
//				}
//				if (need == 0)
//					win();
//			}
//		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			pressed = true;
			doubleClick=false;
			//nextDouble=false;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			pressed = false;
			doubleClick=false;
			//nextDouble=false;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			tempButton = (ButtonMine) e.getSource();
			tempButton.getModel().setArmed(true);
			tempButton.getModel().setPressed(true);
            pressed = true;
            if(e.getModifiersEx()==(e.BUTTON3_DOWN_MASK + e.BUTTON1_DOWN_MASK)){
            	doubleClick=true;
            	//System.out.println("doubleclick");
            }
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			tempButton = (ButtonMine) e.getSource();
			tempButton.getModel().setArmed(false);
			tempButton.getModel().setPressed(false);
			
            if (pressed) {
            	if(e.getModifiersEx()==(e.BUTTON3_DOWN_MASK )||e.getModifiersEx()==(e.BUTTON1_DOWN_MASK)){
            		exploreField2(tempButton);
            		//System.out.println("double");
            		//nextDouble=true;
            	}
//            	else if(nextDouble){
//            		System.out.println("nextDouble");
//            		nextDouble=false;
//            	}
            	else if (SwingUtilities.isRightMouseButton(e)) {
                	if(!tempButton.isClicked){
                		if(tempButton.getText().equals("")){
                			tempButton.setText("M");
                		}else if(tempButton.getText().equals("M")){
                			tempButton.setText("?");
                		}else tempButton.setText("");
                	}
                	mineLeftLabel.setText("Mines left: "+--mineLeft);
                }
                else {
                	boolean old=tempButton.isClicked;
                	tempButton = (ButtonMine) e.getSource();
        			tempButton.isClicked = true;
        			tempButton.setBackground(Color.white);
        			if (!firstClicked) {
        				// set mines
        				tempButton.isInited = true;
        				for (int i = 0; i < MINE;) {
        					ButtonMine tempMine = buttons[(int) (Math.random() * COLUMN)][(int) (Math
        							.random() * ROW)];
        					if (!tempMine.isInited) {
        						tempMine.isMine = true;
        						tempMine.isInited=true;
        						// tempMine.setText("*");
        						i++;
        					}
        				}
        				for (int i = 0; i < COLUMN; i++) {
        					for (int j = 0; j < ROW; j++) {
        						// buttons[i][j].surrounded=

        						getSurroundedMineAmount(buttons[i][j]);
        						// if(!buttons[i][j].isMine){
        						// buttons[i][j].setText(new
        						// Integer(buttons[i][j].surrounded).toString());
        						// }
        					}
        				}
        				firstClicked = true;
        			}
        			if(!old)normalClickHelper(tempButton.column, tempButton.row);
//        			if (tempButton.isMine) {
//        				gg(tempButton);
//        			} else {
//        				need--;
//        				int surrounded = tempButton.surrounded;
//        				if (surrounded > 0) {
//        					tempButton.setText(new Integer(surrounded).toString());
//        				} else {
//        					exploreField(tempButton);
//        				}
//        				if (need == 0)
//        					win();
//        			}
                }
            }
            pressed = false;
		}
	}

	public void initGui() {
		// font=new Font("font", Font.PLAIN, 10);
		//System.out.print("init");
		if(firstTimeRun){
			frame = new JFrame();
			panel = new JPanel();
		}
		firstTimeRun=false;
		firstClicked=false;
		canClaimWin=true;
		mineLeft=MINE;
		frame.setLayout(null);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		// panel.setSize(COLUMN*BUTTONSIZE1, ROW*BUTTONSIZE2);
		// panel.setPreferredSize(new Dimension(COLUMN*BUTTONSIZE1,
		// ROW*BUTTONSIZE2));
		panel.removeAll();
		panel.setBounds(3, 100, COLUMN * BUTTONSIZE1, ROW * BUTTONSIZE2);
		createField(ROW, COLUMN);
		panel2=new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel2.setBounds(3, 0, COLUMN * BUTTONSIZE1, 100);
		mineLeftLabel=new JLabel("Mines left: "+MINE);
		restartButton=new JButton("restart");
		restartListener=new RestartListener();
		restartButton.addActionListener(restartListener);
		panel2.add(mineLeftLabel);
		panel2.add(restartButton);
		panel.setVisible(false);
		panel.setVisible(true);
		panel.validate();
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.CENTER, panel2);
		frame.setSize(COLUMN * BUTTONSIZE1 + 22, ROW * BUTTONSIZE2 + 150);
		//frame.setVisible(false);
		frame.setVisible(true);
		frame.validate();
	}

	public int surroundHelper(int a, int b) {
		if (a < 0 || b < 0 || a >= COLUMN || b >= ROW) {
			return 0;
		} else {
			// System.out.print(a+" "+b+""+buttons[a][b].isMine);
			if (buttons[a][b].isMine) {
				return 1;
			} else
				return 0;
		}
	}

	public int getSurroundedMineAmount(ButtonMine buttonMine) {
		int counter = 0;
		int column = buttonMine.column;
		int row = buttonMine.row;
		// System.out.println(column+"c"+row+"d");
		counter += surroundHelper(column - 1, row - 1);
		counter += surroundHelper(column - 1, row);
		counter += surroundHelper(column - 1, row + 1);
		counter += surroundHelper(column, row - 1);
		counter += surroundHelper(column, row + 1);
		counter += surroundHelper(column + 1, row - 1);
		counter += surroundHelper(column + 1, row);
		counter += surroundHelper(column + 1, row + 1);
		// System.out.print(counter+" ");
		buttonMine.surrounded = counter;
		return counter;
	}

	public void exploreHelper(int a, int b) {
		if (a < 0 || b < 0 || a >= COLUMN || b >= ROW) {

		} else if (!buttons[a][b].isClicked) {
			buttons[a][b].isClicked = true;
			buttons[a][b].setBackground(Color.white);
			need--;
			System.out.println(need);
			int surrounded = buttons[a][b].surrounded;
			if (surrounded > 0) {
				buttons[a][b].setText(new Integer(surrounded).toString());
			} else {
				exploreField(buttons[a][b]);
			}
			if (need == 0)
				win();
		}
	}

	public void exploreField(ButtonMine buttonMine) {
		int column = buttonMine.column;
		int row = buttonMine.row;
		exploreHelper(column - 1, row - 1);
		exploreHelper(column - 1, row);
		exploreHelper(column - 1, row + 1);
		exploreHelper(column, row - 1);
		exploreHelper(column, row + 1);
		exploreHelper(column + 1, row - 1);
		exploreHelper(column + 1, row);
		exploreHelper(column + 1, row + 1);
	}
	
	public void normalClickHelper(int a, int b){
		ButtonMine tempButton0=buttons[a][b];
		//boolean temp=true;
		if(!tempButton0.isClicked){
			tempButton0.isClicked = true;
			//temp=false;
		}
		tempButton0.setBackground(Color.white);
		if (tempButton0.isMine) {
			gg(tempButton0);
		} else {
			need--;
			System.out.println(need);
			int surrounded = tempButton0.surrounded;
			if (surrounded > 0) {
				tempButton0.setText(new Integer(surrounded).toString());
			} else {
				exploreField(tempButton0);
			}
			if (need == 0)
				win();
		}
	}
	
	public int isMarked(int a, int b){
		if (a < 0 || b < 0 || a >= COLUMN || b >= ROW) {
			return 0;
		} else if(buttons[a][b].getText().equals("M")){
			return 1;
		} else return 0;
	}
	
	public boolean properlyMarked(int a, int b){
		if(!Character.isDigit(buttons[a][b].getText().charAt(0))){
			return false;
		}
		int text=Integer.valueOf(buttons[a][b].getText());
		int tempCounter=0;
		tempCounter+=isMarked(a-1, b-1);
		tempCounter+=isMarked(a-1, b);
		tempCounter+=isMarked(a-1, b+1);
		tempCounter+=isMarked(a, b-1);
		tempCounter+=isMarked(a, b+1);
		tempCounter+=isMarked(a+1, b-1);
		tempCounter+=isMarked(a+1, b);
		tempCounter+=isMarked(a+1, b+1);
		return (text==tempCounter);
	}
	public void exploreHelper2(int a, int b){
		if (a < 0 || b < 0 || a >= COLUMN || b >= ROW) {

		} else if ((!buttons[a][b].isClicked)&&(!buttons[a][b].getText().equals("M"))&&(!buttons[a][b].getText().equals("?"))) {
			normalClickHelper(a,b);
		}
	}
	
	public void exploreField2(ButtonMine buttonMine){
		int column = buttonMine.column;
		int row = buttonMine.row;
		if(properlyMarked(column, row)){
			exploreHelper2(column-1, row-1);
			exploreHelper2(column-1, row);
			exploreHelper2(column-1, row+1);
			exploreHelper2(column, row-1);
			exploreHelper2(column, row+1);
			exploreHelper2(column+1, row-1);
			exploreHelper2(column+1, row);
			exploreHelper2(column+1, row+1);
		}
	}

	public void gg(ButtonMine buttonMine) {
		//
		canClaimWin=false;
		buttonMine.setText("*");
		//buttonMine.getModel().set
		for (int i = 0; i < COLUMN; i++) {
			for (int j = 0; j < ROW; j++) {
				if(buttons[i][j].isMine){
					//if(!buttons[i][j].getText().equals("M"))
					buttons[i][j].setText("*");
					buttons[i][j].setBackground(Color.yellow);
				}
			}
		}
		buttonMine.setBackground(Color.red);
	}

	public void win() {
		if(canClaimWin){
			JOptionPane.showMessageDialog(null, "You win!");
			canClaimWin=false;
		}
	}

	public void init() {
		need = ROW * COLUMN - MINE;
		//System.out.print("refresh");
		initGui();
	}
	// right click
	// double click
	// init()
	// mine remained show
}