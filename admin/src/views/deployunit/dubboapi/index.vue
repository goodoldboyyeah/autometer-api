<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" style="width: 1300px" >
         <span v-if="hasPermission('api:search')">

          <el-form-item  label="微服务名:" required>
              <el-select v-model="search.deployunitname"  filterable clearable placeholder="微服务"
            @change="deployunitselectChanged($event)">
            <div v-for="(depunitname, index) in deployunitList" :key="index">
              <el-option :label="depunitname.deployunitname" :value="depunitname.deployunitname" required/>
            </div>
          </el-select>
          </el-form-item>
          <el-form-item  label="Interfaces:">
            <el-select v-model="search.modelname" filterable clearable placeholder="Interfaces" @change="modelselectChanged($event)">
            <div v-for="(model, index) in modelList" :key="index">
              <el-option :label="model.modelname" :value="model.modelname" required/>
            </div>
          </el-select>
          </el-form-item>
           <el-form-item  label="API名:">
            <el-input  v-model="search.apiname" clearable @keyup.enter.native="searchBy" placeholder="API名"></el-input>
          </el-form-item>
            <el-form-item  label="版本:">
            <el-input  v-model="search.version" clearable @keyup.enter.native="searchBy" placeholder="版本"></el-input>
          </el-form-item>
            <el-form-item  label="Group:">
            <el-input  v-model="search.dgroup" clearable @keyup.enter.native="searchBy" placeholder="Group"></el-input>
          </el-form-item>
          <el-form-item  label="范围:">
            <el-select v-model="search.nickname" clearable placeholder="范围"  @change="creatorselectChanged($event)">
              <el-option label="我的" value="我的" />
              <el-option label="全部" value="全部" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchBy" :loading="btnLoading">查询</el-button>
          </el-form-item>

        <el-form-item>
          <el-button
            type="success"
            size="mini"
            icon="el-icon-refresh"
            v-if="hasPermission('api:list')"
            @click.native.prevent="getapiList"
          >刷新
          </el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('api:add')"
            @click.native.prevent="showAdddubboapiDialog"
          >新建API
          </el-button>
           <el-button
             type="primary"
             size="mini"
             v-if="hasPermission('api:add')"
             @click.native.prevent="showExportdubboapiDialog"
           >微服务导入API
          </el-button>
<!--          <el-button-->
<!--            type="primary"-->
<!--            size="mini"-->
<!--            v-if="hasPermission('api:add')"-->
<!--            @click.native.prevent="showCopyapiDialog"-->
<!--          >复制API-->
<!--          </el-button>-->
<!--          <el-button-->
<!--            type="danger"-->
<!--            size="mini"-->
<!--            v-if="hasPermission('api:add')"-->
<!--            @click.native.prevent="removebatchapi"-->
<!--          >批量删除-->
<!--          </el-button>-->
        </el-form-item>
         </span>
      </el-form>
    </div>
    <el-table
      :data="dubboapiList"
      :key="itemKey"
      v-loading.body="listLoading"
      element-loading-text="loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="编号" align="center" width="60">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true"  label="API" align="center" prop="apiname" width="100"/>
      <el-table-column :show-overflow-tooltip="true"  label="微服务" align="center" prop="deployunitname" width="100">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="mini"
            @click.native.prevent="showDeployUnit(scope.row)"
          >{{scope.row.deployunitname}}
          </el-button>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true"  label="Interfaces" align="center" prop="modelname" width="80"/>
      <el-table-column :show-overflow-tooltip="true"   label="版本" align="center" prop="version" width="70"/>
      <el-table-column :show-overflow-tooltip="true"   label="group" align="center" prop="dgroup" width="70"/>
      <el-table-column label="响应类型" align="center" prop="responecontenttype" width="80"/>
      <el-table-column label="用例数" align="center" prop="casecounts" width="60"/>
