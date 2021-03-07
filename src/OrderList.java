//�ۼ��� : ������

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

public class OrderList extends JFrame implements ActionListener {
	JPanel[] p = new JPanel[2];
	Connection con;
	Statement stmt;
	ResultSet rs;
	JOptionPane popup;
	
	public MainPanel main = new MainPanel();
	public TitlePanel title = new TitlePanel();
	public ListPanel list = new ListPanel();
	public ManagePanel manage = new ManagePanel();
	
	public OrderList() throws SQLException {
		try { //���ͳ� ����
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    System.err.println("Cannot set look and feel:" + e.getMessage ());
		}
		setTitle("�ֹ� ������ ������");
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
		list.setBounds(0, 50, 712, 600);
		manage.setBounds(713, 45, 75, 600);
		
		p[0].add(main);
		p[1].add(title);
		p[1].add(list);
		p[1].add(manage);
		
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
			try { //���ͳ� ����
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(new BorderLayout());
			Image = new ImageIcon("image/Image.png");
			logo = new JButton(Image);
			ext = new JButton("������");
			
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
			try { //���ͳ� ����
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			lb = new JLabel("�ֹ� ������ ��ȸ");
			tf = new JTextField(15);
			bt = new JButton("�˻�");
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
					rs = stmt.executeQuery("SELECT * FROM gorder where date like '%" + tf.getText() + "%';");
					
					while (rs.next()){
						(list.data).addRow(new String[]{rs.getString("date"), rs.getString("aname"),
								rs.getString("mname"), rs.getString("mcount"), rs.getString("price")});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	public class ListPanel extends JPanel {
		String[] header = {"��¥", "�ŷ�ó", "���", "����", "�ݾ�"};
		private DefaultTableModel data; //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
		JScrollPane jsp;
		JTable tb;
		
		public ListPanel() throws SQLException {
			try { //���ͳ� ����
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			data = new DefaultTableModel(header,0){ //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			tb = new JTable(data);
			jsp = new JScrollPane(tb);
			
			con = makeConnection();
			stmt = con.createStatement();
			
			while(data.getRowCount() > 0)
				   data.removeRow(0);
			
		    rs = stmt.executeQuery("SELECT * FROM gorder;");
			while (rs.next()){
				data.addRow(new String[]{rs.getString("date"), rs.getString("aname"),
						rs.getString("mname"), rs.getString("mcount"), rs.getString("price")});
			}
			jsp.setBounds(11, 0, 700, 467);
			
			add(jsp);
		}
	}
	class ManagePanel extends JPanel implements ActionListener {
		JButton[] bt = new JButton[2];
		String[] bst = {"���", "���"};
		
		public ManagePanel() {
			try { //���ͳ� ����
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			for(int i = 0; i < 2; i++) {
				bt[i] = new JButton(bst[i]);
				bt[i].addActionListener(this);
			    add(bt[i]);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == bt[0]) { // ���
				write1 w;
				int cal = 0;
				int k = 0;
				String[][] data = new String[(list.tb).getRowCount()][(list.tb).getColumnCount()];
				
				if((list.tb).getRowCount() == 0)
					popup.showMessageDialog(bt[0], "�����Ͱ� �����ϴ�.");
				
				for (int i = 0; i < (list.tb).getRowCount(); i++)
					for (int j = 0; j < (list.tb).getColumnCount(); j++)
						data[i][j] = (String)(list.tb).getValueAt(i, j);
				
				for (int i = 0; i < (list.tb).getRowCount(); i++) {
					if (data[0][0].equals(data[i][0]) == false) {
						popup.showMessageDialog(bt[0], "��¥�� ��ġ���� �ʽ��ϴ�.");
						break;
					} else {
						k++;
						if ((list.tb).getRowCount() == k) {
							try {
								w = new write1();
								
								(w.expimp.text).setText("����");
								(w.kind.text).setText("��� �ֹ�");
								
								w.day.text.setText(data[0][0]);

								for (int j = 0; j < (list.tb).getRowCount(); j++)
									cal += Integer.parseInt(data[j][4]);
								
								(w.money.text[1]).setText(Integer.toString(cal));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
            if(e.getSource() == bt[1]) { //���
            	(list.tb).clearSelection();
			}
		}
	}
	public static Connection makeConnection() { //��ü���� ���α׷��� ConnectDatebase �ǽ� ����
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
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		OrderList f = new OrderList();
	}
}