package run;

import entidades.Aluno;
import entidades.Grupo;
import utils.Menu;
import controle.Controle;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

public class MainControle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controle controle = new Controle();
        testeCadastraAluno(controle);
        while (true) {
            try {
                String opcao = menu(scanner);
                comando(opcao.toLowerCase(Locale.ROOT), scanner, controle);
            } catch (NullPointerException e) {
                System.err.println("Posicao invalida!");
            } catch (Exception e) {
                //System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * metodo exclusivo para testes e desenvolvimento
     *
     * @param controle
     */
    private static void testeCadastraAluno(Controle controle) {
        controle.cadastraAluno("123", "welbber", "Ciência computação");
        controle.cadastraAluno("124", "Joao", "Ciência computação");
        controle.cadastraAluno("125", "Jose", "Ciência computação");
        controle.cadastraGrupo("Estudos", 10);
        controle.cadastraGrupo("PROG II", 50);
    }

    /**
     * metodo que imprime o menu principal do sistema
     *
     * @param scanner scanner para capturar opcao desejada do usuario
     * @return o retorno e a opcao desejada pelo usuario
     */
    private static String menu(Scanner scanner) {
        log(Menu.menu());
        log("\nOpção> ");
        String opcao = scanner.nextLine();
        return opcao;
    }

    /**
     * @param opcao    opcao desejada pelo usuario
     * @param scanner  scanner para se necessario ser usado pelos metodos invodacados
     * @param controle controle no qual deve-se realizar necessario
     */
    private static void comando(String opcao, Scanner scanner, Controle controle) {
        switch (opcao) {
            case "c":
                cadastraAluno(scanner, controle);
                break;
            case "e":
                consultaAluno(scanner, controle);
                break;
            case "n":
                cadastraGrupo(scanner, controle);
                break;
            case "a":
                alocaAluno(scanner, controle);
                break;
            case "r":
                cadastraAlunoRespondeQuadro(scanner, controle);
                break;
            case "i":
                imprimiAlunosRespondeQuadro(controle);
                break;
            case "o":
                checaGrupoAlunoEsta(scanner, controle);
                break;
            case "s":
                sair();
                break;
            default:
                log("Opção invalida");
        }
    }

    /**
     * Metodo para checar quais grupos um aluno esta
     *
     * @param scanner  scanner para capturar entrada do usuario
     * @param controle controle no qual ser checado
     */
    private static void checaGrupoAlunoEsta(Scanner scanner, Controle controle) {
        log("Matrícula> ");
        String matricula = scanner.nextLine();

        HashSet<Grupo> grupos = controle.gruposAlunoEsta(matricula);

        log("Grupos:");

        //caso o aluno não esteja em nenhum grupo
        if (grupos.isEmpty())
            log("Auno não esta em nenhum grupo");
        else {
            for (Grupo grupo : grupos) {
                log(String.format("- %s", grupo.getNome()));
            }
        }
    }

    /**
     * Metodo que lista todos os alunos que responderam ao quadro
     *
     * @param controle controle no qual sera buscado a lista
     */
    private static void imprimiAlunosRespondeQuadro(Controle controle) {
        log("Alunos: ");

        HashSet<Aluno> alunos = controle.getAlunosRespondemQuadro();

        if (alunos.isEmpty())
            log("Nenhum aluno respondeu questões no quadro");
        else {
            int count = 1;
            for (Aluno aluno : alunos) {
                log(String.format("%d. %s", count, aluno.toString()));
            }
        }
    }

    /**
     * realiza cadastro de alunos que responderam questoes no quadro
     *
     * @param scanner  scanner para capturar entrada usuario
     * @param controle controle no qual ser cadastrado informacoes
     */
    private static void cadastraAlunoRespondeQuadro(Scanner scanner, Controle controle) {
        log("Matrícula> ");
        String matricula = scanner.nextLine();

        controle.cadastraAlunoRespondeQuadro(matricula);

        log("Aluno Registrado!");
    }

    /**
     * aloca ou verifica pertinencia de um aluno a um grupo
     *
     * @param scanner  capturar entrada usuario
     * @param controle controle no qual sera checado
     */
    private static void alocaAluno(Scanner scanner, Controle controle) {
        log("(A)locar Aluno ou (P)ertinência a Grupo?> ");
        String opcao = scanner.nextLine();

        // erro caso usuario digite algo diferente
        if (!(opcao.equals("a") || opcao.equals("p"))) {
            throw new IllegalArgumentException("Opção invalida!");
        }

        log("Grupo> ");
        String grupo = scanner.nextLine();

        log("Matricula> ");
        String matricula = scanner.nextLine();

        if (opcao.toLowerCase(Locale.ROOT).equals("a")) {
            controle.alocaAluno(matricula, grupo);
            log("Aluno alocado!");
        } else {
            if (controle.pertinenciaGrupo(matricula, grupo)) {
                log("Aluno pertence ao grupo");
            } else {
                log("Aluno não pertence ao grupo");
            }
        }
    }

    /**
     * metodo que usa sys.exit() para encerrar aplicacao
     */
    private static void sair() {
        log("Até mais!");
        System.exit(0);
    }

    /**
     * Metodo para consultar consultar um aluno através da matricula informada pelo ususario
     *
     * @param scanner  Scanner para capturar entrada usuario
     * @param controle controle no qual ira buscar aluno
     */
    private static void consultaAluno(Scanner scanner, Controle controle) {

        log("Matrícula> ");
        String matricula = scanner.nextLine();

        log(controle.retornaAluno(matricula).toString());
    }

    /**
     * metodo para cadastrar um aluno a um controle
     *
     * @param scanner  para capturar as informacoes do aluno
     * @param controle controle no qual aluno sera cadastrado
     */
    private static void cadastraAluno(Scanner scanner, Controle controle) {
        log("Matrícula> ");
        String matricula = scanner.nextLine();

        log("Nome> ");
        String nomeAluno = scanner.nextLine();

        log("Curso> ");
        String curso = scanner.nextLine();

        controle.cadastraAluno(matricula, nomeAluno, curso);

        log("Cadastro realizado!");
    }

    /**
     * Metodo que cadastra grupo
     *
     * @param scanner  scanner para capturar entrada usuario
     * @param controle controle no qual sera cadastrado grupo
     */
    private static void cadastraGrupo(Scanner scanner, Controle controle) {
        log("Grupo> ");
        String nomeGrupo = scanner.nextLine();

        log("Tamanho> ");
        Integer tamanho = scanner.nextInt();

        controle.cadastraGrupo(nomeGrupo, tamanho);

        log("Cadstro realizado!");
    }


    /**
     * metodo que imprime mensagens recebidas atraves parametro
     *
     * @param mensagem mensagem que deve ser imprimida
     */
    private static void log(Object mensagem) {
        System.out.print("\n" + mensagem);
    }
}
