<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/includes/head.jsp" />
<div>
   <nav class="navbar navbar-fixed-top my-navbar" role="navigation">  
        <div class="container-fluid tutiao-content3">  
            <div class="navbar-header">  
                <button type="button" class="navbar-toggle" data-toggle="collapse"  
                        data-target="#my-navbar-collapse">  
                    <span class="sr-only">切换导航</span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                </button>  
                <div><img class="navbar-brand" href="<%=basePath%>" src="<%=basePath%>images/logo.png" class="img-rounded"></div>
            </div>  
            <div class="collapse navbar-collapse" id="my-navbar-collapse">  
                <ul class="nav navbar-nav">  
                    <li ><a class="ac" href="#" id="nav_kan">看图条</a></li>  
                    <li ><a class="ac" href="#" id="nav_search" style="display: none;">搜索结果</a></li>
                </ul>  

                <div class="navbar-form navbar-right" role="search">
                  <div class="form-group index-user-nav">
                  
                  	<s:if test="#session.user != null">
                  			<li><a href="https://www.script-tutorials.com/category/resources/"><s:property value="#session.user.name" /></a>
					            <ul class="subs">
					                <li><a href="<%=basePath%>user_loginOut">退出</a></li>
					            </ul>
					        </li>
						</s:if>
						<s:else>
							<div class="input-group index-btn-login" id="btn-login">
	                       	登录
	                       </div>
						</s:else>
						
                      <div class="input-group">
                      <input type="text" class="form-control glyphicon glyphicon-search" placeholder="Search">
                      <span class="glyphicon glyphicon-search input-group-addon"></span>
                      </div>
                  </div>
                  
              </div> 

            </div> 

        </div>  
    </nav>  

</div>

<div class="tutiao-content container">

<div class="row" id="tutiao_show_row">
    <div class="col-sm-8" >
         <div class="thumbnail" id="tutiao_show" data-tid="${tid}">
            <img src="" alt="" width="800px" id="tutiao_show_img">
             <div class="caption text-center">
                <button type="button" class="btn btn-primary btn-sm" id="tutiao-pic-left">
                  <span class="glyphicon glyphicon-step-backward"></span> 上一个
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="pic_lager">
                  <span class="glyphicon glyphicon-search"></span> 大图
                </button>
                <small>&nbsp;&nbsp;&nbsp;</small>
                <button type="button" class="btn btn-primary btn-sm" id="tutiao-pic-right">
                  <span class="glyphicon glyphicon-step-forward"></span> 下一个
                </button>
             </div>
        </div>

    </div>
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-body">
    
                <h4 id="tutiao_show_title"></h4>
                <span class="tutiao-abstract-index"><em id="tutiao_show_curr_page">1</em><span class="tutiao-abstract-index">/2</span></span>
                
                <p id="tutiao_show_des"> </p>
                
            </div>
        </div>

    </div>
    
</div>



<div class="row" id="tuijian_row">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body">
                
                <div class="tutiao-panel-body-d">
                    <div class="row">
                        <div class="col-sm-8">
                            <h4 id="tuijian_title"></h4>
                        </div>
                        <div class="col-sm-4">
                            <small class="pull-right">
                            <button type="button" class="btn btn-primary btn-sm" id="tuijian_refresh">
                                  <span class="glyphicon glyphicon-refresh"></span> 重新浏览
                                </button>
                                <button type="button" class="btn btn-primary btn-sm" id="tuijian_next">
                                  <span class="glyphicon glyphicon-step-forward"></span> 下一图条
                                </button>
                            </small>
                        </div>
                    </div>
    
                </div>
                
				<div id="tuijian_data" class="masonry">
				
				</div>
                
                
            </div>
        </div>
    </div>
</div>



<div class="row" id="div_xiangguan">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" >
                <h3>相关推荐</h3>
                
                <div id="about_row" class="masonry">
                
                </div>
                  
            </div>
        </div>
    </div>
</div>

<div class="row" id="div_pinglun">
    <div class="col-sm-12">
        <div class="panel panel-default">
            <div class="panel-body" >
                <span class="tutiao-abstract-index"><em id="tutiao_pinglun_num">39</em><span class="tutiao-abstract-index">条评论</span></span>
                
                <div id="pinglun_row" style="margin-top:20px;">
                	<div class="input-group col-sm-6">
                      <textarea class="form-control glyphicon" id="tutiao-pinglun-content" rows="2" placeholder="写下您的评论..."></textarea>
                      <span class="glyphicon input-group-addon" id="tutiao-btn-pinglun">评论</span>
                      </div>
                </div>
                <ul class="list-group col-sm-6" style="margin-top:20px;" id="tutiao-pinglun-ul">
				   <%--  <li class="list-group-item">
				    	<p class="text-left"><a>用户名</a> <small class="text-muted">9天前</small></p>
				    	<p class="text-left">内容</p>
				        <p class="pull-right text-muted" id="tutiao-btn-zan"><span style="margin-right:5px;">3</span><span class="glyphicon glyphicon-thumbs-up"></span></p><br>
				   	</li> --%>
				   
				</ul>
				<div class="col-sm-12">
					<button type="button" class="btn btn-link" id="tutiao-morepinglun">查看更多评论</button>
				</div>
				
            </div>
        </div>
    </div>
</div>


<div class="row" id="div_search">
    <div class="col-sm-12">
         <div id="search_row" class="masonry">
                
         </div>
    </div>
</div>

</div>


<div class="tutiao-d-large-show thumbnail" id="img_large_show">
    <img src="">
</div>

<jsp:include page="/includes/foot.jsp" />
<script src="<%=basePath%>scripts/main2.js"></script>