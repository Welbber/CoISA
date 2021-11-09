package utils;

/**
 * Classe responsavel por obter o menu principal do sistema, com as opcoes e formanando o mesmo
 *
 * @author welbber
 */
public class Menu {

    private static final String CADASTRA_ALUNO = "(C)adastrar Aluno";

    private static final String EXIBE_ALUNO = "(E)xibir Aluno";

    private static final String NOVO_GRUPO = "(N)ovo Grupo";

    private static final String ALOCA_ALUNO_GRUPO = "(A)locar Aluno no Grupo e Verificar pertinência a Grupos";

    private static final String REGISTRAR_ALUNO = "(R)egistrar Aluno que Respondeu";

    private static final String IMPRIMI_ALUNO = "(I)mprimir Alunos que Responderam";

    private static final String IMPRIMI_GRUPO_ALUNO = "(O)lhaí quais Grupos o Aluno Tá";

    private static final String SAIR = "(S)im, quero Fechar o Programa!";

    private static final String TITULO = "CONTROLE DE ALUNOS";

    private static final int QUANTIDADE_DE_CARACTERES_LARGURA = 58;

    private static final String BRANCO = " ";

    private static final String MODELO = "\n|%s%s|";

    private static String getCADASTRA_ALUNO() {
        return formataSaida(CADASTRA_ALUNO);
    }

    private static String getEXIBE_ALUNO() {
        return formataSaida(EXIBE_ALUNO);
    }

    private static String getNOVO_GRUPO() {
        return formataSaida(NOVO_GRUPO);
    }

    private static String getALOCA_ALUNO_GRUPO() {
        return formataSaida(ALOCA_ALUNO_GRUPO);
    }

    private static String getREGISTRAR_ALUNO() {
        return formataSaida(REGISTRAR_ALUNO);
    }

    private static String getIMPRIMI_ALUNO() {
        return formataSaida(IMPRIMI_ALUNO);
    }

    private static String getIMPRIMI_GRUPO_ALUNO() {
        return formataSaida(IMPRIMI_GRUPO_ALUNO);
    }

    private static String getSAIR() {
        return formataSaida(SAIR);
    }

    /**
     * Metodo que formata o campo de acordo com o tamanho maximo
     *
     * @param campo campo a ser formatado
     * @return o retorno é a opcao foramtada |OPCAO | preenchando o tamanho maximo da largura do menu
     */
    private static String formataSaida(String campo) {
        return String.format(MODELO, campo, BRANCO.repeat(QUANTIDADE_DE_CARACTERES_LARGURA - campo.length()));
    }

    /**
     * @return retorna uma linha com quantidade de caracteres do tamanho maximo da largura do menu
     */
    private static String linhaTracejada() {
        return String.format("\n+%s+", "-".repeat(QUANTIDADE_DE_CARACTERES_LARGURA));
    }

    /**
     * formatada o menu
     *
     * @return retorna o menu formatado
     */
    public static String menu() {
        StringBuilder menu = new StringBuilder();

        int espacosTitulo = ((QUANTIDADE_DE_CARACTERES_LARGURA / 2) + (TITULO.length() / 2)) - TITULO.length();

        menu.append(linhaTracejada());
        menu.append(String.format("\n|%s%s%s|", BRANCO.repeat(espacosTitulo), TITULO,
                BRANCO.repeat(espacosTitulo % 2 == 0 ? espacosTitulo : espacosTitulo + 1)));
        menu.append(linhaTracejada());
        menu.append(getCADASTRA_ALUNO());
        menu.append(getEXIBE_ALUNO());
        menu.append(getNOVO_GRUPO());
        menu.append(getALOCA_ALUNO_GRUPO());
        menu.append(getREGISTRAR_ALUNO());
        menu.append(getIMPRIMI_ALUNO());
        menu.append(getIMPRIMI_GRUPO_ALUNO());
        menu.append(getSAIR());
        menu.append(linhaTracejada());

        return menu.toString();
    }
}