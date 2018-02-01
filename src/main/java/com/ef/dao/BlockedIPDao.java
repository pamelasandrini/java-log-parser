package com.ef.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlockedIPDao {

	private boolean insert(String ip, String blockReason, Connection conn) {

		String sql = "INSERT INTO BLOCKED_IPS ( IP, BLOCK_REASON) values (? , ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, ip);
			stmt.setString(2, blockReason);

			int id = stmt.executeUpdate();

			return id > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean insert(String ip, String blockReason) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			return insert(ip, blockReason, conn);

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

}
