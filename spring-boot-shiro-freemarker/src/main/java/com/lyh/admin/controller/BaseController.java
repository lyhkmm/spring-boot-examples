package com.lyh.admin.controller;

import com.lyh.admin.common.DateEditor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    /**
     * 带参重定向
     *
     * @param path
     * @return
     */
    protected String redirect(String path) {
        return "redirect:" + path;
    }

    /**
     * 不带参重定向
     *
     * @param response
     * @param path
     * @return
     */
    protected String redirect(HttpServletResponse response, String path) {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取分页请求
     *
     * @return
     */
    protected PageRequest getPageRequest() {
        int page = 0;
        int size = 10;
        Sort sort = null;
        String sortName = request.getParameter("sortName");
        String sortOrder = request.getParameter("sortOrder");
        if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
            if (sortOrder.equalsIgnoreCase("desc")) {
                sort = new Sort(Direction.DESC, sortName);
            } else {
                sort = new Sort(Direction.ASC, sortName);
            }
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            page = Integer.parseInt(pageNumber) - 1;
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return pageRequest;
    }

    /**
     * 获取分页请求
     *
     * @param sort 排序条件
     * @return
     */
    protected PageRequest getPageRequest(Sort sort) {
        int page = 0;
        int size = 10;
        String sortName = request.getParameter("sortName");
        String sortOrder = request.getParameter("sortOrder");
        if (StringUtils.isNoneBlank(sortName) && StringUtils.isNoneBlank(sortOrder)) {
            if (sortOrder.equalsIgnoreCase("desc")) {
                sort.and(new Sort(Direction.DESC, sortName));
            } else {
                sort.and(new Sort(Direction.ASC, sortName));
            }
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            page = Integer.parseInt(pageNumber) - 1;
        }
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return pageRequest;
    }

}
