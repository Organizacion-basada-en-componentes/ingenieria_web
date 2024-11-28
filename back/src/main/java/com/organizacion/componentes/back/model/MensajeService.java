package com.organizacion.componentes.back.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Método para guardar un mensaje
    public Mensaje saveMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    // Método para obtener todos los mensajes
    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    // Método para obtener los mensajes de un paciente específico
    public List<Mensaje> getMensajesByPaciente(Long pacienteId) {
        // Este método puede ser personalizado en el repositorio
        return mensajeRepository.findByPacienteId(pacienteId); // Asumiríamos que este método se implementa en el
                                                               // repositorio
    }

    // Método para obtener los mensajes de un médico específico
    public List<Mensaje> getMensajesByMedico(Long medicoId) {
        // Este método también puede ser personalizado en el repositorio
        return mensajeRepository.findByMedicoId(medicoId); // Asumiríamos que este método se implementa en el
                                                           // repositorio
    }

    // Método para obtener un mensaje por ID
    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajeRepository.findById(id);
    }

    // Método para eliminar un mensaje
    public void deleteMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }
}
