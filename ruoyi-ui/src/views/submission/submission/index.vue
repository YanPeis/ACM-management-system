<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
            <el-form-item label="提交时间" prop="submittime">
                <el-date-picker clearable
                                v-model="queryParams.submittime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="请选择提交时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="题目状态" prop="verdict">
                <el-input
                    v-model="queryParams.verdict"
                    placeholder="请输入题目状态"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="作者" prop="author">
                <el-input
                    v-model="queryParams.author"
                    placeholder="请输入作者"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="用户学号" prop="userscore">
                <el-input
                    v-model="queryParams.userscore"
                    placeholder="请输入用户学号"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item label="题目名称" prop="problemname">
                <el-input
                    v-model="queryParams.problemname"
                    placeholder="请输入题目名称"
                    clearable
                    @keyup.enter.native="handleQuery"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-edit"
                    size="mini"
                    :disabled="single"
                    @click="handleUpdate"
                    v-hasPermi="['submission:submission:edit']"
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
                    v-hasPermi="['submission:submission:remove']"
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
                    v-hasPermi="['submission:submission:export']"
                >导出
                </el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="submissionList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="提交时间" align="center" prop="submittime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.submittime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="题目状态" align="center" prop="verdict"/>
            <el-table-column label="用户学号" align="center" prop="userscore"/>
            <el-table-column label="题目名称" align="center"   prop="problemname">
                <template slot-scope="scope">
                    <el-link type="primary" target="_blank" :href="scope.row.problemweb">{{scope.row.problemname}}</el-link>

                    </template>
            </el-table-column>
            <el-table-column label="内存使用量" align="center" prop="memoryconsumed"/>
            <el-table-column label="编程语言" align="center" prop="programminglanguage"/>
            <el-table-column label="作者" align="center" prop="author"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['submission:submission:edit']"
                    >修改
                    </el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['submission:submission:remove']"
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

        <!-- 添加或修改submission对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="提交时间" prop="submittime">
                    <el-date-picker clearable
                                    v-model="form.submittime"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="请选择提交时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="题目状态" prop="verdict">
                    <el-input v-model="form.verdict" placeholder="请输入题目状态"/>
                </el-form-item>
                <el-form-item label="编程语言" prop="programminglanguage">
                    <el-input v-model="form.programminglanguage" placeholder="请输入编程语言"/>
                </el-form-item>
                <el-form-item label="内存使用量" prop="memoryconsumed">
                    <el-input v-model="form.memoryconsumed" placeholder="请输入内存使用量"/>
                </el-form-item>
                <el-form-item label="作者" prop="author">
                    <el-input v-model="form.author" placeholder="请输入作者"/>
                </el-form-item>
                <el-form-item label="用户学号" prop="userscore">
                    <el-input v-model="form.userscore" placeholder="请输入用户学号"/>
                </el-form-item>
                <el-form-item label="题目网站" prop="problemweb">
                    <el-input v-model="form.problemweb" placeholder="请输入题目网站"/>
                </el-form-item>
                <el-form-item label="题目名称" prop="problemname">
                    <el-input v-model="form.problemname" placeholder="请输入题目名称"/>
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
    addSubmission,
    delSubmission,
    getSubmission,
    listSubmission,
    updateSubmission
} from "@/api/submission/submission";

export default {
    name: "Submission",
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
            // submission表格数据
            submissionList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                submittime: null,
                verdict: null,
                author: null,
                userscore: null,
                problemname: null
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                submittime: [
                    {required: true, message: "提交时间不能为空", trigger: "blur"}
                ],
                verdict: [
                    {required: true, message: "题目状态不能为空", trigger: "blur"}
                ],
                programminglanguage: [
                    {required: true, message: "编程语言不能为空", trigger: "blur"}
                ],
                memoryconsumed: [
                    {required: true, message: "内存使用量不能为空", trigger: "blur"}
                ],
                author: [
                    {required: true, message: "作者不能为空", trigger: "blur"}
                ],
                userscore: [
                    {required: true, message: "用户学号不能为空", trigger: "blur"}
                ],
                problemweb: [
                    {required: true, message: "题目网站不能为空", trigger: "blur"}
                ],
                problemname: [
                    {required: true, message: "题目名称不能为空", trigger: "blur"}
                ]
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询submission列表 */
        getList() {
            this.loading = true;
            listSubmission(this.queryParams).then(response => {
                this.submissionList = response.rows;
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
                submitid: null,
                submittime: null,
                verdict: null,
                programminglanguage: null,
                memoryconsumed: null,
                author: null,
                userscore: null,
                problemweb: null,
                problemname: null
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
            this.ids = selection.map(item => item.submitid)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const submitid = row.submitid || this.ids
            getSubmission(submitid).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改submission";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.submitid != null) {
                        updateSubmission(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addSubmission(this.form).then(response => {
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
            const submitids = row.submitid || this.ids;
            this.$modal.confirm('是否确认删除submission编号为"' + submitids + '"的数据项？').then(function () {
                return delSubmission(submitids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {
            });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('submission/submission/export', {
                ...this.queryParams
            }, `submission_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
