import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form3 {
    private JPanel panel1;
    private JButton button1;

    private JLabel jLabel;

    private Appi app;

    Form3(Appi app){
        this.app = app;
        jLabel.setText("Dado não encontrado");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.setContentPane(new Form2(app).getPainel());
                app.pack();
                app.setSize(800,200);
            }
        });
    }

    public JPanel getPainel(){
        return panel1;
    }
}
