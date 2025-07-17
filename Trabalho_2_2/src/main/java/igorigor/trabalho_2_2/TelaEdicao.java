
package igorigor.trabalho_2_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

public class TelaEdicao extends JFrame {

    private Tabuleiro tabuleiro;
    private TelaTabuleiro telaPrincipal;
    private JButton[][] botoesCelulas;

    private final char[] tipos = {'+', '@', '&', '#'};
    private final Color corViva = new Color(50, 200, 100);
    private final Color corMorta = Color.LIGHT_GRAY;

    public TelaEdicao(Tabuleiro tabuleiro, TelaTabuleiro telaPrincipal) {
        this.tabuleiro = tabuleiro;
        this.telaPrincipal = telaPrincipal;

        setTitle("Editar Tabuleiro");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        int linhas = tabuleiro.getLinhas();
        int colunas = tabuleiro.getColunas();

        botoesCelulas = new JButton[linhas][colunas];
        JPanel grid = new JPanel(new GridLayout(linhas, colunas));

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                int finalI = i;
                int finalJ = j;

                char tipoAtual = tabuleiro.getMatrizCelulas()[i + 1][j + 1];
                int estadoAtual = tabuleiro.getMatrizEstados()[i + 1][j + 1];

                JButton botao = new JButton(String.valueOf(tipoAtual));
                botao.setFont(new Font("Arial", Font.BOLD, 16));
                botao.setOpaque(true);
                botao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                botao.setBackground(estadoAtual == 1 ? corViva : corMorta);

                botao.addActionListener(e -> {
                    char tipoAntigo = botao.getText().charAt(0);
                    int novoTipoIndex = (getTipoIndex(tipoAntigo) + 1) % tipos.length;
                    char novoTipo = tipos[novoTipoIndex];

                    Color corAtual = botao.getBackground();
                    boolean viva = corAtual.equals(corViva);
                    boolean novaVida = !viva;

                    botao.setText(String.valueOf(novoTipo));
                    botao.setBackground(novaVida ? corViva : corMorta);
                });

                botoesCelulas[i][j] = botao;
                grid.add(botao);
            }
        }

        JButton confirmar = new JButton("Confirmar");
        JButton cancelar = new JButton("Cancelar");
        JButton funcionamento = new JButton("Funcionamento da edição");

        confirmar.addActionListener(e -> {
            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    char tipo = botoesCelulas[i][j].getText().charAt(0);
                    boolean viva = botoesCelulas[i][j].getBackground().equals(corViva);

                    tabuleiro.getMatrizCelulas()[i + 1][j + 1] = tipo;
                    tabuleiro.getMatrizEstadosInicial()[i + 1][j + 1] = viva ? 1 : 0;
                }
            }

            telaPrincipal.setVisible(true);
            dispose();
        });

        cancelar.addActionListener(e -> {
            telaPrincipal.setVisible(true);
            dispose();
        });
        
        funcionamento.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, 
            "Para editar o tabuleiro, clique nas células que deseja modificar.\n\n" +
            "Cada clique alterna entre os tipos e o estado (viva ou morta).\n\n" +
            "\nTipos de Células:\n" +
            "Célula Clássica   (+)\n" +
            "Célula Forte      (@)\n" +
            "Célula Tímida     (&)\n" +
            "Célula Matemática (#)\n\n" +
            "\nVerde: célula está VIVA\n" +
            "Cinza: célula está MORTA\n",
            "\nDesenvolvido por Guilherme Henrique de Sousa e Igor Gabriel Daré Grubisich",
            JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(confirmar);
        painelBotoes.add(cancelar);
        painelBotoes.add(funcionamento);

        add(grid, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private int getTipoIndex(char tipo) {
        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i] == tipo) return i;
        }
        return 0;
    }
}

