<template>
    <div class="app-container">
        <el-table v-loading="loading" :data="certificatesList">
            <el-table-column type="selection" width="55" align="center"/>
            <el-table-column label="获奖者姓名" align="center" prop="certificatesOwnerName"/>
            <el-table-column label="获奖者学号" align="center" prop="certificatesOwnerScore"/>
            <el-table-column label="奖项名称" align="center" prop="certificatesName"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button class="el-button" @click="getCertificate(scope.row.certificatesPath)">下载</el-button>
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

    </div>
</template>

<script>
import {listCertificatesUser} from "@/api/certificates/certificates";


export default {
    name: "CertificatesUser",
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
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询certificates列表 */
        getList() {
            this.loading = true;
            listCertificatesUser().then(response => {
                this.certificatesList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        getCertificate(certificatePath) {
            console.log(certificatePath)
            this.$download.resource(certificatePath)
        }
    }
};
</script>
