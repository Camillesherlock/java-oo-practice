package example.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.sql.Users;
import struct.User;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JPasswordField passwordField_password;

	private Users users = Users.getUser();

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		textField_name = new JTextField();
		textField_name.setBounds(129, 51, 237, 21);
		contentPane.add(textField_name);
		textField_name.addKeyListener(new KeyListener() {

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
		textField_name.setColumns(10);

		passwordField_password = new JPasswordField();
		passwordField_password.setBounds(129, 82, 237, 21);
		contentPane.add(passwordField_password);

		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(62, 56, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(62, 85, 54, 15);
		contentPane.add(lblNewLabel_1);

		JButton btn_cancel = new JButton("取消");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_cancel.setBounds(62, 142, 93, 23);
		contentPane.add(btn_cancel);

		JButton btn_login = new JButton("登录");
		btn_login.setBounds(270, 142, 93, 23);
		contentPane.add(btn_login);

		JLabel text_register = new JLabel("注册账号");
		text_register.setForeground(Color.BLUE);
		text_register.setBounds(270, 175, 54, 15);
		contentPane.add(text_register);

		// 登录
		btn_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String password = passwordField_password.getText();

				if (textField_name.getText().equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "账号或密码不能为空", "提示ʾ", JOptionPane.YES_OPTION);
					return;
				}
				long account = 0;
				try {
					account = Long.valueOf(textField_name.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "账号输入错误", "提示ʾ", JOptionPane.YES_OPTION);
				}
				User user = (User) users.query(account);
				System.out.println(user.getPassword()+" "+password);
				if (user != null && user.getPassword().equals(password)) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainWindow frame = MainWindow.getMainWindow(user);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});

					dispose();
				} else {

					JOptionPane.showMessageDialog(null, "账号或密码错误", "提示ʾ", JOptionPane.YES_OPTION);
					passwordField_password.setText("");
				}

			}
		});

		// 取消
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 注册
		text_register.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mousePressed(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Register frame = new Register();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根

			}
		});
	}
}
