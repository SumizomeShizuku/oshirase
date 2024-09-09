package com.anestec.hello.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/items")
    public String showItems(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        int offset = page * pageSize;

        // 查询数据库中的数据并分页
        String sql = "SELECT * FROM Item LIMIT ? OFFSET ?";
        List<Map<String, Object>> items = jdbcTemplate.queryForList(
                sql,
                pageSize, offset
        // new BeanPropertyRowMapper<>(Item.class)
        );

        // 计算总行数并判断总页数
        String countSql = "SELECT COUNT(*) FROM Item";
        Integer totalItems = jdbcTemplate.queryForObject(countSql, Integer.class);
        if (totalItems == null) {
            totalItems = 0; // Handle the case where the count query returns null
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "items";
    }

}
