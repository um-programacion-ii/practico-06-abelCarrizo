package dao.implementations;

import dao.interfaces.ObraSocialDAO;
import models.ObraSocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObraSocialDAOImpl implements ObraSocialDAO {
    private final Map<Integer, ObraSocial> obraSociales = new HashMap<>();

    @Override
    public List<ObraSocial> findByNombre(String nombre) {
        List<ObraSocial> obraSocialWithNombre = new ArrayList<>();
        for (ObraSocial obraSocial : obraSociales.values()) {
            if (obraSocial.getNombre().equals(nombre)) {
                obraSocialWithNombre.add(obraSocial);
            }
        }
        return obraSocialWithNombre;
    }

    @Override
    public List<ObraSocial> findAll() {
        return new ArrayList<>(obraSociales.values());
    }

    @Override
    public ObraSocial findById(int id) {
        return obraSociales.get(id);
    }

    @Override
    public void save(ObraSocial obraSocial) {
        System.out.println("La obra social " + obraSocial.getNombre() + " ha sido registrada");
        obraSociales.put(obraSocial.getId(), obraSocial);
    }

    @Override
    public void update(ObraSocial obraSocial) {
        System.out.println("Obra Social " + obraSocial.getNombre() + " ha sido modificada");
        obraSociales.put(obraSocial.getId(), obraSocial);
    }

    @Override
    public void delete(int id) {
        System.out.println("Obra social: " + obraSociales.get(id).getNombre() + " eliminada");
        obraSociales.remove(id);
    }
}
