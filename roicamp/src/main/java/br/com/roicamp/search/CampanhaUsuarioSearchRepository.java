package br.com.roicamp.search;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.Campanha;
import br.com.roicamp.model.CampanhaUsuario;

@Repository
public class CampanhaUsuarioSearchRepository extends BaseSearchRepository {

	@SuppressWarnings("unchecked")
	public List<CampanhaUsuario> findUsuarioCampanhasByUsuario(Long usuarioId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From CampanhaUsuario");
		hql.append(" where baseModel.dataInativacao is null");
		hql.append("   and usuario.id = :usuarioId");

		Query query = getQuery(hql.toString());
		query.setLong("usuarioId", usuarioId);
		return (List<CampanhaUsuario>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<CampanhaUsuario> findUsuarioCampanhasByCampanha(Long campanhaId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From CampanhaUsuario ");
		hql.append(" where baseModel.dataInativacao is null");
		hql.append("   and campanha.id = :campanhaId");

		Query query = getQuery(hql.toString());
		query.setLong("campanhaId", campanhaId);
		return (List<CampanhaUsuario>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Campanha> findCampanhasProgramadasNaoAssociadas(Long timeId, Long usuarioId) {
		StringBuilder hql = new StringBuilder();
		hql.append("   From Campanha cmp");
		hql.append("  where cmp.baseModel.dataInativacao is null");
		hql.append("    and cmp.fimVigencia >= :fimVigencia");
		hql.append("    and cmp.timeId = :timeId");
		hql.append("    and cmp.id not in");
		hql.append("    (");
		hql.append("       Select cmpusu.campanha.id");
		hql.append("         From CampanhaUsuario cmpusu");
		hql.append("        where cmpusu.usuario.id = :usuarioId");
		hql.append("    )");

		Query query = getQuery(hql.toString());
		query.setLong("timeId", timeId);
		query.setLong("usuarioId", usuarioId);
		query.setDate("fimVigencia", new Date());
		return (List<Campanha>) query.list();
	}

}
