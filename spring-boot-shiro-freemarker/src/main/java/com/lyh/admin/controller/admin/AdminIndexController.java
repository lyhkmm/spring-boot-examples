package com.lyh.admin.controller.admin;

import com.lyh.admin.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminIndexController extends BaseController {
    @RequestMapping(value = {"/admin/", "/admin/index"})
    public String index() {

        return "admin/index";
    }
}
