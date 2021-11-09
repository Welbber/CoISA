package entidades;

import entidades.Aluno;
import utils.Constantes;
import java.util.HashSet;
import java.util.Set;

public class Grupo {

    private String nome;

    private Integer tamanho;

    private Set<Aluno> alunos;

    public Grupo(String nome, Integer tamanho) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.alunos = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    /**
     * adiciona um aluno ao grupo caso ele ainda não esteja, caso esteja e lançado erro
     *
     * @param aluno aluno a ser alocado
     */
    public void adicionaAluno(Aluno aluno) {
        if (alunos.contains(aluno))
            throw new IllegalArgumentException(Constantes.MENSAGEM_ALUNO_JA_CADASTRADO_GRUPO);
        if (alunos.size() <= tamanho)
            alunos.add(aluno);
        else
            throw new IllegalArgumentException(Constantes.MENSAGEM_GRUPO_CHEIO);
    }
}
