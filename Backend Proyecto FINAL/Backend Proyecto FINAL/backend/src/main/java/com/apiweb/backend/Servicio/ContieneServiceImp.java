package com.apiweb.backend.Servicio;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Modelo.ContieneModel;
import com.apiweb.backend.Repositorio.IContieneRepository;

@Service
public class ContieneServiceImp implements IContieneService {
@Autowired IContieneRepository contieneRepository;

    @Override
    public String guardarContiene(ContieneModel contiene) {
        contieneRepository.save(contiene);
        return "la informacion de cuanta cantidad del producto "+contiene.getProductoId().getIdProducto()+
        " contenidas en la compra "+contiene.getCompraId().getIdCompra()+" fue creada con exito";
    }
    @Override
    public ContieneModel buscarContienePorId(Long id) {
        Optional<ContieneModel> contieneOptional = contieneRepository.findById(id);
        return contieneOptional.orElse(null);
    }

    @Override
    public void actualizarContiene(Long idContiene , ContieneModel contiene) {
        contiene.setIdContiene(idContiene); // Asegúrate de establecer el ID correcto en el objeto antes de guardar
        contieneRepository.save(contiene);
    }

    @Override
    public void eliminarContiene(Long id) {
        contieneRepository.deleteById(id);
    }

    @Override
    public List<ContieneModel> listarContenciones() {
        return contieneRepository.findAll();
    }
    
  
}
