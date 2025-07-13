package med.voll.api.domain.Consulta.Validaciones;

import med.voll.api.domain.Consulta.DatosReservarConsulta;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas{

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservarConsulta  datos){
        //La eleccion de un medico es opcional, puede que este if no se utilize
        if(datos.idMedico() == null){
            return;
        }

        var medicoEstaActivo = repository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser selecinado con un medico no activo!");
        }
    }
}
