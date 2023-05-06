package com.zetty01234.zettyyun.blog;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface blogMapper {

    ArrayList<blogDTO> selectHome() throws Exception;
    ArrayList<blogDTO> selectBlogList() throws Exception;

    void insertBlog(blogDTO dto) throws Exception;

    void insertHome(blogDTO dto) throws Exception;

    blogDTO selectBlog(int b_seq) throws Exception;

    void updateBlog(blogDTO dto) throws Exception;

    void updateHome() throws Exception;

    void deleteBlog(int b_seq) throws Exception;
}
