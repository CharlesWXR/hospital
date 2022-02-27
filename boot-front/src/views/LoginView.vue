<template>
  <a-empty></a-empty>
</template>

<script>
import { getCurrentInstance, ref } from "vue";
import { message } from "ant-design-vue";

export default {
  name: "LoginView",
  setup() {
    const { appContext } = getCurrentInstance();
    const $http = appContext.config.globalProperties.$http;
    const $route = appContext.config.globalProperties.$route;
    const $router = appContext.config.globalProperties.$router;

    $http.get("/exclude/user/" + $route.params.token).then((response) => {
      let res = response.data;
      if (res.code === 200) {
        message.info("验证通过，跳转中……");
        $router.push("/navigator");
      } else {
        $router.push("/fail");
      }
    });
  },
};
</script>

<style>
</style>