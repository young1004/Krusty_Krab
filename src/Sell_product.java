//�ۼ��� : ���뱸(�Ǹ� ��), ������(���� ���� ���� ��), ������(����ֹ� ��)

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
			try { //���ͳ� ����
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}
			catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			setTitle("�Ǹ�/����/�ֹ�");
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
			select.addTab("�Ǹ�", p[1]);
			select.addTab("�Ϻ� ���� ����", account);
			select.addTab("��� �ֹ�", metrial);
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
			
			btn = new JButton("������");
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
		String[] listname = {"��ǰ","����", "����"};
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
		String[] Labeltext = {"��ǰ��", "����", "��ǰ ����", "���� �ݾ�", "�Ž�����"};
		
		JButton[] btn = new JButton[2];
		String[] btnstr = {"�߰�", "���"};	
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
			if(e.getSource() == btn[0]){//�߰���ư
				int count = Integer.parseInt(text[1].getText());//����
				int price = count * Integer.parseInt(text[2].getText());//����
				String p = Integer.toString(price);//addRow ���� ����ȯ
				
				(listp.data).addRow(new String[] {
						text[0].getText(),text[1].getText(),p});
				
				price = 0;//�ʱ�ȭ
				for(int i = 0; i < (listp.infotable).getRowCount(); i++){
					p = (String)(listp.infotable).getValueAt(i, 2);//���̺��� ���� ������
					price += Integer.parseInt(p);//���� ��� ����
				}
				p = Integer.toString(price);
				(sellp.text).setText(p);//�ѱݾ� textField
				
				for(int i = 0; i <3; i++)
					text[i].setText("");
			}
			if(e.getSource() == btn[1]){//���
				for(int i = 0; i <3; i++)
					text[i].setText("");
			}
			if(e.getSource() == text[3]){//���� �ݾ�
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
		String[] btnstr = {"�ֹ� �Ϸ�", "�ֹ� ���"};
		Connection con;
		Statement stmt, stmt1, stmt2;
		ResultSet rs, rs1;
		JButton popupbtn = new JButton("Ȯ��");
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
			lb = new JTextField("�� �ݾ�");
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
			if(e.getSource() == btn[0]){//�ֹ��Ϸ�
				String s;
				int counter;//������ ���õ� �κ��� ������ ����
				boolean check = true;//����ó���� ���� boolean ����
				
				for (int i = 0; i < (listp.infotable).getRowCount(); i++) {//���̺��� ��� �� Ž��
					
					if((infop.text[3].getText()).equals("") || (infop.text[4].getText()).equals("")){
						popup.showMessageDialog(popupbtn, "���� �ݾװ� �Ž������� ����� �ֽʽÿ�.");
						break;//���� ó��1
					}

					try {
						for (int j = 0; j < (listp.infotable).getRowCount(); j++) {//��� ���� Ȯ���� ����
							s = "select gname, gcookm, mcount from goods";
							s += " where gname ='" + (listp.infotable).getValueAt(i, 0) + "';";
							rs = stmt.executeQuery(s);//��ǰ ���̺� �˻��� ����

								while (rs.next()) {// �˻��� �� Ž��
									String material = rs.getString("gcookm");// ����
									String mcount = rs.getString("mcount");// ����
									int gcount = Integer.parseInt((String) (listp.infotable).getValueAt(i, 1));// ��ǰ����
									int count = gcount * Integer.parseInt(mcount);// �� �ʿ��� ��ǰ����
									
									s = "select * from material where mname ='";
									s += material + "';";// ��� ���̺� �� ������ �ۼ�

									rs1 = stmt1.executeQuery(s);// �Ǵٸ� resultset�� �� ����
									rs1.next();
									counter = rs1.getInt("mcount");// ��� ��� ���� ������

									if (counter < count)// ����
										check = false;// ��� ��� ������ �� �ٲ�

								}
							}
						
						s = "select gname from sell_list where gname='";
						s += (listp.infotable).getValueAt(i, 0) + "';";//������ �ۼ�
						
						rs = stmt.executeQuery(s);//����� ����
						
						if (rs.next()) {//������� �ִٸ�

							if (check == true) {//��ᰡ �������� ������
								s = "select gname, gcookm, mcount from goods";
								s += " where gname ='" + (listp.infotable).getValueAt(i, 0) + "';";
								rs = stmt.executeQuery(s);//����� ����

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
						while (rs.next()){//�Ϻ� ���� ���� �� ���ΰ�ħ
							(account.data).addRow(new String[]{rs.getString("gname"), rs.getString("scount"), rs.getString("sprice")});
						}
						while((metrial.data).getRowCount() > 0)
							(metrial.data).removeRow(0);
						
						rs = stmt.executeQuery("select * from material");
						while (rs.next()){//��� �ֹ� â ���ΰ�ħ
							(metrial.data).addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
				    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(check == false){
						check = true;
						popup.showMessageDialog(popupbtn, "��ᰡ �����մϴ�!");
						break;
					}
				}
				while((listp.data).getRowCount() > 0)
					(listp.data).removeRow(0);
				
				(sellp.text).setText("");
				for(int i = 3; i < 5; i++)
					infop.text[i].setText("");
			}
			
			if(e.getSource() == btn[1]){//�ֹ����
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
		String q = "select DISTINCT gname from goods;";//������
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
			
			for(int i = 0; i < gnum; i++){//��ǰ�̸����� ��ư����
				rs.next();
				btnstr[i] = rs.getString("gname");
				btn[i] = new JButton(btnstr[i]);
				add(btn[i]);	
				btn[i].addActionListener(this);
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//������ ��ǰ�� ���� ��ư
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
		int count = 4;// �ؽ�Ʈ �󺧸� �����ϱ� ���� ����
		
		String[] listname = {"��ǰ", "����", "����"};// ����Ʈ ��Ű��
		private DefaultTableModel data; //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
		JTable infotable; //����Ʈ ����
		JScrollPane scroll;// ��ũ�� ����
		JLabel[] label = new JLabel[count];//�� ����
		JTextField[] text = new JTextField[count];//�ؽ�Ʈ ���̺� ����
		String[] Labeltext = {"��¥", "����", "�з�", "����"}; //�� �̸�
		
		JButton[] bt[];
		JButton[] btn = new JButton[3]; //��ư ����
		String[] btnstr = {"�Ϸ�", "�߰�" , "��������"};//��ư �̸�
		
		public accountPanel() {
			try {
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}//������ ���ͳ� ����
			setLayout(null);
			data = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}}; //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
			infotable = new JTable(data);//����Ʈ ����
			scroll = new JScrollPane(infotable);//��ũ�� ����
			
			while(data.getRowCount() > 0)
				   data.removeRow(0);//����Ʈ �ʱ�ȭ
			
			try {
				rs = stmt.executeQuery("SELECT * FROM sell_list");
				while (rs.next()){
					data.addRow(new String[]{rs.getString("gname"), rs.getString("scount"), rs.getString("sprice")});
				}//����Ʈ�� �߰�
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
			}//�󺧰� �ؽ�Ʈ �ʵ� ��ġ �� �߰�
			text[1].setText("����");
			text[2].setText("��ǰ �Ǹ�");//�ؽ�Ʈ �ʵ� �ʱⰪ
			
			text[1].setEditable(false);
			text[2].setEditable(false);//�ʱⰪ ����
			
			for(int i = 0; i < btnstr.length; i++) {
				btn[i] = new JButton(btnstr[i]);
				btn[i].addActionListener(this);
				btn[i].setBounds(540, 270+i*50, 93, 28);
				add(btn[i]);
			}//��ư ������ �߰�
			
			scroll.setBounds(0, 0, 780, 250);//��ũ�� ��ġ ����
			
			add(scroll);//��ũ�� �߰�
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btn[0]) { //�Ϸ�
				if (text[0].getText().equals("") || text[3].getText().equals("")) {
					popup.showMessageDialog(btn[0], "������ ��� �Է� �ϼ���.");//��ư ���� ����
				}
				else {
					try {
						rs = stmt.executeQuery("SELECT * FROM cashlist");
						
						int count1 = 0, count2 = 0;//���� Ȯ�� ����
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && (text[2].getText()).equals(rs.getString("sep2"))) {
								popup.showMessageDialog(btn[0], "�̹� �����Ͱ� �ֽ��ϴ�.");
								count2 = -1;
								break;
							}
							else{
								count1 ++;
							}
							count2++;
						}//���� Ȯ�� ����
						if(count1 == count2) {
							String s = "INSERT INTO cashlist (date, sep1, sep2, imp, export) VALUES ('";
							s += text[0].getText() + "', '" + text[1].getText() + "', '" + text[2].getText()
									+ "', '" + Integer.parseInt(text[3].getText()) + "', '" + Integer.parseInt("0") + "');";//sql�� �ۼ�
							int i;
							
							i = stmt.executeUpdate(s);
							if (i == 1)
								System.out.println("���ڵ� �߰� ����");
							else
								System.out.println("���ڵ� �߰� ����");
							
							stmt.executeUpdate("DELETE FROM sell_list");//sell_list �ʱ�ȭ
							
							text[0].setText("");
							text[3].setText(""); //�ؽ�Ʈ �ʵ� �ʱ�ȭ
							
							while(data.getRowCount() > 0)
								data.removeRow(0);//����Ʈ �ʱ�ȭ
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
            if (e.getSource() == btn[1]) { //�߰�
            	if (text[0].getText().equals("") || text[3].getText().equals("")) {
            		popup.showMessageDialog(btn[1], "������ ��� �Է� �ϼ���.");//��ư ���� ����
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
						}//���� Ȯ�� ����
						
						if(count == 1) {
							int cal1 = 0;//���� �ʱⰪ
			            	try {
								rs = stmt.executeQuery("SELECT imp FROM cashlist where date='" + text[0].getText() + "'");
								while(rs.next()) {
									cal1 = Integer.parseInt(text[3].getText()) + rs.getInt("imp");
									Integer.toString(cal1);
								}//���Ⱚ
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
			            	String s = "UPDATE cashlist SET sep1='";
							s += text[1].getText() + "', sep2='" + text[2].getText() + "', imp='" + cal1 + "' where date='" + text[0].getText() + "' and sep2='"
									+ text[2].getText() + "';";//sql�� �ۼ�
							int i;
							try {
								i = stmt.executeUpdate(s);
								if (i == 1)
									System.out.println("���ڵ� ������Ʈ ����");
								else
									System.out.println("���ڵ� ������Ʈ ����");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								stmt.executeUpdate("DELETE FROM sell_list");//sell_list �ʱ�ȭ
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							text[0].setText("");
							text[3].setText("");
							
							while(data.getRowCount() > 0)
								data.removeRow(0);// ����Ʈ �ʱ�ȭ
						}
						else {
							popup.showMessageDialog(btn[1], "������ �����Ͱ� �����ϴ�.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
            if (e.getSource() == btn[2]) { // ��������
				int cal2 = 0;//���� �ʱⰪ

				try {
					rs = stmt.executeQuery("SELECT sum(sprice) FROM sell_list"); //sell_list ȣ��

					for (int i = 0; i < infotable.getRowCount(); i++) {
						while (rs.next()) {
							cal2 = rs.getInt("sum(sprice)");
						}
					}//���� ��
					text[3].setText(Integer.toString(cal2));//���Ⱚ �Է�
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
	class metrialPanel extends JPanel implements ActionListener {
		String[] listname = {"����", "����", "����", "����"};
		private DefaultTableModel data; //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
		JTable infotable;
		JScrollPane scroll;
		JLabel[] label = new JLabel[5];
		JTextField[] text = new JTextField[5];
		String[] Labeltext = {"��¥", "�ŷ�ó��", "����", "����", "�ݾ�"};
		
		JButton[] bt[];
		JButton[] btn = new JButton[3];
		String[] btnstr = {"�ֹ�", "�ֹ� �߰�", "����"};
		
		public metrialPanel() {
			try { //���ͳ� ����
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    System.err.println("Cannot set look and feel:" + e.getMessage ());
			}
			setLayout(null);
			data = new DefaultTableModel(listname,0){ //��ü���� ���α׷��� Coffee Server, Client �ǽ� ����
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
			if(e.getSource() == btn[0]) { //�ֹ�
				if (text[0].getText().equals("") || text[1].getText().equals("") //��ư ���� ����
						|| text[2].getText().equals("") || text[3].getText().equals("")
						|| text[4].getText().equals("")) {
					popup.showMessageDialog(btn[0], "������ ��� �Է� �ϼ���.");
				}
				else {
					
					try {
						rs = stmt.executeQuery("SELECT * FROM gorder");
						
						int count1 = 0, count2 = 0;
						
						while(rs.next()) {
							if((text[0].getText()).equals(rs.getString("date")) && (text[2].getText()).equals(rs.getString("mname"))) {
								popup.showMessageDialog(btn[0], "�̹� �����Ͱ� �ֽ��ϴ�.");
								count2 = -1;
								break;
							}
							else {
								count1++;
							}
							count2++;
						}
						if(count1 == count2) {
							//��� ���� ������Ʈ
							int cal = 0;
							
							rs = stmt.executeQuery("SELECT mcount FROM material where mname='" + text[2].getText() + "'");
							while(rs.next()) {
								cal = Integer.parseInt(text[3].getText()) + rs.getInt("mcount");
								Integer.toString(cal);
							}
							stmt.executeUpdate("UPDATE material SET mcount='" + cal + "' where mname='" + text[2].getText() + "';");
							
							
							//�ŷ��������� ������ �߰�
							String s = "INSERT INTO gorder (date, aname, mname, mcount, price) VALUES ('";
							s += text[0].getText() + "', '" + text[1].getText() + "', '" + text[2].getText()
									+ "', '" + Integer.parseInt((text[3].getText())) + "', '" + Integer.parseInt(text[4].getText()) + "');";
							int i = 0;
							
							i = stmt.executeUpdate(s);
							if (i == 1)
								System.out.println("������ �߰� ����");
							else
								System.out.println("������ �߰� ����");
							
							
							//����� ��� �����͸� ����
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
			
			if(e.getSource() == btn[1]) { //�ֹ� �߰�
				if (text[0].getText().equals("") || text[1].getText().equals("") //��ư ���� ����
						|| text[2].getText().equals("") || text[3].getText().equals("")
						|| text[4].getText().equals("")) {
					popup.showMessageDialog(btn[0], "������ ��� �Է� �ϼ���.");
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
							//��� ���� ������Ʈ
							int cal1 = 0;
							
							rs = stmt.executeQuery("SELECT mcount FROM material where mname='" + text[2].getText() + "'");
							while(rs.next()) {
								cal1 = Integer.parseInt(text[3].getText()) + rs.getInt("mcount");
								Integer.toString(cal1);
							}
							
							stmt.executeUpdate("UPDATE material SET mcount='" + cal1 + "' where mname='" + text[2].getText() + "';");
							
							
							//�ŷ��������� ������ ������Ʈ
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
								System.out.println("������ �߰� ����");
							else
								System.out.println("������ �߰� ����");
							
							
							//����� ��� �����͸� ����
							while(data.getRowCount() > 0)
								   data.removeRow(0);
							
							rs = stmt.executeQuery("select * from material");
							while (rs.next()){
								data.addRow(new String[]{rs.getString("mname"), Integer.toString(rs.getInt("munit")),
					    				Integer.toString(rs.getInt("mcount")), Integer.toString(rs.getInt("mprice"))});
							}
						}
						else{
							popup.showMessageDialog(btn[1], "������ �����Ͱ� �����ϴ�.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			if(e.getSource() == btn[2]) { //����
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
			
			if(e.getSource() == text[3]){ //���
				try { //�ش� ����� ������ ���� ���� ���
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

	public static Connection makeConnection() { //��ü���� ���α׷��� ConnectDatebase �ǽ� ����
		String url = "jdbc:mysql://localhost:3306/krustykrab";
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
		new Sell_product();
	}
}