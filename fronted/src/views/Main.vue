<script setup>
import DataBaseForm from '@/components/DataBaseForm.vue'
import InfoSelect from '@/components/InfoSelect.vue'
import Preview from '@/components/Preview.vue'

import { ref } from 'vue'

const dataBaseFormRef = ref()
const infoSelectRef = ref()

const activeTab = ref(1)

// step 1 填写数据库基本内容 获取数据库信息
// 获取数据库信息完毕
const handleFinished = (data) => {
  activeTab.value = 2

  infoSelectRef.value.setTableList(data)
}


// step 2 选择要生成的表
// 取消生成
const handleCancel = () => {
  activeTab.value = 1
}

// 提交生成
const handleSubmit = () => {
  activeTab.value = 3
}

// step 3 预览
const ymlParserRef = ref()


// step4 生成代码
// 完成
const handleOver = () => {
  activeTab.value = 1
  infoSelectRef.value.clean()
  dataBaseFormRef.value.clean()
}
</script>

<template>
    <div class="main">
      <div class="tabs">
        <el-tabs tab-position="left" style="height: auto" :stretch="true" v-model="activeTab">
          <el-tab-pane label="填写数据库信息" :name="1">
            <DataBaseForm ref="dataBaseFormRef" @finished="handleFinished" />
          </el-tab-pane>
          <el-tab-pane label="选择要生成的表" :name="2">
            <InfoSelect ref="infoSelectRef" @cancel="handleCancel" @submit="handleSubmit" />
          </el-tab-pane>
          <el-tab-pane label="预览" :name="3">
            <Preview />
          </el-tab-pane>
          <el-tab-pane label="完成" :name="4">
              <el-result title="生成成功" sub-title="恭喜您，代码已生成">
                <template #icon>
                  <el-image
                    style="width: 400px; height: 400px"
                    fit="cover"
                    src="https://www.bakistrim.site:5231/images/2025/08/03/wink.jpg"
                  />
                </template>
                <template #extra>
                  <el-button type="primary" @click="handleOver">Back</el-button>
                </template>
              </el-result>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
</template>

<style lang="scss" scoped>
.main {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
 // ... 其他保持不变的样式 ...
  width: calc(100% - 2px); // 补偿边框宽度
  height: calc(100vh - 20px); // 补偿边框高度
  margin: 0 auto; // 水平居中
  overflow: auto; // 添加滚动条控制
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px #ccc;

  .tabs {
    overflow: auto;
    overflow-x: hidden;
    width: 100%;
    height: 90vh;
  }
}
</style>