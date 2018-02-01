package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ef.bo.RequestBO;
import com.ef.util.DateUtil;

public class RequestDao {

	private boolean insert(RequestBO request, Connection conn) {

		String sql = "INSERT INTO REQUESTS ( IP, REQUEST_DATE, REQUEST, STATUS, USER_AGENT) values (? , ? , ? , ? , ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, request.getIp());
			stmt.setTimestamp(2, DateUtil.convertStringToTimestamp(request.getDate()));
			stmt.setString(3, request.getRequest());
			stmt.setString(4, request.getStatus());
			stmt.setString(5, request.getUserAgent());

			int id = stmt.executeUpdate();

			return id > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean insert(RequestBO request) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			return insert(request, conn);

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public void insert(List<RequestBO> requestList) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			requestList.forEach(r -> insert(r, conn));

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
