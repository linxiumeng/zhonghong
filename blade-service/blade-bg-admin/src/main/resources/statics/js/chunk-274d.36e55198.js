(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-274d"],{"8M3d":function(e,t,a){var r=a("wlCk");"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);(0,a("SZ7m").default)("4ee4e25b",r,!0,{})},ZAxM:function(e,t,a){"use strict";var r=a("8M3d");a.n(r).a},"t/cf":function(e,t,a){"use strict";a.r(t);var r=a("QbLZ"),n=a.n(r),l=a("fBUP"),s={data:function(){return{searchForm:{demandStatus:"",ordersFormStatus:"",demandDate:"",demandDeadLineDate:"",keywords:""},buyerList:[],tableLoading:!1,page:{currentPage:1,size:10,total:100},statusTable:["初始化","招标中","已暂停","已结束"],demandStatusTable:["等待确认","订单确认失败","等待定价","等待确认金额","等待重新定价","等待支付","申请融资","等待融资放款","融资未通过","已退款","待确认收款","已完成","待还款","补交保证金","已完成"]}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.tableLoading=!0,this.$http.post(l.a.commercialTenant.demandList,n()({page:this.page.currentPage,size:this.page.size,demandStartDate:this.searchForm.demandDate&&this.searchForm.demandDate[0],demandEndDate:this.searchForm.demandDate&&this.searchForm.demandDate[1],demandDeadLineStartDate:this.searchForm.demandDeadLineDate&&this.searchForm.demandDeadLineDate[0],demandDeadLineEndDate:this.searchForm.demandDeadLineDate&&this.searchForm.demandDeadLineDate[1]},this.searchForm)).then(function(t){t.result.records.map(function(t){return t.statusText=e.statusTable[t.status],t.demandStatusText="",t.purchaseOrdersEntity&&void 0!==t.purchaseOrdersEntity.status?t.demandStatusText=e.demandStatusTable[t.purchaseOrdersEntity.status]:t.demandStatusText="未下单",t}),e.tableLoading=!1,e.buyerList=t.result.records,e.page={currentPage:t.result.current,size:t.result.size,total:t.result.total}}).catch(function(){e.tableLoading=!1})},toSearch:function(){this.page.currentPage=1,this.getList()},handleCurrentChange:function(e){this.page.currentPage=e,this.getList()}}},i=(a("ZAxM"),a("KHd+")),o=Object(i.a)(s,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"pay-by-stages-container flex-table"},[a("el-form",{staticClass:"form-inline",attrs:{inline:!0,model:e.searchForm,size:"mini"}},[a("el-form-item",{attrs:{label:"需求状态"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.searchForm.demandStatus,callback:function(t){e.$set(e.searchForm,"demandStatus",t)},expression:"searchForm.demandStatus"}},[a("el-option",{attrs:{label:"全部",value:""}}),e._v(" "),a("el-option",{attrs:{label:"招标中",value:"1"}}),e._v(" "),a("el-option",{attrs:{label:"已暂停",value:"2"}}),e._v(" "),a("el-option",{attrs:{label:"已结束",value:"3"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"下单状态"}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.searchForm.ordersFormStatus,callback:function(t){e.$set(e.searchForm,"ordersFormStatus",t)},expression:"searchForm.ordersFormStatus"}},[a("el-option",{attrs:{label:"全部",value:""}}),e._v(" "),a("el-option",{attrs:{label:"未下单",value:"0"}}),e._v(" "),a("el-option",{attrs:{label:"已下单",value:"1"}}),e._v(" "),a("el-option",{attrs:{label:"订单失败",value:"2"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"发布时间"}},[a("el-date-picker",{attrs:{"default-time":["00:00:00","23:59:59"],"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.searchForm.demandDate,callback:function(t){e.$set(e.searchForm,"demandDate",t)},expression:"searchForm.demandDate"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"截止日期"}},[a("el-date-picker",{attrs:{"default-time":["00:00:00","23:59:59"],"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.searchForm.demandDeadLineDate,callback:function(t){e.$set(e.searchForm,"demandDeadLineDate",t)},expression:"searchForm.demandDeadLineDate"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"其他"}},[a("el-input",{attrs:{placeholder:"输入账号、企业名称搜索"},model:{value:e.searchForm.keywords,callback:function(t){e.$set(e.searchForm,"keywords",t)},expression:"searchForm.keywords"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.toSearch}},[e._v("搜索")])],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],staticClass:"table-flex-item",attrs:{data:e.buyerList,stripe:!0,height:"100px","header-align":"center"}},[a("el-table-column",{attrs:{prop:"id",label:"需求编号",width:"120",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userEntity.companyName",label:"企业名称",width:"120",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userEntity.mobile",label:"账号",width:"120",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"ftype",label:"产品类型",width:"120",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"num",label:"需求数量",width:"100",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"funit",label:"单位",width:"100",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"creatTime",label:"发布时间",width:"100",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"deadline",label:"截止日期",width:"100",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"quotationCount",label:"报价次数",width:"100",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"statusText",label:"需求状态",width:"140",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"demandStatusText",label:"下单状态",width:"130",align:"center"}})],1),e._v(" "),a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.size,total:e.page.total,layout:"prev, pager, next, jumper"},on:{"update:currentPage":function(t){e.$set(e.page,"currentPage",t)},"current-change":e.handleCurrentChange}})],1)],1)},[],!1,null,"19bc393a",null);o.options.__file="demandList.vue";t.default=o.exports},wlCk:function(e,t,a){(e.exports=a("I1BE")(!1)).push([e.i,".pay-by-stages-container[data-v-19bc393a] {\n  height: calc(100% - 60px);\n  background: #fff;\n  margin: 30px;\n  width: 1260px;\n}\n.pay-by-stages-container .form-inline[data-v-19bc393a] {\n  padding: 20px 20px 0 20px;\n}\n.pay-by-stages-container .pagination[data-v-19bc393a] {\n  text-align: center;\n  padding: 10px;\n}\n",""])}}]);