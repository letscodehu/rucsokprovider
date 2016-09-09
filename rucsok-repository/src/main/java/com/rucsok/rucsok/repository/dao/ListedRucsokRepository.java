package com.rucsok.rucsok.repository.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ListedRucsokRepository {

	private EntityManager em;

	@Autowired
	public ListedRucsokRepository(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getHotRucsok(String startDate, String endDate, int limit) {

		String queryStr =

				"SELECT COALESCE(up_vs.up_vote_number, 0), COALESCE(down_vs.down_vote_number, 0), id, title, link, imageUrl, videoUrl, createdAt "
						+ "FROM Rucsok "

						+ "LEFT JOIN (SELECT uv.rucsok_id, SUM(CASE WHEN (uv.vote = 'UP') THEN 1 ELSE 0 END) AS up_vote_number FROM Vote uv GROUP BY uv.rucsok_id) AS up_vs "
						+ "ON Rucsok.id = up_vs.rucsok_id "

						+ "LEFT JOIN (SELECT dv.rucsok_id, SUM(CASE WHEN (dv.vote = 'DOWN') THEN -1 ELSE 0 END) AS down_vote_number FROM Vote dv GROUP BY dv.rucsok_id) AS down_vs "
						+ "ON Rucsok.id = down_vs.rucsok_id "

						+ "WHERE Rucsok.createdAt BETWEEN :startDate AND :endDate "
						+ "ORDER BY up_vs.up_vote_number DESC, down_vs.down_vote_number DESC, Rucsok.createdAt DESC LIMIT 0, :limit";
		
		Query query = em.createNativeQuery(queryStr);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("limit", limit);
		return query.getResultList();
	}

}
