import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.List;
import java.util.Vector;

public class Form1 {
    private Form2 formDois;
    private JPanel painel;

    private JTable jTable1;

    private JScrollPane jScrollPane2;
    private JTextField textField1;
    private JButton voltarButton;

    private Appi app;

    public Form1(Appi app, String dado) {
        this.app = app;
        this.formDois = formDois;
        jTable1 = new JTable();

        jScrollPane2 = new JScrollPane();


        DefaultTableModel csv_data = new DefaultTableModel();
        try {
            FileReader fileReader;
            CSVFormat csvFileFormat = CSVFormat.newFormat(';');
            String fileName = dado + ".csv";
            fileReader = new FileReader(fileName);

            CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);


            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            int start = 0;
            for (CSVRecord records : csvRecords) {
                if (start == 0) {
                    start = 1;
                    csv_data.addColumn("Titulo");
                    csv_data.addColumn("Area do Conhecimento");
                    csv_data.addColumn("Campus");
                    csv_data.addColumn("Data inicio");
                    csv_data.addColumn("Data de fim");
                } else {
                    Vector row = new Vector(5);

                    row.add(records.get(0));
                    row.add(records.get(1));
                    row.add(records.get(2));
                    row.add(records.get(3));
                    row.add(records.get(4));
                    csv_data.addRow(row);

                }
                System.out.println(records.get(1));
            }

        } catch (Exception e) {
            System.out.println("Error in Parsing CSV File");
            e.printStackTrace();

        }
        TableRowSorter tr = new TableRowSorter(csv_data);
        jTable1.setModel(csv_data);
        jTable1.setRowSorter(tr);
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                csv_data.fireTableDataChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                csv_data.fireTableDataChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                csv_data.fireTableDataChanged();
            }
        });
        tr.setRowFilter(new RowFilter() {
            @Override
            public boolean include(Entry entry) {
                String nome = entry.getValue(0).toString().toLowerCase();
                String texto = textField1.getText();
               return nome.startsWith(texto.toLowerCase());
            }
        });


        jScrollPane2.getViewport().add(jTable1);
        painel.add(jScrollPane2);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setContentPane(new Form2(app).getPainel());
                app.pack();
                app.setSize(800, 200);
            }
        });


    }

    public Form1(Appi app, String dado, String alfa) {
        this.app = app;

        jTable1 = new JTable();

        jScrollPane2 = new JScrollPane();


        DefaultTableModel csv_data = new DefaultTableModel();
        try {
            FileReader fileReader;
            CSVFormat csvFileFormat = CSVFormat.DEFAULT;
            String fileName = ".csv";
            fileReader = new FileReader(fileName);

            CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);


            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            int start = 0;
            for (CSVRecord records : csvRecords) {
                if (start == 0) {
                    start = 1;
                    csv_data.addColumn("Titulo");
                    csv_data.addColumn("Area do Conhecimento");
                    csv_data.addColumn("Campus");
                    csv_data.addColumn("Data inicio");
                    csv_data.addColumn("Data de fim");
                } else {
                    Vector row = new Vector(5);
                    row.add(records.get(0));
                    row.add(records.get(1));
                    row.add(records.get(2));
                    row.add(records.get(3));
                    row.add(records.get(4));
                    csv_data.addRow(row);

                }
                System.out.println(records.get(1));
            }

        } catch (Exception e) {
            System.out.println("Error in Parsing CSV File");
            e.printStackTrace();

        }
        jTable1.setModel(csv_data);
        jScrollPane2.getViewport().add(jTable1);
        painel.add(jScrollPane2);
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setContentPane(new Form2(app).getPainel());
                app.pack();
                app.setSize(800, 200);
            }
        });


    }

    public JPanel getPainel() {
        return painel;
    }
}
