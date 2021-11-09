package validaçao;

import entidades.Aluno;
import entidades.Grupo;
import utils.Constantes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/**
 * @author welbber porto
 * classe que valida se um campo esta de acordo com seu tipo
 */
public class ValidacaoControle {
    /**
     * metodo que valida um campo atraves de regex que identificam se o campo possui somente numeros ou somente numero
     *
     * @param campo campo a ser validado
     * @param tipo  tipo do campo a ser validado
     */
    public static void validaCampo(String campo, TiposCampo tipo) {
        if (isBlank(campo)) {
            throw new NullPointerException(Constantes.MENSAGEM_CAMPO_VAZIO);
        }

        switch (tipo) {
            case NUMERICO:
                if (!campo.matches("^[0-9]*+$"))
                    throw new IllegalArgumentException(Constantes.MENSAGEM_SOMENTE_NUMEROS);
                break;
            case ALFABETO:

                if (!campo.matches("^[a-zA-ZzáàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ\\s]*+$"))
                    throw new IllegalArgumentException(Constantes.MENSAGEM_SOMENTE_LETRAS);
                break;
            case ALPHANUMERICO:
                break;
        }
    }

    /**
     * Se o nome grupo informado existir dentro do map informado é lançado uma excesão de existencia
     *
     * @param mapa  mapa no qual sera checado se a chave informada ja existe
     * @param chave chave a ser analisada
     */
    public static void validaExistenciaGrupo(HashMap<String, Grupo> mapa, String chave, boolean deveExistir) {

        String nomeGrupo = chave.toLowerCase(Locale.ROOT);

        if (deveExistir) {
            if (!mapa.containsKey(nomeGrupo))
                throw new IllegalArgumentException(Constantes.MENSAGEM_GRUPO_NAO_EXISTENTE);
        } else {
            if (mapa.containsKey(nomeGrupo))
                throw new IllegalArgumentException(Constantes.MENSAGEM_GRUPO_EXISTENTE);
        }

    }

    /**
     * Verifica se deve ser necessario ou nao a existencia de um aluno ja cadastado com aquela matricula
     *
     * @param mapa  mapa de alunos
     * @param chave matricula a ser verificada
     */
    public static void validaExistenciaAluno(HashMap<String, Aluno> mapa, String chave, boolean deveExistir) {
        if (deveExistir) {
            if (!mapa.containsKey(chave))
                throw new IllegalArgumentException(Constantes.MENSAGEM_MATRICULA_NAO_EXISTENTE);
        } else {
            if (mapa.containsKey(chave))
                throw new IllegalArgumentException(Constantes.MENSAGEM_MATRICULA_EXISTENTE);
        }
    }

    /**
     * verifica se aluno ja respondeu ao quadro, se sim e lancado erro
     *
     * @param listaAlunos lista de alunos que responderam ao quadro
     * @param aluno       aluno a ser checado
     */
    public static void alunoJarespondeuQuadro(HashSet<Aluno> listaAlunos, Aluno aluno) {
        if (listaAlunos.contains(aluno))
            throw new IllegalArgumentException(Constantes.MENSAGEM_ALUNO_RESPONDEU_QUADRO);
    }

    /**
     * metodo que analisa se um campo é nulo ou vazio ou somente com espacos
     *
     * @param campo campo a ser validado
     * @return retorno true caso o campo esteja vazio
     */
    private static boolean isBlank(String campo) {
        return campo == null || campo.trim().equals("");
    }
}
