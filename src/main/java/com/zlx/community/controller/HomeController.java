package com.zlx.community.controller;

import com.zlx.community.entity.DiscussPost;
import com.zlx.community.entity.Page;
import com.zlx.community.entity.User;
import com.zlx.community.service.DiscussPostService;
import com.zlx.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /**
     * 获得牛客网讨论区首页index.html
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        page.setRows(discussPostService.findDiscussPostsRows(0));
        page.setPath("/index");
        int offset = page.getOffset();
        int limit = page.getLimit();

        List<Map<String, Object>> list = new ArrayList<>();

        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, offset, limit);
        for(DiscussPost discussPost : discussPosts){
            User user = userService.findUserById(discussPost.getUserId());
            Map<String, Object> map = new HashMap<>();//将一个帖子和发布帖子的用户封装到Map集合中
            map.put("discussPost", discussPost);
            map.put("user", user);
            list.add(map);//将所有的“帖子和发布帖子的用户”封装到Map集合之后统一封装到List集合中
        }

        model.addAttribute("list", list);//最后一定要：将数据封装到Model中才能在模板引擎中访问到

        return "index";
    }
}
