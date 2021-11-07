import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JProgressBar;

public class App {

    private JFrame frmzakdolgozat;
    private JTextField txtrtkek;
    private JTextField textField;
    private JTextField textField_1;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel linearequation;
    private JPanel hatizsak;
    private JSpinner spinner;
    private JSpinner spinner_1;
    private JScrollPane scrollPane2;
    private int iteration = 0;

    boolean[] col_checked;
    boolean[] row_checked;
    double[][] matrix;
    int[] sortX;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frmzakdolgozat.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public App() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private JPanel[] panels = new JPanel[3];

    private JButton btnLinerisEgyenletrendszer;
    private JButton btnNewButton_2;
    private JTable table;
    private JButton btnNewButton_3;
    private JPanel szallitasi;
    private JTextField textField_2;
    private JLabel ertekek_1;
    private JLabel lblSlyok_1;
    private JTextField textField_3;
    private JButton btnNewButton_5;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JTable table_1;

    private void initialize() {
        frmzakdolgozat = new JFrame();
        frmzakdolgozat.setTitle("SzakdolgozatProgram");
        frmzakdolgozat.setBounds(100, 100, 1481, 699);
        frmzakdolgozat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmzakdolgozat.getContentPane().setLayout(null);

        szallitasi = new JPanel();
        szallitasi.setLayout(null);
        szallitasi.setBounds(350, 11, 1067, 638);
        frmzakdolgozat.getContentPane().add(szallitasi);

        textField_2 = new JTextField();
        textField_2.setToolTipText("\u00C9rt\u00E9kek");
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_2.setColumns(10);
        textField_2.setBounds(152, 11, 402, 36);
        szallitasi.add(textField_2);

        ertekek_1 = new JLabel("Fogyaszt\u00F3k sz\u00E1ma");
        ertekek_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ertekek_1.setBounds(10, 22, 130, 20);
        szallitasi.add(ertekek_1);

        lblSlyok_1 = new JLabel("Termel\u0151k sz\u00E1ma");
        lblSlyok_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSlyok_1.setBounds(10, 69, 118, 20);
        szallitasi.add(lblSlyok_1);

        textField_3 = new JTextField();
        textField_3.setToolTipText("\u00C9rt\u00E9kek");
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_3.setColumns(10);
        textField_3.setBounds(152, 58, 402, 36);
        szallitasi.add(textField_3);

        btnNewButton_5 = new JButton("GO");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fogyasztokSzama = Integer.parseInt(textField_2.getText());
                int termelokSzama = Integer.parseInt(textField_3.getText());

                table_1.setModel(new DefaultTableModel(
                        generateTablazat(fogyasztokSzama, termelokSzama),
                        generateFogyasztok(fogyasztokSzama)
                ) {
                    boolean[] columnEditables = generateEditable(fogyasztokSzama);

                    public boolean isCellEditable(int row, int column) {
                        return columnEditables[column];
                    }
                });
            }
        });
        btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_5.setBounds(10, 104, 544, 36);
        szallitasi.add(btnNewButton_5);

        scrollPane_1 = new JScrollPane((Component) null);
        scrollPane_1.setBounds(10, 150, 1047, 204);
        szallitasi.add(scrollPane_1);

        table_1 = new JTable();
        table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        table_1.setCellSelectionEnabled(true);

        scrollPane_1.setViewportView(table_1);

        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(10, 410, 1047, 218);
        szallitasi.add(scrollPane_2);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        scrollPane_2.setViewportView(textArea);

        JButton btnNewButton_5_1 = new JButton("GO2");
        btnNewButton_5_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fogyasztokSzama = Integer.parseInt(textField_2.getText());
                int termelokSzama = Integer.parseInt(textField_3.getText());

                double[][] model = getSzallitasiMatrix(table_1, fogyasztokSzama, termelokSzama);

                String result = SzallitasiFeladat.go(model);

                textArea.setText(result);
            }
        });
        btnNewButton_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_5_1.setBounds(10, 364, 544, 36);
        szallitasi.add(btnNewButton_5_1);

        linearequation = new JPanel();
        linearequation.setBounds(350, 11, 1067, 638);
        frmzakdolgozat.getContentPane().add(linearequation);

        JButton btnNewButton = new JButton("H\u00E1tizs\u00E1k feladat");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hidePanels();

                hatizsak.setVisible(true);
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton.setBounds(10, 42, 256, 46);
        frmzakdolgozat.getContentPane().add(btnNewButton);

        hatizsak = new JPanel();
        hatizsak.setBounds(350, 11, 1067, 638);
        frmzakdolgozat.getContentPane().add(hatizsak);
        hatizsak.setLayout(null);

        txtrtkek = new JTextField();
        txtrtkek.setFont(new Font("Tahoma", Font.PLAIN, 16));
        //////////////////////////////////////////////////////////////////////////////
        txtrtkek.setText("41, 29, 10, 20, 5, 11");
        txtrtkek.setToolTipText("\u00C9rt\u00E9kek");
        txtrtkek.setBounds(152, 11, 402, 36);
        hatizsak.add(txtrtkek);
        txtrtkek.setColumns(10);

        JLabel ertekek = new JLabel("\u00C9rt\u00E9kek");
        ertekek.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ertekek.setBounds(10, 22, 71, 14);
        hatizsak.add(ertekek);

        JLabel lblSlyok = new JLabel("S\u00FAlyok");
        lblSlyok.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSlyok.setBounds(10, 69, 71, 14);
        hatizsak.add(lblSlyok);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        /////////////////////////////////////////////////////////////////////////
        textField.setText("31, 36, 32, 13, 2, 10");
        textField.setToolTipText("\u00C9rt\u00E9kek");
        textField.setColumns(10);
        textField.setBounds(152, 58, 402, 36);
        hatizsak.add(textField);

        JButton btnNewButton_1 = new JButton("GO");
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final double[] ertekek = separateBy(txtrtkek.getText(), ",");
                final double[] sulyok = separateBy(textField.getText(), ",");
                final double meret = Double.parseDouble(textField_1.getText());

                final double[][] output = Hatizsak.go(ertekek, sulyok, meret);
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < output.length; i++) {
                    for (int j = 0; j < output[i].length; j++) {
                        sb.append(output[i][j] + "\t");
                    }
                    sb.append("\n");
                }

                sb.append("Optimum: " + Hatizsak.searchmax(output));

                textPane.setText(sb.toString());
            }
        });
        btnNewButton_1.setBounds(10, 155, 544, 36);
        hatizsak.add(btnNewButton_1);

        JLabel lblHtizskMrete = new JLabel("H\u00E1tizs\u00E1k m\u00E9rete");
        lblHtizskMrete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHtizskMrete.setBounds(10, 116, 132, 14);
        hatizsak.add(lblHtizskMrete);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField_1.setText("50");
        textField_1.setToolTipText("\u00C9rt\u00E9kek");
        textField_1.setColumns(10);
        textField_1.setBounds(152, 105, 402, 36);
        hatizsak.add(textField_1);

        textPane = new JTextPane();
        textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textPane.setBounds(10, 202, 1047, 425);
        //hatizsak.add(textPane);
        textPane.setEditable(false);


        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 202, 1047, 425);

        hatizsak.add(scrollPane);

        btnLinerisEgyenletrendszer = new JButton("Line\u00E1ris egyenletrendszer");
        btnLinerisEgyenletrendszer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hidePanels();
                linearequation.setVisible(true);
            }
        });
        btnLinerisEgyenletrendszer.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLinerisEgyenletrendszer.setBounds(10, 99, 256, 46);
        frmzakdolgozat.getContentPane().add(btnLinerisEgyenletrendszer);

        linearequation.setVisible(false);

        panels[0] = hatizsak;
        panels[1] = linearequation;
        panels[2] = szallitasi;
        linearequation.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ismeretlenek sz\u00E1ma");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 11, 155, 14);
        linearequation.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Egyenletek sz\u00E1ma");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(10, 36, 137, 14);
        linearequation.add(lblNewLabel_1);

        btnNewButton_2 = new JButton("Gener\u00E1l\u00E1s");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                iteration = 0;
                final int ismeretlenek = (int) spinner.getValue();
                final int egyenletek = (int) spinner_1.getValue();
                sortX = new int[egyenletek];
                col_checked = new boolean[ismeretlenek]; // oszlop
                row_checked = new boolean[egyenletek]; // oszlop

                String[] header = new String[ismeretlenek + 1];
                Object[][] data = new Object[egyenletek][ismeretlenek + 1];

                for (int i = 0; i < ismeretlenek; i++) {
                    header[i] = "X" + (i + 1);
                }

                header[header.length - 1] = "b";

                for (int i = 0; i < egyenletek; i++) {
                    for (int j = 0; j < ismeretlenek + 1; j++) {
                        data[i][j] = null;
                    }
                }

                table.setModel(new DefaultTableModel(
                        data,
                        header
                ));
            }
        });
        btnNewButton_2.setBounds(287, 10, 120, 40);
        linearequation.add(btnNewButton_2);

        spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        spinner.setBounds(177, 10, 86, 20);
        linearequation.add(spinner);

        spinner_1 = new JSpinner();
        spinner_1.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
        spinner_1.setBounds(177, 35, 86, 20);
        linearequation.add(spinner_1);

        table = new JTable();
        table.setRowSelectionAllowed(false);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                }
        ));
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setBounds(10, 95, 603, 100);
        scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 95, 1047, 498);
        scrollPane2.setViewportView(table);
        linearequation.add(scrollPane2);
        
        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(589, 0, 336, 70);
        linearequation.add(scrollPane_3);

        JTextArea textArea_11 = new JTextArea();
        textArea_11.setFont(new Font("Monospaced", Font.PLAIN, 18));
        scrollPane_3.setViewportView(textArea_11);
        
        btnNewButton_3 = new JButton("GO");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                final int ismeretlenek = (int) spinner.getValue();
                final int egyenletek = (int) spinner_1.getValue();
                int pivoty = table.getSelectedRow();
                int pivotx = table.getSelectedColumn();
                int _rows = Integer.parseInt(spinner_1.getValue() + "");
                sortX[iteration] = pivoty+1 - iteration* _rows;
                System.out.println(pivotx + "--- " + pivoty + "--- " + _rows);
                System.out.println(row_checked.length + "---hihi--- " + col_checked.length);


                if (row_checked[pivoty - (_rows) * iteration] || col_checked[pivotx])
                    return;

                row_checked[pivoty - (_rows) * iteration] = true;
                col_checked[pivotx] = true;




                //double[][] matrix = Pivot.go(getPivotMatrix(getActualMatrix(table)), pivotx - _rows*iteration, pivoty);
                matrix = Pivot.go(getPivotMatrix(getActualMatrix(table)), pivoty - _rows * iteration, pivotx);
                int rows = table.getRowCount();

                String[] header = new String[ismeretlenek + 1];
                for (int i = 0; i < ismeretlenek; i++) {
                    header[i] = "X" + (i + 1);
                }

                header[header.length - 1] = "b";

                DefaultTableModel dtm = new DefaultTableModel(header,rows + matrix.length);

                System.out.println(rows);
                System.out.println(matrix.length);
                System.out.println(matrix[0].length);

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        dtm.setValueAt(table.getValueAt(i, j), i, j);
                    }
                }

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        dtm.setValueAt(matrix[i][j], rows + i, j);
                    }
                }



                table.setModel(dtm);
                iteration++;

                boolean vege=true;

                double vanSzabadOszlop=0;
                double vanSzabadSor = 0;


                for (int i = 0; i < col_checked.length; i++) {
                    if (!col_checked[i] )
                        for (int j = 0; j < row_checked.length; j++) {
                            if(!row_checked[j]){
                                if(matrix[i][j]!=0)
                                    vanSzabadOszlop = 1;
                            }
                        }
                }

                for (int i = 0; i < row_checked.length; i++) {
                    if (!row_checked[i]) {
                        for (int j = 0; j < col_checked.length; j++) {
                            if(!col_checked[j]){
                                if(matrix[j][i]!=0)
                                    vanSzabadSor = 1;
                            }
                        }
                    }

                }
                
                String megallapitas;
                if (!(vanSzabadSor == 1 && vanSzabadOszlop == 1))
                	textArea_11.setText(Pivot.megallpitas(matrix, row_checked, col_checked, sortX));
                


                /*for(int i = 0; i < row_checked.length; i++)
                    if(!row_checked[i])
                        vege = false;

                if(!vege) {
                    for (int i = 0; i < col_checked.length; i++)
                        if (!col_checked[i])
                            vege = false;
                }
                if(vege)
                    Pivot.megallpitas(matrix, row_checked, col_checked);*/


                //double[][] matrix = Pivot.go(getPivotMatrix(getActualMatrix(table)), pivotx - _rows*iteration, pivoty);






