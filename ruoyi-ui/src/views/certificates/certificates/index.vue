<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
            <el-form-item label="获奖者姓名" prop="certificatesOwnerName">
                <el-input
                    v-model="queryParams.certificatesOwnerName"
                    placeholder="请输入获奖者姓名"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="获奖者学号" prop="certificatesOwnerScore">
                <el-input
                    v-model="queryParams.certificatesOwnerScore"
                    placeholder="请输入获奖者学号"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="奖项名称" prop="certificatesName">
                <el-input
                    v-model="queryParams.certificatesName"
                    placeholder="请输入奖项名称"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                <el-button icon="el-icon-download" size="mini" @click="getCertificate(scope.row.certificatesPath)">下载</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    plain
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                    v-hasPermi="['certificates:certificates:add']"
                >新增
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['certificates:certificates:edit']"
                >修改
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    plain
                    icon="el-icon-delete"
                    size="mini"
                    :disabled="multiple"
                    @click="handleDelete"
                    v-hasPermi="['certificates:certificates:remove']"
                >删除
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="warning"
                    plain
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['certificates:certificates:export']"
                >导出
                </el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="certificatesList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="获奖者姓名" align="center" prop="certificatesOwnerName"/>
            <el-table-column label="获奖者学号" align="center" prop="certificatesOwnerScore"/>
            <el-table-column label="奖项名称" align="center" prop="certificatesName"/>
            <el-table-column label="奖状路径" align="center" prop="certificatesPath"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['certificates:certificates:edit']"
                    >修改
                    </el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['certificates:certificates:remove']"
                    >删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
        />

        <!-- 添加或修改certificates对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="获奖者姓名" prop="certificatesOwnerName">
                    <el-input v-model="form.certificatesOwnerName" placeholder="请输入获奖者姓名"/>
                </el-form-item>
                <el-form-item label="获奖者学号" prop="certificatesOwnerScore">
                    <el-input v-model="form.certificatesOwnerScore" placeholder="请输入获奖者学号"/>
                </el-form-item>
                <el-form-item label="奖项名称" prop="certificatesName">
                    <el-input v-model="form.certificatesName" placeholder="请输入奖项名称"/>
                </el-form-item>
                <el-form-item label="奖状路径">
                    <file-upload v-model="form.certificatesPath"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import {
    addCertificates,
    delCertificates,
    getCertificates,
    listCertificates,
    updateCertificates
} from "@/api/certificates/certificates";

export default {
    name: "Certificates",
    data() {
        return {
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // certificates表格数据
            certificatesList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                certificatesOwnerName: null,
                certificatesOwnerScore: null,
                certificatesName: null,
                certificatesPath: null
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                certificatesOwnerName: [
                    {required: true, message: "获奖者姓名不能为空", trigger: "blur"}
                ],
                certificatesOwnerScore: [
                    {required: true, message: "获奖者学号不能为空", trigger: "blur"}
                ],
                certificatesName: [
                    {required: true, message: "奖项名称不能为空", trigger: "blur"}
                ],
                certificatesPath: [
                    {required: true, message: "奖状路径不能为空", trigger: "blur"}
                ]
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询certificates列表 */
        getList() {
            this.loading = true;
            listCertificates(this.queryParams).then(response => {
                this.certificatesList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            this.form = {
                certificatesId: null,
                certificatesOwnerName: null,
                certificatesOwnerScore: null,
                certificatesName: null,
                certificatesPath: null
            };
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.certificatesId)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加certificates";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const certificatesId = row.certificatesId || this.ids
            getCertificates(certificatesId).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改certificates";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.certificatesId != null) {
                        updateCertificates(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addCertificates(this.form).then(response => {
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const certificatesIds = row.certificatesId || this.ids;
            this.$modal.confirm('是否确认删除certificates编号为"' + certificatesIds + '"的数据项？').then(function () {
                return delCertificates(certificatesIds);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {
            });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('certificates/certificates/export', {
                ...this.queryParams
            }, `certificates_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
