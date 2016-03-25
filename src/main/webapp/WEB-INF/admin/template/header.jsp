<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-default top-navbar" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".sidebar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html"><i class="fa fa-gear"></i>
			<strong>THÀNH MỸ</strong></a>
	</div>

	<ul class="nav navbar-top-links navbar-right">
		<li>
			<div style="color:white;font-size: 20px">Hello  ${username}</div>
		</li>
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#" aria-expanded="false"> <i
				class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
	</ul>
</nav>