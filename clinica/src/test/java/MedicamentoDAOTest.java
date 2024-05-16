import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dao.implementations.MedicamentoDAOImpl;
import dao.interfaces.MedicamentoDAO;
import models.Medicamento;

public class MedicamentoDAOTest {
    private MedicamentoDAO medicamentoDAO;

    @BeforeEach
    void setUp() {
        medicamentoDAO = new MedicamentoDAOImpl();
    }

    @Test
    @DisplayName("Guardar y buscar un medicamento por ID")
    void saveAndFindById() {
        Medicamento medicamento = new Medicamento("Paracetamol", 100);
        medicamentoDAO.save(medicamento);

        Medicamento medicamentoRecuperado = medicamentoDAO.findById(medicamento.getId());
        assertEquals(medicamento, medicamentoRecuperado);
    }

    @Test
    @DisplayName("Buscar medicamentos por nombre")
    void findByNombre() {
        medicamentoDAO.save(new Medicamento("Paracetamol", 100));
        medicamentoDAO.save(new Medicamento("Ibuprofeno", 50));
        medicamentoDAO.save(new Medicamento("Paracetamol", 200));

        List<Medicamento> paracetamol = medicamentoDAO.findByNombre("Paracetamol");
        assertEquals(2, paracetamol.size());

        List<Medicamento> ibuprofeno = medicamentoDAO.findByNombre("Ibuprofeno");
        assertEquals(1, ibuprofeno.size());

        List<Medicamento> aspirina = medicamentoDAO.findByNombre("Aspirina");
        assertEquals(0, aspirina.size());
    }

    @Test
    @DisplayName("Buscar medicamentos por cantidad")
    void findByCantidad() {
        medicamentoDAO.save(new Medicamento("Paracetamol", 100));
        medicamentoDAO.save(new Medicamento("Ibuprofeno", 50));
        medicamentoDAO.save(new Medicamento("Paracetamol", 200));

        List<Medicamento> medicamentosConCantidad100 = medicamentoDAO.findByCantidad(100);
        assertEquals(1, medicamentosConCantidad100.size());

        List<Medicamento> medicamentosConCantidad50 = medicamentoDAO.findByCantidad(50);
        assertEquals(1, medicamentosConCantidad50.size());

        List<Medicamento> medicamentosConCantidad300 = medicamentoDAO.findByCantidad(300);
        assertEquals(0, medicamentosConCantidad300.size());
    }

    @Test
    @DisplayName("Buscar todos los medicamentos")
    void findAll() {
        medicamentoDAO.save(new Medicamento("Paracetamol", 100));
        medicamentoDAO.save(new Medicamento("Ibuprofeno", 50));
        medicamentoDAO.save(new Medicamento("Paracetamol", 200));

        List<Medicamento> medicamentos = medicamentoDAO.findAll();
        assertEquals(3, medicamentos.size());
    }

    @Test
    @DisplayName("Actualizar un medicamento")
    void update() {
        Medicamento medicamento = new Medicamento("Paracetamol", 100);
        medicamentoDAO.save(medicamento);

        medicamento.setCantidad(200);
        medicamentoDAO.update(medicamento);

        Medicamento medicamentoActualizado = medicamentoDAO.findById(medicamento.getId());
        assertEquals(200, medicamentoActualizado.getCantidad());
    }

    @Test
    @DisplayName("Eliminar un medicamento")
    void delete() {
        Medicamento medicamento = new Medicamento("Paracetamol", 100);
        medicamentoDAO.save(medicamento);

        medicamentoDAO.delete(medicamento.getId());

        Medicamento medicamentoEliminado = medicamentoDAO.findById(medicamento.getId());
        assertNull(medicamentoEliminado);
    }
}
