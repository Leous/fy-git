<#include "../header.ftl" />
    <!-- BEGIN CONTAINER -->
    <script type="text/javascript" src="/static/public/scripts/page.js" charset="utf-8"></script>
    <!-- BEGIN SIDEBAR -->

<!-- END SIDEBAR -->
<!-- BEGIN CONTENT -->
<div class="main-panel">
    <h4 class="sub-header"><span><#if project??>${project}</#if>任务列表</span><span style="float: right">+</span></h4>
    <!-- BEGIN EXAMPLE TABLE PORTLET-->
    <div class="portlet-body">
        <div class="form-inline">
            <form action="task/index" method="post" name="pagefrm">
                创建人：<input name="createId" id="createId" class="form-control input-small input-inline input-sm" type="text">
                负责人：<input name="responseId" id="responseId" class="form-control input-small input-inline input-sm" type="text">

                <select name="status" id="status" class="form-control input-small input-sm input-inline">
                    <option value="">状态</option>
                    <option value=0  <#if status?? && status=="0">selected</#if>>  待处理    </option>
                    <option value=1  <#if status?? && status=="1">selected</#if>>  完成</option>
                    <option value=-1 <#if status?? && status=="-1">selected</#if>> 未完成</option>
                    <option value=2 <#if status?? && status=="2">selected</#if>> 进行中</option>
                    <option value=3 <#if status?? && status=="3">selected</#if>> 延后</option>
                    <option value=4 <#if status?? && status=="4">selected</#if>> 超时完成</option>
                </select>

                <select name="priority" id="priority" class="form-control input-small input-sm input-inline">
                    <option value=""> 优先级</option>
                    <option value=0  <#if priority?? && priority=="0">selected</#if>>  低</option>
                    <option value=2  <#if priority?? && priority=="2">selected</#if>>  中</option>
                    <option value=1  <#if priority?? && priority=="1">selected</#if>>  正常</option>
                    <option value=3 <#if priority?? && priority=="3">selected</#if>> 较高</option>
                    <option value=4 <#if priority?? && priority=="4">selected</#if>> 高</option>
                    <option value=5 <#if priority?? && priority=="5">selected</#if>> 超高</option>
                    <option value=6 <#if priority?? && priority=="6">selected</#if>> 紧急任务</option>
                </select>
                <input type="hidden" name="pageIndex" id="pageIndex" value="0" />
                <input class="btn btn-default" value="搜索" onclick="return searchTrade()" type="submit"><p></p>
            </form>
        </div>
        <hr>

        <table class="table table-striped table-bordered table-hover" id="sample_1">
            <thead>
            <tr>
                <th>负责人</th>
                <th>任务编号</th>
                <th>创建人</th>
                <th>标题</th>
                <th>时长</th><#--交易时间-->
                <th>创建时间</th>
                <th>状态</th>
                <th>优先级</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>
<#include "../footer.ftl" />

<script>

</script>
