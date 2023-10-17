package com.tago.domain.trip.repository.impl;

import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.repository.OriginTripJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OriginTripJdbcRepositoryImpl implements OriginTripJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveAll(List<OriginTripCreateDto> commands) {
        String SQL = "INSERT INTO trip(name, date_time, meet_place, total_time, max_cnt, current_cnt, " +
                "same_gender, same_age, is_pet, origin, member_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";

        jdbcTemplate.batchUpdate(SQL, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    OriginTripCreateDto command = commands.get(i);
                    ps.setString(1, command.getName());
                    ps.setTimestamp(2, Timestamp.valueOf(command.getDateTime()));
                    ps.setString(3, command.getMeetPlace());
                    ps.setInt(4, command.getTotalTime());
                    ps.setInt(5, command.getMaxCnt());
                    ps.setInt(6, command.getCurrentCnt());
                    ps.setBoolean(7, command.getSameGender());
                    ps.setBoolean(8, command.getSameAge());
                    ps.setBoolean(9, command.getIsPet());
                    ps.setBoolean(10, command.getOrigin());
                    ps.setLong(11, command.getMemberId());
                }

                @Override
                public int getBatchSize() {
                    return commands.size();
                }
            }
        );
    }
}