<!--      <el-table-column :show-overflow-tooltip="true"   label="测试维护人" align="center" prop="mnickname" width="90"/>-->
      <el-table-column :show-overflow-tooltip="true"   label="开发维护人" align="center" prop="mnickname" width="90"/>
      <el-table-column :show-overflow-tooltip="true"   label="操作人" align="center" prop="creator" width="60"/>
      <el-table-column :show-overflow-tooltip="true"  label="创建时间" align="center" prop="createTime" width="120">
        <template slot-scope="scope">{{ unix2CurrentTime(scope.row.createTime) }}</template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true"  label="最后修改时间" align="center" prop="lastmodifyTime" width="120">
        <template slot-scope="scope">{{ unix2CurrentTime(scope.row.lastmodifyTime) }}
        </template>
      </el-table-column>

      <el-table-column label="管理" align="center" width="250"
                       v-if="hasPermission('dubboapi:update')  || hasPermission('dubboapi:delete')">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('dubboapi:update') && scope.row.id !== id"
            @click.native.prevent="showUpdatedubboapiDialog(scope.$index)"
          >修改
          </el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('dubboapi:delete') && scope.row.id !== id"
            @click.native.prevent="removedubboapi(scope.$index)"
          >删除
          </el-button>
          <el-button
            type="primary"
            size="mini"
            v-if="hasPermission('api:delete') && scope.row.id !== id"
            @click.native.prevent="ShowNewParamsDialog(scope.$index)"
          >API参数
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="search.page"
      :page-size="search.size"
      :total="total"
      :page-sizes="[10, 20, 30, 40]"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>

    <el-dialog :title="textMap[dialogStatus]"  width="800px"  :visible.sync="dialogFormVisible">
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="120px"
        style="width: 450px; margin-left:50px;"
        :model="tmpdubboapi"
        ref="tmpdubboapi"
      >
        <el-form-item label="API名称:" prop="apiname" required>
          <el-input style="width: 500px"
                    placeholder="API名称"
                    type="text"
                    maxlength="40"
                    prefix-icon="el-icon-edit"
                    auto-complete="off"
                    v-model="tmpdubboapi.apiname"
          />
        </el-form-item>

        <el-form-item label="微服务:" prop="deployunitname" required>
          <el-select v-model="tmpdubboapi.deployunitname" style="width: 500px" filterable clearable placeholder="微服务"
                     @change="deployunitselectChanged($event)">
            <el-option label="请选择" value="''" style="display: none"/>
            <div v-for="(depunitname, index) in deployunitList" :key="index">
              <el-option :label="depunitname.deployunitname" :value="depunitname.deployunitname" required/>
            </div>
          </el-select>
        </el-form-item>

        <el-form-item label="Interfaces:" prop="modelname" required>
          <el-select v-model="tmpdubboapi.modelname" filterable clearable placeholder="Interfaces"  style="width: 500px"
                     @change="modelselectChanged($event)">
            <el-option label="请选择" value="''" style="display: none"/>
            <div v-for="(model, index) in modelList" :key="index">
              <el-option :label="model.modelname" :value="model.modelname" required/>
            </div>
          </el-select>
        </el-form-item>

        <el-form-item label="版本:" prop="version" required>
          <el-select v-model="tmpdubboapi.version" filterable clearable placeholder="版本"  style="width: 500px">
            <el-option label="请选择" value="''" style="display: none"/>
            <div v-for="(model, index) in modelList" :key="index">
              <el-option :label="model.version" :value="model.version" required/>
            </div>
          </el-select>
        </el-form-item>

        <el-form-item label="分组:" prop="dgroup" required>
          <el-select v-model="tmpdubboapi.dgroup" filterable clearable placeholder="分组"  style="width: 500px">
            <el-option label="请选择" value="''" style="display: none"/>
            <div v-for="(model, index) in modelList" :key="index">
              <el-option :label="model.dgroup" :value="model.dgroup" required/>
            </div>
          </el-select>
        </el-form-item>


        <el-form-item label="响应类型:" prop="modelname" required>
          <el-select v-model="tmpdubboapi.responecontenttype" filterable clearable placeholder="响应类型"  style="width: 500px">
            <el-option label="基础类型" value="Basic" />
            <el-option label="对象类型" value="Json" />
          </el-select>
        </el-form-item>


        <el-form-item label="维护人:" prop="mnickname" required>
          <el-select v-model="tmpdubboapi.mnickname" filterable clearable placeholder="维护人"  style="width: 500px"
                     @change="mnicknameselectChanged($event)">
            <div v-for="(mnickname, index) in accountList" :key="index">
              <el-option :label="mnickname.nickname" :value="mnickname.nickname" required/>
            </div>
          </el-select>
        </el-form-item>

        <el-form-item label="备注:" prop="memo">
          <el-input style="width: 500px"
                    type="text"
                    maxlength="100"
                    prefix-icon="el-icon-message"
                    auto-complete="off"
                    v-model="tmpdubboapi.memo"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="danger"
          v-if="dialogStatus === 'add'"
          @click.native.prevent="$refs['tmpdubboapi'].resetFields()"
        >重置
        </el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'add'"
          :loading="btnLoading"
          @click.native.prevent="adddubboapi"
        >添加
        </el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updatedubboapi"
        >修改
        </el-button>
      </div>
    </el-dialog>

    <el-dialog  title='API参数' width="900px" :visible.sync="NewParamsdialogFormVisible">
      <div class="filter-container">
        <el-form :inline="true">
          <template>
            <el-tabs v-model="activeName" @tab-click="tabclick" type="card" ref="tabs">
              <el-tab-pane label="参数" name="zero">
                <template>
                  <el-table :data="Headertabledatas" border @selection-change="handleSelectionChange">
                    <el-table-column label="参数名" prop="keyname" align="center">
                      <template slot-scope="scope">
                        <el-input size="mini" placeholder="参数名" v-model="scope.row.keyname"></el-input>
                      </template>
                    </el-table-column>
                    <el-table-column label="参数类型" prop="paramstype" align="center">
                      <template slot-scope="scope">
                        <el-select style="width: 120px" v-model="scope.row.paramstype" filterable placeholder="参数类型" >
                          <div v-for="(Paramstype, index) in ParamstypeList" :key="index">
                            <el-option :label="Paramstype.dicitmevalue" :value="Paramstype.dicitmevalue"/>
                          </div>
                        </el-select>
                      </template>
                    </el-table-column>
                    <el-table-column label="默认值" prop="keydefaultvalue" align="center">
                      <template slot-scope="scope">
                        <el-input size="mini" placeholder="默认值" v-model="scope.row.keydefaultvalue"></el-input>
                      </template>
                    </el-table-column>

                    <el-table-column label="操作" align="center" width="200">
                      <template slot-scope="scope">
                        <!--                        <el-button type="primary" size="mini" @click="UseParams(scope.row,scope.$index)">使用变量-->
                        <!--                        </el-button>-->
                        <el-button type="primary" size="mini" @click="copeHeader(scope.row,scope.$index)">新增
                        </el-button>
                        <el-button type="primary" size="mini" @click="delectHeader(scope.$index)">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                </template>
              </el-tab-pane>
              <el-tab-pane label="变量使用" name="third">
                <uservariables></uservariables>
              </el-tab-pane>
            </el-tabs>
          </template>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="NewParamsdialogFormVisible = false">取消</el-button>
        <el-button
          type="success"
          :loading="btnLoading"
          @click.native.prevent="addapiallparams"
        >保存
        </el-button>
      </div>
    </el-dialog>

    <el-dialog title="微服务导入" width="800px" :visible.sync="ModelsFormVisible">
      <div class="filter-container">
        <el-form :inline="true">
          <el-form-item  label="微服务名:">
            <el-select v-model="searchmodel.deployunitname"  filterable  placeholder="微服务"
                       @change="deployunitselectChanged($event)">
              <div v-for="(depunitname, index) in deployunitList" :key="index">
                <el-option :label="depunitname.deployunitname" :value="depunitname.deployunitname" required/>
              </div>
            </el-select>
          </el-form-item>
          <el-form-item  label="Interfaces:">
            <el-select v-model="searchmodel.modelname" filterable  placeholder="Interfaces" @change="modelselectChanged($event)">
              <div v-for="(model, index) in modelList" :key="index">
                <el-option :label="model.modelname" :value="model.modelname" required/>
              </div>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="searchModelBy" :loading="btnLoading">查询</el-button>
          </el-form-item>
        </el-form>
      </div>

      <el-table
        :data="exportmodelsList"
        @selection-change="handleSelectionChange"
        element-loading-text="loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column
          type="selection"
          width="40">
        </el-table-column>
        <el-table-column label="编号" align="center" width="45">
          <template slot-scope="scope">
            <span v-text="modelgetIndex(scope.$index)"></span>
          </template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true"  label="Interfaces名" align="center" prop="modelname" width="300"/>
        <el-table-column :show-overflow-tooltip="true"  label="版本" align="center" prop="version" width="100"/>
        <el-table-column :show-overflow-tooltip="true"  label="分组" align="center" prop="dgroup" width="100"/>

        <el-table-column label="管理" align="center" width="180"
                         v-if="hasPermission('dubboapi:update')  || hasPermission('dubboapi:delete')">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click.native.prevent="showMethodsDialog(scope.$index)"
            >Methods
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="modelhandleSizeChange"
        @current-change="modelhandleCurrentChange"
        :current-page="searchmodel.page"
        :page-size="searchmodel.size"
        :total="searchmodeltotal"
        :page-sizes="[10, 20, 30, 40]"
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="ModelsFormVisible = false">取消</el-button>
        <el-button
          type="success"
          @click.native.prevent="exportapi"
        >添加</el-button>
      </div>
    </el-dialog>

    <el-dialog title="Methods" width="800px" :visible.sync="MethodsFormVisible">
      <el-table
        :data="methodsList"
        element-loading-text="loading"
        border
        fit
        highlight-current-row
      >
        <el-table-column :show-overflow-tooltip="true"  label="Methods" align="center" prop="methodname" width="350"/>
        <el-table-column :show-overflow-tooltip="true"  label="创建时间" align="center" prop="createTime" width="220">
          <template slot-scope="scope">{{ unix2CurrentTime(scope.row.createTime) }}</template>
        </el-table-column>
        <el-table-column :show-overflow-tooltip="true"  label="最后修改时间" align="center" prop="lastmodifyTime" width="220">
          <template slot-scope="scope">{{ unix2CurrentTime(scope.row.lastmodifyTime) }}
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

  </div>
