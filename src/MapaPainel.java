import javax.swing.*;
import java.awt.*;

public class MapaPainel extends JPanel {
    private char[][] mapa;

    public MapaPainel(char[][] mapa) {
        this.mapa = mapa;
    }

    public void setMapa(char[][] novoMapa) {
        this.mapa = novoMapa;
        repaint(); // Solicita o redesenho do painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mapa == null) return;

        int cellSize = 30; // Tamanho de cada célula

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                char c = mapa[i][j];

                // Escolhe uma cor com base no caractere
                switch (c) {
                    case '*':
                        g.setColor(Color.RED);
                        break;
                    case '|':
                        g.setColor(Color.GRAY);
                        break;
                    case '_':
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                    case '2':
                        g.setColor(Color.YELLOW);
                        break;
                    case '@':
                        g.setColor(Color.BLUE);
                        break;
                    default: // Espaço vazio
                        g.setColor(Color.WHITE);
                        break;
                }

                // Desenha a célula como um quadrado preenchido
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                // Desenha a borda da célula
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
