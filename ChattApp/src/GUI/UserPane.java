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

import server.TCPServer2;
import server.User;

public class UserPane extends JPanel {
	DefaultListModel<String> nameListModel;
	public JList<String> nameList;
	public UserPane(){
		
		//ArrayList<User> users = TCPServer2.activeUsers; 
		
		setSize(100,300);
		JTextField textfield = new JTextField("Users Online");
		textfield.setEditable(false);

		nameListModel = new DefaultListModel<String>();
		
		
		nameList = new JList<String>(nameListModel);
		
		nameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//nameList.setPrototypeCellValue("123456789012");
		nameList.addMouseListener(new MouseClickListener());

		JScrollPane userList = new JScrollPane(nameList);


		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,textfield,userList);
		
		add(splitPane);
	}
	public void setUserList(ArrayList<User> users, User user){
		for(User s:users){
			String name=s.getNick();
			if(name.equals(user.getNick())){
				name=name+" (you)";
			}
			nameListModel.addElement(name);
		}
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
//			System.out.println(selectedName);
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
//			System.out.println(selectedName+" 2");
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
//			System.out.println(selectedName+" 3");
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			 String selectedName = nameList.getSelectedValue();
//			System.out.println(selectedName+" 4");
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String selectedName = nameList.getSelectedValue();
//			System.out.println(selectedName+" 5");
		}
	}
}
