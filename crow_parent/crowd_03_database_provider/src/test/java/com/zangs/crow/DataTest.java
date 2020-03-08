package com.zangs.crow;

import com.zangs.crow.base.bean.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@ActiveProfiles("database_provider_01")
@SpringBootTest(classes = MainDatabase01.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataTest {
    @Autowired
    Dao dao;

    @Autowired
    private DataSource dataSource;

    @Test
    public void main() {
        Daos.migration(dao, "com.zangs.crow", true, false);
        TestBean bean = new TestBean();
        dao.update(bean);
    }
}
