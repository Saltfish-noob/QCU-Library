package edu.qcu.controller;

import com.github.pagehelper.PageInfo;
import edu.qcu.domain.Permission;
import edu.qcu.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    private final IPermissionService permissionService;

    @Autowired
    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindAllPermission')")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize
    ) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("back-end/permission-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    @PreAuthorize("hasAuthority('Permission_FindPermissionById')")
    public ModelAndView findById(String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("back-end/permission-show");
        return mv;
    }

    @RequestMapping("/updatePermission.do")
    @PreAuthorize("hasAuthority('Permission_UpdatePermission')")
    public String updatePermission(Permission permission)throws Exception{
        permissionService.updatePermission(permission);
        return "redirect:findAll.do";
    }


    @RequestMapping("/save.do")
    @PreAuthorize("hasAuthority('Permission_SavePermission')")
    public String save(Permission permission)throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("delPermission.do")
    @PreAuthorize("hasAuthority('Permission_DeletePermission')")
    public String delPermission(
            @RequestParam(name = "ids",required = false) String[] ids
    )throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        System.out.println(Arrays.toString(ids));
        if (ids.length == 0) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('您尚未选择任何信息');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        List<String> alert = new ArrayList<>();
        boolean NoInfo = true;
        for (String permissionId : ids) {
            if (permissionService.findRoleCountByPermission(permissionId) != 0) {
                NoInfo = false;
                alert.add(permissionId);
            } else {
                permissionService.delPermission(permissionId);
            }

            if (!Objects.equals(permissionId, "")) ;
        }
        if (!NoInfo) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('以下权限未被删除，因为仍有角色使用该权限.权限ID如下" + alert + "');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('删除成功');");
        pw.println("window.location.href='findAll.do'");
        pw.println("</script>");
        pw.close();
        return "redirect:findAll.do";
    }
}
