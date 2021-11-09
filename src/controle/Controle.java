package controle;

import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;
import validaçao.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Controle {


    private HashMap<String, Aluno> mapaAlunos;
    private HashMap<String, Grupo> mapaGrupos;
    private HashSet<Aluno> alunosRespondemQuadro;

    public Controle() {
        this.mapaAlunos = new HashMap<>();
        this.mapaGrupos = new HashMap<>();
        this.alunosRespondemQuadro = new HashSet<>();
    }

    /**
     * metodo que cadastra um aluno caso os campos estejam validos e nao existe um aluno com a matricula informada
     *
     * @param matricula matricula aluno
     * @param nome      nome aluno
     * @param curso     curso aluno
     */
    public void cadastraAluno(String matricula, String nome, String curso) {

        //trecho para realizar validação da entrada do usuario
        ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
        ValidacaoControle.validaCampo(nome, TiposCampo.ALFABETO);
        ValidacaoControle.validaCampo(curso, TiposCampo.ALFABETO);

        //valida se já existe um aluno com matricula
        ValidacaoControle.validaExistenciaAluno(this.mapaAlunos, nome, false);

        mapaAlunos.put(matricula, new Aluno(matricula, nome, curso));
    }

    public Aluno retornaAluno(String matricula) {

        ValidacaoControle.validaExistenciaAluno(this.mapaAlunos, matricula, true);

        return mapaAlunos.get(matricula);
    }

    public void cadastraGrupo(String nome, Integer tamanho) {

        //nao existe grupo com tamanho negativo ou zerado
        if (tamanho <= 0)
            throw new IllegalArgumentException(Constantes.MENSAGEM_TAMANHO_GRUPO_INVALIDO);

        ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nome, false);

        mapaGrupos.put(nome.toLowerCase(Locale.ROOT), new Grupo(nome, tamanho));
    }

    public void alocaAluno(String matricula, String nomeGrupo) {

        ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
        ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nomeGrupo, true);

        Grupo grupo = mapaGrupos.get(nomeGrupo);
        grupo.adicionaAluno(retornaAluno(matricula));
    }

    public boolean pertinenciaGrupo(String matricula, String nomeGrupo) {
        ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);

        ValidacaoControle.validaExistenciaGrupo(this.mapaGrupos, nomeGrupo, true);
        return mapaGrupos.get(nomeGrupo).getAlunos().contains(retornaAluno(matricula));
    }

    public void cadastraAlunoRespondeQuadro(String matricula) {
        ValidacaoControle.validaCampo(matricula, TiposCampo.NUMERICO);
        Aluno aluno = retornaAluno(matricula);
        ValidacaoControle.alunoJarespondeuQuadro(this.alunosRespondemQuadro, aluno);

        this.alunosRespondemQuadro.add(aluno);
    }

    public HashSet<Aluno> getAlunosRespondemQuadro() {
        return this.alunosRespondemQuadro;
    }

    public HashSet<Grupo> gruposAlunoEsta(String matricula) {

        HashSet<Grupo> grupos = new HashSet<>();
        Aluno aluno = retornaAluno(matricula);
        for (String nomeGrupo : this.mapaGrupos.keySet()) {
            if (this.mapaGrupos.get(nomeGrupo).getAlunos().contains(aluno))
                grupos.add(this.mapaGrupos.get(nomeGrupo));
        }
        return grupos;
    }
}
