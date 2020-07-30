package example.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.sql.Users;
import struct.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField textField_account;
	private JTextField textField_password;
	private JTextField textField_name;
	private Users users = Users.getUser();

	/**
	 * Create the frame.
	 */
	public Register() {
		setResizable(false);
		setTitle("注册");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 347, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 10, 54, 15);
		contentPane.add(lblNewLabel);

		textField_account = new JTextField();
		textField_account.setBounds(74, 7, 206, 21);
		contentPane.add(textField_account);
		textField_account.setColumns(10);
		textField_account.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isDigit(e.getKeyChar())) {
					return;
				} else {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根

			}
		});

		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(10, 41, 54, 15);
		contentPane.add(lblNewLabel_1);

		textField_password = new JTextField();
		textField_password.setBounds(74, 38, 206, 21);
		contentPane.add(textField_password);
		textField_password.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("用户名：");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(10, 72, 54, 15);
		contentPane.add(lblNewLabel_2);

		textField_name = new JTextField();
		textField_name.setBounds(74, 69, 206, 21);
		contentPane.add(textField_name);
		textField_name.setColumns(10);

		JCheckBox ckbx_isAdmin = new JCheckBox("是否管理员", false);
		ckbx_isAdmin.setBounds(74, 106, 103, 23);
		contentPane.add(ckbx_isAdmin);

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(42, 147, 93, 23);
		contentPane.add(btn_cancel);

		JButton btn_ok = new JButton("确定");
		btn_ok.setBounds(187, 147, 93, 23);
		contentPane.add(btn_ok);

		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = textField_password.getText();
				String name = textField_name.getText();
				boolean isAdmin = ckbx_isAdmin.isSelected();

				if (textField_account.getText().equals("") || password.equals("") || name.equals("")) {
					JOptionPane.showMessageDialog(null, "不能为空", "提示ʾ", JOptionPane.YES_OPTION);
					return;
				}
				long account = 0;
				try {
					account = Long.valueOf(textField_account.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "账号输入错误", "提示ʾ", JOptionPane.YES_OPTION);
				}

				User user = new User(account, name, password, isAdmin);
				if (users.insert(user)) {
					JOptionPane.showMessageDialog(null, "注册成功", "提示ʾ", JOptionPane.YES_OPTION);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "注册失败，可能是账号已存在", "提示ʾ", JOptionPane.YES_OPTION);
				}
			}
		});

		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
	}
}
