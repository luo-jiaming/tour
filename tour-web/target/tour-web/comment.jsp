<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="resources/css/comment.css">

<div class="container" id="commentDiv">

    <div class="comment-title">
        涂涂点评（共有<i id="total"></i>条真实评价）
    </div>

    <div class="comment-body"></div>

    <input type="hidden" id="size" value="5"/>
    <div class="comment-page">
        <div class="page-content">
            <ul class="pager pagination-sm">
                <li class=""><a id="firstPage" href="javascript:void(0)" value="1">首页</a></li>
                <li class=""><a id="prePage" href="javascript:void(0)" value="">上一页</a></li>
                <li class=""><a id="nextPage" href="javascript:void(0)" value="">下一页</a></li>
                <li class=""><a id="lastPage" href="javascript:void(0)" value="">尾页</a></li>
            </ul>
        </div>
        <div class="page-current">
            第<span id="pageNum">1</span>页
        </div>
        <div class="page-total">
            共<span id="pages"></span>页
        </div>
    </div>

    <div class="comment-add">
        <div class="comment-add-title comment-active">
            <span>评价</span>
        </div>
        <div class='media-left comment-add-avatar'>
            <c:if test="${user != null}">
                <img src='${user.avatar}' class='img-circle'>
            </c:if>
            <c:if test="${user == null}">
                <img src='resources/img/default.gif' class='img-circle'>
            </c:if>
        </div>
        <div class="comment-add-content">
            <textarea id="content" class="form-control" rows="10" placeholder="说点什么吧"></textarea>
            <input id="addCommentBtn" type="button" class="btn btn-style btn-warning" value="提交点评"/>
        </div>
    </div>

</div>