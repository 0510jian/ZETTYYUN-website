package com.zetty01234.zettyyun.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class blogServiceImpl implements blogService{

    @Autowired
    private blogMapper mapper;

    @Override
    public ArrayList<blogDTO> selectHome() throws Exception {
        return mapper.selectHome();
    }

    @Override
    public ArrayList<blogDTO> selectBlogList() throws Exception {
        return mapper.selectBlogList();
    }

    @Override
    public void insertBlog(blogDTO dto) throws Exception {
        if (dto.isBHome()) {
            mapper.updateHome();
        }
        mapper.insertBlog(dto);
    }

    @Override
    public blogDTO selectBlog(int b_seq) throws Exception {
        return mapper.selectBlog(b_seq);
    }

    @Override
    public void updateBlog(blogDTO dto) throws Exception {
        if (dto.isBHome()) {
            mapper.updateHome();
        }
        mapper.updateBlog(dto);
    }

    @Override
    public void deleteBlog(int b_seq) throws Exception {
        mapper.deleteBlog(b_seq);
    }

}
