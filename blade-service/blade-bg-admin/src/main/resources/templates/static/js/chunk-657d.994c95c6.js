(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-657d"],{"09al":function(e,t,n){"use strict";n.r(t);var a=n("QbLZ"),i=n.n(a),o=n("fBUP"),r=n("uJMD"),s={props:{detail:{type:Object,default:function(){return{}}}},data:function(){var e=function(e,t,n){t?n():n(new Error("不可为空"))};return{editAnnouncementForm:{},editAnnouncementRules:{title:[{required:!0,trigger:"blur",validator:e}],desc:[{required:!0,trigger:"blur",validator:e}],isOpen:[{required:!0,trigger:"change",validator:e}]},btnLoading:!1}},created:function(){this.editAnnouncementForm=r.a.deepClone(this.detail),"number"==typeof this.editAnnouncementForm.isOpen&&(this.editAnnouncementForm.isOpen+="")},methods:{handleSubmit:function(){var e=this;this.$refs.editAnnouncementForm.validate(function(t){if(!t)return!1;e.btnLoading=!0;var n=e.editAnnouncementForm.id?o.a.contentManage.changeAnnounce:o.a.contentManage.addAnnounce;e.$http.post(n,{id:e.editAnnouncementForm.id,title:e.editAnnouncementForm.title,desc:e.editAnnouncementForm.desc,isOpen:e.editAnnouncementForm.isOpen}).then(function(t){e.btnLoading=!1,e.$message({message:"操作成功",type:"success"}),e.$emit("closeLayer")}).catch(function(){e.btnLoading=!1})})}}},l=(n("hFgk"),n("KHd+")),c=Object(l.a)(s,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"edit-announcement"},[n("el-form",{ref:"editAnnouncementForm",staticClass:"edit-announcement-form",attrs:{model:e.editAnnouncementForm,rules:e.editAnnouncementRules,"auto-complete":"off","label-position":"right","label-width":"80px"}},[n("el-form-item",{attrs:{prop:"title",label:"标题"}},[n("el-input",{attrs:{name:"title",type:"text","auto-complete":"off",maxlength:"20",placeholder:"请输入标题"},model:{value:e.editAnnouncementForm.title,callback:function(t){e.$set(e.editAnnouncementForm,"title",t)},expression:"editAnnouncementForm.title"}})],1),e._v(" "),n("el-form-item",{attrs:{prop:"desc",label:"内容"}},[n("el-input",{attrs:{rows:7,resize:"none",type:"textarea","auto-complete":"off",maxlength:"200",placeholder:"请输入内容，不超过200字"},model:{value:e.editAnnouncementForm.desc,callback:function(t){e.$set(e.editAnnouncementForm,"desc",t)},expression:"editAnnouncementForm.desc"}})],1),e._v(" "),n("el-form-item",{attrs:{prop:"isOpen",label:"状态"}},[n("el-select",{attrs:{placeholder:"请选择"},model:{value:e.editAnnouncementForm.isOpen,callback:function(t){e.$set(e.editAnnouncementForm,"isOpen",t)},expression:"editAnnouncementForm.isOpen"}},[n("el-option",{attrs:{label:"隐藏",value:"0"}}),e._v(" "),n("el-option",{attrs:{label:"发布",value:"1"}})],1)],1),e._v(" "),n("el-button",{staticClass:"editAnnouncementBtn",attrs:{loading:e.btnLoading,type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.handleSubmit(t)}}},[e._v("\n      提交\n    ")])],1)],1)},[],!1,null,"427ec4de",null);c.options.__file="editAnnouncement.vue";var u={components:{editAnnouncement:c.exports},data:function(){return{searchForm:{status:"",date:"",keywords:""},dataList:[],detailData:{},tableLoading:!1,announceLayerTitle:"",page:{currentPage:1,size:10,total:100},announcementVisible:!1}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.tableLoading=!0,this.$http.post(o.a.contentManage.announceList,i()({page:this.page.currentPage,size:this.page.size,startDate:this.searchForm.date&&this.searchForm.date[0],endDate:this.searchForm.date&&this.searchForm.date[1]},this.searchForm)).then(function(t){t.result.records.map(function(e){return e.isOpenText=0===e.isOpen?"隐藏":"已发布",e.descLittle=e.desc?e.desc.substring(0,20)+"...":"",e}),e.tableLoading=!1,e.dataList=t.result.records,e.page={currentPage:t.result.current,size:t.result.size,total:t.result.total}}).catch(function(){e.tableLoading=!1})},addAnnouncement:function(e){var t=arguments.length>1&&void 0!==arguments[1]?arguments[1]:{};this.announceLayerTitle=t.id?"编辑公告":"新增公告",this.detailData=t,this.announcementVisible=!0},change:function(e,t){var n=this,a=0===t.isOpen?1:0;this.$http.post(o.a.contentManage.changeAnnounce,{id:t.id,isOpen:a}).then(function(t){n.dataList[e].isOpen=a,n.dataList[e].isOpenText=0===a?"隐藏":"已发布",n.$message({message:"操作成功",type:"success"})})},deleteIt:function(e,t){var n=this;this.$confirm("是否确认删除","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",center:!0}).then(function(){n.$http.post(o.a.contentManage.deleteAnnounce,[t.id]).then(function(e){n.getList(),n.$message({message:"删除成功",type:"success"})})})},toSearch:function(){this.page.currentPage=1,this.getList()},handleCurrentChange:function(e){this.page.currentPage=e,this.getList()},closeLayer:function(){this.getList(),this.announcementVisible=!1}}},d=(n("VfKY"),Object(l.a)(u,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"announcement-container flex-table"},[n("el-form",{staticClass:"form-inline",attrs:{inline:!0,model:e.searchForm,size:"mini"}},[n("el-form-item",{attrs:{label:"状态"}},[n("el-select",{attrs:{placeholder:"请选择"},model:{value:e.searchForm.status,callback:function(t){e.$set(e.searchForm,"status",t)},expression:"searchForm.status"}},[n("el-option",{attrs:{label:"全部",value:""}}),e._v(" "),n("el-option",{attrs:{label:"隐藏",value:"0"}}),e._v(" "),n("el-option",{attrs:{label:"已发布",value:"1"}})],1)],1),e._v(" "),n("el-form-item",{attrs:{label:"日期"}},[n("el-date-picker",{attrs:{"default-time":["00:00:00","23:59:59"],"value-format":"yyyy-MM-dd HH:mm:ss",type:"datetimerange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.searchForm.date,callback:function(t){e.$set(e.searchForm,"date",t)},expression:"searchForm.date"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"标题"}},[n("el-input",{attrs:{placeholder:"输入标题搜索"},model:{value:e.searchForm.keywords,callback:function(t){e.$set(e.searchForm,"keywords",t)},expression:"searchForm.keywords"}})],1),e._v(" "),n("el-form-item",[n("el-button",{attrs:{type:"primary"},on:{click:e.toSearch}},[e._v("搜索")])],1),e._v(" "),e.btnPermission[402]?n("el-form-item",{staticClass:"fr"},[n("el-button",{attrs:{type:"primary"},on:{click:e.addAnnouncement}},[e._v("新增公告")])],1):e._e()],1),e._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],staticClass:"table-flex-item",attrs:{data:e.dataList,stripe:!0,height:"100px","header-align":"center"}},[n("el-table-column",{attrs:{prop:"id",label:"序号",width:"100",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"createDate",label:"提交时间",width:"100",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"title",label:"标题",width:"180",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"descLittle",label:"内容",width:"300",align:"center"}}),e._v(" "),n("el-table-column",{attrs:{prop:"isOpenText",label:"状态",width:"100",align:"center"}}),e._v(" "),e.btnPermission[403]||e.btnPermission[404]||e.btnPermission[405]?n("el-table-column",{attrs:{label:"操作",align:"center",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[e.btnPermission[403]?n("span",{staticClass:"operate-btn",on:{click:function(n){e.addAnnouncement(t.$index,t.row)}}},[e._v("\n          编辑\n        ")]):e._e(),e._v(" "),e.btnPermission[405]?n("span",{staticClass:"operate-btn",on:{click:function(n){e.change(t.$index,t.row)}}},[e._v("\n          "+e._s(0===t.row.isOpen?"发布":"隐藏")+"\n        ")]):e._e(),e._v(" "),e.btnPermission[404]?n("span",{staticClass:"operate-btn",on:{click:function(n){e.deleteIt(t.$index,t.row)}}},[e._v("\n          删除\n        ")]):e._e()]}}])}):e._e()],1),e._v(" "),n("div",{staticClass:"block pagination"},[n("el-pagination",{attrs:{"current-page":e.page.currentPage,"page-size":e.page.size,total:e.page.total,layout:"prev, pager, next, jumper"},on:{"update:currentPage":function(t){e.$set(e.page,"currentPage",t)},"current-change":e.handleCurrentChange}})],1),e._v(" "),n("el-dialog",{attrs:{visible:e.announcementVisible,title:e.announceLayerTitle,width:"1000px",center:""},on:{"update:visible":function(t){e.announcementVisible=t}}},[e.announcementVisible?n("editAnnouncement",{attrs:{detail:e.detailData},on:{closeLayer:e.closeLayer}}):e._e()],1)],1)},[],!1,null,"4bc8a6ad",null));d.options.__file="announcement.vue";t.default=d.exports},Qc7f:function(e,t,n){var a=n("dOl7");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);(0,n("SZ7m").default)("5075e09e",a,!0,{})},VfKY:function(e,t,n){"use strict";var a=n("Qc7f");n.n(a).a},WM2v:function(e,t,n){var a=n("agsQ");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);(0,n("SZ7m").default)("e41f0018",a,!0,{})},agsQ:function(e,t,n){(e.exports=n("I1BE")(!1)).push([e.i,".edit-announcement .editAnnouncementBtn[data-v-427ec4de] {\n  display: block;\n  width: 100px;\n  margin: 0 auto;\n}\n",""])},dOl7:function(e,t,n){(e.exports=n("I1BE")(!1)).push([e.i,".announcement-container[data-v-4bc8a6ad] {\n  height: calc(100% - 60px);\n  background: #fff;\n  margin: 30px;\n  width: 1130px;\n}\n.announcement-container .form-inline[data-v-4bc8a6ad] {\n  padding: 20px 20px 0 20px;\n}\n.announcement-container .operate-btn[data-v-4bc8a6ad] {\n  cursor: pointer;\n  text-decoration: underline;\n  color: #409EFF;\n  margin-right: 20px;\n}\n.announcement-container .pagination[data-v-4bc8a6ad] {\n  text-align: center;\n  padding: 10px;\n}\n",""])},hFgk:function(e,t,n){"use strict";var a=n("WM2v");n.n(a).a}}]);