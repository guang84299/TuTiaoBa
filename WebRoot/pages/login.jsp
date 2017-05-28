<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + path + "/";
	int port = request.getServerPort();
	if(port == 8080)
	{
		basePath = request.getScheme() + "://"
			+ request.getServerName() + ":"+ port + path + "/";
	}
%>
<jsp:include page="/includes/head.jsp" />

  <div class="login-logo-wrap">
   <a href="<%=basePath%>"> <img src="<%=basePath%>images/logo.png"  class="img-responsive"></a>
  </div>

  <div class="well login-content" data-toggle="<s:if test="#request.active == true">tooltip</s:if>" title="<s:if test="#request.active == true">邮箱激活成功，请登录</s:if>">
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label for="firstname" class="col-xs-2 control-label"></label>
          <div class="col-xs-8">
            <input type="text" class="form-control" id="username" placeholder="用户名">
          </div>
        </div>
        
        <div class="form-group">
          <label for="inputPassword" class="col-xs-2 control-label"></label>
          <div class="col-xs-8">
            <input type="password" class="form-control" id="password" placeholder="密码">
          </div>
        </div>
        <div class="form-group">
          <label class="col-xs-2 control-label"></label>
          <div class="col-xs-5">
            <input type="text" class="form-control" id="code" placeholder="验证码">
          </div>
          <h4 class="col-xs-2" ><span class="label label-default">${sessionScope.code }</span></h4>
        </div>

        <div id="login-btn-login">
          登 陆
        </div>
        <div id="login-btn-toreg">注册</div>
        <div class="form-group" id="login-pro" style="display:none;">
        	<label class="col-xs-5 control-label"></label>
        	<img class="col-xs-2" src="<%=basePath%>images/pro.gif">
        </div>
        <small id="btn-lost" class="pull-right"><a>忘记密码?</a></small>
      </form>

  </div>
<jsp:include page="/includes/foot.jsp" />  
<script >
var baseUrl =  window.location.protocol + "//" + window.location.host + "/";
		
  $(function(){
  
  function judgmentClient(){
	    var UserClient = navigator.userAgent.toLowerCase();
	    var IsIPad = UserClient.match(/ipad/i) == "ipad";
	    var IsIphoneOs = UserClient.match(/iphone os/i) == "iphone os";
	    var IsMidp = UserClient.match(/midp/i) == "midp";
	    var IsUc7 = UserClient.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	    var IsUc = UserClient.match(/ucweb/i) == "ucweb";
	    var IsAndroid = UserClient.match(/android/i) == "android";
	    var IsCE = UserClient.match(/windows ce/i) == "windows ce";
	    var IsWM = UserClient.match(/windows mobile/i) == "windows mobile";
	    if(IsIPad || IsIphoneOs || IsMidp || IsUc7 || IsUc || IsAndroid || IsCE || IsWM){
	        return true;
	    }else{
	    	return false;
	    }
	}
	
	var isMobile = judgmentClient();

      function validateName(name){  
          if(name == null || name.length == 0)
          {
            alert("用户名不能为空！");
            return false;
          }
          
          return true;
      };

      function validatePassword(password){  
          if(password == null || password.length == 0)
          {
            alert("密码不能为空！");
            return false;
          }
          return true;
      };

      function validateCode(code){  
          if(code == null || code.length == 0)
          {
            alert("请输入验证码！");
            return false;
          }
          return true;
      };


      $("#login-btn-login").click(function(){
      
      	 var name = $("#username").val();
         var passwor = $("#password").val();
         var cod = $("#code").val();
        
        if(validateName(name))
        {
          if(validatePassword(passwor))
          {
            if(validateCode(cod))
            {
            	 $("#login-pro").show();
            	 $("#login-btn-login").hide();
                 $.ajax({
		            type: "post",
		            data: {username: name,password:passwor,code:cod},
		            url: baseUrl + "user_vallogin"
		          }).done(function(result) {
		            if(result == 'true')
		            {
		            	location.href = baseUrl;
		            }
		            else
		            {
		            	if(result == '1')
		            		alert("用户名或密码不正确！");
		            	else if(result == '2')
		            		alert("验证码不正确！");
		            	else if(result == '3')
		            		alert("您的邮箱还没有激活！");
		            	window.location.reload();
		            }
		          });
            }
          }
        }
      });
      
      $("[data-toggle='tooltip']").tooltip();
      
       $("#login-btn-toreg").click(function(){
		location.href = baseUrl + "user_toRegister";
	});
	
	$(".login-logo-wrap").click(function(){
		location.href = baseUrl;
	});
  
  $('#particles').particleground({
        dotColor: '#faeaea',
        lineColor: '#faeaea'
      });
      
      if(isMobile)
      {
      		$(".well").addClass("login-content-m");
      }

  });
  
 
</script>
