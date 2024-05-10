package com.jimenez.app.rutas.repositories;

import com.jimenez.app.rutas.models.Cargamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CargamentoRepository implements IRepository<Cargamento> {
    private Connection conn;

    public CargamentoRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Cargamento> listar() throws SQLException {
        return null;
    }

    @Override
    public Cargamento getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Cargamento cargamento) throws SQLException {
        String sql="";
        sql = "insert into cargamentos (ID_CARGAMENTO, ruta_id, descripcion," +
                "peso, estatus)" +
                "values (SEQUENCE5.NEXTVAL,?,?,?,?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, cargamento.getRutaId());
            stmt.setString(2,cargamento.getDescripcion());
            stmt.setDouble(3, cargamento.getPeso());
            stmt.setLong(4, 0);
            stmt.executeUpdate();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
