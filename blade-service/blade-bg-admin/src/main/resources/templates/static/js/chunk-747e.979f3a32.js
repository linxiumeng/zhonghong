(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-747e"],{A4NR:function(t,e,i){},MEwv:function(t,e,i){"use strict";i.r(e);var n=i("fBUP"),a=i("uJMD"),s=i("RO9D"),o={props:{detail:{type:Object,default:function(){return{}}}},data:function(){var t=function(t,e,i){e?i():i(new Error("不可为空"))};return{editAdminAccountForm:{},rolesList:[],editAdminAccountRules:{mobile:[{required:!0,trigger:"blur",validator:function(t,e,i){s.a.regPhone.test(e)?i():i(new Error("账号需为手机号"))}}],username:[{required:!0,trigger:"blur",validator:t}],rolesNow:[{required:!0,trigger:"change",validator:t}]},isAdd:!1,btnLoading:!1}},created:function(){this.editAdminAccountForm=a.a.deepClone(this.detail),this.isAdd=!this.editAdminAccountForm.mobile,this.isAdd?this.$set(this.editAdminAccountForm,"rolesNow",""):this.$set(this.editAdminAccountForm,"rolesNow",this.editAdminAccountForm.roleEntityList&&this.editAdminAccountForm.roleEntityList[0]?""+this.editAdminAccountForm.roleEntityList[0].roleId:""),this.getRolosList()},methods:{getRolosList:function(){var t=this;this.$http.post(n.a.systemManage.getRolesList,{page:1,size:100}).then(function(e){t.rolesList=e.page.records})},handleEditAdminAccount:function(){var t=this;this.$refs.editAdminAccountForm.validate(function(e){if(!e)return!1;t.btnLoading=!0;var i=t.isAdd?n.a.systemManage.addManager:n.a.systemManage.editManager,a=t.isAdd?{mobile:t.editAdminAccountForm.mobile,password:"A88888888",roleIdList:[t.editAdminAccountForm.rolesNow],status:"1",username:t.editAdminAccountForm.username}:{userId:t.editAdminAccountForm.userId,roleIdList:[t.editAdminAccountForm.rolesNow],username:t.editAdminAccountForm.username};t.$http.post(i,a).then(function(e){t.btnLoading=!1,t.$message({message:"操作成功",type:"success"}),t.$emit("closeLayer")}).catch(function(){t.btnLoading=!1})})}}},r=(i("R9uW"),i("KHd+")),l=Object(r.a)(o,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"edit-admin-account"},[i("el-form",{ref:"editAdminAccountForm",staticClass:"edit-admin-account-form",attrs:{model:t.editAdminAccountForm,rules:t.editAdminAccountRules,"auto-complete":"off","label-position":"right","label-width":"80px"}},[t.isAdd?i("el-form-item",{attrs:{prop:"mobile",label:"账号"}},[i("el-input",{attrs:{name:"mobile",type:"text","auto-complete":"off",maxlength:"15",placeholder:"请输入手机号"},model:{value:t.editAdminAccountForm.mobile,callback:function(e){t.$set(t.editAdminAccountForm,"mobile",e)},expression:"editAdminAccountForm.mobile"}})],1):i("el-form-item",{attrs:{label:"账号"}},[i("span",[t._v(t._s(t.editAdminAccountForm.mobile))])]),t._v(" "),i("el-form-item",{attrs:{prop:"username",label:"姓名"}},[i("el-input",{attrs:{name:"name",type:"text","auto-complete":"off",maxlength:"15",placeholder:"请输入姓名"},model:{value:t.editAdminAccountForm.username,callback:function(e){t.$set(t.editAdminAccountForm,"username",e)},expression:"editAdminAccountForm.username"}})],1),t._v(" "),i("el-form-item",{staticClass:"block",attrs:{prop:"rolesNow",label:"角色"}},[i("el-select",{staticClass:"block",attrs:{placeholder:"请选择角色"},model:{value:t.editAdminAccountForm.rolesNow,callback:function(e){t.$set(t.editAdminAccountForm,"rolesNow",e)},expression:"editAdminAccountForm.rolesNow"}},t._l(t.rolesList,function(t,e){return i("el-option",{key:e,attrs:{label:t.roleName,value:""+t.roleId}})}))],1),t._v(" "),t.isAdd?i("div",[i("p",[t._v("默认密码为: A88888888")])]):t._e(),t._v(" "),i("el-button",{staticClass:"editAdminAccountBtn",attrs:{loading:t.btnLoading,type:"primary"},nativeOn:{click:function(e){return e.preventDefault(),t.handleEditAdminAccount(e)}}},[t._v("\n      提交\n    ")])],1)],1)},[],!1,null,"6ef006cf",null);l.options.__file="editAdminAccount.vue";var c={components:{editAdminAccount:l.exports},data:function(){return{dataList:[],detailData:{},tableLoading:!1,adminLayerTitle:"",page:{currentPage:1,size:10,total:100},adminVisible:!1,statusTable:["冻结","正常"]}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.tableLoading=!0,this.$http.post(n.a.systemManage.managerList,{page:this.page.currentPage,size:this.page.size}).then(function(e){t.tableLoading=!1,e.page.records.map(function(e){return e.roleIdListText=e.roleEntityList&&e.roleEntityList[0]&&e.roleEntityList[0].roleName,e.statusText=t.statusTable[e.status],e}),t.dataList=e.page.records,t.page={currentPage:e.page.current,size:e.page.size,total:e.page.total}}).catch(function(){t.tableLoading=!1})},edit:function(t,e){this.detailData=this.dataList[t],this.adminLayerTitle="编辑账号",this.adminVisible=!0},addAccount:function(){this.detailData={},this.adminLayerTitle="增加账号",this.adminVisible=!0},changeStatus:function(t,e){var i=this,a=0===e.status?1:0,s=0===e.status?"解冻":"冻结";this.$confirm("确定要"+s+"账号 "+e.username+" 吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",center:!0}).then(function(){i.$http.post(n.a.systemManage.editManager,{userId:e.userId,status:a,roleIdList:null}).then(function(e){i.dataList[t].status=a,i.dataList[t].statusText=i.statusTable[a],i.$message({message:"操作成功",type:"success"})})})},resetPsd:function(t,e){var i=this;this.$confirm("确定要重置账号 "+e.username+" 的密码吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",center:!0}).then(function(){i.$http.post(n.a.systemManage.editManager,{userId:e.userId,password:"A88888888",roleIdList:null}).then(function(t){i.$message({message:"操作成功",type:"success"})})})},handleCurrentChange:function(t){this.page.currentPage=t,this.getList()},closeDetail:function(){this.getList(),this.adminVisible=!1}}},d=(i("mNQ0"),Object(r.a)(c,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"admin-account-container flex-table"},[i("el-form",{staticClass:"form-inline",attrs:{inline:!0,size:"mini"}},[t.btnPermission[16]?i("el-form-item",{staticClass:"fr"},[i("el-button",{attrs:{type:"primary"},on:{click:t.addAccount}},[t._v("添加管理员")])],1):t._e()],1),t._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.tableLoading,expression:"tableLoading"}],staticClass:"table-flex-item",attrs:{data:t.dataList,stripe:!0,height:"100px","header-align":"center"}},[i("el-table-column",{attrs:{prop:"userId",label:"编号",width:"120",align:"center"}}),t._v(" "),i("el-table-column",{attrs:{prop:"username",label:"管理员名称",width:"150",align:"center"}}),t._v(" "),i("el-table-column",{attrs:{prop:"mobile",label:"管理员账号",width:"150",align:"center"}}),t._v(" "),i("el-table-column",{attrs:{prop:"roleIdListText",label:"角色",width:"150",align:"center"}}),t._v(" "),i("el-table-column",{attrs:{prop:"createTime",label:"添加时间",width:"100",align:"center"}}),t._v(" "),i("el-table-column",{attrs:{prop:"statusText",label:"状态",width:"140",align:"center"}}),t._v(" "),t.btnPermission[17]||t.btnPermission[18]||t.btnPermission[411]?i("el-table-column",{attrs:{label:"操作",align:"center",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.btnPermission[17]?i("span",{staticClass:"operate-btn",on:{click:function(i){t.edit(e.$index,e.row)}}},[t._v("\n          编辑\n        ")]):t._e(),t._v(" "),t.btnPermission[18]?i("span",{staticClass:"operate-btn",on:{click:function(i){t.changeStatus(e.$index,e.row)}}},[t._v("\n          "+t._s(0===e.row.status?"解冻":"冻结")+"\n        ")]):t._e(),t._v(" "),t.btnPermission[411]?i("span",{staticClass:"operate-btn",on:{click:function(i){t.resetPsd(e.$index,e.row)}}},[t._v("\n          重置密码\n        ")]):t._e()]}}])}):t._e()],1),t._v(" "),i("div",{staticClass:"block pagination"},[i("el-pagination",{attrs:{"current-page":t.page.currentPage,"page-size":t.page.size,total:t.page.total,layout:"prev, pager, next, jumper"},on:{"update:currentPage":function(e){t.$set(t.page,"currentPage",e)},"current-change":t.handleCurrentChange}})],1),t._v(" "),i("el-dialog",{attrs:{visible:t.adminVisible,title:t.adminLayerTitle,width:"500px",center:""},on:{"update:visible":function(e){t.adminVisible=e}}},[t.adminVisible?i("editAdminAccount",{attrs:{detail:t.detailData},on:{closeLayer:t.closeDetail}}):t._e()],1)],1)},[],!1,null,"28e44354",null));d.options.__file="adminAccount.vue";e.default=d.exports},R9uW:function(t,e,i){"use strict";var n=i("szXK");i.n(n).a},mNQ0:function(t,e,i){"use strict";var n=i("A4NR");i.n(n).a},szXK:function(t,e,i){}}]);