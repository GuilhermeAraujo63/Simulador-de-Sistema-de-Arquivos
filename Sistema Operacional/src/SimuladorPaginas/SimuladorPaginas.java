package SimuladorPaginas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Aging.AgingPageReplacement;
import Clock.Clock;
import LRU.LRU;
import NFU.NFU;
import java.util.Arrays;

public class SimuladorPaginas {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Simulador de Algoritmos de Substituição de Páginas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel labelAlgoritmo = new JLabel("Escolha o algoritmo a ser simulado:");
            JComboBox<String> comboAlgoritmo = new JComboBox<>(new String[]{"Aging", "Clock", "LRU", "NFU"});

            JLabel labelQuadros = new JLabel("Insira o número de quadros de página:");
            JTextField textQuadros = new JTextField();

            JLabel labelPaginas = new JLabel("Insira a sequência de páginas (separadas por espaço):");
            JTextField textPaginas = new JTextField();

            JButton botaoSimular = new JButton("Simular");
            JTextArea areaResultado = new JTextArea(10, 50);
            areaResultado.setEditable(false);

            JScrollPane scrollResultado = new JScrollPane(areaResultado);

            final GraphPanel graficoPanel = new GraphPanel();
            graficoPanel.setPreferredSize(new Dimension(600, 300));

            botaoSimular.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        
                        int quadros = Integer.parseInt(textQuadros.getText().trim());
                        if (quadros <= 0) {
                            throw new NumberFormatException("O número de quadros deve ser um número positivo.");
                        }

                        String[] inputPaginas = textPaginas.getText().trim().split(" ");
                        int[] paginas = new int[inputPaginas.length];
                        for (int i = 0; i < inputPaginas.length; i++) {
                            paginas[i] = Integer.parseInt(inputPaginas[i]);
                        }

                        if (paginas.length == 0) {
                            areaResultado.append("Erro: A sequência de páginas não pode estar vazia.\n");
                            return;
                        }

                        String algoritmo = (String) comboAlgoritmo.getSelectedItem();
                        int faltas = 0;

                        switch (algoritmo) {
                            case "Aging":
                                AgingPageReplacement aging = new AgingPageReplacement();
                                faltas = aging.aging(paginas, quadros);
                                break;

                            case "Clock":
                                Clock clock = new Clock(quadros);
                                faltas = clock.simular(paginas);
                                break;

                            case "LRU":
                                LRU lru = new LRU(quadros);
                                faltas = lru.simular(paginas);
                                break;

                            case "NFU":
                                NFU nfu = new NFU(quadros);
                                faltas = nfu.simular(paginas);
                                break;

                            default:
                                areaResultado.append("Algoritmo inválido!\n");
                                return;
                        }

                        areaResultado.append("Algoritmo: " + algoritmo + " - Faltas de página: " + faltas + "\n");

                        graficoPanel.setGraphData(paginas, quadros, algoritmo, faltas);
                        graficoPanel.repaint();

                    } catch (NumberFormatException ex) {
                        areaResultado.append("Erro: " + ex.getMessage() + "\n");
                    }
                }
            });

            panel.add(labelAlgoritmo);
            panel.add(comboAlgoritmo);
            panel.add(labelQuadros);
            panel.add(textQuadros);
            panel.add(labelPaginas);
            panel.add(textPaginas);
            panel.add(botaoSimular);
            panel.add(scrollResultado);
            panel.add(graficoPanel);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static class GraphData {
        int[] paginas;
        int quadros;
        @SuppressWarnings("unused")
        String algoritmo;
        @SuppressWarnings("unused")
        int faltas;

        public void setData(int[] paginas, int quadros, String algoritmo, int faltas) {
            this.paginas = paginas;
            this.quadros = quadros;
            this.algoritmo = algoritmo;
            this.faltas = faltas;
        }
    }

    private static class GraphPanel extends JPanel {
        private GraphData graficoData = new GraphData();

        public void setGraphData(int[] paginas, int quadros, String algoritmo, int faltas) {
            graficoData.setData(paginas, quadros, algoritmo, faltas);
            System.out.println("Dados para o gráfico: " + algoritmo + " | Faltas: " + faltas);
            System.out.println("Sequência de páginas: " + Arrays.toString(paginas));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            desenharGrafico(g, graficoData);
        }

        @SuppressWarnings("unused")
        private void desenharGrafico(Graphics g, GraphData graficoData) {
            if (graficoData == null || graficoData.paginas == null) return;

            Graphics2D g2d = (Graphics2D) g;

            int larguraBarra = Math.max(1, 600 / graficoData.paginas.length);
            int alturaMaxima = 300;

            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, 600, 300);

            g2d.setColor(Color.BLUE);
            for (int i = 0; i < graficoData.paginas.length; i++) {
                int altura = (int) (((double) graficoData.paginas[i] / graficoData.quadros) * alturaMaxima);
                g2d.fillRect(i * larguraBarra, alturaMaxima - altura, larguraBarra, altura);
            }

            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(2));
            for (int i = 0; i < graficoData.paginas.length; i++) {
                int altura = (int) (((double) graficoData.paginas[i] / graficoData.quadros) * alturaMaxima);
                g2d.drawLine(i * larguraBarra, alturaMaxima, i * larguraBarra, alturaMaxima - altura);
            }

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            for (int i = 0; i < graficoData.paginas.length; i++) {
                int altura = (int) (((double) graficoData.paginas[i] / graficoData.quadros) * alturaMaxima);
                g2d.drawString(String.valueOf(graficoData.paginas[i]), i * larguraBarra, alturaMaxima - 5);
            }
        }
    }
}