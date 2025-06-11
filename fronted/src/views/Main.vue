<script setup>
import DataBaseForm from '@/components/DataBaseForm.vue'

</script>

<template>
    <div class="main">
      <h1 class="title">Java 代码生成器</h1>
      <DataBaseForm />
    </div>
</template>

<style lang="scss" scoped>
.main {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
 // ... 其他保持不变的样式 ...
  width: calc(100% - 2px); // 补偿边框宽度
  height: calc(100vh - 20px); // 补偿边框高度
  margin: 0 auto; // 水平居中
  overflow: auto; // 添加滚动条控制
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px #ccc;

  .title {
    font-size: 60px;
  }
}
</style>