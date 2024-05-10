package com.jimenez.app.rutas.services;

import com.jimenez.app.rutas.models.Camion;
import com.jimenez.app.rutas.models.Chofer;
import com.jimenez.app.rutas.models.Ruta;

import java.util.List;

public interface IRutasService extends IService<Ruta>{

    List<Camion> listarCamiones();
    List<Chofer> listarChoferes();
    Long guardarReturnId (Ruta ruta);

}
