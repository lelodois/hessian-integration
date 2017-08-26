package br.com.roicamp.search;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.Campanha;

@Repository
public class CampanhaSearchRepository extends BaseSearchRepository {

	@SuppressWarnings("unchecked")
	public List<Campanha> findCampanhasVigentes(Campanha campanha) {
		StringBuilder hql = new StringBuilder();
		hql.append("  From Campanha cmp");
		hql.append(" where cmp.baseModel.dataInativacao is null");
		hql.append("   and cmp.timeId = :timeId");
		hql.append("   and cmp.id != :entityId");
		hql.append("   and (");
		hql.append("   		 	(:inicioVigencia >= cmp.inicioVigencia and :inicioVigencia <= cmp.fimVigencia)");
		hql.append("         or	(:fimVigencia    >= cmp.inicioVigencia and :fimVigencia    <= cmp.fimVigencia)");
		hql.append("       )");

		Query query = getQuery(hql.toString());
		query.setLong("entityId", campanha.getId());
		query.setLong("timeId", campanha.getTimeId());
		query.setDate("inicioVigencia", campanha.getInicioVigencia());
		query.setDate("fimVigencia", campanha.getFimVigencia());
		return (List<Campanha>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Campanha> findCampanhasAlteradas() {
		StringBuilder hql = new StringBuilder();
		hql.append("  From Campanha cmp");
		hql.append(" where cmp.baseModel.dataInativacao is null");
		hql.append("   and cmp.fimVigencia >= :fimVigencia");
		hql.append("   and cmp.baseModel.ultimaAlteracao >= :dataInicio");

		Query query = getQuery(hql.toString());
		query.setDate("fimVigencia", DateTime.now().toDate());
		query.setDate("dataInicio", DateTime.now().minusMonths(1).toDate());
		return (List<Campanha>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Campanha> findCampanhasProgramadas(Long timeId) {
		StringBuilder hql = new StringBuilder();
		hql.append("  From Campanha cmp");
		hql.append(" where cmp.baseModel.dataInativacao is null");
		hql.append("   and cmp.fimVigencia >= :fimVigencia");
		hql.append("   and cmp.timeId = :timeId");

		Query query = getQuery(hql.toString());
		query.setLong("timeId", timeId);
		query.setDate("fimVigencia", new Date());
		return (List<Campanha>) query.list();
	}

}
