package edu.qcu.controller;

import com.github.pagehelper.PageInfo;
import edu.qcu.domain.Role;
import edu.qcu.domain.UserInfo;
import edu.qcu.service.IBorrowLogService;
import edu.qcu.service.IRecommendService;
import edu.qcu.service.IRoleService;
import edu.qcu.service.IUserService;
import edu.qcu.service.impl.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    private IRoleService roleService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    private IBorrowLogService borrowLogService;

    @Autowired
    public void setBorrowLogService(IBorrowLogService borrowLogService) {
        this.borrowLogService = borrowLogService;
    }

    private HttpSession session;

    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }

    private IRecommendService recommendService;

    @Autowired
    public void setRecommendService(IRecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasAuthority('Permission_FindAllUser')")
    public ModelAndView findAll(
            @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize
    ) throws Exception {
        List<UserInfo> userList = userService.findAll(page, pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("back-end/user-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    @PreAuthorize("hasAuthority('Permission_FindUserById')")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);
        mv.addObject("user", user);
        mv.setViewName("back-end/user-show");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("hasAuthority('Permission_SaveUser')")
    public String save(UserInfo userInfo) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        if (userService.findUserByUserName(userInfo.getUsername()) == 0) {
            userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
            userService.save(userInfo);
        } else {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('用户名已经存在');");
            pw.println("window.history.go(-1)");
            pw.println("</script>");
            pw.close();
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("hasAuthority('Permission_FindUserAndRoleById')")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        ModelAndView mv = new ModelAndView();

        UserInfo user = userService.findById(id);
        List<Role> otherRoles = userService.findOtherRoles(id);
        List<Role> allRoles = roleService.findAll();

        if (otherRoles.isEmpty())
            otherRoles.add(new Role());

        mv.addObject("roleList", allRoles);
        mv.addObject("otherList", otherRoles);
        mv.addObject("user", user);

        mv.setViewName("back-end/user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("hasAuthority('Permission_AddRoleToUser')")
    public String addRoleToUser(
            @RequestParam(name = "userId", required = true) String userId,
            @RequestParam(name = "ids", required = false) String[] roleIds
    ) throws Exception {
        roleService.delRoleToUser(userId);
        if (roleIds != null)
            userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("updateUser.do")
    @PreAuthorize("hasAuthority('Permission_UpdateUser')")
    public String userUpdate(UserInfo userInfo) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        String a = userInfo.getUsername();
        String b = userService.findById(userInfo.getId()).getUsername();
        System.out.println(Objects.equals(a, b));
        if (userService.findUserByUserName(userInfo.getUsername()) == 0 || Objects.equals(a, b)) {
            UserInfo old_userinfo = userService.findById(userInfo.getId());
            if (userInfo.getPassword().equals(""))
                userInfo.setPassword(old_userinfo.getPassword());
            else
                userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
            userService.updateUserInfo(userInfo);
        } else {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('该用户名已存在');");
            pw.println("window.history.go(-1)");
            pw.println("</script>");
            pw.close();
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/delUser.do")
    @PreAuthorize("hasAuthority('Permission_DeleteUser')")
    public String delUser(
            @RequestParam(value = "ids", required = false) String[] userIds
    ) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        if (userIds.length == 0) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('您尚未选择任何信息');");
            pw.println("window.location.href='findAll.do'");
            pw.println("</script>");
            pw.close();
        }
        List<String> alert = new ArrayList<>();
        boolean NoInfo = true;
        for (String userId : userIds) {
            if (userService.findInfoByUserId(userId) != 0) {
                NoInfo = false;
                alert.add(userId);
            } else {
                //删除权限
                roleService.delRoleToUser(userId);
                borrowLogService.delBorrowLogByUserId(userId);
                recommendService.delRecommendByUserId(userId
                );
                userService.delUserById(userId);
            }
        }
        if (!NoInfo) {
            PrintWriter pw = response.getWriter();
            pw.println("<script>");
            pw.println("alert('以下用户未被删除，因为有未归还的书籍.用户ID如下" + alert + "');");
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

    @RequestMapping("/userSelf.do")
    @PreAuthorize("hasAuthority('Permission_FindUserSelf')")
    public ModelAndView alterUserSelfInfo(String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(userId);
        mv.addObject("user", user);
        mv.setViewName("back-end/userSelf");
        return mv;
    }

    @RequestMapping("updateUserSelf.do")
    @PreAuthorize("hasAuthority('Permission_UpdateUserSelf')")
    public void userSelfUpdate(UserInfo userInfo) throws Exception {
        UserInfo old_userinfo = userService.findById(userInfo.getId());
        if (userInfo.getPassword().equals(""))
            userInfo.setPassword(old_userinfo.getPassword());
        else
            userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        userService.updateUserInfo(userInfo);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('修改成功,您需要重新登录');");
        pw.println("window.location.href='/QCU_Library/logout.do'");
        pw.println("</script>");
        pw.close();
    }

    @RequestMapping("alertUserpw.do")
    public void alertUserPw(String passwd)throws Exception{
        userService.alterPassWordBySelf(passwd,(String)session.getAttribute("userId"));
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('修改成功,您需要重新登录');");
        pw.println("window.location.href='/QCU_Library/logout.do'");
        pw.println("</script>");
        pw.close();
    }
}
