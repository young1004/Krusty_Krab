//작성자 : 김재하

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

public class CashList extends JFrame implements ActionListener {
	JPanel[] p = new JPanel[2];
	Connection con;
	Statement stmt;
	ResultSet rs;
	JOptionPane popup;
	
	public MainPanel main = new MainPanel();
	public TitlePanel title = new TitlePanel();
	public ListPanel list = new ListPanel();
	public ManagePanel manage = new ManagePanel();
	public BenefitPanel benefit = new BenefitPanel();
	
	public CashList() throws SQLException {
		try { //인터넷 참조
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    System.err.println("Cannot set look and feel:" + e.getMessage ());
		}
		setTitle("가계 정리 페이지");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setResizable(false);
		for(int i = 0; i < 2; i++) {
			p[i] = new JPanel();
		}
		p[0].setLayout(new BorderLayout());
		p[1].setLayout(null);
		
		title.setBounds(0, 0, 800, 40);
		list.setBounds(0, 50, 712, 435);
		manage.setBounds(713, 45, 75, 435);
		benefit.setBounds(0, 490, 720, 30);
		benefit.setBackground(Color.white);
		
		p[0].add(main);
		p[1].add(title);
		p[1].add(list);
		p[1].add(manage);
		p[1].add(benefit);
		
		(main.ext).addActionListener(this);
		
		add(p[0], BorderLayout.NORTH);
		add(p[1]);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == main.ext) {
			this.dispose();
		}
	}
	class MainPanel extends JPanel implements ActionListener {
		JButton logo, ext;
		ImageIcon Image;
		
		public MainPanel() {
			try { //인터넷 참조
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(new BorderLayout());
			Image = new ImageIcon("image/Image.png");
			logo = new JButton(Image);
			ext = new JButton("나가기");
			
			logo.addActionListener(this);
			
			add(logo, BorderLayout.WEST);
			add(ext, BorderLayout.EAST);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == logo) {
				new main();
				dispose();
			}
		}
	}
	class TitlePanel extends JPanel implements ActionListener {
		JLabel lb;
		JButton bt;
		JTextField tf;
		
		public TitlePanel() throws SQLException {
			try { //인터넷 참조
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			lb = new JLabel("가계 정리 조회");
			tf = new JTextField(15);
			bt = new JButton("검색");
			bt.addActionListener(this);
			
			lb.setBounds(15, 10, 100, 28);
			tf.setBounds(375, 10, 260, 28);
			bt.setBounds(638, 10, 73, 28);
			
			add(lb);
			add(tf);
			add(bt);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == bt)
			{
				while((list.data).getRowCount() > 0)
					   (list.data).removeRow(0);
				try {
					rs = stmt.executeQuery("SELECT * FROM cashlist where date like '%" + tf.getText() + "%';");
					
					while (rs.next()){
						(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
								rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public class ListPanel extends JPanel {
		String[] header = {"날짜", "구분", "분류", "수입액", "지출액"};
		private DefaultTableModel data;
		JScrollPane jsp;
		JTable tb;
		
		public ListPanel() throws SQLException {
			try { //인터넷 참조
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			data = new DefaultTableModel(header,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			tb = new JTable(data);
			jsp = new JScrollPane(tb);
			
			con = makeConnection();
			stmt = con.createStatement();
			
			while(data.getRowCount() > 0)
				   data.removeRow(0);
			
		    rs = stmt.executeQuery("SELECT * FROM cashlist;");
			while (rs.next()){
				data.addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
						rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
			}
			jsp.setBounds(11, 0, 700, 430);
			
			add(jsp);
		}
	}
	class ManagePanel extends JPanel implements ActionListener {
		JButton[] bt = new JButton[6];
		String[] bst = {"수입", "지출" , "등록", "수정", "삭제", "취소"};
		
		public ManagePanel() {
			try { //인터넷 참조
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			for(int i = 0; i < 6; i++) {
				bt[i] = new JButton(bst[i]);
				bt[i].addActionListener(this);
			    add(bt[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String check = "";
			String search = (title.tf).getText();
			if(e.getSource() == bt[0]) { //수입
				if(search.equals(check)) {
					while((list.data).getRowCount() > 0)
						   (list.data).removeRow(0);
				    
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist where sep1='수입';");
						while (rs.next()){
							(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
									rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					while((list.data).getRowCount() > 0)
						   (list.data).removeRow(0);
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist where date like '%" + search + "%' and sep1='수입';");
						while (rs.next()){
							(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
									rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource() == bt[1]) { //지출
				
				if(search.equals(check)) {
					while((list.data).getRowCount() > 0)
						   (list.data).removeRow(0);
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist where sep1='지출';");
						while (rs.next()){
							(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
									rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
						}	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					while((list.data).getRowCount() > 0)
						   (list.data).removeRow(0);
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist where date like '%" + search + "%' and sep1='지출';");
						while (rs.next()){
							(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
									rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource() == bt[2]) { //등록
				try {
					new write1();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            if(e.getSource() == bt[3]) { //수정
            	try {
					if ((list.tb).getSelectedRow() >= 0){
						write1 w = new write1();
						int selectrow = (list.tb).getSelectedRow();
						String[] data = new String[(list.tb).getColumnCount()];
						
						for(int i=0;i < (list.tb).getColumnCount();i++)
							data[i] = (String)(list.tb).getValueAt(selectrow, i);
						
						(w.day.text).setText(data[0]);
						(w.expimp.text).setText(data[1]);
						(w.kind.text).setText(data[2]);
						(w.money.text[0]).setText(data[3]);
						(w.money.text[1]).setText(data[4]);
					}
					else {
						popup.showMessageDialog(bt[3], "열을 선택하시오.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            if(e.getSource() == bt[4]) { //삭제
            	if ((list.tb).getSelectedRow() >= 0){
					String[] data = new String[(list.tb).getColumnCount()];
					
					for(int i=0;i < (list.tb).getColumnCount();i++)
						data[i] = (String)(list.tb).getValueAt((list.tb).getSelectedRow(), i);
					
					String s = "DELETE FROM cashlist where date='" + data[0] + "' and sep2='" + data[2] + "';";
					int i;
					try {
						i = stmt.executeUpdate("DELETE FROM cashlist where date='" + data[0]
								+ "' and sep2='" + data[2] + "';");
						if (i == 1) {
							System.out.println("레코드 삭제 성공");
							popup.showMessageDialog(bt[3], "삭제 완료");
						}
						else
							System.out.println("레코드 삭제 실패");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
            	else {
					popup.showMessageDialog(bt[3], "열을 선택하시오.");
				}
            	
            	//변경된 재료 데이터를 갱신
            	while((list.data).getRowCount() > 0)
            		(list.data).removeRow(0);
            	
 		        try {
					rs = stmt.executeQuery("SELECT * FROM cashlist;");
					while (rs.next()){
	 			    	(list.data).addRow(new String[]{rs.getString("date"), rs.getString("sep1"),
	 	 						rs.getString("sep2"), rs.getString("imp"), rs.getString("export")});
	 			    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
            if(e.getSource() == bt[5]) { //취소
            	(list.tb).clearSelection();
            	(title.tf).setText(null);
			}
		}
	}
	
	class BenefitPanel extends JPanel implements ActionListener {
		JLabel[] lb = new JLabel[4];
		String[] lbt = {"기간", "수입", "지출", "손익"};
		JTextField[] tf = new JTextField[4];
		JButton bt;
		
		public BenefitPanel() {
			setLayout(null);
			for(int i = 0; i < 4; i++){
				lb[i] = new JLabel(lbt[i]);
				tf[i] = new JTextField(20);
				
				add(lb[i]);
				add(tf[i]);
			}
			
			lb[0].setBounds(10, 5, 30, 20);
			tf[0].setBounds(45, 5, 40, 20);
			tf[0].addActionListener(this);
			
			lb[1].setBounds(120, 5, 30, 20);
			tf[1].setBounds(160, 5, 110, 20);
			tf[1].setEditable(false);
			
			lb[2].setBounds(290, 5, 30, 20);
			tf[2].setBounds(330, 5, 110, 20);
			tf[2].setEditable(false);
			
			bt = new JButton("계산");
			bt.setBounds(460, 5, 60, 20);
			add(bt);
			bt.addActionListener(this);
			
			lb[3].setBounds(563, 5, 30, 20);
			tf[3].setBounds(602, 5, 110, 20);
			tf[3].setEditable(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String check = "";
			String search;
			if(e.getSource() == tf[0]) {
				search = tf[0].getText();
				tf[3].setText("");
				
				if(search.equals(check)) {
					tf[1].setText("");
					tf[2].setText("");
				}
				else {
					try {
						rs = stmt.executeQuery("SELECT sum(imp),sum(export) FROM cashlist where date like '%" + tf[0].getText() + "%';");
						while(rs.next()) {
							tf[1].setText(rs.getString("sum(imp)"));
							tf[2].setText(rs.getString("sum(export)"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource() == bt) {
				int cal = Integer.parseInt(tf[1].getText()) - Integer.parseInt(tf[2].getText());
				tf[3].setText(Integer.toString(cal));
			}
		}
	}
	public static Connection makeConnection() { //객체지향 프로그램밍 ConnectDatebase 실습 참조
		String url = "jdbc:mysql://localhost/krustykrab";
		String id = "root";
		String password = "yopmail";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CashList f = new CashList();
	}
}