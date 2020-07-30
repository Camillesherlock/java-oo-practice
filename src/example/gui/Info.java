package example.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import example.sql.Lists;
import struct.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private List listL;
	private MainWindow mainWindow = MainWindow.getMainWindow();
	private Lists lists = Lists.getLists();

	/**
	 * Create the dialog.
	 */
	public Info(List list) {
		if (list == null) {
			list = new List("标题", "详情", false, 0);
		}
		this.listL = list;

		setTitle("热搜详情");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lbl_title = new JLabel(list.getTitle());
			contentPanel.add(lbl_title, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				JTextArea textArea = new JTextArea(list.getDescribe());
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					JButton btn_vote = new JButton("投票");
					btn_vote.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							lists.tempAdd(listL, 1);
							mainWindow.loadList();
						}
					});
					buttonPane.add(btn_vote);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
