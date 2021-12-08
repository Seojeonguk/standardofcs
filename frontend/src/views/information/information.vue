<template lang="">
  <div class="info-information">
    <div class="information-content infirst">각 서비스에 대한 소개는</div>
    <div class="information-content infirst">왼쪽에서 선택하여</div>
    <div class="information-content infirst">자세히 볼 수 있습니다.</div>
    <div class="information-content inempty"></div>
    <div class="information-content insecond">
      각 소개의
      <q-icon name="help_outline" style="color: red"></q-icon> 아이콘을 통해
    </div>
    <div class="information-content insecond">
      기능의 상세정보를 살펴볼 수 있습니다.
    </div>
    <div class="information-content inthird">
      <q-icon name="help_outline" style="color: gray"></q-icon> 아이콘을 통해
      상세정보 보기를 끌 수 있습니다.
    </div>
  </div>
</template>
<script>
import { onMounted } from "vue";

export default {
  name: "info-information",
  setup() {
    onMounted(() => {
      const menu = localStorage.getItem("menu");
      document.getElementById(menu).classList.add("click-menu");
    });

    return {
      onMounted,
    };
  },
};
</script>
<style lang="scss">
.info-information {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: auto;
  height: 100%;

  overflow: auto;
}
.information-content {
  font-size: 36px;
  font-weight: bold;
  &.inempty {
    margin: 1em;
    height: 32px;
  }
  &.infirst {
    margin: 0.25em;
  }
  &.insecond {
    font-size: 24px;
  }
  &.inthird {
    font-size: 24px;
    color: rgba(0, 0, 0, 0.3);
  }
}
</style>
