<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>findyou</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/public/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/public/form-validation/css/formValidation.min.css" />
    <link rel="stylesheet" href="/public/jquery-confirm/jquery-confirm.min.css" />
    <link rel="stylesheet" href="/public/assets/css/login.css"/>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
<div class="container">
    <div class="row">
        <br><br><br>
        <div class="col-md-4 col-md-offset-4">
            <form role="form" id="loginForm">
                <div class="text-center">
                    <h1 class="title">FORYOU</h1>
                </div>
                <br>

                <!-- 普通账号密码登录 -->
                <div id="userLogin">
                    <div class="text-center">
                        <div class="form-group">
                            <input type="text" class="form-control" name="fusername" id="fusername" placeholder="请输入账号...">
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="password" class="form-control suffixPassword" name="fpassword" placeholder="请输入密码..." id="fpassword">
                                <span class="input-group-addon suffixEye" id="passwordEye"><i class="fa fa-eye" id="passwordShowOrHide"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="checkbox remember_checkbox">
                        <label>
                            <input type="checkbox" id="form_remember" name="remember" value="1">记住密码
                        </label>
                        <a href="#" class="forget-a">忘记密码?</a>
                    </div>
                    <br>
                    <div class="text-center">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success submit-btn" data-loading-text="Loading..." id="doLogin">登&nbsp;录</button>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-default register-btn" id="register_btn">注册</button>
                        </div>
                    </div>
                </div>
            </form>

            <form role="form" id="mobileForm" class="hide">
                <div class="text-center">
                    <h1 class="title">FORYOU</h1>
                </div>
                <br>

                <!-- 手机号登录 -->
                <div id="userLogin">
                    <div class="text-center">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">区号
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li value="+86" checked><a href="#">+86</a></li>
                                        <li value="+88"><a href="#">+88</a></li>
                                        <li value="+89"><a href="#">+89</a></li>
                                        <li value="+101"><a href="#">+101</a></li>
                                        <li value="+222"><a href="#">+222</a></li>
                                    </ul>
                                </div><!-- /btn-group -->
                                <input type="text" class="form-control" name="fmobile" id="fmobile" placeholder="请输入11位手机号..." minlength="11" maxlength="11">
                            </div><!-- /input-group -->
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" class="form-control" name="fverifyCode" id="fverifyCode" placeholder="请输入6位验证码..." minlength="6" maxlength="6">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-success" id="getVerifyCode">获取验证码</button>
                                </div><!-- /btn-group -->
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="text-center">
                        <div class="form-group">
                            <button type="submit" class="btn btn-success submit-btn" data-loading-text="Loading..." id="doMobileLogin">登&nbsp;录</button>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-default register-btn" id="register_btn_mobile">注册</button>
                        </div>
                    </div>
                </div>
            </form>
            <form role="form">
                <div>
                    <div class="form-group">
                        <p>尝试下其他登录方式?</p>
                    </div>
                    <div class="text-center">
                        <div class="form-group" id="typeAppend">
                            <button type="button" class="btn btn-default mobile-btn" id="mobile_btn">
                                <label>
                                    <img src="/images/basic/mobile.png"/>
                                </label>&nbsp;手机登录
                            </button>
                            <button type="button" class="btn btn-default wechat-btn" id="wechat_btn">
                                <label>
                                    <img src="/images/basic/wechat.png"/>
                                </label>&nbsp;微信登录
                            </button>
                        </div>
                        <div class="text-center">
                            <button type="button" class="btn btn-default qq-btn" id="qq_btn">
                                <label>
                                    <img src="/images/basic/qq.png"/>
                                </label>&nbsp;QQ登录
                            </button>
                            <button type="button" class="btn btn-default github-btn" id="github_btn">
                                <label>
                                    <img src="/images/basic/github.png"/>
                                </label>&nbsp;github
                            </button>
                        </div>
                    </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" src="/public/scripts/jquery-2.1.4.min.js?v=2017010122" charset="utf-8"></script>
<script type="text/javascript" src="/public/scripts/jquery.common.js" charset="utf-8"></script>
<script type="text/javascript" src="/public/jQuery.md5.js" charset="utf-8"></script>
<script type="text/javascript" src="/public/bootstrap/js/bootstrap.min.js" charset="utf-8"></script>

<script type="text/javascript" src="/public/jquery-confirm/jquery-confirm.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/public/form-validation/js/formValidation.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/public/form-validation/framework/bootstrap.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/public/form-validation/js/zh_CN.js" charset="utf-8"></script>

<!--[if lt IE 9]>
<script src="/public/html5shiv.min.js"></script>
<script src="/public/respond.min.js"></script>
<![endif]-->

