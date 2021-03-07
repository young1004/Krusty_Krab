//�ۼ���: ������
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class write1 extends JFrame { // �ۼ�������
	JPanel p[] = new JPanel[1]; // ��� ����
	JLabel l = new JLabel(); // �� ����
	Connection con;
	Statement stmt;
	ResultSet rs;
	JOptionPane popup;

	public dayPanel day = new dayPanel(); // ��¥ ��� ȣ��
	public expimpPanel expimp = new expimpPanel(); // ����/���� ���� ��� ȣ��
	public kindPanel kind = new kindPanel(); // ������� ����
	public moneyPanel money = new moneyPanel(); // ����/���� ���� ��� ����
	public Button0Panel Button0 = new Button0Panel(); // ��ư��� ����

	public write1() throws SQLException, IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Cannot set look and feel:" + e.getMessage());
		} // ������ ���ͳ� ����

		setSize(500, 257); // �����׷� ��ü ������
		setTitle("�ۼ� ������"); // ����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ���α׷� ���ױ�
		setLocationRelativeTo(null); // ����� â ��ġ ����(�߾ӿ� ���� �ϱ����� �ΰ� ��)
		setResizable(false); // ������ ����

		con = makeConnection();
		stmt = con.createStatement();

		for (int i = 0; i < 1; i++)
			p[i] = new JPanel(); // ��� ����

		for (int i = 0; i < 1; i++)
			p[i].setLayout(new GridLayout(1, 1)); // ��� ���̾ƿ� ����

		l = new JLabel(" �ۼ�������");

		l.setBounds(0, 0, 70, 30);
		Button0.setBounds(100, 0, 400, 30);
		day.setBounds(0, 40, 243, 55);
		expimp.setBounds(242, 40, 243, 55);
		kind.setBounds(0, 95, 243, 120);
		money.setBounds(242, 95, 243, 120); // ��� ��ġ ����

		p[0].setLayout(null);
		p[0].add(l);
		p[0].add(Button0);
		p[0].add(day);
		p[0].add(expimp);
		p[0].add(kind);
		p[0].add(money);

		add(p[0]); // �г� ���̱� ���� ����

		setVisible(true); //
	}

	class dayPanel extends JPanel { // ��¥ �г�

		JTextField text = new JTextField(); // �ؽ�Ʈ �ʵ� ����

		public dayPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // ������ ���ͳ� ����
			setLayout(null); // ���̾ƿ� ����

			text = new JTextField(15); // �ؽ�Ʈ �ʵ� ����
			setBorder(new TitledBorder("��¥")); // ��¥ĭ ����
			text.setBounds(38, 20, 168, 25);

			add(text); // �ؽ�Ʈ �ʵ� �߰�
		}
	}

	class expimpPanel extends JPanel { // ����/���� ���� ��� ����

		JTextField text = new JTextField(); // �ؽ�Ʈ �ʵ� ����

		public expimpPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // ������ ���ͳ� ����
			setLayout(null); // ���̾ƿ� ����

			text = new JTextField(15); // �ؽ�Ʈ �ʵ� ����
			setBorder(new TitledBorder("����(����/����)")); // ����ĭ ����
			text.setBounds(38, 20, 168, 25);

			add(text); // �ؽ�Ʈ �ʵ� �߰�
		}
	}

	class kindPanel extends JPanel { // ���� �г� ����

		JTextField text = new JTextField(); // �ؽ�Ʈ �ʵ� ����

		public kindPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // ������ ���ͳ� ����
			setLayout(null); // ���̾ƿ�

			text = new JTextField(15); // �� �ؽ�Ʈ �ʵ� ����

			setBorder(new TitledBorder("�з�"));
			text.setBounds(38, 15, 168, 25);

			add(text); // ���̱�
		}
	}

	class moneyPanel extends JPanel { // ����/���� ���� ��� ����

		JTextField text[] = new JTextField[2];
		JLabel Label[] = new JLabel[2]; // �� �ؽ�Ʈ �ʵ� ����

		public moneyPanel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // ������ ���ͳ� ����
			setLayout(null);
			for (int i = 0; i < text.length; i++)
				text[i] = new JTextField();

			for (int i = 0; i < Label.length; i++)
				Label[i] = new JLabel(); // �ؽ�Ʈ �ʵ� �� ����

			Label[0] = new JLabel("����");
			text[0] = new JTextField(15);
			Label[1] = new JLabel("����");
			text[1] = new JTextField(15); // �ؽ�Ʈ �ʵ� �󺧰� ����
			setBorder(new TitledBorder("�ݾ�"));

			for (int i = 0; i < text.length; i++) {
				text[i].setText("0");
				text[i].setBounds(100, 15 + i * 30, 137, 25);
			}

			for (int i = 0; i < Label.length; i++) {
				Label[i].setBounds(13, 10 + i * 30, 70, 40);
			} // ��ġ����

			add(Label[0]);
			add(text[0]);
			add(Label[1]);
			add(text[1]); // ���̱�
		}
	}

	class Button0Panel extends JPanel implements ActionListener { // ��ư���
		JLabel Logo;
		JLabel Label[] = new JLabel[1];
		JButton button[] = new JButton[3]; // ���� ����

		public Button0Panel() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			} // ������ ���ͳ� ����
			setLayout(null);

			String[] buttonStr = { "���", "��� �Ϸ�", "���� �Ϸ�" }; // ��ư �̸�
			for (int i = 0; i < 3; i++) {
				button[i] = new JButton(buttonStr[i]);
				button[i].setBounds(95 + i * 97, 0, 90, 30);
				add(button[i]);
			} // ��ư ������ ��ġ����
			button[0].addActionListener(this);
			button[1].addActionListener(this);
			button[2].addActionListener(this);
		} // ���Ǹ����� �߰�

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == button[0]) { // ��� ��ư
				dispose();
			}
			if (e.getSource() == button[1]) { // ��� ��ư
				if (day.text.getText().equals("") || expimp.text.getText().equals("") || kind.text.getText().equals("")
						|| money.text[0].getText().equals("") || money.text[1].getText().equals("")) {
					popup.showMessageDialog(button[1], "������ ��� �Է� �ϼ���."); // ��ư �ߵ�����
				} else {

					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						int count1=0,count2=0;
						while (rs.next()) {
							if (((day.text).getText()).equals(rs.getString("date"))
									&& ((kind.text).getText()).equals(rs.getString("sep2"))) {
								popup.showMessageDialog(button[1], "�̹� �����Ͱ� �ֽ��ϴ�.");
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
									+ "', '" + Integer.parseInt((money.text[1]).getText()) + "');"; // �μ�Ʈ��  sql�� �Է�
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("���ڵ� �߰� ����");
								else
									System.out.println("���ڵ� �߰� ����");
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
			if (e.getSource() == button[2]) { // ������ư
				if (day.text.getText().equals("") || expimp.text.getText().equals("") || kind.text.getText().equals("")
						|| money.text[0].getText().equals("") || money.text[1].getText().equals("")) {
					popup.showMessageDialog(button[2], "������ ��� �Է� �ϼ���."); // ��ư ���� ����
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
									+ (day.text).getText() + "' and sep2='" + (kind.text).getText() + "';"; // sql�� ���� ����
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("���ڵ� ������Ʈ ����");
								else
									System.out.println("���ڵ� ������Ʈ ����");
								dispose();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}else{
							popup.showMessageDialog(button[2], "������ �����Ͱ� �����ϴ�.");
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

	private static Connection makeConnection() throws SQLException { // ������  ���̽� ���� �ǽ� ���� ���¼ҽ� ����
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/krustykrab";
		String id = "root";
		String password = "yopmail";
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, id, password);
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
		return con;
	}

	public static void main(String[] args) throws SQLException, IOException {
		write1 f = new write1();
	}
}