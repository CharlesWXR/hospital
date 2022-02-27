<template>
  <a-layout>
    <a-layout-header class="btn-area">
      <span class="logo-font">导医系统</span>
      <a-button @click="onBack"> 返回 </a-button>
      <a-button type="primary" style="margin-left: 10px"> 保存 </a-button>
      <a-button type="primary" style="margin-left: 10px" @click="print">
        保存并打印
      </a-button>
    </a-layout-header>
    <a-layout-content>
      <a-typography-title>南京师范大学殡仪馆</a-typography-title>
      <a-row style="width: 80%; margin-left: 10%; align-items: center">
        <a-col :span="16">
          <a-descriptions>
            <a-descriptions-item label="姓名">
              {{ user.name }}
            </a-descriptions-item>
            <a-descriptions-item label="性别">
              {{ user.gender }}
            </a-descriptions-item>
            <a-descriptions-item label="年龄">
              {{ user.age }}
            </a-descriptions-item>
            <a-descriptions-item label="医生">
              {{ docName }}
            </a-descriptions-item>
            <a-descriptions-item label="诊断时间">
              {{ time }}
            </a-descriptions-item>
          </a-descriptions>
        </a-col>
        <a-col :span="8">
          <vue-qrcode :value="generateURL" :options="{ width: 100 }" />
        </a-col>
      </a-row>
      <a-row
        style="
          width: 100%;
          padding-left: 10%;
          padding-right: 10%;
          margin-top: 5px;
        "
      >
        <a-table
          :columns="columns"
          :data-source="data"
          :pagination="false"
          style="width: 100%"
          bordered
        >
          <template #bodyCell="{ column, text, record }">
            <template v-if="column.dataIndex === 'name'">
              <div class="editable-cell">
                <div class="editable-cell-text-wrapper">
                  {{ text || " " }}

                  <close-outlined
                    class="editable-cell-icon"
                    @click="onDelete(record.id)"
                  />
                </div>
              </div>
            </template>
            <template v-else-if="column.dataIndex === 'starve'">
              <div v-if="record.starve">是</div>
              <div v-else>否</div>
            </template>
            <template v-else-if="column.dataIndex === 'dependency'">
              <div v-if="record.dependency && record.dependency.length > 0">
                <span
                  v-for="dependency in record.dependency"
                  :key="dependency.name"
                >
                  {{ dependency.name }}
                </span>
              </div>
              <div v-else><span>无</span></div>
            </template>
          </template>
        </a-table>
      </a-row>
    </a-layout-content>
  </a-layout>
</template>

<script>
import { reactive, ref, getCurrentInstance } from "vue";
import { CloseOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import { pullAllBy } from "lodash";
import dayjs from "dayjs";
import qs from "qs";

const columns = [
  {
    title: "项目名称",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "前置项目",
    dataIndex: "dependecy",
    key: "dependency",
  },
  {
    title: "是否需要空腹",
    dataIndex: "starve",
    key: "starve",
    width: 150,
  },
];

export default {
  name: "DiagnosisResultView",
  components: {
    CloseOutlined,
  },
  computed: {
    generateURL() {
      return "http://localhost:8080/#/" + this.token;
    },
    time() {
      return dayjs().format("YYYY-MM-DD HH:mm:ss");
    },
  },
  setup() {
    const { appContext } = getCurrentInstance();
    const $store = appContext.config.globalProperties.$store;
    const $http = appContext.config.globalProperties.$http;
    const $router = appContext.config.globalProperties.$router;

    const onBack = () => {
      $router.push("/docDiagnosis");
    };

    const user = reactive({
      id: 5,
      name: "zby",
      gender: "male",
      age: "-1",
    });

    const docName = ref("zby");

    const print = () => {
      window.print();
    };

    const token = ref("");

    $http
      .post("/exclude/user", qs.stringify({ user_id: user.id }))
      .then((response) => {
        let res = response.data;
        if (res.code == 200) {
          token.value = res.result;
        } else {
          message.error("Unexpected error happend");
        }
      });

    const data = ref([]);
    $http.get("/exclude/inspection/" + user.id).then((response) => {
      let res = response.data;
      if (res.code == 200) {
        data.value = res.result;
      }
    });

    const onDelete = (id) => {
      pullAllBy(data.value, [{ id: id }], "id");
    };

    return {
      columns,
      data,

      onBack,

      user,
      print,
      token,
      docName,

      dayjs,

      onDelete,
    };
  },
};
</script>

<style scoped lang="scss">
@media print {
  .btn-area {
    display: none;
  }
}

.logo-font {
  float: left;
  color: #409eff;
  font-size: 2rem;
}

.editable-cell {
  position: relative;

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
    margin-top: 4px;
    display: none;
    color: red;
  }

  .editable-cell-icon:hover {
    color: #ff9d00;
  }
}
.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>