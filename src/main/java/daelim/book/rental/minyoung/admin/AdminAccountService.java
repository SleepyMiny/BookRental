package daelim.book.rental.minyoung.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminAccountService {
    final static public int ADMIN_ACCOUNT_ALREADY_EXISTS = 0;
    final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
    final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;

    @Autowired
    private AdminAccountDao adminAccountDao;

    @Autowired
    private JavaMailSender javaMailSender;  // ✅ 인터페이스 타입으로 수정

    public int createAccount(AdminAccountVo adminAccountVo) {
        System.out.println("[AdminAccountService] createAccount");

        boolean isExist = adminAccountDao.existAccount(adminAccountVo.getId());
        if (!isExist) {
            int result = adminAccountDao.insertAccount(adminAccountVo);
            if (result > 0) {
                return ADMIN_ACCOUNT_CREATE_SUCCESS;
            } else {
                return ADMIN_ACCOUNT_CREATE_FAIL;
            }
        } else {
            return ADMIN_ACCOUNT_ALREADY_EXISTS;
        }

    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AdminAccountVo login(AdminAccountVo adminAccountVo) {

        AdminAccountVo loginedAdminAccountVo = adminAccountDao.selectAdmin(adminAccountVo);
        if (loginedAdminAccountVo != null) {
            if (passwordEncoder.matches(adminAccountVo.getPassword(), loginedAdminAccountVo.getPassword())) {
                return loginedAdminAccountVo;
            }
        }
        return null;
    }

    public int modifyAccount(AdminAccountVo adminAccountVo) {
        return adminAccountDao.updateAdminAccount(adminAccountVo);
    }

    public AdminAccountVo getLoginedAdminAccountVo(int no) {
        return adminAccountDao.selectAdmin(no);
    }

    public int findPasswordConfirm(AdminAccountVo adminAccountVo) {

        AdminAccountVo selectedAdminAccountVo = adminAccountDao.selectAdmin(adminAccountVo.getId(),
                adminAccountVo.getName(),
                adminAccountVo.getEmail());

        int result = 0;

        if (selectedAdminAccountVo != null) {

            String newPassword = createNewPassword();
            result = adminAccountDao.updatePassword(adminAccountVo.getId(), newPassword);

            if (result > 0)
                sendNewPasswordByMail(adminAccountVo.getEmail(), newPassword);
        }

        return result;

    }

    private String createNewPassword() {

        char[] chars = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z'
        };

        StringBuffer stringBuffer = new StringBuffer();
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(new Date().getTime());

        int index = 0;
        int length = chars.length;
        for (int i = 0; i < 8; i++) {
            index = secureRandom.nextInt(length);

            if (index % 2 == 0)
                stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
            else
                stringBuffer.append(String.valueOf(chars[index]).toLowerCase());

        }

        return stringBuffer.toString();

    }

    private void sendNewPasswordByMail(String toMailAddr, String newPassword) {
        System.out.println("[AdminMemberService] sendNewPasswordByMail()");

        final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(toMailAddr);
                mimeMessageHelper.setSubject("[한국 도서관] 새 비밀번호 안내입니다.");
                mimeMessageHelper.setText("새 비밀번호 : " + newPassword, true);

            }

        };
        javaMailSender.send(mimeMessagePreparator);

    }

}
