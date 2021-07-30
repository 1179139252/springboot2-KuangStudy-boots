package com.hai.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hai.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService extends IService<Category> {

    List<Category> findCategroies(Integer cid);
}
