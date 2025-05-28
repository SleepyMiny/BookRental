package daelim.book.rental.minyoung.admin;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminAccountDao {
    private final BCryptPasswordEncoder passwordEncoder;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminAccountDao(DataSource dataSource, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public int insertAccount(AdminAccountVo adminAccountVo) {

        List<String> args = new ArrayList<>();
        String sql = "INSERT INTO TB_ADMIN_ACCOUNT (";
        if(adminAccountVo.getId().equals("system")) {
            sql += "approval, ";
            args.add("1");
        }

        sql += "id, ";
        args.add(adminAccountVo.getId());

        sql += "password, ";
//        args.add(adminAccountVo.getPassword());
        args.add(passwordEncoder.encode(adminAccountVo.getPassword()));

        sql += "name, ";
        args.add(adminAccountVo.getName());

        sql += "gender, ";
        args.add(adminAccountVo.getGender());

        sql += "part, ";
        args.add(adminAccountVo.getPart());

        sql += "position, ";
        args.add(adminAccountVo.getPosition());

        sql += "email, ";
        args.add(adminAccountVo.getEmail());

        sql += "phone, ";
        args.add(adminAccountVo.getPhone());

        sql += "regDate, modDate) ";

        if (adminAccountVo.getId().equals("system")) {
            sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
        } else {
            sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW() )";
        }

        System.out.println(sql);

        int result = -1;
        try {
            result = jdbcTemplate.update(sql, args.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Boolean existAccount(String id) {
        String sql = "select COUNT(*) from TB_ADMIN_ACCOUNT where id = ?";
        int result = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return result > 0;
    }

    public AdminAccountVo selectAdmin (AdminAccountVo adminAccountVo){
        String sql = "select * from TB_ADMIN_ACCOUNT where id = ? AND approval > 0";
        List<AdminAccountVo> adminAccountVoList = new ArrayList<>();
        try {
            adminAccountVoList = jdbcTemplate.query(sql, new RowMapper<AdminAccountVo>() {
                @Override
                public AdminAccountVo mapRow(ResultSet rs, int rowNum) throws SQLException {
                    AdminAccountVo adminAccountVo = new AdminAccountVo();
                    adminAccountVo.setNo(rs.getInt("no"));
                    adminAccountVo.setId(rs.getString("id"));
                    adminAccountVo.setName(rs.getString("name"));
                    adminAccountVo.setPassword(rs.getString("password"));
                    adminAccountVo.setGender(rs.getString("gender"));
                    adminAccountVo.setPart(rs.getString("part"));
                    adminAccountVo.setPosition(rs.getString("position"));
                    adminAccountVo.setEmail(rs.getString("email"));
                    adminAccountVo.setPhone(rs.getString("phone"));
                    adminAccountVo.setRegDate(rs.getString("regDate"));
                    adminAccountVo.setModDate(rs.getString("modDate"));
                    return adminAccountVo;
                }
            }, adminAccountVo.getId());
            if(!passwordEncoder.matches(adminAccountVo.getPassword(), adminAccountVoList.get(0).getPassword())) {
                adminAccountVoList.clear();
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return adminAccountVoList.size() == 0 ? null : adminAccountVoList.get(0);
    }

    public int updateAdminAccount(AdminAccountVo adminAccountVo) {
        String sql = "UPDATE TB_ADMIN_ACCOUNT SET "
                + "name = ?, "
                + "gender = ?, "
                + "part = ?, "
                + "position = ?, "
                + "email = ?, "
                + "phone = ?, "
                + "modDate = NOW() "
                + "WHERE no = ? ";

        int result = -1;

        try {
            result = jdbcTemplate.update(sql,
                    adminAccountVo.getName(),
                    adminAccountVo.getGender(),
                    adminAccountVo.getPart(),
                    adminAccountVo.getPosition(),
                    adminAccountVo.getEmail(),
                    adminAccountVo.getPhone(),
                    adminAccountVo.getNo()
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public AdminAccountVo selectAdmin(int no) {
        System.out.println("[AdminMemberDao] selectAdmin()");

        String sql =  "SELECT * FROM TB_ADMIN_ACCOUNT WHERE no = ? ";

        List<AdminAccountVo> adminAccountVos = new ArrayList<AdminAccountVo>();

        try {

            adminAccountVos = jdbcTemplate.query(sql, new RowMapper<AdminAccountVo>() {

                @Override
                public AdminAccountVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AdminAccountVo adminAccountVo = new AdminAccountVo();
                    adminAccountVo.setNo(rs.getInt("no"));
                    adminAccountVo.setId(rs.getString("id"));
                    adminAccountVo.setName(rs.getString("name"));
                    adminAccountVo.setPassword(rs.getString("password"));
                    adminAccountVo.setGender(rs.getString("gender"));
                    adminAccountVo.setPart(rs.getString("part"));
                    adminAccountVo.setPosition(rs.getString("position"));
                    adminAccountVo.setEmail(rs.getString("email"));
                    adminAccountVo.setPhone(rs.getString("phone"));
                    adminAccountVo.setRegDate(rs.getString("regDate"));
                    adminAccountVo.setModDate(rs.getString("modDate"));
                    adminAccountVo.setApproval(rs.getInt("approval"));

                    return adminAccountVo;
                }

            }, no);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return adminAccountVos.size() > 0 ? adminAccountVos.get(0) : null;

    }

    public AdminAccountVo selectAdmin(String id, String name, String email) {

        String sql =  "SELECT * FROM TB_ADMIN_ACCOUNT "
                + "WHERE id = ? AND name = ? AND email = ?";

        List<AdminAccountVo> adminMemberVos = new ArrayList<AdminAccountVo>();

        try {

            adminMemberVos = jdbcTemplate.query(sql, new RowMapper<AdminAccountVo>() {

                @Override
                public AdminAccountVo mapRow(ResultSet rs, int rowNum) throws SQLException {

                    AdminAccountVo adminMemberVo = new AdminAccountVo();
                    adminMemberVo.setNo(rs.getInt("no"));
                    adminMemberVo.setId(rs.getString("id"));
                    adminMemberVo.setName(rs.getString("name"));
                    adminMemberVo.setPassword(rs.getString("password"));
                    adminMemberVo.setGender(rs.getString("gender"));
                    adminMemberVo.setPart(rs.getString("part"));
                    adminMemberVo.setPosition(rs.getString("position"));
                    adminMemberVo.setEmail(rs.getString("email"));
                    adminMemberVo.setPhone(rs.getString("phone"));
                    adminMemberVo.setRegDate(rs.getString("regDate"));
                    adminMemberVo.setModDate(rs.getString("modDate"));
                    adminMemberVo.setApproval(rs.getInt("approval"));

                    return adminMemberVo;

                }

            }, id, name, email);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return adminMemberVos.size() > 0 ? adminMemberVos.get(0) : null;

    }

    public int updatePassword(String a_m_id, String newPassword) {
        System.out.println("[AdminMemberDao] updatePassword()");

        String sql =  "UPDATE TB_ADMIN_ACCOUNT SET "
                + "password = ?,  modDate = NOW() "
                + "WHERE id = ?";
        int result = -1;
        try {
            result = jdbcTemplate.update(sql, bCryptPasswordEncoder.encode(newPassword), a_m_id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
