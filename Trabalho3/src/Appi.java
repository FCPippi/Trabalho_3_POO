import javax.swing.*;

/**
 * Aplicacao grafica
 */

public class Appi extends JFrame {
    private Form1 formUm;
    private Form2 formDois;

    public Appi() {
        super();
        formDois = new Form2(this);
        this.setContentPane(formDois.getPainel());
        this.setTitle("Trabalho 3");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800,200);
        this.setVisible(true);
    }

    /**
     * Alterna os paineis da janela
     * @param painel numero do painel para a troca
     */




}

