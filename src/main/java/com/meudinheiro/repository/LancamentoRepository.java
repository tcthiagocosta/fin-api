package com.meudinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meudinheiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
	
	List<Lancamento> findAllByOrderByIdAsc();
	
	@Query("SELECT l FROM Lancamento l join Transferencia t  on l.transferencia = t "
			+ " WHERE t.id = :idTransferencia and l.tipo = :tipo")
	Lancamento findByTransferenciaAndTipo(@Param("idTransferencia") Integer idTransferencia, @Param("tipo") String tipo);
	
//	@Query("delete from Lancamento l join Transferencia t  on l.transferencia = t "
//			+ " WHERE t.id = :idTransferencia")
//	void deleteByTransferenciaId(@Param("idTransferencia") Integer idTransferencia);
	
	List<Lancamento> findAllByTransferenciaId(Integer id);
}
