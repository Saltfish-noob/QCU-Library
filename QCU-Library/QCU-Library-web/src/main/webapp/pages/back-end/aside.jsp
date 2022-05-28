<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/images/user2-160x160.jpg"
                     class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${sessionScope.nickname}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>
            <li id="admin-index"><a
                    href="${pageContext.request.contextPath}/pages/back-end/main.jsp"><i
                    class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
                <span>基础数据</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>
            </a>
                <ul class="treeview-menu">

                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/book/findAll.do?page=1&pageSize=5">
                        <i class="fa fa-circle-o"></i> 书籍管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/category/findAll.do?page=1&pageSize=5"> <i
                            class="fa fa-circle-o"></i> 类别管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/borrowLog/findAll.do?page=1&pageSize=5"> <i
                            class="fa fa-circle-o"></i> 借阅记录
                    </a></li>

                </ul>
            </li>
            <li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
                <span>系统管理</span> <span class="pull-right-container"> <i
                        class="fa fa-angle-left pull-right"></i>
				</span>


            </a>
                <ul class="treeview-menu">
                    <li id="system-setting">
                            <a href="${pageContext.request.contextPath}/user/findAll.do?page=1&pageSize=5"> <i
                                    class="fa fa-circle-o"></i> 用户管理
                            </a>
                    </li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/role/findAll.do?page=1&pageSize=5"> <i
                            class="fa fa-circle-o"></i> 角色管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/permission/findAll.do?page=1&pageSize=5">
                        <i class="fa fa-circle-o"></i> 资源权限管理
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/sysLog/findAll.do?page=1&pageSize=30"> <i
                            class="fa fa-circle-o"></i> 访问日志
                    </a></li>
                    <li id="system-setting"><a
                            href="${pageContext.request.contextPath}/pages/back-end/Recommend.jsp"> <i
                            class="fa fa-circle-o"></i> 计算推荐书籍
                    </a></li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>