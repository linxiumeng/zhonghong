(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3194"],{"1s5w":function(t,e,a){},"6ML+":function(t,e,a){"use strict";var i=a("s8A+");a.n(i).a},Sv7x:function(t,e,a){"use strict";var i=a("uJMD"),l={props:{detail:{type:Object,default:function(){return{}}}},data:function(){return{cloneDetail:{}}},created:function(){for(var t in this.cloneDetail=i.a.deepClone(this.detail),this.cloneDetail){var e=this.cloneDetail[t];e||0===e||"filePoint"===t||(this.cloneDetail[t]="——")}},methods:{fileDetail:function(t){window.open(t)}}},n=(a("Z7UI"),a("KHd+")),o=Object(n.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"order-info-container"},[a("p",{staticClass:"order-date"},[t._v("下单时间 "+t._s(t.cloneDetail.creatTime))]),t._v(" "),a("ul",{staticClass:"user-info"},[a("li",[a("h2",[t._v("采购商: "+t._s(t.cloneDetail.buyerCompany))]),t._v(" "),a("p",[t._v("联系人: "+t._s(t.cloneDetail.buyerLinkman))]),t._v(" "),a("p",[t._v("联系电话: "+t._s(t.cloneDetail.buyerPhone))]),t._v(" "),a("p",[t._v("联系邮箱: "+t._s(t.cloneDetail.buyerEmail))])]),t._v(" "),a("li",[a("h2",[t._v("供应商: "+t._s(t.cloneDetail.providerCompany))]),t._v(" "),a("p",[t._v("联系人: "+t._s(t.cloneDetail.providerLinkman))]),t._v(" "),a("p",[t._v("联系电话: "+t._s(t.cloneDetail.providerPhone))]),t._v(" "),a("p",[t._v("联系邮箱: "+t._s(t.cloneDetail.providerEmail))])])]),t._v(" "),a("div",{staticClass:"product-info"},[a("p",{staticClass:"product-name"},[t._v("产品名称:  "+t._s(t.cloneDetail.goodsName))]),t._v(" "),a("ul",[a("li",[a("p",[t._v("采购类型: "+t._s(t.cloneDetail.goodsType))]),t._v(" "),a("p",[t._v("采购数量: "+t._s(t.cloneDetail.goodsAmount)+" "+t._s(t.cloneDetail.goodsUnit))]),t._v(" "),a("p",[t._v("产品单价: "+t._s(t.cloneDetail.goodsPrice))])]),t._v(" "),a("li",[a("p",[t._v("贸易条款: "+t._s(t.cloneDetail.tradeClause))]),t._v(" "),a("p",[t._v("信用方式: "+t._s(t.cloneDetail.paymentBy))]),t._v(" "),a("p",[t._v("升贴水: "+t._s(t.cloneDetail.premiumsDiscounts))])]),t._v(" "),a("li",[a("p",[t._v("计价基准: "+t._s(t.cloneDetail.pricingManner))]),t._v(" "),a("p",[t._v("计价期: "+t._s(t.cloneDetail.contractualValueDate))]),t._v(" "),a("p",[t._v("支付日: "+t._s(t.cloneDetail.payDay))])]),t._v(" "),a("li",[a("p",[t._v("发货港/提货点: "+t._s(t.cloneDetail.shipAddress))]),t._v(" "),a("p",[t._v("装货期: "+t._s(t.cloneDetail.loadDate))]),t._v(" "),a("p",[t._v("采购商备注: "+t._s(t.cloneDetail.buyerRemark))])])])]),t._v(" "),a("ul",{staticClass:"oil-info"},[a("li",[a("p",[t._v("原油产地: "+t._s(t.cloneDetail.placeOfOrigin))]),t._v(" "),a("p",[t._v("油种: "+t._s(t.cloneDetail.oilType))]),t._v(" "),a("p",[t._v("API度: "+t._s(t.cloneDetail.api))])]),t._v(" "),a("li",[a("p",[t._v("含硫量(%):  "+t._s(t.cloneDetail.sulphurContent))]),t._v(" "),a("p",[t._v("酸值(mgKOH/g): "+t._s(t.cloneDetail.acidValue))]),t._v(" "),a("p",[t._v("残碳(%): "+t._s(t.cloneDetail.carbonResidue))])]),t._v(" "),a("li",[a("p",[t._v("镍(ppm): "+t._s(t.cloneDetail.nickel))]),t._v(" "),a("p",[t._v("钒(ppm): "+t._s(t.cloneDetail.vanadium))]),t._v(" "),a("p",[t._v(">350℃质量收率(%): "+t._s(t.cloneDetail.shrink))])]),t._v(" "),a("li",[a("p",[t._v("备注: "+t._s(t.cloneDetail.goodsRemark))]),t._v(" "),t.cloneDetail.filePoint?a("p",{staticClass:"fileDetail",on:{click:function(e){t.fileDetail(t.cloneDetail.filePoint)}}},[t._v("查看原油指标文件")]):t._e(),t._v(" "),t._m(0)])])])},[function(){var t=this.$createElement,e=this._self._c||t;return e("p",[e("br")])}],!1,null,"46590bc4",null);o.options.__file="orderInfo.vue";e.a=o.exports},Z7UI:function(t,e,a){"use strict";var i=a("bRoR");a.n(i).a},ZAOL:function(t,e,a){"use strict";var i=a("uJMD"),l={props:{detail:{type:Object,default:function(){return{}}}},data:function(){return{isShow:!0,statusTable:["","已还款","已逾期"]}},created:function(){var t=this;this.detail.accountRepaymentEntity?(this.detail.finalQuotation=i.a.numFormat(this.detail.finalQuotation),this.detail.firstPaidAccount=i.a.numFormat(this.detail.firstPaidAccount),this.detail.accountRepaymentEntity.totalAmount=i.a.numFormat(this.detail.accountRepaymentEntity.totalAmount),this.detail.accountRepaymentEntity.accountRepaymentStepEntityList&&this.detail.accountRepaymentEntity.accountRepaymentStepEntityList.map(function(e){return e.preiodDate=e.preiodDate&&e.preiodDate.split(" ")[0],e.account=i.a.numFormat(e.account),e.statusText=t.statusTable[e.status],e})):this.isShow=!1}},n=(a("eGIX"),a("KHd+")),o=Object(n.a)(l,function(){var t=this,e=t.$createElement,a=t._self._c||e;return t.isShow?a("div",{staticClass:"order-finance-info-container"},[a("ul",{staticClass:"base-info"},[a("li",[a("p",[t._v("订单金额")]),t._v(" "),a("p",[t._v("¥ "+t._s(t.detail.finalQuotation))])]),t._v(" "),a("li",[a("p",[t._v("首付款")]),t._v(" "),a("p",[t._v("¥ "+t._s(t.detail.firstPaidAccount))])]),t._v(" "),a("li",[a("p",[t._v("融资金额")]),t._v(" "),a("p",[t._v("¥ "+t._s(t.detail.accountRepaymentEntity.totalAmount))])]),t._v(" "),a("li",[a("p",[t._v("融资期限")]),t._v(" "),a("p",[t._v(t._s(t.detail.accountRepaymentEntity.periods)+"个月")])]),t._v(" "),a("li",[a("p",[t._v("融资利率")]),t._v(" "),a("p",[t._v(t._s("10%"))])])]),t._v(" "),t.detail.accountRepaymentEntity.accountRepaymentStepEntityList&&t.detail.accountRepaymentEntity.accountRepaymentStepEntityList.length?a("div",{staticClass:"finance-plan"},[a("p",{staticClass:"finance-plan-title"},[t._v("还款计划")]),t._v(" "),a("ul",t._l(t.detail.accountRepaymentEntity.accountRepaymentStepEntityList,function(e,i){return a("li",{key:i},[a("p",[t._v("第"+t._s(e.preiod)+"期 "+t._s(e.preiodDate)+" 还款")]),t._v(" "),a("p",[t._v("¥ "+t._s(e.account))]),t._v(" "),a("p",[t._v(t._s(e.statusText))])])}))]):t._e()]):t._e()},[],!1,null,"34b51116",null);o.options.__file="orderFinanceInfo.vue";e.a=o.exports},bRoR:function(t,e,a){},eGIX:function(t,e,a){"use strict";var i=a("1s5w");a.n(i).a},lmhE:function(t,e,a){"use strict";a.r(e);var i=a("QbLZ"),l=a.n(i),n=a("fBUP"),o=a("uJMD"),r=a("Sv7x"),s=a("ZAOL"),c={components:{orderInfo:r.a,orderFinanceInfo:s.a},data:function(){return{searchForm:{ordersStatus:"",date:"",keywords:""},orderList:[],orderDetail:{},tableLoading:!1,page:{currentPage:1,size:10,total:100},orderDetailVisible:!1,statusTable:["等待确认","订单确认失败","等待定价","等待确认金额","等待重新定价","等待支付","申请融资","等待融资放款","融资未通过","已退款","待供应商确认收款","已完成(未融资)","待还款","补交保证金","已完成(融资)"]}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.tableLoading=!0,this.$http.post(n.a.orderManage.getOrderList,l()({page:this.page.currentPage,size:this.page.size,startDate:this.searchForm.date&&this.searchForm.date[0],endDate:this.searchForm.date&&this.searchForm.date[1]},this.searchForm)).then(function(e){e.page.records.map(function(e){return null!==e.finalQuotation&&(e.finalQuotation="0"===e.finalQuotation?"浮动价":o.a.numFormat(e.finalQuotation)),e.statusText=t.statusTable[e.status],e}),t.tableLoading=!1,t.orderList=e.page.records,t.page={currentPage:e.page.current,size:e.page.size,total:e.page.total}}).catch(function(){t.tableLoading=!1})},showDetail:function(t,e){var a=this;this.$http.post(n.a.orderManage.ordrerDetail,{id:e.id}).then(function(t){a.orderDetail=t.result,a.orderDetailVisible=!0})},toSearch:function(){this.page.currentPage=1,this.getList()},handleCurrentChange:function(t){this.page.currentPage=t,this.getList()},openContract:function(t){window.open(t)},closeDetail:function(){this.orderDetailVisible=!1}}},p=(a("6ML+"),a("KHd+")),u=Object(p.a)(c,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"order-list-container flex-table"},[a("el-form",{staticClass:"form-inline",attrs:{inline:!0,model:t.searchForm,size:"mini"}},[a("el-form-item",{attrs:{label:"订单状态"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:t.searchForm.ordersStatus,callback:function(e){t.$set(t.searchForm,"ordersStatus",e)},expression:"searchForm.ordersStatus"}},[a("el-option",{attrs:{label:"全部",value:""}}),t._v(" "),a("el-option",{attrs:{label:"等待确认",value:"0"}}),t._v(" "),a("el-option",{attrs:{label:"订单确认失败",value:"1"}}),t._v(" "),a("el-option",{attrs:{label:"等待定价",value:"2"}}),t._v(" "),a("el-option",{attrs:{label:"等待确认金额",value:"3"}}),t._v(" "),a("el-option",{attrs:{label:"等待重新定价",value:"4"}}),t._v(" "),a("el-option",{attrs:{label:"等待支付",value:"5"}}),t._v(" "),a("el-option",{attrs:{label:"申请融资",value:"6"}}),t._v(" "),a("el-option",{attrs:{label:"等待融资放款",value:"7"}}),t._v(" "),a("el-option",{attrs:{label:"融资未通过",value:"8"}}),t._v(" "),a("el-option",{attrs:{label:"已退款",value:"9"}}),t._v(" "),a("el-option",{attrs:{label:"待供应商确认收款",value:"10"}}),t._v(" "),a("el-option",{attrs:{label:"已完成(未融资)",value:"11"}}),t._v(" "),a("el-option",{attrs:{label:"待还款",value:"12"}}),t._v(" "),a("el-option",{attrs:{label:"补交保证金",value:"13"}}),t._v(" "),a("el-option",{attrs:{label:"已完成(融资)",value:"14"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"日期"}},[a("el-date-picker",{attrs:{"default-time":["00:00:00","23:59:59"],"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:t.searchForm.date,callback:function(e){t.$set(t.searchForm,"date",e)},expression:"searchForm.date"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"其他"}},[a("el-input",{attrs:{placeholder:"输入账号、企业名称搜索"},model:{value:t.searchForm.keywords,callback:function(e){t.$set(t.searchForm,"keywords",e)},expression:"searchForm.keywords"}})],1),t._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:t.toSearch}},[t._v("搜索")])],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.tableLoading,expression:"tableLoading"}],staticClass:"table-flex-item",attrs:{data:t.orderList,stripe:!0,height:"100px","header-align":"center"}},[a("el-table-column",{attrs:{prop:"id",label:"订单号",width:"120",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"buyerPhone",label:"采购商账号",width:"140",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"buyerCompany",label:"采购商名称",width:"140",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goodsId",label:"产品编号",width:"140",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"providerCompany",label:"供应商名称",width:"140",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"finalQuotation",label:"订单金额",width:"150",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goodsAmount",label:"数量",width:"100",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"goodsUnit",label:"单位",width:"100",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"creatTime",label:"下单时间",width:"100",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"statusText",label:"订单状态",width:"130",align:"center"}}),t._v(" "),t.btnPermission[385]?a("el-table-column",{attrs:{label:"操作",align:"center",width:"130",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.btnPermission[385]?a("p",{staticClass:"operate-btn",on:{click:function(a){t.showDetail(e.$index,e.row)}}},[t._v("\n          查看详情\n        ")]):t._e()]}}])}):t._e()],1),t._v(" "),a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":t.page.currentPage,"page-size":t.page.size,total:t.page.total,layout:"prev, pager, next, jumper"},on:{"update:currentPage":function(e){t.$set(t.page,"currentPage",e)},"current-change":t.handleCurrentChange}})],1),t._v(" "),t.orderDetailVisible?a("el-dialog",{attrs:{visible:t.orderDetailVisible,width:"1000px",center:"",title:"订单详情"},on:{"update:visible":function(e){t.orderDetailVisible=e}}},[a("orderInfo",{attrs:{detail:t.orderDetail}}),t._v(" "),a("orderFinanceInfo",{attrs:{detail:t.orderDetail}}),t._v(" "),t.orderDetail.contract?a("div",{staticClass:"contract"},[a("h3",[t._v("合同单据")]),t._v(" "),t.orderDetail.contract?a("p",{on:{click:function(e){t.openContract(t.orderDetail.contract)}}},[t._v("合同")]):t._e()]):t._e(),t._v(" "),t.orderDetail.loadBillEntity?a("div",{staticClass:"bill-of-lading"},[a("h3",[t._v("提货单")]),t._v(" "),a("p",{on:{click:function(e){t.openContract(t.orderDetail.loadBillEntity.path)}}},[t._v("\n        "+t._s(t.orderDetail.loadBillEntity.path)+"\n      ")])]):t._e(),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:t.closeDetail}},[t._v("关闭")])],1)],1):t._e()],1)},[],!1,null,"31859538",null);u.options.__file="orderList.vue";e.default=u.exports},"s8A+":function(t,e,a){}}]);