package edu.qcu.controller;

import edu.qcu.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/Recommend")
public class RecommendController {

    private IRecommendService recommendService;

    @Autowired
    public void setRecommendService(IRecommendService recommendService) {
        this.recommendService = recommendService;
    }

    private HttpServletResponse response;

    @Autowired
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }


    @RequestMapping("/calculateUserRelation.do")
    @PreAuthorize("hasAuthority('Permission_CalculateUserRelation')")
    public void calculateRecommendValue() throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        recommendService.calculateRecommendValue();
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('更新完成');");
        pw.println("window.location.href='/QCU_Library/pages/back-end/Recommend.jsp'; ");
        pw.println("</script>");
        pw.close();
    }

    @RequestMapping("/calculateBookRelation.do")
    @PreAuthorize("hasAuthority('Permission_CalculateBookRelation')")
    public void calculateBookRelation()throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        recommendService.calculateBookRelation();
        PrintWriter pw = response.getWriter();
        pw.println("<script>");
        pw.println("alert('更新完成');");
        pw.println("window.location.href='/QCU_Library/pages/back-end/Recommend.jsp'; ");
        pw.println("</script>");
        pw.close();
    }
}
