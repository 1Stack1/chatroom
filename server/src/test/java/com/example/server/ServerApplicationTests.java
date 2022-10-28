package com.example.server;

import com.example.common.bean.User;
import com.example.server.reposity.HistoryRepository;
import com.example.dao.dao.UserDao;
import com.example.common.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//websocketConfig会影响test
class ServerApplicationTests {

    @Autowired
    HistoryRepository historyReposity;
    @Autowired
    RedisCache redisCache;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDao userDao;

    @Test
    public void test01(){
        User user = userDao.selectByUsername("1");
        System.out.println(user);
        //System.out.println(passwordEncoder.encode("licehan123"));
        //System.out.println(passwordEncoder.encode("2"));
        //System.out.println(passwordEncoder.encode("de"));
        //System.out.println(2==3);
    }

    @Test
    public void test02(){
        Map<String,String> map=new HashMap<>();
        map.put("password","password");map.put("email","email");map.put("code",""+"number");
        redisCache.setCacheMap("username",map);
        redisCache.expire("username",15, TimeUnit.MINUTES);


        Map<String, Object> username = redisCache.getCacheMap("username");
        System.out.println(username.get("password"));
        System.out.println(username.get("email"));
    }

    @Test
    public void test03(){
        //historyReposity.insert(new Message("1","2022-10-17 01:56:35","1","2","i am your dad",null));
        //historyReposity.insert(new Message("2","2022-10-17 01:56:50","2","1","i am your son",null));

        //historyReposity.deleteAll();
        /*List<Message> byFrom = historyReposity.findByFromAndTo("1","2");
        for(Message msg:byFrom){
            System.out.println(msg);
        }*/
    }

    @Test
    public void test04() throws  ParseException {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now="2022-10-17 02:13:03";
        Date date1=dateFormat.parse(now);
        Date[] dates=new Date[2];
        dates[0]=date;dates[1]=date1;
        Arrays.sort(dates);
        for(Date d:dates){
            System.out.println(d);
        }
        //System.out.println(date);
    }

    @Test
    public void test05(){
        System.out.println("2020-01-01".compareTo("2020-02-03"));
    }

    @Test
    public void test06(){
        List<String> list= userDao.selectRoleByUsername("1");
        for(String s:list){
            System.out.println(s);
        }
    }


    @Test
    public void test07(){
        Set<String> set=new HashSet<>();
        redisCache.setCacheSet("a",set).add("username");
        redisCache.setCacheSet("a",set).add("asdas","sada");
        for(Object s:redisCache.getCacheSet("a")){
            System.out.println(s);
        }
    }
}
