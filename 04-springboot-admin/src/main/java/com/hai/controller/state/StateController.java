package com.hai.controller.state;

import com.hai.controller.common.BaseController;
import com.hai.pojo.state.State;
import com.hai.service.state.StateIServicempl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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
    public String templatelist(ModelMap map){


//        查询所有的列表 返回数据到前台展示
        List<State> list = stateIServicempl.list();

        map.put("state_list",list);

        return "/state/template";
    }

}
