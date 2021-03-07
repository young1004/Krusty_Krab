//작성자: 김태주
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class write1 extends JFrame { // 작성페이지
	JPanel p[] = new JPanel[1]; // 페널 생성
	JLabel l = new JLabel(); // 라벨 생성
	Connection con;
	Statement stmt;
	ResultSet rs;
	JOptionPane popup;

	public dayPanel day = new dayPanel(); // 날짜 페널 호출
	public expimpPanel expimp = new expimpPanel(); // 수입/지출 선택 페널 호출
	public kindPanel kind = new kindPanel(); // 계정페널 생성
	public moneyPanel money = new moneyPanel(); // 수입/지출 금전 페널 생성
	public Button0Panel Button0 = new Button0Panel(); // 버튼페널 생성

	public write1() throws SQLException, IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Cannot set look and feel:" + e.getMessage());
		} // 유아이 인터넷 참조

		setSize(500, 257); // 프러그랭 전체 사이즈
		setTitle("작성 페이지"); // 제목
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 프로그렘 끝네기
		setLocationRelativeTo(null); // 실행된 창 위치 설정(중앙에 싫행 하기위해 널값 줌)
		setResizable(false); // 사이즈 고정

		con = makeConnection();
		stmt = con.createStatement();

		for (int i = 0; i < 1; i++)
			p[i] = new JPanel(); // 페널 생성

		for (int i = 0; i < 1; i++)
			p[i].setLayout(new GridLayout(1, 1)); // 페널 레이아웃 지정

		l = new JLabel(" 작성페이지");

		l.setBounds(0, 0, 70, 30);
		Button0.setBounds(100, 0, 400, 30);
		day.setBounds(0, 40, 243, 55);
		expimp.setBounds(242, 40, 243, 55);
		kind.setBounds(0, 95, 243, 120);
		money.setBounds(242, 95, 243, 120); // 페널 위치 지정

		p[0].setLayout(null);
		p[0].add(l);
		p[0].add(Button0);
		p[0].add(day);
		p[0].add(expimp);
		p[0].add(kind);
		p[0].add(money);

		add(p[0]); // 패널 붙이기 위한 과정

		setVisible(true); //
	}

	class dayPanel extends JPanel { // 날짜 패널

		JTextField text = new JTextField(); // 텍스트 필드 선언

		public dayPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // 유아이 인터넷 참조
			setLayout(null); // 레이아웃 설정

			text = new JTextField(15); // 텍스트 필드 생성
			setBorder(new TitledBorder("날짜")); // 날짜칸 생성
			text.setBounds(38, 20, 168, 25);

			add(text); // 텍스트 필드 추가
		}
	}

	class expimpPanel extends JPanel { // 수입/지출 선택 페널 생성

		JTextField text = new JTextField(); // 텍스트 필드 생성

		public expimpPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // 유아이 인터넷 참조
			setLayout(null); // 레이아웃 설정

			text = new JTextField(15); // 텍스트 필드 생성
			setBorder(new TitledBorder("구분(지출/수입)")); // 구분칸 생성
			text.setBounds(38, 20, 168, 25);

			add(text); // 텍스트 필드 추가
		}
	}

	class kindPanel extends JPanel { // 계정 패널 생성

		JTextField text = new JTextField(); // 텍스트 필드 선언

		public kindPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // 유아이 인터넷 참조
			setLayout(null); // 레이아웃

			text = new JTextField(15); // 라벨 텍스트 필드 생성

			setBorder(new TitledBorder("분류"));
			text.setBounds(38, 15, 168, 25);

			add(text); // 붙이기
		}
	}

	class moneyPanel extends JPanel { // 수입/지출 금전 페널 생성

		JTextField text[] = new JTextField[2];
		JLabel Label[] = new JLabel[2]; // 라벨 텍스트 필드 선언

		public moneyPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // 유아이 인터넷 참조
			setLayout(null);
			for (int i = 0; i < text.length; i++)
				text[i] = new JTextField();

			for (int i = 0; i < Label.length; i++)
				Label[i] = new JLabel(); // 텍스트 필드 라벨 생성

			Label[0] = new JLabel("수입");
			text[0] = new JTextField(15);
			Label[1] = new JLabel("지출");
			text[1] = new JTextField(15); // 텍스트 필드 라벨값 지정
			setBorder(new TitledBorder("금액"));

			for (int i = 0; i < text.length; i++) {
				text[i].setText("0");
				text[i].setBounds(100, 15 + i * 30, 137, 25);
			}

			for (int i = 0; i < Label.length; i++) {
				Label[i].setBounds(13, 10 + i * 30, 70, 40);
			} // 위치지정

			add(Label[0]);
			add(text[0]);
			add(Label[1]);
			add(text[1]); // 붙이기
		}
	}

	class Button0Panel extends JPanel implements ActionListener { // 버튼페널
		JLabel Logo;
		JLabel Label[] = new JLabel[1];
		JButton button[] = new JButton[3]; // 변수 선언

		public Button0Panel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // 유아이 인터넷 참조
			setLayout(null);

			String[] buttonStr = { "취소", "등록 완료", "수정 완료" }; // 버튼 이름
			for (int i = 0; i < 3; i++) {
				button[i] = new JButton(buttonStr[i]);
				button[i].setBounds(95 + i * 97, 0, 90, 30);
				add(button[i]);
			} // 버튼 생성밑 위치지정
			button[0].addActionListener(this);
			button[1].addActionListener(this);
			button[2].addActionListener(this);
		} // 엑션리슨어 추가

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == button[0]) { // 취소 버튼
				dispose();
			}
			if (e.getSource() == button[1]) { // 등록 버튼
				if (day.text.getText().equals("") || expimp.text.getText().equals("") || kind.text.getText().equals("")
						|| money.text[0].getText().equals("") || money.text[1].getText().equals("")) {
					popup.showMessageDialog(button[1], "정보를 모두 입력 하세요."); // 버튼 발동조건
				} else {

					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						int count1=0,count2=0;
						while (rs.next()) {
							if (((day.text).getText()).equals(rs.getString("date"))
									&& ((kind.text).getText()).equals(rs.getString("sep2"))) {
								popup.showMessageDialog(button[1], "이미 데이터가 있습니다.");
								count2=-1;
								break;
							} else{
								count1++;
							}
							count2++;
							

						}
						if(count1==count2) {
							String s = "INSERT INTO cashlist (date, sep1, sep2, imp, export) VALUES ('";
							s += (day.text).getText() + "', '" + (expimp.text).getText() + "', '"
									+ (kind.text).getText() + "', '" + Integer.parseInt(((money.text[0]).getText()))
									+ "', '" + Integer.parseInt((money.text[1]).getText()) + "');"; // 인설트문  sql에 입력
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("레코드 추가 성공");
								else
									System.out.println("레코드 추가 실패");
								dispose();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

				}
			}
			if (e.getSource() == button[2]) { // 수정버튼
				if (day.text.getText().equals("") || expimp.text.getText().equals("") || kind.text.getText().equals("")
						|| money.text[0].getText().equals("") || money.text[1].getText().equals("")) {
					popup.showMessageDialog(button[2], "정보를 모두 입력 하세요."); // 버튼 실행 조건
				} else {
					int count=0;
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						while (rs.next()) {
							if (((day.text).getText()).equals(rs.getString("date"))
									&& ((kind.text).getText()).equals(rs.getString("sep2"))) {
								count =1;
								break;
							}
						}
						if(count==1){
							String s = "UPDATE cashlist SET sep1='";
							s += (expimp.text).getText() + "', sep2='" + (kind.text).getText() + "', imp='"
									+ Integer.parseInt((money.text[0]).getText()) + "', export='"
									+ Integer.parseInt((money.text[1]).getText()) + "' where date='"
									+ (day.text).getText() + "' and sep2='" + (kind.text).getText() + "';"; // sql문 실행 조건
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("레코드 업데이트 성공");
								else
									System.out.println("레코드 업데이트 실패");
								dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}else{
							popup.showMessageDialog(button[2], "수정할 데이터가 없습니다.");
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	private static Connection makeConnection() throws SQLException { // 데이터  베이스 연동 실습 내용 오픈소스 참조
		// TODO Auto-generated method stub
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

	public static void main(String[] args) throws SQLException, IOException {
		write1 f = new write1();
	}
}