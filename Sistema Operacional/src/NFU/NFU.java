package NFU;

import java.util.HashMap;
import java.util.Map;

public class NFU {
    private final int contagemQuadrosPagina;
    private int contagemFaltasPagina;
    private final Map<Integer, Integer> cache;
    private final Map<Integer, Integer> frequencia;

    public NFU(int contagemQuadrosPagina) {
        if (contagemQuadrosPagina <= 0) {
            throw new IllegalArgumentException("O número de quadros de página deve ser maior que zero.");
        }

        this.contagemQuadrosPagina = contagemQuadrosPagina;
        this.contagemFaltasPagina = 0;
        this.cache = new HashMap<>();
        this.frequencia = new HashMap<>();
    }

    public void inserir(int numeroPagina) {
        if (!cache.containsKey(numeroPagina)) {
            contagemFaltasPagina++;

            if (cache.size() >= contagemQuadrosPagina) {
                int paginaParaRemover = cache.keySet().stream()
                        .min((p1, p2) -> Integer.compare(frequencia.get(p1), frequencia.get(p2)))
                        .orElseThrow();

                cache.remove(paginaParaRemover);
                frequencia.remove(paginaParaRemover);
            }

            cache.put(numeroPagina, numeroPagina);
            frequencia.put(numeroPagina, 1);
        } else {
            frequencia.put(numeroPagina, frequencia.get(numeroPagina) + 1);
        }
    }

    public int getContagemFaltasPagina() {
        return contagemFaltasPagina;
    }

    public void exibirCache() {
        System.out.println("Estado atual do cache: " + cache.keySet());
    }

    public int simular(int[] paginas) {
        for (int pagina : paginas) {
            inserir(pagina);
        }
        return getContagemFaltasPagina();
    }

    public static void main(String[] args) {
        NFU nfu = new NFU(3);

        int[] paginas = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};

        for (int pagina : paginas) {
            nfu.inserir(pagina);
            nfu.exibirCache();
        }

        System.out.println("Total de faltas de pagina: " + nfu.getContagemFaltasPagina());
    }
}