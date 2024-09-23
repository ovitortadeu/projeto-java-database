package br.com.CarroSeguro.main;

import br.com.CarroSeguro.model.dao.*;
import br.com.CarroSeguro.model.dto.*;

import java.sql.Connection;
import java.util.Scanner;

public class UsaCarroSeguro {
    public static void main(String[] args) {
        Scanner scan;
        String aux = "";
        int auxN = 0;
        int menu = 0;

        try {
            scan = new Scanner(System.in);
            Connection con = ConnectionFactory.abrirConexao();


            while (true) {

                System.out.println("Bem vindo ao CarroSeguro\nGostaria de fazer seu cadastro?\n[1]Sim\n[2]Não");
                menu = scan.nextInt();

                while (menu == 1) {
                    // Fazendo cadastro
                    Usuario user = new Usuario();
                    UsuarioDAO userDAO = new UsuarioDAO(con);

                    int novoId = userDAO.obterNovoIdUsuario(con);
                    user.setIdUsuario(novoId);

                    scan.nextLine();  // Consumir o newline residual

                    System.out.println("Primeiro precisamos de seu nome");
                    aux = scan.nextLine();
                    user.setNmUsuario(aux);

                    String cpf;
                    boolean cpfValido = false;

                    while (!cpfValido) {
                        System.out.println("Agora o seu CPF. Exemplo(12345678911)");
                        cpf = scan.nextLine();

                        if (cpf.length() == 11 && cpf.chars().allMatch(Character::isDigit)) {
                            cpfValido = true;
                            user.setUsCPF(Long.parseLong(cpf));
                        } else {
                            System.out.println("CPF inválido. Por favor, insira um CPF com 11 números.");
                        }
                    }

                    System.out.println("Agora o seu email: ");
                    aux = scan.nextLine();
                    user.setEmailUsuario(aux);

                    System.out.println("Agora, por último, sua senha por favor");
                    aux = scan.nextLine();
                    user.setSenhaUsuario(aux);

                    System.out.println(userDAO.inserir(user));

                    System.out.println("""
                            Sua conta foi criada com sucesso, mas temos mais algumas recomendações
                            Gostaria de informar sua localidade?\n
                            [1]Sim
                            [2]Não""");
                    auxN = scan.nextInt();

                    if (auxN == 1) {
                        Estado est = new Estado();
                        EstadoDAO estDAO = new EstadoDAO(con);
                        aux = scan.nextLine();
                        System.out.println("""
                                Para informar sua localidade iremos pedir algumas informações \nPrimeiramente, qual o nome do seu Estado de moradia?""");

                        aux = scan.nextLine();
                        est.setNmEstado(aux);


                        System.out.println("E a sigla dele?");

                        aux = scan.nextLine();
                        est.setSgEstado(aux);

                        int novoEstadoID = estDAO.obterNovoIdEstado(con);
                        est.setIdEstado(novoEstadoID);

                        estDAO.inserir(est);

                        Cidade cid = new Cidade();
                        CidadeDAO cidDAO = new CidadeDAO(con);
                        int novoIdCidade = cidDAO.obterNovoIdCidade(con);

                        System.out.println("Agora o nome da sua cidade");

                        cid.setIdCidade(novoIdCidade);
                        cid.setIdEstado(est.getIdEstado());

                        aux = scan.nextLine();

                        cid.setNmCidade(aux);

                        while (true) {
                            System.out.println("E agora o DDD da sua cidade");
                            auxN = scan.nextInt();

                            if (auxN > 99) {
                                System.out.println("DDD incorreto, por favor coloque um DDD abaixo de 99");
                            } else {
                                cid.setNrDDD(auxN);
                                break;
                            }
                        }

                        cidDAO.inserir(cid);

                        aux = scan.nextLine();


                        Bairro brr = new Bairro();
                        BairroDAO brrDAO = new BairroDAO(con);
                        System.out.println("O nome do seu bairro");

                        int novoIdBairro = brrDAO.obterNovoIdBairro(con);

                        brr.setIdBairro(novoIdBairro);
                        brr.setIdCidade(cid.getIdCidade());

                        aux = scan.nextLine();  // Lê o nome do bairro
                        brr.setNmBairro(aux);

                        System.out.println("E agora o nome da zona do bairro");
                        aux = scan.nextLine();  // Lê o nome da zona
                        brr.setNmZonaBairro(aux);

                        brrDAO.inserir(brr);

                        Logradouro lg = new Logradouro();
                        LogradouroDAO lgDAO = new LogradouroDAO(con);

                        System.out.println("Agora, vamos finalmente para a última parte, as informações do seu logradouro" +
                                "\nPrimeiro informe o nome da sua rua");

                        int novoIdLogradouro = lgDAO.obterNovoIdLogradouro(con);
                        lg.setIdLogradouro(novoIdLogradouro);
                        lg.setIdBairro(brr.getIdBairro());
                        lg.setIdUsuario(user.getIdUsuario());

                        aux = scan.nextLine();
                        lg.setNmLogradouro(aux);

                        System.out.println("O número da sua casa");
                        auxN = scan.nextInt();
                        lg.setNrLogradouro(auxN);
                        scan.nextLine(); // Consome newline residual

                        System.out.println("E por final o seu cep. Exemplo: 06666500");
                        auxN = scan.nextInt();

                        while (true) {
                            if (auxN > 99999999) {
                                System.out.println("Por favor insira um CEP válido");
                            } else {
                                lg.setNrCep(auxN);
                                break;
                            }
                        }
                        lgDAO.inserir(lg);
                        scan.close();

                        System.out.println("Cadastro do usuário completo, e agora, gostaria de adicionar um carro e verificar um problema nele?\n[1]Sim[2]Não");
                        auxN = scan.nextInt();

                        if (auxN == 1) {


                        }

                    }
                }


                if (menu == 2) {
                    try {
                        System.out.println("OK, como você não tem conta, gostaria de saber o que nosso site pode fazer?\n[1]Sim\n[2]Voltar ao menu principal\n[3]Sair");
                        auxN = scan.nextInt();

                        if (auxN == 1) {
                            System.out.println("Nosso site possui um chatbot onde você informa o problema que o seu carro está tendo, com a peça e o sintoma e ele"
                                    + " gerará um orçamento com base na peça");
                        } else if (auxN == 2) {
                            menu = 0;
                        } else if (auxN == 3) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }


            }

        } catch(Exception e){
            System.out.println("Alguma informação está errada, tente novamente");
        }
    }
}
