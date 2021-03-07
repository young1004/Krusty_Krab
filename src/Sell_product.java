//작성자 : 조용구(판매 탭), 김태주(일일 매출 정산 탭), 김재하(재료주문 탭)

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Sell_product extends JFrame implements ActionListener{
		int PanelCount = 5;
		JPanel[] p = new JPanel[PanelCount];
		Connection con;
		Statement stmt;
		ResultSet rs;
		JOptionPane popup;
		int counts =0 ;
		String gname[] = new String [5];
		int gprice[] =new int[5];
		public LogoPanel Logop = new LogoPanel();
		public listPanel listp = new listPanel();
		public menuPanel menup = new menuPanel();
		public infoPanel infop = new infoPanel();
		public sellPanel sellp = new sellPanel();
		public accountPanel account = new accountPanel();
		public metrialPanel metrial = new metrialPanel();
		
		public Sell_product() throws SQLException{
			try { //인터넷 참조
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
			catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			setTitle("판매/정산/주문");
			setSize(800, 600);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);
			
			for(int i = 0; i < PanelCount; i++){
				p[i] = new JPanel();
				p[i].setLayout(null);
				add(p[i]);
			}
			Logop.setBounds(0, 0, 800, 50);
			listp.setBounds(0, 0, 400, 435);
			menup.setBounds(400, 4, 380, 150);
			infop.setBounds(400, 150, 400, 300);
			sellp.setBounds(0, 435, 800, 50);
			
			p[0].setBounds(0, 0, 800, 50);
			
			JTabbedPane select = new JTabbedPane();
			select.addTab("판매", p[1]);
			select.addTab("일별 매출 정산", account);
			select.addTab("재료 주문", metrial);
			select.setBounds(0, 47, 785, 515);
			add(select);
			
			p[0].add(Logop);
			p[1].add(listp);
			p[1].add(menup);
			p[1].add(infop);
			p[1].add(sellp);
			
			(Logop.btn).addActionListener(this);

			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == Logop.btn) {
				this.dispose();
			}
		}

	class LogoPanel extends JPanel implements ActionListener {
		JButton Logo, btn;
		ImageIcon Image;
		
		public LogoPanel(){
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			Image = new ImageIcon("image/Image.png");
			Logo = new JButton(Image);
			
			btn = new JButton("나가기");
			add(btn);
			btn.setBounds(650, 0, 130, 28);
			
			Logo.addActionListener(this);
			add(Logo);
			Logo.setBounds(5, 0, 100, 28);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == Logo) {
				new main();
				dispose();
			}
		}
	}

	class listPanel extends JPanel{
		String[] listname = {"제품","수량", "가격"};
		private DefaultTableModel data;
		JTable infotable;
		JScrollPane scroll;
		
		public listPanel() throws SQLException{
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			data = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			infotable = new JTable(data);
			scroll = new JScrollPane(infotable);
			
			for (int i = 0; i < listname.length; i++)
				infotable.getColumnModel().getColumn(i).setPreferredWidth(100);
			scroll.setPreferredSize(new Dimension(400, 430));
			
			con = makeConnection();
			stmt = con.createStatement();
			
			while(data.getRowCount() > 0)
				   data.removeRow(0);
			
			scroll.setBounds(0, 0, 400, 430);
			
			add(scroll);
		}
	}
	class infoPanel extends JPanel implements ActionListener{
		int count = 5;
		JLabel[] label = new JLabel[count];
		JTextField[] text = new JTextField[count];
		String[] Labeltext = {"제품명", "수량", "제품 가격", "받은 금액", "거스름돈"};
		
		JButton[] btn = new JButton[2];
		String[] btnstr = {"추가", "취소"};	
		public infoPanel(){
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			for(int i = 0; i < 3; i++){
				label[i] = new JLabel(Labeltext[i]);
				text[i] = new JTextField(20);
				
				label[i].setBounds(20, 10+i*50, 73, 28);
				text[i].setBounds(130, 10+i*50, 200, 28);
				
				add(label[i]);
				add(text[i]);
			}
			text[0].setEditable(false);
			text[2].setEditable(false);
			
			for(int i = 3; i < 5; i++){
				label[i] = new JLabel(Labeltext[i]);
				text[i] = new JTextField(20);
			    
				label[i].setBounds(20, 55+i*50, 73, 28);
				text[i].setBounds(130, 55+i*50, 200, 28);
				
				add(label[i]);
				add(text[i]);
			}
			text[3].addActionListener(this);
			
			for(int i = 0; i < btnstr.length; i++)
				btn[i] = new JButton(btnstr[i]);
			
			btn[0].setBounds(130, 150, 93, 28);
			btn[1].setBounds(235, 150, 93, 28);
			
			for(int i = 0; i < btnstr.length; i++){
				add(btn[i]);
				btn[i].addActionListener(this);
				}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn[0]){//추가버튼
				int count = Integer.parseInt(text[1].getText());//수량
				int price = count * Integer.parseInt(text[2].getText());//가격
				String p = Integer.toString(price);//addRow 위해 형변환
				
				(listp.data).addRow(new String[] {
						text[0].getText(),text[1].getText(),p});
				
				price = 0;//초기화
				for(int i = 0; i < (listp.infotable).getRowCount(); i++){
					p = (String)(listp.infotable).getValueAt(i, 2);//테이블에서 가격 가져옴
					price += Integer.parseInt(p);//가격 모두 더함
				}
				p = Integer.toString(price);
				(sellp.text).setText(p);//총금액 textField
				
				for(int i = 0; i <3; i++)
					text[i].setText("");
			}
			if(e.getSource() == btn[1]){//취소
				for(int i = 0; i <3; i++)
					text[i].setText("");
			}
			if(e.getSource() == text[3]){//받은 금액
				String s = text[3].getText();
				int change = Integer.parseInt(s) - Integer.parseInt((sellp.text).getText());
				s = Integer.toString(change);
				text[4].setText(s);	
			}
		}
	}
	class sellPanel extends JPanel implements ActionListener {
		JTextField text, lb;
		JButton[] btn = new JButton[2];
		String[] btnstr = {"주문 완료", "주문 취소"};
		Connection con;
		Statement stmt, stmt1, stmt2;
		ResultSet rs, rs1;
		JButton popupbtn = new JButton("확인");
		public sellPanel() throws SQLException {
			con = makeConnection();
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			lb = new JTextField("총 금액");
			text = new JTextField();
			
			lb.setBounds(0, 15, 115, 28);
			text.setBounds(120, 15, 370, 28);
			
			for(int i = 0; i < btnstr.length; i++)
				btn[i] = new JButton(btnstr[i]);
			
			btn[0].setBounds(540, 15, 93, 28);
			btn[1].setBounds(645, 15, 93, 28);
			
			for(int i = 0; i < btnstr.length; i++){
				add(btn[i]);
				btn[i].addActionListener(this);
				}
			lb.setEditable(false);
			add(lb);
			add(text);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btn[0]){//주문완료
				String s;
				int counter;//갯수와 관련된 부분을 저장할 변수
				boolean check = true;//예외처리를 위한 boolean 변수
				
				for (int i = 0; i < (listp.infotable).getRowCount(); i++) {//테이블의 모든 줄 탐색
					
					if((infop.text[3].getText()).equals("") || (infop.text[4].getText()).equals("")){
						popup.showMessageDialog(popupbtn, "받은 금액과 거스름돈을 계산해 주십시오.");
						break;//예외 처리1
					}

					try {
						for (int j = 0; j < (listp.infotable).getRowCount(); j++) {//재료 갯수 확인을 위함
							s = "select gname, gcookm, mcount from goods";
							s += " where gname ='" + (listp.infotable).getValueAt(i, 0) + "';";
							rs = stmt.executeQuery(s);//제품 테이블 검색값 저장

								while (rs.next()) {// 검색된 값 탐색
									String material = rs.getString("gcookm");// 재료명
									String mcount = rs.getString("mcount");// 갯수
									int gcount = Integer.parseInt((String) (listp.infotable).getValueAt(i, 1));// 제품갯수
									int count = gcount * Integer.parseInt(mcount);// 총 필요한 제품갯수
									
									s = "select * from material where mname ='";
									s += material + "';";// 재료 테이블 값 쿼리문 작성

									rs1 = stmt1.executeQuery(s);// 또다른 resultset에 값 저장
									rs1.next();
									counter = rs1.getInt("mcount");// 재료 재고 갯수 가져옴

									if (counter < count)// 비교후
										check = false;// 재료 재고가 적으면 값 바뀜

								}
							}
						
						s = "select gname from sell_list where gname='";
						s += (listp.infotable).getValueAt(i, 0) + "';";//쿼리문 작성
						
						rs = stmt.executeQuery(s);//결과값 저장
						
						if (rs.next()) {//결과값이 있다면

							if (check == true) {//재료가 부족하지 않으면
								s = "select gname, gcookm, mcount from goods";
								s += " where gname ='" + (listp.infotable).getValueAt(i, 0) + "';";
								rs = stmt.executeQuery(s);//결과값 저장

								while (rs.next()) {
									String material = rs.getString("gcookm");
									String mcount = rs.getString("mcount");
									int gcount = Integer.parseInt((String)(listp.infotable).getValueAt(i, 1));
									int count = gcount * Integer.parseInt(mcount);

									s = "UPDATE material SET mcount = mcount - ";
									s += count + " where mname ='";
									s += material + "';";
									stmt2.executeUpdate(s);
								}

								s = "UPDATE sell_list SET scount =scount+";
								s += (listp.infotable).getValueAt(i, 1);
								s += ", sprice =sprice+" + (listp.infotable).getValueAt(i, 2);
								s += " where gname ='" + (listp.infotable).getValueAt(i, 0);
								s += "';";
								stmt.executeUpdate(s);
							}
						}
						else {
							if (check == true) {
								s = "select gname, gcookm, mcount from goods";
								s += " where gname ='" + (listp.infotable).getValueAt(i, 0) + "';";
								rs = stmt.executeQuery(s);

								while (rs.next()) {
									String material = rs.getString("gcookm");
									String mcount = rs.getString("mcount");
									int gcount = Integer.parseInt((String)(listp.infotable).getValueAt(i, 1));
									int count = gcount * Integer.parseInt(mcount);

									s = "UPDATE material SET mcount = mcount - ";
									s += count + " where mname ='";
									s += material + "';";
									stmt2.executeUpdate(s);
								}

								s = "INSERT INTO sell_list (gname, scount, sprice) ";
								s += "VALUES ('" + (listp.infotable).getValueAt(i, 0);
								s += "', " + (listp.infotable).getValueAt(i, 1);
								s += ", " + (listp.infotable).getValueAt(i, 2);
								s += ")";

								stmt.executeUpdate(s);
							}
						}
						
						while((account.data).getRowCount() > 0)
							(account.data).removeRow(0);
						
						rs = stmt.executeQuery("SELECT * FROM sell_list;");
						while (rs.next()){//일별 매출 정산 탭 새로고침
							(account.data).addRow(new String[]{rs.getString("gname"), rs.getString("scount"), rs.getString("sprice")});
						}
						while((metrial.data).getRowCount() > 0)
							(metrial.data).removeRow(0);
						
						rs = stmt.executeQuery("select * from material");
						while (rs.next()){//재료 주문 창 새로고침
							(metrial.data).addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
				    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(check == false){
						check = true;
						popup.showMessageDialog(popupbtn, "재료가 부족합니다!");
						break;
					}
				}
				while((listp.data).getRowCount() > 0)
					(listp.data).removeRow(0);
				
				(sellp.text).setText("");
				for(int i = 3; i < 5; i++)
					infop.text[i].setText("");
			}
			
			if(e.getSource() == btn[1]){//주문취소
				while((listp.data).getRowCount() > 0)
					(listp.data).removeRow(0);
				
				(sellp.text).setText("");
				for(int i = 3; i < 5; i++)
					infop.text[i].setText("");
			}
		}
	}
	class menuPanel extends JPanel implements ActionListener{
		int gnum = 6;
		JButton[] btn = new JButton[gnum];
		String[] btnstr = new String[gnum];
		Connection con;
		Statement stmt;
		ResultSet rs;
		String q = "select DISTINCT gname from goods;";//쿼리문
		public menuPanel() throws SQLException{
			con = makeConnection();
			stmt = con.createStatement();
			
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(new GridLayout(2,3));
			
			rs = stmt.executeQuery(q);
			
			for(int i = 0; i < gnum; i++){//제품이름으로 버튼생성
				rs.next();
				btnstr[i] = rs.getString("gname");
				btn[i] = new JButton(btnstr[i]);
				add(btn[i]);	
				btn[i].addActionListener(this);
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//각각의 제품에 대한 버튼
			if(e.getSource() == btn[0]){
				q = "select *from goods where gname='";
				q += btnstr[0] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btn[1]){
				q = "select *from goods where gname='";
				q += btnstr[1] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			if(e.getSource() == btn[2]){
				q = "select *from goods where gname='";
				q += btnstr[2] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			if(e.getSource() == btn[3]){
				q = "select *from goods where gname='";
				q += btnstr[3] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			if(e.getSource() == btn[4]){
				q = "select *from goods where gname='";
				q += btnstr[4] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
			if(e.getSource() == btn[5]){
				q = "select *from goods where gname='";
				q += btnstr[5] + "';";
				try {
					rs = stmt.executeQuery(q);
					rs.next();
					(infop.text[0]).setText(rs.getString("gname"));
					(infop.text[2]).setText(rs.getString("gprice"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		}
	}
	class accountPanel extends JPanel implements ActionListener{
		int count = 4;// 텍스트 라벨를 생성하기 위한 변수
		
		String[] listname = {"제품", "수량", "가격"};// 리스트 스키마
		private DefaultTableModel data; //객체지향 프로그래밍 Coffee Server, Client 실습 참조
		JTable infotable; //리스트 선언
		JScrollPane scroll;// 스크롤 생성
		JLabel[] label = new JLabel[count];//라벨 생성
		JTextField[] text = new JTextField[count];//텍스트 테이블 생성
		String[] Labeltext = {"날짜", "구분", "분류", "매출"}; //라벨 이름
		
		JButton[] bt[];
		JButton[] btn = new JButton[3]; //버튼 생성
		String[] btnstr = {"완료", "추가" , "가져오기"};//버튼 이름
		
		public accountPanel() {
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}//유아이 인터넷 참조
			setLayout(null);
			data = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}}; //객체지향 프로그래밍 Coffee Server, Client 실습 참조
			infotable = new JTable(data);//리스트 생성
			scroll = new JScrollPane(infotable);//스크롤 생성
			
			while(data.getRowCount() > 0)
				   data.removeRow(0);//리스트 초기화
			
			try {
				rs = stmt.executeQuery("SELECT * FROM sell_list");
				while (rs.next()){
					data.addRow(new String[]{rs.getString("gname"), rs.getString("scount"), rs.getString("sprice")});
				}//리스트에 추가
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < count; i++){
				label[i] = new JLabel(Labeltext[i]);
				text[i] = new JTextField(20);
				
				label[i].setBounds(110, 270+i*50, 73, 28);
				text[i].setBounds(200, 270+i*50, 200, 28);
				
				add(label[i]);
				add(text[i]);
			}//라벨과 텍스트 필드 배치 밑 추가
			text[1].setText("수입");
			text[2].setText("제품 판매");//텍스트 필드 초기값
			
			text[1].setEditable(false);
			text[2].setEditable(false);//초기값 고정
			
			for(int i = 0; i < btnstr.length; i++) {
				btn[i] = new JButton(btnstr[i]);
				btn[i].addActionListener(this);
				btn[i].setBounds(540, 270+i*50, 93, 28);
				add(btn[i]);
			}//버튼 생성및 추가
			
			scroll.setBounds(0, 0, 780, 250);//스크롤 위치 지정
			
			add(scroll);//스크롤 추가
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btn[0]) { //완료
				if (text[0].getText().equals("") || text[3].getText().equals("")) {
					popup.showMessageDialog(btn[0], "정보를 모두 입력 하세요.");//버튼 접근 조건
				}
				else {
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						
						int count1 = 0, count2 = 0;//오류 확인 변수
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && (text[2].getText()).equals(rs.getString("sep2"))) {
								popup.showMessageDialog(btn[0], "이미 데이터가 있습니다.");
								count2 = -1;
								break;
							}
							else{
								count1 ++;
							}
							count2++;
						}//오류 확인 과정
						if(count1 == count2) {
							String s = "INSERT INTO cashlist (date, sep1, sep2, imp, export) VALUES ('";
							s += text[0].getText() + "', '" + text[1].getText() + "', '" + text[2].getText()
									+ "', '" + Integer.parseInt(text[3].getText()) + "', '" + Integer.parseInt("0") + "');";//sql문 작성
							int i;
							
							i = stmt.executeUpdate(s);
							if (i == 1)
								System.out.println("레코드 추가 성공");
							else
								System.out.println("레코드 추가 실패");
							
							stmt.executeUpdate("DELETE FROM sell_list");//sell_list 초기화
							
							text[0].setText("");
							text[3].setText(""); //텍스트 필드 초기화
							
							while(data.getRowCount() > 0)
								data.removeRow(0);//리스트 초기화
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
            if (e.getSource() == btn[1]) { //추가
            	if (text[0].getText().equals("") || text[3].getText().equals("")) {
            		popup.showMessageDialog(btn[1], "정보를 모두 입력 하세요.");//버튼 접근 조건
				}
				else {
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						
						int count = 0;
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && (text[2].getText()).equals(rs.getString("sep2"))) {
								count = 1;
								break;
							}
						}//오류 확인 과정
						
						if(count == 1) {
							int cal1 = 0;//매출 초기값
			            	try {
								rs = stmt.executeQuery("SELECT imp FROM cashlist where date='" + text[0].getText() + "'");
								while(rs.next()) {
									cal1 = Integer.parseInt(text[3].getText()) + rs.getInt("imp");
									Integer.toString(cal1);
								}//매출값
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
			            	String s = "UPDATE cashlist SET sep1='";
							s += text[1].getText() + "', sep2='" + text[2].getText() + "', imp='" + cal1 + "' where date='" + text[0].getText() + "' and sep2='"
									+ text[2].getText() + "';";//sql문 작성
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("레코드 업데이트 성공");
								else
									System.out.println("레코드 업데이트 실패");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								stmt.executeUpdate("DELETE FROM sell_list");//sell_list 초기화
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							text[0].setText("");
							text[3].setText("");
							
							while(data.getRowCount() > 0)
								data.removeRow(0);// 리스트 초기화
						}
						else {
							popup.showMessageDialog(btn[1], "수정할 데이터가 없습니다.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
            if (e.getSource() == btn[2]) { // 가져오기
				int cal2 = 0;//매출 초기값

				try {
					rs = stmt.executeQuery("SELECT sum(sprice) FROM sell_list"); //sell_list 호출

					for (int i = 0; i < infotable.getRowCount(); i++) {
						while (rs.next()) {
							cal2 = rs.getInt("sum(sprice)");
						}
					}//매출 값
					text[3].setText(Integer.toString(cal2));//매출값 입력
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	class metrialPanel extends JPanel implements ActionListener {
		String[] listname = {"재료명", "단위", "수량", "가격"};
		private DefaultTableModel data; //객체지향 프로그래밍 Coffee Server, Client 실습 참조
		JTable infotable;
		JScrollPane scroll;
		JLabel[] label = new JLabel[5];
		JTextField[] text = new JTextField[5];
		String[] Labeltext = {"날짜", "거래처명", "재료명", "수량", "금액"};
		
		JButton[] bt[];
		JButton[] btn = new JButton[3];
		String[] btnstr = {"주문", "주문 추가", "선택"};
		
		public metrialPanel() {
			try { //인터넷 참조
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			data = new DefaultTableModel(listname,0){ //객체지향 프로그래밍 Coffee Server, Client 실습 참조
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			infotable = new JTable(data);
			scroll = new JScrollPane(infotable);
			
			try {
				rs = stmt.executeQuery("select * from material");
				while (rs.next()){
					data.addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
		    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i = 0; i < 3; i++){
				label[i] = new JLabel(Labeltext[i]);
				text[i] = new JTextField(20);
				
				label[i].setBounds(50, 270+i*50, 73, 28);
				text[i].setBounds(140, 270+i*50, 200, 28);
				
				add(label[i]);
				add(text[i]);
			}
			
			for(int i = 3; i < 5; i++){
				label[i] = new JLabel(Labeltext[i]);
				text[i] = new JTextField(20);
				
				label[i].setBounds(440, 270+(i-3)*50, 73, 28);
				text[i].setBounds(530, 270+(i-3)*50, 200, 28);
				
				add(label[i]);
				add(text[i]);
			}
			
			for(int i = 0; i < btnstr.length; i++) {
				btn[i] = new JButton(btnstr[i]);
				btn[i].setBounds(240+(i*105), 440, 93, 28);
				add(btn[i]);
				
				btn[i].addActionListener(this);
			}
			text[3].addActionListener(this);
			
			text[2].setEditable(false);
			
			scroll.setBounds(0, 0, 780, 250);
				
			add(scroll);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btn[0]) { //주문
				if (text[0].getText().equals("") || text[1].getText().equals("") //버튼 실행 조건
						|| text[2].getText().equals("") || text[3].getText().equals("")
						|| text[4].getText().equals("")) {
					popup.showMessageDialog(btn[0], "정보를 모두 입력 하세요.");
				}
				else {
					
					try {
						rs = stmt.executeQuery("SELECT * FROM gorder");
						
						int count1 = 0, count2 = 0;
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && (text[2].getText()).equals(rs.getString("mname"))) {
								popup.showMessageDialog(btn[0], "이미 데이터가 있습니다.");
								count2 = -1;
								break;
							}
							else {
								count1++;
							}
							count2++;
						}
						if(count1 == count2) {
							//재료 개수 업데이트
							int cal = 0;
							
							rs = stmt.executeQuery("SELECT mcount FROM material where mname='" + text[2].getText() + "'");
							while(rs.next()) {
								cal = Integer.parseInt(text[3].getText()) + rs.getInt("mcount");
								Integer.toString(cal);
							}
							stmt.executeUpdate("UPDATE material SET mcount='" + cal + "' where mname='" + text[2].getText() + "';");
							
							
							//거래내역서에 데이터 추가
							String s = "INSERT INTO gorder (date, aname, mname, mcount, price) VALUES ('";
							s += text[0].getText() + "', '" + text[1].getText() + "', '" + text[2].getText()
									+ "', '" + Integer.parseInt((text[3].getText())) + "', '" + Integer.parseInt(text[4].getText()) + "');";
							int i = 0;
							
							i = stmt.executeUpdate(s);
							if (i == 1)
								System.out.println("데이터 추가 성공");
							else
								System.out.println("데이터 추가 실패");
							
							
							//변경된 재료 데이터를 갱신
							while(data.getRowCount() > 0)
								   data.removeRow(0);
							
							rs = stmt.executeQuery("select * from material");
							while (rs.next()){
								data.addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
					    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			if(e.getSource() == btn[1]) { //주문 추가
				if (text[0].getText().equals("") || text[1].getText().equals("") //버튼 실행 조건
						|| text[2].getText().equals("") || text[3].getText().equals("")
						|| text[4].getText().equals("")) {
					popup.showMessageDialog(btn[0], "정보를 모두 입력 하세요.");
				}
				else {
					try {
						rs = stmt.executeQuery("SELECT * FROM gorder");
						
						int count = 0;
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && 
									(text[2].getText()).equals(rs.getString("mname"))) {
								count = 1;
								break;
							}
						}
						if(count == 1) {
							//재료 개수 업데이트
							int cal1 = 0;
							
							rs = stmt.executeQuery("SELECT mcount FROM material where mname='" + text[2].getText() + "'");
							while(rs.next()) {
								cal1 = Integer.parseInt(text[3].getText()) + rs.getInt("mcount");
								Integer.toString(cal1);
							}
							
							stmt.executeUpdate("UPDATE material SET mcount='" + cal1 + "' where mname='" + text[2].getText() + "';");
							
							
							//거래내역서에 데이터 업데이트
							int cal2 = 0, cal3 = 0;
							
							rs = stmt.executeQuery("SELECT price,mcount FROM gorder where date='" + text[0].getText() + "' and mname='" + text[2].getText() + "'");
							while(rs.next()) {
								cal2 = Integer.parseInt(text[3].getText()) + rs.getInt("mcount");
								cal3 = Integer.parseInt(text[4].getText()) + rs.getInt("price");
								Integer.toString(cal2);
								Integer.toString(cal3);
							}
							
							int i = 0;
							
							i = stmt.executeUpdate("UPDATE gorder SET mcount='" + cal2 + "', price='" + cal3
									+ "' where date='" + text[0].getText() + "';");
							if (i == 1)
								System.out.println("데이터 추가 성공");
							else
								System.out.println("데이터 추가 실패");
							
							
							//변경된 재료 데이터를 갱신
							while(data.getRowCount() > 0)
								   data.removeRow(0);
							
							rs = stmt.executeQuery("select * from material");
							while (rs.next()){
								data.addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
					    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
							}
						}
						else{
							popup.showMessageDialog(btn[1], "수정할 데이터가 없습니다.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource() == btn[2]) { //선택
				if (infotable.getSelectedRow() >= 0){
					text[0].setText("");
					text[3].setText("");
					text[4].setText("");
					
					 int selectRow = infotable.getSelectedRow();
					 String aname, data;
					 
					 data = (String)infotable.getValueAt(selectRow, 0);
					 
					 try {
							rs = stmt.executeQuery("select * from material where mname='" + data + "';");
							rs.next();
							aname = rs.getString("accname");
							text[1].setText(aname);
							text[2].setText(data);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
			
			if(e.getSource() == text[3]){ //계산
				try { //해당 재료의 개수에 대한 가격 계산
					rs = stmt.executeQuery("SELECT mprice FROM material where mname='" + text[2].getText() + "'");
					while(rs.next()) {
						int cal = Integer.parseInt(text[3].getText()) * rs.getInt("mprice");
						text[4].setText((Integer.toString(cal)));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static Connection makeConnection() { //객체지향 프로그램밍 ConnectDatebase 실습 참조
		String url = "jdbc:mysql://localhost:3306/krustykrab";
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
		new Sell_product();
	}
}