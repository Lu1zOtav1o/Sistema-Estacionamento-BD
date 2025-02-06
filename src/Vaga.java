public class Vaga {
    private int numero;
    private String tipoVeiculo;
    private String status;  // "livre" ou "ocupada"
    private String placaVeiculo; // A placa do veículo, ou "" se estiver livre

    // Construtor para inicializar a vaga com número e tipo do veículo
    public Vaga(int numero, String tipoVeiculo) {
        this.numero = numero;
        this.tipoVeiculo = tipoVeiculo;
        this.status = "livre"; // Valor padrão ao criar a vaga
        this.placaVeiculo = ""; // Valor padrão
    }

    // Getters para acessar os atributos fora da classe
    public int getNumero() {
        return numero;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public String getStatus() {
        return status;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    // Setters para modificar o status e a placa diretamente
    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    // Método para ocupar a vaga com a placa do veículo
    public void ocuparVaga(String placa) {
        this.status = "ocupada";
        this.placaVeiculo = placa;
    }

    // Método para liberar a vaga
    public void liberarVaga() {
        this.status = "livre";
        this.placaVeiculo = ""; // Limpa a placa
    }
}
