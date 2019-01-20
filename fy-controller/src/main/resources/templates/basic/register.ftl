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
                <br><br>
                <div class="col-md-4 col-md-offset-4">
                    <form role="form" id="registerForm">
                        <div class="text-center">
                            <h1 class="title">FORYOU</h1>
                        </div>
                        <br>
                        <div class="text-center">
                            <div class="form-group">
                                <input type="text" class="form-control" name="fusername" placeholder="请输入新账号..." id="fusername">
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="password" class="form-control suffixPassword" name="fpassword" placeholder="请输入新密码..." id="fpassword">
                                    <span class="input-group-addon suffixEye" id="passwordEye"><i class="fa fa-eye" id="passwordShowOrHide"></i></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input type="password" class="form-control suffixPassword" name="frepeat" placeholder="请再次输入新密码..." id="frepeat">
                                    <span class="input-group-addon suffixEye" id="repeatEye"><i class="fa fa-eye" id="repeatShowOrHide"></i></span>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="text-center">
                            <div class="form-group">
                                <button type="submit" class="btn btn-success submit-btn" data-loading-text="Loading..." id="doRegister">注册</button>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default register-btn" id="returnLogin">返回登录</button>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <p>尝试下其他登录方式?</p>
                        </div>
                        <div class="text-center">
                            <div class="form-group">
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

<script type="text/javascript" src="/public/scripts/jquery.common.js" charset="utf-8"></script>

<!--[if lt IE 9]>
<script src="/public/html5shiv.min.js"></script>
<script src="/public/respond.min.js"></script>
<![endif]-->

<script>
    $(function () {
        $('#returnLogin').on('click',function(){
            window.location.href="/api/v1/partner/toLogin";
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

        $('#repeatEye').on('click', function () {
            var type = $('#frepeat').attr('type');
            if(type == 'password'){
                $('#frepeat').attr('type', 'text');
                $('#repeatShowOrHide').attr('class', 'fa fa-eye-slash');
            }else {
                $('#frepeat').attr('type', 'password');
                $('#repeatShowOrHide').attr('class', 'fa fa-eye');
            }
        });
        
        $('#mobile_btn').on('click', function () {
            window.location.href="/api/v1/partner/toLogin?from=0";
        });

        $('#wechat_btn').on('click', function () {
            $.alert('暂不支持微信登录');
        });

        $('#github_btn').on('click', function () {
            $.alert('暂不支持github登录');
        });

        $('#qq_btn').on('click', function () {
            $.alert('暂不支持QQ登录');
        });
    });

    $(document).ready(function() {
        $('#registerForm').formValidation({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'fa fa-check'
            },
            fields: {
                fusername: {
                    verbose: false,//代表验证按顺序验证。验证成功才会下一个
                    validators: {
                        notEmpty: {
                            message: '请输入账号.'
                        }/*,
                        remote: {
                            url: '/api/v1/partner/verifyAccount',//验证地址
                            message: '用户已存在',//提示消息
                            delay:  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                            type: 'POST',
                            contentType: "application/json",
                            data: JSON.stringify(getSign({"account": $("#fusername").val()}, '/partner/verifyAccount'))
                        }*/
                    }
                },
                fpassword: {
                    verbose: false,
                    validators: {
                        notEmpty: {
                            message: '请输入密码.'
                        },
                        identical: {
                            field: 'frepeat',
                            message: '两次输入的密码不一致.'
                        }
                    }
                },
                frepeat: {
                    verbose: false,
                    validators: {
                        notEmpty: {
                            message: '请再次输入密码.'
                        },
                        identical: {
                            field: 'fpassword',
                            message: '两次输入的密码不一致.'
                        }
                    }
                }
            }
        })
        .on('success.form.fv', function(e) {
            // Prevent default form submission
            e.preventDefault();
            var $form = $(e.target),        // The form instance
                fv    = $(e.target).data('formValidation'); // FormValidation instance

            var fusername = $("#fusername").val();
            var fpassword = $("#fpassword").val();
            var frepeat = $("#frepeat").val();
            var data = {
                "account": fusername,
                "password": $.md5(fpassword),
                "comfirmPassword": $.md5(frepeat),
                "type": 2
            };

            $btn = $("#doRegister").button('loading');
            $.ajax({
                url: '/api/v1/partner/register',
                data: JSON.stringify(getSign(data, '/api/v1/partner/register')),
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
                            autoClose: '直接登录|3000',
                            buttons: {
                                '直接登录': {
                                    action: function () {
                                        window.location.href="/api/v1/partner/toLogin";
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

