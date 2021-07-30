package com.hai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hai.config.KsdRedisBloomConfiguration;
import com.hai.mapper.CategoryMapper;
import com.hai.pojo.Category;
import io.rebloom.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private Client rebloomClient;


    @PostConstruct
    public void initBloomData(){
        List<Category> categroies = this.findCategroies(0);
        if(!CollectionUtils.isEmpty(categroies)){
            // 把对应一级分类的id放入到bloomfilter中
            String[] ids = new String[categroies.size()];
            for (int i = 0; i < categroies.size(); i++) {
                ids[i] = categroies.get(i).getId()+"";

            }
            // bf.madd
            rebloomClient.addMulti("redis:bloom:category",ids);
        }
    }




    @Override
    public List<Category> findCategroies(Integer cid) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", cid);
        return this.list(queryWrapper);

    }
}
