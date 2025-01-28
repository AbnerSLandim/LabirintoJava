import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        LeArquivo leArquivo = new LeArquivo();
        leArquivo.recuperaPosicaoPlayer();
        char[][] mapa = leArquivo.getMapa();

        MapaPainel mapaPainel = new MapaPainel(mapa);

        JFrame frame = new JFrame("Mapa Gráfico");
        frame.setSize(720, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mapaPainel);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                leArquivo.movePersonagem(e);
                mapaPainel.setMapa(leArquivo.getMapa());
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        frame.setVisible(true);
    }
}
