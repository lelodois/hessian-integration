package br.com.roicamp.search;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.roicamp.model.Usuario;

@Repository
public class UsuarioSearchRepository extends BaseSearchRepository {

	public Usuario findOneUsuarioByEmail(String email) {
		StringBuilder hql = new StringBuilder();
		hql.append(" From Usuario usu");
		hql.append(" where usu.baseModel.dataInativacao is null");
		hql.append("   and upper(usu.email) = :email");

		Query query = getQuery(hql.toString());
		query.setString("email", email.toUpperCase());
		return (Usuario) query.uniqueResult();
	}

}
