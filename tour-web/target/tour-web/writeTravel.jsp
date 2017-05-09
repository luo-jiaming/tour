<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加文字 - 写游记</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="resources/css/bootstrap.min.css">

    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/holder.min.js"></script>

    <link rel="stylesheet" href="resources/fileinput/css/fileinput.min.css">
    <script src="resources/fileinput/js/fileinput.min.js"></script>
    <script src="resources/fileinput/js/locales/zh.js"></script>

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
            margin-bottom: 10px;
            background-image: url("resources/img/page_bg.jpg");
        }

        .travel-head img {
            width: 100%;
        }

        .travel-head-wrap {
            width: 40%;
            height: 25%;
            margin: 180px auto;
            margin-bottom: 30px;
        }

        .setting-img {
            font-size: 70px;
            color: white;
            float: left;
        }

        .setting-wrap {
            float: left;
            width: 80%;
            margin-left: 5%;
        }

        .setting-title {
            margin-top: 3%;
            font-size: 23px;
            color: #666;
        }

        .setting-tip {
            margin-top: 3%;
            font-size: 16px;
            color: #888;
        }

        .travel-title {
            margin: 0 auto;
            width: 70%;
            height: 15%;
            box-shadow: 0 2px 2px rgba(110, 98, 85, .5);
        }

        .travel-title input {
            height: 100%;
            border-radius: 0;
            font-size: 20px;
        }

        .travel-content {
            width: 70%;
            height: auto;
            float: left;
            margin-right: 8%;
            margin-top: 30px;
            color: #444;
            line-height: 25px;
            font-size: 16px;
        }

        .addBar .bar {
            height: 28px;
            width: 50%;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .bar .addDiv {
            width: 15%;
            height: 100%;
            float: left;
        }

        .bar .addText, .bar .addImg {
            width: 33%;
            height: 100%;
            float: left;
        }

        .bar .addDiv .addIcon {
            background: url(resources/img/nm_sprite4.png) no-repeat;
            background-size: 196px;
            background-position: 0 0;
            width: 28px;
            height: 28px;
            float: left;
        }

        .bar .addText .textIcon {
            background: url(resources/img/nm_sprite4.png) no-repeat;
            background-size: 196px;
            background-position: -84px 0px;
            width: 28px;
            height: 28px;
            float: left;
        }

        .bar .addImg .imgIcon {
            background: url(resources/img/nm_sprite4.png) no-repeat;
            background-size: 196px;
            background-position: -56px 0px;
            width: 28px;
            height: 28px;
            float: left;
        }

        .bar .iconText {
            height: 100%;
            float: left;
            margin-left: 10px;
            padding-top: 2px;
        }

        .bar .iconText:hover {
            color: #ff9d00;
            cursor: pointer;
        }

        .travel-content img {
            margin-top: 10px;
            margin-bottom: 10px;
            width: 100%;
            height: auto;
        }

        .travel-recommend {
            float: right;
            height: 300px;
            width: 22%;
            background-color: white;
            margin-top: 100px;
        }

        .bottom {
            margin-top: 20%;
            margin-bottom: 5%;
            height: 100px;
        }

        .select-spot {
            width: 30%;
            height: 35%;
        }

        .select-label {
            margin-top: 2%;
            float: left;
            color: #ff9d00;
        }

        .select {
            float: right;
            width: 70%;
        }

        .travel-tail {
            width: 70%;
        }

        .btn-style {
            margin-top: 10px;
            background-color: #ff9d00;
            color: white;
            float: right;
        }

        .btn-style:hover {
            background-color: #ff8d00;
            color: white;
        }

        .btn-cancel {
            margin-top: 10px;
            float: right;
            margin-right: 10px;
        }

    </style>

    <script>
        $(function () {

            initHead();

            initAllSpot();

            /**
             * 攻略头部图片
             */
            $('.setting-img').click(function () {
                $('#uploadindeximg').click();
            });

            $('#uploadindeximg').fileinput({
                language: 'zh',
                uploadUrl: "uploadindeximg",
                enctype: 'multipart/form-data',
                showCaption: false,
                showPreview: true,
                showUploadedThumbs: true,
                showBrowse: false,
                uploadAsync: true,
                allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],
                dropZoneEnabled: false
            }).on('fileuploaded', function (event, data, previewId, index) {
                var res = data.response;
                $('.travel-head').css("background-image", "url(" + res.src + ")");
            });

            fileInput($('.travelimg').last());
            bindEvent();
            reportBtnClick();

        });

        /**
         * 更新顶部导航栏
         */
        function initHead() {
            $('.head-nav').find('div').removeClass('head-nav-active');
        }

        /**
         * 加载所有景点
         */
        function initAllSpot() {
            $('#spotId').empty();
            $.ajax({
                type: "POST",
                url: "/tour/getAllSpot",
                success: function(data) {
                    var inner = "";
                    for (var i = 0; i<data.length; i++) {
                        inner += " <option value='" + data[i].id + "'>" + data[i].spotName + "</option>";
                    }
                    $('#spotId').append(inner);
                }
            });
        }

        function getContentDiv(obj, className) {
            return obj.parent().parent().nextAll('.' + className);
        }

        /**
         * 在obj后面加addBar
         * @param obj
         */
        function addBarDiv(obj) {
            var inner = "<div class='addBar'>" +
                    "<div class='bar'>" +
                    "<div class='addDiv'>" +
                    "<div class='addIcon'></div>" +
                    "</div>" +
                    "<div class='addText'>" +
                    "<div class='textIcon'></div>" +
                    "<div class='iconText write'>添加文字</div>" +
                    "</div>" +
                    "<div class='addImg'>" +
                    "<div class='imgIcon'></div>" +
                    "<div class='iconText photo'>添加照片</div>" +
                    "</div>" +
                    "</div>" +
                    "<div class='textContent' style='display: none'>" +
                    "<textarea class='form-control' rows='10'></textarea>" +
                    "<input class='btn btn-style btn-warning btn-finish' type='button' value='完成' />" +
                    "<input class='btn btn-default btn-cancel' type='button' value='取消' />" +
                    "</div>" +
                    "<div class='imgContent' style='display: block'>" +
                    "<input class='travelimg' type='file' accept='image/*' name='file' class='file-loading'/>" +
                    "</div>" +
                    "</div>";
            obj.after(inner);
        }

        /**
         * 渲染文件框
         * @param obj
         */
        function fileInput(obj) {
            obj.fileinput({
                language: 'zh',
                uploadUrl: "uploadtravelimg",
                enctype: 'multipart/form-data',
                showCaption: false,
                showPreview: true,
                showUploadedThumbs: true,
                showBrowse: false,
                uploadAsync: true,
                allowedFileExtensions: ['jpg', 'gif', 'png', 'jpeg'],
                dropZoneEnabled: false
            }).on('fileuploaded', function (event, data, previewId, index) {
                var res = data.response;
                var inner = "<img src='" + res.src + "' />";
                var thisAddBar = obj.parent().parent();         //找到该文件框对应的addBar
                thisAddBar.after(inner);                        //在该addBar后面追加图片
                addBarDiv(thisAddBar.next());                   //在图片之后追加addBar
                var newAddBar = thisAddBar.next().next();       //获得新的addBar
                fileInput(newAddBar.find('.travelimg'));        //将追加的addBar中的文件框渲染
            });
        }

        /**
         * 绑定事件
         */
        function bindEvent() {
            $('.travel-content').on('click', '.write', function () {
                getContentDiv($(this), 'imgContent').hide();
                getContentDiv($(this), 'textContent').show();
            });

            $('.travel-content').on('click', '.photo', function () {
                getContentDiv($(this), 'imgContent').find('input').fileinput('clear');
                getContentDiv($(this), 'imgContent').show();
                getContentDiv($(this), 'imgContent').find('input').click();
                getContentDiv($(this), 'textContent').hide();
            });

            $('.travel-content').on('click', '.btn-finish', function () {
                var textArea = $(this).prev();                     //获得该按钮对应的TextArea
                var textAreaContent = textArea.val();
                if (textAreaContent == '') {
                    $('.tip').html("亲爱的涂涂，请输入文本");
                    $('#modal').modal('show');
                    return;
                }
                $(this).parent().hide();
                var inner = "<p>" + textAreaContent + "</p>";
                var thisAddBar = textArea.parent().parent();       //该按钮对应的所属addBar
                thisAddBar.after(inner);                           //addBar追加文本
                addBarDiv(thisAddBar.next());                      //再追加的文本后面再加一个addBar
                var newAddBar = thisAddBar.next().next();          //获得新的addBar
                fileInput(newAddBar.find('.travelimg'));           //找到追加之后的addBar中的文件选择框并将其渲染
            });

            $('.travel-content').on('click', '.btn-cancel', function () {
                $(this).parent().hide();
            });
        }

        /**
         * 验证输入参数
         */
        function validateTravel() {
            var title = $('#travel-title').val();
            if ($.trim(title) == '') {
                $('.tip').html("亲爱的涂涂，游记标题还没有输入哦");
                $('#modal').modal('show');
                return false;
            }
            var reg = new RegExp('"', "g");
            var imgurl = $('.travel-head').css("background-image").replace("url(", "").replace(")", "").replace(reg, "");
            if (imgurl == "http://localhost:8081/tour/resources/img/page_bg.jpg") {
                $('.tip').html("亲爱的涂涂，游记主页照片还没有上传哦");
                $('#modal').modal('show');
                return false;
            }
            var spotid = $('select').val();
            if (spotid == '') {
                $('.tip').html("亲爱的涂涂，您还没有选择和游记关联的目的地哦");
                $('#modal').modal('show');
                return false;
            }
            var content = $('.travel-content');
            if (content.find('p').html() == null && content.find('img').html() == null) {
                $('.tip').html("亲爱的涂涂，您还没有写入任何东西哦");
                $('#modal').modal('show');
                return false;
            }
            return true;
        }

        /**
         * 得到游记的具体内容（数据库需要存的数据）
         * @returns {*|jQuery}
         */
        function getContent() {
            $('.addBar').remove();
            return $('.travel-content').html();
        }

        /**
         * 发表游记按钮点击事件
         */
        function reportBtnClick() {
            $('#report').click(function() {
                if (validateTravel()) {
                    var content = getContent();
                    $('#content').val(content);
                    var reg = new RegExp('"', "g");
                    var imgurl = $('.travel-head').css("background-image").replace("url(", "").replace(")", "").replace(reg, "");
                    $('#img').val(imgurl);
                    $('#travelform').submit();
                }
            });
        }

    </script>
