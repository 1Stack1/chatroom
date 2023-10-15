/*
package com.example.server.timer;

import com.alibaba.fastjson.JSON;
import com.example.server.bean.History;
import com.example.common.constant.TimeInterval;
import com.example.server.dao.HistoryDao;
import com.example.server.dao.OrganizationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.example.common.constant.RedisPrefix.*;

*/
/**
 * 将redis中的聊天记录存到数据库定时任务
 *//*

@Component
public class UpdateDataSchedule {

    private static final Logger log = Logger.getLogger(UpdateDataSchedule.class.getName());

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    HistoryDao historyDao;
    @Autowired
    OrganizationDao organizationDao;

    */
/**
     * 单聊历史记录刷到数据库
     *//*

    @Scheduled(cron = "0/3 * * * * ?")
    private void SangleChatToSql() {
        //1.从获取聊天记录
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        String key = SANGLE_CHAT+(TimeInterval.getDifTime() - 1);
        List<String> dataList = opsForList.range(
                                                    key,
                                                    0,
                                                    opsForList.size(key));
        //2.如果历史记录不为空
        int oldVal = dataList.size();
        if(oldVal != 0){
            log.info(dataList.toString());
            List<History> historyList = dataList.stream().map((msg) -> {
                History history = JSON.parseObject(msg, History.class);
                return history;
            }).collect(Collectors.toList());
            //3.将记录存储到数据库
            historyDao.addHistoryList(historyList);
            //4.将redis中的存储删除
            stringRedisTemplate.delete(key);
        }

    }

    */
/**
     * 更新未接受到的消息
     *//*

    @Scheduled(cron = "0/3 * * * * ?")
    private void updateUnreceive() {
        //1.需要更新消息信息
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
        String key = CONFIRM_RECEIVE + (TimeInterval.getDifTime() - 1);
        List<String> dataList = opsForList.range(
                key,
                0,
                opsForList.size(key));
        //2.如果需要更新不为空
        int oldVal = dataList.size();
        if(oldVal != 0){
            log.info(dataList.toString());
            dataList.stream().forEach((val)->{
                String[] split = val.split("-");
                //3.更新数据库
                historyDao.updateUnreceive(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
            });
            //4.将redis中的存储删除
            stringRedisTemplate.delete(key);
        }

    }


    @Scheduled(cron = "0/3 * * * * ?")
    private void OrgChatToSql() {
        //1.从获取聊天记录
        String key = ORG_CHAT + (TimeInterval.getDifTime() - 1);
        HashOperations<String, Object, Object> opsForHash = stringRedisTemplate.opsForHash();
        Map<Object, Object> entries = opsForHash.entries(key);
        //2.如果历史记录不为空
        if(entries.size() != 0){
            //3.获取所有历史记录
            HashMap<String,Integer> waitUpdate = new HashMap<>();
            entries.entrySet().forEach((entry)->{
                History history = JSON.parseObject((String) entry.getKey(), History.class);
                String[] ids = ((String) entry.getValue()).split(",");
                for(String id : ids){
                    String hKey = history.getToId()+"-"+id;
                    if(waitUpdate.containsKey(hKey)){
                        waitUpdate.put(hKey, Math.max(waitUpdate.get(hKey),history.getId()));
                    }else{
                        waitUpdate.put(hKey, history.getId());
                    }
                }
            });
            //4.批量插入
            List<History> historyList = entries.keySet().stream().map((obj) -> {
                return JSON.parseObject((String) obj, History.class);
            }).collect(Collectors.toList());
            log.info("插入数据库的数据========>"+historyList.toString());
            historyDao.addHistoryList(historyList);
            //5.更新存储用户看到群消息id字段
            log.info("更新的数据======>"+waitUpdate.toString());
            waitUpdate.entrySet().forEach((entry)->{
                String[] split = ((String) entry.getKey()).split("-");
                organizationDao.updateLastViewHistoryId(Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        entry.getValue());
            });
        }

    }

}
*/
