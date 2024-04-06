
import dao.PacienteDAO;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modelo.Paciente;
import org.junit.Test;

public class TesteHospitalar {

    @Test
    public void testCadastrarPaciente() {
        try {
            Date dataNascimento = new Date(100, 0, 1);
            Paciente pac = new Paciente(0, "Paciente teste", "Endereco Teste", dataNascimento, "(19) 99999-9999", "000.000.000-00", "000000-0", "teste@teste.com", 1);
            PacienteDAO cadastro = new PacienteDAO();

            // Tentar cadastrar o paciente
            cadastro.cadastrarPaciente(pac);

            // Se não houve exceção, o paciente foi cadastrado com sucesso
            assertTrue("O paciente não foi cadastrado com sucesso", true);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    @Test
    public void testCamposObrigatorios() {
        try {
            Date dataNascimento = new Date(100, 0, 1);
            Paciente pac = new Paciente(0, "Paciente teste", "Endereco Teste", dataNascimento, "(19) 99999-9999", "000.000.000-00", null, null, 1);
            PacienteDAO cadastro = new PacienteDAO();

            // Tentar cadastrar o paciente
            cadastro.cadastrarPaciente(pac);

            // Se não houve exceção, o paciente foi cadastrado com sucesso
            assertTrue("O paciente não foi cadastrado com sucesso", true);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    @Test
    public void testColetarPacientes() {
        try {
            PacienteDAO cadastro = new PacienteDAO();
            ArrayList<Paciente> pacientes = cadastro.buscarPaciente();

            // Verificar se a lista de pacientes não está vazia
            assertFalse("A lista de pacientes está vazia", pacientes.isEmpty());

            assertTrue("A lista de pacientes não contém pacientes", pacientes.size() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erro ao coletar paciente: " + e.getMessage());
        }
    }
    
    @Test
    public void testBuscarPacientePorNome() {
        // Cadastre o paciente de teste
        try {
            PacienteDAO cadastro = new PacienteDAO();
            String nome = "Paciente teste";
            String query = "where NOME like '%" + nome + "%'";
            
            // Buscar pacientes pelo nome
            ArrayList<Paciente> pacientes = cadastro.buscarPacienteFiltro(query);

            // Verificar se a lista de pacientes não está vazia
            assertFalse("A lista de pacientes está vazia", pacientes.isEmpty());

            // Verificar se a lista de pacientes contém o paciente inserido
            boolean pacienteEncontrado = false;
            for (Paciente paciente : pacientes) {
                if (paciente.getNome().equals(nome)) {
                    pacienteEncontrado = true;
                    break;
                }
            }
            assertTrue("Paciente não encontrado na lista", pacienteEncontrado);
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erro ao buscar paciente: " + e.getMessage());
        }
    }
    
      @Test
    public void testBuscarTodosDadosPaciente() {
        try {
            // Teste para verificar se todos os dados de um paciente são recuperados corretamente
            PacienteDAO cadastro = new PacienteDAO();
            String nome = "Paciente teste";
            String query = "where NOME like '%" + nome + "%'";
            
            // Buscar pacientes pelo nome
            ArrayList<Paciente> pacientes = cadastro.buscarPacienteFiltro(query);

            // Verificar se a lista de pacientes não está vazia
            assertFalse("A lista de pacientes está vazia", pacientes.isEmpty());

            // Verificar se os dados do paciente não estão vazios
            for (Paciente paciente : pacientes) {
                assertNotNull("Nome do paciente está vazio", paciente.getNome());
                assertNotNull("Endereço do paciente está vazio", paciente.getEndereco());
                assertNotNull("Data de nascimento do paciente está vazia", paciente.getDataNascimento());
                assertNotNull("Telefone do paciente está vazio", paciente.getTelefone());
                assertNotNull("CPF do paciente está vazio", paciente.getCpf());
                assertNotNull("RG do paciente está vazio", paciente.getRg());
                assertNotNull("Email do paciente está vazio", paciente.getEmail());
                assertNotNull("ID do convênio do paciente está vazio", paciente.getIdConvenio());
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Erro ao buscar todos os dados do paciente: " + e.getMessage());
        }
    }
    
}
