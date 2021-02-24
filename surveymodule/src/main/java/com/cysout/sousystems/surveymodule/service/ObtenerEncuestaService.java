package com.cysout.sousystems.surveymodule.service;

import com.cysout.sousystems.surveymodule.entity.Encuesta;

public interface ObtenerEncuestaService {
    Boolean guardarEncuesta(String jsonEncuesta);
    Encuesta obtenerEncuesta();
}
