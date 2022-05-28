package edu.qcu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.qcu.dao.IPermissionDao;
import edu.qcu.domain.Permission;
import edu.qcu.domain.Role;
import edu.qcu.service.IPermissionService;
import edu.qcu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/role")
public class RoleController {

    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    private IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindRoleAll')")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize
    ) throws Exception {
        List<Role> roleList = roleService.findAll(page, pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("back-end/role-list");
        return mv;
    }

    @RequestMapping("/findPermissionByRoleId.do")
    @PreAuthorize("hasAuthority('Permission_FindPermissionByRoleId')")
    public ModelAndView findPermissionByRoleId(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> allPermissionList = permissionService.findAll();
        List<Permission> otherPermissionList = roleService.findOtherPermissionList(id);
        if (otherPermissionList.isEmpty())
            otherPermissionList.add(new Permission());
        mv.addObject("PermissionList", allPermissionList);
        mv.addObject("otherPermission", otherPermissionList);
        mv.addObject("roleId", id);
        mv.setViewName("back-end/role-permission-add");
        return mv;
    }

    @RequestMapping("addPermissionToRole.do")
    @PreAuthorize("hasAuthority('Permission_FindRoleAll')")
    public String addPermissionToRole(
            @RequestParam(name = "roleId") String roleId,
            @RequestParam(name = "ids", required = false) String[] permissionIds
    ) throws Exception {
        roleService.delPermissionByRoleId(roleId);
        if (permissionIds != null)
            roleService.addPermission(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("save.do")
    @PreAuthorize("hasAuthority('Permission_SaveRole')")
    public String save(Role role) throws Exception {
        if (roleService.findRoleByRoleName(role.getRoleName()) == 0)
            roleService.save(role);
        else {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('该角色已存在');");
            pw.println("window.history.go(-1)");
            pw.println("</script>");
            pw.close();
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("delRole.do")
    @PreAuthorize("hasAuthority('Permission_DeleteRole')")
    public String delRole(
            @RequestParam(name = "ids", required = false) String[] roleIds
    ) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        if (roleIds.length == 0) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('您尚未选择任何信息');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        List<String> alert = new ArrayList<>();
        boolean NoUserIsRole = true;
        for (String roleId : roleIds) {
            if (roleService.findUserNumByRolesId(roleId) != 0) {
                NoUserIsRole = false;
                alert.add(roleId);
            } else {
                roleService.delRole(roleId);
            }
            if (!Objects.equals(roleId, "")) ;
        }
        if (!NoUserIsRole) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('以下角色未被删除，因为仍有用户属于该角色.角色ID如下" + alert + "');");
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
