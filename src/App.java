import java.sql.Connection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();

            Connection conn = conexaobd.conectar();
        if (conn != null) {
            System.out.println("Conexão bem-sucedida!");
        }

    

        while (true) {
            System.out.println("\n----------------------------------------------------------------");
            System.out.println("Sistema de Gerenciamento de Estacionamento, escolha uma opção:");
            System.out.println("1. Cadastrar Vaga");
            System.out.println("2. Registrar Entrada de Veículo");
            System.out.println("3. Registrar Saída de Veículo");
            System.out.println("4. Consultar Vagas Disponíveis por Tipo de Veículo");
            System.out.println("5. Listar Todas as Vagas");
            System.out.println("0. Sair");

            int opcao = 7;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine(); // Limpar entrada inválida
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Número da vaga: ");
                    int numero = 0;
                    try {
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                        String tipoVeiculo = scanner.nextLine();
                        estacionamento.cadastrarVaga(numero, tipoVeiculo);
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar vaga. Digite um número válido!");
                        scanner.nextLine(); // Limpar entrada inválida
                    }
                    break;

                case 2:
                    System.out.print("Digite o número da vaga: ");
                    int numeroVaga = 9;
                    try {
                        numeroVaga = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Digite um número inteiro.");
                        scanner.nextLine(); // Limpar entrada inválida
                        break;
                    }

                    boolean entradaRegistrada = false; // Controle para evitar fluxo indesejado
                    for (Vaga vaga : estacionamento.getVagas()) {
                        if (vaga.getNumero() == numeroVaga && vaga.getStatus().equals("livre")) {
                            String tipoVeiculo = "";
                            System.out.println("Digite o tipo de veículo que deseja cadastrar:");
                            try {
                                tipoVeiculo = scanner.nextLine();
                            } catch (Exception e) {
                                System.out.println("Tipo de veículo inválido.");
                                break;
                            }

                            if (!vaga.getTipoVeiculo().equalsIgnoreCase(tipoVeiculo)) {
                                System.out.println("Digite o tipo de veículo igual ao que a vaga aceita para confirmação!");
                                break;
                            }

                            System.out.println("Digite a placa do veículo:");
                            String placaVeiculo;
                            try {
                                placaVeiculo = scanner.nextLine();
                            } catch (Exception e) {
                                System.out.println("Digite uma placa válida!");
                                break;
                            }
                            estacionamento.registrarEntrada(numeroVaga, placaVeiculo, tipoVeiculo);
                            entradaRegistrada = true; // Marca que a entrada foi registrada
                            break;
                        } else {
                            System.out.println("Vaga não existente ou ocupada!");
                        }
                    }

                    if (!entradaRegistrada) {
                        System.out.println("Não foi possível registrar a entrada.");
                    }
                    break; // Sai do `case 2`

                case 3:
                    System.out.print("Número da vaga para liberar: ");
                    try {
                        int numeroSaida = scanner.nextInt();
                        estacionamento.registrarSaida(numeroSaida);
                    } catch (Exception e) {
                        System.out.println("Erro ao registrar saída. Digite um número válido!");
                        scanner.nextLine(); // Limpar entrada inválida
                    }
                    break;

                case 4:
                String tipoConsulta;
                    System.out.print("Tipo de veículo (carro, moto, caminhão): ");
                    try {
                     tipoConsulta = scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("Digite um tipo de veículo válido!");
                        break;
                    }
                    estacionamento.consultarVagasLivres(tipoConsulta);
                    break;

                case 5:
                    estacionamento.listarTodasVagas();
                    break;

                case 0:
                    System.out.println("Saindo do sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}