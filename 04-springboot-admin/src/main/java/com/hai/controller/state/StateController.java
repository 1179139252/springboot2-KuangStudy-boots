package com.hai.controller.state;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hai.controller.common.BaseController;
import com.hai.pojo.state.State;
import com.hai.service.state.StateIServicempl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 *
 * 用户模块
 * @param
 * @return
 * @author merry
 * @creed: Talk is cheap,show me the code
 * @date 2021/7/2 17:00
 */

@Controller
public class StateController extends BaseController {


    @Autowired
    StateIServicempl stateIServicempl;

    /**
     *
     * 跳转到 统计界面
     * @return java.lang.String
     * @author merry
     * @creed: Talk is cheap,show me the code
     * @date 2021/7/2 17:04
     */

    @GetMapping("/state/list")
    public String templatelist(ModelMap map,@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, @RequestParam(value = "pagesize",defaultValue = "10") Integer pagesize,ModelMap modelMap){

        // 3: 渲染

        Page<State> pageState = new Page<>(pageNo,pagesize);
        Page<State> page = stateIServicempl.page(pageState);
        // 2: 把数据放入到作用域
        // 每页显示的具体数据
        modelMap.put("state_list", page.getRecords());
        // 总记录数
        modelMap.put("total", page.getTotal());
        //pageSize是每页显示多少条
        modelMap.put("pageSize", page.getSize());
        // pageNo 当前页
        modelMap.put("pageNo", page.getCurrent());
        //  pages分了多少页
        modelMap.put("pages", page.getPages());

        return "/state/template";
    }

    /**
     * 根据id进行删除
     * @param id
     * @return
     */

    @ResponseBody
    @PostMapping("/state/del/{id}")
    public Integer delState(@PathVariable("id") Integer id){
        boolean b = stateIServicempl.removeById(id);

        return b?1:0;
    }



    /**
     * 根据id进行删除
     * @param id
     * @return
     */

    @ResponseBody
    @PostMapping("/state/delete/{id}")
    public Integer deletedState(@PathVariable("id") Integer id){
        boolean b = stateIServicempl.removeById(id);
        return b?1:0;
    }



}
