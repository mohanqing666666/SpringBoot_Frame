<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<script type="text/javascript">

</script>
<title>Insert title here</title>
</head>
<body>
    这是个jsp页面<br/>

    id: ${record.id}  <br/>
    orderNo: ${record.orderNo} <br/>
    orderType: ${record.orderType}
</body>
</html>