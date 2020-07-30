package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import struct.List;

public class MyList extends JPanel implements ListCellRenderer<Object> {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		List cellList = (List) value;

		setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lbl_index = new JLabel("New label");
		panel.add(lbl_index, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lbl_title = new JLabel("New label");
		panel_1.add(lbl_title, BorderLayout.NORTH);

		JTextArea textArea = new JTextArea();
		panel_1.add(textArea, BorderLayout.CENTER);

		//
		lbl_index.setText("热度：" + cellList.getAchievement());
		lbl_title.setText(cellList.getTitle());
		textArea.setText(cellList.getDescribe());

		return panel_2;
	}

}
