import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataSaveSorted {
    private List<TrabalhoDePesquisa> trabalhos = new ArrayList<>(3000);


    public void sortByName() {
        Comparator<TrabalhoDePesquisa> comparator = TrabalhoDePesquisa::compareTo;
        this.trabalhos.sort(comparator);
    }

    public void carregaDadosArquivo(String dado) {
        List<TrabalhoDePesquisa> trab = new ArrayList<>(3000);
        String line;
        Path path = Paths.get(dado + ".csv");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");


                String titulo = dados[0];
                String areaConhecimento = dados[1];
                String campus = dados[2];
                String dataInicio = dados[3];
                String dataTermino = dados[4];

                TrabalhoDePesquisa trabalho = new TrabalhoDePesquisa(titulo, areaConhecimento, campus, dataInicio, dataTermino);
                trab.add(trabalho);

            }
            trabalhos = trab;
        } catch (IOException e) {
            System.err.println("Erro: " + e);
        }
    }


    public boolean salvaDadosArquivo(String nomeArquivo) {
        String linha;
        try {
            FileWriter fw = new FileWriter(nomeArquivo + "Alfa.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            for (TrabalhoDePesquisa trab : trabalhos) {
                linha = trab.toString();
                bw.write(linha);
            }

            bw.close();
            return true;
        } catch (IOException e) {
            System.err.println("Erro: " + e);
            return false;
        }
    }
}
