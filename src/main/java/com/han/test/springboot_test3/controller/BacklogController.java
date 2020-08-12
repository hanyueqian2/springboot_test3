package com.han.test.springboot_test3.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.mapper.BacklogMapper;
import com.han.test.springboot_test3.utils.excel.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;


@Controller
@RequestMapping(value = "/backlog", produces = "application/json;charset=UTF-8")
@ResponseBody
public class BacklogController {

    @Autowired
    private BacklogMapper backlogMapper;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    MessageUtils messageUtils;

    @GetMapping("/testDruid")
    @Transactional(rollbackFor = Exception.class)
    public void testDruid() {
        Backlog backlog = new Backlog();
        backlog.setId(1L);
        backlog.setEnter("enter");
        backlog.setModule("Module");
        backlog.setTask("task");
        backlog.setLeaveTime(new Date());
        backlog.setSchedule(UUID.randomUUID().toString().substring(0, 6));
        List<Backlog> backlogs = backlogMapper.selectAll();
        System.out.println("查询结果:" + backlogs.size());

        backlogMapper.updateByPrimaryKeySelective(backlog);
        System.out.println("哈哈,成功了");
    }

    @GetMapping("/testSelect")
    public void testSelect() {
        List<Backlog> backlogs = backlogMapper.selectAll();
        System.out.println("查询结果:" + backlogs.size());
    }

