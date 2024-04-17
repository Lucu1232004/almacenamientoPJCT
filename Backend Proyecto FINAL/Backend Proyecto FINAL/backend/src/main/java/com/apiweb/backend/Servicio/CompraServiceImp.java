package com.apiweb.backend.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Excepcion.RecursoNoEncontradoException;
import com.apiweb.backend.Modelo.CompraModel;
import com.apiweb.backend.Repositorio.ICompraRepository;

@Service
public class CompraServiceImp implements ICompraService {
@Autowired ICompraRepository compraRepository;

    @Override
    public String guardarCompra(CompraModel compra) {
        compraRepository.save(compra);
        return "La compra de Id: "+compra.getIdCompra()+
        " del usuario con Id: "+compra.getUsuarioId().getIdUsuario()+
        " por un monto total de: "+compra.getCostoTotal()+ " fue realizada con exito";
    }
    @Override
    public CompraModel buscarCompraPorId(Integer idCompra){
        Optional<CompraModel> compraEncontrada = compraRepository.findById(idCompra);
        return compraEncontrada.
        orElseThrow(()->new RecursoNoEncontradoException("Compra no encontrada con el ID "+idCompra));
    }

    @Override
public String actualizarCompra(CompraModel compra) {
    CompraModel existingCompra = buscarCompraPorId(compra.getIdCompra());
    if (existingCompra != null) {
        existingCompra.setCostoTotal(compra.getCostoTotal());  // Suponiendo que quieres actualizar este campo, entre otros posibles
        // Actualiza otros campos según sea necesario
        compraRepository.save(existingCompra);
        return "Compra actualizada con éxito con ID: " + existingCompra.getIdCompra();
    } else {
        throw new RecursoNoEncontradoException("Compra no encontrada con el ID " + compra.getIdCompra());
     }
    }
    @Override
public void eliminarCompra(Integer idCompra) {
    if (compraRepository.existsById(idCompra)) {
        compraRepository.deleteById(idCompra);
    } else {
        throw new RecursoNoEncontradoException("Compra no encontrada con el ID " + idCompra);
    }
    }
    @Override
    public List<CompraModel> listarTodasLasCompras() {
    return compraRepository.findAll();
    }



}
