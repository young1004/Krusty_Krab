//�ۼ���: ���뱸

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Info_check extends JFrame implements ActionListener {

	int PanelCount = 4;// �г��� ������ ���� ����
	JPanel[] p = new JPanel[PanelCount];// �гλ���
	Connection con;// ������ ���� Connection ����
	Statement stmt;// ������ ������ ���� Statement ����
	ResultSet rs;// �������� �ᱫ�� ������ ResultSet ����
	JOptionPane popup;//�˾��� ���� JOptionPane ����
	JButton popupbtn;//�˾��� ���� JButton ����

	public LogoPanel Logop = new LogoPanel();// LogoPanel ������
	public BtnPanel Btnp = new BtnPanel();// BtnPanel ������
	public selectPanel selectp = new selectPanel();// selectPanel ������
	public managePanel managep = new managePanel();// managePanel ������

	public Info_check() throws SQLException {
		con = makeConnection();// makeConnection���� mysql ����
		stmt = con.createStatement();// ������ Statement���� ����
		
		try {// �ý����� �׸��� �������� try-catch�� (���¼ҽ� �ڵ� ����)
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Cannot set look and feel:" + e.getMessage());
		}

		setTitle("���� ��ȸ ������");
		setSize(800, 600);
		setResizable(false);
		setLayout(null);// ���̾ƿ� ����
		setLocationRelativeTo(null);// JFrame �߾� ����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ����â�� �������� ��

		for (int i = 0; i < PanelCount; i++) {// �г��� ������ŭ �г� ����
			p[i] = new JPanel();
			p[i].setLayout(null);// �г��� ���̾ƿ� ����
		}

		// ������ �г��� setBounds�� �̿��Ͽ� ��ġ �� ũ�� ����
		Logop.setBounds(0, 0, 800, 50);
		Btnp.setBounds(0, 0, 700, 50);
		selectp.setBounds(0, 0, 700, 500);
		managep.setBounds(0, 0, 100, 521);

		p[0].setBounds(0, 0, 800, 50);
		p[1].setBounds(0, 50, 700, 50);
		p[2].setBounds(0, 100, 700, 500);
		p[3].setBounds(700, 50, 100, 521);

		// ������ �гλ����ڸ� ���� p�гο� add����
		p[0].add(Logop);
		p[1].add(Btnp);
		p[2].add(selectp);
		p[3].add(managep);

		for (int i = 0; i < PanelCount; i++)// ��� �г��� JFrame�� add
			add(p[i]);

		(Logop.ext).addActionListener(this);// �������ư Listener
		(Btnp.surfbtn).addActionListener(this);// �˻���ư Listener
		(Btnp.surft).addActionListener(this);

		for (int i = 0; i < (managep.btn).length; i++)// ������ ��ư Listener
			(managep.btn[i]).addActionListener(this);// ���� ���, ����, ����, ���
		
		

		while ((selectp.selectp1.listp.model).getRowCount() > 0)
			(selectp.selectp1.listp.model).removeRow(0);

		try {
			rs = stmt.executeQuery("select * from account;");
			while (rs.next()) {
				(selectp.selectp1.listp.model).addRow(new String[] {
						rs.getString("aname"), rs.getString("atype"),
						rs.getString("aceo"), rs.getString("aphone"),
						rs.getString("address") });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		while ((selectp.selectp2.listp.model).getRowCount() > 0)
			(selectp.selectp2.listp.model).removeRow(0);

		try {
			rs = stmt.executeQuery("select * from goods;");
			while (rs.next()) {
				(selectp.selectp2.listp.model).addRow(new String[] {
						rs.getString("gname"), rs.getString("gprice"),
						rs.getString("gkal"), rs.getString("gcookm"),
						rs.getString("mcount") });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		while ((selectp.selectp3.listp.model).getRowCount() > 0)
			(selectp.selectp3.listp.model).removeRow(0);

		try {
			rs = stmt.executeQuery("select * from material;");
			while (rs.next()) {
				(selectp.selectp3.listp.model).addRow(new String[] {
						rs.getString("accname"), rs.getString("mname"),
						rs.getString("mcount"), rs.getString("mprice"),
						rs.getString("munit") });
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == Logop.ext) {// �������ư�� ������
			this.dispose();// ���� â�� �������� ��
		}
		
		if (e.getSource() == Btnp.surft) {// �˻� �ؽ�Ʈ �ʵ�
			String s;// �������� ������ String

			if ((selectp.select).getSelectedIndex() == 0) {// JTabbedPane�� ù��° ��
				String text;

				while ((selectp.selectp1.listp.model).getRowCount() > 0)
					(selectp.selectp1.listp.model).removeRow(0);

				text = (Btnp.surft).getText();// �˻�â�� �Է��� ���� �޾� ����

				// like���� �̿��� �˻����� s�� ����
				s = "select * from account where ";
				s += "aname like '%" + text;
				s += "%' or atype like '%" + text + "%' or ";
				s += "aceo like '%" + text + "%' or aphone like '";
				s += text + "' or address like" + " '%" + text + "%';";

				try {
					rs = stmt.executeQuery(s);
					while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if ((selectp.select).getSelectedIndex() == 1) {
				String text;

				while ((selectp.selectp2.listp.model).getRowCount() > 0)
					(selectp.selectp2.listp.model).removeRow(0);

				text = (Btnp.surft).getText();

				s = "select * from goods where ";
				s += "gname like '%" + text;
				s += "%' or gprice like '" + text + "' or ";
				s += "gkal like '" + text + "' or gcookm like '";
				s += text + "%' or mcount like '" + text + "';";

				try {
					rs = stmt.executeQuery(s);

					while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if ((selectp.select).getSelectedIndex() == 2) {
				String text;

				while ((selectp.selectp3.listp.model).getRowCount() > 0)
					(selectp.selectp3.listp.model).removeRow(0);

				text = (Btnp.surft).getText();

				s = "select * from material where accname ";
				s += "like '%" + text + "%' or mname like '%" + text;
				s += "%' or mcount like '%" + text + "%' or ";
				s += "mprice like '%" + text + "%' or munit like '%";
				s += text + "%';";

				try {
					rs = stmt.executeQuery(s);

					while (rs.next()) {
						(selectp.selectp3.listp.model).addRow(new String[] {
								rs.getString("accname"), rs.getString("mname"),
								rs.getString("mcount"), rs.getString("mprice"),
								rs.getString("munit") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource() == Btnp.surfbtn) {// �˻���ư
			String s;// �������� ������ String

			if ((selectp.select).getSelectedIndex() == 0) {// JTabbedPane�� ù��° ��
				String text;

				while ((selectp.selectp1.listp.model).getRowCount() > 0)
					(selectp.selectp1.listp.model).removeRow(0);

				text = (Btnp.surft).getText();// �˻�â�� �Է��� ���� �޾� ����

				// like���� �̿��� �˻����� s�� ����
				s = "select * from account where ";
				s += "aname like '%" + text;
				s += "%' or atype like '%" + text + "%' or ";
				s += "aceo like '%" + text + "%' or aphone like '";
				s += text + "' or address like" + " '%" + text + "%';";

				try {
					rs = stmt.executeQuery(s);
					while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if ((selectp.select).getSelectedIndex() == 1) {
				String text;

				while ((selectp.selectp2.listp.model).getRowCount() > 0)
					(selectp.selectp2.listp.model).removeRow(0);

				text = (Btnp.surft).getText();

				s = "select * from goods where ";
				s += "gname like '%" + text;
				s += "%' or gprice like '" + text + "' or ";
				s += "gkal like '" + text + "' or gcookm like '";
				s += text + "%' or mcount like '" + text + "';";

				try {
					rs = stmt.executeQuery(s);

					while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if ((selectp.select).getSelectedIndex() == 2) {
				String text;

				while ((selectp.selectp3.listp.model).getRowCount() > 0)
					(selectp.selectp3.listp.model).removeRow(0);

				text = (Btnp.surft).getText();

				s = "select * from material where accname ";
				s += "like '%" + text + "%' or mname like '%" + text;
				s += "%' or mcount like '%" + text + "%' or ";
				s += "mprice like '%" + text + "%' or munit like '%";
				s += text + "%';";

				try {
					rs = stmt.executeQuery(s);

					while (rs.next()) {
						(selectp.selectp3.listp.model).addRow(new String[] {
								rs.getString("accname"), rs.getString("mname"),
								rs.getString("mcount"), rs.getString("mprice"),
								rs.getString("munit") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource() == managep.btn[0]) {//���ù�ư
			if ((selectp.selectp1.listp.infotable).getSelectedRow() >= 0){//Ư�� ���� ���õǸ�
				 int selectRow = (selectp.selectp1.listp.infotable).getSelectedRow();//���õ� �� ���� ����
				 int ColumnCount = (selectp.selectp1.listp.infotable).getColumnCount();//���õ� ���̺��� Į�� ������ ����
				 String data[] = new String[ColumnCount];// Į�� ����ũ���� �迭����
				 
				 (selectp.selectp1.detailp.text[0]).setEditable(false);//������ ���� �⺻Ű�� ���� ����
				 
				 for(int i=0;i < ColumnCount;i++) //�����͸� �ޱ����� for��
		               data[i] = (String)(selectp.selectp1.listp.infotable).getValueAt(selectRow, i);
				 
				 for(int i = 0; i < data.length; i++)//TextField�� ���� �ѷ���
				 (selectp.selectp1.detailp.text[i]).setText(data[i]);
				 
			}
			
			if ((selectp.selectp2.listp.infotable).getSelectedRow() >= 0){
				 int selectRow = (selectp.selectp2.listp.infotable).getSelectedRow();
				 int ColumnCount = (selectp.selectp2.listp.infotable).getColumnCount();
				 String data[] = new String[ColumnCount];
				 
				 (selectp.selectp2.detailp.text[0]).setEditable(false);
				 (selectp.selectp2.detailp.text[3]).setEditable(false);
				 
				 for(int i=0;i < ColumnCount;i++) 
		               data[i] = (String)(selectp.selectp2.listp.infotable).getValueAt(selectRow, i);
				 
				 for(int i = 0; i < data.length; i++)
				 (selectp.selectp2.detailp.text[i]).setText(data[i]);
				 
			}
			
			if ((selectp.selectp3.listp.infotable).getSelectedRow() >= 0){
				 int selectRow = (selectp.selectp3.listp.infotable).getSelectedRow();
				 int ColumnCount = (selectp.selectp3.listp.infotable).getColumnCount();
				 String data[] = new String[ColumnCount];
				 
				 for(int i = 0; i < 3; i++)
					 (selectp.selectp3.detailp.text[i]).setEditable(false); 
				 for(int i=0;i < ColumnCount;i++) 
		               data[i] = (String)(selectp.selectp3.listp.infotable).getValueAt(selectRow, i);
				 
				 for(int i = 0; i < data.length; i++)
				 (selectp.selectp3.detailp.text[i]).setText(data[i]);
				 
			}
		}

		if (e.getSource() == managep.btn[1]) {// ��� ��ư
			String s = "";//String ���� ���� �� �ʱ�ȭ
			boolean check = true;//����ó���� ���� boolean ����
			
			if ((selectp.select).getSelectedIndex() == 0) {// JTabbedPane�� ù��° ��
				while ((selectp.selectp1.listp.model).getRowCount() > 0)
					(selectp.selectp1.listp.model).removeRow(0);

				String text1[] = new String[(selectp.selectp1.listp.listname).length];

				for (int i = 0; i < text1.length; i++) {
					text1[i] = (selectp.selectp1.detailp.text[i]).getText();

					if (text1[i].equals(""))//�ؽ�Ʈ�� �Էµ��� ���� ���� ������
						check = false;//check�� false�� �ٲ�

				}
				
				try {
					s = "select *from account where aname='";
					s += text1[0] + "';";
					rs = stmt.executeQuery(s);
					
					if(rs.next())//�⺻Ű �ߺ� �˻�
						check = false;
					
					s = "INSERT INTO account ";
					s += "(aname, atype, aceo, aphone, address) VALUES ";
					s += "('" + text1[0] + "', '" + text1[1] + "', '" + text1[2];
					s += "', '" + text1[3] + "', '" + text1[4] + "');";

					if (check == true)//������ ������
						stmt.executeUpdate(s);

					if (check == false) {//���� ó��
						check = true;// boolean ���� �ʱ�ȭ
						popup.showMessageDialog(popupbtn, "�ùٸ� �����͸� �Է��� �ֽʽÿ�.");// �˾����
					}
					rs = stmt.executeQuery("select * from account;");

					while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < (selectp.selectp1.listp.listname).length; i++)//�ؽ�Ʈ�ʵ� �����
					(selectp.selectp1.detailp.text[i]).setText("");
			}

			if ((selectp.select).getSelectedIndex() == 1) {
				while ((selectp.selectp2.listp.model).getRowCount() > 0)
					(selectp.selectp2.listp.model).removeRow(0);

				String text2[] = new String[(selectp.selectp2.listp.listname).length];
				int gprice, gkal, mcount;

				for (int i = 0; i < text2.length; i++) {
					text2[i] = (selectp.selectp2.detailp.text[i]).getText();

					if (text2[i].equals(""))
						check = false;

				}

				try {
					s = "select * from goods where gname ='";
					s += text2[0] + "' and gcookm ='" + text2[3] + "';";
					
					rs = stmt.executeQuery(s);
					
					if(rs.next())
						check = false;
					
					s = "select * from material where mname='";
					s += text2[3] + "';";
					
					rs = stmt.executeQuery(s);
					
					if(rs.next());
					else
						check = false;
					
					if (check == true) {
						gprice = Integer.parseInt(text2[1]);
						gkal = Integer.parseInt(text2[2]);
						mcount = Integer.parseInt(text2[4]);

						s = "INSERT INTO goods ";
						s += "(gname, gprice, gkal, gcookm, mcount) VALUES ";
						s += "('" + text2[0] + "'," + gprice;
						s += ", " + gkal + ", '" + text2[3] + "', " + mcount + ");";
						
						stmt.executeUpdate(s);
					}
					
					if (check == false) {
						check = true;// boolean ���� �ʱ�ȭ
						popup.showMessageDialog(popupbtn, "�ùٸ� �����͸� �Է��� �ֽʽÿ�!");// �˾����
					}

					rs = stmt.executeQuery("select * from goods");

					while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < (selectp.selectp2.listp.listname).length; i++)
					(selectp.selectp2.detailp.text[i]).setText("");
			}
			
			if ((selectp.select).getSelectedIndex() == 2){
				while((selectp.selectp3.listp.model).getRowCount() > 0)
					(selectp.selectp3.listp.model).removeRow(0);

				String text3[] = new String[(selectp.selectp3.listp.listname).length];
				int mcount, mprice;

				for (int i = 0; i < text3.length; i++) {
					text3[i] = (selectp.selectp3.detailp.text[i]).getText();

					if (text3[i].equals(""))
						check = false;

				}

				try {
					s = "select * from material where mname ='";
					s += text3[1] + "';";
					
					rs = stmt.executeQuery(s);
					
					s = "select * from account where aname='";
					s += text3[0] + "';";
					
					rs = stmt.executeQuery(s);
					
					if(rs.next());
					else
						check = false;
					
					if(rs.next())
						check = false;
					
					if (check == true) {
						mcount = Integer.parseInt(text3[2]);
						mprice = Integer.parseInt(text3[3]);

						s = "INSERT INTO material ";
						s += "(accname, mname, mcount, mprice, munit) VALUES ";
						s += "('" + text3[0] + "', '" + text3[1] + "', " + mcount;
						s += ", " + mprice + ", '" + text3[4] + "');";
						
						stmt.executeUpdate(s);
					}
					
					if (check == false) {
						check = true;// boolean ���� �ʱ�ȭ
						popup.showMessageDialog(popupbtn, "�ùٸ� �����͸� �Է��� �ֽʽÿ�!");// �˾����
					}

					rs = stmt.executeQuery("select * from material;");

					while (rs.next()) {
						(selectp.selectp3.listp.model).addRow(new String[] {
								rs.getString("accname"), rs.getString("mname"),
								rs.getString("mcount"), rs.getString("mprice"),
								rs.getString("munit") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < (selectp.selectp3.listp.listname).length; i++)
					(selectp.selectp3.detailp.text[i]).setText("");
			}

		}

		if (e.getSource() == managep.btn[2]) {//������ư
			String s;
			if ((selectp.select).getSelectedIndex() == 0){
				while((selectp.selectp1.listp.model).getRowCount() > 0)
					(selectp.selectp1.listp.model).removeRow(0);
				
				try {
					String text1[] = new String[(selectp.selectp1.listp.listname).length];

					for (int i = 0; i < text1.length; i++)
						text1[i] = (selectp.selectp1.detailp.text[i]).getText();

					s = "UPDATE account SET atype ='";
					s += text1[1] + "', aceo ='" + text1[2];
					s += "', aphone ='" + text1[3] + "', address ='";
					s += text1[4] + "' where aname ='" + text1[0] + "';";

					stmt.executeUpdate(s);
					rs = stmt.executeQuery("select * from account;");

					while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for(int i = 0; i < (selectp.selectp1.listp.listname).length; i++)
					(selectp.selectp1.detailp.text[i]).setText("");
			}
			
			if ((selectp.select).getSelectedIndex() == 1){
				while((selectp.selectp2.listp.model).getRowCount() > 0)
					(selectp.selectp2.listp.model).removeRow(0);
				
				try {
					String text2[] = new String[(selectp.selectp2.listp.listname).length];
					int gprice, gkal, mcount; 
					 
					 for(int i = 0; i < text2.length; i++)
						text2[i] = (selectp.selectp2.detailp.text[i]).getText();
					
					gprice = Integer.parseInt(text2[1]);
					gkal = Integer.parseInt(text2[2]);
					mcount = Integer.parseInt(text2[4]);
					
					s = "UPDATE goods SET gprice =";
					s += gprice + ", gkal =" + gkal;
					s += ", mcount =" + mcount + " where gname='";
					s += text2[0] + "' and gcookm='" + text2[3] + "';";
					
					stmt.executeUpdate(s);//������Ʈ ��
					
					s = "UPDATE goods SET gprice =";
					s += gprice + ", gkal =" + gkal + " where gname ='";
					s += text2[0] + "';";
					
					stmt.executeUpdate(s);//���� �׸��̹Ƿ� �ϰ� ������Ʈ
					rs = stmt.executeQuery("select * from goods;");

					while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for(int i = 0; i < (selectp.selectp2.listp.listname).length; i++)
					(selectp.selectp2.detailp.text[i]).setText("");
			}
			
			if ((selectp.select).getSelectedIndex() == 2){
				while((selectp.selectp3.listp.model).getRowCount() > 0)
					(selectp.selectp3.listp.model).removeRow(0);
				
				try {
					String text3[] = new String[(selectp.selectp3.listp.listname).length];
					int mcount, mprice; 
					 
					for(int i = 0; i < text3.length; i++)
						text3[i] = (selectp.selectp3.detailp.text[i]).getText();
					
					mcount = Integer.parseInt(text3[2]);
					mprice = Integer.parseInt(text3[3]);
					
					s = "UPDATE material SET mprice=";
					s += mprice + ", munit='" + text3[4] + "'";
					s += " where mname='" + text3[1] + "';";
					
					stmt.executeUpdate(s);
					rs = stmt.executeQuery("select * from material");

					while (rs.next()) {
						(selectp.selectp3.listp.model).addRow(new String[] {
								rs.getString("accname"), rs.getString("mname"),
								rs.getString("mcount"), rs.getString("mprice"),
								rs.getString("munit") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < (selectp.selectp3.listp.listname).length; i++)
					(selectp.selectp3.detailp.text[i]).setText("");
			}
			
		}

		if (e.getSource() == managep.btn[3]) {//������ư
			String s;
			String text;
			boolean check = true;
			if ((selectp.select).getSelectedIndex() == 0){
				while((selectp.selectp1.listp.model).getRowCount() > 0)
					(selectp.selectp1.listp.model).removeRow(0);
				
				text = (selectp.selectp1.detailp.text[0]).getText();
				
				try {
					s = "select *from material where accname='";
					s += text + "';";
					
					rs = stmt.executeQuery(s);
					
					if(rs.next())//����������
						check = false;
					
					if(check == true){//���̾�����
					s = "DELETE FROM account where aname=";
					s += "'" + text + "';";
					
					stmt.executeUpdate(s);
					}
					
					if(check == false){
						check = true;
						popup.showMessageDialog(popupbtn, "�̹� �����ǰ� �ִ� ���Դϴ�!");
					}
					
					rs = stmt.executeQuery("select * from account;");

					while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				for(int i = 0; i < (selectp.selectp1.listp.listname).length; i++)
						(selectp.selectp1.detailp.text[i]).setText("");

			}

			if ((selectp.select).getSelectedIndex() == 1){
				while((selectp.selectp2.listp.model).getRowCount() > 0)
					(selectp.selectp2.listp.model).removeRow(0);
				
				try {
					s = "DELETE FROM goods where gname=";
					s += "'" + (selectp.selectp2.detailp.text[0]).getText() + "'";
					s += " and gcookm =" + "'" + (selectp.selectp2.detailp.text[3]).getText();
					s += "';";
					
					stmt.executeUpdate(s);
					rs = stmt.executeQuery("select * from goods;");

					while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
					for(int i = 0; i < (selectp.selectp2.listp.listname).length; i++)
						(selectp.selectp2.detailp.text[i]).setText("");


				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			if ((selectp.select).getSelectedIndex() == 2){
				while((selectp.selectp3.listp.model).getRowCount() > 0)
					(selectp.selectp3.listp.model).removeRow(0);
				
				text = (selectp.selectp3.detailp.text[1]).getText();
				
				try {
					s = "select *from goods where gcookm='";
					s += text + "';";
					
					rs = stmt.executeQuery(s);
					
					if(rs.next())
						check = false;
					
					if(check == true){
					s = "DELETE FROM material where mname=";
					s += "'" + text + "'" + ";";
					stmt.executeUpdate(s);
					}
					
					if(check == false){
						check = true;
						popup.showMessageDialog(popupbtn, "�̹� �����ǰ� �ִ� ���Դϴ�!");
					}
					
					rs = stmt.executeQuery("select * from material");

					try {
						rs = stmt.executeQuery("select * from material;");
						while (rs.next()) {
							(selectp.selectp3.listp.model).addRow(new String[] {
									rs.getString("accname"),
									rs.getString("mname"),
									rs.getString("mcount"),
									rs.getString("mprice"),
									rs.getString("munit") });
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					for (int i = 0; i < (selectp.selectp3.listp.listname).length; i++)
						(selectp.selectp3.detailp.text[i]).setText("");

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		if (e.getSource() == managep.btn[4]) {//��� ��ư
			if ((selectp.select).getSelectedIndex() == 0){
				 for(int i = 0; i < (selectp.selectp1.listp.listname).length; i++)
					(selectp.selectp1.detailp.text[i]).setText("");
				 
				 (selectp.selectp1.detailp.text[0]).setEditable(true);
				 (Btnp.surft).setText("");
				 
				 while((selectp.selectp1.listp.model).getRowCount() > 0)
						(selectp.selectp1.listp.model).removeRow(0);

					try {
						rs = stmt.executeQuery("select * from account;");
						while (rs.next()) {
						(selectp.selectp1.listp.model).addRow(new String[] {
								rs.getString("aname"), rs.getString("atype"),
								rs.getString("aceo"), rs.getString("aphone"),
								rs.getString("address") });
					}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 
				 if ((selectp.selectp1.listp.infotable).getSelectedRow() >= 0)
					 (selectp.selectp1.listp.infotable).clearSelection();
				 
			}
			
			if ((selectp.select).getSelectedIndex() == 1){ 
				 for(int i = 0; i < (selectp.selectp2.listp.listname).length; i++)
					(selectp.selectp2.detailp.text[i]).setText("");
				 
				 (Btnp.surft).setText("");
				 (selectp.selectp2.detailp.text[0]).setEditable(true);
				 (selectp.selectp2.detailp.text[3]).setEditable(true);
				 
				 while((selectp.selectp2.listp.model).getRowCount() > 0)
						(selectp.selectp2.listp.model).removeRow(0);

					try {
						rs = stmt.executeQuery("select * from goods;");
						while (rs.next()) {
						(selectp.selectp2.listp.model).addRow(new String[] {
								rs.getString("gname"), rs.getString("gprice"),
								rs.getString("gkal"), rs.getString("gcookm"),
								rs.getString("mcount") });
					}
				} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 
				 if ((selectp.selectp2.listp.infotable).getSelectedRow() >= 0)
					 (selectp.selectp2.listp.infotable).clearSelection();
			}
			
			if ((selectp.select).getSelectedIndex() == 2){
				 for(int i = 0; i < (selectp.selectp3.listp.listname).length; i++)
					(selectp.selectp3.detailp.text[i]).setText(""); 
				 
				 (Btnp.surft).setText("");
				 
				 for(int i = 0; i < 3; i++)
					 (selectp.selectp3.detailp.text[i]).setEditable(true);
				 
				 while((selectp.selectp3.listp.model).getRowCount() > 0)
						(selectp.selectp3.listp.model).removeRow(0);
				 
				 try {
						rs = stmt.executeQuery("select * from material;");
					while (rs.next()) {
						(selectp.selectp3.listp.model).addRow(new String[] {
								rs.getString("accname"), rs.getString("mname"),
								rs.getString("mcount"), rs.getString("mprice"),
								rs.getString("munit") });
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				 
				 if ((selectp.selectp3.listp.infotable).getSelectedRow() >= 0)
					 (selectp.selectp3.listp.infotable).clearSelection();
			}
		}
	}

	class LogoPanel extends JPanel implements ActionListener {
		JButton Logo,ext;
		ImageIcon Image;

		public LogoPanel() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			Image = new ImageIcon("image/Image.png");
			Logo = new JButton(Image);
			ext = new JButton("������");

			Logo.setBounds(5, 0, 100, 28);
			ext.setBounds(720, 0, 73, 28);
			
			Logo.addActionListener(this);

			add(Logo);
			add(ext);
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

	class BtnPanel extends JPanel {
		JLabel title;
		JTextField surft;
		JButton surfbtn;

		public BtnPanel() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			title = new JLabel("���� ��ȸ");
			surft = new JTextField(50);
			surfbtn = new JButton("�˻�");

			title.setBounds(5, 0, 73, 28);
			surft.setBounds(310, 0, 300, 28);
			surfbtn.setBounds(627, 0, 73, 28);

			add(title);
			add(surft);
			add(surfbtn);
		}
	}

	class listPanel1 extends JPanel {
		String[] listname = {"�ŷ�ó��", "����", "��ǥ��", "��ȭ��ȣ", "�ּ�" };
		DefaultTableModel model;
		JTable infotable;
		JScrollPane scroll;

		public listPanel1() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}

			model = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			infotable = new JTable(model);
			scroll = new JScrollPane(infotable);

			for (int i = 0; i < listname.length; i++)// JTable�� ��缿��
				infotable.getColumnModel().getColumn(i).setPreferredWidth(100);// ����ũ�⸦ 100�� �����.
			
			infotable.getColumnModel().getColumn(4).setPreferredWidth(220);
			infotable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���̺� ũ�⸦ �ڵ����� ������ �ʴ´�.
			scroll.setPreferredSize(new Dimension(400, 443));
			scroll.setBounds(0, 0, 400, 443);
			add(scroll);
		}
	}

	class listPanel2 extends JPanel {
		String[] listname = {"��ǰ��", "����", "Į�θ�", "���", "��� ����" };
		DefaultTableModel model;
		JTable infotable;
		JScrollPane scroll;

		public listPanel2() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}

			model = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			infotable = new JTable(model);
			scroll = new JScrollPane(infotable);

			for (int i = 0; i < listname.length; i++)// JTable�� ��缿��
				infotable.getColumnModel().getColumn(i).setPreferredWidth(100);// ����ũ�⸦ 100�� �����.
			
			infotable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���̺� ũ�⸦ �ڵ����� ������ �ʴ´�.
			scroll.setPreferredSize(new Dimension(400, 443));
			scroll.setBounds(0, 0, 400, 443);
			add(scroll);
		}
	}

	class listPanel3 extends JPanel {
		String[] listname = {"�ŷ�ó��", "����", "����", "����", "����" };
		DefaultTableModel model;
		JTable infotable;
		JScrollPane scroll;

		public listPanel3() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}

			model = new DefaultTableModel(listname,0){
				public boolean isCellEditable(int i, int c){
					return false;
				}};
			infotable = new JTable(model);
			scroll = new JScrollPane(infotable);

			for (int i = 0; i < listname.length; i++)// JTable�� ��缿��
				infotable.getColumnModel().getColumn(i).setPreferredWidth(100);// ����ũ�⸦ 100�� �����.
			
			infotable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���̺� ũ�⸦ �ڵ����� ������ �ʴ´�.
			scroll.setPreferredSize(new Dimension(400, 443));
			scroll.setBounds(0, 0, 400, 443);
			add(scroll);
		}
	}

	class detailPanel1 extends JPanel {
		String[] labeltext = {"�ŷ�ó��", "����", "��ǥ��", "��ȭ��ȣ", "�ּ�"};
		JLabel[] label = new JLabel[labeltext.length];
		JTextField[] text = new JTextField[labeltext.length];
		
		public detailPanel1() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			for (int i = 0; i < labeltext.length; i++) {
				label[i] = new JLabel(labeltext[i]);
				text[i] = new JTextField(30);
				label[i].setBounds(30, 60 + i * 50, 73, 28);
				text[i].setBounds(110, 60 + i * 50, 173, 28);

				add(label[i]);
				add(text[i]);
			}

		}
	}

	class detailPanel2 extends JPanel {
		String[] labeltext = {"��ǰ��", "����", "Į�θ�", "���" , "��� ����"};
		JLabel[] label = new JLabel[labeltext.length];
		JTextField[] text = new JTextField[labeltext.length];

		public detailPanel2() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			for (int i = 0; i < labeltext.length; i++) {
				label[i] = new JLabel(labeltext[i]);
				text[i] = new JTextField(30);
				label[i].setBounds(30, 60 + i * 50, 73, 28);
				text[i].setBounds(110, 60 + i * 50, 173, 28);

				add(label[i]);
				add(text[i]);
			}

		}
	}

	class detailPanel3 extends JPanel {
		String[] labeltext = {"�ŷ�ó��", "����", "����", "����", "����" };
		JLabel[] label = new JLabel[labeltext.length];
		JTextField[] text = new JTextField[labeltext.length];

		public detailPanel3() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			for (int i = 0; i < labeltext.length; i++) {
				label[i] = new JLabel(labeltext[i]);
				text[i] = new JTextField(30);
				label[i].setBounds(30, 60 + i * 50, 73, 28);
				text[i].setBounds(110, 60 + i * 50, 173, 28);

				add(label[i]);
				add(text[i]);
			}

		}
	}

	class managePanel extends JPanel {
		String[] btnname = { "����","���", "����", "����", "���" };
		JButton btn[] = new JButton[btnname.length];

		public managePanel() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			for (int i = 0; i < btn.length; i++) {
				btn[i] = new JButton(btnname[i]);
				btn[i].setBounds(11, 72 + 35 * i, 73, 28);
				add(btn[i]);
			}

		}
	}

	class select1 extends JPanel {
		public listPanel1 listp = new listPanel1();
		public detailPanel1 detailp = new detailPanel1();

		public select1() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			listp.setBounds(0, 0, 400, 443);
			detailp.setBounds(400, 0, 300, 456);

			add(listp);
			add(detailp);
		}
	}

	class select2 extends JPanel {
		public listPanel2 listp = new listPanel2();
		public detailPanel2 detailp = new detailPanel2();

		public select2() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			listp.setBounds(0, 0, 400, 443);
			detailp.setBounds(400, 0, 300, 456);

			add(listp);
			add(detailp);
		}
	}

	class select3 extends JPanel {
		public listPanel3 listp = new listPanel3();
		public detailPanel3 detailp = new detailPanel3();

		public select3() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			listp.setBounds(0, 0, 400, 443);
			detailp.setBounds(400, 0, 300, 456);

			add(listp);
			add(detailp);
		}
	}

	class selectPanel extends JPanel {
		public select1 selectp1 = new select1();
		public select2 selectp2 = new select2();
		public select3 selectp3 = new select3();
		JTabbedPane select;

		public selectPanel() {
			setLayout(null);
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Cannot set look and feel:" + e.getMessage());
			}
			
			select = new JTabbedPane();
			select.addTab("�ŷ�ó ����", selectp1);
			select.addTab("��ǰ ����", selectp2);
			select.addTab("��� ����", selectp3);
			select.setBounds(0, 0, 700, 500);

			add(select);
		}

	}
	
	public static Connection makeConnection() { //�ǽ� ���� ���¼ҽ� �ڵ� ����
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
		new Info_check();
	}
}