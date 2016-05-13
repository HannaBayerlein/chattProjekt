package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

public class UserPane extends JPanel {
	public JList<String> nameList;
	public UserPane(ArrayList<String> users){

		setSize(100,300);
		JTextField textfield = new JTextField("Users Online");
		textfield.setEditable(false);
//		JTextArea textarea= new JTextArea(18,10);
//		textarea.setEditable(false);
		
		DefaultListModel<String> nameListModel = new DefaultListModel<String>();
		for(String s:users){
			nameListModel.addElement(s);
		}
		
		nameList = new JList<String>(nameListModel);
		
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nameList.setPrototypeCellValue("123456789012");
		nameList.addMouseListener(new MouseClickListener());
	//	nameList.addListSelectionListener(new NameSelectionListener());
		JScrollPane userList = new JScrollPane(nameList);
		
//		StringBuilder sb = new StringBuilder();
//		for(String user:users){
//			sb.append(user + "\n");
//		}
//		textarea.setText(sb.toString());
//		

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,textfield,userList);
		
		//Hade detta istället för splitpane, o då hammn de breve varann..
//		textfield.setAlignmentX(TOP_ALIGNMENT);
//		textarea.setAlignmentX(BOTTOM_ALIGNMENT);		
//		add(textfield);
//		add(textarea);
		
		add(splitPane);
	}
	class MouseClickListener implements MouseListener {
		/**
		 * Called when the user selects a name in the name list. Fetches
		 * performance dates from the database and displays them in the date
		 * list.
		 * 
		 * @param e
		 *            The selected list item.
		 */
		public void valueChanged(ListSelectionEvent e) {
			if (nameList.isSelectionEmpty()) {
				return;
			}
			
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() > 1){
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName+" dubbelklick");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName+" 2");
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName+" 3");
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName+" 4");
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
			System.out.println(selectedName+" 5");
		}
	}
}
