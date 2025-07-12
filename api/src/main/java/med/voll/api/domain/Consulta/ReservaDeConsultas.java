package med.voll.api.domain.Consulta;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void reservar(DatosReservarConsulta datos) {



        var medico = medicoRepository.findById(datos.idMedico()).get();
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, datos.fecha());

        consultaRepository.save(consulta);
    }
}
