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
                  <div class="form-group">
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