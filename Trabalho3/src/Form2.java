import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Form2 {

    private JPanel painel;
    public JTextField nomeArquivo;
    private JButton pesquisarArquivoButton;
    private JButton pesquisarEmOrdemAlfabeticaButton;


    private Appi app;

    private String arquivo;

    public Form2(Appi appi) {
        this.app = appi;
        pesquisarArquivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arquivo = nomeArquivo.getText();
                File file1 = new File(arquivo + ".csv");
                if (file1.exists()) {
                    appi.setContentPane(new Form1(appi, arquivo).getPainel());
                    appi.pack();
                    appi.setSize(800, 200);
                } else {
                    appi.setContentPane(new Form3(appi).getPainel());
                    appi.pack();
                    appi.setSize(800, 200);
                }

            }
        });

        pesquisarEmOrdemAlfabeticaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arquivo = nomeArquivo.getText();
                File file = new File(nomeArquivo.getText()+ ".csv");
                if (file.exists()) {
                    DataSaveSorted alphaSort = new DataSaveSorted();
                    alphaSort.carregaDadosArquivo(arquivo);
                    alphaSort.sortByName();
                    alphaSort.salvaDadosArquivo(arquivo);

                } else {
                    appi.setContentPane(new Form3(appi).getPainel());
                    appi.pack();
                    appi.setSize(800, 200);
                }


            }
        });
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public JPanel getPainel() {
        return painel;
    }
}
