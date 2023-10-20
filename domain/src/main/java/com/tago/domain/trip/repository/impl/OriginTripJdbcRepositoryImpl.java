package com.tago.domain.trip.repository.impl;

import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.dto.OriginTripPlaceCreateDto;
import com.tago.domain.trip.repository.OriginTripJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OriginTripJdbcRepositoryImpl implements OriginTripJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Override
    public void saveAllTrip(List<OriginTripCreateDto> commands) {
        String TRIP_SQL = "INSERT INTO trip(name, date_time, meet_place, total_time, max_cnt, current_cnt, " +
                "same_gender, same_age, is_pet, origin, member_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, current_timestamp)";
        jdbcTemplate.batchUpdate(TRIP_SQL, new BatchPreparedStatementSetter() {
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

    @Override
    public void saveAllTripPlace(List<OriginTripPlaceCreateDto> commands) {
        String TRIP_PLACE_SQL = "INSERT INTO trip_place(`order`, trip_id, place_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, current_timestamp, current_timestamp)";

        jdbcTemplate.batchUpdate(TRIP_PLACE_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                OriginTripPlaceCreateDto command = commands.get(i);
                ps.setInt(1, command.getOrder());
                ps.setLong(2, command.getTripId());
                ps.setLong(3, command.getPlaceId());
            }

            @Override
            public int getBatchSize() {
                return commands.size();
            }
        });
    }

    @Override
    public Long lastInsertId() {
        return jdbcTemplate.queryForObject("SELECT last_insert_id()", Long.class);
    }
}
