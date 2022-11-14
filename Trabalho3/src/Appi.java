import javax.swing.*;

/**
 * Aplicacao grafica
 */

public class Appi extends JFrame {

    public Appi() {
        super();
        Form2 formDois = new Form2(this);
        this.setContentPane(formDois.getPainel());
        this.setTitle("Trabalho 3");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800,200);
        this.setVisible(true);
    }

}

