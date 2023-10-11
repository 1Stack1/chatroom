package com.example.server.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.bean.Friend;
import com.example.common.bean.Group;
import com.example.common.bean.History;
import com.example.common.bean.Organization;
import com.example.common.constant.TimeInterval;
import com.example.common.dto.UnreceivedMsg;
import com.example.dao.dao.FriendDao;
import com.example.dao.dao.GroupDao;
import com.example.dao.dao.HistoryDao;
import com.example.dao.dao.OrganizationDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import static com.example.common.constant.RedisPrefix.CONFIRM_RECEIVE;

@Slf4j
@RestController
public class PageController {
    @Autowired
    GroupDao groupDao;
    @Autowired
    FriendDao friendDao;
    @Autowired
    HistoryDao historyDao;
    @Autowired
    OrganizationDao organizationDao;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取指定用户的分组
     * @param username
     * @return
     */
    @GetMapping("/groupList/{username}")
    public String getGroupList(@PathVariable("username") String username) {
        List<Group> groups = groupDao.selectGroupsByUserName(username);
        System.out.println(JSON.toJSONString(groups));
        return JSON.toJSONString(groups);
    }


    /**
     * 获取指定分组的所有用户信息
     * @param groupId
     * @return
     */
    @GetMapping("/friendList/{groupId}")
    public String getFriendList(@PathVariable("groupId") Integer groupId) {
        List<Friend> friends = friendDao.selectFriendsByGroupId(groupId);
        System.out.println(friends);
        return JSON.toJSONString(friends);
    }

    /**
     * 获取两个用户之间的历史记录
     * @param map key->userId,value-.toId
     * @return
     */
    @PostMapping("/historyList")
    public String getHistoryList(@RequestBody Map<String,Integer> map) {
        List<History> histories = historyDao.selectHistoryById(map.get("userId"), map.get("toUserId"));
        System.out.println(histories);
        return JSON.toJSONString(histories);
    }

    /**
     * 获取指定用户未接受到的聊天记录
     * @param userId
     * @return
     */
    @GetMapping("/unreceivedMsgs/{userId}")
    public String getUnreceivedMsgs(@PathVariable("userId") Integer userId) {
        List<UnreceivedMsg> unreceivedMsgs = historyDao.getUnrecivedMsgs(userId).stream().map((msg) -> {
            if (msg.getContent().length() > 8) {
                msg.setContent(msg.getContent().substring(0, 8) + "...");
            }
            return msg;
        }).collect(Collectors.toList());
        return JSON.toJSONString(unreceivedMsgs);
    }

    /**
     * 用户确认收到信息
     * @param map key->userId,value->toId
     * @return
     */
    @PostMapping("/confirmReceive")
    public String confirmReceive(@RequestBody Map<String,Integer> map) {
        //1.从历史记录里查询两个用户的聊天记录
        Integer userId = map.get("userId");
        Integer fromId = map.get("fromId");
        List<History> histories = historyDao.selectHistoryById(map.get("userId"), map.get("fromId"));
        //2.将确认收到存储到redis
        String key = CONFIRM_RECEIVE + TimeInterval.getDifTime();
        stringRedisTemplate.opsForList().rightPush(key,fromId + "-" +userId);
        log.info("==========id为"+userId+"的用户接收到了"+"id为"+fromId+"用户的信息========");
        stringRedisTemplate.expire(key,4, TimeUnit.SECONDS);
        return JSON.toJSONString(histories);
    }

    /**
     * 获取指定用户所有的群
     * @param userId
     * @return
     */
    @GetMapping("/orgList/{userId}")
    public String getOrgList(@PathVariable("userId") Integer userId) {
        List<Organization> orgs = organizationDao.findOrgs(userId);
        return JSON.toJSONString(orgs);
    }

    /**
     * 获取指定群聊的消息
     * @param
     * @return
     */
    @GetMapping("/orgHistories/{orgId}")
    public String getMsgListOfOrg(@PathVariable("orgId") Integer orgId) {
        List<History> historiesOfOrg = organizationDao.findHistoriesOfOrg(orgId);
        return JSON.toJSONString(historiesOfOrg);
    }
}
