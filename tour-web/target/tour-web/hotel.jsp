<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>酒店</title>
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

        .hotel-wrap {
            height: auto;
            margin: 20px auto;
        }

        .hotel-title {
            margin-top: 30px;
        }

        .hotel-level {
            color: #ff9d00;
            height: 10px;
            margin-top: 10px;
        }

        .split {
            width: 100%;
            height: 1px;
            background-color: #eee;
            margin-top: 20px;
        }

        .hotel-nav {
            width: 100%;
            height: 59px;
            font-size: 16px;
            border-bottom: 1px solid #eee;
        }

        .hotel-nav-general {
            text-align: center;
            width: 100px;
            height: 58px;
            padding-top: 18px;
            cursor: pointer;
            border-bottom: 3px solid #ff9d00;
            color: #ff9d00;
        }

        .hotel-img {
            width: 100%;
            height: 450px;
            padding-bottom: 25px;
            border-bottom: 1px solid #e5e5e5;
            margin-bottom: 25px;
            font-size: 16px;
            color: #333;
            line-height: 24px;
            overflow: hidden;
        }

        .hotel-img img {
            height: 100%;
            width: auto;
        }

        .hotel-intro {
            padding-bottom: 25px;
            border-bottom: 1px solid #e5e5e5;
            margin-bottom: 25px;
            font-size: 16px;
            color: #666;
            line-height: 24px;
        }

        .basic-infor, .hotel-loc, .hotel-type {
            border-bottom: 1px solid #e5e5e5;
        }

        .infor-title, .loc-title, .type-title {
            font-size: 20px;
            color: #333;
            margin-top: 20px;
            font-weight: normal;
            letter-spacing: 1px;
            margin-bottom: 15px;
        }

        .infor-body {
            color: #666;
            margin-bottom: 20px;
        }

        .not-have {
            color: #ddd;
        }

        .loc-body {
            color: #999;
        }

        .loc-map {
            margin-top: 20px;
            width: 500px;
            height: 250px;
            margin-bottom: 20px;
        }

        .type-body {
            width: 100%;
            height: auto;
            color: #666;
            margin-bottom: 20px;
        }

        .type-item {
            width: 100%;
            height: 180px;
            padding: 10px;
        }

        .type-item:hover {
            background-color: #f5f5f5;
        }

        .type-item .item-img {
            float: left;
            width: 22%;
            height: 160px;
            overflow: hidden;
        }

        .type-item .item-img img {
            height: 100%;
            width: 100%;
        }

        .type-remark {
            width: 40%;
            float: left;
            text-align: center;
        }

        .type-name {
            float: left;
            width: 40%;
            font-size: 17px;
            text-align: center;
        }

        .type-price {
            float: right;
            font-size: 20px;
            color: #ff9d00;
            margin-right: 30px;
        }

        .type-item .item-content {
            margin: 65px auto;
            width: 75%;
            float: right;
        }

        .footer {
            width: 100%;
            height: 50px;
        }


    </style>

    <script>
        $(function () {

            initHeadHotel();
            //第一页评论
            ajaxFirstComment();
            //添加评论
            addCommentBtnClick();

        });

        /**
         * 更新顶部导航栏
         */
        function initHeadHotel() {
            $('.head-nav').find('div').removeClass('head-nav-active');
            $('.head-nav').find('.head-nav-hotel').addClass('head-nav-active');
        }

        /**
         * 验证评论输入
         */
        function validateContent() {
            var content = $.trim($('#content').val());
            if (content == null || content == '') {
                $('.tip').html("客官，请输入评论内容");
                $('#modal').modal('show');
                $('#content').val("");
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
                url: "/tour/hotelComment",
                data: {"hotelId": $('#hotelid').val(), "pageNum": 1, "size": $('#size').val()},
                success: function (data) {
                    addCommentDiv(data);
                    setPage(data);
                }
            });
        }

        /**
         * 添加评论列表
         * @param data
         */
        function addCommentDiv(data) {
            var comment = "";
            var maplist = data.maplist;
            for (var i = 0; i < maplist.length; i++) {
                var newDate = new Date(maplist[i].comment.time);
                comment += "<div class='media'>" +
                        "<div class='media-left'>" +
                        "<img src='" + maplist[i].user.avatar + "' class='img-circle'>" +
                        "</div>" +
                        "<div class='media-body'>" +
                        "<h5 class='media-heading'>" + maplist[i].user.nick + "</h5>" + maplist[i].comment.content +
                        "<div class='comment-time'>" + newDate.toLocaleString() + "</div>" +
                        "</div>" +
                        "</div>";
            }
            $('.comment-body').empty();
            $('.comment-body').append(comment);
        }

        /**
         * 设置分页属性
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
         */
        function pageClick() {
            $('.pager a').each(function (i) {
                $(this).off('click');
                if ($(this).parent().attr('class') != 'disabled') {
                    $(this).click(function () {
                        var pageNum = $(this).attr('value');
                        $.ajax({
                            type: "POST",
                            url: "/tour/hotelComment",
                            data: {"hotelId": $('#hotelid').val(), "pageNum": pageNum, "size": $('#size').val()},
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
                var userId = '${user.id}';
                if (userId == '') {
                    $('.tip').html("您还没有登录哦");
                    $('#modal').modal('show');
                    $('#content').val("");
                    return;
                }
                var content = $.trim($('#content').val());
                if (validateContent()) {
                    $.ajax({
                        type: "POST",
                        url: "/tour/addHotelComment",
                        data: {"hotelId": $('#hotelid').val(), "content": content},
                        async: true,
                        success: function (data) {
                            $('.tip').html("感谢您的评论");
                            $('#modal').modal('show');
                            $('#content').val("");
                            ajaxFirstComment();
                        }
                    });
                }
            });
        }

    </script>

</head>
<body>

<%@ include file="/nav.jsp" %>
<%@ include file="/tip.jsp" %>

<input id="hotelid" type="hidden" value="${json.hotel.id}"/>

<div class="hotel-wrap container">
    <div class="hotel-title">
        <h1>${json.hotel.hotelName}</h1>
    </div>
    <div class="hotel-level">
    <c:forEach begin="1" end="${json.hotel.hotelLevel}">
        <span class='glyphicon glyphicon-star'></span>
    </c:forEach>
    </div>
    <div class="split"></div>
    <div class="hotel-nav">
        <div class="hotel-nav-general">
            <span>概况</span>
        </div>
    </div>
</div>

<div class="nav-general-content container">
    <div class="hotel-img">
        <img src='${json.hotel.indexImg}' alt=''>
    </div>

    <div class="hotel-intro">
       ${json.hotel.hotelIntroduce}
    </div>

    <div class="basic-infor">
        <div class="infor-title">基本信息</div>
        <div class="infor-body container">
            <div class="row">

                <c:if test="${json.hotel.wifi == 0}">
                <div class="col-lg-3 not-have">
                    <s><span class="glyphicon glyphicon-ok"></span> 免费WiFi</s>
                </div>
                </c:if>

                <c:if test="${json.hotel.wifi == 1}">
                    <div class="col-lg-3">
                        <span class="glyphicon glyphicon-ok"></span> 免费WiFi
                    </div>
                </c:if>

                <c:if test="${json.hotel.bath == 0}">
                    <div class="col-lg-3 not-have">
                        <s><span class="glyphicon glyphicon-ok"></span> 淋浴设施</s>
                    </div>
                </c:if>

                <c:if test="${json.hotel.bath == 1}">
                    <div class="col-lg-3">
                        <span class="glyphicon glyphicon-ok"></span> 淋浴设施
                    </div>
                </c:if>

                <c:if test="${json.hotel.park == 0}">
                    <div class="col-lg-3 not-have">
                        <s><span class="glyphicon glyphicon-ok"></span> 停车场</s>
                    </div>
                </c:if>

                <c:if test="${json.hotel.park == 1}">
                    <div class="col-lg-3">
                        <span class="glyphicon glyphicon-ok"></span> 停车场
                    </div>
                </c:if>

                <c:if test="${json.hotel.restaurant == 0}">
                    <div class="col-lg-3 not-have">
                        <s><span class="glyphicon glyphicon-ok"></span> 餐厅</s>
                    </div>
                </c:if>

                <c:if test="${json.hotel.restaurant == 1}">
                    <div class="col-lg-3">
                        <span class="glyphicon glyphicon-ok"></span> 餐厅
                    </div>
                </c:if>

            </div>
        </div>
    </div>

    <div class="hotel-loc">
        <div class="loc-title">酒店位置</div>
        <div class="loc-body">${json.hotel.location}</div>
        <div id="mapContainer" class="loc-map"></div>
    </div>

    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=368a175838176615ee0513371b307c44"></script>
    <script type="text/javascript">
        var coordinate = "${json.hotel.coordinate}";
        var array = coordinate.substring(1, coordinate.length - 1).split(",");
        var x = array[0];
        var y = array[1];
        var map = new AMap.Map('mapContainer', {
            zoom: 14,
            mapStyle: 'normal',
            center: [x, y]
        });
        var marker = new AMap.Marker({
            position: [x, y],   //marker所在的位置
            map: map            //创建时直接赋予map属性
        });
        AMap.plugin(['AMap.ToolBar', 'AMap.Scale'], function () {
            map.addControl(new AMap.ToolBar());
            map.addControl(new AMap.Scale());
        });
    </script>

    <div class="hotel-type">
        <div class="type-title">房间类型</div>
        <div class="type-body">
            <c:forEach items="${json.roomlist}" var="room" varStatus="status">
                <div class="type-item">
                    <div class="item-img">
                        <img class="img-rounded" src="${room.indexImg}" />
                    </div>
                    <div class="item-content">
                        <div class="type-remark">${room.remark}</div>
                        <div class="type-name">${room.typeName}</div>
                        <div class="type-price">¥${room.price}</div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</div>

<%@ include file="/comment.jsp" %>

<div class="footer"></div>

</body>
</html>