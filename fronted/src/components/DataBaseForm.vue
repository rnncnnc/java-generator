<script setup>
import { ref } from 'vue'
import { download } from '@/utils/request'

const formRef = ref()
const form = ref({
  database: '',
  host: '',
  port: '',
  schema: '',
  username: '',
  password: '',
  group: '',
  artifact: '',
  tablePrefix: '',
  fieldSeparator: ''
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
  group: [
    { required: true, message: '请输入groupId', trigger: 'blur' }
  ],
  artifact: [
    { required: true, message: '请输入artifactId', trigger: 'blur' }
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
    groupId: form.value.group,
    artifactId: form.value.artifact,
    tablePrefix: form.value.tablePrefix,
    fieldSeparator: form.value.fieldSeparator
  }

  download('/api/builder', params)
}
</script>

<template>
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
          <el-input v-model="form.password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item label="group" prop="group">
          <el-input v-model="form.group" placeholder="请输入groupId"/>
        </el-form-item>
        <el-form-item label="artifact" prop="artifact">
          <el-input v-model="form.artifact" placeholder="请输入artifactId"/>
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
</template>

<style lang="scss" scoped>

</style>