    //get请求处理
    @GetMapping
    public  List<Backlog>  get(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");//允许跨域访问
        String sid = request.getParameter("id");
        if (sid != null) {
            Long id = Long.valueOf(sid);
            Backlog backlog = backlogMapper.selectByPrimaryKey(id);
            List<Backlog> list = new ArrayList<>();
            list.add(backlog);
            return list;
        }
        //------------------获取离港时间--------------------------------
        String sTime = request.getParameter("leaveTime");
        Long leaveTime = null;
        if (sTime != null) {
            leaveTime = Long.valueOf(sTime);
        }
        //-------------------------------------------------------------
        String module = request.getParameter("module");
        String task = request.getParameter("task");

        //使用PageHelper进行分页
        PageHelper.startPage(1,5);

        List<Backlog> list = backlogMapper.getList(leaveTime, module, task);

        //把list强制转换为Page以获取分页的全部信息
        //Page{count=true, pageNum=1, pageSize=5, startRow=0, endRow=5, total=21, pages=5, reasonable=true, pageSizeZero=false}
        Page<Backlog> page= (Page<Backlog>) list;

        System.out.println(page);

        PageInfo pageInfo = new PageInfo(list);
        System.out.println(pageInfo.getList());
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo);
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPrePage());
        System.out.println(pageInfo.getNextPage());

        return list;

    }

    //delete请求处理
    @DeleteMapping
    public String delete(HttpServletRequest request) {
        String sid = request.getParameter("id");
        if (sid != null) {
            Long id = Long.valueOf(sid);
            backlogMapper.deleteByPrimaryKey(id);
            return "success delete " + id;
        }
        return "no delete";
    }

    //post请求处理
    @PostMapping
    public String post(@RequestBody JSONObject json) {
        List<Backlog> backlogs = this.getResult(json);
        for (Backlog backlog: backlogs){
            backlogMapper.insertSelective(backlog);
        }

        return "success save " + backlogs;
    }

    //put请求处理
    @PutMapping
    public String put(@RequestBody JSONObject json) {
        List<Backlog> backlogs = this.getResult(json);

        for (Backlog backlog: backlogs){
            backlogMapper.updateByPrimaryKeySelective(backlog);
        }

        return "success put:" + backlogs;
    }






    //把JSONObject转换为Backlog的
    private Backlog addBacklog(JSONObject json) {
        Backlog backlog = new Backlog();

        Long id = json.getLong("id");
        if (id != null) {
            backlog.setId(id);
        }
        String module = json.getString("module");
        if (module != null) {
            backlog.setModule(module);
        }
        String businessNumber = json.getString("businessNumber");
        if (businessNumber != null) {
            backlog.setBusinessNumber(businessNumber);
        }
        String task = json.getString("task");
        if (task != null) {
            backlog.setTask(task);
        }
        String schedule = json.getString("schedule");
        if (schedule != null) {
            backlog.setSchedule(schedule);
        }
        String enter = json.getString("enter");
        if (enter != null) {
            backlog.setEnter(enter);
        }
        String sLeaveTime = json.getString("leaveTime");
        if (sLeaveTime != null) {
            Long leaveTime = Long.valueOf(sLeaveTime);
//            backlog.setLeaveTime(leaveTime);
        }
        return backlog;
    }

    //把接收的json数据转换为List<Backlog>
    private List<Backlog> getResult(JSONObject json){
        //-----接受名字为data的数组,并把它转化为一个List<Object>------
        JSONArray datas = json.getJSONArray("data");
        List<Object> initData = datas.subList(0, datas.size());
        //--------------------------------------------------
        List<Backlog> backlogs = new ArrayList<>();
        Backlog backlog;
        //-------循环遍历返回的List<Object>,把它储存进一个List<Backlog>-----
        for (Object data : initData) {
            if(data instanceof JSONObject){
                JSONObject obj = (JSONObject)data;
                backlog = this.addBacklog(obj);//自定义的把JSONObject转换为Backlog的函数
                backlogs.add(backlog);
            }
        }
        //--------------------------------------------------
        return backlogs;
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/testNum")
    public String testNum() {
        List<Backlog> backlogs = backlogMapper.selectMany(0);
        for (Backlog backlog : backlogs) {
            System.out.println(backlog.getId());

        }
        int i = backlogMapper.updateSome(backlogs.get(0).getTestNum());
        if (i <= 0) {
            return "error";
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "OK";
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/testNum2")
    public String testNum2() {
        List<Backlog> backlogs = backlogMapper.selectMany(0);
        for (Backlog backlog : backlogs) {
            System.out.println(backlog.getId());

        }
        int i = backlogMapper.updateSome1(backlogs.get(0).getTestNum());
        int c = i / 0;
        if (i <= 0) {
            return "error";
        }

        return "OK";
    }

    @GetMapping("/testThread")
    public String testThread() {
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("start:" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000 * 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end:" + Thread.currentThread().getName());
        });

        return "OK";
    }

    @GetMapping("/testMyBatis")
    public String testMyBatis() {

        Backlog backlog = backlogMapper.selectByPrimaryKey(1L);
        System.out.println("查询结果1:" + backlog.getId());
        Backlog backlog1 = backlogMapper.selectByPrimaryKey(1L);
        System.out.println("查询结果2:" + backlog1.getId());
        Backlog backlog2 = backlogMapper.selectByPrimaryKey(1L);
        System.out.println("查询结果3:" + backlog2.getId());
        Backlog backlog3 = backlogMapper.selectByPrimaryKey(1L);
        System.out.println("查询结果4:" + backlog3.getId());

        return "OK";
    }
    @GetMapping("/testMyBatis2")
    public String testMyBatis2() {

        Backlog backlog = backlogMapper.selectByPrimaryKey(1);
        System.out.println("查询结果1:" + backlog.getId());
        Backlog backlog1 = backlogMapper.selectByPrimaryKey(1);
        System.out.println("查询结果2:" + backlog1.getId());
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Backlog backlog2 = backlogMapper.selectByPrimaryKey(1);
        System.out.println("查询结果3:" + backlog2.getId());
        Backlog backlog3 = backlogMapper.selectByPrimaryKey(1);
        System.out.println("查询结果4:" + backlog3.getId());
        new FutureTask<Integer>(null);

//        ExecutorService

        return "OK";
    }
    @GetMapping("/testLang")
    public String testLang() {

        String name = messageUtils.get("user.title");
        System.out.println(name);

        return name;
    }
}
