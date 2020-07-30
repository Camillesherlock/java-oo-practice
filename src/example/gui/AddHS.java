package example.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.sql.Lists;

import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddHS extends JFrame {

	private JPanel contentPane;
	private JTextField textField_title;
	private Lists lists = Lists.getLists();

	/**
	 * Create the frame.
	 */
	public AddHS(boolean isSuper) {
		setResizable(false);
		setTitle("添加热搜");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("标题");
		lblNewLabel.setBounds(10, 10, 54, 15);
		contentPane.add(lblNewLabel);

		textField_title = new JTextField();
		textField_title.setBounds(10, 35, 554, 21);
		contentPane.add(textField_title);
		textField_title.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("描述");
		lblNewLabel_1.setBounds(10, 66, 54, 15);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 554, 162);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);

		JButton btn_ok = new JButton("确定");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = textField_title.getText();
				String describe = textArea.getText();
				if (title.equals("") || describe.equals("")) {
					JOptionPane.showMessageDialog(null, "标题或内容不能为空", "提示ʾ", JOptionPane.YES_OPTION);
					return;
				}
				if (title.length() > 15) {
					JOptionPane.showMessageDialog(null, "添加失败，标题过长", "提示ʾ", JOptionPane.YES_OPTION);
					return;
				}
				if (describe.length() > 300) {
					JOptionPane.showMessageDialog(null, "添加失败，内容过长", "提示ʾ", JOptionPane.YES_OPTION);
					return;
				}
				if (lists.insert(new struct.List(title, describe, isSuper, 0))) {
					MainWindow.getMainWindow().loadList();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "添加失败，可能是相同标题热搜已存在", "提示ʾ", JOptionPane.YES_OPTION);
				}

			}
		});
		btn_ok.setBounds(471, 281, 93, 23);
		contentPane.add(btn_ok);

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(368, 281, 93, 23);
		contentPane.add(btn_cancel);

		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