<script>
    $(function () {
        $('#register_btn').on('click',function(){
            window.location.href="/api/v1/partner/toRegister";
        });
        $('#register_btn_mobile').on('click',function(){
            window.location.href="/api/v1/partner/toRegister";
        });

        $('#passwordEye').on('click', function () {
            var type = $('#fpassword').attr('type');
            if(type == 'password'){
                $('#fpassword').attr('type', 'text');
                $('#passwordShowOrHide').attr('class', 'fa fa-eye-slash');
            }else {
                $('#fpassword').attr('type', 'password');
                $('#passwordShowOrHide').attr('class', 'fa fa-eye');
            }
        });
        
        $('#typeAppend').on('click', '#mobile_btn', function () {
            $('#mobileForm')[0].reset();
            $('#loginForm')[0].reset();
            console.log('mobile');
            $('#loginForm').hide();
            $('#mobileForm').show();
            $('#mobileForm').removeClass();
            $('#mobile_btn').remove();
            $('#typeAppend').prepend(
                "<button type='button' class='btn btn-default mobile-btn' id='normal_btn'>" +
                "                                <label>" +
                "                                    <img src='/images/basic/normal.png'/>" +
                "                                </label>&nbsp;&nbsp;账号登录" +
                "                            </button>"
            );
        });

        $('#typeAppend').on('click', '#normal_btn', function () {
            $('#mobileForm')[0].reset();
            $('#loginForm')[0].reset();
            console.log('normal');
            $('#loginForm').show();
            $('#mobileForm').hide();
            $('#normal_btn').remove();
            $('#typeAppend').prepend(
                "<button type='button' class='btn btn-default mobile-btn' id='mobile_btn'>" +
                "                                <label>" +
                "                                    <img src='/images/basic/mobile.png'/>" +
                "                                </label>&nbsp;手机登录" +
                "                            </button>"
            );
        });
    });

    <!-- 账号密码登录 -->
    $(document).ready(function() {
        $('#loginForm').formValidation({
            framework: 'bootstrap',
            fields: {
                fusername: {
                    validators: {
                        notEmpty: {
                            message: '请输入账号.'
                        }
                    }
                },
                fpassword: {
                    validators: {
                        notEmpty: {
                            message: '请输入密码.'
                        }
                    }
                }
            }
        }).on('success.form.fv', function(e) {
            // Prevent default form submission
            e.preventDefault();
            var $form = $(e.target),        // The form instance
                fv    = $(e.target).data('formValidation'); // FormValidation instance

            var fusername = $("#fusername").val();
            var fpassword = $("#fpassword").val();
            var data = {"account": fusername, "password": $.md5(fpassword), "type": 2};

            $btn = $("#doLogin").button('loading');
            $.ajax({
                url: '/api/v1/partner/login',
                data: JSON.stringify(data),
                type: 'POST',
                contentType:"application/json",
                dataType: 'json',
                cache: false,
                success: function(json){
                    $btn.button('reset');
                    if (json.code == "0000"){  //成功
                        $.alert({
                            title: false,
                            content: json.message,
                            autoClose: 'ok|3000',
                            buttons: {
                                'ok': {
                                    action: function () {
                                        window.location.href="/api/v1/partner/index";
                                    }
                                }
                            }
                        });
                    }
                    else{
                        $.alert({
                            title: false,
                            content: json.message,
                            autoClose: '返回|3000',
                            buttons: {
                                '返回': {
                                }
                            }
                        });
                    }
                },
                error: function(httpRequest, statusText, errorThrown){ //错误处理
                    $btn.button('reset');
                    $.alert({
                        title: false,
                        content: '服务异常，请稍后重试',
                        autoClose: '返回|3000',
                        buttons: {
                            '返回': {
                            }
                        }
                    });
                }
            });
        });
    });

    <!-- 手机号登陆 -->
    $(document).ready(function() {
        $('#mobileForm').formValidation({
            framework: 'bootstrap',
            fields: {
                fmobile: {
                    validators: {
                        notEmpty: {
                            message: '请输入11位手机号.'
                        }
                    }
                },
                fverifyCode: {
                    validators: {
                        notEmpty: {
                            message: '请输入6位验证码.'
                        }
                    }
                }
            }
        }).on('success.form.fv', function(e) {
            // Prevent default form submission
            e.preventDefault();
            var $form = $(e.target),        // The form instance
                fv    = $(e.target).data('formValidation'); // FormValidation instance

            var areaCode = $("#fareaCode").val();
            var fmobile = $("#fmobile").val();
            var fverifyCode = $("#fverifyCode").val();
            var data = {"areaCode": areaCode, "tel": fmobile, "verifyCode": fverifyCode, "type": 1};

            $btn = $("#doMobileLogin").button('loading');
            $.ajax({
                url: '/api/v1/partner/login',
                data: JSON.stringify(data),
                type: 'POST',
                contentType:"application/json",
                dataType: 'json',
                cache: false,
                success: function(json){
                    $btn.button('reset');
                    if (json.code == "0000"){  //成功
                        $.alert({
                            title: false,
                            content: json.message,
                            autoClose: 'ok|3000',
                            buttons: {
                                'ok': {
                                    action: function () {
                                        window.location.href="/api/v1/partner/index";
                                    }
                                }
                            }
                        });
                    }
                    else{
                        $.alert({
                            title: false,
                            content: json.message,
                            autoClose: '返回|3000',
                            buttons: {
                                '返回': {
                                }
                            }
                        });
                    }
                },
                error: function(httpRequest, statusText, errorThrown){ //错误处理
                    $btn.button('reset');
                    $.alert({
                        title: false,
                        content: '服务异常，请稍后重试',
                        autoClose: '返回|3000',
                        buttons: {
                            '返回': {
                            }
                        }
                    });
                }
            });
        });
    });
</script>