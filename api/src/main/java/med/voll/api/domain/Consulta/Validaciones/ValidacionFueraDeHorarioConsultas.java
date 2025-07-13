package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;

import java.time.DayOfWeek;

public class ValidacionFueraDeHorarioConsultas {
    public void validar(DatosReservarConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeAperturaClinica = fechaConsulta.getHour() > 18;
        if (domingo || horarioDespuesDeAperturaClinica || horarioAntesDeAperturaClinica) {
            throw new ValidacionException("Horario selececionado no disponible");
        }
    }
}
