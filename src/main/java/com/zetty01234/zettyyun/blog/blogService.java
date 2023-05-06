package com.zetty01234.zettyyun.blog;

import java.util.ArrayList;

public interface blogService {

    ArrayList<blogDTO> selectHome() throws Exception;
    ArrayList<blogDTO> selectBlogList() throws Exception;

    void insertBlog(blogDTO dto) throws Exception;

    blogDTO selectBlog(int b_seq) throws Exception;

    void updateBlog(blogDTO dto) throws Exception;

    void deleteBlog(int b_seq) throws Exception;
}
