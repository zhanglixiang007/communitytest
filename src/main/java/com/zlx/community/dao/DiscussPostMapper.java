package com.zlx.community.dao;


import com.zlx.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiscussPostMapper {


    /**
     * 根据userId查询指定范围内的帖子（除被拉黑的帖子status=2）
     * 其中userId为0表示查询所有用户帖子，为特定值则表示查询特定用户帖子---动态拼接SQL语句
     * offset和limit是分页常用的，offset表示偏移量即第几个条记录(从0开始)，limit表示页面大小即此次一共查询多少条记录
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);


    /**
     * 根据userId查询用户帖子总数
     * 其中userId为0表示查询所有用户帖子总数，为特定值则表示查询特定用户帖子总数
     */
    int selectDiscussPostsRows(int userId);
}
