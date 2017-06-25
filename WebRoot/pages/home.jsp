<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%><%@taglib uri="/struts-tags" prefix="s"%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + path + "/";
	int port = request.getServerPort();
	if(port == 8080)
	{
		basePath = request.getScheme() + "://"
			+ request.getServerName() + ":"+ port + path + "/";
	}
%><jsp:include page="/includes/head.jsp" />

<div class="row">
<div class="hidden-xs col-md-2 col-xs-2"></div>   

<div class="content visible-md-block visible-lg-block col-md-20 col-xs-20">
    <div class="row">

        <div class="col-md-6 col-xs-6">
          <div id="myCarousel" class="carousel slide g-box-1">
          <!-- 轮播（Carousel）指标 -->
          <ol class="carousel-indicators">
          	<s:iterator value="#lunbos" var="val" status="sta">
              <li data-target="#myCarousel" data-slide-to="${sta.index}" class="<s:if test="#sta.index == 0">active</s:if>"></li>
           	</s:iterator>
          </ol>   
          <!-- 轮播（Carousel）项目 -->
          <div class="carousel-inner">
          	<s:iterator value="#lunbos" var="val" status="sta">
              <div class="item <s:if test="#sta.index == 0">active</s:if>">
                  <img src="<%=basePath%>${val.headPath }" alt="${val.title }">
                  <a href="<%=basePath%>mm/${val.id }.html" class="carousel-caption">${val.title }</a>
              </div>
          	</s:iterator>
          </div>
      </div>
      </div>
      

      <div class="col-md-12 col-xs-12">
        <div class="g-box-2">
          
        <div class="g-title">
          <h4 class="notice">
          <s:iterator value="#zhu" var="val" status="sta">
          <a href="<%=basePath%>mm/${val.id }.html">${val.title }</a>
          </s:iterator>
          </h4>
        </div>
        <div class="row g-title-2">

            <div class="col-xs-12">
              
            <div class="list-group g-box-2-1">
            <s:iterator value="#fu_left" var="val" status="sta">
              <div class="list-group-item notice">
                <span class="glyphicon glyphicon-heart pull-left"></span>
                <a href="<%=basePath%>mm/${val.id }.html">${val.title }</a>
              </div>
             </s:iterator>  
            </div>
            </div>
           
            <div class="col-xs-12">

            <div class="list-group g-box-2-2">
			<s:iterator value="#fu_right" var="val" status="sta">
              <div class="list-group-item notice">
                <span class="glyphicon glyphicon-heart pull-left"></span>
                <a href="<%=basePath%>life/${val.id }.html">${val.title }</a>
              </div>
             </s:iterator> 
            </div>

            </div>
            
        </div>

        </div>
      </div>
      
    <div class="col-md-6 col-xs-6">
        <div class="g-box-3">
          
        <div class="g-title">
          <h4>美女排行</h4>
        </div>
        <div class="list-group">
        <s:iterator value="#rank_mm" var="val" status="sta">
           <div class="list-group-item notice">
            <span class="badge pull-left"><s:property value="#sta.index+1" /></span>
            <a href="<%=basePath%>mm/${val.id }.html">${val.title }</a>
          </div>
          </s:iterator> 
      </div>

        </div>
    </div>

    </div>
</div>  <!-- content end -->

<div class="hidden-xs col-md-2 col-xs-2"></div>     
</div><!-- row end -->

<div class="row">
  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 

  <div class="content-2 col-md-20 col-xs-24">

  <div class="row">

  <div class="col-md-18 col-xs-24">

  <div class="row g-thumbnail g-box-4">
    <div class="g-title">
          <h4>美女图条</h4>
    </div>
    <s:iterator value="#tutiao_mm" var="val" status="sta">
    <div class="col-sm-8 col-md-6 col-xs-12">
      <div class="thumbnail">
        <a href="<%=basePath%>mm/${val.id }.html"><img src="<%=basePath%>${val.headPath }" alt="${val.title }"></a>
        <div class="caption notice">
          <a href="<%=basePath%>mm/${val.id }.html">${val.title }</a>
        </div>
      </div>
    </div>
	</s:iterator>
    <div class="g-title col-xs-24">
          <h5><a href="<%=basePath%>mm">查看更多...</a></h5>
    </div>

  </div>

  <div class="row g-thumbnail g-box-4-1">
    <div class="g-title">
          <h4>生活图条</h4>
    </div>
    <s:iterator value="#tutiao_life" var="val" status="sta">
    <div class="col-sm-8 col-md-6 col-xs-12">
      <div class="thumbnail">
        <a href="<%=basePath%>life/${val.id }.html"><img src="<%=basePath%>${val.headPath }" alt="${val.title }"></a>
        <div class="caption notice">
          <a href="<%=basePath%>life/${val.id }.html">${val.title }</a>
        </div>
      </div>
    </div>
    </s:iterator>
    <div class="g-title col-xs-24">
          <h5><a href="<%=basePath%>life">查看更多...</a></h5>
    </div>

  </div>

  </div>

  <div class="visible-md-block visible-lg-block col-md-6 col-xs-6">

  <div class="g-box-5">
        <div class="g-title">
          <h4>精彩美女推荐</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#tuijian_mm" var="val" status="sta">
           <div class="list-group-item notice">
            <span class="glyphicon glyphicon-heart pull-left"></span>
            <a href="<%=basePath%>mm/${val.id }.html">${val.title }</a>
          </div>
         </s:iterator>
      </div>
  </div>

  <div class="g-box-5-1">
        <div class="g-title">
          <h4>生活排行</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#rank_life" var="val" status="sta">
          <div class="list-group-item notice">
            <span class="badge pull-left"><s:property value="#sta.index+1" /></span>
            <a href="<%=basePath%>life/${val.id }.html">${val.title }</a>
          </div>
        </s:iterator> 
      </div>
  </div>

  <div class="g-box-5-2">
        <div class="g-title">
          <h4>精彩生活推荐</h4>
        </div>
        <div class="list-group">
		<s:iterator value="#tuijian_life" var="val" status="sta">
           <div class="list-group-item notice">
            <span class="glyphicon glyphicon-heart pull-left"></span>
            <a href="<%=basePath%>life/${val.id }.html">${val.title }</a>
          </div>
        </s:iterator>  
      </div>
  </div>

  </div>
  

  </div> 
<!-- row end -->
  </div>

  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 
</div>



<div class="row">
  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 
   <div class="content-3 col-md-20 col-xs-24">
      <div  class="g-box-6">
          <div class="g-title-3">
              <h6>热搜: 
              <a href="#">美女</a>
              <a href="#">美女图片</a>
              <a href="#">性感</a>
              </h6>
          </div>
      </div>
      
   </div>
  <div class="visible-md-block visible-lg-block col-md-2 col-xs-2"></div> 
</div>

<jsp:include page="/includes/foot.jsp" />
<script type="text/javascript">
        $('.carousel').carousel();
</script>
</html>