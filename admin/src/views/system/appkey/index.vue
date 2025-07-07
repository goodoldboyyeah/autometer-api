<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true"  style="width: 900px">
        <el-form-item>
          <el-button
            type="success"
            size="mini"
            icon="el-icon-refresh"
            v-if="hasPermission('appkey:list')"
            @click.native.prevent="getappkeyList"
          >刷新
          </el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('appkey:add')"
            @click.native.prevent="showAddappkeyDialog"
          >添加秘钥
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="appkeyList"
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
      <el-table-column :show-overflow-tooltip="true"  label="appid" align="center" prop="appid" width="120"/>
      <el-table-column :show-overflow-tooltip="true"  label="appkey" align="center" prop="appkey" width="300"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">{{ unix2CurrentTime(scope.row.createTime) }}</template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="lastmodifyTime" width="160">
        <template slot-scope="scope">{{ unix2CurrentTime(scope.row.lastmodifyTime) }}
        </template>
      </el-table-column>

      <el-table-column label="管理" align="center"
                       v-if="hasPermission('appkey:update')  || hasPermission('appkey:delete')">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('appkey:update') && scope.row.id !== id"
            @click.native.prevent="showUpdateappkeyDialog(scope.$index)"
          >修改
          </el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('appkey:delete') && scope.row.id !== id"
            @click.native.prevent="removeappkey(scope.$index)"
          >删除
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
        style="width: 400px; margin-left:50px;"
        :model="tmpappkey"
        ref="tmpappkey"
      >
        <el-form-item label="appid" prop="appid" required>
          <el-input style="width: 500px"
            maxlength="40"
            type="text"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            v-model="tmpappkey.appid"
          />
        </el-form-item>
        <el-form-item label="appkey" prop="appkey" required>
          <el-input style="width: 500px"
            type="text"
            maxlength="40"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            v-model.trim="tmpappkey.appkey"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'add'"
          :loading="btnLoading"
          @click.native.prevent="addappkey"
        >添加
        </el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updateappkey"
        >修改
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {
    search,
    addappkey,
    updateappkey,
    removeappkey
  } from '@/api/system/appkey'
  import { unix2CurrentTime } from '@/utils'
  import { mapGetters } from 'vuex'

  export default {
    name: '测试秘钥',
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
        id: null,
        itemKey: null,
        tmpappkeyname: null,
        tmpip: null,
        appkeyList: [], // 秘钥列表
        listLoading: false, // 数据加载等待动画
        total: 0, // 数据总数
        dialogStatus: 'add',
        dialogFormVisible: false,
        textMap: {
          updateRole: '修改秘钥',
          update: '修改秘钥',
          add: '添加秘钥'
        },
        btnLoading: false, // 按钮等待动画
        tmpappkey: {
          id: '',
          appid: '',
          appkey: ''
        },
        search: {
          page: 1,
          size: 10
        }
      }
    },

    computed: {
      ...mapGetters(['name', 'sidebar', 'projectlist', 'projectid', 'accountId'])
    },

    created() {
      this.getappkeyList()
    },

    methods: {
      unix2CurrentTime,

      /**
       * 获取秘钥列表
       */
      getappkeyList() {
        this.listLoading = true
        search(this.search).then(response => {
          this.appkeyList = response.data.list
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
          this.appkeyList = response.data.list
          this.total = response.data.total
        }).catch(res => {
          this.$message.error('搜索失败')
        })
        this.listLoading = false
        this.btnLoading = false
      },

      /**
       * 改变每页数量
       * @param size 页大小
       */
      handleSizeChange(size) {
        this.search.page = 1
        this.search.size = size
        this.getappkeyList()
      },
      /**
       * 改变页码
       * @param page 页号
       */
      handleCurrentChange(page) {
        this.search.page = page
        this.getappkeyList()
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
      showAddappkeyDialog() {
        // 显示新增对话框
        this.dialogFormVisible = true
        this.dialogStatus = 'add'
        this.tmpappkey.id = ''
        this.tmpappkey.appid = ''
        this.tmpappkey.appkey = ''
      },
      /**
       * 添加秘钥
       */
      addappkey() {
        this.$refs.tmpappkey.validate(valid => {
          if (valid) {
            this.btnLoading = true
            addappkey(this.tmpappkey).then(() => {
              this.$message.success('添加成功')
              this.getappkeyList()
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
      showUpdateappkeyDialog(index) {
        this.dialogFormVisible = true
        this.dialogStatus = 'update'
        this.tmpappkey.id = this.appkeyList[index].id
        this.tmpappkey.appid = this.appkeyList[index].appid
        this.tmpappkey.appkey = this.appkeyList[index].appkey
      },
      /**
       * 更新秘钥
       */
      updateappkey() {
        this.$refs.tmpappkey.validate(valid => {
          if (valid) {
            updateappkey(this.tmpappkey).then(() => {
              this.$message.success('更新成功')
              this.getappkeyList()
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
      removeappkey(index) {
        this.$confirm('删除该秘钥？', '警告', {
          confirmButtonText: '是',
          cancelButtonText: '否',
          type: 'warning'
        }).then(() => {
          const id = this.appkeyList[index].id
          removeappkey(id).then(() => {
            this.$message.success('删除成功')
            this.getappkeyList()
          })
        }).catch(() => {
          this.$message.info('已取消删除')
        })
      },

      /**
       * 秘钥是否唯一
       * @param 秘钥
       */
      isUniqueDetail(appkey) {
        for (let i = 0; i < this.appkeyList.length; i++) {
          if (this.appkeyList[i].id !== appkey.id) { // 排除自己
            if (this.appkeyList[i].appkeyname === appkey.appkeyname) {
              this.$message.error('秘钥名已存在')
              return false
            }
            if (this.appkeyList[i].ip === appkey.ip) {
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
