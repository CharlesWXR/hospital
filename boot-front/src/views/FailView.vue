<template>
  <a-result
    status="warning"
    title="There are some problems with your operation."
  >
    <div class="desc">
      <p style="font-size: 16px">
        <strong>The content you submitted has the following error:</strong>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        Your account has been frozen
        <a>Thaw immediately &gt;</a>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        Your account is not yet eligible to apply
        <a>Apply Unlock &gt;</a>
      </p>
    </div>
  </a-result>
</template>

<script>
import { CloseCircleOutlined } from "@ant-design/icons-vue";

export default {
  name: "FailView",
  components: {
    CloseCircleOutlined,
  },
};
</script>

<style>
</style>