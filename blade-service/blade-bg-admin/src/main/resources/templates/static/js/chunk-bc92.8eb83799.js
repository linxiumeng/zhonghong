(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-bc92"],{"+epL":function(e,t,a){},"6i+M":function(e,t,a){"use strict";var r=a("+epL");a.n(r).a},OqiC:function(e,t,a){"use strict";a.r(t);var r=a("QbLZ"),n=a.n(r),s=a("fBUP"),i=a("uJMD"),l={data:function(){return{searchForm:{date:"",keywords:""},statementsList:[],tableLoading:!1,page:{currentPage:1,size:10,total:100}}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.tableLoading=!0,this.$http.post(s.a.tradeStatements.repaymentStatementsList,n()({page:this.page.currentPage,size:this.page.size,startDate:this.searchForm.date&&this.searchForm.date[0],endDate:this.searchForm.date&&this.searchForm.date[1]},this.searchForm)).then(function(t){t.result.records.map(function(e){return e.repaymentAmount=i.a.numFormat(e.repaymentAmount),e}),e.tableLoading=!1,e.statementsList=t.result.records,e.page={currentPage:t.result.current,size:t.result.size,total:t.result.total}}).catch(function(){e.tableLoading=!1})},toSearch:function(){this.page.currentPage=1,this.getList()},handleCurrentChange:function(e){this.page.currentPage=e,this.getList()}}},o=(a("6i+M"),a("KHd+")),c=Object(o.a)(l,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"repayment-statements-container flex-table"},[a("el-form",{staticClass:"form-inline",attrs:{inline:!0,model:e.searchForm,size:"mini"}},[a("el-form-item",{attrs:{label:"日期"}},[a("el-date-picker",{attrs:{"default-time":["00:00:00","23:59:59"],"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.searchForm.date,callback:function(t){e.$set(e.searchForm,"date",t)},expression:"searchForm.date"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"其他"}},[a("el-input",{attrs:{placeholder:"输入账号、流水号搜索"},model:{value:e.searchForm.keywords,callback:function(t){e.$set(e.searchForm,"keywords",t)},expression:"searchForm.keywords"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.toSearch}},[e._v("搜索")])],1)],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],staticClass:"table-flex-item",attrs:{data:e.statementsList,stripe:!0,height:"100px","header-align":"center"}},[a("el-table-column",{attrs:{prop:"id",label:"流水号",width:"150",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userEntity.companyName",label:"还款方名称",width:"150",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"userEntity.mobile",label:"还款方账号",width:"150",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"repaymentAmount",label:"还款金额",width:"120",align:"center"}}),e._v(" "),a("el-table-column",{attrs:{prop:"createDate",label:"还款时间",width:"100",align:"center"}})],1),e._v(" "),a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.size,total:e.page.total,layout:"prev, pager, next, jumper"},on:{"update:currentPage":function(t){e.$set(e.page,"currentPage",t)},"current-change":e.handleCurrentChange}})],1)],1)},[],!1,null,"ef4a2528",null);c.options.__file="repaymentStatements.vue";t.default=c.exports}}]);