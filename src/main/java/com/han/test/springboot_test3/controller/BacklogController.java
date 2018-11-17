package com.han.test.springboot_test3.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.test.springboot_test3.domain.Backlog;
import com.han.test.springboot_test3.mapper.BacklogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/backlog", produces = "application/json;charset=UTF-8")
@ResponseBody
public class BacklogController {

    @Autowired
    private BacklogMapper mapper;

    //get请求处理
    @GetMapping
    public  List<Backlog>  get(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String sid = request.getParameter("id");
        if (sid != null) {
            Long id = Long.valueOf(sid);
            Backlog backlog = mapper.selectByPrimaryKey(id);
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

        List<Backlog> list = mapper.getList(leaveTime, module, task);

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
            mapper.deleteByPrimaryKey(id);
            return "success delete " + id;
        }
        return "no delete";
    }

    //post请求处理
    @PostMapping
    public String post(@RequestBody JSONObject json) {
        List<Backlog> backlogs = this.getResult(json);
        for (Backlog backlog: backlogs){
            mapper.insertSelective(backlog);
        }

        return "success save " + backlogs;
    }

    //put请求处理
    @PutMapping
    public String put(@RequestBody JSONObject json) {
        List<Backlog> backlogs = this.getResult(json);

        for (Backlog backlog: backlogs){
            mapper.updateByPrimaryKeySelective(backlog);
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
            backlog.setLeaveTime(leaveTime);
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
}
