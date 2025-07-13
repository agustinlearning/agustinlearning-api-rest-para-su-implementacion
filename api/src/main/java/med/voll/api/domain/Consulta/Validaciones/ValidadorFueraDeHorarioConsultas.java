package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraDeHorarioConsultas implements ValidadorDeConsultas{
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
