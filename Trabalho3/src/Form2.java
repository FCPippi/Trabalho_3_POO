import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Form2 {

    private JPanel painel;
    public JTextField nomeArquivo;
    private JButton pesquisarArquivoButton;
    private JButton pesquisarEmOrdemAlfabeticaButton;

    public Form2(Appi appi) {
        pesquisarArquivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String arquivo = nomeArquivo.getText();
                File file1 = new File(arquivo + ".csv");
                if (file1.exists()) {
                    try {
                        appi.setContentPane(new Form1(appi, arquivo).getPainel());
                        appi.pack();
                        appi.setSize(800, 200);
                    } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsException) {
                        appi.setContentPane(new Form3(appi, "Erro no processamento dos Dados.").getPainel());
                        appi.pack();
                        appi.setSize(800, 200);
                    }
                } else {
                    appi.setContentPane(new Form3(appi, "Dado não encontrado.").getPainel());
                    appi.pack();
                    appi.setSize(800, 200);
                }

            }
        });

        pesquisarEmOrdemAlfabeticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String arquivo = nomeArquivo.getText();
                File file = new File(nomeArquivo.getText() + ".csv");
                if (file.exists()) {
                    try {
                        DataSaveSorted alphaSort = new DataSaveSorted();
                        alphaSort.carregaDadosArquivo(arquivo);
                        alphaSort.sortByName();
                        alphaSort.salvaDadosArquivo(arquivo);
                    } catch (ArrayIndexOutOfBoundsException indexOutOfBoundsException) {
                        appi.setContentPane(new Form3(appi, "Erro no processamento dos Dados.").getPainel());
                        appi.pack();
                        appi.setSize(800, 200);
                    }

                } else {
                    appi.setContentPane(new Form3(appi, "Dado não encontrado").getPainel());
                    appi.pack();
                    appi.setSize(800, 200);
                }


            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }
}
