<#escape x as (x)!>
<script type="text/javascript">

    jQuery(window).load(function() {
        // Page Preloader
        jQuery('#preloader').delay(350).fadeOut(function(){
            jQuery('body').delay(350).css({'overflow':'visible'});
        });
    });

    (function($){
        $(document).ready(function(){

            $('#cssmenu li.active').addClass('open').children('ul').show();
            $('#cssmenu li.has-sub>a').on('click', function(){
                $(this).removeAttr('href');
                var element = $(this).parent('li');
                if (element.hasClass('open')) {
                    element.removeClass('open');
                    element.find('li').removeClass('open');
                    element.find('ul').slideUp(200);
                }else {
                    element.addClass('open');
                    element.children('ul').slideDown(200);
                    element.siblings('li').children('ul').slideUp(200);
                    element.siblings('li').removeClass('open');
                }
            });

        });
    })(jQuery);

    //设置菜单状态
    function setMenuActive(mainName, subName){
        $('#cssmenu li.has-sub').filter(function(){
            $(this).removeClass('open');
            $(this).find('li').removeClass('open');
            $(this).find('ul').slideUp(200);

            return $.trim($(this).children("a").text()) == mainName;
        }).addClass("open").children('ul').slideDown(200);
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
</#escape>