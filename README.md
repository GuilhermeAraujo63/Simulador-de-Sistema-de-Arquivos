# Simulador de Algoritmos de Substituição de Páginas

Este projeto implementa um simulador para os algoritmos de substituição de páginas: **Aging**, **Clock**, **LRU (Least Recently Used)** e **NFU (Not Frequently Used)**. O simulador foi desenvolvido em **Java**, com uma interface gráfica utilizando a biblioteca **Swing**, permitindo a visualização do desempenho de cada algoritmo em diferentes cenários de acesso à memória.

## Como Funciona

O simulador permite ao usuário escolher o número de quadros de página disponíveis e uma sequência de páginas a serem carregadas. O programa então simula a execução de cada algoritmo e gera um gráfico com o número de falhas de página ao longo do tempo.

### Algoritmos Implementados

- **Aging**: Usa um contador para manter o histórico de acesso das páginas e decide qual página substituir com base nesse histórico.
- **Clock**: Utiliza uma estrutura circular e marca as páginas com uma referência para decidir qual substituir.
- **LRU (Least Recently Used)**: Substitui a página que foi menos recentemente acessada.
- **NFU (Not Frequently Used)**: Monitora a frequência de acessos a cada página, mas de forma simplificada em relação ao Aging.

## Requisitos

- **Java 8 ou superior**: Para compilar e rodar o código.
- **Biblioteca Swing**: Para a interface gráfica (já inclusa no JDK do Java).

## Como Rodar o Projeto

### Passo 1: Clonar o Repositório

Clone este repositório para sua máquina local:

```bash
[git clone https://github.com/seu-usuario/simulador-algoritmos-substituicao.git](https://github.com/GuilhermeAraujo63/Simulador-de-Sistema-de-Arquivos.git)