</template>
<script>
import {
  search,
  adddubboapi,
  updatedubboapi,
  removedubboapi,
  exportapi
} from '@/api/deployunit/dubboapi'
import { getdepunitLists as getdepunitLists } from '@/api/deployunit/depunit'
import { searchdeployunitmodel } from '@/api/deployunit/depunitmodel'
import { searchallaccount as searchallaccount } from '@/api/account'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import { getdatabydiccodeList as getdatabydiccodeList } from '@/api/system/dictionary'
import uservariables from '@/components/testvariables'
import { addapiallparams, removeapiparams, searchbyid } from '@/api/deployunit/dubboapiparams'
import { search as searchmethods } from '@/api/deployunit/dubbomodelmethods'

export default {
  name: 'dubboapi',
  components: { uservariables },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      ModelsFormVisible: false,
      tmpapiname: '',
      tmpsearchmodelmodelname: '',
      tmpsearchmodeldeployunitname: '',
      tmpdeployunitname: '',
      tmpmodelname: '',
      tmpversion: null,
      tmpdgroup: null,
      id: null,
      itemKey: null,
      methodsList: [],
      InterfaceExportList: [],
      multipleSelection: [],
      ParamstypeList: [],
      exportmodelsList: [],
      accountList: [],
      tmpdubboapiname: null,
      tmpip: null,
      Headertabledatas: [],
      modelList: [],
      deployunitList: [], // 服务列表
      dubboapiList: [], // 秘钥列表
      listLoading: false, // 数据加载等待动画
      total: 0, // 数据总数
      searchmodeltotal: 0,
      dialogStatus: 'add',
      dialogFormVisible: false,
      MethodsFormVisible: false,
      NewParamsdialogFormVisible: false,
      textMap: {
        updateRole: '修改API',
        update: '修改API',
        add: '添加API'
      },
      btnLoading: false, // 按钮等待动画
      tmpdubboapi: {
        id: '',
        deployunitid: '',
        deployunitname: '',
        modelid: '',
        modelname: '',
        apiname: '',
        version: '',
        dgroup: '',
        responecontenttype: '',
        creator: '',
        casecounts: 0,
        projectid: '',
        mnickname: '',
        creatorid: '',
        memo: '',
        mid: ''
      },
      tmpapiparams: {
        id: '',
        apiid: '',
        deployunitid: '',
        apiname: '',
        deployunitname: '',
        keyname: '',
        keydefaultvalue: '',
        paramstype: 'int',
        creator: ''
      },
      tmpapi: {
        id: '',
        deployunitid: '',
        deployunitname: '',
        apiname: ''
      },
      tmpmodelquery: {
        page: 1,
        size: 100,
        deployunitid: '',
        servicetype: 'Dubbo服务'
      },
      dicParamsQuery: {
        page: 1, // 页码
        size: 30, // 每页数量
        diccode: 'DubboParamsType' // 获取字典表入参
      },
      searchmodel: {
        page: 1,
        size: 20,
        deployunitname: null,
        modelname: null,
        servicetype: 'Dubbo服务',
        deployunitid: null,
        projectid: ''
      },
      searchmethods: {
        modelid: null
      },
      search: {
        page: 1,
        size: 10,
        apiname: null,
        deployunitname: null,
        modelname: null,
        version: null,
        dgroup: null,
        servicetype: 'Dubbo服务',
        projectid: ''
      }
    }
  },

  computed: {
    ...mapGetters(['name', 'nickname', 'sidebar', 'projectlist', 'projectid', 'accountId'])
  },

  created() {
    this.getaccountLists()
    this.search.projectid = window.localStorage.getItem('pid')
    this.searchmodel.projectid = window.localStorage.getItem('pid')
    this.getdubboapiList()
    this.getdepunitLists()
    this.getparamstypeList()
    this.Headertabledatas = [
      { id: '', keyname: '', keytype: '', keydefaultvalue: '', paramstype: 'int', creatorid: this.accountId, creator: this.nickname }
    ]
  },

  activated() {
    console.log('接收行：', this.$route.query.apiname)
    this.tmpapiname = this.$route.query.apiname
    this.search.projectid = window.localStorage.getItem('pid')
    this.getdubboapiList()
    this.getdepunitLists()
    this.getaccountLists()
    this.getparamstypeList()
  },

  methods: {
    unix2CurrentTime,

    handleSelectionChange(rows) {
      this.multipleSelection = rows
    },

    modelgetIndex(index) {
      return (this.searchmodel.page - 1) * this.searchmodel.size + index + 1
    },

    creatorselectChanged(e) {
      if (e === '全部') {
        this.search.mid = null
      } else {
        this.search.mid = this.accountId
      }
    },

    showDeployUnit(e) {
      console.log('当前行：', e.deployunitname)
      this.$router.push({ path: '/deployunit/dubbodepunit/list', query: { deployunitname: e.deployunitname }})
    },

    addapiallparams(index) {
      for (let i = 0; i < this.Headertabledatas.length; i++) {
        this.Headertabledatas[i].apiid = this.tmpapi.id
        this.Headertabledatas[i].apiname = this.tmpapi.apiname
        this.Headertabledatas[i].deployunitid = this.tmpapi.deployunitid
        this.Headertabledatas[i].deployunitname = this.tmpapi.deployunitname
        this.Headertabledatas[i].creatorid = this.accountId
      }
      console.log('this.Headertabledatas is.....................')
      console.log(this.Headertabledatas)
      addapiallparams(this.Headertabledatas).then(() => {
        this.$message.success('添加参数成功')
      }).catch(res => {
        this.$message.error('添加Header失败')
      })
      this.NewParamsdialogFormVisible = false
    },

    getparamstypeList() {
      getdatabydiccodeList(this.dicParamsQuery).then(response => {
        this.ParamstypeList = response.data.list
      }).catch(res => {
        this.$message.error('加载字典访问方式列表失败')
      })
    },

    copeHeader(val, index) {
      var newrow = {
        id: '',
        keyname: '',
        keytype: '',
        keydefaultvalue: '',
        apiid: '',
        apiname: '',
        deployunitid: '',
        deployunitname: '',
        paramstype: 'int',
        creatorid: this.accountId,
        creator: this.nickname
      }
      console.log(newrow)
      this.Headertabledatas.splice(index + 1, 0, JSON.parse(JSON.stringify(newrow)))
    },

    delectHeader(index) {
      // this.tabledatas.splice(index, 1)
      console.log(this.Headertabledatas)
      if (this.Headertabledatas[index].id !== '') {
        // 服务端删除
        this.$confirm('删除参数，用到此参数的所有用例值会跟随一起删除？', '警告', {
          confirmButtonText: '是',
          cancelButtonText: '否',
          type: 'warning'
        }).then(() => {
          const id = this.Headertabledatas[index].id
          removeapiparams(id).then(() => {
            this.$message.success('删除成功')
            this.Headertabledatas.splice(index, 1)
            if (this.Headertabledatas.length === 0) {
              var newrow = {
                id: '',
                keyname: '',
                keytype: '',
                keydefaultvalue: '',
                apiid: '',
                apiname: '',
                deployunitid: '',
                deployunitname: '',
                paramstype: 'int',
                creatorid: this.accountId,
                creator: this.nickname
              }
              this.Headertabledatas.splice(1, 0, JSON.parse(JSON.stringify(newrow)))
              console.log(this.Headertabledatas)
            }
            this.$foreceUpdate()
          })
        }).catch(() => {
          this.$message.info('删除取消')
        })
      } else {
        this.Headertabledatas.splice(index, 1)
        console.log(this.Headertabledatas.length)
        if (this.Headertabledatas.length === 0) {
          var newrow = {
            id: '',
            keyname: '',
            keytype: '',
            keydefaultvalue: '',
            apiid: '',
            apiname: '',
            deployunitid: '',
            deployunitname: '',
            paramstype: 'int',
            creatorid: this.accountId,
            creator: this.nickname
          }
          this.Headertabledatas.splice(1, 0, JSON.parse(JSON.stringify(newrow)))
          console.log(this.Headertabledatas)
        }
        this.$foreceUpdate()
      }
    },

    mnicknameselectChanged(e) {
      for (let i = 0; i < this.accountList.length; i++) {
        if (this.accountList[i].nickname === e) {
          this.tmpdubboapi.mid = this.accountList[i].id
        }
      }
    },

    getaccountLists() {
      this.accountList = null
      searchallaccount().then(response => {
        this.accountList = response.data
      }).catch(res => {
        this.$message.error('加载服务列表失败')
      })
    },

    modelselectChanged(e) {
      for (let i = 0; i < this.modelList.length; i++) {
        if (this.modelList[i].modelname === e) {
          this.tmpdubboapi.modelid = this.modelList[i].id
        }
      }
    },

    deployunitselectChanged(e) {
      for (let i = 0; i < this.deployunitList.length; i++) {
        if (this.deployunitList[i].deployunitname === e) {
          this.tmpmodelquery.deployunitid = this.deployunitList[i].id
          this.tmpdubboapi.deployunitid = this.deployunitList[i].id
          this.searchmodel.deployunitid = this.deployunitList[i].id
        }
        this.searchmodel.modelname = null
        console.log(this.deployunitList[i].id)
        this.searchdeployunitmodel()
      }
    },

    searchdeployunitmodel() {
      this.modelList = null
      this.tmpdubboapi.modelname = null
      searchdeployunitmodel(this.tmpmodelquery).then(response => {
        this.modelList = response.data.list
      }).catch(res => {
        this.$message.error('加载服务模块列表失败')
      })
    },

    searchmethod() {
      searchmethods(this.searchmethods).then(response => {
        this.methodsList = response.data
      }).catch(res => {
        this.$message.error('加载服务列表失败')
      })
    },

    getdepunitLists() {
      this.listLoading = true
      getdepunitLists(this.search).then(response => {
        this.deployunitList = response.data
        this.listLoading = false
      }).catch(res => {
        this.$message.error('加载服务列表失败')
      })
    },
    /**
     * 获取秘钥列表
     */
    getdubboapiList() {
      this.listLoading = true
      this.search.apiname = this.tmpapiname
      this.search.deployunitname = this.tmpdeployunitname
      this.search.modelname = this.tmpmodelname
      this.search.version = this.tmpversion
      this.search.dgroup = this.tmpdgroup
      search(this.search).then(response => {
        this.dubboapiList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(res => {
        this.$message.error('加载秘钥列表失败')
      })
    },

    searchBy() {
      this.search.page = 1
      this.listLoading = true
      search(this.search).then(response => {
        this.itemKey = Math.random()
        this.dubboapiList = response.data.list
        this.total = response.data.total
      }).catch(res => {
        this.$message.error('搜索失败')
      })
      this.listLoading = false
      this.btnLoading = false
      this.tmpapiname = this.search.apiname
      this.tmpdeployunitname = this.search.deployunitname
      this.tmpmodelname = this.search.modelname
      this.tmpversion = this.search.version
      this.tmpdgroup = this.search.dgroup
    },

    searchModelBy() {
      this.searchmodel.page = 1
      searchdeployunitmodel(this.searchmodel).then(response => {
        this.exportmodelsList = response.data.list
        this.searchmodeltotal = response.data.total
      }).catch(res => {
        this.$message.error('搜索失败')
      })
      this.tmpsearchmodelmodelname = this.searchmodel.modelname
      this.tmpsearchmodeldeployunitname = this.searchmodel.deployunitname
    },

    getsearchmodelList() {
      this.searchmodel.modelname = this.tmpsearchmodelmodelname
      this.searchmodel.deployunitname = this.tmpsearchmodeldeployunitname
      searchdeployunitmodel(this.searchmodel).then(response => {
        this.exportmodelsList = response.data.list
        this.searchmodeltotal = response.data.total
      }).catch(res => {
        this.$message.error('加载秘钥列表失败')
      })
    },
    /**
     * 改变每页数量
     * @param size 页大小
     */
    handleSizeChange(size) {
      this.search.page = 1
      this.search.size = size
      this.getdubboapiList()
    },

    modelhandleSizeChange(size) {
      this.searchmodel.page = 1
      this.searchmodel.size = size
      this.getsearchmodelList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.search.page = page
      this.getdubboapiList()
    },

    modelhandleCurrentChange(page) {
      this.searchmodel.page = page
      this.getsearchmodelList()
    },
    /**
     * 表格序号
     * 可参考自定义表格序号
     * http://element-cn.eleme.io/#/zh-CN/component/table#zi-ding-yi-suo-yin
     * @param index 数据下标
     * @returns 表格序号
     */
    getIndex(index) {
      return (this.search.page - 1) * this.search.size + index + 1
    },
    /**
     * 显示添加秘钥对话框
     */
    showAdddubboapiDialog() {
      // 显示新增对话框
      this.dialogFormVisible = true
      this.dialogStatus = 'add'
      this.tmpdubboapi.id = ''
      this.tmpdubboapi.deployunitid = ''
      this.tmpdubboapi.deployunitname = ''
      this.tmpdubboapi.responecontenttype = ''
      this.tmpdubboapi.modelid = ''
      this.tmpdubboapi.modelname = ''
      this.tmpdubboapi.version = ''
      this.tmpdubboapi.dgroup = ''
      this.tmpdubboapi.apiname = ''
      this.tmpdubboapi.memo = ''
      this.tmpdubboapi.mid = ''
      this.tmpdubboapi.mnickname = ''
      this.tmpdubboapi.creatorid = this.accountId
      this.tmpdubboapi.creator = this.nickname
      this.tmpdubboapi.projectid = window.localStorage.getItem('pid')
    },

    showExportdubboapiDialog() {
      // 显示新增对话框
      this.ModelsFormVisible = true
      this.searchmodel.modelname = null
      this.searchmodel.deployunitid = null
      this.searchmodel.deployunitname = null
      this.exportmodelsList = null
      this.multipleSelection = []
    },

    exportapi() {
      this.InterfaceExportList = []
      if (this.multipleSelection.length === 0) {
        this.$message.error('请选择导入的Interface，或请确认已经在微服务-Iterface中已经完成导入')
      } else {
        for (let i = 0; i < this.multipleSelection.length; i++) {
          this.InterfaceExportList.push({
            'id': this.multipleSelection[i].id,
            'deployunitid': this.multipleSelection[i].deployunitid,
            'dgroup': this.multipleSelection[i].dgroup,
            'version': this.multipleSelection[i].version,
            'modelname': this.multipleSelection[i].modelname,
            'creator': this.name,
            'mnickname': this.nickname,
            'creatorid': this.accountId,
            'mid': this.accountId,
            'projectid': window.localStorage.getItem('pid')
          })
        }
        exportapi(this.InterfaceExportList).then(() => {
          this.ModelsFormVisible = false
          this.$message.success('导入中。。。。。。。。。。。。请稍后刷新页面查看')
        }).catch(res => {
          this.$message.error('导入失败')
        })
      }
    },

    ShowNewParamsDialog(index) {
      // 显示新增对话框
      this.activeName = 'zero'
      this.NewParamsdialogFormVisible = true
      this.tmpapi.id = this.dubboapiList[index].id
      this.tmpapi.deployunitid = this.dubboapiList[index].deployunitid
      this.tmpapi.deployunitname = this.dubboapiList[index].deployunitname
      this.tmpapi.apiname = this.dubboapiList[index].apiname
      this.searchbyid()
      console.log(this.tmpapi)
    },

    showMethodsDialog(index) {
      // 显示新增对话框
      this.MethodsFormVisible = true
      this.searchmethods.modelid = this.exportmodelsList[index].id
      this.searchmethod()
    },

    searchbyid() {
      searchbyid(this.tmpapi).then(response => {
        this.Headertabledatas = response.data
        if (this.Headertabledatas.length === 0) {
          var newrow = {
            id: '',
            keyname: '',
            keytype: '',
            keydefaultvalue: '',
            apiid: '',
            apiname: '',
            deployunitid: '',
            deployunitname: '',
            paramstype: 'int',
            creatorid: this.accountId,
            creator: this.nickname
          }
          this.Headertabledatas.splice(1, 0, JSON.parse(JSON.stringify(newrow)))
        }
      }).catch(res => {
        this.$message.error('加载请求数据格式列表失败')
      })
    },

    /**
     * 添加秘钥
     */
    adddubboapi() {
      this.$refs.tmpdubboapi.validate(valid => {
        if (valid) {
          this.btnLoading = true
          adddubboapi(this.tmpdubboapi).then(() => {
            this.$message.success('添加成功')
            this.getdubboapiList()
            this.dialogFormVisible = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加失败')
            this.btnLoading = false
          })
        }
      })
    },
    /**
     * 显示修改秘钥对话框
     * @param index 秘钥下标
     */
    showUpdatedubboapiDialog(index) {
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      this.tmpdubboapi.id = this.dubboapiList[index].id
      this.tmpdubboapi.apiname = this.dubboapiList[index].apiname
      this.tmpdubboapi.deployunitname = this.dubboapiList[index].deployunitname
      this.tmpdubboapi.modelname = this.dubboapiList[index].modelname
      this.tmpdubboapi.deployunitid = this.dubboapiList[index].deployunitid
      this.tmpdubboapi.modelid = this.dubboapiList[index].modelid
      this.tmpdubboapi.version = this.dubboapiList[index].version
      this.tmpdubboapi.dgroup = this.dubboapiList[index].dgroup
      this.tmpdubboapi.responecontenttype = this.dubboapiList[index].responecontenttype
      this.tmpdubboapi.memo = this.dubboapiList[index].memo
      this.tmpdubboapi.casecounts = this.dubboapiList[index].casecounts
      this.tmpdubboapi.creator = this.dubboapiList[index].creator
      this.tmpdubboapi.creatorid = this.accountId
      this.tmpdubboapi.mnickname = this.dubboapiList[index].mnickname
      this.tmpdubboapi.mid = this.dubboapiList[index].mid
      this.tmpmodelquery.deployunitid = this.tmpdubboapi.deployunitid
      searchdeployunitmodel(this.tmpmodelquery).then(response => {
        this.modelList = response.data.list
      }).catch(res => {
        this.$message.error('加载服务模块列表失败')
      })
    },
    /**
     * 更新秘钥
     */
    updatedubboapi() {
      this.$refs.tmpdubboapi.validate(valid => {
        if (valid) {
          updatedubboapi(this.tmpdubboapi).then(() => {
            this.$message.success('更新成功')
            this.getdubboapiList()
            this.dialogFormVisible = false
          }).catch(res => {
            this.$message.error('更新失败')
          })
        }
      })
    },

    /**
     * 删除秘钥
     * @param index 秘钥下标
     */
    removedubboapi(index) {
      this.$confirm('删除该秘钥？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const id = this.dubboapiList[index].id
        removedubboapi(id).then(() => {
          this.$message.success('删除成功')
          this.getdubboapiList()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },

    /**
     * 秘钥是否唯一
     * @param 秘钥
     */
    isUniqueDetail(dubboapi) {
      for (let i = 0; i < this.dubboapiList.length; i++) {
        if (this.dubboapiList[i].id !== dubboapi.id) { // 排除自己
          if (this.dubboapiList[i].dubboapiname === dubboapi.dubboapiname) {
            this.$message.error('秘钥名已存在')
            return false
          }
          if (this.dubboapiList[i].ip === dubboapi.ip) {
            this.$message.error('秘钥名ip已存在')
            return false
          }
        }
      }
      return true
    }
  }
}
</script>
