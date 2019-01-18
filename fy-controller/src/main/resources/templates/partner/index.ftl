<#include "../header.ftl" />
<!-- BEGIN CONTAINER -->
<script type="text/javascript" src="/static/public/scripts/page.js" charset="utf-8"></script>
<!-- BEGIN SIDEBAR -->
<#include "../sidebar.ftl" />

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
                <#--<#if taskList?? && taskList?size&gt;0>
                    <#list taskList as list>
                    <tr class="odd gradeX">
                        <td title="${list.tranType}">
                            <#if list.tranType==1000>
                                <@spring.message "pay"/>&lt;#&ndash;支付&ndash;&gt;
                            <#elseif  list.tranType==1001>
                                <@spring.message "payin"/>&lt;#&ndash;充值&ndash;&gt;
                            <#elseif list.tranType==2001>
                                <@spring.message "transfer"/>&lt;#&ndash;转账&ndash;&gt;
                            <#else>
                                ---
                            </#if>
                        </td>
                        <#if partnerSessionInfo.PARTNER_TYPE ?? && partnerSessionInfo.PARTNER_TYPE != "PTYPE_PARTNER">
                            <td>${list.partnerId}</td>&lt;#&ndash;商户ID&ndash;&gt;
                            <td>${list.partnerName!'--'}</td>
                        </#if>
                        <td>${list.orderSn}</td>&lt;#&ndash;平台订单号&ndash;&gt;
                        <td>${list.outTradeNo!'--'}</td>&lt;#&ndash;商户提交唯一订单号&ndash;&gt;
                        <td>${list.orderAmount?string("###0.00")!'--'}</td>  &lt;#&ndash;金额&ndash;&gt;
                        <td><#if list.tradeTime??>${list.tradeTime?string("yyyy-MM-dd HH:mm:ss")}<#else>----</#if></td> &lt;#&ndash;付款(交易)时间&ndash;&gt;
                        <td><#if list.finishTime??>${list.finishTime?string("yyyy-MM-dd HH:mm:ss")}<#else>----</#if></td>&lt;#&ndash;交易完成时间&ndash;&gt;
                        <td title="${list.orderStatus}">
                            <#if list.orderStatus==0 || list.orderStatus==2 || list.orderStatus==-9>
                                <span class="label label-sm label-primary"><@spring.message "trade.process"/>    </span><!-- 处理中 &ndash;&gt;
                            <#elseif list.orderStatus==1>
                                <span class="label label-sm label-success"><@spring.message "state_success"/>         </span><!-- 交易成功 &ndash;&gt;
                            <#elseif list.orderStatus==-1>
                                <span class="label label-sm label-warning"><@spring.message "state_unsuccess"/>       </span>&lt;#&ndash; 交易失败 &ndash;&gt;
                            <#elseif list.orderStatus==-2>
                                <span class="label label-sm label-warning"><@spring.message "trade.close"/>           </span>&lt;#&ndash; 订单自动关闭 &ndash;&gt;
                            <#elseif list.orderStatus==-3>
                                <span class="label label-sm label-warning"><@spring.message "trade.refund"/>          </span>&lt;#&ndash; 订单已退款 &ndash;&gt;
                            </#if>
                        </td>
                        <td title="${list.noticeStatus}">
                            <#if list.noticeStatus==2>
                                <span class="label label-sm label-default"><@spring.message "trade.notifying"/></span>&lt;#&ndash;正在通知&ndash;&gt;
                            <#elseif list.noticeStatus==1>
                                <span class="label label-sm label-success"><@spring.message "state_success"/></span>&lt;#&ndash;通知成功&ndash;&gt;
                            <#elseif list.noticeStatus==0 && list.tranType!=2001>
                                <span class="label label-sm label-default"><@spring.message "trade.wait_notification"/></span>&lt;#&ndash;等待通知&ndash;&gt;
                            <#elseif list.noticeStatus==-1 || list.noticeStatus==-9>
                                <span class="label label-sm label-danger"><@spring.message "state_unsuccess"/></span>&lt;#&ndash;通知失败&ndash;&gt;
                            <#else><span>---</span>
                            </#if>
                        </td>
                        <td>
                            <#if list.orderStatus==1>
                                <#if list.refundStatus?? && list.tranType!=2001>
                                    <#if list.refundStatus==0>        &lt;#&ndash;<@spring.message "refund.processing"/>&ndash;&gt;
                                        <a href="javascript:void(0);" onclick="supply('${list.orderSn}', this)"><@spring.message "trade.order_add"/></a>
                                        | <a href="javascript:refund('${list.partnerId}', '${list.orderSn}', ${list.orderAmount})"><@spring.message "refund"/></a>
                                        | <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    <#elseif list.refundStatus==2>  &lt;#&ndash;<@spring.message "refund_approve"/>&ndash;&gt;
                                        <a href="javascript:void(0);" onclick="supply('${list.orderSn}', this)"><@spring.message "trade.order_add"/></a> |
                                        <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    <#elseif list.refundStatus==-1>  <@spring.message "refund_unsuccess"/>
                                        <a href="javascript:void(0);" onclick="supply('${list.orderSn}', this)"><@spring.message "trade.order_add"/></a> |
                                        <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    <#elseif list.refundStatus==3>  <@spring.message "refund.refuse"/>
                                        <a href="javascript:void(0);" onclick="supply('${list.orderSn}', this)"><@spring.message "trade.order_add"/></a> |
                                        <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    <#elseif list.refundStatus==1> <@spring.message "refund_success"/>
                                        <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    <#else>
                                        <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                    </#if>
                                <#else>
                                    <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                                </#if>
                            <#else>
                                <a href="javascript:detail('${list.orderSn}')"><@spring.message "trade.detail"/></a>
                            </#if>
                        </td>
                    </tr>
                    </#list>
                    <tr style="background:#C1FFC1">
                        <td colspan="17">交易明细: 总金额（<strong style="color: red">￥${sum}</strong>）, 总笔数（<strong style="color: red">${count}</strong>）</td>
                    </tr>
                <#else>
                <tr bgcolor="#fafafa" >
                    <#if partnerSessionInfo.PARTNER_TYPE ?? && partnerSessionInfo.PARTNER_TYPE != "PTYPE_PARTNER">
                        <td id="empty_td" colspan="11">
                    <#else>
                        <td id="empty_td" colspan="9">
                    </#if>
                    <em><@spring.message "account.sorry_query_null"/></em></td>
                </tr>
                </#if>-->
            </tbody>
        </table>
    </div>
</div>
<#include "../footer.ftl" />

<script>

</script>
