package team3.gui;

import team3.utils.Common;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Dialogs extends JDialog implements KeyListener {
    public static JTextArea textAreaForDialogs;
    static JTable table;
    public static DefaultTableModel model;

    public Dialogs(String p_file) {
        setTextAreaForDialogs();

        switch (p_file) {
            case "smiDlg": {
                setSmiDialog();
                final JScrollPane scrollPane = new JScrollPane();
                Object[] columns = {"", "Source", "", " "};
                model = new DefaultTableModel(new Object[][]{
                }, columns) {
                    final boolean[] columnEditable = new boolean[]{false, false, true, true};
                    public boolean isCellEditable(int row, int column) {
                        return columnEditable[column];
                    }
                    // Сортировка
                    final Class[] types_unique = {Integer.class, String.class, Boolean.class, Button.class};
                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return this.types_unique[columnIndex];
                    }
                };
                table = new JTable(model);
                setSmiTable();
                getContentPane().add(table, BorderLayout.CENTER);

                setScrollPane(scrollPane);

                Common.showDialog("smi");
                break;
            }
            case "logDlg": {
                setLogDialog();
                final JScrollPane scrollPane = new JScrollPane();
                setScrollPaneForText(scrollPane);
                Common.showDialog("log");
                break;
            }
            case "exclDlg": {
                setExclDialog();
                final JScrollPane scrollPane = new JScrollPane();
                Object[] columns = {"Num", "Word", "Del"};
                model = new DefaultTableModel(new Object[][]{
                }, columns) {
                    final boolean[] columnEditable = new boolean[]{false, false, true};

                    public boolean isCellEditable(int row, int column) {
                        return columnEditable[column];
                    }

                    // Сортировка
                    final Class[] types_unique = {Integer.class, String.class, Button.class};

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return this.types_unique[columnIndex];
                    }
                };
                table = new JTable(model);
                setExclTable();

                setScrollPane(scrollPane);

                Common.showDialog("excl");
                break;
            }
        }
        // делаем фокус на окно, чтобы работал захват клавиш
        this.requestFocusInWindow();
        this.setVisible(true);
    }

    private void setScrollPaneForText(JScrollPane scrollPane) {
        scrollPane.setBounds(10, 27, 324, 233);
        this.getContentPane().add(scrollPane);
        scrollPane.setViewportView(textAreaForDialogs);
    }

    private void setScrollPane(JScrollPane scrollPane) {
        scrollPane.setBounds(10, 27, 324, 233);
        this.getContentPane().add(scrollPane);
        scrollPane.setViewportView(table);
    }

    private void setSmiTable() {
        table.getColumnModel().getColumn(2).setCellEditor(new CheckBoxEditor(new JCheckBox()));
        table.getColumn(" ").setCellRenderer(new ButtonColumn(table, 3));
        table.setAutoCreateRowSorter(true);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(20);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 13));
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(0).setMaxWidth(24);
        table.getColumnModel().getColumn(2).setMaxWidth(30);
        table.getColumnModel().getColumn(3).setMaxWidth(30);
    }

    private void setExclDialog() {
        this.setResizable(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setBounds(600, 200, 250, 300);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Excluded words");
        this.setLocationRelativeTo(Gui.exclBtn);
    }

    private void setExclTable() {
        table.getColumn("Del").setCellRenderer(new ButtonColumn(table, 2));
        table.setAutoCreateRowSorter(true);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setRowHeight(20);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 13));
        table.getColumnModel().getColumn(0).setCellRenderer(renderer);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        getContentPane().add(table, BorderLayout.CENTER);
    }

    private void setLogDialog() {
        this.setResizable(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setBounds(600, 200, 350, 300);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Log");
        this.setLocationRelativeTo(Gui.logBtn);
    }

    private void setSmiDialog() {
        this.setResizable(false);
        this.setFont(new Font("Tahoma", Font.PLAIN, 14));
        this.setBounds(600, 200, 250, 300);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setTitle("Sources");
        this.setLocationRelativeTo(Gui.smiBtn);
    }

    private void setTextAreaForDialogs() {
        textAreaForDialogs = new JTextArea();
        textAreaForDialogs.setFont(new Font("Dialog", Font.PLAIN, 13));
        textAreaForDialogs.setTabSize(10);
        textAreaForDialogs.setEditable(false);
        textAreaForDialogs.setLineWrap(true);
        textAreaForDialogs.setWrapStyleWord(true);
        textAreaForDialogs.setBounds(12, 27, 22, 233);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Закрываем диалоговые окна клавишей ESC
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}