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
```

### Passo 2: Compilar o Projeto

Abra o terminal e navegue até a pasta do projeto. Em seguida, compile o código utilizando o seguinte comando:

```bash
javac SimuladorPaginas.java
```

### Passo 3: Executar o Simulador

Após a compilação, execute o simulador com o comando:

```bash
java SimuladorPaginas
```

Isso abrirá a interface gráfica onde você poderá:

- Inserir a sequência de páginas a ser carregada.
- Selecionar o número de quadros disponíveis.
- Escolher o algoritmo de substituição de páginas.
- Visualizar os resultados e gráficos.

### Como Usar a Interface Gráfica

1. Sequência de Páginas: Insira uma sequência de números inteiros separados por vírgulas (exemplo: 1,2,3,4,5,1,2,6).
2. Número de Quadros: Insira o número de quadros disponíveis na memória.
3. Escolher Algoritmo: Selecione um dos algoritmos de substituição de páginas para realizar a simulação (Aging, Clock, LRU ou NFU).
4. Rodar Simulação: Clique no botão para iniciar a simulação. O número de falhas de página será calculado e exibido.

## Exemplos de Teste

Você pode testar os algoritmos com diferentes sequências de páginas e número de quadros para observar o impacto nas falhas de página. Alguns exemplos de sequências de páginas:

- Sequência 1: 1,2,3,4,1,2,3,4,1,2,3,4
- Sequência 2: 1,2,3,1,2,3,4,5,6,1,2,3

### Resultados
O simulador gera um gráfico com o número de falhas de página ao longo do tempo para cada algoritmo. Esses gráficos ajudam a entender o desempenho de cada algoritmo em diferentes cenários de memória.
