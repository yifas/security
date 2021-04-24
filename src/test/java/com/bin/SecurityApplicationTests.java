package com.bin;

import com.bin.project.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    ConfigurableApplicationContext run;

    @Autowired
    private SysRoleService sysRoleService;
    /**
     * BCrypt加盐加密
     */
    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        //注：底层已经加盐了！！
        String encode = passwordEncoder.encode("123456");
        System.err.println(encode);
    }

    @Test
    void testUpdateRole(){

        Long id = 6L;
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(1L);
        roleIds.add(2L);

        int i = sysRoleService.updateRole(id, roleIds);
        System.out.println(i);

    }

    @Test
    void testLogging(){
         Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.trace("日志输出 trace");
        logger.debug("日志输出 debug");
        logger.info("日志输出 info");
        logger.warn("日志输出 warn");
        logger.error("日志输出 error");
    }
    /**
     * 获取所有方法url
     * @param run
     */
    @Test
    void getAllUrl(ConfigurableApplicationContext run) {

        //LOGGER.info("getUrlMethod is called.");

        StringBuilder sbSql = new StringBuilder();
        sbSql.append("INSERT INTO interfacelist (url,name,type)values");

        //获取restcontroller注解的类名
        String[] beanNamesForAnnotation = run.getBeanNamesForAnnotation(RestController.class);

        //获取类对象
        for (String str : beanNamesForAnnotation) {
            Object bean = run.getBean(str);
            Class<?> forName = bean.getClass();
            System.out.println(forName.getName());

            //获取requestmapping注解的类
            RequestMapping declaredAnnotation = forName.getAnnotation(RequestMapping.class);

            String url_path = "";
            if (declaredAnnotation != null) {
                String[] value = (declaredAnnotation.value());
                //获取类的url路径
                url_path = value[0];
                for (Method method : forName.getDeclaredMethods()) {
                    RequestMapping annotation2 = method.getAnnotation(RequestMapping.class);
                    if (annotation2 != null) {
                        url_path += annotation2.value()[0];
                        System.out.println("方法路径" + url_path + "方法名" + method.getName());
                        sbSql.append("('").append(url_path).append("','").append(method.getName()).append("','").append("api'),");
                    }
                    url_path = value[0];
                }
            }
        }

        //删除字符串sbCols最后一个逗号
        sbSql.deleteCharAt(sbSql.length() - 1);

        System.out.println(sbSql);

        System.out.println(sbSql.toString());
    }

}
