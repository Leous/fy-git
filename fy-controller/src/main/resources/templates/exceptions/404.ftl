<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>findyou</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <link rel="stylesheet" href="/public/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/public/font-awesome/css/font-awesome.min.css" />

    <link href='http://fonts.googleapis.com/css?family=Nova+Flat' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="/public/assets/css/exceptions.css"/>
</head>
<body>
    <div class="container">
        <div class="row pad-top text-center">
            <div class="col-md-6 col-md-offset-3 text-center">
                <h1>  What have you done? </h1>
                <h5> Now Go Back Using Below LInk </h5>
                <p class="font-404"> 404 </p>
            </div>
        </div>
        <div class="row text-center">
            <div class="col-md-8 col-md-offset-2">
                <h3><i class="fa fa-lightbulb-o fa-5x"></i></h3>
                <a href="#" class="btn btn-primary" id="back_btn">点击返回</a>
            </div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript" src="/public/scripts/jquery-2.1.4.min.js?v=2017010122" charset="utf-8"></script>
<script>
    $('#back_btn').on('click', function () {
        history.back();
    });
</script>
