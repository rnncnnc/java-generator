<script setup>
import { ref } from "vue"
import { ElMessage } from "element-plus"

import { useIndexStore } from "@/store/index"

const tableList = ref([])

// 设置表列表
const setTableList = (data) => {
    tableList.value = data
}


// 选择要生成的表
const multipleSelection = ref([])
// 选择项变化处理
const handleSelectionChange = (val) => {
  multipleSelection.value = val
}

const emit = defineEmits(["cancel", "submit"])




// 处理提交
const handleSubmit = () => {


    if (multipleSelection.value.length === 0) {
        ElMessage.error('请选择要生成的表')
        return
    }

    emit("submit", multipleSelection.value)
}

// 处理取消
const handleCancel = () => {
    tableList.value = []

    emit("cancel")
}

defineExpose({
    setTableList,
    clean: () => {
        tableList.value = []
    }
})


</script>

<template>
    <div class="table-box">
        <el-table
            max-height="650"
            :data="tableList"
            :border="false"
            :preserve-expanded-content="false"
            style="width: 100%"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55" />
            <el-table-column type="expand">
                <template #default="props">
                    <div>
                        <h3>Fields</h3>
                        <el-table :data="props.row.fields" :border="false">
                            <el-table-column label="Field" prop="fieldColumn" />
                            <el-table-column label="Type" prop="fieldDataType" />
                            <el-table-column label="Key" prop="fieldKey" />
                            <el-table-column label="Comment" prop="fieldComment" />
                        </el-table>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="Table" prop="tableOriginName" />
            <el-table-column label="Comment" prop="tableComment" />
        </el-table>

        <div class="btn-group">
            <el-button type="primary" @click="handleSubmit">确定</el-button>
            <el-button type="danger" @click="handleCancel">取消</el-button>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.table-box {
    padding: 20px;
}
.btn-group {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
}
</style>