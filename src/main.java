//작성자 : 김재하

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class main extends JFrame implements ActionListener{
	JPanel p[] = new JPanel[2];
	JButton b[] = new JButton[4];
	ImageIcon Logo;
	JLabel L;
	
	public main(){
		try { //인터넷 참조
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage ()); 
		}
		
		setSize(800,600);
		setTitle("메인 페이지");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		for(int i=0; i<2;i++){
			p[i] = new JPanel();
		}
		
		for(int i=0; i<4;i++){
			b[i] = new JButton();
		}
		
		p[0].setLayout(null);
		p[1].setLayout(null);
		String[] buttonStr = {"정보 조회","가계 정리(수입/지출)", "주문 내역서", "제품 판매(판매/정산/주문)"};
		
		for	(int i = 0;i <4;i++){
			b[i] = new JButton(buttonStr[i]);
			b[i].addActionListener(this);
		}
		Logo = new ImageIcon("image/Logo.png");
		L = new JLabel(Logo);
		
		L.setBounds(300, 30, 200, 100);
		
		b[0].setBounds(170, 70, 200, 100);
		b[1].setBounds(420, 70, 200, 100);
		b[2].setBounds(170, 220, 200, 100);
		b[3].setBounds(420, 220, 200, 100);
		
		p[0].add(L);
		
		p[1].add(b[0]);
		p[1].add(b[1]);
		p[1].add(b[2]);
		p[1].add(b[3]);
		
		p[0].setBounds(0, 0, 800, 160);
		p[0].setBackground(Color.white);
		
		p[1].setBounds(0, 160, 800, 440);
		p[1].setBackground(Color.LIGHT_GRAY);
		
		add(p[0]);
		add(p[1]);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b[0]) { //정보 조회
			try {
				new Info_check();
				dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        if (e.getSource() == b[1]) { //금전 출납 (수입/지출)
			try {
				new CashList();
				dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        if (e.getSource() == b[2]) { //거래 내역서
        	try {
				new OrderList();
				dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if (e.getSource() == b[3]) { //제품 판매
        	try {
				new Sell_product();
				dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		main f = new main();
	}
}