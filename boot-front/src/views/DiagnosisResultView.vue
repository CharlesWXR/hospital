<template>
  <a-layout>
    <a-layout-header class="btn-area">
      <a-button> 返回 </a-button>
      <a-button type="primary" style="margin-left: 10px" @click="print">
        打印
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
          <template #bodyCell="{ column, record }">
            <template v-if="column.dataIndex === 'starve'">
              <div v-if="record.starve">是</div>
              <div v-else>否</div>
            </template>
          </template>
        </a-table>
      </a-row>
    </a-layout-content>
  </a-layout>
</template>

<script>
import { reactive, ref } from "vue";
import dayjs from "dayjs";

const columns = [
  {
    title: "项目名称",
    dataIndex: "name",
    key: "name",
  },
  {
    title: "是否需要空腹",
    dataIndex: "starve",
    key: "starve",
  },
];

export default {
  name: "DiagnosisResultView",
  // props: [user, token, docName, data],
  component: {},
  computed: {
    generateURL() {
      return "http://localhost:8080/#/" + this.token;
    },
    time() {
      return dayjs().format("YYYY-MM-DD HH:mm:ss");
    },
  },
  setup() {
    const user = reactive({
      name: "zby",
      gender: "male",
      age: "-1",
    });

    const docName = ref("zby");

    const print = () => {
      window.print();
    };

    const token =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySUQiOjF9.uHaTBQBDDp0Oac33bGQvdlck8XOaBqmLhADuhPQKqyQ";

    const data = [
      {
        key: "1",
        name: "尿检",
        starve: true,
      },
      {
        key: "2",
        name: "x-光",
        starve: false,
      },
    ];

    return {
      columns,
      data,

      user,
      print,
      token,
      docName,

      dayjs,
    };
  },
};
</script>

<style scoped>
@media print {
  .btn-area {
    display: none;
  }
}
</style>