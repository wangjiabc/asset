package com.voucher.manage.daoModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoomInfoRowMapper implements RowMapper<RoomInfo> {
    //rs为返回结果集，以每行为单位封装着
    public RoomInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        RoomInfo roomInfo=new RoomInfo();
        roomInfo.setGUID(rs.getString("GUID"));
        roomInfo.setNum(rs.getString("Num"));
        roomInfo.setOriginalNum(rs.getString("OriginalNum"));
        roomInfo.setAddress(rs.getString("Address"));
        roomInfo.setOriginalAddress(rs.getString("OriginalAddress"));
        return roomInfo;
    }

}
