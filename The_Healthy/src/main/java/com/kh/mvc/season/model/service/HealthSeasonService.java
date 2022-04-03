package com.kh.mvc.season.model.service;

import static com.kh.common.jdbc.JDBCTemplate.close;
import static com.kh.common.jdbc.JDBCTemplate.commit;
import static com.kh.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.season.api.HealthSeasonAPI;
import com.kh.mvc.season.model.dao.HealthSeasonDao;
import com.kh.mvc.season.model.vo.HealthSeason;
import com.kh.common.util.PageInfo;

public class HealthSeasonService {
	private HealthSeasonDao dao = new HealthSeasonDao();

	public HealthSeasonService() {
		if (getHealthSeasonCount() < 1) {
			HealthSeasonAPI api = new HealthSeasonAPI();
			List<HealthSeason> hsList = api.callHealthSeasonListByJSON();
			insert(hsList);
		}
	}

	private int insert(List<HealthSeason> hsList) {
		Connection conn = getConnection();
		int result = 0;

		result = dao.insertSeasonFood(conn, hsList);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);
		return result;
	}

	private int getHealthSeasonCount() {
		Connection conn = getConnection();
		int result = dao.getHealthSeasonCount(conn);
		close(conn);
		return result;
	}

	public HealthSeason findHealthSeasonByNo(int no) {
		Connection conn = getConnection();
		HealthSeason hs = dao.findHealthSeasonByNo(conn, no);
		close(conn);
		return hs;
	}

	public List<HealthSeason> getHealthSeasonList(PageInfo pageinfo, String month) {
		Connection conn = getConnection();
		List<HealthSeason> list;
		list = dao.findAll(conn, pageinfo, month);
		close(conn);
		return list;
	}
	
	public int getHealthSeasonCount(String month) {
		Connection conn = getConnection();
		int result = dao.getHealthSeasonCount(conn, month);
		close(conn);
		return result;
	}

	public List<HealthSeason> getHealthSeasonMonthList(String month) {
		Connection conn = getConnection();
		List<HealthSeason> list;
		list = dao.findAll(conn, month);
		close(conn);
		return list;
	}
}
