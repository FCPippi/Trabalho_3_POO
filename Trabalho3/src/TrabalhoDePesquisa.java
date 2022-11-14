import java.text.Normalizer;

public class TrabalhoDePesquisa implements Comparable<TrabalhoDePesquisa> {

    private String titulo;
    private String areaConhecimento;
    private String campus;
    private String dataInicio;
    private String dataTermino;

    public TrabalhoDePesquisa(String titulo, String areaConhecimento, String campus, String dataInicio, String dataTermino) {
        this.titulo = titulo;
        this.areaConhecimento = areaConhecimento;
        this.campus = campus;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo + ','
                + areaConhecimento + ','
                + campus + ','
                + dataInicio + ','
                + dataTermino;
    }
    @Override
    public int compareTo(TrabalhoDePesquisa t) {
        String s1 = Normalizer
                .normalize(titulo.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        String s2 = Normalizer
                .normalize(t.titulo.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        return s1.compareTo(s2);
    }
}

