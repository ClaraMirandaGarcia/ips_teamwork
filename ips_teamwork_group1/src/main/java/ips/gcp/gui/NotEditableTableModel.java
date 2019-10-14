package ips.gcp.gui;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NotEditableTableModel extends DefaultTableModel {
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public NotEditableTableModel() {
		// TODO Auto-generated constructor stub
	}

	public NotEditableTableModel(int arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NotEditableTableModel(Vector arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NotEditableTableModel(Object[] arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NotEditableTableModel(Vector arg0, Vector arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NotEditableTableModel(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
