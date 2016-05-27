import javax.swing.JButton;


public class ButtonMine extends JButton {
	boolean isMine=false;
	boolean isInited=false;
	boolean isClicked=false;
	int surrounded=0;
	int column;
	int row;
	public ButtonMine(String string, int column, int row){
		super(string);
		this.column=column;
		this.row=row;
	}
}
