package daelim.book.rental.minyoung.user.account;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserAccountDao {
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountDao(DataSource dataSource, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public int insertAccount(UserAccountVo vo) {
        String sql = "INSERT INTO TB_USER_MEMBER (id, pw, name, gender, email, phone, regDate, modDate) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

        int result = -1;
        try {
            result = jdbcTemplate.update(sql,
                    vo.getId(),
                    passwordEncoder.encode(vo.getPw()),
                    vo.getName(),
                    vo.getGender(),
                    vo.getEmail(),
                    vo.getPhone()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 아이디 중복 확인
    public boolean existAccount(String id) {
        String sql = "SELECT COUNT(*) FROM TB_USER_MEMBER WHERE id = ?";
        try {
            int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return true; // 오류 발생 시 중복된 것으로 처리
        }
    }

    // 로그인 정보 확인
    public UserAccountVo selectUser(UserAccountVo vo) {
        String sql = "SELECT * FROM TB_USER_MEMBER WHERE id = ?";
        List<UserAccountVo> users = new ArrayList<>();

        try {
            users = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
                UserAccountVo user = new UserAccountVo();
                user.setNo(rs.getInt("no"));
                user.setId(rs.getString("id"));
                user.setPw(rs.getString("pw"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
                user.setModDate(rs.getTimestamp("modDate").toLocalDateTime());
                return user;
            }, vo.getId());

            if (!users.isEmpty() && passwordEncoder.matches(vo.getPw(), users.get(0).getPw())) {
                return users.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
