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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Form1 {
    private JPanel painel;

    private JTable tabela;

    private JScrollPane painelScroll;
    private JTextField nomeProjeto;
    private JButton voltarButton;

    public Form1(Appi app, String dado) {
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
                    Vector row = new Vector();
                    row.add(records.get(0));
                    row.add(records.get(1));
                    row.add(records.get(2));
                    row.add(records.get(3));
                    row.add(records.get(4));
                    csv_data.addRow(row);


                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TableRowSorter tr = new TableRowSorter(csv_data);
        tabela.setModel(csv_data);
        tabela.setRowSorter(tr);
        nomeProjeto.getDocument().addDocumentListener(new DocumentListener() {
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
                String texto = nomeProjeto.getText();
                return nome.startsWith(texto.toLowerCase());
            }
        });


        painelScroll.getViewport().add(tabela);
        painel.add(painelScroll);
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
