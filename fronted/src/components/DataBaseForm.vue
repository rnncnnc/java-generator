<script setup>
import { ref } from 'vue'
import { getTableList } from '@/api/database'

import { useIndexStore } from '@/store/index'

import { ElMessage } from 'element-plus'


const formRef = ref()
const form = ref({
  database: '',
  host: '',
  port: '',
  schema: '',
  username: '',
  password: '',
  basePackage: '',
  tablePrefix: '',
  fieldSeparator: '',
})

const rules = ref({
  database: [
    { required: true, message: '请选择数据库类型', trigger: 'blur' }
  ],
  host: [
    { required: true, message: '请输入host', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9-._]+$/, message: '请输入正确的host', trigger: 'blur' }
  ],
  port: [
    { required: true, message: '请输入端口', trigger: 'blur' },
    { pattern: /^[0-9]+$/, message: '请输入数字', trigger: 'blur' }
  ],
  schema: [
    { required: true, message: '请输入schema', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ],
  basePackage: [
    { required: true, message: '请输入基础包名', trigger: 'blur' }
  ]
})

const options = ref([
  {
    value: 'mysql',
    label: 'MySQL'
  },
  {
    value: 'postgresql',
    label: 'PostgreSQL'
  }
])

// 下拉框的值变化时触发事件
const changeDatabase = (val) => {
  if (val === 'mysql') {
    form.value.port = '3306'
  } else if (val === 'postgresql') {
    form.value.port = '5432'
  }
}

const emit = defineEmits(['finished'])

// 提交表单
const submitForm = async () => {
  const valid = await formRef.value.validate()
  if (valid) {
    console.log(form.value)
  }

  const dbUrl = 'jdbc:' + form.value.database + '://' + form.value.host + ':' + form.value.port + '/' + form.value.schema

  const params = {
    dbType: form.value.database,
    dbUrl: dbUrl,
    username: form.value.username,
    password: form.value.password,
    tablePrefix: form.value.tablePrefix,
    fieldSeparator: form.value.fieldSeparator
  }

  const indexStore = useIndexStore()
  indexStore.setBasePackage(form.value.basePackage)

  getTableList(params).then(res => {
    ElMessage.success('获取数据库信息成功')

    emit('finished', res.data)
  }).catch(err => {
    console.log(err)
  })
}

defineExpose({
  clean: () => {
    form.value = {
      database: '',
      host: '',
      port: '',
      schema: '',
      username: '',
      password: '',
      basePackage: '',
      tablePrefix: '',
      fieldSeparator: '',
    }
  }
})
</script>

<template>
    <div class="box">
      <el-form ref="formRef" :model="form" :rules="rules" label-width="auto" style="max-width: 600px">
        <el-form-item label="database" prop="database">
          <el-select
            v-model="form.database"
            placeholder="请选择数据库类型"
            style="width: 240px"
            @change="changeDatabase"
            >
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
            </el-select>
        </el-form-item>
        <el-form-item label="host" prop="host">
          <el-input v-model="form.host" placeholder="请输入host"/>
        </el-form-item>
        <el-form-item label="port" prop="port">
          <el-input v-model="form.port" placeholder="请输入端口"/>
        </el-form-item>
        <el-form-item label="schema" prop="schema">
          <el-input v-model="form.schema" placeholder="请输入schema"/>
        </el-form-item>
        <el-form-item label="username" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"/>
        </el-form-item>
        <el-form-item label="password" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item label="base package" prop="basePackage">
          <el-input v-model="form.basePackage" placeholder="请输入基础包名"/>
        </el-form-item>
        <el-form-item label="table prefix" prop="tablePrefix">
          <el-input v-model="form.tablePrefix" placeholder="请输入表前缀"/>
        </el-form-item>
        <el-form-item label="field separator" prop="fieldSeparator">
          <el-input v-model="form.fieldSeparator" placeholder="请输入字段分隔符"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm" style="width: 100%;">生成代码</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<style lang="scss" scoped>
.box {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  margin-top: 100px;
}
</style>