/**
*	同步分页这种是post提交的。新的分页形式
*	传入参数syncPage(第几页,共几页)
*	pageindex 当前页
*	pagenumber  总页数
*	pagesize 一页显示多少行
*	total 总条数
*/
function syncPage(pageindex, pagenumber, pagesize, total) {
	var strHtml = "";
	var endPage;
	if (pageindex - 1 < 1) {//是第一页的时候
		strHtml += "<a title='首页'>&lt;&lt;</a> ";
		strHtml += "<a title='上一页'>&lt;</a> ";
	} else {
		strHtml += "<a title='首页' href='javascript:pagebutton(1);'>&lt;&lt;</a> ";
		strHtml += "<a title='上一页' href='javascript:pagebutton("+(pageindex-1)+");'>&lt;</a> ";
	}
	if (pageindex != 1) {strHtml += "<a title='第一页' href='javascript:pagebutton(1);'>1</a> ";}
	if (pageindex >= 5) {strHtml += "<span>...</span> ";}
	if (pagenumber > pageindex + 2) {
		endPage = pageindex + 2;
	} else {
		endPage = pagenumber;
	}
	for (var i = pageindex - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == pageindex) {
				strHtml += "<strong title='第" + i + "页'>" + i + "</strong> ";
			} else {
				if (i != 1 && i != pagenumber) {
					strHtml += "<a title='第" + i + "页' href='javascript:pagebutton("+i+");'>"+i+"</a> ";
				}
			}
		}
	}
	if (pageindex + 3 < pagenumber) {strHtml += "<span>...</span> ";}//从第3个开始就用。。。代替
	if (pageindex != pagenumber) {strHtml += "<a title='第" + pagenumber + "页' href='javascript:pagebutton("+pagenumber+");'>" + pagenumber + "</a> ";}
	if (pageindex + 1 > pagenumber) {
		strHtml += "<a title='下一页'>&gt;</a> ";
		strHtml += "<a title='末页'>&gt;&gt;</a> ";
	} else {
		strHtml += "<a title='下一页' href='javascript:pagebutton("+(pageindex+1)+");'>&gt;</a> ";
		strHtml += "<a title='末页' href='javascript:pagebutton("+pagenumber+");'>&gt;&gt;</a> ";
	}
	if(strHtml != "") {
		strHtml += "<label>页次" + pageindex + " / " + pagenumber + "&nbsp" + pagesize + "条/页 共<em>&nbsp;" + total +"&nbsp;</em>条</label>";
	}
	return strHtml;
}
function syncPage_En(pageindex, pagenumber, pagesize, total) {
    var strHtml = "";
    var endPage;
    if (pageindex - 1 < 1) {//是第一页的时候
        strHtml += "<a title='首页'>&lt;&lt;</a> ";
        strHtml += "<a title='上一页'>&lt;</a> ";
    } else {
        strHtml += "<a title='首页' href='javascript:pagebutton(1);'>&lt;&lt;</a> ";
        strHtml += "<a title='上一页' href='javascript:pagebutton("+(pageindex-1)+");'>&lt;</a> ";
    }
    if (pageindex != 1) {strHtml += "<a title='第一页' href='javascript:pagebutton(1);'>1</a> ";}
    if (pageindex >= 5) {strHtml += "<span>...</span> ";}
    if (pagenumber > pageindex + 2) {
        endPage = pageindex + 2;
    } else {
        endPage = pagenumber;
    }
    for (var i = pageindex - 2; i <= endPage; i++) {
        if (i > 0) {
            if (i == pageindex) {
                strHtml += "<strong title='第" + i + "页'>" + i + "</strong> ";
            } else {
                if (i != 1 && i != pagenumber) {
                    strHtml += "<a title='第" + i + "页' href='javascript:pagebutton("+i+");'>"+i+"</a> ";
                }
            }
        }
    }
    if (pageindex + 3 < pagenumber) {strHtml += "<span>...</span> ";}//从第3个开始就用。。。代替
    if (pageindex != pagenumber) {strHtml += "<a title='第" + pagenumber + "页' href='javascript:pagebutton("+pagenumber+");'>" + pagenumber + "</a> ";}
    if (pageindex + 1 > pagenumber) {
        strHtml += "<a title='下一页'>&gt;</a> ";
        strHtml += "<a title='末页'>&gt;&gt;</a> ";
    } else {
        strHtml += "<a title='下一页' href='javascript:pagebutton("+(pageindex+1)+");'>&gt;</a> ";
        strHtml += "<a title='末页' href='javascript:pagebutton("+pagenumber+");'>&gt;&gt;</a> ";
    }
    if(strHtml != "") {
        strHtml += "<label>Pages" + pageindex + " / " + pagenumber + "&nbsp" + pagesize + " transaction/page total<em>&nbsp;" + total +"&nbsp;</em>transaction</label>";
    }
    return strHtml;
}

/**
 * 这里做提交的审核
 * @param pageindex
 */
function pagebutton(pageindex){
	var pagefrm=document.pagefrm;
	$("#pageIndex").val(pageindex);
	pagefrm.method = "post";
	pagefrm.submit();
}
