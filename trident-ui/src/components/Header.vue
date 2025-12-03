<template>
  <div class="header">
    <span class="position-trip" @click="changeLang">{{ $t('U_LIST_0005') }}</span>
  </div>
</template>
<script lang="ts" setup>
defineProps({
  list: Array,
})

const changeLang = () => {}
</script>
<style lang="scss" scoped>
@use 'scss/_index.scss' as *;
.header {
  height: 60px;
  padding: 0 20px;
  .position-tip {
    display: inline-block;
    line-height: 60px;
    vertical-align: top;
    font-size: 14px;
  }
  .el-breadcrumb {
    display: inline-block;
    ::v-deep .el-breadcrumb__item {
      display: inline-block;
      line-height: 60px;
    }
    .item-link {
      font-weight: normal;
      color: $THEME_COLOR;
    }
  }
  .user-info {
    display: inline-block;
    // float: right;
    height: 40px;
    margin-top: 10px;
    & > .el-button {
      vertical-align: top;
      margin-right: 20px;
      line-height: 22px;
    }
    .language {
      vertical-align: top;
      line-height: 20px;
      margin-top: 10px;
    }
    ::v-deep .el-icon-user-solid {
      margin-left: 0;
      color: #fff;
    }
  }
  .el-dropdown-menu {
    ::v-deep .el-dropdown-menu__item {
      min-width: 100px;
      &:hover {
        background: $HOVER_BACKGROUND_COLOR !important;
        color: $THEME_COLOR !important;
      }
    }
  }
}
</style>
