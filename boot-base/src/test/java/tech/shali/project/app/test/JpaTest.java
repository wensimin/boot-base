package tech.shali.project.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tech.shali.project.app.entity.SysUser;
import tech.shali.project.app.service.SysUserService;

import javax.transaction.Transactional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class JpaTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void save(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName(UUID.randomUUID().toString());
        sysUser.setPassword("123");
        sysUserService.save(sysUser);
    }
}
