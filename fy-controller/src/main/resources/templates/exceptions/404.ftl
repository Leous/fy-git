<#include "../header.ftl" />
<div class="container">
    <div class="row pad-top text-center">
        <div class="col-md-6 col-md-offset-3 text-center">
            <h1>  What have you done? </h1>
            <h5> Now Go Back Using Below LInk</h5>
            <h2> 404 </h2>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-md-8 col-md-offset-2">
            <h3> <i class="fa fa-lightbulb-o fa-5x"></i> </h3>
            <a href="#" class="btn btn-primary" id="back_btn">点击返回</a>
        </div>
    </div>
</div>
<#include "../footer.ftl" />
<script>
    $('#back_btn').on('click', function () {
        history.back();
    });
</script>