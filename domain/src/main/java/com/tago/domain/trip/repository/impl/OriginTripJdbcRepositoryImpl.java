package com.tago.domain.trip.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class TripJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    public void saveAll(List<CreateTripCommand> commands) {
        String SQL = "INSERT INTO trip(primary key, name, date_time, meet_place, total_time, max_cnt, current_cnt, " +
                "same_gender, same_age, is_pet, member_id, origin, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";

        jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                }

                @Override
                public int getBatchSize() {
                    return 0;
                }
            }
        )
    }
}
