import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
        private List<Vaga> vagas;
    
    //Construtor para estacionamento, com vagas que será uma ArrayList
    public Estacionamento() {
        vagas = conexaobd.puxarVagas(); 
    }
    public List<Vaga> getVagas() {
        return new ArrayList<>(vagas); 
    }

    // Metodo para cadastrar uma vaga
    public void cadastrarVaga(int numero, String tipoVeiculo) {
        for (Vaga vaga : vagas){
            if (vaga.getNumero() == numero){
                System.out.println("O número da vaga já é de uma vaga existente, digite um número diferente.");
                return;
            }
        }
        if (tipoVeiculo.equalsIgnoreCase("moto") || 
        tipoVeiculo.equalsIgnoreCase("carro") || 
        tipoVeiculo.equalsIgnoreCase("caminhão")) {
            Vaga vaga = new Vaga (numero, tipoVeiculo);
            vagas.add(vaga);
            System.out.println("Vaga cadastrada com sucesso"); 
            conexaobd.inserirVaga(numero, tipoVeiculo, "", "livre");
    }
    else {
        System.out.println("Digite um tipo de veículo válido!");
    }
}
    //Registrar entrada dos veiculos e saber se a vaga esta ocupada
    public void registrarEntrada(int numero, String placaVeiculo, String tipoEntrada) {
        for (Vaga vaga : vagas) {
            if (vaga.getNumero() == numero) {
                // Ocupa a vaga com a placa do veículo
                vaga.ocuparVaga(placaVeiculo);
                
                // Atualiza a vaga no banco de dados
                conexaobd.atualizarVaga(numero, tipoEntrada, placaVeiculo, "ocupada");
                
                System.out.println("Veículo registrado com sucesso na vaga " + numero + ".");
                return;
            }
        }
        System.out.println("Vaga não encontrada ou já ocupada.");
    }
    
//registrar saida de um veiculo, liberando a vaga
public void registrarSaida(int numero) {
    for (Vaga vaga : vagas) {
        if (vaga.getNumero() == numero && vaga.getStatus().equals("ocupada")) {
            // Liberando a vaga na lista de objetos
            vaga.liberarVaga();

            // Atualizando a vaga no banco de dados
            conexaobd.atualizarVaga(numero, vaga.getTipoVeiculo(), "", "livre");

            System.out.println("Saída registrada e vaga " + numero + " liberada.");
            return;
        }
    }
    System.out.println("Vaga não encontrada ou já está livre.");
}

//consultar as vagas que estao disponiveis
public void consultarVagasLivres(String tipoVeiculo) {
    if (tipoVeiculo.equalsIgnoreCase("carro") || tipoVeiculo.equalsIgnoreCase("moto") || tipoVeiculo.equalsIgnoreCase("caminhão")) {
        System.out.println("Vagas disponíveis para " + tipoVeiculo + ":");
        boolean encontrouVaga = false;  // Flag para verificar se encontrou alguma vaga

        // Percorrendo todas as vagas
        for (Vaga vaga : vagas) {
            if (vaga.getTipoVeiculo().equalsIgnoreCase(tipoVeiculo) && vaga.getStatus().equals("livre")) {
                System.out.println("Vaga número " + vaga.getNumero());
                encontrouVaga = true;  // Marca que encontrou pelo menos uma vaga
            }
        }

        // Se não encontrou nenhuma vaga
        if (!encontrouVaga) {
            System.out.println("Nenhuma vaga disponível para " + tipoVeiculo + ".");
        }

    } else {
        System.out.println("Não existe um veículo com esse nome.");
    }
}

//Listar todas as vagas, preenchidas ou livres
public void listarTodasVagas() {
    System.out.println("Lista de todas as vagas:");
    for (Vaga vaga : vagas) {
        System.out.println("Vaga: " + vaga.getNumero() + " - Status: " + vaga.getStatus() + " - Tipo de veículo: " + vaga.getTipoVeiculo());
        if ("ocupada".equals(vaga.getStatus())){
            System.out.println("Placa do veículo: " + vaga.getPlacaVeiculo() + "\n");
        }
    }
}

}