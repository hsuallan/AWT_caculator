/*1.Author Hsuallan
 *2.皆有說明
 *3.皆符合標準 自評100(●´ω｀●)ゞ
 *4.80%原創，有參考別人用string給按鈕值&事件用string來抓
 *5.格式算清楚，然後可以連算ex:8+3 = 11 +4 =14，可以用上一個答案來直接算
 *  然後還融合一些顏色讓版面好看，配色可google: ocean dolch
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class H7_110516036 extends JFrame {//主要的介面
	static GridLayout grid = new GridLayout(5, 4, 2, 2);//定義5*4的版面&間隔==2
	static JPanel panel = new JPanel(grid);//Panel 套用grid
	static JTextField txt = new JTextField(25);//textfield 長度25

	public static void main(String[] args) {
		H7_110516036 frm = new H7_110516036();
		Container cp = frm.getContentPane();
		//設定基本資料
		frm.setTitle("Calculator");
		frm.setResizable(false);
		frm.setAlwaysOnTop(true);
		frm.setSize(320, 230);
		frm.setLocation(800, 400);
		frm.setVisible(true);
		panel.setBackground(new Color(80, 47, 31));
		txt.setEditable(false);
		cp.setLayout(new FlowLayout());
		cp.setBackground(new Color(80, 47, 31));
		cp.add(txt);
		cp.add(panel);
		key(panel);//key加入panel中
	}

	public static void key(JPanel panel) {
		act a = new act();
		String[] str = { "DEL", "RESET", "Author", "=", "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "x", "0",
				".", "00", "÷", };
		Color tu2 = new Color(37, 205, 197);
		Color N9 = new Color(49, 51, 55);
		Color CC = new Color(125, 128, 115);
		for (int i = 0; i < str.length; i++) {//將鍵盤值給予按鈕
			JButton but = new JButton(str[i]);
			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {//顏色設定用
				but.setBackground(CC);
				but.setForeground(tu2);
				but.addActionListener(a);
			} else {//顏色設定用
				but.setBackground(N9);
				but.setForeground(tu2);
				but.addActionListener(a);
			}
			panel.add(but);
		}
	}

	static class act implements ActionListener {//事件處理區
		String output = "", operator, temp = "";
		float operand1 = 0, operand2 = 0, ans;
		boolean operator_enter = true;

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get operand
			if (e.getActionCommand().matches("[0-9\\.]")||e.getActionCommand()=="00") {
				output += e.getActionCommand();
				if (operator_enter == false) {// 2nd
					temp += e.getActionCommand();
					operand2 = Float.parseFloat(temp);
				}
				txt.setText(output);
			}
			// Get operator
			try {
				if (operator_enter && (e.getActionCommand() == "+" || e.getActionCommand() == "-"
						|| e.getActionCommand() == "x" || e.getActionCommand() == "÷")) {
					operand1 = Float.parseFloat(output);
					output += e.getActionCommand();
					txt.setText(output);
					operator = e.getActionCommand();
					operator_enter = false;
				}
			} catch (Exception e2) {
			}
			if (e.getActionCommand() == "=") {
				try {
					switch (operator) {
					case "+":
						txt.setText(output = Float.toString(operand1 + operand2));
						operator_enter = true;
						temp = "";
						break;
					case "-":
						txt.setText(output = Float.toString(operand1 - operand2));
						operator_enter = true;
						temp = "";
						operand1 = Float.parseFloat(output);
						break;
					case "x":
						txt.setText(output = Float.toString(operand1 * operand2));
						operator_enter = true;
						operand1 = Float.parseFloat(output);
						temp = "";
						break;
					case "÷":
						txt.setText(output = Float.toString(operand1 / operand2));
						operator_enter = true;
						operand1 = Float.parseFloat(output);
						temp = "";
						break;
					}

				} catch (Exception e2) {
				}

			}
			if (e.getActionCommand() == "RESET") {
				output = "";
				operand1 = 0;
				operator = null;
				operand2 = 0;
				temp = "";
				operator_enter = true;
				txt.setText(null);
			}
			if (e.getActionCommand() == "DEL") {
				int last = output.length();
				String lastString = "";
				try {
					lastString = output.substring(last - 1);
				} catch (Exception e2) {
				}
				if (lastString.matches("[^0-9]")) {// +-x÷
					operator_enter = true;
					operator = null;
				} else {
					if (temp.isEmpty() == false) {// operand2 enterin
						try {
							temp = temp.substring(0, temp.length() - 1);
							operand2 = Float.parseFloat(temp);
						} catch (Exception e2) {
						}
					}
				}
				try {
					output = output.substring(0, last - 1);
					txt.setText(output);
				} catch (StringIndexOutOfBoundsException e2) {
				}

			}
			if (e.getActionCommand() == "Author") {
				txt.setText("Author = Hsuallan");
			}
		}
	}
}
