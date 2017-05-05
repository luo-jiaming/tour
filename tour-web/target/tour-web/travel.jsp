<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>攻略 - 途悠游</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/holder.min.js"></script>

    <style>
        
        textarea {
            resize: none;
        }

        .container {
            width: 80%;
        }

        h1 {
            display: inline;
            margin-right: 10px;
            font-weight: normal;
            font-size: 30px;
            color: #333;
        }

        .split {
            width: 100%;
            height: 1px;
            background-color: #eee;
            margin-top: 20px;
        }

        .travel-head {
            width: 100%;
            height: 400px;
            overflow: hidden;
            margin-bottom: -80px;
        }

        .travel-head img {
            width: 100%;
        }

        .travel-title {
            position: relative;
        }

        .user-avatar {
            width: 120px;
            height: 120px;
            margin-right: 40px;
            border-radius: 60px;
            overflow: hidden;
            float: left;
        }

        .user-avatar img {
            width: 100%;
            height: 100%;
        }

        .title-name {
            width: 700px;
            float: left;
            margin-top:20px;
            color: white;
            font-size: 25px;
            overflow: hidden;
        }

        .travel-user {
            height: 30px;
            width: auto;
            margin-top: 45px;
        }

        .user-name {
            margin-right: 30px;
            float: left;
            color: #ff7200;
            font-size: 14px;
        }

        .write-time {
            padding-top: 3px;
            color: #aaa;
            font-size: 13px;
        }

        .content-div {
            padding-bottom: 50px;
            border-bottom: 1px solid #e5e5e5;
        }

        .travel-content {
            width: 70%;
            height: auto;
            float: left;
            margin-right: 6%;
            margin-top: 50px;
            color: #666;
            line-height: 25px;
            font-size: 16px;
        }

        .travel-content img {
            margin-top: 10px;
            margin-bottom: 10px;
            width: 100%;
            height: auto;
        }

        .travel-recommend {
            float: right;
            height: 520px;
            width: 24%;
            margin-top: 50px;
        }

        .travel-recommend p {
            font-size: 16px;
            color: #ff9d00;
        }

        .travel-recommend-div {
            width: 100%;
            height: 30%;
            border-radius: 5px;
            overflow: hidden;
            margin-bottom: 25px;
        }

        .travel-recommend-div img {
            width: 120%;
            height: 100%;
        }

        .travel-recommend-div .recommend-title {
            margin: -40px auto;
            padding: 10px;
            position:relative;
            color: white;
            font-weight: 500;
            text-align: center;
        }

        .travel-recommend-div .recommend-title:hover {
            color: #ff9d00;
        }

        .footer {
            width: 100%;
            height: 50px;
        }

    </style>

    <script>
        $(function () {

            initHeadTravel();
            limitTitle();
            ajaxFirstComment();
            addCommentBtnClick();

        });

        /**
         * 更新顶部导航栏
         */
        function initHeadTravel() {
            $('.head-nav').find('div').removeClass('head-nav-active');
            $('.head-nav').find('.head-nav-travel').addClass('head-nav-active');
        }

        /**
         * 限制相关游记标题的字数
         */
        function limitTitle() {
            $('.recommend-title').each(function(i) {
                var title = $(this).html();
                if (title.length > 15) {
                    title = title.substring(0, 15) + "...";
                }
                $(this).html(title);
            });
        }

        /**
         * 验证评论输入
         * private  obj=$('')
         */
        function validateContent(obj) {
            var content = $.trim(obj.val());
            if (content == null || content == '') {
                $('.tip').html("客官，请输入评论内容");
                $('#modal').modal('show');
                obj.val("");
                return false;
            }
            return true;
        }

        /**
         * 获取第一页comment
         */
        function ajaxFirstComment() {
            $.ajax({
                type: "POST",
                url: "/tour/travelComment",
                data: {"travelId": $('#travelid').val(), "pageNum": 1, "size": $('#size').val()},
                success: function (data) {
                    addCommentDiv(data);
                    setPage(data);
                }
            });
        }

        /**
         * 添加评论列表
         * private
         * @param data
         */
        function addCommentDiv(data) {
            var comment = "";
            var maplist = data.maplist;
            var userId = "${user.id}";            //登录用户
            var authorId = "${json.user.id}";     //游记作者
            for (var i = 0; i < maplist.length; i++) {
                var trashDiv = "";
                var newDate = new Date(maplist[i].comment.time);
                var applyContent = "";
                if (maplist[i].applyComment != undefined) {     //判断是否是回复的评论
                    var applyDate = new Date(maplist[i].applyComment.time);
                    applyContent = "<div class='comment-reference'>引用 " + maplist[i].applyUser.nick + " 发表于 " + applyDate.toLocaleString() + " 的回复:</div>";
                }
                if (userId == authorId) {                      //判断该游记是否是该用户写的
                    trashDiv += "<div class='comment-operate'><span class='glyphicon glyphicon-trash'><input type='hidden' value='" + maplist[i].comment.id + "' /></span></div>";
                }
                comment += "<div class='media'>" +
                                "<div class='media-left'>" +
                                    "<img src='" + maplist[i].user.avatar + "' class='img-circle'>" +
                                "</div>" +
                                "<div class='media-body'>" +
                                    "<h5 class='media-heading'>" + maplist[i].user.nick + "</h5>" +
                                    applyContent +
                                    maplist[i].comment.content +
                                    "<div class='comment-time-div'>" +
                                        "<div class='comment-time'>" + newDate.toLocaleString() + "</div>" +
                                        trashDiv +
                                        "<div class='comment-reply'>回复</div>" +
                                    "</div>" +
                                    "<div class='comment-reply-div'>" +
                                        "<input class='touid' type='hidden' value='" + maplist[i].user.id + "' />" +
                                        "<textarea class='form-control reply-content' rows='5' placeholder='回复 " + maplist[i].user.nick + " :'></textarea>" +
                                        "<input type='button' class='btn btn-style btn-warning btn-sm reply' value='回复'>" +
                                        "<input type='button' class='btn btn-default btn-sm cancel' value='收起'>" +
                                    "</div>" +
                                "</div>" +
                           "</div>";
            }
            $('.comment-body').empty();
            $('.comment-body').append(comment);
            trashClick();
            cancelBtnClick();
            reply();
            replyBtnClick();
        }

        /**
         * 点击回复，出现回复输入框
         */
        function reply() {
            $('.comment-reply').each(function() {
                $(this).click(function() {
                    $('.cancel').click();
                    $(this).parent().next().slideDown();
                    $(this).hide();
                });
            });
        }

        /**
         * 点击取消按钮时间，恢复之前的样子
         */
        function cancelBtnClick() {
            $(".cancel").each(function() {
                $(this).click(function() {
                    $(this).parent().slideUp();
                    $(this).parent().prev().find('.comment-reply').show();
                });
            });
        }

        /**
         * 点击回复按钮事件
         */
        function replyBtnClick() {
            $('.reply').each(function() {
                $(this).click(function() {
                    if (validateUser()) {
                        var textarea = $(this).parent().find('textarea');
                        var content = $.trim(textarea.val());
                        var touid = $(this).parent().find('.touid');
                        var applycid = $(this).parent().find('.applycid');
                        if (validateContent(textarea)) {
                            $.ajax({
                                type: "POST",
                                url: "/tour/addTravelComment",
                                data: {"travelId": $('#travelid').val(), "content": content, "applyCid": applycid.val()},
                                async: true,
                                success: function (data) {
                                    $('.tip').html("回复成功");
                                    $('#modal').modal('show');
                                    $('#content').val("");
                                    ajaxFirstComment();
                                }
                            });
                        }
                    }
                });
            });

        }

        /**
         * 评论删除点击事件
         * private
         */
        function trashClick() {
            $('.glyphicon-trash').each(function(i) {
                $(this).click(function() {
                    var commentId = $(this).find('input').val();
                    $('#dialog').modal('show');
                    confirmBtnClick(commentId);
                });
            });
        }

        /**
         * 确认删除按钮点击事件
         * private
         */
        function confirmBtnClick(id) {
            $('#confirm').click(function() {
                $.ajax({
                    type: "POST",
                    url: "/tour/delTravelComment",
                    data: {"id": id},
                    async: true,
                    success: function (data) {
                        ajaxFirstComment();
                        $('.tip').html("删除成功");
                        $('#modal').modal('show');
                    }
                });
            });
        }

        /**
         * 设置分页属性
         * private
         * @param data
         */
        function setPage(data) {
            var firstPage = data.pageinfor.firstPage;
            var prePage = data.pageinfor.prePage;
            var nextPage = data.pageinfor.nextPage;
            var lastPage = data.pageinfor.lastPage;
            var pageNum = data.pageinfor.pageNum;
            var pages = data.pageinfor.pages;

            $('#total').html(data.pageinfor.total);
            $('#firstPage').attr('value', firstPage);
            $('#prePage').attr('value', prePage);
            $('#nextPage').attr('value', nextPage);
            $('#lastPage').attr('value', lastPage);
            $('#pageNum').html(pageNum);
            $('#pages').html(pages);

            //设置分页样式
            $('.pager li').removeClass('disabled');
            if (prePage == 0) {
                $('#prePage').parent().addClass('disabled');
            }
            if (nextPage == 0) {
                $('#nextPage').parent().addClass('disabled');
            }
            if (pageNum == 1) {
                $('#firstPage').parent().addClass('disabled');
            }
            if (pageNum == pages) {
                $('#lastPage').parent().addClass('disabled');
            }
            pageClick();        //绑定事件
        }

        /**
         * 分页按钮绑定事件
         * private
         */
        function pageClick() {
            $('.pager a').each(function (i) {
                $(this).off('click');
                if ($(this).parent().attr('class') != 'disabled') {
                    $(this).click(function () {
                        var pageNum = $(this).attr('value');
                        $.ajax({
                            type: "POST",
                            url: "/tour/travelComment",
                            data: {"travelId": $('#travelid').val(), "pageNum": pageNum, "size": $('#size').val()},
                            success: function (data) {
                                addCommentDiv(data);
                                setPage(data);
                            }
                        });
                    });
                }
            });
        }

        /**
         * 添加评论
         */
        function addCommentBtnClick() {
            $('#addCommentBtn').click(function () {
                if (validateUser()) {
                    var content = $.trim($('#content').val());
                    if (validateContent($('#content'))) {
                        $.ajax({
                            type: "POST",
                            url: "/tour/addTravelComment",
                            data: {"travelId": $('#travelid').val(), "content": content},
                            async: true,
                            success: function (data) {
                                $('.tip').html("感谢您的评论");
                                $('#modal').modal('show');
                                $('#content').val("");
                                ajaxFirstComment();
                            }
                        });
                    }
                }
            });
        }

        /**
         * 验证是否用户登录
         * private
         */
        function validateUser() {
            var userId = '${user.id}';
            if (userId == '') {
                $('.tip').html("您还没有登录哦");
                $('#modal').modal('show');
                $('#content').val("");
                return false;
            }
            return true;
        }

    </script>