/*
                int rows =  table.getRowCount();
                DefaultTableModel dtm = new DefaultTableModel(rows + matrix.length, matrix[0].length);

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        dtm.setValueAt(table.getValueAt(i, j), i, j);
                    }
                }

                for (int i = rows; i < rows+matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        dtm.setValueAt(matrix[i-rows][j], i, j);
                    }
                }

                table.setModel(dtm);
                iteration++;

 */
            }
        });
        btnNewButton_3.setBounds(10, 604, 89, 23);
        linearequation.add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Szállítási feladat");
        btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));

        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hidePanels();
                szallitasi.setVisible(true);
            }
        });
        btnNewButton_4.setBounds(10, 155, 256, 46);
        frmzakdolgozat.getContentPane().add(btnNewButton_4);

        textArea.setEditable(false);
       
        ///////////////////////////////////////////////////////////////
    }

    public String[] generateFogyasztok(int darab) {
        String[] output = new String[darab + 2];

        output[0] = "";

        for (int i = 1; i <= darab; i++) {
            output[i] = "F" + i;
        }

        output[darab + 1] = "Kínálat";

        return output;
    }

    public boolean[] generateEditable(int fogyasztok) {
        boolean[] output = new boolean[fogyasztok + 2];
        output[0] = false;

        for (int i = 1; i < output.length; i++) {
            output[i] = true;
        }

        return output;
    }

    public Object[][] generateTablazat(int fogyasztok, int termelok) {
        Object[][] output = new Object[termelok + 1][fogyasztok + 2];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j <= fogyasztok; j++) {
                if (j == 0)
                    output[i][j] = "T" + (i + 1);
                else
                    output[i][j] = "";
            }
        }

        output[termelok][0] = "Igény";

        return output;
    }

    public double[][] getSzallitasiMatrix(JTable table, int fogyasztok, int termelok) {
        double[][] output = new double[termelok + 1][fogyasztok + 1];

        System.out.println(termelok);
        System.out.println(fogyasztok);

        for (int i = 0; i < termelok + 1; i++) {
            for (int j = 1; j <= fogyasztok + 1; j++) {
                output[i][j - 1] = Double.parseDouble(table.getValueAt(i, j) + "");
            }
        }

        return output;
    }

    public Object[][] getActualMatrix(JTable table) {
        int rows = Integer.parseInt(spinner_1.getValue() + "");
        int cols = Integer.parseInt(spinner.getValue() + "");
        Object[][] output = new Object[table.getRowCount() - iteration * rows][cols + 1];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                output[i][j] = table.getValueAt(rows * iteration + i, j);
            }
        }

        return output;
    }

    public Object[][] toObjMatrix(double[][] matrix) {
        Object[][] output = new Object[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                output[i][j] = matrix[i][j];
                System.out.println(matrix[i][j]);
            }
        }

        return output;
    }

    public String[] generateHeader(double[][] matrix) {
        int len = matrix[0].length;
        String[] output = new String[len];

        for (int i = 0; i < len; i++) {
            output[i] = "x" + i;
        }

        return output;
    }

    public double[][] getPivotMatrix(Object[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        double[][] output = new double[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                output[i][j] = Double.parseDouble(matrix[i][j] + "");
            }
        }

        return output;
    }

    public double[] separateBy(String data, String separator) {
        final String ertekek = data.replaceAll("\\s", "");
        final String[] ertekekTomb = ertekek.split(",");
        double[] szamErtekek = new double[ertekekTomb.length];
        int count = 0;

        for (String ert : ertekekTomb) {
            szamErtekek[count++] = Double.parseDouble(ert);
        }

        return szamErtekek;
    }

    public void hidePanels() {
        for (int i = 0; i < panels.length; i++) {
            panels[i].setVisible(false);
        }
    }
}

