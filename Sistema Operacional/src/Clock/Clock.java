package Clock;

import java.util.LinkedList;
import java.util.Queue;

public class Clock {
    private final int contagemQuadrosPagina; 
    private int contagemFaltasPagina; 
    private final Queue<Pagina> cache; 
    private Pagina ponteiro; 

    private static class Pagina {
        int numero;
        boolean bitReferencia;

        Pagina(int numero) {
            this.numero = numero;
            this.bitReferencia = false;
        }
    }

    public Clock(int contagemQuadrosPagina) {
        if (contagemQuadrosPagina <= 0) {
            throw new IllegalArgumentException("O número de quadros de página deve ser maior que zero.");
        }

        this.contagemQuadrosPagina = contagemQuadrosPagina;
        this.contagemFaltasPagina = 0;
        this.cache = new LinkedList<>();
        this.ponteiro = null;
    }

    public void inserir(int numeroPagina) {
        for (Pagina pagina : cache) {
            if (pagina.numero == numeroPagina) {
                pagina.bitReferencia = true; 
                return;
            }
        }

        contagemFaltasPagina++;

        if (cache.size() >= contagemQuadrosPagina) {
            substituirPagina();
        }

        Pagina novaPagina = new Pagina(numeroPagina);
        cache.offer(novaPagina);
        if (ponteiro == null) {
            ponteiro = novaPagina; 
        }
    }

    private void substituirPagina() {
        while (true) {
            if (ponteiro == null) {
                ponteiro = cache.peek();
            }

            if (ponteiro.bitReferencia) {
                ponteiro.bitReferencia = false; 
                ponteiro = cache.poll();
                cache.offer(ponteiro);
            } else {
                cache.remove(ponteiro);
                ponteiro = cache.peek();
                break;
            }
        }
    }

    public int getContagemFaltasPagina() {
        return contagemFaltasPagina;
    }

    public void exibirCache() {
        System.out.println("Estado atual do cache: ");
        for (Pagina pagina : cache) {
            System.out.print("Pagina: " + pagina.numero + ", Bit de Referencia: " + pagina.bitReferencia + " | ");
        }
        System.out.println();
    }

    public int simular(int[] paginas) {
        for (int pagina : paginas) {
            inserir(pagina);
            exibirCache();
        }
        System.out.println("Total de faltas de pagina: " + getContagemFaltasPagina());
                return contagemFaltasPagina;
    }

    public static void main(String[] args) {
        Clock clock = new Clock(3); 

        int[] paginas = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5}; 

        clock.simular(paginas);
    }
}