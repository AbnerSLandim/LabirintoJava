import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeArquivo {

    private char[][] mapa = new char[10][25];
    private int posicaoX;
    private int posicaoY;
    private boolean chave = false;

    public LeArquivo() {
        String caminhoMapa = "src/mapa1.txt";
        try {
            FileReader arq = new FileReader(caminhoMapa);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha;
            int i = 0;

            while ((linha = lerArq.readLine()) != null && i < mapa.length) {
                if (linha.length() <= mapa[i].length) {
                    mapa[i] = linha.toCharArray();
                } else {
                    System.out.println("Linha maior que o esperado, cortando...");
                    mapa[i] = linha.substring(0, mapa[i].length).toCharArray();
                }
                i++;
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public void recuperaPosicaoPlayer() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '*') {
                    posicaoX = i;
                    posicaoY = j;
                    return;
                }
            }
        }
        System.out.println("Jogador não encontrado no mapa.");
    }

    public void movePersonagem(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int novaX = posicaoX, novaY = posicaoY;

        switch (keyCode) {
            case KeyEvent.VK_UP:
                novaX--;
                break;
            case KeyEvent.VK_DOWN:
                novaX++;
                break;
            case KeyEvent.VK_LEFT:
                novaY--;
                break;
            case KeyEvent.VK_RIGHT:
                novaY++;
                break;
            default:
                System.out.println("Tecla não mapeada");
                return;
        }

        if (novaX >= 0 && novaX < mapa.length && novaY >= 0 && novaY < mapa[0].length) {
            char proximoCaractere = mapa[novaX][novaY];


            if (proximoCaractere == '-' || proximoCaractere == '@' || proximoCaractere == '2') {
                if (proximoCaractere == '2') {
                    chave = true;
                    System.out.println("Você pegou a chave!");
                }

                if (chave && proximoCaractere == '@') {
                    JOptionPane.showMessageDialog(null, "Você Ganhou!!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
                    reiniciarJogo();
                    return;
                }

                mapa[posicaoX][posicaoY] = '-';
                posicaoX = novaX;
                posicaoY = novaY;
                mapa[posicaoX][posicaoY] = '*';  // Marca a nova posição
            } else {
                System.out.println("Movimento bloqueado: próxima posição inválida!");
            }
        } else {
            System.out.println("Movimento inválido: fora dos limites!");
        }
    }

    private void reiniciarJogo() {
        String caminhoMapa = "src/mapa1.txt";
        try {
            FileReader arq = new FileReader(caminhoMapa);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha;
            int i = 0;

            while ((linha = lerArq.readLine()) != null && i < mapa.length) {
                if (linha.length() <= mapa[i].length) {
                    mapa[i] = linha.toCharArray();
                } else {
                    mapa[i] = linha.substring(0, mapa[i].length).toCharArray();
                }
                i++;
            }

            recuperaPosicaoPlayer();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }





    public char[][] getMapa() {
        return mapa;
    }
}
