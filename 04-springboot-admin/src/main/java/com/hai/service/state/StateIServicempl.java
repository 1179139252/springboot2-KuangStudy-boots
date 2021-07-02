package com.hai.service.state;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hai.mapper.state.StaterMapper;
import com.hai.pojo.state.State;
import org.springframework.stereotype.Service;

@Service
public class StateIServicempl extends ServiceImpl<StaterMapper, State> implements StateService {
}
