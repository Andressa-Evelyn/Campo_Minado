package CampoMinado.modelo;

import java.util.ArrayList;
import java.util.List;

public class tabuleiro {
    private int linhas;
    private int colunas;
    private int minas;

    private List<Campo> campos = new ArrayList<>();

    public tabuleiro(int linhas, int colunas, int minas){
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        // gerarCampos();
        //associarVizinhos();
        //sortearMinas();
    }

    public void gerarCampos(){
        for(int linha=0; linha < linhas; linha++){
            for(int coluna = 0; coluna < colunas; coluna++){
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    public void associarVizinhos(){
        for(Campo c1: campos){
            for(Campo c2: campos){
                c1.adicionarVizinhos(c2);
            }
        }
    }

    public void sortearMinas(){

    }

}