</head>
<body>

<%@ include file="/nav.jsp" %>
<%@ include file="/tip.jsp" %>

<input id="travelid" type="hidden" value="${json.travel.id}"/>

<div class="travel-head">
    <img src="${json.travel.img}" />
</div>

<div class="travel-title container">
    <div class="user-avatar">
        <img src="${json.user.avatar}" />
    </div>
    <div class="title-name">
        ${json.travel.title}
        <div class="travel-user">
            <div class="user-name">
                ${json.user.nick}
            </div>
            <div class="write-time">
                ${json.travel.time}
            </div>
        </div>
    </div>
</div>

<div class="split"></div>

<div class="container content-div">

    <div class="travel-content">
        ${json.travel.content}
    </div>

    <div class="travel-recommend">
        <p>相关游记</p>
        <c:forEach items="${json.list}" var="travel" >
            <a href="/tour/travel?id=${travel.id}" target="_blank">
                <div class="travel-recommend-div">
                    <img src="${travel.img}" />
                    <div class="recommend-title">${travel.title}</div>
                </div>
            </a>
        </c:forEach>
    </div>

</div>

<%@ include file="/comment.jsp" %>

<div class="footer"></div>

<!-- 弹出框 -->
<div class="modal fade" id="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p>你确定要删除该评论吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-style btn-warning" id="confirm" data-dismiss="modal">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>