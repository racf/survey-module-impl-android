package com.cysout.sousystems.surveymodule.service;

import com.cysout.sousystems.surveymodule.entity.Survey;

public interface ObtenerEncuestaService {
    Boolean guardarEncuesta(String jsonEncuesta);
    Survey obtenerEncuesta();
}
