package example.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import View.MyList;
import example.sql.Lists;
import struct.List;
import struct.User;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JPanel contentPane;
	private JList<List> list_superHS, list_HS;
	private Lists lists = Lists.getLists();

	private ArrayList<List> superHS_lists;
	private ArrayList<List> HS_lists;

	private static MainWindow mainWindow = null;

	public static MainWindow getMainWindow(User user) {
		mainWindow = new MainWindow(user);
		return mainWindow;
	}

	public static MainWindow getMainWindow() {
		if (mainWindow == null) {
			throw new NullPointerException();
		}
		return mainWindow;
	}

	/**
	 * Create the frame.
	 */
	private MainWindow(User user) {
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(12, 1, 1, 1));

		JButton btn_addHS = new JButton("添加热搜");
		panel.add(btn_addHS);

		JButton btn_addSuperHS = new JButton("添加超级热搜");
		panel.add(btn_addSuperHS);
		if (!user.isAdmin()) {
			btn_addSuperHS.setVisible(false);
		}

		JButton btn_buyHS = new JButton("购买热搜");
		btn_buyHS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddHS frame = new AddHS(false);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(btn_buyHS);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 5, 1));

		list_superHS = new JList();
		panel_1.add(list_superHS);
		list_superHS.setBorder(BorderFactory.createTitledBorder("超级热搜"));
		list_superHS.setCellRenderer(new MyList());
		list_superHS.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			boolean keyPressed = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list_superHS.getSelectedIndex();
				try {
					Info dialog = new Info(superHS_lists.get(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		list_HS = new JList();
		panel_1.add(list_HS);
		list_HS.setBorder(BorderFactory.createTitledBorder("热搜"));
		list_HS.setCellRenderer(new MyList());
		list_HS.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			boolean keyPressed = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				int index = list_HS.getSelectedIndex();
				try {
					Info dialog = new Info(HS_lists.get(index));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		setLocationRelativeTo(null);

		btn_addHS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddHS frame = new AddHS(false);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		btn_addSuperHS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddHS frame = new AddHS(true);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});

		loadList();
	}

	/**
	 * 加载列表
	 */
	public void loadList() {
		ArrayList<List> lists = this.lists.query();
		superHS_lists = new ArrayList<List>();
		HS_lists = new ArrayList<List>();

		for (List tem : lists) {
			if (tem.isSuper()) {
				superHS_lists.add(tem);
			} else {
				HS_lists.add(tem);
			}
		}

		HS_lists = sortList(HS_lists);
		superHS_lists = sortList(superHS_lists);

		ListModel<List> listModel = new MyModel(HS_lists);
		list_HS.setModel(listModel);

		ListModel<List> listModel_S = new MyModel(superHS_lists);
		list_superHS.setModel(listModel_S);

	}

	class MyModel extends DefaultListModel {
		private static final long serialVersionUID = 1L;
		ArrayList<List> lists = new ArrayList<List>();

		public MyModel(ArrayList<List> lists) {
			this.lists = lists;
		}

		@Override
		public int getSize() {
			return lists.size();
		}

		@Override
		public List getElementAt(int index) {
			return lists.get(index);
		}

	}

	/**
	 * 榜单排序
	 * 
	 * @return
	 */
	private ArrayList<List> sortList(ArrayList<List> list) {
		ArrayList<List> re = new ArrayList<List>();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			List max = list.get(0);
			for (List tem : list) {
				if (max.getAchievement() < tem.getAchievement()) {
					max = tem;
				}
			}
			re.add(max);
			list.remove(max);
		}
		return re;
	}

}