</head>
<body>

<%@ include file="/nav.jsp" %>
<%@ include file="/tip.jsp" %>

<form id="travelform" action="/tour/savetravel" method="post">

    <div class="travel-head">

        <div class="travel-head-wrap">
            <div class="setting-img">
                <span class="glyphicon glyphicon-picture"></span>
            </div>
            <div class="setting-wrap">
                <div class="setting-title">
                    点击左侧设置游记头图
                </div>
                <div class="setting-tip">
                    图片建议选择尺寸大于1680px的高清大图，如相机原图
                </div>
            </div>
        </div>

        <div class="travel-title">
            <input id="travel-title" name="title" type="text" class="form-control" placeholder="填写游记标题"/>
        </div>

    </div>

    <div class="container">

        <input id="uploadindeximg" type="file" accept="image/*" name="file" class="file-loading"/>
        <input id="content" name="content" type="hidden" value=""/>
        <input id="img" name="img" type="hidden" value=""/>

        <div class="travel-content">
            <div class="addBar">
                <div class="bar">
                    <div class="addDiv">
                        <div class="addIcon"></div>
                    </div>
                    <div class="addText">
                        <div class="textIcon"></div>
                        <div class="iconText write">添加文字</div>
                    </div>
                    <div class="addImg">
                        <div class="imgIcon"></div>
                        <div class="iconText photo">添加照片</div>
                    </div>
                </div>

                <div class="textContent" style="display: none">
                    <textarea class="form-control" rows="10"></textarea>
                    <input class="btn btn-style btn-warning btn-finish" type="button" value="完成"/>
                    <input class="btn btn-default btn-cancel" type="button" value="取消"/>
                </div>

                <div class="imgContent" style="display: block">
                    <input class="travelimg" type="file" accept="image/*" name="file" class="file-loading"/>
                </div>
            </div>
        </div>

        <div class="travel-recommend"></div>

    </div>

    <div class="container bottom">
        <div class="select-spot">
            <div class="select-label">选择景点:</div>
            <div class="select">
                <select class="form-control" name="spotId" id="spotId"></select>
            </div>
        </div>
        <div class="travel-tail">
            <div class="split"></div>
            <input class="btn btn-style btn-warning" id="report" type="button" value="发表游记"/>
        </div>
    </div>

</form>

</body>
</html>