import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class conexaobd {

    private static final String URL = "jdbc:mysql://localhost:3306/estacionamento_bd";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // Método para conectar ao banco de dados
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    // Método para inserir uma nova vaga no banco de dados
    public static void inserirVaga(int numeroVaga, String tipo, String placaVeiculo, String status) {
        String sql = "INSERT INTO vaga (numero_vaga, tipo, status, placa_veiculo) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexaobd.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Setando os parâmetros
            stmt.setInt(1, numeroVaga);
            stmt.setString(2, tipo);
            stmt.setString(3, status);
            stmt.setString(4, placaVeiculo);

            // Executando a inserção
            stmt.executeUpdate();
            System.out.println("Vaga inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar os dados de uma vaga no banco
    public static void atualizarVaga(int numeroVaga, String tipo, String placaVeiculo, String status) {
        String sql = "UPDATE vaga SET tipo = ?, status = ?, placa_veiculo = ? WHERE numero_vaga = ?";
        
        try (Connection conn = conexaobd.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            
            stmt.setString(1, tipo);
            stmt.setString(2, status); 
            stmt.setString(3, placaVeiculo); 
            stmt.setInt(4, numeroVaga);
            
            
            stmt.executeUpdate();
            System.out.println("Vaga " + numeroVaga + " atualizada com sucesso.");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public static List<Vaga> puxarVagas() {
        List<Vaga> vagas = new ArrayList<>();
        String sql = "SELECT * FROM vaga";
        
        try (Connection conn = conexaobd.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                int numeroVaga = rs.getInt("numero_vaga");
                String tipo = rs.getString("tipo");
                String status = rs.getString("status");
                String placaVeiculo = rs.getString("placa_veiculo");
                
                Vaga vaga = new Vaga(numeroVaga, tipo);
                // Definindo os valores obtidos do banco
                vaga.setStatus(status);
                vaga.setPlacaVeiculo(placaVeiculo);
                
                vagas.add(vaga);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vagas;
    }
    
}
