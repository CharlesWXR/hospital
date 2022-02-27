<template>
  <a-layout>
    <a-layout-header>
      <span class="logo-font">导医系统</span>
    </a-layout-header>
    <a-layout-content>
      <a-row style="width: 80%; margin-left: 10%; margin-top: 10px">
        <a-table
          style="width: 100%"
          :columns="columns"
          :dataSource="dataSource.data"
          :pagination="false"
          bordered
        >
          <template #bodyCell="{ column }">
            <template v-if="column.dataIndex === 'operation'">
              <a-button type="primary">就诊</a-button>
            </template>
          </template>
        </a-table>
      </a-row>
    </a-layout-content>
    <a-layout-footer></a-layout-footer>
  </a-layout>
</template>

<script>
import { reactive, getCurrentInstance } from "vue";

const columns = [
  {
    title: "姓名",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "性别",
    dataIndex: "gender",
    key: "gender",
  },
  {
    title: "年龄",
    dataIndex: "age",
    key: "age",
  },
  {
    title: "操作",
    dataIndex: "operation",
    key: "operation",
  },
];

export default {
  name: "DocDiagnosisView",
  setup() {
    const { appContext } = getCurrentInstance();
    const $http = appContext.config.globalProperties.$http;
    const $route = appContext.config.globalProperties.$route;
    const $router = appContext.config.globalProperties.$router;

    const dataSource = reactive({
      data: [],
    });
    $http.get("/exclude/user").then((response) => {
      let res = response.data;
      if (res.code === 200) {
        dataSource.data = res.result;
      } else {
        message.error("Unexpected error happened!");
      }
    });

    return {
      dataSource,
      columns,
    };
  },
};
</script>

<style scoped>
.logo-font {
  float: left;
  color: #409eff;
  font-size: 2rem;
}
</style>