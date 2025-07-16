package igorigor.trabalho_2_2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TelaTabuleiro extends JFrame{
    
    private JPanel stringTabuleiro;
    private JMenuBar barraDeMenus;
    private JMenu menuArquivo; 
    private JMenuItem abrir; 
    private JMenuItem salvar; 
    private JFileChooser escolhedorDeArquivo;
    private Tabuleiro novoTabuleiro;
    private JButton botaoAvancar;
    private JButton botaoEditar;
    private JPanel painelSul;
    private JLabel[][] matrizDeLabel;
    private int[][] novaMatrizEstados;
    private JLabel labelTempoEntreTelas;
    private JLabel labelNumeroInteracoes;
    private JLabel labelPronto;
    private JPanel painelInteracoes;
    private JPanel painelTempo;
    private JTextArea areaTempoEntreTelas;
    private JTextArea areaNumeroInteracoes;
    private JButton botaoOk;
    private int tempoIntervalo;
    private int numInteracoes;
    private int contadorInteracoes = 0;
    
    public TelaTabuleiro(Tabuleiro tabuleiro){
        
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        botaoAvancar = new JButton("Avançar");
        botaoAvancar.setEnabled(false);
        botaoAvancar.setActionCommand("Avançar");
        botaoEditar = new JButton("Editar");
        botaoEditar.setEnabled(false);
        botaoEditar.setActionCommand("Editar");

        stringTabuleiro = new JPanel(new GridLayout(3, 1));
        
        painelInteracoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTempo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        labelTempoEntreTelas = new JLabel("Intervalo entre as interações (ms): ");
        labelTempoEntreTelas.setFont(new Font("Arial", Font.BOLD, 15));
        labelNumeroInteracoes = new JLabel("Número de interações: ");
        labelNumeroInteracoes.setFont(new Font("Arial", Font.BOLD, 15));
        areaTempoEntreTelas = new JTextArea(1, 5);
        areaNumeroInteracoes = new JTextArea(1, 5);
        botaoOk = new JButton("OK");
        botaoOk.setBackground(Color.BLACK);
        botaoOk.setForeground(Color.WHITE);
        
        painelInteracoes.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        painelTempo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        
        painelInteracoes.add(labelNumeroInteracoes);
        painelInteracoes.add(areaNumeroInteracoes);
        painelTempo.add(labelTempoEntreTelas);
        painelTempo.add(areaTempoEntreTelas);
        
        barraDeMenus = new JMenuBar();
        setJMenuBar(barraDeMenus);
        
        stringTabuleiro.add(painelInteracoes);
        stringTabuleiro.add(painelTempo);
        stringTabuleiro.add(botaoOk);
        
        menuArquivo = new JMenu("Arquivo"); 
        botaoOk.setActionCommand("botaoOkTempoEntreTelas");
        
        abrir = new JMenuItem("Abrir"); 
        abrir.setActionCommand("Abrir");
        
        salvar = new JMenuItem("Salvar"); 
        salvar.setActionCommand("Salvar");
        
        menuArquivo.add(abrir);
        menuArquivo.add(salvar); 
        
        barraDeMenus.add(menuArquivo); 
        menuArquivo.setEnabled(false);
        
        escolhedorDeArquivo = new JFileChooser();
        
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int resultado;
                
                switch(e.getActionCommand()){
                    case "Abrir":
                        resultado = escolhedorDeArquivo.showOpenDialog(TelaTabuleiro.this); 
                        if(resultado == JFileChooser.APPROVE_OPTION){
                            try{
                                novoTabuleiro = new Tabuleiro(escolhedorDeArquivo.getSelectedFile());
                                novoTabuleiro.completaMatrizes();
                                stringTabuleiro.setLayout(new GridLayout(novoTabuleiro.getLinhas(), novoTabuleiro.getColunas()));
                                matrizDeLabel = new JLabel[novoTabuleiro.getLinhas()][novoTabuleiro.getColunas()];
                                for(int i = 0; i < novoTabuleiro.getLinhas(); i++){
                                    for(int j = 0; j < novoTabuleiro.getColunas(); j++){
                                        matrizDeLabel[i][j] = new JLabel();
                                        matrizDeLabel[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                        matrizDeLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                                        
                                        matrizDeLabel[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                                        
                                        if(novoTabuleiro.getMatrizEstadosInicial()[i + 1][j + 1] == 1){
                                            matrizDeLabel[i][j].setText(String.valueOf(novoTabuleiro.getMatrizCelulas()[i + 1][j + 1]));
                                        }else{
                                            matrizDeLabel[i][j].setText(".");
                                        }
                                    }
                                }
                                stringTabuleiro.removeAll();
                                for(int i = 0; i < novoTabuleiro.getLinhas(); i++){
                                    for(int j = 0; j < novoTabuleiro.getColunas(); j++){
                                        stringTabuleiro.add(matrizDeLabel[i][j]);
                                    }
                                }
                                stringTabuleiro.revalidate();
                                stringTabuleiro.repaint();
                                botaoAvancar.setEnabled(true);
                                novaMatrizEstados = new int[novoTabuleiro.getLinhas()][novoTabuleiro.getColunas()];
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(TelaTabuleiro.this, "Erro ao abrir o arquivo: " + ex.getMessage());
                                return;
                            } catch(InputMismatchException ex2){
                                JOptionPane.showMessageDialog(TelaTabuleiro.this, "Arquivo talvez não seja válido.");
                            }
                        }
                        break; 

                    case "Salvar":
                        resultado = escolhedorDeArquivo.showSaveDialog(TelaTabuleiro.this);
                        if (resultado == JFileChooser.APPROVE_OPTION) {
                            try {
                                PrintWriter writer = new PrintWriter(escolhedorDeArquivo.getSelectedFile());
                                writer.print(novoTabuleiro.getColunas() + " " + novoTabuleiro.getLinhas() + "\n");
                                writer.print(novoTabuleiro.paraArquivo2(novoTabuleiro.getMatrizCelulas()));
                                writer.println();
                                writer.print(novoTabuleiro.retornaMatrizEstados());
                                writer.close();
                                JOptionPane.showMessageDialog(TelaTabuleiro.this, "Tabuleiro salvo com sucesso!");
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(TelaTabuleiro.this, "Erro ao salvar o arquivo: " + ex.getMessage());
                            }
                        }
                        break; 
                    
                    case "Avançar":
                        
                        if(contadorInteracoes < numInteracoes) {
                            botaoAvancar.setEnabled(false);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(tempoIntervalo);
                                    } catch (InterruptedException ex) {
                                        Thread.currentThread().interrupt();
                                        Logger.getLogger(TelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            novoTabuleiro.logica();
                                            novoTabuleiro.copiaMatrizes();
                                            for(int i = 0; i < novoTabuleiro.getLinhas(); i++){
                                                for(int j = 0; j < novoTabuleiro.getColunas(); j++){
                                                    if(novoTabuleiro.getMatrizEstados()[i + 1][j + 1] == 1){
                                                        matrizDeLabel[i][j].setText(String.valueOf(novoTabuleiro.getMatrizCelulas()[i + 1][j + 1]));
                                                    }else{
                                                        matrizDeLabel[i][j].setText(".");
                                                    }
                                                }
                                            }
                                            stringTabuleiro.revalidate();
                                            stringTabuleiro.repaint();
                                            contadorInteracoes++;
                                            
                                            if(contadorInteracoes == numInteracoes){
                                                botaoAvancar.setEnabled(false);
                                                JOptionPane.showMessageDialog(null, "FIM DE JOGO!");
                                            } else {
                                                botaoAvancar.setEnabled(true);
                                            }
                                        }
                                    });
                                }
                            }).start();
                        }
                        break;
                    
                    case "botaoOkTempoEntreTelas":
                        try{
                        numInteracoes = Integer.parseInt(areaNumeroInteracoes.getText());
                        tempoIntervalo = Integer.parseInt(areaTempoEntreTelas.getText());
                        
                        if(numInteracoes < 1 || numInteracoes > 100){
                            JOptionPane.showMessageDialog(null, "O número de interações deve estar entre 1 e 100.");
                            return;
                        }
                        
                        if(tempoIntervalo < 0 || tempoIntervalo > 2000){
                            JOptionPane.showMessageDialog(null, "O tempo de intervalo deve ser entre 0 e 2000 ms.");
                            return;
                        }
                        
                        menuArquivo.setEnabled(true);
                        botaoOk.setEnabled(false);
                        break;
                        
                        }catch(NumberFormatException e1){
                            JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");
                            return;
                        }
                }
            }
        };
        
        abrir.addActionListener(listener); 
        salvar.addActionListener(listener); 
        botaoAvancar.addActionListener(listener);
        botaoEditar.addActionListener(listener);
        botaoOk.addActionListener(listener);
        
        add(stringTabuleiro, BorderLayout.CENTER);
        
        painelSul = new JPanel();
        painelSul.add(botaoEditar);
        painelSul.add(botaoAvancar);
        
        add(painelSul, BorderLayout.SOUTH);
        
        
        setVisible(true);
    }
    
}