import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Window.Type;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnRemove;
	private JTextField textField;
	private JScrollPane scrollPane;
	List <List_Objects> do_List = new ArrayList<>();
	private JTable table;
	private JLabel lblTask;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initGUI();
		startFromSaveFile();
		refresh();
		
		

	}
	private void initGUI() {
		
		
		setTitle("To-Do");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{513, 0};
		gbl_contentPane.rowHeights = new int[]{39, 229, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		lblTask = new JLabel("Task");
		lblTask.setFont(new Font("AppleMyungjo", Font.BOLD | Font.ITALIC, 17));
		lblTask.setForeground(new Color(255, 255, 255));
		panel.add(lblTask);
		
		textField = new JTextField();
		textField.setToolTipText("");
		panel.add(textField);
		textField.setColumns(20);
		
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("AppleMyungjo", Font.BOLD | Font.ITALIC, 19));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnAdd_actionPerformed(e);
			}
		});
		panel.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("AppleMyungjo", Font.PLAIN, 19));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_btnRemove_actionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnRemove);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				do_table_mousePressed(e);
			}
		});
		
		table.setBorder(new CompoundBorder());
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Apple Color Emoji", Font.BOLD, 22));
		table.setRowHeight(30);
		
		scrollPane.setViewportView(table);
		
		
		
	}

	protected void do_btnAdd_actionPerformed(ActionEvent e) {
		
		List_Objects obj = new List_Objects(textField.getText(), false);
		do_List.add(obj);
		refresh();
		

	}
	
	public void startFromSaveFile() {
		try {
			
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream("save.dat"));
			do_List=(ArrayList) reader.readObject();
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	public void refresh() {
		
		
		DefaultTableModel tblModel = new DefaultTableModel(null, new String [] {"Task", "Done"}) {
            public Class getColumnClass(int c) {
              switch (c) {
                case 0: return String.class;
                default: return Boolean.class;
              }   
            } };
            
            table.setModel(tblModel);
            for(int x=0;x<do_List.size();x++) {
    			
   			 tblModel.addRow(new Object [] {do_List.get(x).getContent(),do_List.get(x).isDone()});
   			
   			
   		}
            textField.setText("");
            table.getColumnModel().getColumn(0).setPreferredWidth(450);
  			table.getColumnModel().getColumn(1).setPreferredWidth(20);
  			
  			
  			save();
           
	}
	
	public void save() {
		
		try {
       	 
			ObjectOutputStream writer=new ObjectOutputStream
					   							(new FileOutputStream("save.dat"));
			
			writer.writeObject(do_List);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	protected void do_btnRemove_actionPerformed(ActionEvent e) throws Exception {
		try {
			if(table.getRowCount()>0) {
				int selectedRow = table.getSelectedRow();
				do_List.remove(selectedRow);
				refresh();
				}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Select a Task to Remove");
		}
		
	}


	protected void do_table_mousePressed(MouseEvent e) {
table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
		
				
				do_List.get(e.getFirstRow()).setDone((boolean) table.getModel().getValueAt(e.getFirstRow(), 1));
				do_List.get(e.getFirstRow()).setContent((String) table.getModel().getValueAt(e.getFirstRow(), 0));
				
				save();
			}
		});
	}
	}

